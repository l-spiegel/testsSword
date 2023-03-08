package appium.Android;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import appium.MobileActions;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.CoreMatchers.*;


public class swordRegressionOnCall {
	
	private final static String VALIDATION_PATH = "/Users/luizaspiegel/Documents/image check";
	private final static String BASELINE = "BASELINE_";

	private AndroidDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsAndroid.getDriver();
	}

	private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
		// TODO Auto-generated method stub
	}

	
	@Test
	public void preventive() throws IOException {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActions mobileActions = new MobileActions(driver);
		
		//login
		MobileElement el1 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[1]");
		el1.clear();
		el1.sendKeys("luiza.preventive@sword.com");
		MobileElement el2 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el2.click();
		el2.sendKeys("Test1234!");
		driver.findElementByAccessibilityId("loginButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		driver.findElementByXPath("//android.widget.TextView[@text='Not now']").click();
		//validar hub screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		String onCallTitle = driver.findElementByXPath("//android.view.View[@content-desc='home_on_call_card']/android.widget.TextView[1]").getText();
		String onCallLabel1 = driver.findElementByXPath("//android.view.View[@content-desc='home_on_call_card']/android.widget.TextView[2]").getText();
		String onCallButton = driver.findElementByXPath("//android.view.View[@content-desc='home_on_call_card']/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("On-Call", onCallTitle);
		Assert.assertEquals("Get on-demand support from a Clinical Pain Specialist via chat.", onCallLabel1);
		Assert.assertEquals("Chat with a Specialist", onCallButton);
		String swordPrograms = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView").getText();
		Assert.assertEquals("Sword Programs", swordPrograms);
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Digital Physical Therapy']")).size() > 0) { //ver se em Android consigo pegar o título do card pra poder validar o que tem dentro, já que em algum futuro teremos mais cards além do DPT
			String dptTitle = driver.findElementByXPath("//android.view.View[@content-desc='on_call_programs_card_0']/android.widget.TextView[1]").getText();
			String dptLabel = driver.findElementByXPath("//android.view.View[@content-desc='on_call_programs_card_0_label']/android.widget.TextView").getText();
			String dptButton = driver.findElementByXPath("//android.view.View[@content-desc='on_call_programs_card_0']/android.view.View[2]/android.widget.TextView").getText();
			Assert.assertEquals("Digital Physical Therapy", dptTitle);
			Assert.assertEquals("Recover from pain or injury with support from a dedicated Physical Therapist.", dptLabel);
			Assert.assertEquals("Enroll now", dptButton);
		}
		//abrir academy tab
		MobileElement academyTab = (MobileElement) driver.findElementByAccessibilityId("bottom_navigation_academy_tab");
		academyTab.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Academy']")));
		//voltar pra hub tab
		MobileElement hubTab = (MobileElement) driver.findElementByAccessibilityId("bottom_navigation_hub_tab");
		hubTab.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//clicar enroll now
		MobileElement enrollButton = (MobileElement) driver.findElementByAccessibilityId("on_call_programs_card_0_button");
		enrollButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Sword']")));
		//voltar
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//clicar on-call card
		driver.findElementByAccessibilityId("home_on_call_card_help_from_specialist_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		//validar triage screen
		String painChatButton = driver.findElementByXPath("//android.view.View[@content-desc='on_call_physical_pain_card']/android.view.View[2]/android.widget.TextView").getText();
		String painChatTitle = driver.findElementByXPath("//android.view.View[@content-desc='on_call_physical_pain_card']/android.widget.TextView[1]").getText();
		String painChatLabel = driver.findElementByXPath("//android.view.View[@content-desc='on_call_physical_pain_card']/android.widget.TextView[2]").getText();
		String headacheChatButton = driver.findElementByXPath("//android.view.View[@content-desc='on_call_headaches_card']/android.view.View[2]/android.widget.TextView").getText();
		String headacheChatTitle = driver.findElementByXPath("//android.view.View[@content-desc='on_call_headaches_card']/android.widget.TextView[1]").getText();
		String headacheChatLabel = driver.findElementByXPath("//android.view.View[@content-desc='on_call_headaches_card']/android.widget.TextView[2]").getText();
		String pelvicChatButton = driver.findElementByXPath("//android.view.View[@content-desc='on_call_pelvic_health_card']/android.view.View[2]/android.widget.TextView").getText();
		String pelvicChatTitle = driver.findElementByXPath("//android.view.View[@content-desc='on_call_pelvic_health_card']/android.widget.TextView[1]").getText();
		String pelvicChatLabel = driver.findElementByXPath("//android.view.View[@content-desc='on_call_pelvic_health_card']/android.widget.TextView[2]").getText();
		Assert.assertEquals("Chat with a Pain Specialist", painChatButton);
		Assert.assertEquals("Physical Pain", painChatTitle);
		Assert.assertEquals("Joint and muscle pain", painChatLabel);
		Assert.assertEquals("Chat with a Headache Specialist", headacheChatButton);
		Assert.assertEquals("Headaches", headacheChatTitle);
		Assert.assertEquals("Tension headaches and migraines", headacheChatLabel);
		Assert.assertEquals("Chat with a Pelvic Specialist", pelvicChatButton);
		Assert.assertEquals("Pelvic Health", pelvicChatTitle);
		Assert.assertEquals("Pelvic, bladder, and bowel function", pelvicChatLabel);
		String terms = driver.findElementByAccessibilityId("on_call_terms_conditions").getText();
		Assert.assertEquals("This practitioner is not intended to be a substitute for professional medical advice, diagnosis or treatment. Read Terms & Conditions", terms);
		//abrir terms and conditions
		driver.findElementByAccessibilityId("on_call_terms_conditions").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		//validar ecrã terms and conditions
		MobileElement termsOnCallTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='On-Call']");
		MobileElement termsTxt1 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[2]");
		mobileActions.swipeByElements(termsTxt1, termsOnCallTxt);
		String termsHeader = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.TextView").getText();
		Assert.assertEquals("Terms & Conditions", termsHeader);
		String termsTxt2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
		Assert.assertEquals("All information provided by the On-call clinical pain specialist is for informational purposes only. Such information is not intended to be and should not be used as a substitute for professional medical advice, diagnosis or treatment. The On-call clinical pain specialist will not be able to provide a diagnosis, write you a prescription, or provide a plan of care or treatment. If you choose to rely on any information provided by the On-call clinical pain specialist, you rely solely at your own risk.\n"
				+ "\n"
				+ "Always seek the advice of your physician or other qualified healthcare provider for any health questions or concerns you may have regarding your medical condition or treatment. If you have or think you may have a medical emergency, need urgent or emergency care, please call your doctor, go to the nearest hospital emergency department, or call 911 immediately. Neither Sword Health, Inc. nor any of its affiliates and contracted entities assumes responsibility or liability for any damages, claims, liabilities, costs or obligations arising from the use of this app or any On-call clinical pain specialist.", termsTxt2);
		//voltar até a hub screen
		driver.findElementByXPath("//android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.view.View/android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//print do hub screen sem on-call chat ativo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("screen1.jpg"));
		byte[] screenshot1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//abrir physical pain chat
		driver.findElementByAccessibilityId("home_on_call_card").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		driver.findElementByAccessibilityId("on_call_physical_pain_card").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
		//voltar sem enviar mensagem
		driver.findElementByXPath("//android.widget.ImageButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//abrir triage screen de novo
		driver.findElementByAccessibilityId("home_on_call_card_help_from_specialist_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		//abrir headaches chat
		driver.findElementByAccessibilityId("on_call_headaches_card_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
		//enviar mensagem
		MobileElement el11 = (MobileElement) driver.findElementByXPath("//android.widget.EditText");
		el11.click();
		el11.sendKeys("Tests. Please ignore");
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.ImageButton[2]").click();
		mobileActions.tapByCoordinates(518, 783);
		//voltar
		driver.findElementByXPath("//android.widget.ImageButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		//validar on-call card novo
		File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile2, new File("screen2.jpg"));
		byte[] screenshot2 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result = driver
		        .getImagesSimilarity(screenshot1, screenshot2, new SimilarityMatchingOptions()
		                .withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.0)));
		String baselineFilename = VALIDATION_PATH + "/" + BASELINE + ".png";
        File comparison = new File(baselineFilename);
        result.storeVisualization(comparison);
        System.out.println("Similarity of: " + result.getScore());
		//clicar no on-call card
		driver.findElementByAccessibilityId("home_on_call_card").click();
		//voltar
		driver.findElementByXPath("//android.widget.ImageButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//fazer logout
		driver.findElementByAccessibilityId("header_menu_button").click();
		driver.findElementByAccessibilityId("menu_option_logout").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Login']")));
		//fazer login no mesmo user de novo
		MobileElement el14 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el14.click();
		el14.sendKeys("Test1234!");
		driver.findElementByAccessibilityId("loginButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		driver.findElementByXPath("//android.widget.TextView[@text='Not now']").click();
		//validar que ainda mostra o mesmo on-call card
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		byte[] screenshot3 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result2 = driver
		        .getImagesSimilarity(screenshot2, screenshot3, new SimilarityMatchingOptions()
		                .withEnabledVisualization());
		assertThat(result2.getVisualization().length, is(greaterThan(0)));
		assertThat(result2.getScore(), is(greaterThan(0.90)));
		String baselineFilename2 = VALIDATION_PATH + "/" + BASELINE + "2" + ".png";
        File comparison2 = new File(baselineFilename2);
        result2.storeVisualization(comparison2);
		System.out.println("Similarity of: " + result2.getScore());

		ConfigurationsAndroid.killDriver();
	}

	@Test
	public void virtualPT() throws IOException {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActions mobileActions = new MobileActions(driver);
		
		//login
		MobileElement el1 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[1]");
		el1.clear();
		el1.sendKeys("l.spiegel+3@swordhealth.com");
		MobileElement el2 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el2.click();
		el2.sendKeys("Test1234!");
		driver.findElementByAccessibilityId("loginButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		driver.findElementByXPath("//android.widget.TextView[@text='Not now']").click();
		//abrir hub screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Weekly goal']")));
		driver.findElementByAccessibilityId("bottom_navigation_hub_tab").click();
		//validar hub screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		String onCallTitle = driver.findElementByXPath("//android.view.View[@content-desc='home_on_call_card']/android.widget.TextView[1]").getText();
		String onCallLabel1 = driver.findElementByXPath("//android.view.View[@content-desc='home_on_call_card']/android.widget.TextView[2]").getText();
		String onCallButton = driver.findElementByXPath("//android.view.View[@content-desc='home_on_call_card']/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("On-Call", onCallTitle);
		Assert.assertEquals("Get on-demand support from a Clinical Pain Specialist via chat.", onCallLabel1);
		Assert.assertEquals("Chat with a Specialist", onCallButton);
		//clicar on-call card
		driver.findElementByAccessibilityId("home_on_call_card_help_from_specialist_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		//validar triage screen
		String painChatButton = driver.findElementByXPath("//android.view.View[@content-desc='on_call_physical_pain_card']/android.view.View[2]/android.widget.TextView").getText();
		String painChatTitle = driver.findElementByXPath("//android.view.View[@content-desc='on_call_physical_pain_card']/android.widget.TextView[1]").getText();
		String painChatLabel = driver.findElementByXPath("//android.view.View[@content-desc='on_call_physical_pain_card']/android.widget.TextView[2]").getText();
		String headacheChatButton = driver.findElementByXPath("//android.view.View[@content-desc='on_call_headaches_card']/android.view.View[2]/android.widget.TextView").getText();
		String headacheChatTitle = driver.findElementByXPath("//android.view.View[@content-desc='on_call_headaches_card']/android.widget.TextView[1]").getText();
		String headacheChatLabel = driver.findElementByXPath("//android.view.View[@content-desc='on_call_headaches_card']/android.widget.TextView[2]").getText();
		String pelvicChatButton = driver.findElementByXPath("//android.view.View[@content-desc='on_call_pelvic_health_card']/android.view.View[2]/android.widget.TextView").getText();
		String pelvicChatTitle = driver.findElementByXPath("//android.view.View[@content-desc='on_call_pelvic_health_card']/android.widget.TextView[1]").getText();
		String pelvicChatLabel = driver.findElementByXPath("//android.view.View[@content-desc='on_call_pelvic_health_card']/android.widget.TextView[2]").getText();
		Assert.assertEquals("Chat with a Pain Specialist", painChatButton);
		Assert.assertEquals("Physical Pain", painChatTitle);
		Assert.assertEquals("Joint and muscle pain", painChatLabel);
		Assert.assertEquals("Chat with a Headache Specialist", headacheChatButton);
		Assert.assertEquals("Headaches", headacheChatTitle);
		Assert.assertEquals("Tension headaches and migraines", headacheChatLabel);
		Assert.assertEquals("Chat with a Pelvic Specialist", pelvicChatButton);
		Assert.assertEquals("Pelvic Health", pelvicChatTitle);
		Assert.assertEquals("Pelvic, bladder, and bowel function", pelvicChatLabel);
		String terms = driver.findElementByAccessibilityId("on_call_terms_conditions").getText();
		Assert.assertEquals("This practitioner is not intended to be a substitute for professional medical advice, diagnosis or treatment. Read Terms & Conditions", terms);
		//abrir terms and conditions
		driver.findElementByAccessibilityId("on_call_terms_conditions").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		//validar ecrã terms and conditions
		MobileElement termsOnCallTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='On-Call']");
		MobileElement termsTxt1 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[2]");
		mobileActions.swipeByElements(termsTxt1, termsOnCallTxt);
		String termsHeader = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.TextView").getText();
		Assert.assertEquals("Terms & Conditions", termsHeader);
		String termsTxt2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
		Assert.assertEquals("All information provided by the On-call clinical pain specialist is for informational purposes only. Such information is not intended to be and should not be used as a substitute for professional medical advice, diagnosis or treatment. The On-call clinical pain specialist will not be able to provide a diagnosis, write you a prescription, or provide a plan of care or treatment. If you choose to rely on any information provided by the On-call clinical pain specialist, you rely solely at your own risk.\n"
				+ "\n"
				+ "Always seek the advice of your physician or other qualified healthcare provider for any health questions or concerns you may have regarding your medical condition or treatment. If you have or think you may have a medical emergency, need urgent or emergency care, please call your doctor, go to the nearest hospital emergency department, or call 911 immediately. Neither Sword Health, Inc. nor any of its affiliates and contracted entities assumes responsibility or liability for any damages, claims, liabilities, costs or obligations arising from the use of this app or any On-call clinical pain specialist.", termsTxt2);
		//voltar até a hub screen
		driver.findElementByXPath("//android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		driver.findElementByXPath("//android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//print do hub screen sem on-call chat ativo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("screen1.jpg"));
		byte[] screenshot1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//abrir headaches chat
		driver.findElementByAccessibilityId("home_on_call_card").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		driver.findElementByAccessibilityId("on_call_headaches_card").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
		//voltar sem enviar mensagem
		driver.findElementByXPath("//android.widget.ImageButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//abrir triage screen de novo
		driver.findElementByAccessibilityId("home_on_call_card_help_from_specialist_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='On-Call']")));
		//abrir pelvic chat
		driver.findElementByAccessibilityId("on_call_pelvic_health_card_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
		//enviar mensagem
		MobileElement el11 = (MobileElement) driver.findElementByXPath("//android.widget.EditText");
		el11.click();
		el11.sendKeys("Tests. Please ignore");
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.ImageButton[2]").click();
		mobileActions.tapByCoordinates(518, 783);
		//voltar
		driver.findElementByXPath("//android.widget.ImageButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		//validar on-call card novo
		File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile2, new File("screen2.jpg"));
		byte[] screenshot2 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result = driver
		        .getImagesSimilarity(screenshot1, screenshot2, new SimilarityMatchingOptions()
		                .withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.0)));
		String baselineFilename = VALIDATION_PATH + "/" + BASELINE + ".png";
        File comparison = new File(baselineFilename);
        result.storeVisualization(comparison);
        System.out.println("Similarity of: " + result.getScore());
		//clicar no on-call card
		driver.findElementByAccessibilityId("home_on_call_card").click();
		//voltar
		driver.findElementByXPath("//android.widget.ImageButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));
		//fazer logout
		driver.findElementByAccessibilityId("header_menu_button").click();
		driver.findElementByAccessibilityId("menu_option_logout").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Login']")));
		//fazer login no mesmo user de novo
		MobileElement el14 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el14.click();
		el14.sendKeys("Test1234!");
		driver.findElementByAccessibilityId("loginButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		driver.findElementByXPath("//android.widget.TextView[@text='Not now']").click();
		//validar que ainda mostra o mesmo on-call card
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Weekly goal']")));
		driver.findElementByAccessibilityId("bottom_navigation_hub_tab").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		byte[] screenshot3 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result2 = driver
		        .getImagesSimilarity(screenshot2, screenshot3, new SimilarityMatchingOptions()
		                .withEnabledVisualization());
		assertThat(result2.getVisualization().length, is(greaterThan(0)));
		assertThat(result2.getScore(), is(greaterThan(0.95)));
		String baselineFilename2 = VALIDATION_PATH + "/" + BASELINE + "2" + ".png";
        File comparison2 = new File(baselineFilename2);
        result2.storeVisualization(comparison2);
		System.out.println("Similarity of: " + result2.getScore());
		//enviar uma foto do rolo
		driver.findElementByAccessibilityId("home_on_call_card").click();
		driver.findElementById("com.swordhealth.guarda.dev:id/ibtnAdd").click();
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]").click();
		driver.findElementByXPath("//android.widget.FrameLayout[@content-desc='Photo taken on Mar 7, 2023, 4:32:23 PM']/androidx.cardview.widget.CardView/android.widget.FrameLayout/android.widget.ImageView").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Tests. Please ignore']")));
		File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile3, new File("screen3.jpg"));
		driver.findElementByXPath("//android.widget.ImageButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_on_call_card']")));

		ConfigurationsAndroid.killDriver();
	}

}