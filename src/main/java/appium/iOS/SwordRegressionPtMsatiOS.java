package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import io.appium.java_client.ios.IOSDriver;
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

public class SwordRegressionPtMsatiOS {

    private final static String VALIDATION_PATH = ConfigurationsiOS.VALIDATION_PATH;
    private final static String BASELINE = "COMP_";
    private final static String PTMSAT_SCREEN_EMPTY = "ptmsat_empty";
    private final static String PTMSAT_SCREEN_RATE_EXPERIENCE_ERROR = "ptmsat_rate_experience_error";
    private final static String PTMSAT_SCREEN_SELECT_OPTION_ERROR = "ptmsat_select_option_error";
    private final static String PTMSAT_SCREEN_SHARE_FEEDBACK_ERROR = "ptmsat_share_feedback_error";
    private final static String PTMSAT_SCREEN_FILLED = "ptmsat_filled";
    private final static String PTMSAT_SCREEN_5_STARS = "ptmsat_5_stars";
    private final static String PTMSAT_SCREEN_4_STARS = "ptmsat_4_stars";
    private final static String PTMSAT_SCREEN_3_STARS = "ptmsat_3_stars";
    private final static String PTMSAT_SCREEN_2_STARS = "ptmsat_2_stars";
    private final static String PTMSAT_SCREEN_1_STAR = "ptmsat_1_star";
    private final static String PTMSAT_SCREEN_SHARE_SUCCESS = "ptmsat_share_success";

