package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
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

public class SwordRegressionExerciseHistoryiOS {

    private final static String CHECK_EXERCISE_HISTORY_1 = "exercise_history_1";
    private final static String CHECK_EXERCISE_HISTORY_1_SCROLL = "exercise_history_1_scroll";
    private final static String CHECK_STARS_BOTTOM_SHEET = "stars_bottom_sheet";
    private final static String CHECK_TIME_BOTTOM_SHEET = "time_bottom_sheet";
    private final static String CHECK_EXERCISE_HISTORY_2 = "exercise_history_2";
    private final static String CHECK_EXERCISE_HISTORY_2_SCROLL = "exercise_history_2_scroll";
    private final static String VALIDATION_PATH = "/Users/luizaspiegel/Documents/image check/regression exercise history/iOS";
    private final static String BASELINE = "COMPARISION_";
    private IOSDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsiOS.getDriver();
    }

    @Test
    public void live() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver,20);
        UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
        MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
        VisualCheck visualCheck = new VisualCheck(driver);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //fazer login
        UtilitiesiOS.login("f.silva@swordhealth.com", "Cabixuda12", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
        //abrir session details
        MobileElement sessionsTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']");
        MobileElement ptCard = driver.findElementByAccessibilityId("home_card_pt");
        mobileActions.swipeByElements(sessionsTxt, ptCard);
        if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@value='Next Session']")).size() > 0) {
            utilitiesiOS.clickByAccessibilityId("home_card_session_details_0_prev_date_button", driver);
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_1_see_more_button")));
        utilitiesiOS.clickByAccessibilityId("home_card_session_details_1", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Exercises']")));
        //abrir exercise history do exercício 1
        utilitiesiOS.clickByAccessibilityId("Close", driver);
        utilitiesiOS.clickByAccessibilityId("session_details_exercise_0_card_see_more", driver);
        utilitiesiOS.clickByAccessibilityId("session_details_exercise_0_card_exercise_history", driver);
        //validar ecrã
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Exercise results']")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Trunk forward bend']");
        String exerciseDescription1 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='exercise_history_video_description']").getText();
        Assert.assertEquals("Improve flexibility, mobility, & strength in back, core, & spine for ease of bending forward", exerciseDescription1);
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Exercise results']");
        String totalStars1 = driver.findElementByAccessibilityId("exercise_history_results_total_stars_label").getText();
        Assert.assertEquals("Total stars", totalStars1);
        String totalTime1 = driver.findElementByAccessibilityId("exercise_history_results_total_time_label").getText();
        Assert.assertEquals("Total time", totalTime1);
        //teste visual
        byte[] exerciseHistoryComp1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        visualCheck.doVisualCheck(CHECK_EXERCISE_HISTORY_1);
        //clicar no vídeo
        utilitiesiOS.clickByAccessibilityId("exercise_history_video_description_content_view", driver);
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
        MobileElement starsPerSessionTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Stars per session']");
        MobileElement exerciseNameTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Trunk forward bend']");
        mobileActions.swipeByElements(starsPerSessionTxt, exerciseNameTxt);
        //validar ecrã
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Stars per session']");
        String lastSessionTxt1 = driver.findElementByAccessibilityId("exercise_history_results_label").getText();
        Assert.assertEquals("Last session results", lastSessionTxt1);
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']");
        visualCheck.doVisualCheck(CHECK_EXERCISE_HISTORY_1_SCROLL);
        //abrir bottom sheet das estrelas
        utilitiesiOS.clickByXPath("(//XCUIElementTypeButton[@name=\"moreInfoClearer\"])[1]", driver);
        //validar bottom sheet das estrelas
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Total stars']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"This is the total number of stars you've earned from this exercise.\"]");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Close']");
        visualCheck.doVisualCheck(CHECK_STARS_BOTTOM_SHEET);
        //fechar no close
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Close\"]", driver);
        //abrir bottom sheet do tempo
        utilitiesiOS.clickByXPath("(//XCUIElementTypeButton[@name=\"moreInfoClearer\"])[2]", driver);
        //validar bottom sheet do tempo
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Total time']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='This card shows the total amount of time you have spent doing this exercise.']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Close']");
        visualCheck.doVisualCheck(CHECK_TIME_BOTTOM_SHEET);
        //fechar clicando fora
        mobileActions.tapByCoordinates(222, 306);
        //abrir exercise history do exercício 2
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        utilitiesiOS.clickByAccessibilityId("session_details_exercise_1_card_see_more", driver);
        utilitiesiOS.clickByAccessibilityId("session_details_exercise_1_card_exercise_history", driver);
        //validar ecrã
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Trunk side bend']");
        String exerciseDescription2 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='exercise_history_video_description']").getText();
        Assert.assertEquals("Improve flexibility, mobility, & strength in back, core, & spine for ease of bending sideways", exerciseDescription2);
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Exercise results']");
        String totalStars2 = driver.findElementByAccessibilityId("exercise_history_results_total_stars_label").getText();
        Assert.assertEquals("Total stars", totalStars2);
        String totalTime2 = driver.findElementByAccessibilityId("exercise_history_results_total_time_label").getText();
        Assert.assertEquals("Total time", totalTime2);
        //teste visual
        byte[] exerciseHistoryComp3 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        visualCheck.doVisualCheck(CHECK_EXERCISE_HISTORY_2);
        //clicar no vídeo
        utilitiesiOS.clickByAccessibilityId("exercise_history_video_info_card", driver);
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
        starsPerSessionTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Stars per session']");
        exerciseNameTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Trunk side bend']");
        mobileActions.swipeByElements(starsPerSessionTxt, exerciseNameTxt);
        //validar ecrã
        String lastSessionTxt2 = driver.findElementByAccessibilityId("exercise_history_results_label").getText();
        Assert.assertEquals("Last session results", lastSessionTxt2);
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']");
        visualCheck.doVisualCheck(CHECK_EXERCISE_HISTORY_2_SCROLL);
        //voltar pra home
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']");

        ConfigurationsiOS.killDriver();
    }

}
