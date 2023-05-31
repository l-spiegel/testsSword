package appium.Android;

import java.net.MalformedURLException;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import static org.hamcrest.MatcherAssert.assertThat;

public class textFieldBehavior {

    private AndroidDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsAndroid.getDriver();
    }

    private final static String CHECK_LOGIN = "login_screen";
    private final static String CHECK_EMAIL_TEXT = "email_textfield_filled";
    private final static String CHECK_PASS_TEXT = "pass_textfield_filled";
    private final static String CHECK_RECOVER = "recover_screen";
    private final static String CHECK_RECOVER_SCREEN_FILLED = "recover_screen_filled";

    private final static By LOGIN_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Login']");
    private final static By EMAIL_TEXT = MobileBy.xpath("//android.widget.TextView[@text='I have a QR card']");
    private final static By PASS_TEXT = MobileBy.xpath("//android.widget.TextView[@text='I have a QR card']");
    private final static By RECOVER_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Recover my password']");
    private final static By RECOVER_SCREEN_FILLED = MobileBy.xpath("//android.widget.TextView[@text='Send instructions']");

    private Object getResource(String string) {
        // TODO Auto-generated method stub
        return null;
    }

    private WebElement waitForElement(WebDriverWait wait, By selector) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(selector));

        try {Thread.sleep(750); } catch (InterruptedException ign) {}

        return el;
    }

    @Test
    public void testLoginAndRecoverPass() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        VisualCheck visualCheck = new VisualCheck(driver);
        MobileActions mobileActions = new MobileActions(driver);

        //abre o ecrã de login e tira screenshot
        waitForElement(wait, LOGIN_SCREEN);
        visualCheck.doVisualCheck(CHECK_LOGIN);
        //clica no textfield do email e tira screenshot
        MobileElement email = (MobileElement) driver.findElementByXPath("//android.widget.EditText[1]");
        email.click();
        email.sendKeys("l.spiegel+3@swordhealth.com");
        mobileActions.tapByCoordinates(878, 302);
        waitForElement(wait, EMAIL_TEXT);
        visualCheck.doVisualCheck(CHECK_EMAIL_TEXT);
        //clica no textfield da pass e tira screenshot
        MobileElement pass = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
        pass.click();
        pass.sendKeys("12345");
        mobileActions.tapByCoordinates(878, 302);
        waitForElement(wait, PASS_TEXT);
        visualCheck.doVisualCheck(CHECK_PASS_TEXT);
        //abre recover pass e tira screenshot
        driver.findElementByAccessibilityId("loginRecoverPasswordButton").click();
        waitForElement(wait, RECOVER_SCREEN);
        visualCheck.doVisualCheck(CHECK_RECOVER);
        //clica no textfield e tira screenshot
        MobileElement recoverEmail = (MobileElement) driver.findElementByXPath("//android.widget.EditText");
        recoverEmail.click();
        recoverEmail.sendKeys("hdkldkl@hdhd.oidj");
        mobileActions.tapByCoordinates(763, 872);
        waitForElement(wait, RECOVER_SCREEN_FILLED);
        visualCheck.doVisualCheck(CHECK_RECOVER_SCREEN_FILLED);

        ConfigurationsAndroid.killDriver();
    }

}