    private IOSDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsiOS.getDriver();
    }

    @Test
    public void ptMsat() throws Exception {
        MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
        WebDriverWait wait = new WebDriverWait(driver,50);
        UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
        VisualCheck visualCheck = new VisualCheck(driver);

        System.out.println("Ligar proxy - Mudar PTMSAT para TRUE");
        //fazer login
        utilitiesiOS.newLogin("l.spiegel+3@swordhealth.com", "Test1234!", driver);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //validar ecrã PTMSAT
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='How would you rate your experience with Luiza Almeida?']"); //o nome do pt muda de acordo com o user
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='How could we improve your experience?']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Share your thoughts here']");
        driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Share feedback\"]");
        driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Later\"]");
        //teste visual
        visualCheck.doVisualCheck(PTMSAT_SCREEN_EMPTY);
        byte[] ptMSatEmpty = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        //Tap "Share feedback" button → Error message
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Share feedback\"]", driver);
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Please complete your experience rating']");
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
        assertThat(result1.getScore(), is(greaterThan(0.94)));
        System.out.println("Similarity of: " + result1.getScore());
        //Tap on the fourth star → Tap "Share feedback" button → Error message
        utilitiesiOS.clickByXPath("(//XCUIElementTypeImage[@name='starEmpty'])[4]", driver);
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Share feedback']", driver);
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Please make a selection above']");
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
        assertThat(result2.getScore(), is(greaterThan(0.71)));
        System.out.println("Similarity of: " + result2.getScore());
        //Select some options (other include) → Tap "Share feedback" button → Error message
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name=\"Other\"]", driver);
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Share feedback']", driver);
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Please share some feedback']");
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
        assertThat(result3.getScore(), is(greaterThan(0.85)));
        System.out.println("Similarity of: " + result3.getScore());
        //Write something on the text box
        MobileElement sendMessage = driver.findElementByXPath("//XCUIElementTypeTextView");
        sendMessage.click();
        sendMessage.sendKeys("Tests. Please ignore. Thank you!");
        //tap fora da caixa de texto
        mobileActions.tapByCoordinates(264, 256);
        //teste visual - comparar com build anterior
        visualCheck.doVisualCheck(PTMSAT_SCREEN_FILLED);
        //fazer scroll pra poder comparar direito
        MobileElement rateYourPtTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"How would you rate your experience with Luiza Almeida?\"]");
        MobileElement improveExperienceTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"How could we improve your experience?\"]");
        mobileActions.swipeByElements(rateYourPtTxt, improveExperienceTxt);
        //teste visual - comparar com o ptmsat_share_feedback_error
        byte[] ptMSatFilled = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result4 = driver
                .getImagesSimilarity(ptMSatFilled, ptMSatShareFeedbackError, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "ptmsat_share_feedback_error_vs_filled" + ".png";
        File comparison4 = new File(baselineFilename);
        result4.storeVisualization(comparison4);
        assertThat(result4.getVisualization().length, is(greaterThan(0)));
        assertThat(result4.getScore(), is(greaterThan(0.92)));
        System.out.println("Similarity of: " + result4.getScore());
        //Unselected the option "Other"
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Other']", driver);
        //Select all options
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Lack of expertise']", driver);
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Lack of support']", driver);
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Not responsive']", driver);
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Other']", driver);
        //Tap "Later button"
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Later\"]", driver);
        //Pull to refresh não mostra o pt msat -> matar e abrir manualmente
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //Tap 2x same star - Tap fifth star → tap on the same star - nothing should happen
        MobileElement fifthStarEmpty = driver. findElementByXPath("(//XCUIElementTypeImage[@name='starEmpty'])[5]");
        fifthStarEmpty.click();
        MobileElement fithStarFilled = driver.findElementByXPath("(//XCUIElementTypeImage[@name=\"star\"])[5]");
        fithStarFilled.click();
        //teste visual - build antiga
        visualCheck.doVisualCheck(PTMSAT_SCREEN_5_STARS);
        //teste visual - comparar ptmsat empty?
        byte[] ptMSat5Stars = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result5 = driver
                .getImagesSimilarity(ptMSat5Stars, ptMSatEmpty, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "ptmsat_5_stars_vs_empty" + ".png";
        File comparison5 = new File(baselineFilename);
        result5.storeVisualization(comparison5);
        assertThat(result5.getVisualization().length, is(greaterThan(0)));
        assertThat(result5.getScore(), is(greaterThan(0.88)));
        System.out.println("Similarity of: " + result5.getScore());
        //Tap one star (fifth one) → unselect one star → Tap on "Other" option → Select one more star
        MobileElement fourthStarFilled = driver.findElementByXPath("(//XCUIElementTypeImage[@name=\"star\"])[4]");
        fourthStarFilled.click();
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Other']", driver);
        utilitiesiOS.clickByXPath("//XCUIElementTypeImage[@name=\"starEmpty\"]", driver);
        fourthStarFilled.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //teste visual - comparar com build anterior
        visualCheck.doVisualCheck(PTMSAT_SCREEN_4_STARS);
        //teste visual - comparar com 5 estrelas
        byte[] ptMSat4Stars = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result6 = driver
                .getImagesSimilarity(ptMSat4Stars, ptMSat5Stars, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "ptmsat_4_stars_vs_5_stars" + ".png";
        File comparison6 = new File(baselineFilename);
        result6.storeVisualization(comparison6);
        assertThat(result6.getVisualization().length, is(greaterThan(0)));
        assertThat(result6.getScore(), is(greaterThan(0.77)));
        System.out.println("Similarity of: " + result6.getScore());
        MobileElement thirdStarFilled = driver.findElementByXPath("(//XCUIElementTypeImage[@name=\"star\"])[3]");
        thirdStarFilled.click();
        //teste visual - comparar com build anterior
        visualCheck.doVisualCheck(PTMSAT_SCREEN_3_STARS);
        //teste visual - comparar com 4 estrelas
        byte[] ptMSat3Stars = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result7 = driver
                .getImagesSimilarity(ptMSat3Stars, ptMSat4Stars, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "ptmsat_3_stars_vs_4_stars" + ".png";
        File comparison7 = new File(baselineFilename);
        result7.storeVisualization(comparison7);
        assertThat(result7.getVisualization().length, is(greaterThan(0)));
        assertThat(result7.getScore(), is(greaterThan(0.93)));
        System.out.println("Similarity of: " + result7.getScore());
        MobileElement secondStarFilled = driver.findElementByXPath("(//XCUIElementTypeImage[@name=\"star\"])[2]");
        secondStarFilled.click();
        //teste visual - comparar com build anterior
        visualCheck.doVisualCheck(PTMSAT_SCREEN_2_STARS);
        //teste visual - comparar com 3 estrelas
        byte[] ptMSat2Stars = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result8 = driver
                .getImagesSimilarity(ptMSat2Stars, ptMSat3Stars, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "ptmsat_2_stars_vs_3_stars" + ".png";
        File comparison8 = new File(baselineFilename);
        result8.storeVisualization(comparison8);
        assertThat(result8.getVisualization().length, is(greaterThan(0)));
        assertThat(result8.getScore(), is(greaterThan(0.93)));
        System.out.println("Similarity of: " + result8.getScore());
        MobileElement firstStar = driver.findElementByXPath("(//XCUIElementTypeImage[@name=\"star\"])[1]");
        firstStar.click();
        //teste visual - comparar com build anterior
        visualCheck.doVisualCheck(PTMSAT_SCREEN_1_STAR);
        //teste visual - comparar com 2 estrelas
        byte[] ptMSat1Star = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result9 = driver
                .getImagesSimilarity(ptMSat1Star, ptMSat2Stars, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "ptmsat_1_star_vs_2_stars" + ".png";
        File comparison9 = new File(baselineFilename);
        result9.storeVisualization(comparison9);
        assertThat(result9.getVisualization().length, is(greaterThan(0)));
        assertThat(result9.getScore(), is(greaterThan(0.93)));
        System.out.println("Similarity of: " + result9.getScore());
        //clicar em todas as estrelas de novo
        firstStar.click();
        utilitiesiOS.clickByXPath("(//XCUIElementTypeImage[@name=\"starEmpty\"])[2]", driver); //terceira estrela
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesiOS.clickByXPath("(//XCUIElementTypeImage[@name=\"starEmpty\"])[1]", driver); //quarta estrela
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesiOS.clickByXPath("(//XCUIElementTypeImage[@name=\"star\"])[2]", driver); //segunda estrela
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesiOS.clickByXPath("(//XCUIElementTypeImage[@name=\"starEmpty\"])[3]", driver); //quinta estrela
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //Tap "Share feedback" button
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Share feedback\"]", driver);
        //validar ecrã de sucesso - vai sumir depois do merge das alterações da bloom
        //driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Thank you!']");
       // driver.findElementByXPath("//XCUIElementTypeStaticText[@name='We appreciate your feedback.']");
        //driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Close']");
        //toaster de sucesso - implementado depois das alterações da bloom
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Thank you! We appreciate your feedback.\"]");
        //teste visual - comparar com build anterior
        visualCheck.doVisualCheck(PTMSAT_SCREEN_SHARE_SUCCESS);
        //Tap "Close" button - também vai sumir depois do merge das alterações da bloom
        //utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Close\"]",driver);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Your Physical Therapist']")));

        ConfigurationsiOS.killDriver();
    }
}
