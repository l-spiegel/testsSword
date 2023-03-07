package appium;

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


public class wrongPassBug {
	private final static String VALIDATION_PATH = "/Users/luizaspiegel/Desktop/visualizations";

	AndroidDriver<MobileElement> driver;

	private AndroidDriver<MobileElement> inicializarAppium() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "android");
	    desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
	    desiredCapabilities.setCapability("appium:deviceName", "07111JEC201460");
	    desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/luizaspiegel/Downloads/app-sword-qa1603.apk");
	    desiredCapabilities.setCapability("appium:noReset", "false");
	    desiredCapabilities.setCapability("appium:autoGrantPermissions", "true");
	    
	    driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	private Object getResource(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private WebElement waitForElement(WebDriverWait wait, By selector) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(selector));

        try { Thread.sleep(750); } catch (InterruptedException ign) {}
       
        return el;
    }

	
	@Test
	public void preventive() throws Exception {
		AndroidDriver<MobileElement> driver = inicializarAppium();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		//tirar screenshot 1
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Login']")));
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("screen1.jpg"));
		byte[] screenshot1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		
		try {
			Thread.sleep(5000);
			} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		driver.findElementByAccessibilityId("loginEmailTextfield").sendKeys("l.spiegel+3@swordhealth.com");
		driver.findElementByXPath("//android.widget.EditText[2]").sendKeys("Test");
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Your email or password is incorrect']")));
		
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