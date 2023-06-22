package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class CrashFinishAccount {

    private AndroidDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsAndroid.getDriver();
    }

    @Test
    public void finishAccount() throws Exception {
        MobileActions mobileActions = new MobileActions(driver);
        WebDriverWait wait = new WebDriverWait(driver,50);
        UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();

        utilitiesAndroid.clickByAccessibilityId("loginSignupButton", driver);
        utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='Signup_']", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Now, let's create your account.\"]")));
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[1]").sendKeys("lll");
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[2]").sendKeys("lll");
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[3]").sendKeys("lll@lll.com");
        //date picker
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[4]").click();
        driver.findElementById("android:id/date_picker_header_year").click();
        MobileElement year2017 = driver.findElementByXPath("//android.widget.TextView[@text='2017']");
        MobileElement year2023 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.DatePicker/android.widget.LinearLayout/android.widget.ScrollView/android.widget.ViewAnimator/android.widget.ListView/android.widget.TextView[7]");
        mobileActions.swipeByElements(year2017, year2023);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        MobileElement year2011 = driver.findElementByXPath("//android.widget.TextView[@text='2011']");
        MobileElement year2016 = driver.findElementByXPath("//android.widget.TextView[@text='2016']");
        mobileActions.swipeByElements(year2011, year2016);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        MobileElement year2006 = driver.findElementByXPath("//android.widget.TextView[@text='2006']");
        MobileElement year2010 = driver.findElementByXPath("//android.widget.TextView[@text='2010']");
        mobileActions.swipeByElements(year2006, year2010);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='2001']", driver);
        driver.findElementById("android:id/button1").click();
        utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[4]", driver);
        utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='American Samoa']", driver);
        MobileElement checkbox3 = driver.findElementByXPath("//android.widget.TextView[@text=\"I have read and consent to Sword Health's Informed Consent for Research to participate in the study of Digital Care Programs for Musculoskeletal Health (optional).\"]");
        MobileElement emailField = driver.findElementByAccessibilityId("signup_email_textfield");
        mobileActions.swipeByElements(checkbox3, emailField);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesAndroid.clickByAccessibilityId("signup_checkbox_1", driver);
        utilitiesAndroid.clickByAccessibilityId("signup_checkbox_2", driver);
        utilitiesAndroid.clickByAccessibilityId("signup_checkbox_3", driver);
        utilitiesAndroid.clickByAccessibilityId("signup_checkbox_4", driver);
        utilitiesAndroid.clickByAccessibilityId("signup_checkbox_5", driver);
        utilitiesAndroid.clickByAccessibilityId("signup_continue_button", driver);
        //insurance info page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Insurance information\"]")));
        driver.findElementByClassName("android.widget.EditText").sendKeys("12345");
        utilitiesAndroid.clickByAccessibilityId("signup_continue_button", driver);
        //finish account page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Finish your account setup\"]")));
        MobileElement phoneField = driver.findElementByXPath("//android.view.View[@content-desc=\"signup_phone_textfield\"]/android.widget.EditText");
        phoneField.sendKeys("0000000000");
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText").sendKeys("12345678");
        for (int i=0; i<5; i++) {
            utilitiesAndroid.clickByAccessibilityId("signup_create_account_button", driver);
            //error screen
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Try again\"]")));
            utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button", driver);
            phoneField.clear();
            phoneField.sendKeys("12345");
            System.out.println(i);
        }
        //voltar pro ecrã de login
        MobileElement backButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button");
        backButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Insurance information\"]")));

        ConfigurationsAndroid.killDriver();
    }
}
