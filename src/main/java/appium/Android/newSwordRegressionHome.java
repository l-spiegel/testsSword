package appium.Android;

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
import io.appium.java_client.remote.MobileCapabilityType;


public class newSwordRegressionHome {

	@Test
	public void loginVirtualPt() throws MalformedURLException {
		AndroidDriver<MobileElement> driver = inicializarAppium();
		MobileActions mobileActions = new MobileActions(driver);
		
		MobileElement el1 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[1]");
		el1.clear();
		el1.sendKeys("luiza_spiegel@hotmail.com");
		MobileElement el2 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el2.click();
		el2.sendKeys("Test1234!");
		MobileElement el3 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.Button");
		el3.click();
		WebDriverWait wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		MobileElement el4 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Not now']");
		el4.click();
	//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']"))); bug do appium
		try {
			  Thread.sleep(30000); //mudar pra menos depois, precisei aumentar pra um teste e a home demorava muito pra carregar -> mudei em todos os sleeps -> voltar pra 5000 depois
		} catch (InterruptedException e) {
			  Thread.currentThread().interrupt();
		}
		if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']")).size() > 0) {
			String weeklyGoalLabel = driver.findElementByAccessibilityId("home_card_weekly_goal_label").getText();
				System.out.println("Weekly goal label: " + weeklyGoalLabel);
			String weekSessions = driver.findElementByXPath("//android.view.View[@content-desc='home_card_weekly_goal']/android.widget.TextView[2]").getText();
				System.out.println(weekSessions);
		} else {
			System.out.print("API com soluços");
			driver.quit();
		}
		//validar pt card
		if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_card_pt']")).size() > 0) {
			String ptName = driver.findElementByXPath("//android.view.View[@content-desc='home_card_pt']/android.widget.TextView[2]").getText();
			System.out.println("PT card found. PT name: " + ptName);
			//swipe para mostrar até card das sessões por semana	
			MobileElement remindersTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Weekly goal']");
			MobileElement ptCard = (MobileElement) driver.findElementByAccessibilityId("home_card_pt");
			mobileActions.swipeByElements(remindersTxt, ptCard);
		} else {
			System.out.println("PT CARD NOT FOUND!");
			MobileElement remindersButton = (MobileElement) driver.findElementByAccessibilityId("home_card_reminders_button");
			MobileElement weeklyGoalCard = (MobileElement) driver.findElementByAccessibilityId("home_card_weekly_goal");
			mobileActions.swipeByElements(remindersButton, weeklyGoalCard);
		}
		//verificar card session details
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_0']")));
		if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_card_session_details_0']")).size() > 0) {
			String nextSession = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.TextView[2]").getText();
			if (nextSession.contentEquals("Next Session")) {
				System.out.println("SESSION DETAILS WITH NEXT SESSION OVERVIEW AVAILABLE");
			} else {
				System.out.println("SESSION DETAILS WITH NEXT SESSION OVERVIEW NOT AVAILABLE");
			}
		} else {
			System.out.println("SESSION DETAILS NOT FOUND!");
		}
		//swipe pra mostrar o card do weekly sessions - trocar o com xpath pra id do card do session details
		if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_card_session_details_0']")).size() > 0) {
			MobileElement myProgressTxt = (MobileElement) driver.findElementByAccessibilityId("home_card_session_details_0"); //bug por causa do program goal aparecer
			MobileElement dateSelector = (MobileElement) driver.findElementByAccessibilityId("home_card_weekly_goal");
			mobileActions.swipeByElements(myProgressTxt, dateSelector);
		}
		//validar card do weekly sessions - atualizar -> não existe mais weekly sessions
		if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_card_weekly_sessions']")).size() > 0) {
			String myProgress2 = driver.findElementByXPath("//android.widget.TextView[@text='My progress']").getText();
				Assert.assertEquals("My progress", myProgress2);
				//System.out.print("my progress: " + myProgress2 + " ");
			String adherenceGraphTitle = driver.findElementByXPath("//android.view.View[@content-desc='home_card_weekly_sessions']/android.widget.TextView[1]").getText();
				Assert.assertEquals("Session activity", adherenceGraphTitle);
				//System.out.print("graph title: " + adherenceGraphTitle + " ");
			String adherenceGraphSessions = driver.findElementByXPath("//android.view.View[@content-desc='home_card_weekly_sessions']/android.widget.TextView[2]").getText();
				Assert.assertEquals("Nº of session days", adherenceGraphSessions);
				//System.out.print("y axis: " + adherenceGraphSessions + " ");
			String adherenceGraphWeeks = driver.findElementByXPath("//android.view.View[@content-desc='home_card_weekly_sessions']/android.widget.TextView[3]").getText();
				Assert.assertEquals("Week", adherenceGraphWeeks);
				//System.out.print("x axis: " + adherenceGraphWeeks + " ");
			//fazer scroll pra mostrar o sessions performance history card
			MobileElement weekTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Week']");
			MobileElement myProgressTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='My progress']");
			mobileActions.swipeByElements(weekTxt, myProgressTxt);
		} else {
		    System.out.println("WEEKLY SESSIONS CARD NOT FOUND!");
		}
		//verificar sessions performance history card -> atualizar não existe mais sessions performance history
		if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_sessions_performance_history_card']")).size() > 0) {
			String starsAchieved = driver.findElementByXPath("//android.view.View[@content-desc='home_sessions_performance_history_card']/android.widget.TextView[1]").getText();
				Assert.assertEquals("Stars achieved per session", starsAchieved);
				//System.out.print("stars achieved title: " + starsAchieved);
			String starsPercentage = driver.findElementByXPath("//android.view.View[@content-desc='home_sessions_performance_history_card']/android.widget.TextView[2]").getText();
				Assert.assertEquals("%", starsPercentage);
				//System.out.print(" y axis: " + starsPercentage);
			String session = driver.findElementByXPath("//android.view.View[@content-desc='home_sessions_performance_history_card']/android.widget.TextView[3]").getText();
				Assert.assertEquals("Session", session);
				//System.out.print(" x axis: " + session);
			//scroll pra mostrar as badges
			MobileElement sessionTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Session']");
			MobileElement starsAchievedTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Stars achieved per session']");
			mobileActions.swipeByElements(sessionTxt, starsAchievedTxt);
		} else {
			System.out.println("SESSIONS PERFORMANCE HISTORY CARD NOT FOUND!");
			//scroll pra mostrar as badges
			MobileElement sessions = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");
			MobileElement painChart = (MobileElement) driver.findElementByAccessibilityId("home_screen_pain_chart");
			mobileActions.swipeByElements(painChart, sessions);
		}
		//card das badges
		if (driver.findElements(By.xpath("//android.view.View[@content-desc='home_badges_card_badge_0']")).size() > 0) {
			String myBadges = driver.findElementByXPath("//android.widget.TextView[@text='My badges']").getText();
				Assert.assertEquals("My badges", myBadges);
			String seeAllBadges = driver.findElementByXPath("//android.widget.TextView[@text='See all badges']").getText();
				Assert.assertEquals("See all badges", seeAllBadges);
		} else {
			System.out.println("BADGES CARD NOT FOUND!");
		}
		MobileElement setttingsButton = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		setttingsButton.click();
		//definir pin
		MobileElement definePin = (MobileElement) driver.findElementByAccessibilityId("menu_option_define_pin");
		definePin.click();
		MobileElement el5 = (MobileElement) driver.findElementByAccessibilityId("Navigate up");
		el5.click();
	//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='header_menu_button']"))); bug appium
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		MobileElement el6 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el6.click();
		MobileElement el7 = (MobileElement) driver.findElementByAccessibilityId("menu_option_define_pin");
		el7.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		MobileElement el8 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[10]");
		el8.click();
		el8.click();
		el8.click();
		el8.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
		MobileElement el9 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[7]");
		el9.click();
		el9.click();
		el9.click();
		el9.click();
		//validar ecrã de pins diferentes
		String definePinCodeError = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
			Assert.assertEquals("Uh-oh! The PIN codes didn't match. Please try again.", definePinCodeError);
		MobileElement el10 = (MobileElement) driver.findElementByXPath("//android.widget.Button");
		el10.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		MobileElement el11 = (MobileElement) driver.findElementByXPath("//android.view.View[10]");
		el11.click();
		el11.click();
		el11.click();
		el11.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
		MobileElement el12 = (MobileElement) driver.findElementByXPath("//android.view.View[10]");
		el12.click();
		el12.click();
		el12.click();
		el12.click();
		//validar ecrã de ligar biometria
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
		String biometrics = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
			Assert.assertEquals("Want to use biometrics for future logins? You can activate it now, or activate it later in Settings.", biometrics);
		MobileElement el13 = (MobileElement) driver.findElementByXPath("//android.widget.Button");
		el13.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
		String newPin = driver.findElementByXPath("//android.widget.TextView[@text='New PIN code set successfully']").getText();
			Assert.assertEquals("New PIN code set successfully", newPin);
	//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='header_menu_button']"))); bug do appium
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//change pin
		MobileElement el14 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el14.click();
		MobileElement el15 = (MobileElement) driver.findElementByAccessibilityId("menu_option_change_pin");
		el15.click();
		MobileElement el16 = (MobileElement) driver.findElementByXPath("//android.view.View[1]");
		el16.click();
		el16.click();
		el16.click();
		el16.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
		String wrongPin = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
			Assert.assertEquals("Wrong PIN code! \n"
					+ "You have 4 more attempts", wrongPin);
		MobileElement el17 = (MobileElement) driver.findElementByXPath("//android.widget.Button");
		el17.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
		MobileElement el18 = (MobileElement) driver.findElementByXPath("//android.view.View[10]");
		el18.click();
		el18.click();
		el18.click();
		el18.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		MobileElement el19 = (MobileElement) driver.findElementByXPath("//android.view.View[10]");
		el19.click();
		el19.click();
		el19.click();
		el19.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
		MobileElement el20 = (MobileElement) driver.findElementByXPath("//android.view.View[10]");
		el20.click();
		el20.click();
		el20.click();
		el20.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
		String newPin2 = driver.findElementByXPath("//android.widget.TextView[@text='New PIN code set successfully']").getText();
			Assert.assertEquals("New PIN code set successfully", newPin2);
	//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='header_menu_button']"))); bug do appium
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//login with biometrics toggle
		MobileElement el21 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el21.click();
		MobileElement el22 = (MobileElement) driver.findElementByAccessibilityId("menu_option_login_biometrics");
		el22.click();
		el22.click();
		//delete account
		MobileElement delete = (MobileElement) driver.findElementByAccessibilityId("menu_option_delete_account");
		delete.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Delete account']")));
		MobileElement el23 = (MobileElement) driver.findElementByAccessibilityId("Navigate up");
		el23.click();
		MobileElement logout = (MobileElement) driver.findElementByAccessibilityId("menu_option_logout");
		logout.click();
			
	}

	private AndroidDriver<MobileElement> inicializarAppium() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "android");
	    desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
	    desiredCapabilities.setCapability("appium:deviceName", "07111JEC201460");
	    desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/luizaspiegel/Downloads/app-sword-qa1524.apk");
	    desiredCapabilities.setCapability("appium:noReset", "false");
	    desiredCapabilities.setCapability("appium:autoGrantPermissions", "true");
	    
	    AndroidDriver<MobileElement> driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}