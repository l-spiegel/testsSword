package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class TestSwordRegressionLearnMoreiOS {

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
	private final static String CHECK_LEARN_MORE_BLOOM_ENROLLED_SCREEN = "bloom_learn_more_enrolled_screen";
	private final static String CHECK_LEARN_MORE_BLOOM_ENROLLED_BOTTOMSHEET = "bloom_learn_more_enrolled_bottom_sheet";
	private final static String CHECK_LEARN_MORE_MOVE_ENROLLED_SCREEN = "move_learn_more_enrolled_screen";
	private final static String CHECK_LEARN_MORE_MOVE_ENROLLED_BOTTOMSHEET = "move_learn_more_enrolled_bottom_sheet";

	private IOSDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
	}

	@Test
	public void learnMoreAllEligible() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		new VisualCheck(driver);

		//login
		utilitiesiOS.newLogin("luiza.preventive@sword.com", "Test1234!", driver);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate dpt learn more screen
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_0_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Digital Physical Therapy']")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Physical therapy you can do anytime, anywhere.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Designed for people:\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Who have a muscle or joint condition\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Who are suffering from pain or limited mobility\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Who are recovering from a recent surgery or injury\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Get started\"]");
		VisualCheck.doVisualCheck(CHECK_DPT_LEARN_MORE_1_SCREEN);
		//scroll to show more content
		MobileElement dptImage = driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeImage");
		MobileElement titleDPT = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Digital Physical Therapy']");
		mobileActions.swipeByElements(dptImage, titleDPT);
		//scroll to show more content
		MobileElement aboutTitle = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"About Digital Physical Therapy\"]");
		mobileActions.swipeByElements(aboutTitle, dptImage);
		//validate the screen
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"About Digital Physical Therapy\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Sword's Digital Physical Therapy program provides you with the highest clinical-grade therapy you can do from home. Want to exercise at 4 a.m. in your pajamas? No problem.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Your Physical Therapist\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"You will select your own PT, who will be with you throughout your program to answer questions and make sure you stay on track.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Your Digital Therapist\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"The Digital Therapist accessed via your tablet, will provide you with instruction and real-time feedback so you know you are doing the exercises correctly.\"]");
		//scroll to show more content
		MobileElement theDigitalTherapistTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"The Digital Therapist accessed via your tablet, will provide you with instruction and real-time feedback so you know you are doing the exercises correctly.\"]");
		mobileActions.swipeByElements(theDigitalTherapistTxt, aboutTitle);
		MobileElement areasWeTreat = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Areas we treat\"]");
		mobileActions.swipeByElements(areasWeTreat, theDigitalTherapistTxt);
		//validate the screen
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"A plan designed for you\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Each program is created for your specific needs, goals and ability.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Areas we treat\"]");
		VisualCheck.doVisualCheck(CHECK_DPT_LEARN_MORE_2_SCREEN);
		//scroll the areas we treat
		MobileElement activeLivingTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Active living & Falls prevention\"]");
		MobileElement ankleTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Ankle/Foot\"]");
		mobileActions.swipeByElements(activeLivingTxt, ankleTxt);
		MobileElement kneeTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Knee\"]");
		mobileActions.swipeByElements(activeLivingTxt, kneeTxt);
		//tap get started
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Get started\"]", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Welcome to Digital Physical Therapy\"]")));
		//back to hub screen
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
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
		//validate bloom learn more
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_1_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Bloom\"]")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Sensitive, clinical-grade care for pelvic health designed just for you.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Designed for women:\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Who struggle with bladder or bowel issues including leakage\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Who suffer from pelvic pain, pressure, or discomfort, or dysfunction from low back, core, or hips\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"During all life stages including young adulthood, pregnancy, postpartum, and menopause\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Get started\"]");
		VisualCheck.doVisualCheck(CHECK_BLOOM_LEARN_MORE_1_SCREEN);
		//scroll to show more content
		MobileElement bloomThirdDotTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"During all life stages including young adulthood, pregnancy, postpartum, and menopause\"]");
		MobileElement titleBloom = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Bloom\"]");
		mobileActions.swipeByElements(bloomThirdDotTxt, titleBloom);
		MobileElement aboutBloomTitle = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"About Bloom\"]");
		MobileElement bloomImage = driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeImage");
		mobileActions.swipeByElements(aboutBloomTitle, bloomImage);
		//validate the screen
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"About Bloom\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Bloom was built by women to provide convenient, safe and supportive care for pelvic health disorders that are often overlooked and under-treated. Each program is designed to address the specific needs, concerns and goals of the individual.\"]");
		//scroll to show more content
		MobileElement pelvicHealthSpecialistsTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Pelvic Health Specialists (PHS)\"]");
		mobileActions.swipeByElements(pelvicHealthSpecialistsTxt, bloomImage);
		//validate the screen
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Pelvic Health Specialists (PHS)\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Our PHSs all have Doctor of Physical Therapy Degrees and clinical experience focusing on the pelvic floor and pelvic health. They work 1-1 with each individual, providing customized guidance.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Bloom Pod\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Your pod tracks and measures the force, stamina, and accuracy of the pelvic floor, offering real-time feedback and results.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Bloom App\"]");
		//scroll to show more content
		MobileElement bloomAppTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Bloom App\"]");
		mobileActions.swipeByElements(bloomAppTxt, pelvicHealthSpecialistsTxt);
		//validate the screen
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"The app connects to your pod for your exercise program. It also gives you access to exercise results, Cognitive Behavioral Therapy, and a library of clinical resources.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Areas we treat\"]");
		//scroll to show all the areas we treat
		areasWeTreat = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Areas we treat\"]");
		mobileActions.swipeByElements(areasWeTreat, bloomAppTxt);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		VisualCheck.doVisualCheck(CHECK_BLOOM_LEARN_MORE_2_SCREEN);
		//scroll the areas we treat
		MobileElement pelvicPainTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Pelvic Pain\"]");
		MobileElement bladderHealthTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Bladder Health\"]");
		mobileActions.swipeByElements(pelvicPainTxt, bladderHealthTxt);
		MobileElement prolapseTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Prolapse\"]");
		mobileActions.swipeByElements(pelvicPainTxt, prolapseTxt);
		//tap get started
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Get started\"]", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Welcome to Bloom\"]")));
		//back to hub screen
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate move learn more
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_2_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Move\"]")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Personalized training programs that help you start moving, stay moving and live pain free.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Designed for people:\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Who want to be more active\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Who want to prevent injury or falls\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Who want to learn and try exercise\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Who have low pain levels\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Get started\"]");
		VisualCheck.doVisualCheck(CHECK_MOVE_LEARN_MORE_1_SCREEN);
		//scroll to show more content
		MobileElement moveFourthDotTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Who have low pain levels\"]");
		MobileElement moveTitleTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Move\"]");
		mobileActions.swipeByElements(moveFourthDotTxt, moveTitleTxt);
		MobileElement aboutMoveTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"About Move\"]");
		mobileActions.swipeByElements(aboutMoveTxt, moveFourthDotTxt);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"About Move\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Sword Move empowers you to move more, and move right. With Move, you have access to your own personal trainer, who will get to know you, create your own personalized activity programs, and be there to support you throughout your journey.\n" +
				"\n" +
				"Connect a wearable device to help track your sleep, steps, and other biometric data so you can see and feel your results. That data also helps provide your personal trainer with valuable insights to help tailor your program as you progress over time.\"]");
		VisualCheck.doVisualCheck(CHECK_MOVE_LEARN_MORE_2_SCREEN);
		//tap get started
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Get started\"]", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Welcome to Move\"]")));
		//back to hub screen
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		ConfigurationsiOS.killDriver();
	}

	@Test
	public void learnMoreAllNotEligible() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		new VisualCheck(driver);

		//login
		System.out.println("Ligou o proxy?");
		utilitiesiOS.newLogin("luiza.preventive@sword.com", "Test1234!", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate dpt learn more screen not eligible
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_0_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Digital Physical Therapy']")));
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Close\"]");
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_DPT_NOT_ELIGIBLE_SCREEN);
		//tap close button
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Close\"]", driver);
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
		//validate bloom learn more screen not eligible
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_1_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Bloom\"]")));
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Close\"]");
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_BLOOM_NOT_ELIGIBLE_SCREEN);
		//tap close button
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Close\"]", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate move learn more screen not eligible
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_2_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Move\"]")));
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Close\"]");
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_MOVE_NOT_ELIGIBLE_SCREEN);
		//tap close button
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Close\"]", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		ConfigurationsiOS.killDriver();
	}

	@Test
	public void learnMoreUnderage() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		new VisualCheck(driver);

		//login
		utilitiesiOS.newLogin("underage@sword.com", "Test1234!", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate dpt learn more screen underage
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_0_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Digital Physical Therapy']")));
		//tap get started button
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Get started\"]", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Welcome to Digital Physical Therapy']")));
		//back to hub screen
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
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
		//validate bloom learn more screen underage
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_1_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Bloom\"]")));
		//tap get started
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Get started\"]", driver);
		//validate the underage bottom sheet
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Thanks for your interest in Bloom!\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Unfortunately, we require Bloom members to be at least 18 years old. We hope to see you enroll in the future!\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Got it\"]");
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_BLOOM_UNDERAGE_BOTTOMSHEET);
		//tap got it button
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Got it\"]", driver);
		//back to hub screen
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate move learn more screen underage
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_2_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Move\"]")));
		//tap get started
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Get started\"]", driver);
		//validate the underage bottom sheet
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Thanks for your interest in Move!\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Unfortunately, we require Move members to be at least 18 years old. We hope to see you enroll in the future!\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Got it\"]");
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_MOVE_UNDERAGE_BOTTOMSHEET);
		//tap outside the bottom sheet
		mobileActions.tapByCoordinates(799, 632);
		//back to hub screen
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		ConfigurationsiOS.killDriver();
	}

	@Test
	public void learnMoreEnrolled() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		new VisualCheck(driver);

		//login
		System.out.println("Ligou o proxy?");
		utilitiesiOS.newLogin("luiza.preventive@sword.com", "Test1234!", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Active\"]")));
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
		//validate bloom learn more screen enrolled
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_1_learn_more_button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_BLOOM_ENROLLED_SCREEN);
		//tap get started
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Get started\"]", driver);
		//validate the bottom sheet
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Uh-oh!\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"It looks like you're already enrolled in a program. Before you start a new program, you'll need to complete your current one.\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Got it\"]");
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_BLOOM_ENROLLED_BOTTOMSHEET);
		//close bottom sheet
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Got it\"]", driver);
		//back to hub screen
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate move learn more screen enrolled
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_2_learn_more_button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_MOVE_ENROLLED_SCREEN);
		//tap get started
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Get started\"]", driver);
		//validate the bottom sheet
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Uh-oh!\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"It looks like you're already enrolled in a program. Before you start a new program, you'll need to complete your current one.\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Got it\"]");
		VisualCheck.doVisualCheck(CHECK_LEARN_MORE_MOVE_ENROLLED_BOTTOMSHEET);
		//close bottom sheet
		mobileActions.tapByCoordinates(854, 1056);
		//back to hub screen
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		ConfigurationsiOS.killDriver();
	}
}