package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class TestSwordRegressionGetStarted {

	private final static String CHECK_GET_STARTED_DPT_SCREEN_1 = "dpt_get_started_screen1";
	private final static String CHECK_GET_STARTED_DPT_SCREEN_2 = "dpt_get_started_screen2";
	private final static String CHECK_GET_STARTED_DPT_SCREEN_3 = "dpt_get_started_screen3";
	private final static String CHECK_GET_STARTED_BLOOM_SCREEN_1 = "bloom_get_started_screen1";
	private final static String CHECK_GET_STARTED_BLOOM_SCREEN_2 = "bloom_get_started_screen2";
	private final static String CHECK_GET_STARTED_BLOOM_SCREEN_3 = "bloom_get_started_screen3";
	private final static String CHECK_GET_STARTED_MOVE_SCREEN_1 = "move_get_started_screen1";
	private final static String CHECK_GET_STARTED_MOVE_SCREEN_2 = "move_get_started_screen2";
	private final static String CHECK_GET_STARTED_MOVE_SCREEN_3 = "move_get_started_screen3";

	private AndroidDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsAndroid.getDriver();
	}

	@Test
	public void getStarted() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActions mobileActions = new MobileActions(driver);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		new VisualCheck(driver);

		//login
		utilitiesAndroid.newLogin("luiza.preventive@sword.com", "Test1234!", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate dpt get started screen
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_0_get_started_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Digital Physical Therapy']")));
		driver.findElementByXPath("//android.widget.TextView[@text=\"Your journey\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Tell us about you\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"We'll learn about your pain and gather details that your Physical Therapist (PT) will use to create a personalized plan for you.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Meet your PT\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"You'll meet your PT via video chat or phone call.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Receive your kit\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"We'll ship your kit with everything you need to get started.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Next\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_DPT_SCREEN_1);
		//scroll to show more content
		MobileElement wellShipTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"We'll ship your kit with everything you need to get started.\"]");
		MobileElement welcomeDPT = driver.findElementByXPath("//android.widget.TextView[@text='Welcome to Digital Physical Therapy']");
		mobileActions.swipeByElements(wellShipTxt, welcomeDPT);
		//validate the rest of the screen
		driver.findElementByXPath("//android.widget.TextView[@text=\"Start feeling better!\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_DPT_SCREEN_2);
		//tap next button
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[3]/android.widget.Button", driver);
		//validate get started second screen
		driver.findElementByXPath("//android.widget.TextView[@text=\"Now tell us about you\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"On the next screens, we'd love to know more about your pain history and health goals.\n" +
				"\n" +
				"We will ask some questions to gather information about your pain and help your Physical Therapist (PT) create a personalized plan just for you!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Let's go!\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_DPT_SCREEN_3);
		//tap let's go button
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[3]/android.widget.Button", driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//go back to hub screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.Button", driver);
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
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
		//validate bloom get started screen
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_1_get_started_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Welcome to Bloom\"]")));
		driver.findElementByXPath("//android.widget.TextView[@text=\"Your journey\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Tell us more about you\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Your Pelvic Health Specialist will gather details to create a personalized plan for you.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Match with a Pelvic Health Specialist\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"In your virtual meeting, you will get insights from your specialist and learn about your tailored program.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Receive your Bloom Kit at home\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"We'll ship your kit, including your pod, so you can get started.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Next\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_BLOOM_SCREEN_1);
		//scroll to show more content
		MobileElement wellShipBloomTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"We'll ship your kit, including your pod, so you can get started.\"]");
		MobileElement welcomeBloom = driver.findElementByXPath("//android.widget.TextView[@text=\"Welcome to Bloom\"]");
		mobileActions.swipeByElements(wellShipBloomTxt, welcomeBloom);
		//validate the rest of the screen
		driver.findElementByXPath("//android.widget.TextView[@text=\"Complete your recovery doing tailored exercise sessions\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Find relief from the comfort of home with a program tailored just for you.\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_BLOOM_SCREEN_2);
		//tap next button
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[3]/android.widget.Button", driver);
		//validate get started second screen
		driver.findElementByXPath("//android.widget.TextView[@text=\"Now tell us about you\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"On the next screens, we'd love to know more about your health goals.\n" +
				"\n" +
				"We will ask some questions to gather information about your symptoms and help your Pelvic Health Specialist (PHS) create a personalized plan just for you! It will take only around 10 minutes.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Let's go!\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_BLOOM_SCREEN_3);
		//tap let's go button
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[3]/android.widget.Button", driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//go back to hub screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.Button", driver);
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//scroll to show more cards
		programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
		homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
		mobileActions.swipeByElements(programsCard0, homeOnCallCard);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		MobileElement programsCard2 = driver.findElementByAccessibilityId("on_call_programs_card_2");
		mobileActions.swipeByElements(programsCard2, programsCard0);
		//validate move get started screen
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_2_get_started_button", driver);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Welcome to Move\"]")));
		driver.findElementByXPath("//android.widget.TextView[@text=\"Your journey\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Match with your personal trainer\"]");
//o appium crasha por causa dessa string		driver.findElementByXPath("//android.widget.TextView[@text=\"We will gather information about you to match you with the best trainer for your preferences, health, needs, goals and lifestyle.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Connect a wearable device\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Connect a wearable device like an Apple Watch or Fitbit if you have one. If you don't, we'll send one directly to you.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Meet and get to know your personal trainer\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Next\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_MOVE_SCREEN_1);
		//scroll to show more content
		MobileElement meetAndGetTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"Meet and get to know your personal trainer\"]");
		MobileElement welcomeMove = driver.findElementByXPath("//android.widget.TextView[@text=\"Welcome to Move\"]");
		mobileActions.swipeByElements(meetAndGetTxt, welcomeMove);
		//validate the rest of the screen
		driver.findElementByXPath("//android.widget.TextView[@text=\"Meet with your personal trainer so they can get to know you and what will make the best program for you. Only got five minutes in the morning to spare? No problem, we've got you covered.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Weekly programming\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Every week, you receive your personalized plan with exercises to get you moving. You'll set activity goals, reminders and collect rewards all through your mobile app.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Track progress\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Use the app to track your progress, set new goals and glean insights from your performance.\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_MOVE_SCREEN_2);
		//tap next button
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[3]/android.widget.Button", driver);
		//validate get started second screen
		driver.findElementByXPath("//android.widget.TextView[@text=\"Now tell us about you\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"On the next screens, we'd love to know more about your health goals.\n" +
				"\n" +
				"We will ask some questions to gather information about you and help your Personal Trainer (PT) create a personalized plan just for you! It will take only around 10 minutes.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Let's go!\"]");
		VisualCheck.doVisualCheck(CHECK_GET_STARTED_MOVE_SCREEN_3);
		//tap let's go button - não abre a webview
		//utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[3]/android.widget.Button", driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//go back to hub screen
		//utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.Button", driver);
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		ConfigurationsAndroid.killDriver();
	}
}