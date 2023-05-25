package appium.iOS;

import java.io.File;
import java.net.MalformedURLException;
import appium.Android.ConfigurationsAndroid;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import io.appium.java_client.ios.IOSDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThan;

public class swordRegressionOnCalliOS {
	private IOSDriver<MobileElement> driver;
	
	private final static String VALIDATION_PATH = "/Users/luizaspiegel/Documents/image check/regression oncall/iOS/virtual pt";
	private final static String BASELINE = "COMP_";
	private final static String CHECK_ON_CALL_NO_CHAT_SCREEN = "on_call_empty_screen";
	private final static String CHECK_TERMS_CONDITIONS = "terms_and_conditions_screen";
	private final static String CHECK_PHYSICAL_PAIN_EMPTY_SCREEN = "physical_pain_chat_empty";
	private final static String CHECK_HEADACHES_EMPTY_SCREEN = "headaches_chat_empty";
	private final static String CHECK_PELVIC_HEALTH_MESSAGE_SENT_SCREEN = "pelvic_health_with_message";
	private final static String CHECK_HEADACHES_MESSAGE_SENT_SCREEN = "headaches_with_message";
	private final static String CHECK_ON_CALL_WITH_CHAT_SCREEN = "on_call_chat_open_screen";
	private final static String CHECK_BOTTOM_SHEET = "bottom_sheet";
	private final static String CHECK_HUB_WITH_PROGRAMS_SCREEN = "hub_with_programs";
	private final static String CHECK_HUB_WITHOUT_PROGRAMS_SCREEN = "hub_without_programs";
	private final static String CHECK_TRIAGE_SCREEN = "triage_screen";
	private final static String CHECK_HUB_WITH_OPEN_CHAT = "hub_with_active_chat";
	private final static String CHECK_HUB_WITH_OPEN_CHAT_AFTER_LOGIN = "hub_with_active_chat_after_login";

	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
	}

	@Test
	public void preventive() throws Exception {
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		VisualCheck visualCheck = new VisualCheck(driver);
		
		//login
		utilitiesiOS.login("luiza.preventive@sword.com", "Test1234!", driver);
		//validar hub screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='On-Call']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Get on-demand support from a Clinical Pain Specialist via chat.\"]");
		String onCallButton = driver.findElementByAccessibilityId("home_on_call_card_help_from_specialist_button").getText();
		Assert.assertEquals("Chat with a Specialist", onCallButton);
		if (driver.findElements(By.id("Explore our programs")).size() > 0) {
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Explore our programs\"]");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Learn more about our programs below to find the best fit for you.\"]");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Eligible\"]");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Digital Physical Therapy\"]");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Physical therapy you can do anytime, anywhere.\"]");
			//clicar get started
			utilitiesiOS.clickByAccessibilityId("on_call_programs_card_0_get_started_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage[@name=\"Sword Health\"]")));
			//voltar
			utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			visualCheck.doVisualCheck(CHECK_HUB_WITH_PROGRAMS_SCREEN);
		} else {
			visualCheck.doVisualCheck(CHECK_HUB_WITHOUT_PROGRAMS_SCREEN);
		}
		//abrir academy tab
		utilitiesiOS.clickByAccessibilityId("bottom_navigation_academy_tab", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Academy']")));
		//voltar pra hub tab
		utilitiesiOS.clickByAccessibilityId("bottom_navigation_hub_tab", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//clicar on-call card
		utilitiesiOS.clickByAccessibilityId("home_on_call_card_help_from_specialist_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		//validar triage screen - ver se em Android consegue validar os textos dos cards
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Chat with a Pain Specialist\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Chat with a Headache Specialist\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Chat with a Pelvic Specialist\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"This practitioner is not intended to be a substitute for professional medical advice, diagnosis or treatment. Read Terms & Conditions\"]");
		visualCheck.doVisualCheck(CHECK_TRIAGE_SCREEN);
		//abrir terms and conditions
		utilitiesiOS.clickByAccessibilityId("on_call_terms_conditions", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Terms & Conditions\"]")));
		//validar ecrã terms and conditions
		visualCheck.doVisualCheck(CHECK_TERMS_CONDITIONS);
		MobileElement termsOnCallTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='On-Call']");
		MobileElement termsTxt1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]");
		mobileActions.swipeByElements(termsTxt1, termsOnCallTxt);
		String termsTxt2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]").getText();
		Assert.assertEquals("All information provided by the On-call clinical pain specialist is for informational purposes only.  Such information is not intended to be and should not be used as a substitute for professional medical advice, diagnosis or treatment.  The On-call clinical pain specialist will not be able to provide a diagnosis, write you a prescription, or provide a plan of care or treatment.  If you choose to rely on any information provided by the On-call clinical pain specialist, you rely solely at your own risk.\n"
				+ "\n"
				+ "Always seek the advice of your physician or other qualified healthcare provider for any health questions or concerns you may have regarding your medical condition or treatment. If you have or think you may have a medical emergency, need urgent or emergency care, please call your doctor, go to the nearest hospital emergency department, or call 911 immediately.  Neither Sword Health, Inc. nor any of its affiliates and contracted entities assumes responsibility or liability for any damages, claims, liabilities, costs or obligations arising from the use of this app or any On-call clinical pain specialist.", termsTxt2);
		//voltar até a hub screen
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//print do hub screen sem on-call chat ativo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		byte[] hubWithoutChat = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//abrir physical pain chat
		utilitiesiOS.clickByAccessibilityId("home_on_call_card", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		utilitiesiOS.clickByAccessibilityId("on_call_physical_pain_card", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		visualCheck.doVisualCheck(CHECK_PHYSICAL_PAIN_EMPTY_SCREEN);
		//voltar sem enviar mensagem
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//abrir triage screen de novo
		utilitiesiOS.clickByAccessibilityId("home_on_call_card_help_from_specialist_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		//abrir headaches chat
		utilitiesiOS.clickByAccessibilityId("on_call_headaches_card_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
		//enviar mensagem
		MobileElement message = driver.findElementByXPath("//XCUIElementTypeTextView");
		message.click();
		message.sendKeys("Tests. Please ignore");
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeButton[2]", driver);
		mobileActions.tapByCoordinates(172, 273);
		visualCheck.doVisualCheck(CHECK_HEADACHES_MESSAGE_SENT_SCREEN);
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		//validar on-call card novo
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		visualCheck.doVisualCheck(CHECK_HUB_WITH_OPEN_CHAT);
		byte[] hubWithChat = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result = driver
		        .getImagesSimilarity(hubWithoutChat, hubWithChat, new SimilarityMatchingOptions()
		                .withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(lessThan(0.95)));
		String baselineFilename = VALIDATION_PATH + "/" + BASELINE + "hub_preventive.png";
        File comparison = new File(baselineFilename);
        result.storeVisualization(comparison);
        System.out.println("Similarity of: " + result.getScore());
		//clicar no on-call card
		utilitiesiOS.clickByAccessibilityId("home_on_call_card", driver);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//fazer logout
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		utilitiesiOS.clickByAccessibilityId("menu_option_logout", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		//fazer login no mesmo user de novo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		driver.findElementByAccessibilityId("loginPasswordTextfield").sendKeys("Test1234!");
		utilitiesiOS.clickByAccessibilityId("loginButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Not now']", driver);
		//validar que ainda mostra o mesmo on-call card
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		visualCheck.doVisualCheck(CHECK_HUB_WITH_OPEN_CHAT_AFTER_LOGIN);
		byte[] hubAfterLogin = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result2 = driver
		        .getImagesSimilarity(hubWithChat, hubAfterLogin, new SimilarityMatchingOptions()
		                .withEnabledVisualization());
		assertThat(result2.getVisualization().length, is(greaterThan(0)));
		assertThat(result2.getScore(), is(greaterThan(0.95)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "hub_after_login_preventive" + ".png";
        File comparison2 = new File(baselineFilename);
        result2.storeVisualization(comparison2);
		System.out.println("Similarity of: " + result2.getScore());

		ConfigurationsiOS.killDriver();
	}

	@Test
	public void virtualPt() throws Exception {
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		VisualCheck visualCheck = new VisualCheck(driver);
		
		//login
		utilitiesiOS.login("vinteum@sword.com", "Test1234!", driver);
		//abrir hub screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		utilitiesiOS.clickByAccessibilityId("bottom_navigation_hub_tab", driver);
		//validar hub screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='On-Call']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Get on-demand support from a Clinical Pain Specialist via chat.']");
		String onCallButton = driver.findElementByAccessibilityId("home_on_call_card_help_from_specialist_button").getText();
		Assert.assertEquals("Chat with a Specialist", onCallButton);
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name=\"Explore our programs\"]")).size() > 0) {
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Learn more about our programs below to find the best fit for you.\"]");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Digital Physical Therapy\"]");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Physical therapy you can do anytime, anywhere.\"]");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Eligible\"]");
			//clicar enroll now
			utilitiesiOS.clickByAccessibilityId("on_call_programs_card_0_get_started_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage[@name=\"Sword Health\"]")));
			//voltar
			utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Get on-demand support from a Clinical Pain Specialist via chat.\"]")));
			visualCheck.doVisualCheck(CHECK_HUB_WITH_PROGRAMS_SCREEN);
		} else {
			visualCheck.doVisualCheck(CHECK_HUB_WITHOUT_PROGRAMS_SCREEN);
		}
		//clicar on-call card
		utilitiesiOS.clickByAccessibilityId("home_on_call_card_help_from_specialist_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		//validar triage screen - ver se em Android consegue validar os textos dos cards
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Chat with a Pain Specialist']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Chat with a Headache Specialist']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Chat with a Pelvic Specialist']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='This practitioner is not intended to be a substitute for professional medical advice, diagnosis or treatment. Read Terms & Conditions']");
		visualCheck.doVisualCheck(CHECK_TRIAGE_SCREEN);
		//abrir terms and conditions
		utilitiesiOS.clickByAccessibilityId("on_call_terms_conditions", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		//validar ecrã terms and conditions
		visualCheck.doVisualCheck(CHECK_TERMS_CONDITIONS);
		MobileElement termsOnCallTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='On-Call']");
		MobileElement termsTxt1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]");
		mobileActions.swipeByElements(termsTxt1, termsOnCallTxt);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Terms & Conditions']");
		String termsTxt2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]").getText();
		Assert.assertEquals("All information provided by the On-call clinical pain specialist is for informational purposes only.  Such information is not intended to be and should not be used as a substitute for professional medical advice, diagnosis or treatment.  The On-call clinical pain specialist will not be able to provide a diagnosis, write you a prescription, or provide a plan of care or treatment.  If you choose to rely on any information provided by the On-call clinical pain specialist, you rely solely at your own risk.\n"
				+ "\n"
				+ "Always seek the advice of your physician or other qualified healthcare provider for any health questions or concerns you may have regarding your medical condition or treatment. If you have or think you may have a medical emergency, need urgent or emergency care, please call your doctor, go to the nearest hospital emergency department, or call 911 immediately.  Neither Sword Health, Inc. nor any of its affiliates and contracted entities assumes responsibility or liability for any damages, claims, liabilities, costs or obligations arising from the use of this app or any On-call clinical pain specialist.", termsTxt2);
		//voltar até a hub screen
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//print do hub screen sem on-call chat ativo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		byte[] hubWithoutChat = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//abrir headaches chat
		utilitiesiOS.clickByAccessibilityId("home_on_call_card", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		utilitiesiOS.clickByAccessibilityId("on_call_headaches_card", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		visualCheck.doVisualCheck(CHECK_HEADACHES_EMPTY_SCREEN);
		//voltar sem enviar mensagem
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//abrir triage screen de novo
		utilitiesiOS.clickByAccessibilityId("home_on_call_card_help_from_specialist_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		//abrir pelvic chat
		utilitiesiOS.clickByAccessibilityId("on_call_pelvic_health_card_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
		//enviar mensagem
		MobileElement message = driver.findElementByXPath("//XCUIElementTypeTextView");
		message.click();
		message.sendKeys("Tests. Please ignore");
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeButton[2]", driver);
		mobileActions.tapByCoordinates(172, 273);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		visualCheck.doVisualCheck(CHECK_PELVIC_HEALTH_MESSAGE_SENT_SCREEN);
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validar on-call card novo
		visualCheck.doVisualCheck(CHECK_HUB_WITH_OPEN_CHAT);
		byte[] hubWithChat = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result = driver
		        .getImagesSimilarity(hubWithoutChat, hubWithChat, new SimilarityMatchingOptions()
		                .withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(lessThan(0.95)));
		String baselineFilename = VALIDATION_PATH + "/" + BASELINE + "hub_virtualpt.png";
        File comparison = new File(baselineFilename);
        result.storeVisualization(comparison);
        System.out.println("Similarity of: " + result.getScore());
		//clicar no on-call card
		utilitiesiOS.clickByAccessibilityId("home_on_call_card", driver);
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//fazer logout
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		utilitiesiOS.clickByAccessibilityId("menu_option_logout", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		//fazer login no mesmo user de novo
		driver.findElementByAccessibilityId("loginPasswordTextfield").sendKeys("Test1234!");
		utilitiesiOS.clickByAccessibilityId("loginButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Not now']", driver);
		//validar que ainda mostra o mesmo on-call card
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		driver.findElementByAccessibilityId("bottom_navigation_hub_tab").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		visualCheck.doVisualCheck(CHECK_HUB_WITH_OPEN_CHAT_AFTER_LOGIN);
		byte[] hubAfterLogin = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result2 = driver
		        .getImagesSimilarity(hubWithChat, hubAfterLogin, new SimilarityMatchingOptions()
		                .withEnabledVisualization());
		assertThat(result2.getVisualization().length, is(greaterThan(0)));
		assertThat(result2.getScore(), is(greaterThan(0.95)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "hub_after_login_virtualpt" + ".png";
        File comparison2 = new File(baselineFilename);
        result2.storeVisualization(comparison2);
		System.out.println("Similarity of: " + result2.getScore());
		//enviar uma foto do rolo
		utilitiesiOS.clickByAccessibilityId("home_on_call_card", driver);
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeButton", driver);
		utilitiesiOS.clickByAccessibilityId("Photos and videos", driver);
		utilitiesiOS.clickByAccessibilityId("Allow Access to All Photos", driver);
		utilitiesiOS.clickByAccessibilityId("Screenshot, 25 May, 12:09 PM", driver); //precisa atualizar pra uma que apareça no rolo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView[@name='Tests. Please ignore']")));
		File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile3, new File("screen3.jpg"));
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));

	}

	@Test
	public void onCallTab() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		VisualCheck visualCheck = new VisualCheck(driver);

		//login
		System.out.println("LIGOU O PROXY??");
		utilitiesiOS.login("luiza.preventive@sword.com", "Test1234!", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Academy\"]")));
		utilitiesiOS.clickByAccessibilityId("bottom_navigation_on_call_tab", driver);
		//validar on-call screen
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"On-Call\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Get on-demand support from a Clinical Pain Specialist, conveniently via chat.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Chat with a Pain Specialist\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Chat with a Headache Specialist\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Chat with a Pelvic Specialist\"]");
		visualCheck.doVisualCheck(CHECK_ON_CALL_NO_CHAT_SCREEN);
		byte[] onCallBackFirstOpen = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		MobileElement pelvicHealthTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Chat with a Pelvic Specialist\"]");
		MobileElement headachesTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Chat with a Headache Specialist\"]");
		mobileActions.swipeByElements(pelvicHealthTxt, headachesTxt);
		byte[] onCallFirstOpenAfterScroll = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"This practitioner is not intended to be a substitute for professional medical advice, diagnosis or treatment. Read Terms & Conditions\"]");
		utilitiesiOS.clickByAccessibilityId("on_call_terms_conditions", driver);
		//validar terms and conditions
		visualCheck.doVisualCheck(CHECK_TERMS_CONDITIONS);
		MobileElement termsOnCallTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"On-Call\"]");
		MobileElement termsTxt1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]"); //o index pode ser 2, dependendo do que o appium encontre
		mobileActions.swipeByElements(termsTxt1, termsOnCallTxt);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Terms & Conditions\"]");
		String termsTxt2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]").getText();
		Assert.assertEquals("All information provided by the On-call clinical pain specialist is for informational purposes only.  Such information is not intended to be and should not be used as a substitute for professional medical advice, diagnosis or treatment.  The On-call clinical pain specialist will not be able to provide a diagnosis, write you a prescription, or provide a plan of care or treatment.  If you choose to rely on any information provided by the On-call clinical pain specialist, you rely solely at your own risk.\n"
				+ "\n"
				+ "Always seek the advice of your physician or other qualified healthcare provider for any health questions or concerns you may have regarding your medical condition or treatment. If you have or think you may have a medical emergency, need urgent or emergency care, please call your doctor, go to the nearest hospital emergency department, or call 911 immediately.  Neither Sword Health, Inc. nor any of its affiliates and contracted entities assumes responsibility or liability for any damages, claims, liabilities, costs or obligations arising from the use of this app or any On-call clinical pain specialist.", termsTxt2);
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		//abrir chat
		utilitiesiOS.clickByAccessibilityId("on_call_physical_pain_card", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		visualCheck.doVisualCheck(CHECK_PHYSICAL_PAIN_EMPTY_SCREEN);
		//voltar sem mandar mensagem
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//comparação visual pra ver se voltou com o mesmo estado
		byte[] onCallBackWithoutMessage = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result1 = driver
				.getImagesSimilarity(onCallFirstOpenAfterScroll, onCallBackWithoutMessage, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result1.getVisualization().length, is(greaterThan(0)));
		assertThat(result1.getScore(), is(greaterThan(0.95)));
		String baselineFilename = VALIDATION_PATH + "/" + BASELINE + "no_messages.png";
		File comparison = new File(baselineFilename);
		result1.storeVisualization(comparison);
		System.out.println("Similarity of: " + result1.getScore());
		//abrir chat e enviar mensagem
		utilitiesiOS.clickByAccessibilityId("on_call_pelvic_health_card_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
		MobileElement sendMessage = driver.findElementByXPath("//XCUIElementTypeTextView");
		sendMessage.click();
		sendMessage.sendKeys("Test. Please ignore");
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[2]", driver);
		mobileActions.tapByCoordinates(190, 773);
		//comparação visual de chat com mensagem enviada
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		visualCheck.doVisualCheck(CHECK_PELVIC_HEALTH_MESSAGE_SENT_SCREEN);
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		//comparar com o chat inativo
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		visualCheck.doVisualCheck(CHECK_ON_CALL_WITH_CHAT_SCREEN);
		byte[] onCallWithMessage = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result2 = driver
				.getImagesSimilarity(onCallBackWithoutMessage, onCallWithMessage, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result2.getVisualization().length, is(greaterThan(0)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "with_messages.png";
		File comparison2 = new File(baselineFilename);
		assertThat(result2.getScore(), is(lessThan(0.95)));
		result2.storeVisualization(comparison2);
		System.out.println("Similarity of: " + result2.getScore());
		//clicar na conversa aberta
		utilitiesiOS.clickByAccessibilityId("on_call_pelvic_health_card", driver);
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"On-Call\"]")));
		//tentar abrir outra conversa
		utilitiesiOS.clickByAccessibilityId("on_call_headaches_card", driver);
		//validar bottom sheet
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"You already have an open chat\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"It looks like you have an open chat with a Pelvic health specialist. Once this conversation is closed, you can start a new one!\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Close\"]");
		visualCheck.doVisualCheck(CHECK_BOTTOM_SHEET);
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Close\"]", driver);
		//fazer logout
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		utilitiesiOS.clickByAccessibilityId("menu_option_logout", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Welcome to Sword\"]")));
		//fazer login no mesmo user de novo
		driver.findElementByAccessibilityId("loginPasswordTextfield").sendKeys("Test1234!");
		utilitiesiOS.clickByAccessibilityId("loginButton", driver);
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Not now\"]", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Academy\"]")));
		utilitiesiOS.clickByAccessibilityId("bottom_navigation_on_call_tab", driver);
		//validar que ainda mostra o mesmo chat aberto
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Get on-demand support from a Clinical Pain Specialist, conveniently via chat.\"]")));
		byte[] onCallWithMessageAfterLogin = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result3 = driver
				.getImagesSimilarity(onCallBackFirstOpen, onCallWithMessageAfterLogin, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result3.getVisualization().length, is(greaterThan(0)));
		assertThat(result3.getScore(), is(greaterThan(0.50)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "after_login" + ".png";
		File comparison3 = new File(baselineFilename);
		result3.storeVisualization(comparison3);
		System.out.println("Similarity of: " + result3.getScore());

		ConfigurationsAndroid.killDriver();
	}

}