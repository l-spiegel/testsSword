package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSwordRegressionHub {

	private final static String VALIDATION_PATH = ConfigurationsAndroid.VALIDATION_PATH;
	private final static String BASELINE = "COMP_";
	private final static String CHECK_HUB_WITHOUT_PROGRAMS_SCREEN = "hub_without_programs";
	private final static String CHECK_HUB_ELIGIBLE_TOP_SCREEN = "hub_eligible_top";
	private final static String CHECK_HUB_ELIGIBLE_BOTTOM_SCREEN = "hub_eligible_bottom";
	private final static String CHECK_HUB_ENROLLED_TOP_SCREEN = "hub_enrolled_top";
	private final static String CHECK_HUB_ENROLLED_BLOOM_POPUP = "hub_enrolled_bloom_popup";
	private final static String CHECK_HUB_ENROLLED_MOVE_POPUP = "hub_enrolled_move_popup";
	private final static String CHECK_HUB_NOT_ELIGIBLE_TOP_SCREEN = "hub_not_eligible_top";
	private final static String CHECK_HUB_INTERESTED_POPUP = "hub_interested_popup";
	private final static String CHECK_DPT_LEARN_MORE_1_SCREEN = "dpt_learn_more_screen1";
	private final static String CHECK_DPT_LEARN_MORE_2_SCREEN = "dpt_learn_more_screen2";
	private final static String CHECK_BLOOM_LEARN_MORE_1_SCREEN = "bloom_learn_more_screen1";
	private final static String CHECK_BLOOM_LEARN_MORE_2_SCREEN = "bloom_learn_more_screen2";
	private final static String CHECK_MOVE_LEARN_MORE_1_SCREEN = "move_learn_more_screen1";
	private final static String CHECK_MOVE_LEARN_MORE_2_SCREEN = "move_learn_more_screen2";
	private final static String CHECK_LEARN_MORE_DPT_NOT_ELIGIBLE_SCREEN = "dpt_not_eligible";
	private final static String CHECK_LEARN_MORE_BLOOM_NOT_ELIGIBLE_SCREEN = "bloom_not_eligible";
	private final static String CHECK_LEARN_MORE_MOVE_NOT_ELIGIBLE_SCREEN = "move_not_eligible";
	private final static String CHECK_LEARN_MORE_BLOOM_UNDERAGE_BOTTOMSHEET = "bloom_learn_more_underage_bottom_sheet";
	private final static String CHECK_LEARN_MORE_MOVE_UNDERAGE_BOTTOMSHEET = "move_learn_more_underage_bottom_sheet";
	private final static String CHECK_HUB_BLOOM_UNDERAGE_BOTTOMSHEET = "hub_bloom_underage_bottom_sheet";
	private final static String CHECK_HUB_MOVE_UNDERAGE_BOTTOMSHEET = "hub_move_underage_bottom_sheet";
	private final static String CHECK_LEARN_MORE_BLOOM_ENROLLED_SCREEN = "bloom_learn_more_enrolled_screen";
	private final static String CHECK_LEARN_MORE_BLOOM_ENROLLED_BOTTOMSHEET = "bloom_learn_more_enrolled_bottom_sheet";
	private final static String CHECK_LEARN_MORE_MOVE_ENROLLED_SCREEN = "move_learn_more_enrolled_screen";
	private final static String CHECK_LEARN_MORE_MOVE_ENROLLED_BOTTOMSHEET = "move_learn_more_enrolled_bottom_sheet";

	private AndroidDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsAndroid.getDriver();
	}

	@Test
	public void hubScreenAllEligible() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActions mobileActions = new MobileActions(driver);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		new VisualCheck(driver);

		String eligible = "Eligible";
		String learnMore = "Learn more";
		String getStarted = "Get started";

		//login
		utilitiesAndroid.newLogin("luiza.preventive@sword.com", "Test1234!", driver);
		//validar hub screen
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//android.widget.TextView[@text='On-Call']");
		driver.findElementByXPath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']");
		driver.findElementByXPath("//android.widget.TextView[@text='Chat with a Specialist']");
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Explore our programs']")).size() < 1) {
			VisualCheck.doVisualCheck(CHECK_HUB_WITHOUT_PROGRAMS_SCREEN);
			System.out.println("Hub screen without program cards");
			ConfigurationsAndroid.killDriver();
		} else {
			driver.findElementByXPath("//android.widget.TextView[@text='Explore our programs']");
			driver.findElementByXPath("//android.widget.TextView[@text='Learn more about our programs below to find the best fit for you.']");
			driver.findElementByXPath("//android.widget.TextView[@text='Digital Physical Therapy']");
			driver.findElementByXPath("//android.widget.TextView[@text='Physical therapy you can do anytime, anywhere.']");
			String dptEligibility = driver.findElementByXPath("//android.view.View[@content-desc=\"on_call_programs_card_0\"]/android.widget.TextView[1]").getText();
			String dptButton1 = driver.findElementByXPath("//android.view.View[@content-desc=\"on_call_programs_card_0\"]/android.view.View[1]/android.widget.TextView").getText();
			String dptButton2 = driver.findElementByXPath("//android.view.View[@content-desc=\"on_call_programs_card_0\"]/android.view.View[2]/android.widget.TextView").getText();
			Assert.assertEquals(eligible, dptEligibility);
			Assert.assertEquals(learnMore, dptButton1);
			Assert.assertEquals(getStarted, dptButton2);
			VisualCheck.doVisualCheck(CHECK_HUB_ELIGIBLE_TOP_SCREEN);
			//open dpt learn more
			utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_0_learn_more_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Who are recovering from a recent surgery or injury']")));
			//back to hub screen
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//open dpt get started
			utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_0_get_started_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Digital Physical Therapy']")));
			//back to hub screen
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
			MobileElement programsCard2 = driver.findElementByAccessibilityId("on_call_programs_card_2");
			mobileActions.swipeByElements(programsCard2, programsCard0);
			VisualCheck.doVisualCheck(CHECK_HUB_ELIGIBLE_BOTTOM_SCREEN);
			//validate bloom program card
			driver.findElementByXPath("//android.widget.TextView[@text='Bloom']");
			driver.findElementByXPath("//android.widget.TextView[@text='Innovative, digital pelvic health therapy from the comfort of home.']");
			String bloomEligibility = driver.findElementByXPath("//android.view.View[@content-desc=\"on_call_programs_card_1\"]/android.widget.TextView[1]").getText();
			String bloomButton1 = driver.findElementByXPath("//android.view.View[@content-desc=\"on_call_programs_card_1\"]/android.view.View[1]/android.widget.TextView").getText();
			String bloomButton2 = driver.findElementByXPath("//android.view.View[@content-desc=\"on_call_programs_card_1\"]/android.view.View[2]/android.widget.TextView").getText();
			Assert.assertEquals(eligible, bloomEligibility);
			Assert.assertEquals(learnMore, bloomButton1);
			Assert.assertEquals(getStarted, bloomButton2);
			//validate move program card
			driver.findElementByXPath("//android.widget.TextView[@text='Move']");
			driver.findElementByXPath("//android.widget.TextView[@text='Get moving, stay moving, and keep pain away with your own personal training program.']");
			String moveEligibility = driver.findElementByXPath("//android.view.View[@content-desc=\"on_call_programs_card_2\"]/android.widget.TextView[1]").getText();
			String moveButton1 = driver.findElementByXPath("//android.view.View[@content-desc=\"on_call_programs_card_2\"]/android.view.View[1]/android.widget.TextView").getText();
			String moveButton2 = driver.findElementByXPath("//android.view.View[@content-desc=\"on_call_programs_card_2\"]/android.view.View[2]/android.widget.TextView").getText();
			Assert.assertEquals(eligible, moveEligibility);
			Assert.assertEquals(learnMore, moveButton1);
			Assert.assertEquals(getStarted, moveButton2);
			//open bloom learn more
			utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_1_learn_more_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Designed for women:']")));
			//back to hub screen
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//scroll to show bloom card
			programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
			homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
			mobileActions.swipeByElements(programsCard0, homeOnCallCard);
			//open bloom get started
			utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_1_get_started_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Bloom']")));
			//back to hub screen
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//scroll to show move card
			programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
			homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
			mobileActions.swipeByElements(programsCard0, homeOnCallCard);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			programsCard2 = driver.findElementByAccessibilityId("on_call_programs_card_2");
			mobileActions.swipeByElements(programsCard2, programsCard0);
			//open move learn more
			utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_2_learn_more_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Who want to learn and try exercise']")));
			//back to hub screen
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//scroll to show move card
			programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
			homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
			mobileActions.swipeByElements(programsCard0, homeOnCallCard);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			programsCard2 = driver.findElementByAccessibilityId("on_call_programs_card_2");
			mobileActions.swipeByElements(programsCard2, programsCard0);
			//open move get started
			utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_2_get_started_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Move']")));
			//back to hub screen
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);

			ConfigurationsAndroid.killDriver();
		}
	}

	@Test
	public void hubScreenEnrolled() throws Exception {
		MobileActions mobileActions = new MobileActions(driver);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		new VisualCheck(driver);

		//login
		System.out.println("Ligou o proxy??");
		utilitiesAndroid.newLogin("luiza.preventive@sword.com", "Test1234!", driver);
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
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_1_get_started_button", driver);
		//validate bloom get started enrolled popup
		driver.findElementByXPath("//android.widget.TextView[@text=\"Since you're already enrolled in a program, you'll need to complete it first.\n" +
				"\n" +
				"Then, you can enroll in a new program.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text='Close']");
		VisualCheck.doVisualCheck(CHECK_HUB_ENROLLED_BLOOM_POPUP);
		//close popup
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		//tap move get started button
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_2_get_started_button", driver);
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

		ConfigurationsAndroid.killDriver();
	}

	@Test
	public void hubScreenUnderageDptNotEligible() throws Exception {
		MobileActions mobileActions = new MobileActions(driver);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		new VisualCheck(driver);

		//login
		System.out.println("Ligou o proxy??");
		utilitiesAndroid.newLogin("underage@sword.com", "Test1234!", driver);
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
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_0_interested_button", driver);
		driver.findElementByXPath("//android.widget.TextView[@text=\"Thank you for your interest. We'll notify your benefits manager.\"]");
		VisualCheck.doVisualCheck(CHECK_HUB_INTERESTED_POPUP);
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
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
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_1_get_started_button", driver);
		//validate bloom underage bottom sheet
		driver.findElementByXPath("//android.widget.TextView[@text=\"Thanks for your interest in Bloom!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Unfortunately, we require Bloom members to be at least 18 years old. We hope to see you enroll in the future!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Got it\"]");
		VisualCheck.doVisualCheck(CHECK_HUB_BLOOM_UNDERAGE_BOTTOMSHEET);
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View/android.widget.Button", driver);
		//tap move get started button
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_2_get_started_button", driver);
		//validate move underage bottom sheet
		driver.findElementByXPath("//android.widget.TextView[@text=\"Thanks for your interest in Move!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Unfortunately, we require Move members to be at least 18 years old. We hope to see you enroll in the future!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Got it\"]");
		VisualCheck.doVisualCheck(CHECK_HUB_MOVE_UNDERAGE_BOTTOMSHEET);
		mobileActions.tapByCoordinates(785, 1181);

		ConfigurationsAndroid.killDriver();
	}

	@Test
	public void learnMoreAllEligible() throws Exception {
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
		//validate dpt learn more screen
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_0_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Digital Physical Therapy']")));
		driver.findElementByXPath("//android.widget.TextView[@text=\"Physical therapy you can do anytime, anywhere.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Designed for people:\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Who have a muscle or joint condition\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Who are suffering from pain or limited mobility\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Who are recovering from a recent surgery or injury\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Get started\"]");
		VisualCheck.doVisualCheck(CHECK_DPT_LEARN_MORE_1_SCREEN);
		//scroll to show more content
		MobileElement dptThirdDotTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"Who are recovering from a recent surgery or injury\"]");
		MobileElement titleDPT = driver.findElementByXPath("//android.widget.TextView[@text='Digital Physical Therapy']");
		mobileActions.swipeByElements(dptThirdDotTxt, titleDPT);
		//validate the screen
		driver.findElementByXPath("//android.widget.TextView[@text=\"About Digital Physical Therapy\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Sword's Digital Physical Therapy program provides you with the highest clinical-grade therapy you can do from home. Want to exercise at 4 a.m. in your pajamas? No problem.\"]");
		//scroll to show more content
		MobileElement aboutTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[2]");
		mobileActions.swipeByElements(aboutTitle, dptThirdDotTxt);
		//validate the screen
		driver.findElementByXPath("//android.widget.TextView[@text=\"Your Physical Therapist\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"You will select your own PT, who will be with you throughout your program to answer questions and make sure you stay on track.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Your Digital Therapist\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"The Digital Therapist accessed via your tablet, will provide you with instruction and real-time feedback so you know you are doing the exercises correctly.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"A plan designed for you\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Each program is created for your specific needs, goals and ability.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Areas we treat\"]");
		//scroll to show more content
		MobileElement areasWeTreat = driver.findElementByXPath("//android.widget.TextView[@text=\"Areas we treat\"]");
		MobileElement yourPhysicalTherapist = driver.findElementByXPath("//android.widget.TextView[@text=\"Your Physical Therapist\"]");
		mobileActions.swipeByElements(areasWeTreat, yourPhysicalTherapist);
		VisualCheck.doVisualCheck(CHECK_DPT_LEARN_MORE_2_SCREEN);
		//scroll the areas we treat
		MobileElement activeLivingTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"Active living & Falls prevention\"]");
		MobileElement ankleTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"Ankle/Foot\"]");
		mobileActions.swipeByElements(activeLivingTxt, ankleTxt);
		MobileElement kneeTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"Knee\"]");
		mobileActions.swipeByElements(activeLivingTxt, kneeTxt);
		//tap get started
		utilitiesAndroid.clickByAccessibilityId("get_started_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Welcome to Digital Physical Therapy\"]")));
		//back to hub screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
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
		//validate bloom learn more
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_1_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Bloom\"]")));
		driver.findElementByXPath("//android.widget.TextView[@text=\"Sensitive, clinical-grade care for pelvic health designed just for you.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Designed for women:\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Who struggle with bladder or bowel issues including leakage\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Who suffer from pelvic pain, pressure, or discomfort, or dysfunction from low back, core, or hips\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"During all life stages including young adulthood, pregnancy, postpartum, and menopause\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Get started\"]");
		VisualCheck.doVisualCheck(CHECK_BLOOM_LEARN_MORE_1_SCREEN);
		//scroll to show more content
		MobileElement bloomThirdDotTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"During all life stages including young adulthood, pregnancy, postpartum, and menopause\"]");
		MobileElement titleBloom = driver.findElementByXPath("//android.widget.TextView[@text=\"Bloom\"]");
		mobileActions.swipeByElements(bloomThirdDotTxt, titleBloom);
		//validate the screen
		driver.findElementByXPath("//android.widget.TextView[@text=\"About Bloom\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Bloom was built by women to provide convenient, safe and supportive care for pelvic health disorders that are often overlooked and under-treated. Each program is designed to address the specific needs, concerns and goals of the individual.\"]");
		//scroll to show more content
		MobileElement aboutBloomTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"Bloom was built by women to provide convenient, safe and supportive care for pelvic health disorders that are often overlooked and under-treated. Each program is designed to address the specific needs, concerns and goals of the individual.\"]");
		MobileElement aboutBloomTitle = driver.findElementByXPath("//android.widget.TextView[@text=\"About Bloom\"]");
		mobileActions.swipeByElements(aboutBloomTxt, aboutBloomTitle);
		MobileElement ourPhsTxt = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[4]");
		mobileActions.swipeByElements(ourPhsTxt, aboutBloomTitle);
		//validate the screen
		driver.findElementByXPath("//android.widget.TextView[@text=\"Pelvic Health Specialists (PHS)\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Our PHSs all have Doctor of Physical Therapy Degrees and clinical experience focusing on the pelvic floor and pelvic health. They work 1-1 with each individual, providing customized guidance.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Bloom Pod\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Your pod tracks and measures the force, stamina, and accuracy of the pelvic floor, offering real-time feedback and results.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Bloom App\"]");
		//scroll to show more content
		MobileElement bloomAppTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"Bloom App\"]");
		MobileElement pelvicHealthSpecialistsTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"Pelvic Health Specialists (PHS)\"]");
		mobileActions.swipeByElements(bloomAppTxt, pelvicHealthSpecialistsTxt);
		//validate the screen
		driver.findElementByXPath("//android.widget.TextView[@text=\"The app connects to your pod for your exercise program. It also gives you access to exercise results, Cognitive Behavioral Therapy, and a library of clinical resources.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Areas we treat\"]");
		VisualCheck.doVisualCheck(CHECK_BLOOM_LEARN_MORE_2_SCREEN);
		//scroll the areas we treat
		MobileElement pelvicPainTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"Pelvic Pain\"]");
		MobileElement bladderHealthTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"Bladder Health\"]");
		mobileActions.swipeByElements(pelvicPainTxt, bladderHealthTxt);
		MobileElement prolapseTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"Prolapse\"]");
		mobileActions.swipeByElements(pelvicPainTxt, prolapseTxt);
		//tap get started
		utilitiesAndroid.clickByAccessibilityId("get_started_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Welcome to Bloom\"]")));
		//back to hub screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
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
		//validate move learn more
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_2_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Move\"]")));
		driver.findElementByXPath("//android.widget.TextView[@text=\"Personalized training programs that help you start moving, stay moving and live pain free.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Designed for people:\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Who want to be more active\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Who want to prevent injury or falls\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Who want to learn and try exercise\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Who have low pain levels\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Get started\"]");
		VisualCheck.doVisualCheck(CHECK_MOVE_LEARN_MORE_1_SCREEN);
		//scroll to show more content
		MobileElement moveFourthDotTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"Who have low pain levels\"]");
		MobileElement moveTitleTxt = driver.findElementByXPath("//android.widget.TextView[@text=\"Move\"]");
		mobileActions.swipeByElements(moveFourthDotTxt, moveTitleTxt);
		MobileElement aboutMoveTxt = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[3]");
		mobileActions.swipeByElements(aboutMoveTxt, moveFourthDotTxt);
		driver.findElementByXPath("//android.widget.TextView[@text=\"About Move\"]");
		VisualCheck.doVisualCheck(CHECK_MOVE_LEARN_MORE_2_SCREEN);
		driver.findElementByXPath("//android.widget.TextView[@text=\"Sword Move empowers you to move more, and move right. With Move, you have access to your own personal trainer, who will get to know you, create your own personalized activity programs, and be there to support you throughout your journey.\n" +
				"\n" +
				"Connect a wearable device to help track your sleep, steps, and other biometric data so you can see and feel your results. That data also helps provide your personal trainer with valuable insights to help tailor your program as you progress over time.\"]");
		VisualCheck.doVisualCheck(CHECK_MOVE_LEARN_MORE_2_SCREEN);
		//tap get started
		utilitiesAndroid.clickByAccessibilityId("get_started_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Welcome to Move\"]")));
		//back to hub screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		ConfigurationsAndroid.killDriver();
	}

	@Test
	public void learnMoreAllNotEligible() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActions mobileActions = new MobileActions(driver);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		new VisualCheck(driver);

		//login
		System.out.println("Ligou o proxy?");
		utilitiesAndroid.newLogin("luiza.preventive@sword.com", "Test1234!", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate dpt learn more screen not eligible
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_0_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Digital Physical Therapy']")));
		driver.findElementByXPath("//android.widget.TextView[@text=\"Close\"]");
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_DPT_NOT_ELIGIBLE_SCREEN);
		//tap close button
		utilitiesAndroid.clickByAccessibilityId("close_button", driver);
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
		//validate bloom learn more screen not eligible
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_1_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Bloom\"]")));
		driver.findElementByXPath("//android.widget.TextView[@text=\"Close\"]");
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_BLOOM_NOT_ELIGIBLE_SCREEN);
		//tap close button
		utilitiesAndroid.clickByAccessibilityId("close_button", driver);
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
		//validate move learn more screen not eligible
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_2_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Move\"]")));
		driver.findElementByXPath("//android.widget.TextView[@text=\"Close\"]");
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_MOVE_NOT_ELIGIBLE_SCREEN);
		//tap close button
		utilitiesAndroid.clickByAccessibilityId("close_button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		ConfigurationsAndroid.killDriver();
	}

	@Test
	public void learnMoreUnderage() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActions mobileActions = new MobileActions(driver);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		new VisualCheck(driver);

		//login
		utilitiesAndroid.newLogin("underage@sword.com", "Test1234!", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate dpt learn more screen underage
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_0_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Digital Physical Therapy']")));
		//tap get started button
		utilitiesAndroid.clickByAccessibilityId("get_started_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Digital Physical Therapy']")));
		//back to hub screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
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
		//validate bloom learn more screen underage
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_1_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Bloom\"]")));
		//tap get started
		utilitiesAndroid.clickByAccessibilityId("get_started_button", driver);
		//validate the underage bottom sheet
		driver.findElementByXPath("//android.widget.TextView[@text=\"Thanks for your interest in Bloom!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Unfortunately, we require Bloom members to be at least 18 years old. We hope to see you enroll in the future!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Got it\"]");
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_BLOOM_UNDERAGE_BOTTOMSHEET);
		//tap got it button
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		//back to hub screen
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
		//validate move learn more screen underage
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_2_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Move\"]")));
		//tap get started
		utilitiesAndroid.clickByAccessibilityId("get_started_button", driver);
		//validate the underage bottom sheet
		driver.findElementByXPath("//android.widget.TextView[@text=\"Thanks for your interest in Move!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Unfortunately, we require Move members to be at least 18 years old. We hope to see you enroll in the future!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Got it\"]");
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_MOVE_UNDERAGE_BOTTOMSHEET);
		//tap outside the bottom sheet
		mobileActions.tapByCoordinates(799, 632);
		//back to hub screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		ConfigurationsAndroid.killDriver();
	}

	@Test
	public void learnMoreEnrolled() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActions mobileActions = new MobileActions(driver);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		new VisualCheck(driver);

		//login
		System.out.println("Ligou o proxy?");
		utilitiesAndroid.newLogin("luiza.preventive@sword.com", "Test1234!", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Currently enrolled\"]")));
		//scroll to show more cards
		MobileElement programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
		MobileElement homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
		mobileActions.swipeByElements(programsCard0, homeOnCallCard);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate bloom learn more screen enrolled
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_1_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Bloom\"]")));
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_BLOOM_ENROLLED_SCREEN);
		//tap get started
		utilitiesAndroid.clickByAccessibilityId("get_started_button", driver);
		//validate the bottom sheet
		driver.findElementByXPath("//android.widget.TextView[@text=\"Uh-oh!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"It looks like you're already enrolled in a program. Before you start a new program, you'll need to complete your current one.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Got it\"]");
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_BLOOM_ENROLLED_BOTTOMSHEET);
		//close bottom sheet
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		//back to hub screen
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
		//validate move learn more screen enrolled
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_2_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Move\"]")));
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_MOVE_ENROLLED_SCREEN);
		//tap get started
		utilitiesAndroid.clickByAccessibilityId("get_started_button", driver);
		//validate the bottom sheet
		driver.findElementByXPath("//android.widget.TextView[@text=\"Uh-oh!\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"It looks like you're already enrolled in a program. Before you start a new program, you'll need to complete your current one.\"]");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Got it\"]");
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_MOVE_ENROLLED_BOTTOMSHEET);
		//close bottom sheet
		mobileActions.tapByCoordinates(854, 1056);
		//back to hub screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		ConfigurationsAndroid.killDriver();
	}
}