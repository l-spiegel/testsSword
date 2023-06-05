package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class SwordRegressionWizard {

    private final static String CHECK_EXERCISE_HISTORY_1 = "exercise_history_1";
    private final static String VALIDATION_PATH = "/Users/luizaspiegel/Documents/image check/regression wizard/Android";
    private final static String BASELINE = "COMPARISION_";

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
        VisualCheck visualCheckAndroid = new VisualCheck(driver);

        System.out.println("Ligar proxy - Mudar wizard para TRUE");

        //fazer login
        utilitiesAndroid.login("l.spiegel+3@swordhealth.com", "Test1234!", driver);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (driver.findElements(By.xpath("//android.widget.TextView[@text='Your Physical Therapist']")).size() > 0) {
            System.out.println("Ligar proxy - Mudar WIZARD para TRUE");
            ConfigurationsAndroid.killDriver();
        }

        //validar 1st screen
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='How it works']")));
        driver.findElementByXPath("//android.widget.TextView[@text='Your program']");
        driver.findElementByXPath("//android.widget.TextView[@text='During your program, your PT will monitor your progress, offer support, and more.']");
        driver.findElementByXPath("//android.widget.TextView[@text='Program goal']");
        driver.findElementByXPath("//android.widget.TextView[@text='9 or more sessions']");
        driver.findElementByXPath("//android.widget.TextView[@text='For best results, try to complete at least 9 sessions (you can always do more!), plus a reassessment with me.']");
        driver.findElementByXPath("//android.widget.TextView[@text='Prepare your body']");
        driver.findElementByXPath("//android.widget.TextView[@text='Activate your muscles']");
        driver.findElementByXPath("//android.widget.TextView[@text='Build strength and endurance']");
        driver.findElementByXPath("//android.widget.TextView[@text='Reduce your symptoms']");
        driver.findElementByXPath("//android.widget.TextView[@text='Handle more reps']");
        driver.findElementByXPath("//android.widget.TextView[@text='Meet program goals']");

        //scroll - validar restante screen - Next button
        MobileElement secondCard = driver.findElementByXPath("//android.widget.TextView[@text='Reduce your symptoms']");
        MobileElement title = driver.findElementByXPath("//android.widget.TextView[@text='9 or more sessions']");
        mobileActions.swipeByElements(secondCard, title);
        driver.findElementByXPath("//android.widget.TextView[@text='Reassessment to check your progress\n" +
                "ETA: 3rd week of program']");
        driver.findElementByXPath("//android.widget.TextView[@text='Next']");
        driver.findElementByXPath("//android.widget.TextView[@text='Skip']");
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.widget.Button", driver);

        //validar 2nd screen - Next button
        driver.findElementByXPath("//android.widget.TextView[@text='How it works']");
        driver.findElementByXPath("//android.widget.TextView[@text='Your personal goals']");
        driver.findElementByXPath("//android.widget.TextView[@text='Track your progress toward your personal health goals.']");
        driver.findElementByXPath("//android.widget.TextView[@text='I’ve customized your sessions based on the goals you selected.']");
        driver.findElementByXPath("//android.widget.TextView[@text='Next']");
        driver.findElementByXPath("//android.widget.TextView[@text='Skip']");

        //Validar Personal Goals que tem e imprimir
        List<String> validarPersonalGoals = new ArrayList<>();
        validarPersonalGoals.add("Decrease my pain or symptoms");
        validarPersonalGoals.add("Decrease/avoid pain using medications");
        validarPersonalGoals.add("Return to my exercise routine");
        validarPersonalGoals.add("Return to playing sports");
        validarPersonalGoals.add("Return to my hobbies/daily activities");
        validarPersonalGoals.add(" Return to my regular work duties");
        validarPersonalGoals.add("Return to my usual family routine");
        validarPersonalGoals.add("Improve my physical fitness");
        validarPersonalGoals.add("Improve my mental well-being");
        validarPersonalGoals.add("Improve my quality of sleep");
        validarPersonalGoals.add("Prevent future pain, injury and/or falls");
        validarPersonalGoals.add("Avoid surgery");
        validarPersonalGoals.add("Other: Personalized member goal?????");

        int maxPersonalGoals = 3;
        int personalGoalsFound = 0;

        for (String personalGoals : validarPersonalGoals) {
            if (driver.findElements(By.xpath("//android.widget.TextView[@text='"+ personalGoals +"']")).size() > 0) {
                System.out.println(personalGoals);
                personalGoalsFound++;
            }
            if (personalGoalsFound == maxPersonalGoals) {
                break;
            }
        }
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.widget.Button", driver);

        //validar 3rd screen
        driver.findElementByXPath("//android.widget.TextView[@text='How it works']");
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
        MobileElement cardReminder = driver.findElementByXPath("//android.widget.TextView[@text='Set a reminder']");
        MobileElement weeklyGoal = driver.findElementByXPath("//android.widget.TextView[@text='Weekly goal']");
        mobileActions.swipeByElements(cardReminder, weeklyGoal);
        driver.findElementByXPath("//android.widget.TextView[@text='See my program']");

        //Criar reminder = Weekly Goal
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.widget.TextView", driver);
        utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_1", driver);
        utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_2", driver);
        utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_3", driver);
        utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_4", driver);
        utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);

        //Carregar - See my program button
        //Na home - ver se recebeu pop up badges achieved e validar
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.widget.Button", driver);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        driver.findElementByXPath("//android.widget.TextView[@text='Reminders Scheduled']");
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.TextView", driver);
        //PULL TO REFRESH para mostrar o wizard
        MobileElement ptCard = driver.findElementByAccessibilityId("home_card_pt");
        MobileElement weeklyCard = driver.findElementByAccessibilityId("home_card_weekly_goal");
        mobileActions.swipeByElements(ptCard, weeklyCard);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='How it works']")));

        //no 1st screen - Skip button - validar que abriu a home
        secondCard = driver.findElementByXPath("//android.widget.TextView[@text='Reduce your symptoms']");
        title = driver.findElementByXPath("//android.widget.TextView[@text='9 or more sessions']");
        mobileActions.swipeByElements(secondCard, title);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.widget.TextView", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Your Physical Therapist']")));
        ConfigurationsAndroid.killDriver();
    }
}




