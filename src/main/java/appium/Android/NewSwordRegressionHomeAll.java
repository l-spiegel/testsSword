package appium.Android;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class NewSwordRegressionHomeAll {

    private AndroidDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsAndroid.getDriver();
    }

    @Test
        public void virtualPt() throws MalformedURLException {
        MobileActions mobileActions = new MobileActions(driver);
        WebDriverWait wait = new WebDriverWait(driver,20);

        //login
        MobileElement email = (MobileElement) driver.findElementByXPath("//android.widget.EditText[1]");
        email.clear();
        email.sendKeys("vinteum@sword.com");
        MobileElement pass = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
        pass.click();
        pass.sendKeys("Test1234!");
        driver.findElementByAccessibilityId("loginButton").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
        driver.findElementByXPath("//android.widget.TextView[@text='Not now']").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Almeida']")));
        //validar card virtual PT
        String virtualPtCard = driver.findElementByXPath("//android.view.View[@content-desc='home_card_pt']/android.widget.TextView[1]").getText();
        Assert.assertEquals("Your Physical Therapist", virtualPtCard);
        //clicar no botão do chat do card
        driver.findElementByAccessibilityId("home_card_pt_button").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.swordhealth.guarda.dev:id/etInputText"))); //tá pra staging
        //voltar pra home
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
            MobileElement pendingActionsCard = (MobileElement) driver.findElementByAccessibilityId("home_card_pending_actions");
            MobileElement ptCard = (MobileElement) driver.findElementByAccessibilityId("home_card_pt");
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
            //scroll pra mostrar o program status inteiro
            MobileElement programStatusCard = (MobileElement) driver.findElementByAccessibilityId("home_card_program_status");
            MobileElement kitDeliveryCard = (MobileElement) driver.findElementByAccessibilityId("home_card_delivery_kit_status");
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
            Assert.assertEquals("For best results, try to complete 9 sessions, plus a reassessment check-in with me.", myProgramReassessment);
            Assert.assertEquals("Prepare your body", myProgramPrepare);
            Assert.assertEquals("Activate your muscles", myProgramActivate);
            Assert.assertEquals("Build strength and endurance", myProgramBuild);
            Assert.assertEquals("Reduce your symptoms", myProgramReduce);
            //scroll
            MobileElement myProgramReduceTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Reduce your symptoms']");
            MobileElement myProgramProgramGoalTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Program goal: 9+ sessions ']");
            mobileActions.swipeByElements(myProgramReduceTxt, myProgramProgramGoalTxt);
            //validar o restante do my program
            String myProgramHandle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[13]").getText();
            String myProgramMeet = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[14]").getText();
            String myProgramFull = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[15]").getText();
            Assert.assertEquals("Handle more reps", myProgramHandle);
            Assert.assertEquals("Meet program goals", myProgramMeet);
            Assert.assertEquals("Full reassessment to check your improvements\n" +
                    "ETA: 3rd week of program", myProgramFull);
            //voltar
            driver.findElementByXPath("//android.widget.Button").click();
            //scroll pra mostrar o weekly goal
            MobileElement programGoalCard = (MobileElement) driver.findElementByAccessibilityId("home_card_program_goal");
            MobileElement programStatusCard = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Program status']");
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
            //clicar nas informações do weekly goal
            driver.findElementByAccessibilityId("home_card_weekly_goal_info_button").click();
            //validar my program do weekly goal
            String myProgramWeeklyGoalHeader = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView").getText();
            String myProgramWeeklyGoalSubtitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[1]").getText();
            String myProgramWeeklyGoal = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
            String myProgramWeeklyGoalLabel = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[5]").getText();
            String myProgramWeeklyGoalTip = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[6]").getText();
            Assert.assertEquals("My program", myProgramWeeklyGoalHeader);
            Assert.assertEquals("We set 4 goal sessions per week", myProgramWeeklyGoalSubtitle);
            Assert.assertEquals(weeklyGoalSubtitle, myProgramWeeklyGoal);
            Assert.assertEquals(weeklyGoalLabel, myProgramWeeklyGoalLabel);
            Assert.assertEquals("Quick tip: You can set reminders for yourself to make sure you never miss a session. You can also customize the days and times you receive them.", myProgramWeeklyGoalTip);
            //voltar
            driver.findElementByXPath("//android.widget.Button").click();
            //scroll pra mostrar as sessions
            MobileElement weeklyGoalCard = (MobileElement) driver.findElementByAccessibilityId("home_card_weekly_goal");
            MobileElement programGoalCard = (MobileElement) driver.findElementByAccessibilityId("home_card_program_goal");
            mobileActions.swipeByElements(weeklyGoalCard, programGoalCard);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //validar sessions
        if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_card_session_details_0']")).size() > 0) {
            String sessionsTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[1]").getText();
            Assert.assertEquals("Sessions", sessionsTitle);
            //scroll pra mostrar o progress
            MobileElement sessionsCard = (MobileElement) driver.findElementByAccessibilityId("home_card_session_details_0");
            MobileElement weeklyGoalCard = (MobileElement) driver.findElementByAccessibilityId("home_card_weekly_goal");
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
            Assert.assertEquals("Total stars earned", progressTotalStarsTxt);
            Assert.assertEquals("78", progressTotalStarsNumber);
            Assert.assertEquals("Pain level during session", progressPainChartLabel);
            //scroll pra mostrar o personal goals e badges
            MobileElement painChartCard = (MobileElement) driver.findElementByAccessibilityId("home_screen_pain_chart");
            MobileElement sessionsCard = (MobileElement) driver.findElementByAccessibilityId("home_card_session_details_0");
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
            //clicar em um achieved
            driver.findElementByAccessibilityId("home_card_personal_goals_0").click();
            //validar popup
            String personalGoalsPopupReopenTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[1]").getText();
            String personalGoalsPopupReopenTxt = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]").getText();
            Assert.assertEquals(personalGoalsLabel1, personalGoalsPopupReopenTitle);
            Assert.assertEquals("Would you like to reopen this personal goal?", personalGoalsPopupReopenTxt);
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView").click();
            //fazer scroll no personal goals
            MobileElement personalGoalsCard2 = (MobileElement) driver.findElementByAccessibilityId("home_card_personal_goals_1");
            MobileElement personalGoalsCard1 = (MobileElement) driver.findElementByAccessibilityId("home_card_personal_goals_0");
            mobileActions.swipeByElements(personalGoalsCard2, personalGoalsCard1);
            MobileElement personalGoalsCard3 = (MobileElement) driver.findElementByAccessibilityId("home_card_personal_goals_2");
            mobileActions.swipeByElements(personalGoalsCard3, personalGoalsCard2);
            //validar o último personal goals
            String personalGoalsLabel3 = driver.findElementByAccessibilityId("home_card_personal_goals_label_2").getText();
            String personalGoalsButton3 = driver.findElementByXPath("//android.view.View[@content-desc='home_card_personal_goals_mark_as_achieved_label']/android.widget.TextView").getText();
            Assert.assertEquals("Return to my hobbies/daily activities", personalGoalsLabel3);
            Assert.assertEquals("Mark as achieved", personalGoalsButton3);
            //clicar no último personal goals
            driver.findElementByAccessibilityId("home_card_personal_goals_mark_as_achieved_label").click();
            //validar o popup
            String personalGoalsPopupAchieveLastTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[1]").getText();
            String personalGoalsPopupAchieveLastTxt = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]").getText();
            String notYetButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView").getText();
            Assert.assertEquals(personalGoalsLabel3, personalGoalsPopupAchieveLastTitle);
            Assert.assertEquals("Did you achieve this goal? If you tap 'Yes', your goals cannot be reopened.", personalGoalsPopupAchieveLastTxt);
            Assert.assertEquals("Not yet", notYetButton);
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView").click();
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //clicar nas settings
        driver.findElementByAccessibilityId("header_menu_button").click();
        //clicar pra deletar a conta
        driver.findElementByAccessibilityId("settings_delete_account").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Delete account']")));
        //voltar
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button").click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //abrir settings de novo
        driver.findElementByAccessibilityId("header_menu_button").click();
        //clicar pra definir pin
        //voltar
        //abrir settings
        //clicar pra definir pin
        //inserir 3 digitos
        //apagar 1
        //terminar de definir o pin
        //voltar pra create pin
        //inserir 4 digitos
        //inserir 4 digitos diferentes no confirm pin
        //validar ecrã de erro
        //clicar em retry
        //inserir 4 digitos no create your pin
        //inserir os mesmos 4 digitos no confirm pin
        //validar ecrã de biometria
        //clicar pra ativar depois
        //validar ecrã de sucesso
        //abrir settings
        //clicar pra ativar biometria
        //clicar de novo na biometria
        //clicar em change pin
        //voltar
        //abrir settings
        //clicar em change pin
        //digitar 3 digitos corretos
        //apagar 1 digito
        //terminar de inserir o pin correto
        //voltar
        //abrir settings
        //clicar em change pin
        //inserir 4 digitos
        //inserir 4 digitos diferentes no confirm pin
        //validar ecrã de erro
        //clicar em retry
        //inserir 4 digitos no create
        //inserir os mesmos 4 digitos no confirm pin
        //validar ecra de sucesso
        //abrir settings
        //clicar em change pin
        //inserir o pin errado
        //validar ecrã de erro
        //retry
        //inserir o pin errado
        //validar ecrã de erro
        //retry
        //inserir o pin errado
        //validar ecrã de erro
        //retry
        //inserir o pin errado
        //validar ecrã de erro
        //retry
        //inserir o pin errado
        //validar ecrã de erro
        //back to login
        //fazer login
        //definir pin
        //abrir settings
        //abrir change pin
        //clicar em forgot pin
        //validar que voltou pra login screen

        //    ConfigurationsAndroid.killDriver();
    }
}

