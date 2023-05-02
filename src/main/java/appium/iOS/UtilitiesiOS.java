package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

public class UtilitiesiOS {

    public void clickByAccessibilityId(String id, IOSDriver<MobileElement> driver) {
        driver.findElementByAccessibilityId(id).click();
    }

    public void clickByXPath(String xpath, IOSDriver<MobileElement> driver) {
        driver.findElementByXPath(xpath).click();
    }

    public static void login(String email, String password, IOSDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver,20);

        driver.findElementByAccessibilityId("Allow").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
        MobileElement emailField = driver.findElementByAccessibilityId("loginEmailTextfield");
        emailField.clear();
        emailField.sendKeys(email);
        MobileElement passField = driver.findElementByAccessibilityId("loginPasswordTextfield");
        passField.click();
        passField.sendKeys(password);
        driver.findElementByAccessibilityId("loginButton").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not now']").click();
    }
}
