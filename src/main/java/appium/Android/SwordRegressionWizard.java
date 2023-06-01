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
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='How it works']")));

        //validar 1st screen

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            driver.findElementByXPath("//android.widget.TextView[@text='Your program']");
            driver.findElementByXPath("//android.widget.TextView[@text='During your program, your PT will monitor your progress, offer support, and more.']");
            driver.findElementByXPath("//android.widget.TextView[@text='Program goal']");
            driver.findElementByXPath("//android.widget.TextView[@text='For best results, try to complete at least 9 sessions (you can always do more!), plus a reassessment with me.']");
            //scroll - validar restante screen - Next button
            MobileElement firstCard = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.TextView[8]");
            MobileElement title = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.TextView[1]");
            mobileActions.swipeByElements(firstCard, title);
            driver.findElementByXPath("//android.widget.TextView[@text='Next']");
            driver.findElementByXPath("//android.widget.TextView[@text='Skip']");
            utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.widget.Button", driver);

            //validar 2nd screen - Next button

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            driver.findElementByXPath("//android.widget.TextView[@text='Your personal goals']");
            driver.findElementByXPath("//android.widget.TextView[@text='Track your progress toward your personal health goals.']");
            driver.findElementByXPath("//android.widget.TextView[@text='I’ve customized your sessions based on the goals you selected.']");
            driver.findElementByXPath("//android.widget.TextView[@text='Next']");
            driver.findElementByXPath("//android.widget.TextView[@text='Skip']");
            utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.widget.Button", driver);

            //validar 3rd screen

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            driver.findElementByXPath("//android.widget.TextView[@text='Your weekly goal']");
            driver.findElementByXPath("//android.widget.TextView[@text='Hitting your personal health goals starts with your weekly goal.']");
            driver.findElementByXPath("//android.widget.TextView[@text='Weekly goal']");
            driver.findElementByXPath("//android.widget.TextView[@text='Set a reminder']");
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

            utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.TextView", driver);

            //fazer logout - fazer login com mesmo user
            //ALTERAR PARA PULL TO REFRESH para mostrar o wizard e eliminar aqui estes steps
            utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
            utilitiesAndroid.clickByAccessibilityId("menu_option_logout", driver);
            utilitiesAndroid.login("l.spiegel+3@swordhealth.com", "Test1234!", driver);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='How it works']")));

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

             //no 1st screen - Skip button - validar que abriu a home
            //ver aqui o scroll porque usei o mesmo de cima e estava a deu erro ?????

            MobileElement firstCard1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.TextView[8]");
            MobileElement title1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.TextView[1]");
            mobileActions.swipeByElements(firstCard1, title1);
            utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.widget.TextView", driver);

            ConfigurationsAndroid.killDriver();

        }
    }




