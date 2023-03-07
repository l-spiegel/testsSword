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

public class SwordRegressionLoginLogoutiPhone {
private IOSDriver driver;


private IOSDriver<MobileElement> inicializarAppium() throws MalformedURLException {
//Setting Desired Capabilities
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("platformName", "iOS");
	capabilities.setCapability("platformVersion", "16.1.1");
	capabilities.setCapability("deviceName", "iPhone 11 Pro");
	capabilities.setCapability("udid", "00008030-001115363A7A802E");
	capabilities.setCapability("automationName", "XCUITest");
	capabilities.setCapability("xcodeOrgId", "698N4JU9B9");
	capabilities.setCapability("xcodeSigningId", "iPhone Developer");
	capabilities.setCapability("app", "/Users/luizaspiegel/Downloads/SWORDHealthRelease430-117.ipa");
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
	public void errosELoginPage() throws MalformedURLException {
		IOSDriver<MobileElement> driver = inicializarAppium();
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		driver.findElementByAccessibilityId("Allow").click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("loginQRCodeButton");
		el1.click();
		String el2 = driver.findElementByAccessibilityId("Application permissions").getText();
			Assert.assertEquals("Application permissions", el2);
		String el3 = driver.findElementByAccessibilityId("The app needs the following permissions to work correctly:").getText();
			Assert.assertEquals("The app needs the following permissions to work correctly:", el3);
		String camera = driver.findElementByAccessibilityId("We use the camera to read your patient card QR code and sign you into Sword").getText();
			Assert.assertEquals("We use the camera to read your patient card QR code and sign you into Sword", camera);
		MobileElement el4 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Next']");
		el4.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Next']")));
		String el5 = driver.findElementByAccessibilityId("Present your card").getText();
			Assert.assertEquals("Present your card", el5);
		String el6 = driver.findElementByAccessibilityId("Hold the card still for a few seconds and don't cover the red square").getText();
			Assert.assertEquals("Hold the card still for a few seconds and don't cover the red square", el6);
		MobileElement el7 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Next']");
		el7.click();
		MobileElement el8 = (MobileElement) driver.findElementByAccessibilityId("OK");
		el8.click();
		MobileElement el9 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Back']");
		el9.click();
		MobileElement el10 = (MobileElement) driver.findElementByAccessibilityId("1PxIcChevronLeft");
		el10.click();
		MobileElement el11 = (MobileElement) driver.findElementByAccessibilityId("1PxIcChevronLeft");
		el11.click();
		MobileElement el12 = (MobileElement) driver.findElementByAccessibilityId("loginSignupButton");
		el12.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Relieve back and joint pain']")));
		MobileElement el13 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el13.click();
		MobileElement el14 = (MobileElement) driver.findElementByAccessibilityId("loginRecoverPasswordButton");
		el14.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Recover my password']")));
		String recoverPass = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("Enter the email associated with your account. Then, we'll send you an email showing you how to reset your password", recoverPass);
		MobileElement el15 = (MobileElement) driver.findElementByAccessibilityId("recoverPasswordEmailTextfield");
		el15.click();
		el15.sendKeys("hjvhik");
		MobileElement el16 = (MobileElement) driver.findElementByAccessibilityId("recoverPasswordButton");
		el16.click();
		MobileElement el18 = (MobileElement) driver.findElementByAccessibilityId("recoverPasswordEmailTextfield");
		el18.click();
		el18.sendKeys("@jjho");
		MobileElement el19 = (MobileElement) driver.findElementByAccessibilityId("recoverPasswordButton");
		el19.click();
		MobileElement el20 = (MobileElement) driver.findElementByAccessibilityId("recoverPasswordEmailTextfield");
		el20.click();
		el20.sendKeys("jsks.com");
		MobileElement el21 = (MobileElement) driver.findElementByAccessibilityId("recoverPasswordButton");
		el21.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeImage")));
		String el22 = driver.findElementByAccessibilityId("Check your email").getText();
			Assert.assertEquals("Check your email", el22);
		String el23 = driver.findElementByAccessibilityId("If you have an active Sword account, you'll see an email from us showing you how to reset your password").getText();
			Assert.assertEquals("If you have an active Sword account, you'll see an email from us showing you how to reset your password", el23);
		MobileElement el24 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Ok']");
		el24.click();
		MobileElement el25 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el25.click();
		el25.sendKeys("12345");
		MobileElement el26 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el26.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//XCUIElementTypeStaticText[@name='Login'])")));
		MobileElement el28 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el28.click();
		MobileElement el29 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el29.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")));
		String el31 = driver.findElementByAccessibilityId("Uh-oh!").getText();
			Assert.assertEquals("Uh-oh!", el31);
		String el32 = driver.findElementByXPath("//XCUIElementTypeStaticText[2]").getText();
			Assert.assertEquals("You've reached the maximum number of login attempts. Please try again in a few minutes.", el32);
		MobileElement el33 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Ok']");
		el33.click();
		
		driver.quit();
}
	
	@Test
	public void fazerLogin() throws MalformedURLException {
		IOSDriver<MobileElement> driver = inicializarAppium();
		WebDriverWait wait = new WebDriverWait(driver,20);
	  
		driver.findElementByAccessibilityId("Allow").click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
	//	MobileElement el1 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTextField");
	//	el1.clear();
	//	el1.sendKeys("nove@maio.com");
		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el2.click();
		el2.sendKeys("10março!");
		MobileElement el5 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el5.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		String el6 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Create your PIN code']").getText();
			Assert.assertEquals("Create your PIN code", el6);
		String el7 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("You can use your PIN code to log in any time your session expires.", el7);
		MobileElement el8 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not now']");
		el8.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		MobileElement el10 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el10.click();
		MobileElement el11 = (MobileElement) driver.findElementByAccessibilityId("menu_option_logout");
		el11.click();
		//novo login
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		MobileElement el12 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el12.click();
		el12.sendKeys("10março!");
		MobileElement el13 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el13.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el14 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Create PIN']");
		el14.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		String el15 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Create your PIN code']").getText();
			Assert.assertEquals("Create your PIN code", el15);
		String el16 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set PIN code later']").getText();
			Assert.assertEquals("Set PIN code later", el16);
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set PIN code later']").click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		MobileElement el17 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el17.click();
		MobileElement el18 = (MobileElement) driver.findElementByAccessibilityId("menu_option_logout");
		el18.click();
		MobileElement el19 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el19.click();
		el19.sendKeys("10março!");
		el19.click();
		MobileElement el20 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el20.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el21 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Create PIN']");
		el21.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el22 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el22.click();
		el22.click();
		el22.click();
		MobileElement el23 = (MobileElement) driver.findElementByAccessibilityId("deleteKey");
		el23.click();
		MobileElement el24 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el24.click();
		el24.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		String el25 = driver.findElementByAccessibilityId("Confirm your PIN code").getText();
			Assert.assertEquals("Confirm your PIN code", el25);
		MobileElement el26 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el26.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el27 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
		el27.click();
		el27.click();
		el27.click();
		el27.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		MobileElement el28 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='7']");
		el28.click();
		el28.click();
		el28.click();
		el28.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")));
		String el30 = driver.findElementByAccessibilityId("Uh-oh! The PIN codes didn't match. Please try again.").getText();
			Assert.assertEquals("Uh-oh! The PIN codes didn't match. Please try again.", el30);
		MobileElement el31 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']");
		el31.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el33 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el33.click();
		el33.click();
		el33.click();
		el33.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		MobileElement el35 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
		el35.click();
		el35.click();
		el35.click();
		el35.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")));
		String el37 = driver.findElementByAccessibilityId("Want to use Face ID for future logins? You can activate it now, or activate it later in Settings.").getText();
			Assert.assertEquals("Want to use Face ID for future logins? You can activate it now, or activate it later in Settings.", el37);
		MobileElement el38 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Activate now']");
		el38.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		MobileElement el39 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el39.click();
		MobileElement el40 = (MobileElement) driver.findElementByAccessibilityId("menu_option_logout");
		el40.click();

		driver.quit();
	}
}