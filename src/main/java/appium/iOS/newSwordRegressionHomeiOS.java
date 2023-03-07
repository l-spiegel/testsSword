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

import appium.MobileActions;

public class newSwordRegressionHomeiOS {
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
	public void loginPreventive() throws MalformedURLException { //não existe mais home em preventive
		IOSDriver<MobileElement> driver = inicializarAppium();
		
		driver.findElementByAccessibilityId("Allow").click();
	//	MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("loginEmailTextfield");
	//	el1.clear();
	//	el1.sendKeys("frodobaggins@sword.com");
		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el2.click();
		el2.sendKeys("28Abril!");
		MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el3.click();
		WebDriverWait wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el4 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Create PIN']");
		el4.click();
		MobileElement el5 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el5.click();
		el5.click();
		el5.click();
		el5.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		MobileElement el7 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el7.click();
		el7.click();
		el7.click();
		el7.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")));
		MobileElement el8 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Activate now']");
		el8.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_preventive_card")));
		String startTreatment = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Start with treatment']").getText();
			Assert.assertEquals("Start with treatment", startTreatment);
		MobileElement el10 = (MobileElement) driver.findElementByAccessibilityId("home_preventive_card_button");
		el10.click();
		String el11 = driver.findElementByAccessibilityId("How it works?").getText();
			Assert.assertEquals("How it works?", el11);
		String el12 = driver.findElementByAccessibilityId("Watch the video to see how simple it is to get your life back!").getText();
			Assert.assertEquals("Watch the video to see how simple it is to get your life back!", el12);
		String el13 = driver.findElementByAccessibilityId("Next steps").getText();
			Assert.assertEquals("Next steps", el13);
		String el14 = driver.findElementByAccessibilityId("Step 1").getText();
			Assert.assertEquals("Step 1", el14);
		String el15 = driver.findElementByAccessibilityId("Complete the onboarding process").getText();
			Assert.assertEquals("Complete the onboarding process", el15);
		String el16 = driver.findElementByAccessibilityId("Step 2").getText();
			Assert.assertEquals("Step 2", el16);
		String el17 = driver.findElementByAccessibilityId("Get ready for a video call and wait for the kit to arrive").getText();
			Assert.assertEquals("Get ready for a video call and wait for the kit to arrive", el17);
		String el18 = driver.findElementByAccessibilityId("Step 3").getText();
			Assert.assertEquals("Step 3", el18);
		String el19 = driver.findElementByAccessibilityId("Start your sessions and get out of the pain").getText();
			Assert.assertEquals("Start your sessions and get out of the pain", el19);
		String letsGo = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("Let's go!", letsGo);
		MobileElement el20 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton");
		el20.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Welcome to Sword']")));
		MobileElement el22 = (MobileElement) driver.findElementByAccessibilityId("1PxIcChevronLeft");
		el22.click();
		MobileElement el23 = (MobileElement) driver.findElementByAccessibilityId("1PxIcChevronLeft");
		el23.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_preventive_card")));
		MobileElement el24 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el24.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='PIN code and Biometrics']")));
		MobileElement el25 = (MobileElement) driver.findElementByAccessibilityId("menu_option_login_biometrics");
		el25.click();
		MobileElement el26 = (MobileElement) driver.findElementByAccessibilityId("menu_option_delete_account");
		el26.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Delete account']")));
		MobileElement el28 = (MobileElement) driver.findElementByAccessibilityId("1PxIcChevronLeft");
		el28.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='PIN code and Biometrics']")));
		MobileElement el29 = (MobileElement) driver.findElementByAccessibilityId("menu_option_change_pin");
		el29.click();
		MobileElement el30 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='1']");
		el30.click();
		el30.click();
		el30.click();
		el30.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")));
		String wrongPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Wrong PIN code!  You have 4 more attempts']").getText();
			Assert.assertEquals("Wrong PIN code! \n"
					+ "You have 4 more attempts", wrongPin);
		MobileElement el31 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']");
		el31.click();
		MobileElement el33 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
		el33.click();
		el33.click();
		el33.click();
		el33.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el34 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el34.click();
		el34.click();
		el34.click();
		el34.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		MobileElement el35 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
		el35.click();
		el35.click();
		el35.click();
		el35.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")));
		String newPin = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='New PIN code set successfully']").getText();
			Assert.assertEquals("New PIN code set successfully", newPin);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_preventive_card")));
		MobileElement el36 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el36.click();
		MobileElement el37 = (MobileElement) driver.findElementByAccessibilityId("menu_option_logout");
		el37.click();
		
		driver.quit();
	}


	@Test
	public void loginVirtualPt() throws MalformedURLException {
		IOSDriver<MobileElement> driver = inicializarAppium();
		
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		driver.findElementByAccessibilityId("Allow").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
	//	MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("loginEmailTextfield");
	//	el1.clear();
	//	el1.sendKeys("luiza_spiegel@hotmail.com");
		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el2.click();
		el2.sendKeys("Cabixuda12");
		MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el3.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el4 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not now']");
		el4.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		if (driver.findElements(By.id("home_card_weekly_goal")).size() > 0) {
	//		String weeklyGoalLabel = driver.findElementByAccessibilityId("home_card_weekly_goal_label").getText();
	//			System.out.println("Weekly goal label: " + weeklyGoalLabel);
		} else {
			System.out.print("API com soluços");
			driver.quit();
		}
		//validar pt card
		if (driver.findElements(By.id("home_card_pt")).size() > 0) {
			String ptName = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_card_pt']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]").getText();
			System.out.println("PT card found. PT name: " + ptName);
			//swipe para mostrar até card das sessões por semana	
			MobileElement remindersTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']"); //bug com o id do botão dos reminders
			MobileElement ptCard = (MobileElement) driver.findElementByAccessibilityId("home_card_pt");
			mobileActions.swipeByElements(remindersTxt, ptCard);
		} else {
			System.out.println("PT CARD NOT FOUND!");
			MobileElement remindersButton = (MobileElement) driver.findElementByAccessibilityId("home_card_reminders_button");
			MobileElement weeklyGoalCard = (MobileElement) driver.findElementByAccessibilityId("home_card_weekly_goal");
			mobileActions.swipeByElements(remindersButton, weeklyGoalCard);
		}
		
		//verificar card session details
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_0")));
		if (driver.findElements(By.id("home_card_session_details_0")).size() > 0) {
			String nextSession = driver.findElementByAccessibilityId("home_card_session_details_0_date_label").getText();
			if (nextSession.contentEquals("Next Session")) {
				System.out.println("SESSION DETAILS WITH NEXT SESSION OVERVIEW AVAILABLE");
			} else {
				System.out.println("SESSION DETAILS WITH NEXT SESSION OVERVIEW NOT AVAILABLE");
			}
		} else {
			System.out.println("SESSION DETAILS NOT FOUND!");
		}
		//swipe pra mostrar o card do weekly sessions - trocar o com xpath pra id do card do session details
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name='My progress']")).size() > 0) {
			MobileElement myProgressTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Progress']");
			MobileElement dateSelector = (MobileElement) driver.findElementByAccessibilityId("home_card_session_details_0");
			mobileActions.swipeByElements(myProgressTxt, dateSelector);
		}
		//validar card do weekly sessions - não existe mais - mudar pro das estrelas
		if (driver.findElements(By.id("home_card_weekly_sessions")).size() > 0) {
			String myProgress2 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='My progress']").getText();
				Assert.assertEquals("My progress", myProgress2);
				//System.out.print("my progress: " + myProgress2 + " ");
			String adherenceGraphTitle = driver.findElementByAccessibilityId("Session activity").getText();
				Assert.assertEquals("Session activity", adherenceGraphTitle);
				//System.out.print("graph title: " + adherenceGraphTitle + " ");
			String adherenceGraphSessions = driver.findElementByAccessibilityId("Nº of session days").getText();
				Assert.assertEquals("Nº of session days", adherenceGraphSessions);
				//System.out.print("y axis: " + adherenceGraphSessions + " ");
			String adherenceGraphWeeks = driver.findElementByAccessibilityId("Week").getText();
				Assert.assertEquals("Week", adherenceGraphWeeks);
				//System.out.print("x axis: " + adherenceGraphWeeks + " ");
			//fazer scroll pra mostrar o sessions performance history card - não existe mais - mudar pro pain chart
			MobileElement weekTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Week']");
			MobileElement myProgressTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Progress']");
			mobileActions.swipeByElements(weekTxt, myProgressTxt);
		} else {
		    System.out.println("WEEKLY SESSIONS CARD NOT FOUND!");
		}
		//verificar sessions performance history card
		if (driver.findElements(By.id("home_sessions_performance_history_card")).size() > 0) {
			String starsAchieved = driver.findElementByAccessibilityId("Stars achieved per session").getText();
				Assert.assertEquals("Stars achieved per session", starsAchieved);
				//System.out.print("stars achieved title: " + starsAchieved);
			String starsPercentage = driver.findElementByAccessibilityId("%").getText();
				Assert.assertEquals("%", starsPercentage);
				//System.out.print(" y axis: " + starsPercentage);
			String session = driver.findElementByAccessibilityId("Session").getText();
				Assert.assertEquals("Session", session);
				//System.out.print(" x axis: " + session);
			//scroll pra mostrar as badges
			MobileElement sessionTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Session']");
			MobileElement starsAchievedTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Stars achieved per session']");
			mobileActions.swipeByElements(sessionTxt, starsAchievedTxt);
		} else {
			System.out.print("SESSIONS PERFORMANCE HISTORY CARD NOT FOUND!");
			//scroll pra mostrar as badges
			MobileElement starsAchievedCard = (MobileElement) driver.findElementByAccessibilityId("home_card_achieved_stars");
			MobileElement sessionsTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']");
			mobileActions.swipeByElements(starsAchievedCard, sessionsTxt);
		}
		//card das badges
		if (driver.findElements(By.id("home_badges_card_badge_0")).size() > 0) {
			String myBadges = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='My badges']").getText();
				Assert.assertEquals("My badges", myBadges);
			String seeAllBadges = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='See all badges']").getText();
				Assert.assertEquals("See all badges", seeAllBadges);
		} else {
			System.out.print("BADGES CARD NOT FOUND!");
		}
		MobileElement setttingsButton = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		setttingsButton.click();
		//define pin
		MobileElement definePin = (MobileElement) driver.findElementByAccessibilityId("menu_option_define_pin");
		definePin.click();
		MobileElement el5 = (MobileElement) driver.findElementByAccessibilityId("1PxIcChevronLeft");
		el5.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Settings']")));
		MobileElement el7 = (MobileElement) driver.findElementByAccessibilityId("menu_option_define_pin");
		el7.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el8 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el8.click();
		el8.click();
		el8.click();
		el8.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		MobileElement el9 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='7']");
		el9.click();
		el9.click();
		el9.click();
		el9.click();
		//validar ecrã de pins diferentes
		String definePinCodeError = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("Uh-oh! The PIN codes didn't match. Please try again.", definePinCodeError);
		MobileElement el10 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']");
		el10.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el11 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el11.click();
		el11.click();
		el11.click();
		el11.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		MobileElement el12 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el12.click();
		el12.click();
		el12.click();
		el12.click();
		//validar ecrã de ligar biometria
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
		String biometrics = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("Want to use Face ID for future logins? You can activate it now, or activate it later in Settings.", biometrics);
		MobileElement el13 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Activate now']");
		el13.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
		String newPin = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("New PIN code set successfully", newPin);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header_menu_button")));
		//change pin
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		MobileElement el14 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el14.click();
		MobileElement el15 = (MobileElement) driver.findElementByAccessibilityId("menu_option_change_pin");
		el15.click();
		MobileElement el16 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='1']");
		el16.click();
		el16.click();
		el16.click();
		el16.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
		String wrongPin = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("Wrong PIN code! \n"
					+ "You have 4 more attempts", wrongPin);
		MobileElement el17 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']");
		el17.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
		MobileElement el18 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el18.click();
		el18.click();
		el18.click();
		el18.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el19 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el19.click();
		el19.click();
		el19.click();
		el19.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		MobileElement el20 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el20.click();
		el20.click();
		el20.click();
		el20.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
		String newPin2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("New PIN code set successfully", newPin2);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header_menu_button")));
		//login with biometrics toggle
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		MobileElement el21 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el21.click();
		MobileElement el22 = (MobileElement) driver.findElementByAccessibilityId("menu_option_login_biometrics");
		el22.click();
		//delete account
		MobileElement delete = (MobileElement) driver.findElementByAccessibilityId("menu_option_delete_account");
		delete.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Delete account']")));
		MobileElement el23 = (MobileElement) driver.findElementByAccessibilityId("1PxIcChevronLeft");
		el23.click();
		MobileElement logout = (MobileElement) driver.findElementByAccessibilityId("menu_option_logout");
		logout.click();
		driver.quit();
			
	}
}