package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;

public class SwordRegressionWizardiOS {

    private final static String CHECK_YOUR_PROGRAM_SCREEN_1 = "your_program_1";
    private final static String CHECK_YOUR_PROGRAM_SCREEN_2 = "your_program_2";
    private final static String CHECK_YOUR_PERSONAL_GOALS_SCREEN = "your_personal_goals";
    private final static String CHECK_YOUR_WEEKLY_GOAL_SCREEN_1 = "your_weekly_goal_1";
    private final static String CHECK_YOUR_WEEKLY_GOAL_SCREEN_2 = "your_weekly_goal_2";
    private final static String CHECK_REMINDERS_POPUP = "reminders_popup";

    private IOSDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsiOS.getDriver();
    }

    @Test
    public void wizard() throws Exception {
        MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
        WebDriverWait wait = new WebDriverWait(driver,50);
        UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
        VisualCheck visualCheck = new VisualCheck(driver);

        String [] personalGoalsArray = {
                "Decrease my pain or symptoms",
                "Decrease/avoid pain using medications",
                "Return to my hobbies/daily activities",
                "Return to my exercise routine",
                "Return to playing sports",
                "Return to my regular work duties",
                "Return to my usual family routine",
                "Improve my physical fitness",
                "Improve my mental well-being",
                "Improve my quality of sleep",
                "Prevent future pain, injury and/or falls",
                "Avoid surgery"
        };

        //fazer login
        utilitiesiOS.newLogin("l.spiegel+3@swordhealth.com", "Test1234!", driver);
        //validar your program
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name='Your Physical Therapist']")).size() > 0) {
            System.out.println("LIGA O PROXY PRO WIZARD");
            ConfigurationsiOS.killDriver();
        }
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='How it works']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Your program']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='During your program, your PT will monitor your progress, offer support, and more.']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Program goal']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='9 or more sessions']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='For best results, try to complete at least 9 sessions (you can always do more!), plus a reassessment with me.']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Prepare your body']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Activate your muscles']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Build strength and endurance']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Reduce your symptoms']");
        //teste visual
        visualCheck.doVisualCheck(CHECK_YOUR_PROGRAM_SCREEN_1);
        //fazer scroll
        MobileElement reduceSymptomsTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Reduce your symptoms']");
        MobileElement yourProgramTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Your program']");
        mobileActions.swipeByElements(reduceSymptomsTxt, yourProgramTxt);
        //validar o restante do your program
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Handle more reps']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Meet program goals']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Reassessment to check your progress ETA: 3rd week of program\"]");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Next']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Skip']");
        //teste visual
        visualCheck.doVisualCheck(CHECK_YOUR_PROGRAM_SCREEN_2);
        //next
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Next']", driver);
        //validar your personal goals - só é visível se tiver program goals - muda de user pra user
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Your personal goals']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"These are the goals you'll work to achieve during your program.\"]");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"I’ve customized your sessions based on the goals you selected.\"]");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Next']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Skip']");

        int maxPersonalGoals = 3;
        int personalGoalsFound = 0;

        for (String personalGoals : personalGoalsArray) {
            if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name='" + personalGoals + "']")).size() > 0) {
                System.out.println("Personal goal found: " + personalGoals);
                personalGoalsFound++;
            }
            if (personalGoalsFound == maxPersonalGoals) {
                break;
            }
        }

        //teste visual
        visualCheck.doVisualCheck(CHECK_YOUR_PERSONAL_GOALS_SCREEN);
        //next
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Next']", driver);
        //validar your weekly goal
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Your weekly goal']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Hitting your personal health goals starts with your weekly goal.']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Weekly goal']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Complete 4 sessions per week']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Quick tip: You can set reminders for yourself to make sure you never miss a session. You can also customize the days and times you receive them. Set your first reminder below!\"]");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set a reminder']");
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Now, you’re ready to do your first session! If you have questions, you can message me anytime.\n" +
                "\n" +
                "Let’s get moving!\"]");
        //teste visual
        visualCheck.doVisualCheck(CHECK_YOUR_WEEKLY_GOAL_SCREEN_1);
        //scroll
        MobileElement getMovingTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Now, you’re ready to do your first session! If you have questions, you can message me anytime.\n" +
                "\n" +
                "Let’s get moving!\"]");
        MobileElement yourWeeklyGoalTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Your weekly goal']");
        mobileActions.swipeByElements(getMovingTxt, yourWeeklyGoalTxt);
        //validar o resto do your weekly goal
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Go to my program']");
        //teste visual
        visualCheck.doVisualCheck(CHECK_YOUR_WEEKLY_GOAL_SCREEN_2);
        //clicar em set a reminder
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name=\"Set a reminder\"]", driver);
        //criar um reminder com dias = weekly goal
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Set a new reminder']")));
        utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_0", driver);
        utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_1", driver);
        utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_2", driver);
        utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_3", driver);
        //salvar
        utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
        //see my program
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Go to my program\"]", driver);
        //validar popup dos reminders
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Congrats! You earned a new badge!']")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Reminders Scheduled']");
        //teste visual
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        visualCheck.doVisualCheck(CHECK_REMINDERS_POPUP);
        //clicar em ok
        utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Ok\"]", driver);
        //pull to refresh pra mostrar o wizard de novo
        MobileElement ptCard = driver.findElementByAccessibilityId("home_card_pt");
        MobileElement weeklyGoalCard = driver.findElementByAccessibilityId("home_card_weekly_goal");
        mobileActions.swipeByElements(ptCard, weeklyGoalCard);
        //fazer scroll no your program
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        reduceSymptomsTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Reduce your symptoms']");
        yourProgramTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Your program']");
        mobileActions.swipeByElements(reduceSymptomsTxt, yourProgramTxt);
        //clicar em skip
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Skip']", driver);
        //validar que abriu a home
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Your Physical Therapist']")));

        ConfigurationsiOS.killDriver();
    }
}
