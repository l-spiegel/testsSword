package appium.Android;

import appium.iOS.VisualCheckiOS;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;

public class NewSwordRegressionHomeAll {

    private AndroidDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsAndroid.getDriver();
    }

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

    @Test
        public void virtualPt() throws Exception {
        MobileActions mobileActions = new MobileActions(driver);
        WebDriverWait wait = new WebDriverWait(driver,20);
        VisualCheck visualCheck = new VisualCheck(driver);
        UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();

        //login
        utilitiesAndroid.login("vinteum@sword.com", "Test1234!", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Almeida']")));
        //validar card virtual PT
        String virtualPtCard = driver.findElementByXPath("//android.view.View[@content-desc='home_card_pt']/android.widget.TextView[1]").getText();
        Assert.assertEquals("Your Physical Therapist", virtualPtCard);
        VisualCheck.doVisualCheck(CHECK_HOME_SCREEN_TOP);
        //clicar no botão do chat do card
        driver.findElementByAccessibilityId("home_card_pt_button").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.swordhealth.guarda.dev:id/etInputText"))); //tá pra staging
        //voltar para home
        driver.findElementByAccessibilityId("bottom_navigation_home_tab").click();
        //validar pending actions
        if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_card_pending_actions']")).size() > 0) {
            String pendingActions = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView").getText();
            String pendingActionsTitle = driver.findElementByAccessibilityId("home_card_pending_actions_title").getText();
            String pendingActionsSubtitle = driver.findElementByAccessibilityId("home_card_pending_actions_subtitle").getText();
            Assert.assertEquals("Pending actions", pendingActions);
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
        if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_card_delivery_kit_status']")).size() > 0) {
            String kitStatusTitle = driver.findElementByXPath("//android.view.View[@content-desc='home_card_delivery_kit_status']/android.widget.TextView[1]").getText();
            String kitStatusPrepared = driver.findElementByXPath("//android.view.View[@content-desc='home_card_delivery_kit_status']/android.widget.TextView[2]").getText();
            String kitStatusPreparedDate = driver.findElementByXPath("//android.view.View[@content-desc='home_card_delivery_kit_status']/android.widget.TextView[3]").getText();
            String kitStatusShipped = driver.findElementByXPath("//android.view.View[@content-desc='home_card_delivery_kit_status']/android.widget.TextView[4]").getText();
            String kitStatusShippedDate = driver.findElementByXPath("//android.view.View[@content-desc='home_card_delivery_kit_status']/android.widget.TextView[5]").getText();
            String kitStatusDelivered = driver.findElementByXPath("//android.view.View[@content-desc='home_card_delivery_kit_status']/android.widget.TextView[6]").getText();
            String kitStatusAddress = driver.findElementByXPath("//android.view.View[@content-desc='home_card_delivery_kit_status']/android.widget.TextView[7]").getText();
            Assert.assertEquals("Sword kit status", kitStatusTitle);
            Assert.assertEquals("Kit being prepared", kitStatusPrepared);
            Assert.assertEquals("December 26", kitStatusPreparedDate);
            Assert.assertEquals("Kit shipped", kitStatusShipped);
            Assert.assertEquals("January 31", kitStatusShippedDate);
            Assert.assertEquals("Kit delivered", kitStatusDelivered);
            Assert.assertEquals("Delivery address", kitStatusAddress);
        //    visualCheck.doVisualCheck(CHECK_KIT_DELIVERY_CARD);
            //scroll pra mostrar o program status inteiro
            MobileElement programStatusCard = driver.findElementByAccessibilityId("home_card_program_status");
            MobileElement kitDeliveryCard = driver.findElementByAccessibilityId("home_card_delivery_kit_status");
            mobileActions.swipeByElements(programStatusCard, kitDeliveryCard);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //validar program status
        if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_card_program_status']")).size() > 0) {
            String programStatusTitle = driver.findElementByXPath("//android.view.View[@content-desc='home_card_program_status']/android.widget.TextView[1]").getText();
            String programStatusCreating = driver.findElementByXPath("//android.view.View[@content-desc='home_card_program_status']/android.widget.TextView[2]").getText();
            String programStatusLabel = driver.findElementByAccessibilityId("home_card_program_status_creating_program_label").getText();
            String programStatusTxt2 = driver.findElementByXPath("//android.view.View[@content-desc='home_card_program_status']/android.view.View/android.widget.TextView[2]").getText();
            Assert.assertEquals("Program status", programStatusTitle);
            Assert.assertEquals("Creating your program", programStatusCreating);
            Assert.assertEquals("Hi, Vinte Seis! I'm creating your program now. ", programStatusLabel);
            Assert.assertEquals("Once your program is ready, you can complete your 1st session!", programStatusTxt2);
        }
        //validar program goal
        if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_card_program_goal']")).size() > 0) {
            String programGoalTitle = driver.findElementByXPath("//android.view.View[@content-desc='home_card_program_goal']/android.widget.TextView[1]").getText();
            String programGoalLabel = driver.findElementByAccessibilityId("home_card_program_goal_label").getText();
            Assert.assertEquals("Program goal: 9+ sessions ", programGoalTitle);
            Assert.assertEquals("50% of Sword members feel significantly less pain by the end of their program", programGoalLabel);
//            VisualCheck.doVisualCheck(CHECK_PROGRAM_GOAL_CARD);
            //clicar nas informações do program goal
            driver.findElementByAccessibilityId("home_card_program_goal_info_button").click();
            //validar my program do program goal
            String myProgramProgramGoalHeader = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView").getText();
            String myProgramProgramGoalSubtitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[1]").getText();
            String myProgramProgramGoal = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
            String myProgramProgramGoalLabel = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[3]").getText();
            String myProgramReassessment = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[4]").getText();
            String myProgramPrepare = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[7]").getText();
            String myProgramActivate = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[8]").getText();
            String myProgramBuild = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[11]").getText();
            String myProgramReduce = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[12]").getText();
            Assert.assertEquals("My program", myProgramProgramGoalHeader);
            Assert.assertEquals("We set up the goals until your first reassessment after session 9.", myProgramProgramGoalSubtitle);
            Assert.assertEquals(programGoalTitle, myProgramProgramGoal);
            Assert.assertEquals(programGoalLabel, myProgramProgramGoalLabel);
            Assert.assertEquals("For best results, try to complete at least 9 sessions (you can always do more!), plus a reassessment with me.", myProgramReassessment);
            Assert.assertEquals("Prepare your body", myProgramPrepare);
            Assert.assertEquals("Activate your muscles", myProgramActivate);
            Assert.assertEquals("Build strength and endurance", myProgramBuild);
            Assert.assertEquals("Reduce your symptoms", myProgramReduce);
            VisualCheck.doVisualCheck(CHECK_MY_PROGRAM_1);
            //scroll
            MobileElement myProgramReduceTxt = driver.findElementByXPath("//android.widget.TextView[@text='Reduce your symptoms']");
            MobileElement myProgramProgramGoalTxt = driver.findElementByXPath("//android.widget.TextView[@text='Program goal: 9+ sessions ']");
            mobileActions.swipeByElements(myProgramReduceTxt, myProgramProgramGoalTxt);
            //validar o restante do my program
            String myProgramHandle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[13]").getText();
            String myProgramMeet = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[14]").getText();
            String myProgramFull = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[15]").getText();
            Assert.assertEquals("Handle more reps", myProgramHandle);
            Assert.assertEquals("Meet program goals", myProgramMeet);
            Assert.assertEquals("Reassessment to check your progress\n" +
                    "ETA: 3rd week of program", myProgramFull);
            VisualCheck.doVisualCheck(CHECK_MY_PROGRAM_2);
            //voltar
            driver.findElementByXPath("//android.widget.Button").click();
            //scroll pra mostrar o weekly goal
            MobileElement programGoalCard = driver.findElementByAccessibilityId("home_card_program_goal");
            MobileElement programStatusCard = driver.findElementByXPath("//android.widget.TextView[@text='Program status']");
            mobileActions.swipeByElements(programGoalCard, programStatusCard);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //validar weekly goal
        if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']")).size() > 0) {
            String weeklyGoalTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[1]").getText();
            String weeklyGoalSubtitle = driver.findElementByXPath("//android.view.View[@content-desc='home_card_weekly_goal']/android.widget.TextView[1]").getText();
            String weeklyGoalLabel = driver.findElementByAccessibilityId("home_card_weekly_goal_label").getText();
            String weeklyGoalSetReminders = driver.findElementByXPath("//android.view.View[@content-desc='home_card_weekly_goal']/android.view.View[2]/android.widget.TextView").getText();
            Assert.assertEquals("Weekly goal", weeklyGoalTitle);
            Assert.assertEquals("Complete 4 sessions this week", weeklyGoalSubtitle);
            Assert.assertEquals("Once your kit arrives, you can start your first exercise session!", weeklyGoalLabel);
            Assert.assertEquals("Set reminders", weeklyGoalSetReminders);
//            VisualCheck.doVisualCheck(CHECK_WEEKLY_GOAL_CARD);
            //clicar nas informações do weekly goal
            driver.findElementByAccessibilityId("home_card_weekly_goal_info_button").click();
            //validar my program do weekly goal
            String myProgramWeeklyGoalHeader = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView").getText();
            String myProgramWeeklyGoalSubtitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[1]").getText();
            String myProgramWeeklyGoal = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
            String myProgramWeeklyGoalLabel = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[5]").getText();
            String myProgramWeeklyGoalTip = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[6]").getText();
            Assert.assertEquals("My weekly goal", myProgramWeeklyGoalHeader);
            Assert.assertEquals("We set 4 goal sessions per week", myProgramWeeklyGoalSubtitle);
            Assert.assertEquals(weeklyGoalSubtitle, myProgramWeeklyGoal);
            Assert.assertEquals(weeklyGoalLabel, myProgramWeeklyGoalLabel);
            Assert.assertEquals("Quick tip: You can set reminders for yourself to make sure you never miss a session. You can also customize the days and times you receive them.", myProgramWeeklyGoalTip);
            visualCheck.doVisualCheck(CHECK_MY_WEEKLY_GOAL);
            //voltar
            driver.findElementByXPath("//android.widget.Button").click();
            //scroll pra mostrar as sessions
            MobileElement weeklyGoalCard = driver.findElementByAccessibilityId("home_card_weekly_goal");
            MobileElement programGoalCard = driver.findElementByAccessibilityId("home_card_program_goal");
            mobileActions.swipeByElements(weeklyGoalCard, programGoalCard);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //validar sessions
        if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_card_session_details_0']")).size() > 0) {
            String sessionsTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[1]").getText();
            Assert.assertEquals("Sessions", sessionsTitle);
//            visualCheck.doVisualCheck(CHECK_SESSIONS_CARD);
            //scroll pra mostrar o progress
            MobileElement sessionsCard = driver.findElementByAccessibilityId("home_card_session_details_0");
            MobileElement weeklyGoalCard = driver.findElementByAccessibilityId("home_card_weekly_goal");
            mobileActions.swipeByElements(sessionsCard, weeklyGoalCard);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //validar progress
        if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_card_achieved_stars']")).size() > 0 && driver.findElements(By.xpath("//android.view.View[@content-desc='home_screen_pain_chart']")).size() > 0) {
            String progressTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView").getText();
            String progressTotalStarsTxt = driver.findElementByXPath("//android.view.View[@content-desc='home_card_achieved_stars']/android.widget.TextView[1]").getText();
            String progressTotalStarsNumber = driver.findElementByAccessibilityId("home_card_achieved_stars_total_stars").getText();
            String progressPainChartLabel = driver.findElementByAccessibilityId("home_screen_pain_chart_label").getText();
            Assert.assertEquals("Progress", progressTitle);
            Assert.assertEquals("Total stars", progressTotalStarsTxt);
            Assert.assertEquals("78", progressTotalStarsNumber);
            Assert.assertEquals("Pain level during session", progressPainChartLabel);
//            visualCheck.doVisualCheck(CHECK_PROGRESS_SECTION);
            //scroll pra mostrar o personal goals e badges
            MobileElement painChartCard = driver.findElementByAccessibilityId("home_screen_pain_chart");
            MobileElement sessionsCard = driver.findElementByAccessibilityId("home_card_session_details_0");
            mobileActions.swipeByElements(painChartCard, sessionsCard);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //validar personal goals
        if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_card_personal_goals_0']")).size() > 0) {
            String personalGoalsTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[1]").getText();
            String personalGoalsLabel1 = driver.findElementByAccessibilityId("home_card_personal_goals_label_0").getText();
            String personalGoalsButton1 = driver.findElementByXPath("(//android.view.View[@content-desc='home_card_personal_goals_unmark_as_achieved_label'])[1]/android.widget.TextView").getText();
            String personalGoalsLabel2 = driver.findElementByAccessibilityId("home_card_personal_goals_label_1").getText();
            String personalGoalsButton2 = driver.findElementByXPath("(//android.view.View[@content-desc='home_card_personal_goals_unmark_as_achieved_label'])[2]/android.widget.TextView").getText();
            Assert.assertEquals("Personal goals", personalGoalsTitle);
            Assert.assertEquals("Decrease my pain or symptoms", personalGoalsLabel1);
            Assert.assertEquals("Achieved", personalGoalsButton1);
            Assert.assertEquals("Decrease/avoid pain using medications", personalGoalsLabel2);
            Assert.assertEquals("Achieved", personalGoalsButton2);
            visualCheck.doVisualCheck(CHECK_PERSONAL_GOALS_SECTION_1);
            //clicar em um achieved
            driver.findElementByAccessibilityId("home_card_personal_goals_0").click();
            //validar popup
            String personalGoalsPopupReopenTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[1]").getText();
            String personalGoalsPopupReopenTxt = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]").getText();
            Assert.assertEquals(personalGoalsLabel1, personalGoalsPopupReopenTitle);
            Assert.assertEquals("Would you like to reopen this personal goal?", personalGoalsPopupReopenTxt);
            visualCheck.doVisualCheck(CHECK_PERSONAL_GOALS_POPUP_1);
            //clicar fora do popup
            mobileActions.tapByCoordinates(735, 1867);
            mobileActions.tapByCoordinates(842, 338);
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView").click();
            //fazer scroll no personal goals
            MobileElement personalGoalsCard2 = driver.findElementByAccessibilityId("home_card_personal_goals_1");
            MobileElement personalGoalsCard1 = driver.findElementByAccessibilityId("home_card_personal_goals_0");
            mobileActions.swipeByElements(personalGoalsCard2, personalGoalsCard1);
            MobileElement personalGoalsCard3 = driver.findElementByAccessibilityId("home_card_personal_goals_2");
            mobileActions.swipeByElements(personalGoalsCard3, personalGoalsCard2);
            //validar o último personal goals
            String personalGoalsLabel3 = driver.findElementByAccessibilityId("home_card_personal_goals_label_2").getText();
            String personalGoalsButton3 = driver.findElementByXPath("//android.view.View[@content-desc='home_card_personal_goals_mark_as_achieved_label']/android.widget.TextView").getText();
            Assert.assertEquals("Return to my hobbies/daily activities", personalGoalsLabel3);
            Assert.assertEquals("Mark as achieved", personalGoalsButton3);
            visualCheck.doVisualCheck(CHECK_PERSONAL_GOALS_SECTION_2);
            //clicar no último personal goals
            driver.findElementByAccessibilityId("home_card_personal_goals_mark_as_achieved_label").click();
            //validar o popup
            String personalGoalsPopupAchieveLastTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[1]").getText();
            String personalGoalsPopupAchieveLastTxt = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]").getText();
            String notYetButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView").getText();
            Assert.assertEquals(personalGoalsLabel3, personalGoalsPopupAchieveLastTitle);
            Assert.assertEquals("Did you achieve this goal? If you tap 'Yes', your goals cannot be reopened.", personalGoalsPopupAchieveLastTxt);
            Assert.assertEquals("Not yet", notYetButton);
            visualCheck.doVisualCheck(CHECK_PERSONAL_GOALS_POPUP_2);
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView").click();
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //clicar nas settings
        driver.findElementByAccessibilityId("header_menu_button").click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        visualCheck.doVisualCheck(CHECK_SETTINGS_WITHOUT_PIN);
        //clicar pra deletar a conta
        driver.findElementByAccessibilityId("settings_delete_account").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Delete account']")));
        //voltar
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.Button").click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //abrir settings de novo
        driver.findElementByAccessibilityId("header_menu_button").click();
        //clicar pra definir pin
        driver.findElementByAccessibilityId("menu_option_define_pin").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
        //voltar
        driver.findElementByXPath("//android.widget.Button").click();
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        driver.findElementByAccessibilityId("header_menu_button").click();
        //clicar pra definir pin
        driver.findElementByAccessibilityId("menu_option_define_pin").click();
        //inserir 3 digitos
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
        visualCheck.doVisualCheck(CHECK_SETTINGS_CREATE_PIN);
        MobileElement number1CreatePin = driver.findElementByXPath("//android.widget.TextView[@text='1']");
        number1CreatePin.click();
        number1CreatePin.click();
        number1CreatePin.click();
        //apagar 1
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[12]").click();
        //terminar de definir o pin
        number1CreatePin.click();
        number1CreatePin.click();
        //voltar pra create pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
        driver.findElementByXPath("//android.widget.Button").click();
        //inserir 4 digitos
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
        visualCheck.doVisualCheck(CHECK_SETTINGS_CONFIRM_PIN);
        MobileElement number2CreatePin = driver.findElementByXPath("//android.widget.TextView[@text='2']");
        number2CreatePin.click();
        number2CreatePin.click();
        number2CreatePin.click();
        number2CreatePin.click();
        //inserir 4 digitos diferentes no confirm pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
        MobileElement number5ConfirmPin = driver.findElementByXPath("//android.widget.TextView[@text='5']");
        number5ConfirmPin.click();
        number5ConfirmPin.click();
        number5ConfirmPin.click();
        number5ConfirmPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        String pinDoesntMatch1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        String retryButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View/android.widget.TextView").getText();
        Assert.assertEquals("Uh-oh! The PIN codes didn't match. Please try again.", pinDoesntMatch1);
        Assert.assertEquals("Retry", retryButton);
        visualCheck.doVisualCheck(CHECK_PIN_DIDNT_MATCH_SETTINGS);
        //clicar em retry
        driver.findElementByXPath("//android.widget.Button").click();
        //inserir 4 digitos no create your pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
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
        String biometrics = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        String activateNowButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.TextView").getText();
        String activateLaterButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView").getText();
        Assert.assertEquals("Want to use biometrics for future logins? You can activate it now, or activate it later in Settings.", biometrics);
        Assert.assertEquals("Activate now", activateNowButton);
        Assert.assertEquals("Activate later", activateLaterButton);
        visualCheck.doVisualCheck(CHECK_ACTIVATE_BIOMETRICS_SETTINGS);
        //clicar pra ativar depois
        driver.findElementByXPath("//android.widget.TextView[@text='Activate later']").click();
        //validar ecrã de sucesso
        String newPinSuccess1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        Assert.assertEquals("New PIN code set successfully", newPinSuccess1);
        visualCheck.doVisualCheck(CHECK_NEW_PIN_SUCCESS_SETTINGS_1);
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        driver.findElementByAccessibilityId("header_menu_button").click();
        //clicar pra ativar biometria
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Login with biometrics']")));
        visualCheck.doVisualCheck(CHECK_SETTINGS_WITH_PIN);
        MobileElement biometricsToggle = driver.findElementByAccessibilityId("menu_option_login_biometrics");
        biometricsToggle.click();
        visualCheck.doVisualCheck(CHECK_BIOMETRICS_TOGGLE);
        //clicar de novo na biometria
        biometricsToggle.click();
        //clicar em change pin
        driver.findElementByAccessibilityId("menu_option_change_pin").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        String forgotPinButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView").getText();
        Assert.assertEquals("Forgot your PIN?", forgotPinButton);
        visualCheck.doVisualCheck(CHECK_ENTER_PIN_SETTINGS);
        //voltar
        driver.findElementByXPath("//android.widget.Button").click();
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        driver.findElementByAccessibilityId("header_menu_button").click();
        //clicar em change pin
        driver.findElementByAccessibilityId("menu_option_change_pin").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        //digitar 3 digitos corretos
        MobileElement number0EnterPin1 = driver.findElementByXPath("//android.widget.TextView[@text='0']");
        number0EnterPin1.click();
        number0EnterPin1.click();
        number0EnterPin1.click();
        //apagar 1 digito
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[12]").click();
        //terminar de inserir o pin correto
        number0EnterPin1.click();
        number0EnterPin1.click();
        //voltar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
        driver.findElementByXPath("//android.widget.Button").click();
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        driver.findElementByAccessibilityId("header_menu_button").click();
        //clicar em change pin
        driver.findElementByAccessibilityId("menu_option_change_pin").click();
        //inserir 4 digitos no enter pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        MobileElement number0EnterPin2 = driver.findElementByXPath("//android.widget.TextView[@text='0']");
        number0EnterPin2.click();
        number0EnterPin2.click();
        number0EnterPin2.click();
        number0EnterPin2.click();
        //inserir 4 digitos no create pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
        MobileElement number3CreatePin = driver.findElementByXPath("//android.widget.TextView[@text='3']");
        number3CreatePin.click();
        number3CreatePin.click();
        number3CreatePin.click();
        number3CreatePin.click();
        //inserir 4 digitos diferentes no confirm pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
        MobileElement number7ConfirmPin = driver.findElementByXPath("//android.widget.TextView[@text='7']");
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        String pinDoesntMatch2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        String retryButton2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View/android.widget.TextView").getText();
        Assert.assertEquals("Uh-oh! The PIN codes didn't match. Please try again.", pinDoesntMatch2);
        Assert.assertEquals("Retry", retryButton2);
        //clicar em retry
        driver.findElementByXPath("//android.widget.Button").click();
        //inserir 4 digitos no create
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
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
        String newPinSuccess2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        Assert.assertEquals("New PIN code set successfully", newPinSuccess2);
        visualCheck.doVisualCheck(CHECK_NEW_PIN_SUCCESS_SETTINGS_2);
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        driver.findElementByAccessibilityId("header_menu_button").click();
        //clicar em change pin
        driver.findElementByAccessibilityId("menu_option_change_pin").click();
        //inserir o pin errado
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        MobileElement number1EnterPin = driver.findElementByXPath("//android.widget.TextView[@text='1']");
        number1EnterPin.click();
        number1EnterPin.click();
        number1EnterPin.click();
        number1EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        String wrongPin1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        Assert.assertEquals("Wrong PIN code! \n" +
                "You have 4 more attempt(s)", wrongPin1);
        visualCheck.doVisualCheck(CHECK_WRONG_PIN_SETTINGS_4_ATTEMPTS);
        //retry
        driver.findElementByXPath("//android.widget.Button").click();
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
        driver.findElementByXPath("//android.widget.Button").click();
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
        driver.findElementByXPath("//android.widget.Button").click();
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
        driver.findElementByXPath("//android.widget.Button").click();
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
        driver.findElementByXPath("//android.widget.Button").click();
        //fazer login
        driver.findElementByXPath("//android.widget.EditText[2]").sendKeys("Test1234!");
        driver.findElementByAccessibilityId("loginButton").click();
        //definir pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.Button").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
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
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView").click();
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        driver.findElementByAccessibilityId("header_menu_button").click();
        //abrir change pin
        driver.findElementByAccessibilityId("menu_option_change_pin").click();
        //clicar em forgot pin
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView").click();
        //validar que voltou pra login screen
        String welcomeSword = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[1]").getText();
        Assert.assertEquals("Welcome to Sword", welcomeSword);

        System.out.println("O TESTE PASSOU");

        ConfigurationsAndroid.killDriver();
    }
}

