package appium;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.remote.MobileCapabilityType;

public class SwordRegressionChat {
	
	@Test
	public void virtualPt() throws MalformedURLException {
		AndroidDriver<MobileElement> driver = inicializarAppium();
		WebDriverWait wait = new WebDriverWait(driver,20);
	  
		MobileElement el1 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[1]");
		el1.clear();
		el1.sendKeys("l.spiegel+3@swordhealth.com");
		MobileElement el2 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el2.click();
		el2.sendKeys("Test1234!");
		//clicar no olhinho
		MobileElement el3 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.EditText[2]/android.view.View/android.view.View[2]/android.widget.Button");
		el3.click();
		el3.click();
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		driver.findElementByXPath("//android.widget.TextView[@text='Not now']").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']")));
		driver.findElementByAccessibilityId("bottom_navigation_chat_tab").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.EditText")));
		MobileElement el7 = (MobileElement) driver.findElementById("com.swordhealth.guarda.dev:id/etInputText");
		el7.click();
		el7.sendKeys("Olá!");
		driver.findElementById("com.swordhealth.guarda.dev:id/ibtnSend").click();
		//mandar foto do rolo
		driver.findElementById("com.swordhealth.guarda.dev:id/ibtnAdd").click();
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]").click();
		driver.findElementByXPath("//android.widget.FrameLayout[@content-desc='Photo taken on Feb 16, 2023, 6:00:51 PM']/androidx.cardview.widget.CardView/android.widget.FrameLayout/android.widget.ImageView").click();
		//voltar pra home
		driver.findElementByAccessibilityId("bottom_navigation_home_tab").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_pt']")));
		
	}

	private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
		// TODO Auto-generated method stub
		
	}

	private AndroidDriver<MobileElement> inicializarAppium() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "android");
	    desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
	    desiredCapabilities.setCapability("appium:deviceName", "07111JEC201460");
	    desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/luizaspiegel/Downloads/app-sword-qa1615.apk");
	    desiredCapabilities.setCapability("appium:noReset", "false");
	    desiredCapabilities.setCapability("appium:autoGrantPermissions", "true");
	    
	    AndroidDriver<MobileElement> driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}