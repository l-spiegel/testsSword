package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;

public class NewSwordRegressionHomeAlliOS {

    private IOSDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsiOS.getDriver();
    }

    @Test
        public void virtualPt() {
        MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
        WebDriverWait wait = new WebDriverWait(driver,20);
        UtilitiesiOS utilitiesiOS = new UtilitiesiOS();

        //login
        utilitiesiOS.clickByAccessibilityId("Allow", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
        MobileElement email = driver.findElementByAccessibilityId("loginEmailTextfield");
        email.clear();
        email.sendKeys("l.spiegel+3@swordhealth.com");
        MobileElement pass = driver.findElementByAccessibilityId("loginPasswordTextfield");
        pass.click();
        pass.sendKeys("Test1234!");
        utilitiesiOS.clickByAccessibilityId("loginButton", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not now']").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Luiza Almeida']")));
        //validar card virtual PT
        String virtualPtCard = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_card_pt']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]").getText();
        Assert.assertEquals("Your Physical Therapist", virtualPtCard);
        //clicar no botão do chat do card
        utilitiesiOS.clickByAccessibilityId("home_card_pt_button", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Start typing...']")));
        //voltar para home
        utilitiesiOS.clickByAccessibilityId("bottom_navigation_home_tab", driver);
        //validar pending actions
        if (driver.findElements(By.xpath("//XCUIElementTypeOther[@name='home_card_pending_actions']")).size() > 0) {
            String pendingActions = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText").getText();
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
        if (driver.findElements(By.id("home_card_delivery_kit_status")).size() > 0) { //não encontra os elementos dentro do card
//            String kitStatusTitle = driver.findElementByXPath("//android.view.View[@content-desc='home_card_delivery_kit_status']/android.widget.TextView[1]").getText();
//          String kitStatusPrepared = driver.findElementByXPath("//android.view.View[@content-desc='home_card_delivery_kit_status']/android.widget.TextView[2]").getText();
//            String kitStatusPreparedDate = driver.findElementByXPath("//android.view.View[@content-desc='home_card_delivery_kit_status']/android.widget.TextView[3]").getText();
//            String kitStatusShipped = driver.findElementByXPath("//android.view.View[@content-desc='home_card_delivery_kit_status']/android.widget.TextView[4]").getText();
//            String kitStatusShippedDate = driver.findElementByXPath("//android.view.View[@content-desc='home_card_delivery_kit_status']/android.widget.TextView[5]").getText();
//            String kitStatusDelivered = driver.findElementByXPath("//android.view.View[@content-desc='home_card_delivery_kit_status']/android.widget.TextView[6]").getText();
//            String kitStatusAddress = driver.findElementByXPath("//android.view.View[@content-desc='home_card_delivery_kit_status']/android.widget.TextView[7]").getText();
//            Assert.assertEquals("Sword kit status", kitStatusTitle);
//            Assert.assertEquals("Kit being prepared", kitStatusPrepared);
//            Assert.assertEquals("December 26", kitStatusPreparedDate);
//            Assert.assertEquals("Kit shipped", kitStatusShipped);
//            Assert.assertEquals("January 31", kitStatusShippedDate);
//            Assert.assertEquals("Kit delivered", kitStatusDelivered);
//            Assert.assertEquals("Delivery address", kitStatusAddress);
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
            String programStatusTitle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText").getText();
            //String programStatusCreating = driver.findElementByXPath("//android.view.View[@content-desc='home_card_program_status']/android.widget.TextView[2]").getText(); //não encontra
            String programStatusLabel = driver.findElementByAccessibilityId("home_card_program_status_creating_program_label").getText();
            //String programStatusTxt2 = driver.findElementByXPath("//android.view.View[@content-desc='home_card_program_status']/android.view.View/android.widget.TextView[2]").getText(); //não encontra
            Assert.assertEquals("Program status", programStatusTitle);
            //Assert.assertEquals("Creating your program", programStatusCreating); //não encontra
            Assert.assertEquals("Hi, Vinte Seis! I'm creating your program now. ", programStatusLabel);
            //Assert.assertEquals("Once your program is ready, you can complete your 1st session!", programStatusTxt2); //não encontra
            MobileElement kitDeliveryCard = driver.findElementByAccessibilityId("home_card_delivery_kit_status");
            MobileElement programStatusCard = driver.findElementByAccessibilityId("home_card_program_status");
            mobileActions.swipeByElements(programStatusCard, kitDeliveryCard);
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //validar program goal
        if (driver.findElements(By.id("home_card_program_goal")).size() > 0) {
//            String programGoalTitle = driver.findElementByXPath("//android.view.View[@content-desc='home_card_program_goal']/android.widget.TextView[1]").getText(); //não acha o texto
            String programGoalLabel = driver.findElementByAccessibilityId("home_card_program_goal_label").getText();
//            Assert.assertEquals("Program goal: 9+ sessions ", programGoalTitle);
            Assert.assertEquals("50% of Sword members feel significantly less pain by the end of their program", programGoalLabel);
            //clicar nas informações do program goal
            utilitiesiOS.clickByAccessibilityId("home_card_program_goal_info_button", driver);
            //validar my program do program goal
            String myProgramProgramGoalHeader = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
            String myProgramProgramGoalSubtitle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
//            String myProgramProgramGoal = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText(); //também não encontra
            String myProgramProgramGoalLabel = driver.findElementByAccessibilityId("home_card_program_goal_label").getText();
            String myProgramReassessment = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell[3]/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
            String myProgramPrepare = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell[4]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText").getText();
            String myProgramActivate = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell[4]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText").getText();
            String myProgramBuild = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell[5]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText").getText();
            String myProgramReduce = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell[5]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText").getText();
            Assert.assertEquals("My program", myProgramProgramGoalHeader);
            Assert.assertEquals("We set up the goals until your first reassessment after session 9.", myProgramProgramGoalSubtitle);
//            Assert.assertEquals(programGoalTitle, myProgramProgramGoal);
            Assert.assertEquals(programGoalLabel, myProgramProgramGoalLabel);
            Assert.assertEquals("For best results, try to complete at least 9 sessions (you can always do more!), plus a reassessment with me.", myProgramReassessment);
            Assert.assertEquals("Prepare your body", myProgramPrepare);
            Assert.assertEquals("Activate your muscles", myProgramActivate);
            Assert.assertEquals("Build strength and endurance", myProgramBuild);
            Assert.assertEquals("Reduce your symptoms", myProgramReduce);
            //scroll
            MobileElement myProgramReduceTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Reduce your symptoms']");
            MobileElement myProgramProgramSubtitle = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='We set up the goals until your first reassessment after session 9.']");
            mobileActions.swipeByElements(myProgramReduceTxt, myProgramProgramSubtitle);
            //validar o restante do my program
            String myProgramHandle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell[4]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText").getText();
            String myProgramMeet = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell[4]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText").getText();
            String myProgramFull = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell[5]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
            Assert.assertEquals("Handle more reps", myProgramHandle);
            Assert.assertEquals("Meet program goals", myProgramMeet);
            Assert.assertEquals("Reassessment to check your progress ETA: 3rd week of program", myProgramFull);
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
            String weeklyGoalTitle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText").getText();
//            String weeklyGoalSubtitle = driver.findElementByXPath("//android.view.View[@content-desc='home_card_weekly_goal']/android.widget.TextView[1]").getText(); //não encontra
//            String weeklyGoalLabel = driver.findElementByAccessibilityId("home_card_weekly_goal_label").getText(); //não existe essa id em iOS
            String weeklyGoalSetReminders = driver.findElementByXPath("//XCUIElementTypeButton[@name='home_card_weekly_goal_reminders_buttons']/XCUIElementTypeStaticText[2]").getText();
            Assert.assertEquals("Weekly goal", weeklyGoalTitle);
//            Assert.assertEquals("Complete 4 sessions this week", weeklyGoalSubtitle);
//            Assert.assertEquals("Once your kit arrives, you can start your first exercise session!", weeklyGoalLabel);
            Assert.assertEquals("Set reminders", weeklyGoalSetReminders);
            //clicar nas informações do weekly goal
            utilitiesiOS.clickByAccessibilityId("home_card_weekly_goal_info_button", driver);
            //validar my program do weekly goal
            String myProgramWeeklyGoalHeader = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
            String myProgramWeeklyGoalSubtitle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
            String myProgramWeeklyGoal = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
            String myProgramWeeklyGoalLabel = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeStaticText").getText();
            String myProgramWeeklyGoalTip = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell[3]/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
            Assert.assertEquals("My weekly goal", myProgramWeeklyGoalHeader);
            Assert.assertEquals("We set 4 goal sessions per week", myProgramWeeklyGoalSubtitle);
            Assert.assertEquals("Complete 4 sessions this week", myProgramWeeklyGoal);
            Assert.assertEquals("Let's end the week strong! Try to complete 1 more session today.", myProgramWeeklyGoalLabel); //a frase muda de acordo com o comportamento do weekly goal e não consigo buscar o do card da home pra comparar entre os dois
            Assert.assertEquals("Quick tip: You can set reminders for yourself to make sure you never miss a session. You can also customize the days and times you receive them.", myProgramWeeklyGoalTip);
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
            String sessionsTitle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText").getText();
            String nextSession = driver.findElementByAccessibilityId("home_card_session_details_0_date_label").getText();
            Assert.assertEquals("Sessions", sessionsTitle);
            Assert.assertEquals("Next Session", nextSession);
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
            String progressTitle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText").getText();
            //String progressTotalStarsTxt = driver.findElementByXPath("//android.view.View[@content-desc='home_card_achieved_stars']/android.widget.TextView[1]").getText(); //não encontra
            String progressTotalStarsNumber = driver.findElementByAccessibilityId("home_card_achieved_stars_total_stars").getText();
            String progressPainChartLabel = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_screen_pain_chart_card']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]").getText(); //não tem o id
            Assert.assertEquals("Progress", progressTitle);
            //Assert.assertEquals("Total stars earned", progressTotalStarsTxt);
            Assert.assertEquals("78", progressTotalStarsNumber);
            Assert.assertEquals("Pain level during session", progressPainChartLabel);
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
            String personalGoalsTitle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText").getText();
            String personalGoalsLabel1 = driver.findElementByAccessibilityId("home_card_personal_goals_label_0").getText();
            String personalGoalsButton1 = driver.findElementByXPath("(//XCUIElementTypeOther[@name='home_card_personal_goals_action_view'])[1]/XCUIElementTypeStaticText").getText();
            String personalGoalsLabel2 = driver.findElementByAccessibilityId("home_card_personal_goals_label_1").getText();
            String personalGoalsButton2 = driver.findElementByXPath("(//XCUIElementTypeOther[@name='home_card_personal_goals_action_view'])[2]/XCUIElementTypeStaticText").getText();
            Assert.assertEquals("Personal goals", personalGoalsTitle);
            Assert.assertEquals("Decrease my pain or symptoms", personalGoalsLabel1);
            Assert.assertEquals("Achieved", personalGoalsButton1);
            Assert.assertEquals("Decrease/avoid pain using medications", personalGoalsLabel2);
            Assert.assertEquals("Achieved", personalGoalsButton2);
            //clicar em um achieved
            utilitiesiOS.clickByAccessibilityId("home_card_personal_goals_0", driver);
            //validar popup
            String personalGoalsPopupReopenTitle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]").getText();
            String personalGoalsPopupReopenTxt = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]").getText();
            Assert.assertEquals(personalGoalsLabel1, personalGoalsPopupReopenTitle);
            Assert.assertEquals("Would you like to reopen this personal goal?", personalGoalsPopupReopenTxt);
            //clicar fora do popup
            mobileActions.tapByCoordinates(303, 150);
            mobileActions.tapByCoordinates(78, 660);
            driver.findElementByXPath("//XCUIElementTypeButton[@name='No']").click();
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
            //clicar no último personal goals
            utilitiesiOS.clickByAccessibilityId("home_card_personal_goals_mark_as_achieved_label", driver);
            //validar o popup
            String personalGoalsPopupAchieveLastTitle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]").getText();
            String personalGoalsPopupAchieveLastTxt = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]").getText();
            String notYetButton = driver.findElementByXPath("//XCUIElementTypeButton[@name='Not yet']/XCUIElementTypeStaticText").getText();
            Assert.assertEquals(personalGoalsLabel3, personalGoalsPopupAchieveLastTitle);
            Assert.assertEquals("Did you achieve this goal? If you tap 'Yes', your goals cannot be reopened.", personalGoalsPopupAchieveLastTxt);
            Assert.assertEquals("Not yet", notYetButton);
            driver.findElementByXPath("//XCUIElementTypeButton[@name='Not yet']").click();
        }
        else {
            System.out.println("LIGA O PROXY CERTO");
        }
        //clicar nas settings
        utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
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
        MobileElement number1CreatePin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='1']");
        number1CreatePin.click();
        number1CreatePin.click();
        number1CreatePin.click();
        //apagar 1
        utilitiesiOS.clickByAccessibilityId("deleteKey", driver);
        //terminar de definir o pin
        number1CreatePin.click();
        number1CreatePin.click();
        //voltar pra create pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        //inserir 4 digitos
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
        MobileElement number2CreatePin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='2']");
        number2CreatePin.click();
        number2CreatePin.click();
        number2CreatePin.click();
        number2CreatePin.click();
        //inserir 4 digitos diferentes no confirm pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
        MobileElement number5ConfirmPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='5']");
        number5ConfirmPin.click();
        number5ConfirmPin.click();
        number5ConfirmPin.click();
        number5ConfirmPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        String pinDoesntMatch1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
        String retryButton = driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']/XCUIElementTypeStaticText").getText();
        Assert.assertEquals("Uh-oh! The PIN codes didn't match. Please try again.", pinDoesntMatch1);
        Assert.assertEquals("Retry", retryButton);
        //clicar em retry
        driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']").click();
        //inserir 4 digitos no create your pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
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
        String biometrics = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
        String activateNowButton = driver.findElementByXPath("//XCUIElementTypeButton[@name='Activate now']/XCUIElementTypeStaticText").getText();
        String activateLaterButton = driver.findElementByXPath("//XCUIElementTypeButton[@name='Activate later']/XCUIElementTypeStaticText").getText();
        Assert.assertEquals("Want to use Face ID for future logins? You can activate it now, or activate it later in Settings.", biometrics);
        Assert.assertEquals("Activate now", activateNowButton);
        Assert.assertEquals("Activate later", activateLaterButton);
        //clicar pra ativar depois
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Activate later']").click();
        //validar ecrã de sucesso
        String newPinSuccess1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
        Assert.assertEquals("New PIN code set successfully", newPinSuccess1);
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
        //clicar pra ativar biometria
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login with Face ID']")));
        driver.findElementByAccessibilityId("menu_option_login_biometrics").click();
        //clicar de novo na biometria
        driver.findElementByAccessibilityId("menu_option_login_biometrics").click();
        //clicar em change pin
        driver.findElementByAccessibilityId("menu_option_change_pin").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        String forgotPinButton = driver.findElementByXPath("//XCUIElementTypeButton[@name='Forgot your PIN?']/XCUIElementTypeStaticText").getText();
        Assert.assertEquals("Forgot your PIN?", forgotPinButton);
        //voltar
        driver.findElementByAccessibilityId("ic arrow left").click();
        //clicar em change pin
        driver.findElementByAccessibilityId("menu_option_change_pin").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        //digitar 3 digitos corretos
        MobileElement number0EnterPin1 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
        number0EnterPin1.click();
        number0EnterPin1.click();
        number0EnterPin1.click();
        //apagar 1 digito
        driver.findElementByAccessibilityId("deleteKey").click();
        //terminar de inserir o pin correto
        number0EnterPin1.click();
        number0EnterPin1.click();
        //voltar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
        driver.findElementByAccessibilityId("ic arrow left").click();
        //clicar em change pin
        driver.findElementByAccessibilityId("menu_option_change_pin").click();
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
        //inserir 4 digitos diferentes no confirm pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
        MobileElement number7ConfirmPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='7']");
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        String pinDoesntMatch2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
        String retryButton2 = driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']/XCUIElementTypeStaticText").getText();
        Assert.assertEquals("Uh-oh! The PIN codes didn't match. Please try again.", pinDoesntMatch2);
        Assert.assertEquals("Retry", retryButton2);
        //clicar em retry
        driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']").click();
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
        String newPinSuccess2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
        Assert.assertEquals("New PIN code set successfully", newPinSuccess2);
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
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        MobileElement number1EnterPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='1']");
        number1EnterPin.click();
        number1EnterPin.click();
        number1EnterPin.click();
        number1EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        String wrongPin1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
        Assert.assertEquals("Wrong PIN code!  You have 4 more attempt(s)", wrongPin1);
        //retry
        driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number2EnterPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='2']");
        number2EnterPin.click();
        number2EnterPin.click();
        number2EnterPin.click();
        number2EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        String wrongPin2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
        Assert.assertEquals("Wrong PIN code!  You have 3 more attempt(s)", wrongPin2);
        //retry
        driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number3EnterPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='3']");
        number3EnterPin.click();
        number3EnterPin.click();
        number3EnterPin.click();
        number3EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        String wrongPin3 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
        Assert.assertEquals("Wrong PIN code!  You have 2 more attempt(s)", wrongPin3);
        //retry
        driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number4EnterPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='4']");
        number4EnterPin.click();
        number4EnterPin.click();
        number4EnterPin.click();
        number4EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        String wrongPin4 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
        Assert.assertEquals("Wrong PIN code!  You have 1 more attempt(s)", wrongPin4);
        //retry
        driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number5EnterPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='5']");
        number5EnterPin.click();
        number5EnterPin.click();
        number5EnterPin.click();
        number5EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        String pinError = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]").getText();
        String loginAgain = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]").getText();
        String backToLoginButton = driver.findElementByXPath("//XCUIElementTypeButton[@name='Back to login']/XCUIElementTypeStaticText").getText();
        Assert.assertEquals("Uh-oh! That's 5 failed attempts", pinError);
        Assert.assertEquals("For security reasons, please log in again and set a new PIN code.", loginAgain);
        Assert.assertEquals("Back to login", backToLoginButton);
        //back to login
        driver.findElementByXPath("//XCUIElementTypeButton[@name='Back to login']").click();
        //fazer login
        driver.findElementByAccessibilityId("loginPasswordTextfield").sendKeys("Test1234!");
        driver.findElementByAccessibilityId("loginButton").click();
        //definir pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
        driver.findElementByXPath("//XCUIElementTypeButton[@name='Create PIN']").click();
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
        driver.findElementByXPath("//XCUIElementTypeButton[@name='Activate later']").click();
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
        driver.findElementByXPath("//XCUIElementTypeButton[@name='Forgot your PIN?']").click();
        //validar que voltou pra login screen
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Welcome to Sword']");

        System.out.println("O TESTE PASSOU");

        ConfigurationsiOS.killDriver();
    }
}

