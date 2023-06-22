package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.net.MalformedURLException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class SwordRegressionExerciseHistory {

    private final static String CHECK_EXERCISE_HISTORY_1 = "exercise_history_1";
    private final static String CHECK_EXERCISE_HISTORY_1_SCROLL = "exercise_history_1_scroll";
    private final static String CHECK_STARS_BOTTOM_SHEET = "stars_bottom_sheet";
    private final static String CHECK_TIME_BOTTOM_SHEET = "time_bottom_sheet";
    private final static String CHECK_EXERCISE_HISTORY_2 = "exercise_history_2";
    private final static String CHECK_EXERCISE_HISTORY_2_SCROLL = "exercise_history_2_scroll";
    private final static String VALIDATION_PATH = ConfigurationsAndroid.VALIDATION_PATH;
    private final static String BASELINE = "COMPARISION_";

    private AndroidDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsAndroid.getDriver();
    }

    @Test
    public void live() throws Exception {
        MobileActions mobileActions = new MobileActions(driver);
        WebDriverWait wait = new WebDriverWait(driver,50);
        UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
        VisualCheck visualCheckAndroid = new VisualCheck(driver);

        //fazer login
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesAndroid.login("f.silva@swordhealth.com", "Cabixuda12", driver);
        //abrir session details
        MobileElement sessionsTxt = driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");
        MobileElement weeklyGoalTxt = driver.findElementByXPath("//android.widget.TextView[@text='Weekly goal']");
        mobileActions.swipeByElements(sessionsTxt, weeklyGoalTxt);
        utilitiesAndroid.clickByAccessibilityId("home_card_session_details_0_prev_date_button", driver);
        utilitiesAndroid.clickByXPath("//android.view.View[@content-desc=\"home_card_session_details_1\"]/android.view.View/android.view.View", driver);
        //abrir exercise history do exercício 1
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[1]/android.view.View[2]/android.widget.Button", driver);
        utilitiesAndroid.clickByAccessibilityId("session_details_exercise_0_card_exercise_history", driver);
        //validar ecrã
        driver.findElementByXPath("//android.widget.TextView[@text='Trunk forward bend']");
        driver.findElementByXPath("//android.widget.TextView[@text='Improve flexibility, mobility, & strength in back, core, & spine for ease of bending forward']");
        driver.findElementByXPath("//android.widget.TextView[@text='Exercise results']");
        driver.findElementByXPath("//android.widget.TextView[@text='Total stars']");
        driver.findElementByXPath("//android.widget.TextView[@text='Total time']");
        //teste visual
        byte[] exerciseHistoryComp1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        visualCheckAndroid.doVisualCheck(CHECK_EXERCISE_HISTORY_1);
        //clicar no vídeo
        utilitiesAndroid.clickByAccessibilityId("exercise_history_video_info_card", driver);
        //validar que fechou - teste visual
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        byte[] exerciseHistoryComp2 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result1 = driver
                .getImagesSimilarity(exerciseHistoryComp1, exerciseHistoryComp2, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        assertThat(result1.getVisualization().length, is(greaterThan(0)));
        assertThat(result1.getScore(), is(greaterThan(0.95)));
        String baselineFilename = VALIDATION_PATH + "/" + BASELINE + "1.png";
        File comparison = new File(baselineFilename);
        result1.storeVisualization(comparison);
        System.out.println("Similarity of: " + result1.getScore());
        //fazer scroll
        MobileElement starsPerSessionTxt = driver.findElementByXPath("//android.widget.TextView[@text='Stars per session']");
        MobileElement exerciseNameTxt = driver.findElementByXPath("//android.widget.TextView[@text='Trunk forward bend']");
        mobileActions.swipeByElements(starsPerSessionTxt, exerciseNameTxt);
        //validar ecrã
        driver.findElementByXPath("//android.widget.TextView[@text='Last session results']");
        driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");
        visualCheckAndroid.doVisualCheck(CHECK_EXERCISE_HISTORY_1_SCROLL);
        //abrir bottom sheet das estrelas
        utilitiesAndroid.clickByXPath("//android.view.View[@content-desc=\"exercise_history_results_total_stars_card\"]/android.view.View/android.widget.Button", driver);
        //validar bottom sheet das estrelas
        driver.findElementByXPath("//android.widget.TextView[@text='Total stars']");
        driver.findElementByXPath("//android.widget.TextView[@text=\"This is the total number of stars you've earned from this exercise.\"]");
        driver.findElementByXPath("//android.widget.TextView[@text='Close']");
        visualCheckAndroid.doVisualCheck(CHECK_STARS_BOTTOM_SHEET);
        //fechar no close
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View[2]/android.view.View/android.widget.Button", driver);
        //abrir bottom sheet do tempo
        utilitiesAndroid.clickByXPath("//android.view.View[@content-desc=\"exercise_history_results_total_time_card\"]/android.view.View/android.widget.Button", driver);
        //validar bottom sheet do tempo
        driver.findElementByXPath("//android.widget.TextView[@text='Total time']");
        driver.findElementByXPath("//android.widget.TextView[@text='This card shows the total amount of time you have spent doing this exercise.']");
        driver.findElementByXPath("//android.widget.TextView[@text='Close']");
        visualCheckAndroid.doVisualCheck(CHECK_TIME_BOTTOM_SHEET);
        //fechar clicando fora
        mobileActions.tapByCoordinates(593, 797);
        //abrir exercise history do exercício 2
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.view.View[2]/android.widget.Button", driver);
        utilitiesAndroid.clickByAccessibilityId("session_details_exercise_1_card_exercise_history", driver);
        //validar ecrã
        driver.findElementByXPath("//android.widget.TextView[@text='Trunk side bend']");
        driver.findElementByXPath("//android.widget.TextView[@text='Improve flexibility, mobility, & strength in back, core, & spine for ease of bending sideways']");
        driver.findElementByXPath("//android.widget.TextView[@text='Exercise results']");
        driver.findElementByXPath("//android.widget.TextView[@text='Total stars']");
        driver.findElementByXPath("//android.widget.TextView[@text='Total time']");
        //teste visual
        byte[] exerciseHistoryComp3 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        visualCheckAndroid.doVisualCheck(CHECK_EXERCISE_HISTORY_2);
        //clicar no vídeo
        utilitiesAndroid.clickByAccessibilityId("exercise_history_video_info_card", driver);
        //validar que fechou - teste visual
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        byte[] exerciseHistoryComp4 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result2 = driver
                .getImagesSimilarity(exerciseHistoryComp3, exerciseHistoryComp4, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        assertThat(result2.getVisualization().length, is(greaterThan(0)));
        assertThat(result2.getScore(), is(greaterThan(0.95)));
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "2.png";
        comparison = new File(baselineFilename);
        result2.storeVisualization(comparison);
        System.out.println("Similarity of: " + result2.getScore());
        //fazer scroll
        starsPerSessionTxt = driver.findElementByXPath("//android.widget.TextView[@text='Stars per session']");
        exerciseNameTxt = driver.findElementByXPath("//android.widget.TextView[@text='Trunk side bend']");
        mobileActions.swipeByElements(starsPerSessionTxt, exerciseNameTxt);
        //validar ecrã
        driver.findElementByXPath("//android.widget.TextView[@text='Last session results']");
        driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");
        visualCheckAndroid.doVisualCheck(CHECK_EXERCISE_HISTORY_2_SCROLL);
        //voltar pra home
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
        driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");

        ConfigurationsAndroid.killDriver();
    }
}
