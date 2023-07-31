package appium.iOS;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import java.time.Duration;

public class UtilitiesiOS {

    public void clickByAccessibilityId(String id, IOSDriver driver) {
        driver.findElement(AppiumBy.accessibilityId(id)).click();
    }

    public void clickByXPath(String xpath, IOSDriver driver) {
        driver.findElement(AppiumBy.xpath(xpath)).click();
    }

    public static void newLogin(String email, String password, IOSDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));

        driver.findElement(AppiumBy.accessibilityId("Allow")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Your journey to a pain-free life starts here']")));
        WebElement emailField = driver.findElement(AppiumBy.accessibilityId("loginEmailTextfield"));
        emailField.clear();
        emailField.sendKeys(email);
        driver.findElement(AppiumBy.accessibilityId("continueButton")). click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Log in']")));
        WebElement passField = driver.findElement(AppiumBy.accessibilityId("loginPasswordTextfield"));
        passField.click();
        passField.sendKeys(password);
        driver.findElement(AppiumBy.accessibilityId("loginButton")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
        driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Not now']")).click();
    }
}
