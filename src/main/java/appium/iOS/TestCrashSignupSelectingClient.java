package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class TestCrashSignupSelectingClient {
	private IOSDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
	}

	@Test
	public void crash() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		VisualCheck visualCheck = new VisualCheck(driver);

//		driver.findElementByAccessibilityId("Allow").click();
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Welcome to Sword']")));
		utilitiesiOS.clickByAccessibilityId("loginSignupButton", driver);
		//select client screen
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Select your organization']");
//		MobileElement searchBar = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTextField");
//		searchBar.click();
//		searchBar.sendKeys("Signup C");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			Thread.currentThread().interrupt();
//		}
		for (int i=1; i<21; i++) {
			utilitiesiOS.clickByAccessibilityId("The Simpsons", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create account']")));
			utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
			System.out.println(i + "x");
		}
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);

	}

}