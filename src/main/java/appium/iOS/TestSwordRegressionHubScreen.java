package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class TestSwordRegressionHubScreen {

	private final static String CHECK_HUB_WITHOUT_PROGRAMS_SCREEN = "hub_without_programs";
	private final static String CHECK_HUB_ELIGIBLE_TOP_SCREEN = "hub_eligible_top";
	private final static String CHECK_HUB_ELIGIBLE_BOTTOM_SCREEN = "hub_eligible_bottom";
	private final static String CHECK_HUB_ENROLLED_TOP_SCREEN = "hub_enrolled_top";
	private final static String CHECK_HUB_ENROLLED_BLOOM_POPUP = "hub_enrolled_bloom_popup";
	private final static String CHECK_HUB_ENROLLED_MOVE_POPUP = "hub_enrolled_move_popup";
	private final static String CHECK_HUB_NOT_ELIGIBLE_TOP_SCREEN = "hub_not_eligible_top";
	private final static String CHECK_HUB_INTERESTED_POPUP = "hub_interested_popup";
	private final static String CHECK_HUB_BLOOM_UNDERAGE_BOTTOMSHEET = "hub_bloom_underage_bottom_sheet";
	private final static String CHECK_HUB_MOVE_UNDERAGE_BOTTOMSHEET = "hub_move_underage_bottom_sheet";

	private IOSDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
	}

	@Test
	public void hubScreenAllEligible() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		new VisualCheck(driver);

		String eligible = "Eligible";
		String learnMore = "Learn more";
		String getStarted = "Get started";

		//login
		UtilitiesiOS.newLogin("luiza.preventive@sword.com", "Test1234!", driver);
		//validar hub screen
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"On-Call\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Get on-demand support from a Clinical Pain Specialist via chat.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@value=\"Chat with a Specialist\"]");
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name=\"Explore our programs\"]")).size() < 1) {
			VisualCheck.doVisualCheck(CHECK_HUB_WITHOUT_PROGRAMS_SCREEN);
			System.out.println("Hub screen without program cards");
			ConfigurationsiOS.killDriver();
		} else {
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Explore our programs\"]");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Learn more about our programs below to find the best fit for you.\"]");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Digital Physical Therapy\"]");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Physical therapy you can do anytime, anywhere.\"]");
			String dptEligibility = driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"Eligible\"])[1]").getText();
			String dptButton1 = driver.findElementByAccessibilityId("on_call_programs_card_0_learn_more_button").getText();
			String dptButton2 = driver.findElementByAccessibilityId("on_call_programs_card_0_get_started_button").getText();
			Assert.assertEquals(eligible, dptEligibility);
			Assert.assertEquals(learnMore, dptButton1);
			Assert.assertEquals(getStarted, dptButton2);
			VisualCheck.doVisualCheck(CHECK_HUB_ELIGIBLE_TOP_SCREEN);
			//open dpt learn more
			utilitiesiOS.clickByAccessibilityId("on_call_programs_card_0_learn_more_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Who are recovering from a recent surgery or injury\"]")));
			//back to hub screen
			utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//open dpt get started
			utilitiesiOS.clickByAccessibilityId("on_call_programs_card_0_get_started_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Welcome to Digital Physical Therapy\"]")));
			//back to hub screen
			utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
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
			VisualCheck.doVisualCheck(CHECK_HUB_ELIGIBLE_BOTTOM_SCREEN);
			//validate bloom program card
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Bloom\"]");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Innovative, digital pelvic health therapy from the comfort of home.\"]");
			String bloomEligibility = driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"Eligible\"])[2]").getText();
			String bloomButton1 = driver.findElementByAccessibilityId("on_call_programs_card_1_learn_more_button").getText();
			String bloomButton2 = driver.findElementByAccessibilityId("on_call_programs_card_1_get_started_button").getText();
			Assert.assertEquals(eligible, bloomEligibility);
			Assert.assertEquals(learnMore, bloomButton1);
			Assert.assertEquals(getStarted, bloomButton2);
			//validate move program card
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Move\"]");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Get moving, stay moving, and keep pain away with your own personal training program.\"]");
			String moveEligibility = driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"Eligible\"])[3]").getText();
			String moveButton1 = driver.findElementByAccessibilityId("on_call_programs_card_2_learn_more_button").getText();
			String moveButton2 = driver.findElementByAccessibilityId("on_call_programs_card_2_get_started_button").getText();
			Assert.assertEquals(eligible, moveEligibility);
			Assert.assertEquals(learnMore, moveButton1);
			Assert.assertEquals(getStarted, moveButton2);
			//open bloom learn more
			utilitiesiOS.clickByAccessibilityId("on_call_programs_card_1_learn_more_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Designed for women:\"]")));
			//back to hub screen
			utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//open bloom get started
			utilitiesiOS.clickByAccessibilityId("on_call_programs_card_1_get_started_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Welcome to Bloom\"]")));
			//back to hub screen
			utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//open move learn more
			utilitiesiOS.clickByAccessibilityId("on_call_programs_card_2_learn_more_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Who want to learn and try exercise\"]")));
			//back to hub screen
			utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//open move get started
			utilitiesiOS.clickByAccessibilityId("on_call_programs_card_2_get_started_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Welcome to Move\"]")));
			//back to hub screen
			utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);

			ConfigurationsiOS.killDriver();
		}
	}

	@Test
	public void hubScreenEnrolled() throws Exception {
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		new VisualCheck(driver);

		//login
		System.out.println("Ligou o proxy??");
		utilitiesiOS.newLogin("luiza.preventive@sword.com", "Test1234!", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//android.widget.TextView[@text='Active']");
		driver.findElementByXPath("//android.widget.TextView[@text='Currently enrolled']");
		VisualCheck.doVisualCheck(CHECK_HUB_ENROLLED_TOP_SCREEN);
		//scroll to show more cards
		MobileElement programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
		MobileElement homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
		mobileActions.swipeByElements(programsCard0, homeOnCallCard);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		MobileElement programsCard2 = driver.findElementByAccessibilityId("on_call_programs_card_2");
		mobileActions.swipeByElements(programsCard2, programsCard0);
		//tap bloom get started button
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_1_get_started_button", driver);
		//validate bloom get started enrolled popup
		driver.findElementByXPath("//android.widget.TextView[@text=\"Since you're already enrolled in a program, you'll need to complete it first.\n" +
				"\n" +
				"Then, you can enroll in a new program.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text='Close']");
		VisualCheck.doVisualCheck(CHECK_HUB_ENROLLED_BLOOM_POPUP);
		//close popup
		utilitiesiOS.clickByXPath("//android.widget.Button", driver);
		//tap move get started button
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_2_get_started_button", driver);
		//validate move get started enrolled popup
		driver.findElementByXPath("//android.widget.TextView[@text=\"Since you're already enrolled in a program, you'll need to complete it first.\n" +
				"\n" +
				"Then, you can enroll in a new program.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text='Close']");
		VisualCheck.doVisualCheck(CHECK_HUB_ENROLLED_MOVE_POPUP);
		//close popup
		mobileActions.tapByCoordinates(382, 1945);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		ConfigurationsiOS.killDriver();
	}

	@Test
	public void hubScreenUnderageDptNotEligible() throws Exception {
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		new VisualCheck(driver);

		//login
		System.out.println("Ligou o proxy??");
		utilitiesiOS.newLogin("underage@sword.com", "Test1234!", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate not eligible card
		driver.findElementByXPath("//android.widget.TextView[@text=\"Not eligible\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"I’m interested\"]");
		VisualCheck.doVisualCheck(CHECK_HUB_NOT_ELIGIBLE_TOP_SCREEN);
		//validate not eligible popup
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_0_interested_button", driver);
		driver.findElementByXPath("//android.widget.TextView[@text=\"Thank you for your interest. We'll notify your benefits manager.\"]");
		VisualCheck.doVisualCheck(CHECK_HUB_INTERESTED_POPUP);
		utilitiesiOS.clickByXPath("//android.widget.Button", driver);
		//scroll to show more cards
		MobileElement programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
		MobileElement homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
		mobileActions.swipeByElements(programsCard0, homeOnCallCard);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		MobileElement programsCard2 = driver.findElementByAccessibilityId("on_call_programs_card_2");
		mobileActions.swipeByElements(programsCard2, programsCard0);
		//tap bloom get started button
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_1_get_started_button", driver);
		//validate bloom underage bottom sheet
		driver.findElementByXPath("//android.widget.TextView[@text=\"Thanks for your interest in Bloom!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Unfortunately, we require Bloom members to be at least 18 years old. We hope to see you enroll in the future!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Got it\"]");
		VisualCheck.doVisualCheck(CHECK_HUB_BLOOM_UNDERAGE_BOTTOMSHEET);
		utilitiesiOS.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View/android.widget.Button", driver);
		//tap move get started button
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_2_get_started_button", driver);
		//validate move underage bottom sheet
		driver.findElementByXPath("//android.widget.TextView[@text=\"Thanks for your interest in Move!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Unfortunately, we require Move members to be at least 18 years old. We hope to see you enroll in the future!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Got it\"]");
		VisualCheck.doVisualCheck(CHECK_HUB_MOVE_UNDERAGE_BOTTOMSHEET);
		mobileActions.tapByCoordinates(785, 1181);

		ConfigurationsiOS.killDriver();
	}
}