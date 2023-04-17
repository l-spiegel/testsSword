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
        utilitiesiOS.login("luiza@marco.com", "10março!", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Luiza Almeida']")));
        //validar card virtual PT
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Your Physical Therapist']");
        //clicar no botão do chat do card
        utilitiesiOS.clickByAccessibilityId("home_card_pt_button", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Start typing...']")));
        //voltar para definePinLoginChangePinHome
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
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Program status']");
            //String programStatusCreating = driver.findElementByXPath("//android.view.View[@content-desc='home_card_program_status']/android.widget.TextView[2]").getText(); //não encontra
            String programStatusLabel = driver.findElementByAccessibilityId("home_card_program_status_creating_program_label").getText();
            //String programStatusTxt2 = driver.findElementByXPath("//android.view.View[@content-desc='home_card_program_status']/android.view.View/android.widget.TextView[2]").getText(); //não encontra
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
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='My program']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='We set up the goals until your first reassessment after session 9.']");
//            String myProgramProgramGoal = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText(); //também não encontra
            String myProgramProgramGoalLabel = driver.findElementByAccessibilityId("home_card_program_goal_label").getText();
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='For best results, try to complete at least 9 sessions (you can always do more!), plus a reassessment with me.']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Prepare your body']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Activate your muscles']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Build strength and endurance']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Reduce your symptoms']");
//            Assert.assertEquals(programGoalTitle, myProgramProgramGoal);
            Assert.assertEquals(programGoalLabel, myProgramProgramGoalLabel);
            //scroll
            MobileElement myProgramReduceTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Reduce your symptoms']");
            MobileElement myProgramProgramSubtitle = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='We set up the goals until your first reassessment after session 9.']");
            mobileActions.swipeByElements(myProgramReduceTxt, myProgramProgramSubtitle);
            //validar o restante do my program
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Handle more reps']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Meet program goals']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Reassessment to check your progress ETA: 3rd week of program']");
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
//            String weeklyGoalSubtitle = driver.findElementByXPath("//android.view.View[@content-desc='home_card_weekly_goal']/android.widget.TextView[1]").getText(); //não encontra
//            String weeklyGoalLabel = driver.findElementByAccessibilityId("home_card_weekly_goal_label").getText(); //não existe essa id em iOS
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set reminders']");
//            Assert.assertEquals("Complete 4 sessions this week", weeklyGoalSubtitle);
//            Assert.assertEquals("Once your kit arrives, you can start your first exercise session!", weeklyGoalLabel);
            //clicar nas informações do weekly goal
            utilitiesiOS.clickByAccessibilityId("home_card_weekly_goal_info_button", driver);
            //validar my program do weekly goal
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='My weekly goal']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='We set 4 goal sessions per week']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Complete 4 sessions this week']");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Let's end the week strong! Try to complete 1 more session today.\"]");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Quick tip: You can set reminders for yourself to make sure you never miss a session. You can also customize the days and times you receive them.']");
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
            //String progressTotalStarsTxt = driver.findElementByXPath("//android.view.View[@content-desc='home_card_achieved_stars']/android.widget.TextView[1]").getText(); //não encontra
            String progressTotalStarsNumber = driver.findElementByAccessibilityId("home_card_achieved_stars_total_stars").getText();
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Pain level during session']"); //não tem o id
            //Assert.assertEquals("Total stars earned", progressTotalStarsTxt);
            Assert.assertEquals("78", progressTotalStarsNumber);
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
            //clicar em um achieved
            utilitiesiOS.clickByAccessibilityId("home_card_personal_goals_0", driver);
            //validar popup
            String personalGoalsPopupReopenTitle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]").getText();
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Would you like to reopen this personal goal?']");
            Assert.assertEquals(personalGoalsLabel1, personalGoalsPopupReopenTitle);
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
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Did you achieve this goal? If you tap 'Yes', your goals cannot be reopened.\"]");
            driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not yet']");
            Assert.assertEquals(personalGoalsLabel3, personalGoalsPopupAchieveLastTitle);
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
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Uh-oh! The PIN codes didn't match. Please try again.\"]");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Retry']");
        //clicar em retry
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Retry']", driver);
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
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Want to use Face ID for future logins? You can activate it now, or activate it later in Settings.']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Activate now']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Activate later']");
        //clicar pra ativar depois
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Activate later']", driver);
        //validar ecrã de sucesso
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='New PIN code set successfully']");
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
        //clicar pra ativar biometria
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login with Face ID']")));
        utilitiesiOS.clickByAccessibilityId("menu_option_login_biometrics", driver);
        //clicar de novo na biometria
        utilitiesiOS.clickByAccessibilityId("menu_option_login_biometrics", driver);
        //clicar em change pin
        utilitiesiOS.clickByAccessibilityId("menu_option_change_pin", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Forgot your PIN?']");
        //voltar
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        //clicar em change pin
        utilitiesiOS.clickByAccessibilityId("menu_option_change_pin", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
        //digitar 3 digitos corretos
        MobileElement number0EnterPin1 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
        number0EnterPin1.click();
        number0EnterPin1.click();
        number0EnterPin1.click();
        //apagar 1 digito
        utilitiesiOS.clickByAccessibilityId("deleteKey", driver);
        //terminar de inserir o pin correto
        number0EnterPin1.click();
        number0EnterPin1.click();
        //voltar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
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
        //inserir 4 digitos diferentes no confirm pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
        MobileElement number7ConfirmPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='7']");
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Uh-oh! The PIN codes didn't match. Please try again.\"]");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Retry']");
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
        //back to login
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Back to login']", driver);
        //fazer login
        driver.findElementByAccessibilityId("loginPasswordTextfield").sendKeys("10março!");
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

        System.out.println("O TESTE PASSOU");

        ConfigurationsiOS.killDriver();
    }
}

