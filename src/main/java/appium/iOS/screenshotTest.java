package appium.iOS;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import appium.iOS.MobileActionsiOS;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.CoreMatchers.*;


public class screenshotTest {
	private IOSDriver driver;
	
	private final static String VALIDATION_PATH = "/Users/luizaspiegel/Desktop/visualizations";

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
		capabilities.setCapability("app", "/Users/luizaspiegel/Downloads/SWORDHealth1565.ipa");
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
	public void preventive() throws IOException {
		IOSDriver<MobileElement> driver = inicializarAppium();
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		//login
		driver.findElementByAccessibilityId("Allow").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Welcome to Sword']")));
		
		//tirar screenshot 1
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("screen1.jpg"));
		byte[] screenshot1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		
		MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el1.sendKeys("1234");
		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el2.click();
		try {
			Thread.sleep(3000);
			} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	//	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=' Your email or password is incorrect']")));
		
		//tirar screenshot 2
		File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile2, new File("screen2.jpg"));
		byte[] screenshot2 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		
		//comparar os screenshots
		SimilarityMatchingResult result = driver
		        .getImagesSimilarity(screenshot1, screenshot2, new SimilarityMatchingOptions()
		                .withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		System.out.println(result.getScore());
		assertThat(result.getScore(), is(greaterThan(0.9)));
		String baselineFilename = VALIDATION_PATH + "/" + "WRONG_PASS" + ".png";
        File comparison = new File(baselineFilename);
        result.storeVisualization(comparison);
		
		driver.quit();
			
	}


}