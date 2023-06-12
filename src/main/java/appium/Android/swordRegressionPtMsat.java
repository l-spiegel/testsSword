package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class swordRegressionPtMsat {

    private final static String VALIDATION_PATH = "/Users/luizaspiegel/Documents/image check/regression pt msat/Android";
    private final static String BASELINE = "COMP_";
    private final static String PTMSAT_SCREEN_EMPTY = "ptmsat_empty";
    private final static String PTMSAT_SCREEN_RATE_EXPERIENCE_ERROR = "ptmsat_rate_experience_error";
    private final static String PTMSAT_SCREEN_SELECT_OPTION_ERROR = "ptmsat_select_option_error";
    private final static String PTMSAT_SCREEN_SHARE_FEEDBACK_ERROR = "ptmsat_share_feedback_error";

    private AndroidDriver<MobileElement> driver;

    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsAndroid.getDriver();
    }
    @Test
    public void PTmsat() throws Exception {
        MobileActions mobileActions = new MobileActions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
        VisualCheck visualCheck = new VisualCheck(driver);

        System.out.println("Ligar proxy - Mudar PTMSAT para TRUE");
        //fazer login
        utilitiesAndroid.login("l.spiegel+3@swordhealth.com", "Test1234!", driver);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //validar ecrã PTMSAT
        driver.findElementByXPath("//android.widget.TextView[@text='How would you rate your experience with Luiza Almeida?']"); //o nome do pt muda de acordo com o user
        driver.findElementByXPath("//android.widget.TextView[@text='How could we improve your experience?']");
        driver.findElementByXPath("//android.widget.TextView[@text='Share your thoughts here']");
        driver.findElementByXPath("//android.widget.TextView[@text='Share feedback']");
        driver.findElementByXPath("//android.widget.TextView[@text='Later']");
        //teste visual
        visualCheck.doVisualCheck(PTMSAT_SCREEN_EMPTY);
        byte[] ptMSatEmpty = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        //Tap "Share feedback" button → Error message
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.Button", driver);
        driver.findElementByXPath("//android.widget.TextView[@text='Please rate your experience']");
        //teste visual - comparar com build anterior
        visualCheck.doVisualCheck(PTMSAT_SCREEN_RATE_EXPERIENCE_ERROR);
        //teste visual - comparar com o ptmsat_empty
        byte[] ptMSatRateExperienceError = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result1 = driver
                .getImagesSimilarity(ptMSatRateExperienceError, ptMSatEmpty, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        String baselineFilename = VALIDATION_PATH + "/" + BASELINE + "ptmsat_rate_experience_error_vs_empty" + ".png";
        File comparison1 = new File(baselineFilename);
        result1.storeVisualization(comparison1);
        assertThat(result1.getVisualization().length, is(greaterThan(0)));
        assertThat(result1.getScore(), is(greaterThan(0.95)));
        System.out.println("Similarity of: " + result1.getScore());
        //Tap on the fourth star → Tap "Share feedback" button → Error message
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[4]/android.view.View", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.Button", driver);
        driver.findElementByXPath("//android.widget.TextView[@text='Please select at least one option']");
        //teste visual - comparar com build anterior
        visualCheck.doVisualCheck(PTMSAT_SCREEN_SELECT_OPTION_ERROR);
        //teste visual - comparar com o ptmsat_rate_experience_error
        byte[] ptMSatSelectOptionError = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result2 = driver
                .getImagesSimilarity(ptMSatSelectOptionError, ptMSatRateExperienceError, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "ptmsat_select_option_error_vs_rate_experience_error" + ".png";
        File comparison2 = new File(baselineFilename);
        result2.storeVisualization(comparison2);
        assertThat(result2.getVisualization().length, is(greaterThan(0)));
        assertThat(result2.getScore(), is(greaterThan(0.88)));
        System.out.println("Similarity of: " + result2.getScore());
        //Select some options (other include) → Tap "Share feedback" button → Error message
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[6]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.Button", driver);
        driver.findElementByXPath("//android.widget.TextView[@text='Please share some feedback']");
        //teste visual - comparar com build anterior
        visualCheck.doVisualCheck(PTMSAT_SCREEN_SHARE_FEEDBACK_ERROR);
        //teste visual - comparar com o ptmsat_select_option_error
        byte[] ptMSatShareFeedbackError = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result3 = driver
                .getImagesSimilarity(ptMSatShareFeedbackError, ptMSatSelectOptionError, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "ptmsat_share_feedback_error_vs_select_option_error" + ".png";
        File comparison3 = new File(baselineFilename);
        result3.storeVisualization(comparison3);
        assertThat(result3.getVisualization().length, is(greaterThan(0)));
        assertThat(result3.getScore(), is(greaterThan(0.93)));
        System.out.println("Similarity of: " + result3.getScore());
        //Write something on the text box
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.Button", driver);
        MobileElement sendMessage = driver.findElementByXPath("//android.widget.EditText");
        sendMessage.click();
        sendMessage.sendKeys("Tests. Please ignore. Thank you!");
        //tap fora da caixa de texto

        //teste visual - comparar com build anterior

        //teste visual - comparar com o ptmsat_share_feedback_error

        //Unselected the option "Other"
        utilitiesAndroid.clickByXPath("//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[1]", driver);
        //Select all options
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[1]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[3]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[4]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[5]", driver);
        //Tap "Later button"
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[2]", driver);

        //Pull to refresh
        MobileElement ptCard = driver.findElementByAccessibilityId("home_card_pt");
        MobileElement weeklyCard = driver.findElementByAccessibilityId("home_card_weekly_goal");
        mobileActions.swipeByElements(ptCard, weeklyCard);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //Tap 2x same star - Tap fifth star → tap on the same star - nothing should happen
        //Tap one star (fifth one) → unselect one star → Tap on "Other" option → Select one more star
        //Unselect one star → unselect another star → unselect another star → unselect another star
        //Select one more star → Select one more star → Select one more star → Select one more star
        //Tap "Share feedback" button → Tap "Close" button

        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[5]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[5]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[4]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[6]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[6]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[5]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[4]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[3]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[2]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[1]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[2]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[3]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[4]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[5]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]", driver);

        driver.findElementByXPath("//android.widget.TextView[@text='Thank you!']");
        driver.findElementByXPath("//android.widget.TextView[@text='We appreciate your feedback.']");
        driver.findElementByXPath("//android.widget.TextView[@text='Close']");

        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View/android.widget.Button",driver);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Your Physical Therapist']")));
        ConfigurationsAndroid.killDriver();
    }
}