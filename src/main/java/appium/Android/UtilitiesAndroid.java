package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilitiesAndroid {

    public void clickByAccessibilityId(String id, AndroidDriver<MobileElement> driver) {
        driver.findElementByAccessibilityId(id).click();
    }

    public void clickByXPath(String xpath, AndroidDriver<MobileElement> driver) {
        driver.findElementByXPath(xpath).click();
    }

    public void login(String email, String password, AndroidDriver<MobileElement> driver) {
        WebDriverWait wait = new WebDriverWait(driver,20);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Login']")));
        MobileElement emailField = driver.findElementByXPath("//android.widget.EditText[1]");
        emailField.clear();
        emailField.sendKeys(email);
        MobileElement passField = driver.findElementByXPath("//android.widget.EditText[2]");
        passField.click();
        passField.sendKeys(password);
        driver.findElementByAccessibilityId("loginButton").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
        driver.findElementByXPath("//android.widget.TextView[@text='Not now']").click();
    }
}
