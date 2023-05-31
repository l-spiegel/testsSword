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
import static org.hamcrest.Matchers.lessThan;

public class NewSwordRegressionHomeAlliOS {

    private IOSDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsiOS.getDriver();
    }

    private final static String VALIDATION_PATH = "/Users/luizaspiegel/Documents/image check/regression home/iOS";
    private final static String BASELINE = "COMP_";

    private final static String CHECK_HOME_SCREEN_TOP = "home_screen_top";
    private final static String CHECK_KIT_DELIVERY_CARD = "kit_delivery_card";
    private final static String CHECK_PROGRAM_STATUS_CARD = "program_status_card";
    private final static String CHECK_PROGRAM_GOAL_CARD = "program_goal_card";
    private final static String CHECK_MY_PROGRAM_1 = "my_program_screen_1";
    private final static String CHECK_MY_PROGRAM_2 = "my_program_screen_2";
    private final static String CHECK_WEEKLY_GOAL_CARD = "weekly_goal_card";
    private final static String CHECK_MY_WEEKLY_GOAL = "my_weekly_goal_screen";
    private final static String CHECK_SESSIONS_CARD = "sessions_card";
    private final static String CHECK_PROGRESS_SECTION = "progress_section";
    private final static String CHECK_PERSONAL_GOALS_SECTION_1 = "personal_goals_section_1";
    private final static String CHECK_PERSONAL_GOALS_SECTION_2 = "personal_goals_section_2";
    private final static String CHECK_PERSONAL_GOALS_POPUP_1 = "personal_goals_popup_1";
    private final static String CHECK_PERSONAL_GOALS_POPUP_2 = "personal_goals_popup_2";
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
        public void virtualPt() throws Exception {
        MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
        WebDriverWait wait = new WebDriverWait(driver,20);
        UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
        VisualCheck visualCheck = new VisualCheck(driver);

        //login
        utilitiesiOS.login("vinteum@sword.com", "Test1234!", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Luiza Almeida']")));
        //validar card virtual PT
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Your Physical Therapist']");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        VisualCheck.doVisualCheck(CHECK_HOME_SCREEN_TOP);
        //clicar no botão do chat do card
        utilitiesiOS.clickByAccessibilityId("home_card_pt_button", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Start typing...']")));
        //voltar para home
        utilitiesiOS.clickByAccessibilityId("bottom_navigation_home_tab", driver);
        //validar pending actions
        if (driver.findElements(By.xpath("//XCUIElementTypeOther[@name='home_card_pending_actions']")).size() > 0) {
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Pending actions']");
            String pendingActionsTitle = driver.findElementByAccessibilityId("home_card_pending_actions_title").getText();
            String pendingActionsSubtitle = driver.findElementByAccessibilityId("home_card_pending_actions_subtitle").getText();
            Assert.assertEquals("It's time for your reassessment check-in", pendingActionsTitle);
            Assert.assertEquals("Tap to complete your reassessment", pendingActionsSubtitle);
            //scroll pra mostrar o kit delivery inteiro
            MobileElement pendingActionsCard = driver.findElementByAccessibilityId("home_card_pending_actions");
            MobileElement ptCard = driver.findElementByAccessibilityId("home_card_pt");
            mobileActions.swipeByElements(pendingActionsCard, ptCard);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //validar o kit delivery
        if (driver.findElements(By.id("home_card_delivery_kit_status")).size() > 0) { //não encontra os elementos dentro do card
            VisualCheck.doVisualCheck(CHECK_KIT_DELIVERY_CARD);
            //scroll pra mostrar o program status inteiro
            MobileElement kitDeliveryCard = driver.findElementByAccessibilityId("home_card_delivery_kit_status");
            MobileElement programStatusTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Program status']");
            mobileActions.swipeByElements(programStatusTxt, kitDeliveryCard);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //validar program status
        if (driver.findElements(By.id("home_card_program_status")).size() > 0) {
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Program status']");
            String programStatusLabel = driver.findElementByAccessibilityId("home_card_program_status_creating_program_label").getText();
            Assert.assertEquals("Hi, Vinte Seis! I'm creating your program now. ", programStatusLabel);
//            VisualCheck.doVisualCheck(CHECK_PROGRAM_STATUS_CARD);
            MobileElement kitDeliveryCard = driver.findElementByAccessibilityId("home_card_delivery_kit_status");
            MobileElement programStatusCard = driver.findElementByAccessibilityId("home_card_program_status");
            mobileActions.swipeByElements(programStatusCard, kitDeliveryCard);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //validar program goal
        if (driver.findElements(By.id("home_card_program_goal")).size() > 0) {
            String programGoalLabel = driver.findElementByAccessibilityId("home_card_program_goal_label").getText();
            Assert.assertEquals("50% of Sword members feel significantly less pain by the end of their program", programGoalLabel);
//            VisualCheck.doVisualCheck(CHECK_PROGRAM_GOAL_CARD);
            //clicar nas informações do program goal
            utilitiesiOS.clickByAccessibilityId("home_card_program_goal_info_button", driver);
            //validar my program do program goal
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='My program']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='We set up the goals until your first reassessment after session 9.']");
            String myProgramProgramGoalLabel = driver.findElementByAccessibilityId("home_card_program_goal_label").getText();
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='For best results, try to complete at least 9 sessions (you can always do more!), plus a reassessment with me.']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Prepare your body']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Activate your muscles']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Build strength and endurance']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Reduce your symptoms']");
            Assert.assertEquals(programGoalLabel, myProgramProgramGoalLabel);
            VisualCheck.doVisualCheck(CHECK_MY_PROGRAM_1);
            //scroll
            MobileElement myProgramReduceTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Reduce your symptoms']");
            MobileElement myProgramProgramSubtitle = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='We set up the goals until your first reassessment after session 9.']");
            mobileActions.swipeByElements(myProgramReduceTxt, myProgramProgramSubtitle);
            //validar o restante do my program
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Handle more reps']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Meet program goals']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Reassessment to check your progress ETA: 3rd week of program']");
            VisualCheck.doVisualCheck(CHECK_MY_PROGRAM_2);
            //voltar
            utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
            //scroll pra mostrar o weekly goal
            MobileElement programGoalCard = driver.findElementByAccessibilityId("home_card_program_goal");
            MobileElement programStatusCard = driver.findElementByAccessibilityId("home_card_program_status");
            mobileActions.swipeByElements(programGoalCard, programStatusCard);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //validar weekly goal
        if (driver.findElements(By.id("home_card_weekly_goal")).size() > 0) {
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Weekly goal']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set reminders']");
            //clicar nas informações do weekly goal
