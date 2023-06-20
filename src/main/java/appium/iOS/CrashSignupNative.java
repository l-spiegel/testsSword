package appium.iOS;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.util.List;

public class CrashSignupNative {

    private IOSDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsiOS.getDriver();
    }

    @Test
    public void crash() throws Exception {
        MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
        WebDriverWait wait = new WebDriverWait(driver,50);
        UtilitiesiOS utilitiesiOS = new UtilitiesiOS();

        utilitiesiOS.clickByAccessibilityId("Allow", driver);
        utilitiesiOS.clickByAccessibilityId("loginSignupButton", driver);
        //client signup_
        utilitiesiOS.clickByAccessibilityId("Signup_", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Create account\"]")));
        driver.findElementByAccessibilityId("signup_first_name_textfield").sendKeys("lll");
        driver.findElementByAccessibilityId("signup_last_name_textfield").sendKeys("lll");
        driver.findElementByAccessibilityId("signup_email_textfield").sendKeys("lll@lll.lll");
        utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[5]/XCUIElementTypeOther", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Time']")));

        String year = "2000";
        List<MobileElement> pw = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));
        // set third PickerWheel - year
        pw.get(2).sendKeys(year);

        utilitiesiOS.clickByAccessibilityId("Done", driver);

        utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[6]/XCUIElementTypeOther", driver);
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name=\"American Samoa\"]", driver);
        MobileElement stateField = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"American Samoa\"]");
        MobileElement lastNameField = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"First name\"]");
        mobileActions.swipeByElements(stateField, lastNameField);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //fazer check nas checkboxes manualmente porque os ids estão quebrados
        MobileElement continueButton = driver.findElementByAccessibilityId("signup_continue_button");
        for (int i=0; i<10; i++) {
            continueButton.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Insurance information\"]")));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            MobileElement backButton = driver.findElementByAccessibilityId("ic arrow left");
            backButton.click();
            System.out.println(i + "x");
        }
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Select your organization\"]")));
        //client signup A
        utilitiesiOS.clickByAccessibilityId("Signup A", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Create account\"]")));
        driver.findElementByAccessibilityId("signup_first_name_textfield").sendKeys("lll");
        driver.findElementByAccessibilityId("signup_last_name_textfield").sendKeys("lll");
        driver.findElementByAccessibilityId("signup_email_textfield").sendKeys("lll@lll.lll");
        utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[5]/XCUIElementTypeOther", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Time']")));

        year = "1990";
        pw = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));
        // set third PickerWheel - year
        pw.get(2).sendKeys(year);

        utilitiesiOS.clickByAccessibilityId("Done", driver);

        utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[6]/XCUIElementTypeOther", driver);
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name=\"American Samoa\"]", driver);
        stateField = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"American Samoa\"]");
        lastNameField = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"First name\"]");
        mobileActions.swipeByElements(stateField, lastNameField);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //fazer check nas checkboxes manualmente porque os ids estão quebrados
        continueButton = driver.findElementByAccessibilityId("signup_continue_button");
        for (int i=0; i<10; i++) {
            continueButton.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Insurance information\"]")));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            MobileElement backButton = driver.findElementByAccessibilityId("ic arrow left");
            backButton.click();
            System.out.println(i + "x");
        }
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Select your organization\"]")));
        //client signup B
        utilitiesiOS.clickByAccessibilityId("Signup B", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Create account\"]")));
        driver.findElementByAccessibilityId("signup_first_name_textfield").sendKeys("lll");
        driver.findElementByAccessibilityId("signup_last_name_textfield").sendKeys("lll");
        driver.findElementByAccessibilityId("signup_email_textfield").sendKeys("lll@lll.lll");
        utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[5]/XCUIElementTypeOther", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Time']")));

        year = "1995";
        pw = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));
        // set third PickerWheel - year
        pw.get(2).sendKeys(year);

        utilitiesiOS.clickByAccessibilityId("Done", driver);

        utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[6]/XCUIElementTypeOther", driver);
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name=\"American Samoa\"]", driver);
        stateField = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"American Samoa\"]");
        lastNameField = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"First name\"]");
        mobileActions.swipeByElements(stateField, lastNameField);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //fazer check nas checkboxes manualmente porque os ids estão quebrados
        continueButton = driver.findElementByAccessibilityId("signup_continue_button");
        for (int i=0; i<10; i++) {
            continueButton.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Insurance information\"]")));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            MobileElement backButton = driver.findElementByAccessibilityId("ic arrow left");
            backButton.click();
            System.out.println(i + "x");
        }
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Select your organization\"]")));
        //client signup C
        utilitiesiOS.clickByAccessibilityId("Signup C", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Create account\"]")));
        driver.findElementByAccessibilityId("signup_first_name_textfield").sendKeys("lll");
        driver.findElementByAccessibilityId("signup_last_name_textfield").sendKeys("lll");
        driver.findElementByAccessibilityId("signup_email_textfield").sendKeys("lll@lll.lll");
        utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[5]/XCUIElementTypeOther", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Time']")));

        year = "1985";
        pw = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));
        // set third PickerWheel - year
        pw.get(2).sendKeys(year);

        utilitiesiOS.clickByAccessibilityId("Done", driver);

        utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[6]/XCUIElementTypeOther", driver);
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name=\"American Samoa\"]", driver);
        stateField = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"American Samoa\"]");
        lastNameField = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"First name\"]");
        mobileActions.swipeByElements(stateField, lastNameField);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //fazer check nas checkboxes manualmente porque os ids estão quebrados
        continueButton = driver.findElementByAccessibilityId("signup_continue_button");
        for (int i=0; i<10; i++) {
            continueButton.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Insurance information\"]")));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            MobileElement backButton = driver.findElementByAccessibilityId("ic arrow left");
            backButton.click();
            System.out.println(i + "x");
        }
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Select your organization\"]")));
        //client signup D
        utilitiesiOS.clickByAccessibilityId("Signup D", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Create account\"]")));
        driver.findElementByAccessibilityId("signup_first_name_textfield").sendKeys("lll");
        driver.findElementByAccessibilityId("signup_last_name_textfield").sendKeys("lll");
        driver.findElementByAccessibilityId("signup_email_textfield").sendKeys("lll@lll.lll");
        utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[5]/XCUIElementTypeOther", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Time']")));

        year = "1980";
        pw = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));
        // set third PickerWheel - year
        pw.get(2).sendKeys(year);

        utilitiesiOS.clickByAccessibilityId("Done", driver);

        utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[6]/XCUIElementTypeOther", driver);
        utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name=\"American Samoa\"]", driver);
        stateField = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"American Samoa\"]");
        lastNameField = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"First name\"]");
        mobileActions.swipeByElements(stateField, lastNameField);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //fazer check nas checkboxes manualmente porque os ids estão quebrados
        continueButton = driver.findElementByAccessibilityId("signup_continue_button");
        for (int i=0; i<10; i++) {
            continueButton.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Yor health coverage\"]")));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            MobileElement backButton = driver.findElementByAccessibilityId("ic arrow left");
            backButton.click();
            System.out.println(i + "x");
        }
        utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Select your organization\"]")));

        ConfigurationsiOS.killDriver();
    }
}
