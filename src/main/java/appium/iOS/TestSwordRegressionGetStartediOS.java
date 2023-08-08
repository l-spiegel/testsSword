package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class TestSwordRegressionGetStartediOS {

	private final static String CHECK_GET_STARTED_DPT_SCREEN_1 = "dpt_get_started_screen1";
	private final static String CHECK_GET_STARTED_DPT_SCREEN_2 = "dpt_get_started_screen2";
	private final static String CHECK_GET_STARTED_DPT_SCREEN_3 = "dpt_get_started_screen3";
	private final static String CHECK_GET_STARTED_BLOOM_SCREEN_1 = "bloom_get_started_screen1";
	private final static String CHECK_GET_STARTED_BLOOM_SCREEN_2 = "bloom_get_started_screen2";
	private final static String CHECK_GET_STARTED_BLOOM_SCREEN_3 = "bloom_get_started_screen3";
	private final static String CHECK_GET_STARTED_MOVE_SCREEN_1 = "move_get_started_screen1";
	private final static String CHECK_GET_STARTED_MOVE_SCREEN_2 = "move_get_started_screen2";
	private final static String CHECK_GET_STARTED_MOVE_SCREEN_3 = "move_get_started_screen3";

	private IOSDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
	}

	@Test
	public void getStarted() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		new VisualCheck(driver);

		//login
		UtilitiesiOS.newLogin("luiza.preventive@sword.com", "Test1234!", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate dpt get started screen
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_0_get_started_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Welcome to Digital Physical Therapy']")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Your journey\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Tell us about you\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"We'll learn about your pain and gather details that your Physical Therapist (PT) will use to create a personalized plan for you.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Meet your PT\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Next\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_DPT_SCREEN_1);
		//scroll to show more content
		MobileElement meetYourPtTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Meet your PT\"]");
		MobileElement welcomeDPT = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Welcome to Digital Physical Therapy\"]");
		mobileActions.swipeByElements(meetYourPtTxt, welcomeDPT);
		//validate the rest of the screen
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"You'll meet your PT via video chat or phone call.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Receive your kit\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"We'll ship your kit with everything you need to get started.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Start feeling better!\"]");
//		VisualCheck.doVisualCheck(CHECK_GET_STARTED_DPT_SCREEN_2);
		//tap next button
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Next\"]", driver);
		//validate get started second screen
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Now tell us about you\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"On the next screens, we'd love to know more about your pain history and health goals.\n" +
				"\n" +
				"We will ask some questions to gather information about your pain and help your Physical Therapist (PT) create a personalized plan just for you!\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Let's go!\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_DPT_SCREEN_3);
		//tap let's go button
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Let's go!\"]", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Cookies\"]")));
		//go back to hub screen
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"ic arrow left\"]", driver);
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//scroll to show more cards
		MobileElement programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
		MobileElement homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
		mobileActions.swipeByElements(programsCard0, homeOnCallCard);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		MobileElement programsCard2 = driver.findElementByXPath("//XCUIElementTypeOther[@name=\"on_call_programs_card_2\"]/XCUIElementTypeImage");
		mobileActions.swipeByElements(programsCard2, programsCard0);
		//validate bloom get started screen
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_1_get_started_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Welcome to Bloom\"]")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Your journey\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Tell us more about you\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Your Pelvic Health Specialist will gather details to create a personalized plan for you.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Match with a Pelvic Health Specialist\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"In your virtual meeting, you will get insights from your specialist and learn about your tailored program.\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Next\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_BLOOM_SCREEN_1);
		//scroll to show more content
		MobileElement inYourVirtualTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"In your virtual meeting, you will get insights from your specialist and learn about your tailored program.\"]");
		MobileElement welcomeBloom = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Welcome to Bloom\"]");
		mobileActions.swipeByElements(inYourVirtualTxt, welcomeBloom);
		//validate the rest of the screen
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Receive your Bloom Kit at home\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"We'll ship your kit, including your pod, so you can get started.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Complete your recovery doing tailored exercise sessions\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Find relief from the comfort of home with a program tailored just for you.\"]");
//		VisualCheck.doVisualCheck(CHECK_GET_STARTED_BLOOM_SCREEN_2);
		//tap next button
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Next\"]", driver);
		//validate get started second screen
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Now tell us about you\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"On the next screens, we'd love to know more about your health goals.\n" +
				"\n" +
				"We will ask some questions to gather information about your symptoms and help your Pelvic Health Specialist (PHS) create a personalized plan just for you! It will take only around 10 minutes.\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Let's go!\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_BLOOM_SCREEN_3);
		//tap let's go button
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Let's go!\"]", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Cookies\"]")));
		//go back to hub screen
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"ic arrow left\"]", driver);
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate move get started screen
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_2_get_started_button", driver);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Welcome to Move\"]")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Your journey\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Match with your personal trainer\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"We will gather information about you to match you with the best trainer for your preferences, health, needs, goals and lifestyle.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Connect a wearable device\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Connect a wearable device like an Apple Watch or Fitbit if you have one. If you don't, we'll send one directly to you.\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Next\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_MOVE_SCREEN_1);
		//scroll to show more content
		MobileElement connectAWearableTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Connect a wearable device like an Apple Watch or Fitbit if you have one. If you don't, we'll send one directly to you.\"]");
		MobileElement welcomeMove = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Welcome to Move\"]");
		mobileActions.swipeByElements(connectAWearableTxt, welcomeMove);
		//validate the rest of the screen
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Meet and get to know your personal trainer\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Meet with your personal trainer so they can get to know you and what will make the best program for you. Only got five minutes in the morning to spare? No problem, we've got you covered.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Weekly programming\"]");
		//scroll to show more content
		MobileElement weeklyProgrammingTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Weekly programming\"]");
		mobileActions.swipeByElements(weeklyProgrammingTxt, connectAWearableTxt);
		//validate the rest of the screen
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Every week, you receive your personalized plan with exercises to get you moving. You'll set activity goals, reminders and collect rewards all through your mobile app.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Track progress\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Use the app to track your progress, set new goals and glean insights from your performance.\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_MOVE_SCREEN_2);
		//tap next button
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Next\"]", driver);
		//validate get started second screen
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Now tell us about you\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"On the next screens, we'd love to know more about your health goals.\n" +
				"\n" +
				"We will ask some questions to gather information about you and help your Personal Trainer (PT) create a personalized plan just for you! It will take only around 10 minutes.\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Let's go!\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_MOVE_SCREEN_3);
		//tap let's go button - não abre a webview
		//utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Let's go!\"]", driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//go back to hub screen
		//utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"ic arrow left\"]", driver);
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		ConfigurationsiOS.killDriver();
	}
}