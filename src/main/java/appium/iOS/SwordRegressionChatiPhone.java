package appium.iOS;

import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SwordRegressionChatiPhone {
private IOSDriver driver;


private IOSDriver<MobileElement> inicializarAppium() throws MalformedURLException {
//Setting Desired Capabilities
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("platformName", "iOS");
	capabilities.setCapability("platformVersion", "16");
	capabilities.setCapability("deviceName", "iPhone 11 Pro");
	capabilities.setCapability("udid", "00008030-001115363A7A802E");
	capabilities.setCapability("automationName", "XCUITest");
	capabilities.setCapability("xcodeOrgId", "698N4JU9B9");
	capabilities.setCapability("xcodeSigningId", "iPhone Developer");
	capabilities.setCapability("app", "/Users/luizaspiegel/Downloads/SWORDHealthRelease420.ipa");
	capabilities.setCapability("updatedWDABundleId", "com.luizateste2.wda.runner");
	capabilities.setCapability("showXcodeLog", "true");
	capabilities.setCapability("wdaLocalPort", "8205");
	capabilities.setCapability("appium:usePrebuiltWDA", "true");
	
	IOSDriver<MobileElement> driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
	return driver;
}

private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
	// TODO Auto-generated method stub
	
}

	
	@Test
	public void virtualPt() throws MalformedURLException {
		IOSDriver<MobileElement> driver = inicializarAppium();
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		driver.findElementByAccessibilityId("Allow").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
	//	MobileElement el1 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTextField");
	//	el1.clear();
	//	el1.sendKeys("luiza@marco.com");
		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el2.click();
		el2.sendKeys("10março!");
		MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el3.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el4 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not now']");
		el4.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		MobileElement el10 = (MobileElement) driver.findElementByAccessibilityId("bottom_navigation_chat_tab");
		el10.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Start typing...']")));
		MobileElement el11 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Start typing...']");
		el11.click();
		el11.sendKeys("Olá!");
		MobileElement el12 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeButton[2]");
		el12.click();
		MobileElement el14 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther");
		el14.click();
		MobileElement el15 = (MobileElement) driver.findElementByAccessibilityId("Home");
		el15.click();
	}
	
}