package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
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

public class SwordRegressionSettings {

    private AndroidDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsAndroid.getDriver();
    }
    private final static String VALIDATION_PATH = "/Users/filipasilva/Documents/GitHub/testsSword/src/visual tests/M31/Settings";
    private final static String BASELINE = "COMP_";
    private final static String CHECK_HOME_SETTINGS_WITHOUT_PIN= "home_settings_without_pin";
    private final static String CHECK_HOME_SETTINGS_WITH_PIN= "home_settings_with_pin";
    private final static String CHECK_SETTINGS_WITHOUT_PIN = "settings_without_pin";
    private final static String CHECK_SETTINGS_CREATE_PIN = "settings_create_pin";
    private final static String CHECK_SETTINGS_CONFIRM_PIN = "settings_confirm_pin";
    private final static String CHECK_PIN_DIDNT_MATCH_SETTINGS = "settings_pin_didnt_match";
    private final static String CHECK_ACTIVATE_BIOMETRICS_SETTINGS = "settings_activate_biometrics";
    private final static String CHECK_NEW_PIN_SUCCESS_SETTINGS_1 = "settings_new_pin_success_1";
    private final static String CHECK_SETTINGS_WITH_PIN = "settings_with_pin";
    private final static String CHECK_BIOMETRICS_TOGGLE = "settings_biometrics_toggle";
    private final static String CHECK_ENTER_PIN_SETTINGS = "settings_enter_pin";
    private final static String CHECK_NEW_PIN_SUCCESS_SETTINGS_2 = "settings_new_pin_success_2";
    private final static String CHECK_WRONG_PIN_SETTINGS_4_ATTEMPTS = "wrong_pin_4_attempts";
    private final static String CHECK_WRONG_PIN_SETTINGS_3_ATTEMPTS = "wrong_pin_3_attempts";
    private final static String CHECK_WRONG_PIN_SETTINGS_2_ATTEMPTS = "wrong_pin_2_attempts";
    private final static String CHECK_WRONG_PIN_SETTINGS_1_ATTEMPTS = "wrong_pin_1_attempts";
    private final static String CHECK_WRONG_PIN_SETTINGS_0_ATTEMPTS = "wrong_pin_0_attempts";
    private final static String CHECK_LOGIN_SCREEN = "login_screen";

    @Test
    public void DPT() throws Exception {
        MobileActions mobileActions = new MobileActions(driver);
        WebDriverWait wait = new WebDriverWait(driver,20);
        VisualCheck visualCheck = new VisualCheck(driver);
        UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();

        //login
        utilitiesAndroid.newLogin("filipa@pn3.com", "Test1234!", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Spiegel']")));
        //validar red dot nas settings
        VisualCheck.doVisualCheck(CHECK_HOME_SETTINGS_WITHOUT_PIN);
        //clicar nas settings
        utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        visualCheck.doVisualCheck(CHECK_SETTINGS_WITHOUT_PIN);
        //clicar pra deletar a conta
        utilitiesAndroid.clickByAccessibilityId("settings_delete_account", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Delete account']")));
        //voltar e validar que a red dot continua nas settings
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.Button", driver);
        VisualCheck.doVisualCheck(CHECK_HOME_SETTINGS_WITHOUT_PIN);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //abrir settings de novo
        utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
        //clicar pra definir pin
        utilitiesAndroid.clickByAccessibilityId("menu_option_define_pin", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create PIN code']")));
        //voltar e validar que a red dot continua nas settings
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Spiegel']")));
        VisualCheck.doVisualCheck(CHECK_HOME_SETTINGS_WITHOUT_PIN);
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
        //clicar pra definir pin
        utilitiesAndroid.clickByAccessibilityId("menu_option_define_pin", driver);
        //inserir 3 digitos
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create PIN code']")));
        visualCheck.doVisualCheck(CHECK_SETTINGS_CREATE_PIN);
        byte[] createPinSettings1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        MobileElement number1CreatePin = driver.findElementByXPath("//android.widget.TextView[@text='1']");
        number1CreatePin.click();
        number1CreatePin.click();
        number1CreatePin.click();
        //comparar com o createpin1 - vazio
        byte[] createPinSettings2 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result1 = driver
                .getImagesSimilarity(createPinSettings2, createPinSettings1, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        String baselineFilename = VALIDATION_PATH + "/" + BASELINE + "settings_create_pin_filled" + ".png";
        File comparison1 = new File(baselineFilename);
        result1.storeVisualization(comparison1);
        assertThat(result1.getVisualization().length, is(greaterThan(0)));
        assertThat(result1.getScore(), is(greaterThan(0.85)));
        System.out.println("settings_create_pin_filled similarity of: " + result1.getScore());
        //apagar 1
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[12]", driver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //comparar com o createpin2
        byte[] createPinSettings3 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result2 = driver
                .getImagesSimilarity(createPinSettings3, createPinSettings2, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "settings_create_pin_erase" + ".png";
        File comparison2 = new File(baselineFilename);
        result2.storeVisualization(comparison2);
        assertThat(result2.getVisualization().length, is(greaterThan(0)));
        assertThat(result2.getScore(), is(greaterThan(0.90)));
        System.out.println("settings_create_pin_filled_delete_one similarity of: " + result2.getScore());
        //terminar de definir o pin
        number1CreatePin.click();
        number1CreatePin.click();
        //voltar pra create pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create PIN code']")));
        //comparar com o createpin1
        byte[] createPinSettings4 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result3 = driver
                .getImagesSimilarity(createPinSettings4, createPinSettings1, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "settings_create_pin1" + ".png";
        File comparison3 = new File(baselineFilename);
        result3.storeVisualization(comparison3);
        assertThat(result3.getVisualization().length, is(greaterThan(0)));
        assertThat(result3.getScore(), is(greaterThan(0.95)));
        System.out.println("settings_create_pin1 similarity of: " + result3.getScore());
        //inserir 4 digitos
        MobileElement number2CreatePin = driver.findElementByXPath("//android.widget.TextView[@text='2']");
        number2CreatePin.click();
        number2CreatePin.click();
        number2CreatePin.click();
        number2CreatePin.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
        visualCheck.doVisualCheck(CHECK_SETTINGS_CONFIRM_PIN);
        //comparar com o createpin1
        byte[] confirmPinSettings1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result4 = driver
                .getImagesSimilarity(confirmPinSettings1, createPinSettings1, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "settings_confirm_pin_vs_create_pin" + ".png";
        File comparison4 = new File(baselineFilename);
        result4.storeVisualization(comparison4);
        assertThat(result4.getVisualization().length, is(greaterThan(0)));
        assertThat(result4.getScore(), is(greaterThan(0.79)));
        System.out.println("settings_confirm_pin_vs_create_pin similarity of: " + result4.getScore());
        //inserir 4 digitos diferentes no confirm pin
        MobileElement number5ConfirmPin = driver.findElementByXPath("//android.widget.TextView[@text='5']");
        number5ConfirmPin.click();
        number5ConfirmPin.click();
        //comparar com o confirmpin2
        byte[] confirmPinSettings2 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result5 = driver
                .getImagesSimilarity(confirmPinSettings2, confirmPinSettings1, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "settings_confirm_pin_filled" + ".png";
        File comparison5 = new File(baselineFilename);
        result5.storeVisualization(comparison5);
        assertThat(result5.getVisualization().length, is(greaterThan(0)));
        assertThat(result5.getScore(), is(greaterThan(0.90)));
        System.out.println("settings_confirm_pin_filled similarity of: " + result5.getScore());
        number5ConfirmPin.click();
        number5ConfirmPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        driver.findElementByXPath("//android.widget.TextView[@text=\"Uh-oh! The PIN codes didn't match. Please try again.\"]");
        driver.findElementByXPath("//android.widget.TextView[@text='Retry']");
        visualCheck.doVisualCheck(CHECK_PIN_DIDNT_MATCH_SETTINGS);
        byte[] pinDidntMatchErrorCreatePin = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        //clicar em retry
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create PIN code']")));
        byte[] createPinSettings5 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        //comparar com o createPinSettings1
        SimilarityMatchingResult result6 = driver
                .getImagesSimilarity(createPinSettings5, createPinSettings1, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "settings_create_pin2" + ".png";
        File comparison6 = new File(baselineFilename);
        result6.storeVisualization(comparison6);
        assertThat(result6.getVisualization().length, is(greaterThan(0)));
        assertThat(result6.getScore(), is(greaterThan(0.95)));
        System.out.println("settings_create_pin2 similarity of: " + result6.getScore());
        //inserir 4 digitos no create your pin
        MobileElement number0CreatePin = driver.findElementByXPath("//android.widget.TextView[@text='0']");
        number0CreatePin.click();
        number0CreatePin.click();
        number0CreatePin.click();
        number0CreatePin.click();
        //inserir os mesmos 4 digitos no confirm pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
        MobileElement number0ConfirmPin = driver.findElementByXPath("//android.widget.TextView[@text='0']");
        number0ConfirmPin.click();
        number0ConfirmPin.click();
        number0ConfirmPin.click();
        number0ConfirmPin.click();
        //validar ecrã de biometria
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        driver.findElementByXPath("//android.widget.TextView[@text=\"Want to use biometrics for future logins? You can activate it now, or activate it later in Settings.\"]");
        driver.findElementByXPath("//android.widget.TextView[@text=\"Activate now\"]");
        driver.findElementByXPath("//android.widget.TextView[@text=\"Activate later\"]");
        visualCheck.doVisualCheck(CHECK_ACTIVATE_BIOMETRICS_SETTINGS);
        //clicar pra ativar depois
        utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='Activate later']", driver);
        //validar ecrã de sucesso
        driver.findElementByXPath("//android.widget.TextView[@text=\"New PIN code set successfully\"]");
        visualCheck.doVisualCheck(CHECK_NEW_PIN_SUCCESS_SETTINGS_1);
        // validar que nas settings já não tem red dot
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Spiegel']")));
        visualCheck.doVisualCheck(CHECK_HOME_SETTINGS_WITH_PIN);
        //abrir settings e validar sem a red dot
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
        visualCheck.doVisualCheck(CHECK_SETTINGS_WITH_PIN);
        //clicar pra ativar biometria
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Login with biometrics']")));
        visualCheck.doVisualCheck(CHECK_SETTINGS_WITH_PIN);
        byte[] biometricsToggleOff1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        MobileElement biometricsToggle = driver.findElementByAccessibilityId("menu_option_login_biometrics");
        biometricsToggle.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        visualCheck.doVisualCheck(CHECK_BIOMETRICS_TOGGLE);
        byte[] biometricsToggleOn = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result7 = driver
                .getImagesSimilarity(biometricsToggleOff1, biometricsToggleOn, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "toggle_on_off" + ".png";
        File comparison7 = new File(baselineFilename);
        result7.storeVisualization(comparison7);
        assertThat(result7.getVisualization().length, is(greaterThan(0)));
        assertThat(result7.getScore(), is(greaterThan(0.95)));
        System.out.println("toggle_on_off similarity of: " + result7.getScore());
        //clicar de novo na biometria
        biometricsToggle.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        byte[] biometricsToggleOff2 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result8 = driver
                .getImagesSimilarity(biometricsToggleOff1, biometricsToggleOff2, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "toggle_off" + ".png";
        File comparison8 = new File(baselineFilename);
        result8.storeVisualization(comparison8);
        assertThat(result8.getVisualization().length, is(greaterThan(0)));
        assertThat(result8.getScore(), is(greaterThan(0.95)));
        System.out.println("toggle_off similarity of: " + result8.getScore());
        //clicar em change pin
        utilitiesAndroid.clickByAccessibilityId("menu_option_change_pin", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        driver.findElementByXPath("//android.widget.TextView[@text='Forgot your PIN?']");
        visualCheck.doVisualCheck(CHECK_ENTER_PIN_SETTINGS);
        //voltar
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
        visualCheck.doVisualCheck(CHECK_SETTINGS_WITH_PIN);
        //clicar em change pin
        utilitiesAndroid.clickByAccessibilityId("menu_option_change_pin", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        byte[] enterPinSettings1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        //digitar 3 digitos corretos
        MobileElement number0EnterPin1 = driver.findElementByXPath("//android.widget.TextView[@text='0']");
        number0EnterPin1.click();
        number0EnterPin1.click();
        number0EnterPin1.click();
        byte[] enterPinSettings2 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        //comparar com o enterpin1
        SimilarityMatchingResult result9 = driver
                .getImagesSimilarity(enterPinSettings2, enterPinSettings1, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "enter_pin_filled" + ".png";
        File comparison9 = new File(baselineFilename);
        result9.storeVisualization(comparison9);
        assertThat(result9.getVisualization().length, is(greaterThan(0)));
        assertThat(result9.getScore(), is(greaterThan(0.90)));
        System.out.println("enter_pin_filled similarity of: " + result9.getScore());
        //apagar 1 digito
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[12]", driver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        byte[] enterPinSettings3 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        //comparar com o enterpin2
        SimilarityMatchingResult result10 = driver
                .getImagesSimilarity(enterPinSettings3, enterPinSettings2, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "enter_pin_erase" + ".png";
        File comparison10 = new File(baselineFilename);
        result10.storeVisualization(comparison10);
        assertThat(result10.getVisualization().length, is(greaterThan(0)));
        assertThat(result10.getScore(), is(greaterThan(0.92)));
        System.out.println("enter_pin_erase similarity of: " + result10.getScore());
        //terminar de inserir o pin correto
        number0EnterPin1.click();
        number0EnterPin1.click();
        //voltar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create PIN code']")));
        byte[] createPinSettings6 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        //comparação com create pin de criar o pin pela 1ª vez
        SimilarityMatchingResult result11 = driver
                .getImagesSimilarity(createPinSettings6, createPinSettings1, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "settings_create_pin_after_enter_pin" + ".png";
        File comparison11 = new File(baselineFilename);
        result11.storeVisualization(comparison11);
        assertThat(result11.getVisualization().length, is(greaterThan(0)));
        assertThat(result11.getScore(), is(greaterThan(0.95)));
        System.out.println("settings_create_pin_after_enter_pin similarity of: " + result11.getScore());
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        //abrir settings e validar sem red dot
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
        visualCheck.doVisualCheck(CHECK_SETTINGS_WITH_PIN);
        //clicar em change pin
        utilitiesAndroid.clickByAccessibilityId("menu_option_change_pin", driver);
        //inserir 4 digitos no enter pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        MobileElement number0EnterPin2 = driver.findElementByXPath("//android.widget.TextView[@text='0']");
        number0EnterPin2.click();
        number0EnterPin2.click();
        number0EnterPin2.click();
        number0EnterPin2.click();
        //inserir 4 digitos no create pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create PIN code']")));
        MobileElement number3CreatePin = driver.findElementByXPath("//android.widget.TextView[@text='3']");
        number3CreatePin.click();
        number3CreatePin.click();
        number3CreatePin.click();
        number3CreatePin.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
        //comparação com o confirmpin1
        byte[] confirmPinSettings3 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result12 = driver
                .getImagesSimilarity(confirmPinSettings3, confirmPinSettings1, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "settings_confirm_pin_after_enter_pin" + ".png";
        File comparison12 = new File(baselineFilename);
        result12.storeVisualization(comparison12);
        assertThat(result12.getVisualization().length, is(greaterThan(0)));
        assertThat(result12.getScore(), is(greaterThan(0.95)));
        System.out.println("settings_confirm_pin_after_enter_pin similarity of: " + result12.getScore());
        //inserir 4 digitos diferentes no confirm pin
        MobileElement number7ConfirmPin = driver.findElementByXPath("//android.widget.TextView[@text='7']");
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        driver.findElementByXPath("//android.widget.TextView[@text=\"Uh-oh! The PIN codes didn't match. Please try again.\"]");
        driver.findElementByXPath("//android.widget.TextView[@text=\"Retry\"]");
        byte[] pinDidntMatchErrorChangePin = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        SimilarityMatchingResult result13 = driver
                .getImagesSimilarity(pinDidntMatchErrorChangePin, pinDidntMatchErrorCreatePin, new SimilarityMatchingOptions()
                        .withEnabledVisualization());
        baselineFilename = VALIDATION_PATH + "/" + BASELINE + "settings_pin_didnt_match_errors" + ".png";
        File comparison13 = new File(baselineFilename);
        result13.storeVisualization(comparison13);
        assertThat(result13.getVisualization().length, is(greaterThan(0)));
        assertThat(result13.getScore(), is(greaterThan(0.95)));
        System.out.println("settings_pin_didnt_match_errors similarity of: " + result13.getScore());
        //clicar em retry
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        //inserir 4 digitos no create
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create PIN code']")));
        MobileElement number9CreatePin = driver.findElementByXPath("//android.widget.TextView[@text='9']");
        number9CreatePin.click();
        number9CreatePin.click();
        number9CreatePin.click();
        number9CreatePin.click();
        //inserir os mesmos 4 digitos no confirm pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
        MobileElement number9ConfirmPin = driver.findElementByXPath("//android.widget.TextView[@text='9']");
        number9ConfirmPin.click();
        number9ConfirmPin.click();
        number9ConfirmPin.click();
        number9ConfirmPin.click();
        //validar ecra de sucesso
        driver.findElementByXPath("//android.widget.TextView[@text=\"New PIN code set successfully\"]");
        visualCheck.doVisualCheck(CHECK_NEW_PIN_SUCCESS_SETTINGS_2);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //abrir settings
        utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Settings']")));
        visualCheck.doVisualCheck(CHECK_SETTINGS_WITH_PIN);
        //clicar em change pin
        utilitiesAndroid.clickByAccessibilityId("menu_option_change_pin", driver);
        //inserir o pin errado
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        MobileElement number1EnterPin = driver.findElementByXPath("//android.widget.TextView[@text='1']");
        number1EnterPin.click();
        number1EnterPin.click();
        number1EnterPin.click();
        number1EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        //trocar essas strings pra acelerar o teste
        String wrongPin1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        Assert.assertEquals("Wrong PIN code! \n" +
                "You have 4 more attempt(s)", wrongPin1);
        visualCheck.doVisualCheck(CHECK_WRONG_PIN_SETTINGS_4_ATTEMPTS);
        //retry
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number2EnterPin = driver.findElementByXPath("//android.widget.TextView[@text='2']");
        number2EnterPin.click();
        number2EnterPin.click();
        number2EnterPin.click();
        number2EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        String wrongPin2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        Assert.assertEquals("Wrong PIN code! \n" +
                "You have 3 more attempt(s)", wrongPin2);
        visualCheck.doVisualCheck(CHECK_WRONG_PIN_SETTINGS_3_ATTEMPTS);
        //retry
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number3EnterPin = driver.findElementByXPath("//android.widget.TextView[@text='3']");
        number3EnterPin.click();
        number3EnterPin.click();
        number3EnterPin.click();
        number3EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        String wrongPin3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        Assert.assertEquals("Wrong PIN code! \n" +
                "You have 2 more attempt(s)", wrongPin3);
        visualCheck.doVisualCheck(CHECK_WRONG_PIN_SETTINGS_2_ATTEMPTS);
        //retry
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number4EnterPin = driver.findElementByXPath("//android.widget.TextView[@text='4']");
        number4EnterPin.click();
        number4EnterPin.click();
        number4EnterPin.click();
        number4EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        String wrongPin4 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        Assert.assertEquals("Wrong PIN code! \n" +
                "You have 1 more attempt(s)", wrongPin4);
        visualCheck.doVisualCheck(CHECK_WRONG_PIN_SETTINGS_1_ATTEMPTS);
        //retry
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number5EnterPin = driver.findElementByXPath("//android.widget.TextView[@text='5']");
        number5EnterPin.click();
        number5EnterPin.click();
        number5EnterPin.click();
        number5EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        String pinError = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[1]").getText();
        String loginAgain = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[2]").getText();
        String backToLoginButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View/android.widget.TextView").getText();
        Assert.assertEquals("Uh-oh! That's 5 failed attempts", pinError);
        Assert.assertEquals("For security reasons, please log in again and set a new PIN code.", loginAgain);
        Assert.assertEquals("Back to login", backToLoginButton);
        visualCheck.doVisualCheck(CHECK_WRONG_PIN_SETTINGS_0_ATTEMPTS);
        //back to login
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        //fazer login
        utilitiesAndroid.clickByAccessibilityId("continueButton", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Log in']")));
        driver.findElementByXPath("//android.widget.EditText").sendKeys("Test1234!");
        utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
        //definir pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create PIN code']")));
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.Button", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create PIN code']")));
        MobileElement number6CreatePin = driver.findElementByXPath("//android.widget.TextView[@text='6']");
        number6CreatePin.click();
        number6CreatePin.click();
        number6CreatePin.click();
        number6CreatePin.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
        MobileElement number6ConfirmPin = driver.findElementByXPath("//android.widget.TextView[@text='6']");
        number6ConfirmPin.click();
        number6ConfirmPin.click();
        number6ConfirmPin.click();
        number6ConfirmPin.click();
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView", driver);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        visualCheck.doVisualCheck(CHECK_HOME_SETTINGS_WITH_PIN);
        //abrir settings
        utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
        visualCheck.doVisualCheck(CHECK_SETTINGS_WITH_PIN);
        //abrir change pin
        utilitiesAndroid.clickByAccessibilityId("menu_option_change_pin", driver);
        //clicar em forgot pin
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView", driver);
        //validar que voltou pra login screen
        driver.findElementByXPath("//android.widget.TextView[@text='Welcome to Sword']");
        visualCheck.doVisualCheck(CHECK_LOGIN_SCREEN);
        //fazer login
        utilitiesAndroid.clickByAccessibilityId("continueButton", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Log in']")));
        driver.findElementByXPath("//android.widget.EditText").sendKeys("Test1234!");
        utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
        utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='Not now']", driver);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //não definir pin e validar que a red dot aparece nas settings
        VisualCheck.doVisualCheck(CHECK_HOME_SETTINGS_WITHOUT_PIN);
        //clicar nas settings
        utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        visualCheck.doVisualCheck(CHECK_SETTINGS_WITHOUT_PIN);

        System.out.println("TEST PASSED WITH SUCCESS");

        ConfigurationsAndroid.killDriver();
    }
}