//            VisualCheck.doVisualCheck(CHECK_WEEKLY_GOAL_CARD);
            utilitiesiOS.clickByAccessibilityId("home_card_weekly_goal_info_button", driver);
            //validar my program do weekly goal
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='My weekly goal']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='We set 4 goal sessions per week']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Complete 4 sessions this week']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Let's end the week strong! Try to complete 1 more session today.\"]");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Quick tip: You can set reminders for yourself to make sure you never miss a session. You can also customize the days and times you receive them.']");
            visualCheck.doVisualCheck(CHECK_MY_WEEKLY_GOAL);
            //voltar
            utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
            //scroll pra mostrar as sessions
            MobileElement sessionsTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']");
            MobileElement programGoalCard = driver.findElementByAccessibilityId("home_card_program_goal");
            mobileActions.swipeByElements(sessionsTxt, programGoalCard);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //validar sessions
        if (driver.findElements(By.id("home_card_session_details_0")).size() > 0) {
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']");
            String nextSession = driver.findElementByAccessibilityId("home_card_session_details_0_date_label").getText();
            Assert.assertEquals("Next Session", nextSession);
//            visualCheck.doVisualCheck(CHECK_SESSIONS_CARD);
            //scroll pra mostrar o progress
            MobileElement progressTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Progress']");
            MobileElement sessionsCardLabel = driver.findElementByAccessibilityId("home_card_session_details_0_date_label");
            mobileActions.swipeByElements(progressTxt, sessionsCardLabel);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //validar progress
        if (driver.findElements(By.id("home_card_achieved_stars")).size() > 0 && driver.findElements(By.id("home_screen_pain_chart_card")).size() > 0) {
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Progress']");
            String progressTotalStarsNumber = driver.findElementByAccessibilityId("home_card_achieved_stars_total_stars").getText();
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Pain level during session']"); //não tem o id
            Assert.assertEquals("78", progressTotalStarsNumber);
//            visualCheck.doVisualCheck(CHECK_PROGRESS_SECTION);
            //scroll pra mostrar o personal goals e badges
            MobileElement painChartCard = driver.findElementByAccessibilityId("home_screen_pain_chart_card");
            MobileElement sessionsCard = driver.findElementByAccessibilityId("home_card_session_details_0");
            mobileActions.swipeByElements(painChartCard, sessionsCard);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //validar personal goals
        if (driver.findElements(By.id("home_card_personal_goals_0")).size() > 0) {
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Personal goals']");
            String personalGoalsLabel1 = driver.findElementByAccessibilityId("home_card_personal_goals_label_0").getText();
            String personalGoalsButton1 = driver.findElementByXPath("(//XCUIElementTypeOther[@name='home_card_personal_goals_action_view'])[1]/XCUIElementTypeStaticText").getText();
            String personalGoalsLabel2 = driver.findElementByAccessibilityId("home_card_personal_goals_label_1").getText();
            String personalGoalsButton2 = driver.findElementByXPath("(//XCUIElementTypeOther[@name='home_card_personal_goals_action_view'])[2]/XCUIElementTypeStaticText").getText();
            Assert.assertEquals("Decrease my pain or symptoms", personalGoalsLabel1);
            Assert.assertEquals("Achieved", personalGoalsButton1);
            Assert.assertEquals("Decrease/avoid pain using medications", personalGoalsLabel2);
            Assert.assertEquals("Achieved", personalGoalsButton2);
            visualCheck.doVisualCheck(CHECK_PERSONAL_GOALS_SECTION_1);
            //clicar em um achieved
            utilitiesiOS.clickByAccessibilityId("home_card_personal_goals_0", driver);
            //validar popup
            String personalGoalsPopupReopenTitle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]").getText();
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Would you like to reopen this personal goal?']");
            Assert.assertEquals(personalGoalsLabel1, personalGoalsPopupReopenTitle);
            visualCheck.doVisualCheck(CHECK_PERSONAL_GOALS_POPUP_1);
            //clicar fora do popup
            mobileActions.tapByCoordinates(303, 150);
            mobileActions.tapByCoordinates(78, 660);
            utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='No']", driver);
            //fazer scroll no personal goals
            MobileElement personalGoalsCard2 = driver.findElementByAccessibilityId("home_card_personal_goals_1");
            MobileElement personalGoalsCard1 = driver.findElementByAccessibilityId("home_card_personal_goals_0");
            mobileActions.swipeByElements(personalGoalsCard2, personalGoalsCard1);
            MobileElement personalGoalsCard3 = driver.findElementByAccessibilityId("home_card_personal_goals_2");
            mobileActions.swipeByElements(personalGoalsCard3, personalGoalsCard2);
            //validar o último personal goals
            String personalGoalsLabel3 = driver.findElementByAccessibilityId("home_card_personal_goals_label_2").getText();
            String personalGoalsButton3 = driver.findElementByAccessibilityId("home_card_personal_goals_mark_as_achieved_label").getText();
            Assert.assertEquals("Return to my hobbies/daily activities", personalGoalsLabel3);
            Assert.assertEquals("Mark as achieved", personalGoalsButton3);
            visualCheck.doVisualCheck(CHECK_PERSONAL_GOALS_SECTION_2);
            //clicar no último personal goals
            utilitiesiOS.clickByAccessibilityId("home_card_personal_goals_mark_as_achieved_label", driver);
            //validar o popup
            String personalGoalsPopupAchieveLastTitle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]").getText();
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Did you achieve this goal? If you tap 'Yes', your goals cannot be reopened.\"]");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not yet']");
            Assert.assertEquals(personalGoalsLabel3, personalGoalsPopupAchieveLastTitle);
            visualCheck.doVisualCheck(CHECK_PERSONAL_GOALS_POPUP_2);
            utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Not yet']", driver);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //clicar nas settings
        utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        visualCheck.doVisualCheck(CHECK_SETTINGS_WITHOUT_PIN);
        //clicar pra deletar a conta
        utilitiesiOS.clickByAccessibilityId("settings_delete_account", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Delete account']")));
        //voltar
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Settings']")));
        //clicar pra definir pin
        utilitiesiOS.clickByAccessibilityId("menu_option_define_pin", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
        //voltar
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        //clicar pra definir pin
        utilitiesiOS.clickByAccessibilityId("menu_option_define_pin", driver);
        //inserir 3 digitos
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
        visualCheck.doVisualCheck(CHECK_SETTINGS_CREATE_PIN);
        byte[] createPinSettings1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        MobileElement number1CreatePin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='1']");
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
        assertThat(result1.getScore(), is(greaterThan(0.95)));
        System.out.println("settings_create_pin_filled similarity of: " + result1.getScore());
        //apagar 1
        utilitiesiOS.clickByAccessibilityId("deleteKey", driver);
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
        assertThat(result2.getScore(), is(greaterThan(0.95)));
        System.out.println("settings_create_pin_filled_delete_one similarity of: " + result2.getScore());
        //terminar de definir o pin
        number1CreatePin.click();
        number1CreatePin.click();
        //voltar pra create pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
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
        MobileElement number2CreatePin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='2']");
        number2CreatePin.click();
        number2CreatePin.click();
        number2CreatePin.click();
        number2CreatePin.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
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
        assertThat(result4.getScore(), is(lessThan(0.95)));
        System.out.println("settings_confirm_pin_vs_create_pin similarity of: " + result4.getScore());
        //inserir 4 digitos diferentes no confirm pin
        MobileElement number5ConfirmPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='5']");
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
        assertThat(result5.getScore(), is(greaterThan(0.95)));
        System.out.println("settings_confirm_pin_filled similarity of: " + result5.getScore());
        number5ConfirmPin.click();
        number5ConfirmPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Uh-oh! The PIN codes didn't match. Please try again.\"]");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Retry']");
        visualCheck.doVisualCheck(CHECK_PIN_DIDNT_MATCH_SETTINGS);
        byte[] pinDidntMatchErrorCreatePin = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        //clicar em retry
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Retry']", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
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
        MobileElement number0CreatePin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
        number0CreatePin.click();
        number0CreatePin.click();
        number0CreatePin.click();
        number0CreatePin.click();
        //inserir os mesmos 4 digitos no confirm pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
        MobileElement number0ConfirmPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
        number0ConfirmPin.click();
        number0ConfirmPin.click();
        number0ConfirmPin.click();
        number0ConfirmPin.click();
        //validar ecrã de biometria
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Want to use Face ID for future logins? You can activate it now, or activate it later in Settings.']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Activate now']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Activate later']");
        visualCheck.doVisualCheck(CHECK_ACTIVATE_BIOMETRICS_SETTINGS);
        //clicar pra ativar depois
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Activate later']", driver);
        //validar ecrã de sucesso
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='New PIN code set successfully']");
        visualCheck.doVisualCheck(CHECK_NEW_PIN_SUCCESS_SETTINGS_1);
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
        //clicar pra ativar biometria
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login with Face ID']")));
        visualCheck.doVisualCheck(CHECK_SETTINGS_WITH_PIN);
        byte[] biometricsToggleOff1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        utilitiesiOS.clickByAccessibilityId("menu_option_login_biometrics", driver);
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
        utilitiesiOS.clickByAccessibilityId("menu_option_login_biometrics", driver);
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
        utilitiesiOS.clickByAccessibilityId("menu_option_change_pin", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Forgot your PIN?']");
        visualCheck.doVisualCheck(CHECK_ENTER_PIN_SETTINGS);
        //voltar
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        //clicar em change pin
        utilitiesiOS.clickByAccessibilityId("menu_option_change_pin", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        byte[] enterPinSettings1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
        //digitar 3 digitos corretos
        MobileElement number0EnterPin1 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
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
        assertThat(result9.getScore(), is(greaterThan(0.95)));
        System.out.println("enter_pin_filled similarity of: " + result9.getScore());
        //apagar 1 digito
        utilitiesiOS.clickByAccessibilityId("deleteKey", driver);
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
        assertThat(result10.getScore(), is(greaterThan(0.95)));
        System.out.println("enter_pin_erase similarity of: " + result10.getScore());
        //terminar de inserir o pin correto
        number0EnterPin1.click();
        number0EnterPin1.click();
        //voltar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
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
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        //clicar em change pin
        utilitiesiOS.clickByAccessibilityId("menu_option_change_pin", driver);
        //inserir 4 digitos no enter pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        MobileElement number0EnterPin2 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
        number0EnterPin2.click();
        number0EnterPin2.click();
        number0EnterPin2.click();
        number0EnterPin2.click();
        //inserir 4 digitos no create pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
        MobileElement number3CreatePin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='3']");
        number3CreatePin.click();
        number3CreatePin.click();
        number3CreatePin.click();
        number3CreatePin.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
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
        MobileElement number7ConfirmPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='7']");
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Uh-oh! The PIN codes didn't match. Please try again.\"]");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Retry']");
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
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Retry']", driver);
        //inserir 4 digitos no create
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
        MobileElement number9CreatePin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='9']");
        number9CreatePin.click();
        number9CreatePin.click();
        number9CreatePin.click();
        number9CreatePin.click();
        //inserir os mesmos 4 digitos no confirm pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
        MobileElement number9ConfirmPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='9']");
        number9ConfirmPin.click();
        number9ConfirmPin.click();
        number9ConfirmPin.click();
        number9ConfirmPin.click();
        //validar ecra de sucesso
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='New PIN code set successfully']");
        visualCheck.doVisualCheck(CHECK_NEW_PIN_SUCCESS_SETTINGS_2);
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
        //clicar em change pin
        utilitiesiOS.clickByAccessibilityId("menu_option_change_pin", driver);
        //inserir o pin errado
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        MobileElement number1EnterPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='1']");
        number1EnterPin.click();
        number1EnterPin.click();
        number1EnterPin.click();
        number1EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Wrong PIN code!  You have 4 more attempt(s)']");
        visualCheck.doVisualCheck(CHECK_WRONG_PIN_SETTINGS_4_ATTEMPTS);
        //retry
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Retry']", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number2EnterPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='2']");
        number2EnterPin.click();
        number2EnterPin.click();
        number2EnterPin.click();
        number2EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Wrong PIN code!  You have 3 more attempt(s)']");
        visualCheck.doVisualCheck(CHECK_WRONG_PIN_SETTINGS_3_ATTEMPTS);
        //retry
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Retry']", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number3EnterPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='3']");
        number3EnterPin.click();
        number3EnterPin.click();
        number3EnterPin.click();
        number3EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Wrong PIN code!  You have 2 more attempt(s)']");
        visualCheck.doVisualCheck(CHECK_WRONG_PIN_SETTINGS_2_ATTEMPTS);
        //retry
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Retry']", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number4EnterPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='4']");
        number4EnterPin.click();
        number4EnterPin.click();
        number4EnterPin.click();
        number4EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Wrong PIN code!  You have 1 more attempt(s)']");
        visualCheck.doVisualCheck(CHECK_WRONG_PIN_SETTINGS_1_ATTEMPTS);
        //retry
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Retry']", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number5EnterPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='5']");
        number5EnterPin.click();
        number5EnterPin.click();
        number5EnterPin.click();
        number5EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Uh-oh! That's 5 failed attempts\"]");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='For security reasons, please log in again and set a new PIN code.']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Back to login']");
        visualCheck.doVisualCheck(CHECK_WRONG_PIN_SETTINGS_0_ATTEMPTS);
        //back to login
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Back to login']", driver);
        //fazer login
        driver.findElementByAccessibilityId("loginPasswordTextfield").sendKeys("Test1234!");
        utilitiesiOS.clickByAccessibilityId("loginButton", driver);
        //definir pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Create PIN']", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
        MobileElement number6CreatePin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='6']");
        number6CreatePin.click();
        number6CreatePin.click();
        number6CreatePin.click();
        number6CreatePin.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
        MobileElement number6ConfirmPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='6']");
        number6ConfirmPin.click();
        number6ConfirmPin.click();
        number6ConfirmPin.click();
        number6ConfirmPin.click();
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Activate later']", driver);
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
        //abrir change pin
        utilitiesiOS.clickByAccessibilityId("menu_option_change_pin", driver);
        //clicar em forgot pin
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Forgot your PIN?']", driver);
        //validar que voltou pra login screen
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Welcome to Sword']");
        visualCheck.doVisualCheck(CHECK_LOGIN_SCREEN);

        System.out.println("O TESTE PASSOU");

        ConfigurationsiOS.killDriver();
    }
}

