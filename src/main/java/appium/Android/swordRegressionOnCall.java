package appium.Android;

import java.io.File;
import java.net.MalformedURLException;
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
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThan;

public class swordRegressionOnCall {

	private final static String VALIDATION_PATH = ConfigurationsAndroid.VALIDATION_PATH;
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

	private AndroidDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsAndroid.getDriver();
	}

	@Test
	public void preventive() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActions mobileActions = new MobileActions(driver);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		VisualCheck visualCheck = new VisualCheck(driver);
		
		//login
		utilitiesAndroid.login("luiza.preventive@sword.com", "Test1234!", driver);
		//validar hub screen
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//android.widget.TextView[@text='On-Call']");
		driver.findElementByXPath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']");
		driver.findElementByXPath("//android.widget.TextView[@text='Chat with a Specialist']");
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Explore our programs']")).size() > 0) {
			driver.findElementByXPath("//android.widget.TextView[@text='Explore our programs']");
			driver.findElementByXPath("//android.widget.TextView[@text='Learn more about our programs below to find the best fit for you.']");
			driver.findElementByXPath("//android.widget.TextView[@text='Digital Physical Therapy']");
			driver.findElementByXPath("//android.widget.TextView[@text='Physical therapy you can do anytime, anywhere.']");
			driver.findElementByXPath("//android.widget.TextView[@text='Eligible']");
			driver.findElementByXPath("//android.widget.TextView[@text='Get started']");
			//clicar enroll now
			utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_0_get_started_button", driver);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Hooray! Your enrollment is complete.']")));
			//voltar
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.Button", driver);
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
		utilitiesAndroid.clickByAccessibilityId("bottom_navigation_academy_tab", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Academy']")));
		//voltar pra hub tab
		utilitiesAndroid.clickByAccessibilityId("bottom_navigation_hub_tab", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//clicar on-call card
		utilitiesAndroid.clickByAccessibilityId("home_on_call_card_help_from_specialist_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		//validar triage screen
		driver.findElementByXPath("//android.widget.TextView[@text='Chat with a Pain Specialist']");
		driver.findElementByXPath("//android.widget.TextView[@text='Physical Pain']");
		driver.findElementByXPath("//android.widget.TextView[@text='Joint and muscle pain']");
		driver.findElementByXPath("//android.widget.TextView[@text='Chat with a Headache Specialist']");
		driver.findElementByXPath("//android.widget.TextView[@text='Headaches']");
		driver.findElementByXPath("//android.widget.TextView[@text='Tension headaches and migraines']");
		driver.findElementByXPath("//android.widget.TextView[@text='Chat with a Pelvic Specialist']");
		driver.findElementByXPath("//android.widget.TextView[@text='Pelvic Health']");
		driver.findElementByXPath("//android.widget.TextView[@text='Pelvic, bladder, and bowel function']");
		String terms = driver.findElementByAccessibilityId("on_call_terms_conditions").getText();
		Assert.assertEquals("This practitioner is not intended to be a substitute for professional medical advice, diagnosis or treatment. Read Terms & Conditions", terms);
		visualCheck.doVisualCheck(CHECK_TRIAGE_SCREEN);
		//abrir terms and conditions
		utilitiesAndroid.clickByAccessibilityId("on_call_terms_conditions", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		//validar ecrã terms and conditions
		MobileElement termsOnCallTxt = driver.findElementByXPath("//android.widget.TextView[@text='On-Call']");
		MobileElement termsTxt1 = driver.findElementByXPath("//android.widget.TextView[2]");
		mobileActions.swipeByElements(termsTxt1, termsOnCallTxt);
		driver.findElementByXPath("//android.widget.TextView[@text='Terms & Conditions']");
		String termsTxt2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
		Assert.assertEquals("All information provided by the On-call clinical pain specialist is for informational purposes only. Such information is not intended to be and should not be used as a substitute for professional medical advice, diagnosis or treatment. The On-call clinical pain specialist will not be able to provide a diagnosis, write you a prescription, or provide a plan of care or treatment. If you choose to rely on any information provided by the On-call clinical pain specialist, you rely solely at your own risk.\n"
				+ "\n"
				+ "Always seek the advice of your physician or other qualified healthcare provider for any health questions or concerns you may have regarding your medical condition or treatment. If you have or think you may have a medical emergency, need urgent or emergency care, please call your doctor, go to the nearest hospital emergency department, or call 911 immediately. Neither Sword Health, Inc. nor any of its affiliates and contracted entities assumes responsibility or liability for any damages, claims, liabilities, costs or obligations arising from the use of this app or any On-call clinical pain specialist.", termsTxt2);
		visualCheck.doVisualCheck(CHECK_TERMS_CONDITIONS);
		//voltar até a hub screen
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//print do hub screen sem on-call chat ativo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		byte[] hubWithoutChat = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//abrir physical pain chat
		utilitiesAndroid.clickByAccessibilityId("home_on_call_card", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		utilitiesAndroid.clickByAccessibilityId("on_call_physical_pain_card", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
		visualCheck.doVisualCheck(CHECK_PHYSICAL_PAIN_EMPTY_SCREEN);
		//voltar sem enviar mensagem
		utilitiesAndroid.clickByXPath("//android.widget.ImageButton", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//abrir triage screen de novo
		utilitiesAndroid.clickByAccessibilityId("home_on_call_card_help_from_specialist_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		//abrir headaches chat
		utilitiesAndroid.clickByAccessibilityId("on_call_headaches_card_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
		//enviar mensagem
		MobileElement sendMessage = driver.findElementByXPath("//android.widget.EditText");
		sendMessage.click();
		sendMessage.sendKeys("Tests. Please ignore");
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.ImageButton[2]", driver);
		mobileActions.tapByCoordinates(518, 783);
		visualCheck.doVisualCheck(CHECK_HEADACHES_MESSAGE_SENT_SCREEN);
		//voltar
		utilitiesAndroid.clickByXPath("//android.widget.ImageButton", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		//validar on-call card novo
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
		utilitiesAndroid.clickByAccessibilityId("home_on_call_card", driver);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//voltar
		utilitiesAndroid.clickByXPath("//android.widget.ImageButton", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//fazer logout
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		utilitiesAndroid.clickByAccessibilityId("menu_option_logout", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Login']")));
		//fazer login no mesmo user de novo
		utilitiesAndroid.login("luiza.preventive@sword.com", "Test1234!", driver);
		//validar que ainda mostra o mesmo on-call card
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		visualCheck.doVisualCheck(CHECK_HUB_WITH_OPEN_CHAT_AFTER_LOGIN);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']")));
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
	
		ConfigurationsAndroid.killDriver();
	}

	@Test
	public void virtualPT() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActions mobileActions = new MobileActions(driver);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		VisualCheck visualCheck = new VisualCheck(driver);
		
		//login
		utilitiesAndroid.login("vinteum@sword.com", "Test1234!", driver);
		//abrir hub screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Pending actions']")));
		utilitiesAndroid.clickByAccessibilityId("bottom_navigation_hub_tab", driver);
		//validar hub screen
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//android.widget.TextView[@text='On-Call']");
		driver.findElementByXPath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']");
		driver.findElementByXPath("//android.widget.TextView[@text='Chat with a Specialist']");
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Explore our programs']")).size() > 0) {
			driver.findElementByXPath("//android.widget.TextView[@text='Explore our programs']");
			driver.findElementByXPath("//android.widget.TextView[@text='Learn more about our programs below to find the best fit for you.']");
			driver.findElementByXPath("//android.widget.TextView[@text='Digital Physical Therapy']");
			driver.findElementByXPath("//android.widget.TextView[@text='Physical therapy you can do anytime, anywhere.']");
			driver.findElementByXPath("//android.widget.TextView[@text='Eligible']");
			driver.findElementByXPath("//android.widget.TextView[@text='Get started']");
			//clicar enroll now
			utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_0_get_started_button", driver);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Hooray! Your enrollment is complete.']")));
			//voltar
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.widget.Button", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
			visualCheck.doVisualCheck(CHECK_HUB_WITH_PROGRAMS_SCREEN);
		} else {
			visualCheck.doVisualCheck(CHECK_HUB_WITHOUT_PROGRAMS_SCREEN);
		}
		//clicar on-call card
		utilitiesAndroid.clickByAccessibilityId("home_on_call_card_help_from_specialist_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		//validar triage screen
		driver.findElementByXPath("//android.widget.TextView[@text='Chat with a Pain Specialist']");
		driver.findElementByXPath("//android.widget.TextView[@text='Physical Pain']");
		driver.findElementByXPath("//android.widget.TextView[@text='Joint and muscle pain']");
		driver.findElementByXPath("//android.widget.TextView[@text='Chat with a Headache Specialist']");
		driver.findElementByXPath("//android.widget.TextView[@text='Headaches']");
		driver.findElementByXPath("//android.widget.TextView[@text='Tension headaches and migraines']");
		driver.findElementByXPath("//android.widget.TextView[@text='Chat with a Pelvic Specialist']");
		driver.findElementByXPath("//android.widget.TextView[@text='Pelvic Health']");
		driver.findElementByXPath("//android.widget.TextView[@text='Pelvic, bladder, and bowel function']");
		String terms = driver.findElementByAccessibilityId("on_call_terms_conditions").getText();
		Assert.assertEquals("This practitioner is not intended to be a substitute for professional medical advice, diagnosis or treatment. Read Terms & Conditions", terms);
		visualCheck.doVisualCheck(CHECK_TRIAGE_SCREEN);
		//abrir terms and conditions
		utilitiesAndroid.clickByAccessibilityId("on_call_terms_conditions", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		//validar ecrã terms and conditions
		MobileElement termsOnCallTxt = driver.findElementByXPath("//android.widget.TextView[@text='On-Call']");
		MobileElement termsTxt1 = driver.findElementByXPath("//android.widget.TextView[2]");
		mobileActions.swipeByElements(termsTxt1, termsOnCallTxt);
		driver.findElementByXPath("//android.widget.TextView[@text='Terms & Conditions']");
		String termsTxt2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
		Assert.assertEquals("All information provided by the On-call clinical pain specialist is for informational purposes only. Such information is not intended to be and should not be used as a substitute for professional medical advice, diagnosis or treatment. The On-call clinical pain specialist will not be able to provide a diagnosis, write you a prescription, or provide a plan of care or treatment. If you choose to rely on any information provided by the On-call clinical pain specialist, you rely solely at your own risk.\n"
				+ "\n"
				+ "Always seek the advice of your physician or other qualified healthcare provider for any health questions or concerns you may have regarding your medical condition or treatment. If you have or think you may have a medical emergency, need urgent or emergency care, please call your doctor, go to the nearest hospital emergency department, or call 911 immediately. Neither Sword Health, Inc. nor any of its affiliates and contracted entities assumes responsibility or liability for any damages, claims, liabilities, costs or obligations arising from the use of this app or any On-call clinical pain specialist.", termsTxt2);
		visualCheck.doVisualCheck(CHECK_TERMS_CONDITIONS);
		//voltar até a hub screen
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//print do hub screen sem on-call chat ativo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		byte[] hubWithoutChat = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//abrir headaches chat
		utilitiesAndroid.clickByAccessibilityId("home_on_call_card", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		utilitiesAndroid.clickByAccessibilityId("on_call_headaches_card", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
		visualCheck.doVisualCheck(CHECK_HEADACHES_EMPTY_SCREEN);
		//voltar sem enviar mensagem
		utilitiesAndroid.clickByXPath("//android.widget.ImageButton", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//abrir triage screen de novo
		utilitiesAndroid.clickByAccessibilityId("home_on_call_card_help_from_specialist_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		//abrir pelvic chat
		utilitiesAndroid.clickByAccessibilityId("on_call_pelvic_health_card_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
		//enviar mensagem
		MobileElement sendMessage = driver.findElementByXPath("//android.widget.EditText");
		sendMessage.click();
		sendMessage.sendKeys("Tests. Please ignore");
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.ImageButton[2]", driver);
		mobileActions.tapByCoordinates(518, 783);
		visualCheck.doVisualCheck(CHECK_PELVIC_HEALTH_MESSAGE_SENT_SCREEN);
		//voltar
		utilitiesAndroid.clickByXPath("//android.widget.ImageButton", driver);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']")));
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
		utilitiesAndroid.clickByAccessibilityId("home_on_call_card", driver);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//voltar
		utilitiesAndroid.clickByXPath("//android.widget.ImageButton", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//fazer logout
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		utilitiesAndroid.clickByAccessibilityId("menu_option_logout", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Login']")));
		//fazer login no mesmo user de novo
		utilitiesAndroid.login("vinteum@sword.com", "Test1234!", driver);
		//validar que ainda mostra o mesmo on-call card
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Pending actions']")));
		utilitiesAndroid.clickByAccessibilityId("bottom_navigation_hub_tab", driver);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']")));
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
		utilitiesAndroid.clickByAccessibilityId("home_on_call_card", driver);
		driver.findElementById("com.swordhealth.guarda.dev:id/ibtnAdd").click();
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]", driver);
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.drawerlayout.widget.DrawerLayout/android.widget.ScrollView/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.cardview.widget.CardView[1]/androidx.cardview.widget.CardView/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.ImageView", driver);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Tests. Please ignore']")));
		File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile3, new File("screen3.jpg"));
		utilitiesAndroid.clickByXPath("//android.widget.ImageButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));

	}

	@Test
	public void onCallTab() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActions mobileActions = new MobileActions(driver);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		VisualCheck visualCheck = new VisualCheck(driver);

		//login
		System.out.println("LIGOU O PROXY??");
		utilitiesAndroid.login("luiza.preventive@sword.com", "Test1234!", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Academy']")));
		utilitiesAndroid.clickByAccessibilityId("bottom_navigation_on_call_tab", driver);
		//validar on-call screen
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//android.widget.TextView[@text='On-Call']");
		driver.findElementByXPath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']");
		driver.findElementByXPath("//android.widget.TextView[@text='Physical Pain']");
		driver.findElementByXPath("//android.widget.TextView[@text='Joint and muscle pain']");
		driver.findElementByXPath("//android.widget.TextView[@text='Chat with a Pain Specialist']");
		driver.findElementByXPath("//android.widget.TextView[@text='Headaches']");
		driver.findElementByXPath("//android.widget.TextView[@text='Tension headaches and migraines']");
		driver.findElementByXPath("//android.widget.TextView[@text='Chat with a Headache Specialist']");
		driver.findElementByXPath("//android.widget.TextView[@text='Pelvic Health']");
		driver.findElementByXPath("//android.widget.TextView[@text='Pelvic, bladder, and bowel function']");
		driver.findElementByXPath("//android.widget.TextView[@text='Chat with a Pelvic Specialist']");
		visualCheck.doVisualCheck(CHECK_ON_CALL_NO_CHAT_SCREEN);
		byte[] onCallFirstOpen = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		MobileElement pelvicHealthTxt = driver.findElementByXPath("//android.widget.TextView[@text='Pelvic Health']");
		MobileElement headachesTxt = driver.findElementByXPath("//android.widget.TextView[@text='Headaches']");
		mobileActions.swipeByElements(pelvicHealthTxt, headachesTxt);
		driver.findElementByXPath("//android.widget.TextView[@text='This practitioner is not intended to be a substitute for professional medical advice, diagnosis or treatment. Read Terms & Conditions']");
		utilitiesAndroid.clickByAccessibilityId("on_call_terms_conditions", driver);
		//validar terms and conditions
		MobileElement termsOnCallTxt = driver.findElementByXPath("//android.widget.TextView[@text='On-Call']");
		MobileElement termsTxt1 = driver.findElementByXPath("//android.widget.TextView[2]");
		mobileActions.swipeByElements(termsTxt1, termsOnCallTxt);
		driver.findElementByXPath("//android.widget.TextView[@text='Terms & Conditions']");
		String termsTxt2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
		Assert.assertEquals("All information provided by the On-call clinical pain specialist is for informational purposes only. Such information is not intended to be and should not be used as a substitute for professional medical advice, diagnosis or treatment. The On-call clinical pain specialist will not be able to provide a diagnosis, write you a prescription, or provide a plan of care or treatment. If you choose to rely on any information provided by the On-call clinical pain specialist, you rely solely at your own risk.\n"
				+ "\n"
				+ "Always seek the advice of your physician or other qualified healthcare provider for any health questions or concerns you may have regarding your medical condition or treatment. If you have or think you may have a medical emergency, need urgent or emergency care, please call your doctor, go to the nearest hospital emergency department, or call 911 immediately. Neither Sword Health, Inc. nor any of its affiliates and contracted entities assumes responsibility or liability for any damages, claims, liabilities, costs or obligations arising from the use of this app or any On-call clinical pain specialist.", termsTxt2);
		visualCheck.doVisualCheck(CHECK_TERMS_CONDITIONS);
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		//abrir chat
		utilitiesAndroid.clickByAccessibilityId("on_call_physical_pain_card", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		visualCheck.doVisualCheck(CHECK_PHYSICAL_PAIN_EMPTY_SCREEN);
		//voltar sem mandar mensagem
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.ImageButton", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//comparação visual pra ver se voltou com o mesmo estado
		byte[] onCallBackWithoutMessage = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result1 = driver
				.getImagesSimilarity(onCallFirstOpen, onCallBackWithoutMessage, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result1.getVisualization().length, is(greaterThan(0)));
		assertThat(result1.getScore(), is(greaterThan(0.95)));
		String baselineFilename = VALIDATION_PATH + "/" + BASELINE + "no_messages.png";
		File comparison = new File(baselineFilename);
		result1.storeVisualization(comparison);
		System.out.println("Similarity of: " + result1.getScore());
		//abrir chat e enviar mensagem
		utilitiesAndroid.clickByAccessibilityId("on_call_pelvic_health_card_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
		MobileElement sendMessage = driver.findElementByXPath("//android.widget.EditText");
		sendMessage.click();
		sendMessage.sendKeys("Test. Please ignore");
		utilitiesAndroid.clickByXPath("//android.widget.ImageButton[2]", driver);
		mobileActions.tapByCoordinates(190, 773);
		//comparação visual de chat com mensagem enviada
		visualCheck.doVisualCheck(CHECK_PELVIC_HEALTH_MESSAGE_SENT_SCREEN);
		//voltar
		utilitiesAndroid.clickByXPath("//android.widget.ImageButton", driver);
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
//		assertThat(result2.getScore(), is(greaterThan(0.90)));
		result2.storeVisualization(comparison2);
		System.out.println("Similarity of: " + result2.getScore());
		//clicar na conversa aberta
		utilitiesAndroid.clickByAccessibilityId("on_call_pelvic_health_card", driver);
		//voltar
		utilitiesAndroid.clickByXPath("//android.widget.ImageButton", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		//tentar abrir outra conversa
		utilitiesAndroid.clickByAccessibilityId("on_call_headaches_card", driver);
		//validar bottom sheet
		driver.findElementByXPath("//android.widget.TextView[@text='You already have an open chat']");
		driver.findElementByXPath("//android.widget.TextView[@text='It looks like you have an open chat with a Pelvic health specialist. Once this conversation is closed, you can start a new one!']");
		driver.findElementByXPath("//android.widget.TextView[@text='Close']");
		visualCheck.doVisualCheck(CHECK_BOTTOM_SHEET);
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View/android.widget.Button", driver);
		//fazer logout
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		utilitiesAndroid.clickByAccessibilityId("menu_option_logout", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Login']")));
		//fazer login no mesmo user de novo
		utilitiesAndroid.login("luiza.preventive@sword.com", "Test1234!", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Academy']")));
		utilitiesAndroid.clickByAccessibilityId("bottom_navigation_on_call_tab", driver);
		//validar que ainda mostra o mesmo chat aberto
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		byte[] onCallWithMessageAfterLogin = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result3 = driver
				.getImagesSimilarity(onCallWithMessage, onCallWithMessageAfterLogin, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result3.getVisualization().length, is(greaterThan(0)));
		assertThat(result3.getScore(), is(greaterThan(0.95)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "after_login" + ".png";
		File comparison3 = new File(baselineFilename);
		result3.storeVisualization(comparison3);
		System.out.println("Similarity of: " + result3.getScore());

		ConfigurationsAndroid.killDriver();
	}

}