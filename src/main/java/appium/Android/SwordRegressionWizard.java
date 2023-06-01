package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;

public class SwordRegressionWizard {

    private final static String CHECK_YOUR_PROGRAM_SCREEN_1 = "your_program_1";
    private final static String CHECK_YOUR_PROGRAM_SCREEN_2 = "your_program_2";
    private final static String CHECK_YOUR_PERSONAL_GOALS_SCREEN = "your_personal_goals";
    private final static String CHECK_YOUR_WEEKLY_GOAL_SCREEN_1 = "your_weekly_goal_1";
    private final static String CHECK_YOUR_WEEKLY_GOAL_SCREEN_2 = "your_weekly_goal_2";
    private final static String CHECK_REMINDERS_POPUP = "reminders_popup";

    private AndroidDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsAndroid.getDriver();
    }

    @Test
    public void live() throws Exception {
        MobileActions mobileActions = new MobileActions(driver);
        WebDriverWait wait = new WebDriverWait(driver,50);
        UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
        VisualCheck visualCheck = new VisualCheck(driver);

        //fazer login
        utilitiesAndroid.login("l.spiegel+3@swordhealth.com", "Test1234!", driver);
        //validar your program
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (driver.findElements(By.xpath("//android.widget.TextView[@text='Your Physical Therapist']")).size() > 0) {
            System.out.println("LIGA O PROXY PRO WIZARD");
            ConfigurationsAndroid.killDriver();
        }
        driver.findElementByXPath("//android.widget.TextView[@text='How it works']");
        driver.findElementByXPath("//android.widget.TextView[@text='Your program']");
        driver.findElementByXPath("//android.widget.TextView[@text='During your program, your PT will monitor your progress, offer support, and more.']");
        driver.findElementByXPath("//android.widget.TextView[@text='Program goal']");
        driver.findElementByXPath("//android.widget.TextView[@text='9 or more sessions']");
        driver.findElementByXPath("//android.widget.TextView[@text='For best results, try to complete at least 9 sessions (you can always do more!), plus a reassessment with me.']");
        driver.findElementByXPath("//android.widget.TextView[@text='Prepare your body']");
        driver.findElementByXPath("//android.widget.TextView[@text='Activate your muscles']");
        driver.findElementByXPath("//android.widget.TextView[@text='Build strength and endurance']");
        driver.findElementByXPath("//android.widget.TextView[@text='Reduce your symptoms']");
        //teste visual
        visualCheck.doVisualCheck(CHECK_YOUR_PROGRAM_SCREEN_1);
        //fazer scroll
        MobileElement reduceSymptomsTxt = driver.findElementByXPath("//android.widget.TextView[@text='Reduce your symptoms']");
        MobileElement yourProgramTxt = driver.findElementByXPath("//android.widget.TextView[@text='Your program']");
        mobileActions.swipeByElements(reduceSymptomsTxt, yourProgramTxt);
        //validar o restante do your program
        driver.findElementByXPath("//android.widget.TextView[@text='Handle more reps']");
        driver.findElementByXPath("//android.widget.TextView[@text='Meet program goals']");
        driver.findElementByXPath("//android.widget.TextView[@text='Reassessment to check your progress\n" +
                "ETA: 3rd week of program']");
        driver.findElementByXPath("//android.widget.TextView[@text='Next']");
        driver.findElementByXPath("//android.widget.TextView[@text='Skip']");
        //teste visual
        visualCheck.doVisualCheck(CHECK_YOUR_PROGRAM_SCREEN_2);
        //next
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        //validar your personal goals - só é visível se tiver program goals - muda de user pra user -> ver como posso distinguir o user de staging do de prod -> goals do l.spiegel+3
        driver.findElementByXPath("//android.widget.TextView[@text='Your personal goals']");
        driver.findElementByXPath("//android.widget.TextView[@text='Track your progress toward your personal health goals.']");
        driver.findElementByXPath("//android.widget.TextView[@text=\"I’ve customized your sessions based on the goals you selected.\"]");
        driver.findElementByXPath("//android.widget.TextView[@text='Decrease my pain or symptoms']");
        driver.findElementByXPath("//android.widget.TextView[@text='Decrease/avoid pain using medications']");
        driver.findElementByXPath("//android.widget.TextView[@text='Return to my hobbies/daily activities']");
        driver.findElementByXPath("//android.widget.TextView[@text='Next']");
        driver.findElementByXPath("//android.widget.TextView[@text='Skip']");
        //teste visual
        visualCheck.doVisualCheck(CHECK_YOUR_PERSONAL_GOALS_SCREEN);
        //next
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        //validar your weekly goal
        driver.findElementByXPath("//android.widget.TextView[@text='Your weekly goal']");
        driver.findElementByXPath("//android.widget.TextView[@text='Hitting your personal health goals starts with your weekly goal.']");
        driver.findElementByXPath("//android.widget.TextView[@text='Weekly goal']");
        driver.findElementByXPath("//android.widget.TextView[@text='Complete 4 sessions per week']");
        driver.findElementByXPath("//android.widget.TextView[@text='Quick tip: You can set reminders for yourself to make sure you never miss a session. You can also customize the days and times you receive them.\n" +
                "Set your first reminder below!']");
        driver.findElementByXPath("//android.widget.TextView[@text='Set a reminder']");
        driver.findElementByXPath("//android.widget.TextView[@text=\"Now, you’re ready to do your first session! If you have questions, you can message me anytime.\n" +
                "\n" +
                "Let’s get moving!\"]");
        //teste visual
        visualCheck.doVisualCheck(CHECK_YOUR_WEEKLY_GOAL_SCREEN_1);
        //scroll
        MobileElement getMovingTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"Now, you’re ready to do your first session! If you have questions, you can message me anytime.\n" +
                "\n" +
                "Let’s get moving!\"]");
        MobileElement yourWeeklyGoalTxt = driver.findElementByXPath("//android.widget.TextView[@text='Your weekly goal']");
        mobileActions.swipeByElements(getMovingTxt, yourWeeklyGoalTxt);
        //validar o resto do your weekly goal
        driver.findElementByXPath("//android.widget.TextView[@text='See my program']");
        //teste visual
        visualCheck.doVisualCheck(CHECK_YOUR_WEEKLY_GOAL_SCREEN_2);
        //clicar em set a reminder
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]", driver);
        //criar um reminder com dias = weekly goal
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Set a new reminder']")));
        utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_0", driver);
        utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_1", driver);
        utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_2", driver);
        utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_3", driver);
        //salvar
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.Button", driver);
        //voltar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
        //see my program
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        //validar popup dos reminders
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Congrats! You earned a new badge!']")));
        driver.findElementByXPath("//android.widget.TextView[@text='Reminders Scheduled']");
        //teste visual
        visualCheck.doVisualCheck(CHECK_REMINDERS_POPUP);
        //clicar em ok
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
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
        reduceSymptomsTxt = driver.findElementByXPath("//android.widget.TextView[@text='Reduce your symptoms']");
        yourProgramTxt = driver.findElementByXPath("//android.widget.TextView[@text='Your program']");
        mobileActions.swipeByElements(reduceSymptomsTxt, yourProgramTxt);
        //clicar em skip
        utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='Skip']", driver);
        //validar que abriu a home
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Your Physical Therapist']")));

        ConfigurationsAndroid.killDriver();
    }
}
