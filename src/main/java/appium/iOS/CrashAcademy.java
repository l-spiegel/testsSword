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

public class CrashAcademy {
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
	capabilities.setCapability("app", "/Users/luizaspiegel/Downloads/SWORDHealth112.ipa");
	capabilities.setCapability("updatedWDABundleId", "com.luizateste2.wda.runner");
	capabilities.setCapability("showXcodeLog", "true");
	capabilities.setCapability("wdaLocalPort", "8200");
	
	IOSDriver<MobileElement> driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
	return driver;
}

private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
	// TODO Auto-generated method stub
	
}
	
	@Test
	public void virtualPT() throws MalformedURLException {
		IOSDriver<MobileElement> driver = inicializarAppium();
		
		driver.findElementByAccessibilityId("Allow").click();
		WebDriverWait wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		MobileElement el1 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeOther[@name='loginEmailTextfield']/XCUIElementTypeTextField");
		el1.clear();
		el1.sendKeys("f.silva@swordhealth.com");
		MobileElement el2 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeOther[@name='loginPasswordTextfield']/XCUIElementTypeSecureTextField");
		el2.sendKeys("Cabixuda12");
		MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el3.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el4 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Not now']");
		el4.click();
		MobileElement el5 = (MobileElement) driver.findElementByAccessibilityId("Academy");
		el5.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Academy']")));
		MobileElement el6 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView[2]/XCUIElementTypeScrollView[1]/XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeOther");
		el6.click();
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("1");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("2");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("3");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("4");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("5");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("6");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("7");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("8");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("9");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("10");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("11");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("12");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("13");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("14");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("15");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("16");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("17");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("18");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("19");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("20");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("21");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("22");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("23");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("24");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("25");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("26");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("27");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("28");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("29");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("30");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("31");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("32");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("33");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("34");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("35");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("36");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("37");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("38");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("39");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("40");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("41");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("42");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("43");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("44");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("45");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("46");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("47");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("48");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("49");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		mobileActions.tapByCoordinates(344, 533);
		System.out.println("50");
		mobileActions.tapByCoordinates(189, 554);
		mobileActions.tapByCoordinates(42, 65); //fechar fullscreen
		
		driver.quit();
	}
}