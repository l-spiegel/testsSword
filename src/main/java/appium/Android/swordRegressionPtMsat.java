package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import java.net.MalformedURLException;
import java.util.Scanner;

public class swordRegressionPtMsat {

    private AndroidDriver<MobileElement> driver;

    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsAndroid.getDriver();
    }
    @Test
    public void PTmsat() throws Exception {
        MobileActions mobileActions = new MobileActions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
        VisualCheck visualCheck = new VisualCheck(driver);

        System.out.println("Ligar proxy - Mudar PTMSAT para TRUE");

        //fazer login
        utilitiesAndroid.login("l.spiegel+3@swordhealth.com", "Test1234!", driver);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //validar ecrã PTMSAT
        driver.findElementByXPath("//android.widget.TextView[@text='How would you rate your experience with Luiza Almeida?']");
        driver.findElementByXPath("//android.widget.TextView[@text='How could we improve your experience?']");
        driver.findElementByXPath("//android.widget.TextView[@text='Share your thoughts here']");
        driver.findElementByXPath("//android.widget.TextView[@text='Share feedback']");
        driver.findElementByXPath("//android.widget.TextView[@text='Later']");

        //Tap "Share feedback" button → Error message
        //Tap on the fourth star → Tap "Share feedback" button → Error message
        //Select some options (other include) → Tap "Share feedback" button → Error message
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.Button", driver);
        driver.findElementByXPath("//android.widget.TextView[@text='Please rate your experience']");
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[4]/android.view.View", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.Button", driver);
        driver.findElementByXPath("//android.widget.TextView[@text='Please select at least one option']");
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[6]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.Button", driver);
        driver.findElementByXPath("//android.widget.TextView[@text='Please share some feedback']");

        //Write something on the text box
        //Unselected the option "Other"
        //Select all options
        //Tap "Later button"

        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.Button", driver);
        MobileElement sendMessage = driver.findElementByXPath("//android.widget.EditText");
        sendMessage.click();
        sendMessage.sendKeys("Tests. Please ignore. Thank you!");
        utilitiesAndroid.clickByXPath("//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[1]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[1]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[3]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[4]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[5]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[2]", driver);

        //Pull to refresh
        MobileElement ptCard = driver.findElementByAccessibilityId("home_card_pt");
        MobileElement weeklyCard = driver.findElementByAccessibilityId("home_card_weekly_goal");
        mobileActions.swipeByElements(ptCard, weeklyCard);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //Tap 2x same star - Tap fifth star → tap on the same star - nothing should happen
        //Tap one star (fifth one) → unselect one star → Tap on "Other" option → Select one more star
        //Unselect one star → unselect another star → unselect another star → unselect another star
        //Select one more star → Select one more star → Select one more star → Select one more star
        //Tap "Share feedback" button → Tap "Close" button

        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[5]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[5]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[4]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[6]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.widget.TextView[6]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[5]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[4]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[3]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[2]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[1]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[2]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[3]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[4]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View[5]", driver);
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[1]", driver);

        driver.findElementByXPath("//android.widget.TextView[@text='Thank you!']");
        driver.findElementByXPath("//android.widget.TextView[@text='We appreciate your feedback.']");
        driver.findElementByXPath("//android.widget.TextView[@text='Close']");

        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View/android.widget.Button",driver);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Your Physical Therapist']")));
        ConfigurationsAndroid.killDriver();
    }
}