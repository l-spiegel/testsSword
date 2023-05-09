package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import java.io.File;

public class VisualCheck {

    private final static String VALIDATION_PATH = "/Users/luizaspiegel/Documents/image check/regression oncall/Android/preventive";
    private final static String BASELINE = "BASELINE_";
    private final static double MATCH_THRESHOLD = 0.95;

    private static AndroidDriver<MobileElement> driver;
    public VisualCheck (AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }
    public static void doVisualCheck(String checkName) throws Exception {

        String baselineFilename = VALIDATION_PATH + "/" + BASELINE + checkName + ".png";
        File baselineImg = new File(baselineFilename);

        // If no baseline image exists for this check, we should create a baseline image
        if (!baselineImg.exists()) {
            System.out.println(String.format("No baseline found for '%s' check; capturing baseline instead of checking", checkName));
            File newBaseline = driver.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(newBaseline, new File(baselineFilename));
            return;
        }

        // Otherwise, if we found a baseline, get the image similarity from Appium. In getting the similarity,
        // we also turn on visualization so we can see what went wrong if something did.

        SimilarityMatchingOptions opts = new SimilarityMatchingOptions();
        opts.withEnabledVisualization();

        SimilarityMatchingResult res = driver.getImagesSimilarity(baselineImg, driver.getScreenshotAs(OutputType.FILE), opts);

        // If the similarity is not high enough, consider the check to have failed
        if (res.getScore() < MATCH_THRESHOLD) {
            File failViz = new File(VALIDATION_PATH + "/FAIL_" + checkName + ".png");
            res.storeVisualization(failViz);

            throw new Exception(
                    String.format("Visual check of '%s' failed; similarity match was only %f, and below the threshold of %f. Visualization written to %s.",
                            checkName, res.getScore(), MATCH_THRESHOLD, failViz.getAbsolutePath()));
        }

        // Otherwise, it passed!
        System.out.println(String.format("Visual check of '%s' passed; similarity match was %f",
                checkName, res.getScore()));
        File successViz = new File(VALIDATION_PATH + "/SUCCESS_" + checkName + ".png");
        res.storeVisualization(successViz);
    }
}
