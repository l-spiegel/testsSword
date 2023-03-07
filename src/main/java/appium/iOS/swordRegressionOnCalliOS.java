package appium.iOS;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
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


public class swordRegressionOnCalliOS {
	private IOSDriver driver;
	
	private final static String VALIDATION_PATH = "/Users/luizaspiegel/Desktop/visualizations";
	private final static String BASELINE = "BASELINE_";


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
		capabilities.setCapability("app", "/Users/luizaspiegel/Downloads/SWORDHealthRelease430-117.ipa");
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
	public void preventive() throws IOException {
		IOSDriver<MobileElement> driver = inicializarAppium();
		
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		//login
		driver.findElementByAccessibilityId("Allow").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
	//	MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("loginEmailTextfield");
	//	el1.clear();
	//	el1.sendKeys("frodobaggins@sword.com");
		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el2.click();
		el2.sendKeys("28Abril!");
		MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el3.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el4 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not now']");
		el4.click();
		//validar hub screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		String onCallTitle = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_on_call_card']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]").getText();
		String onCallLabel1 = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_on_call_card']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]").getText();
		String onCallButton = driver.findElementByAccessibilityId("home_on_call_card_help_from_specialist_button").getText();
		Assert.assertEquals("On-Call", onCallTitle);
		Assert.assertEquals("Get on-demand support from a Clinical Pain Specialist via chat.", onCallLabel1);
		Assert.assertEquals("Chat with a Specialist", onCallButton);
		String swordPrograms = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sword Programs']").getText();
		Assert.assertEquals("Sword Programs", swordPrograms);
		if (driver.findElements(By.id("on_call_programs_card_0")).size() > 0) { //ver se em Android consigo pegar o título do card pra poder validar o que tem dentro, já que em algum futuro teremos mais cards além do DPT
			String dptLabel = driver.findElementByAccessibilityId("on_call_programs_card_0_label").getText();
			Assert.assertEquals("Recover from pain or injury with support from a dedicated Physical Therapist.", dptLabel);
		}
		//abrir academy tab
		MobileElement academyTab = (MobileElement) driver.findElementByAccessibilityId("bottom_navigation_academy_tab");
		academyTab.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Academy']")));
		//voltar pra hub tab
		MobileElement hubTab = (MobileElement) driver.findElementByAccessibilityId("bottom_navigation_hub_tab");
		hubTab.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//clicar enroll now
		MobileElement enrollButton = (MobileElement) driver.findElementByAccessibilityId("on_call_programs_card_0_button");
		enrollButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Welcome to Sword']")));
		//voltar
		driver.findElementByAccessibilityId("ic arrow left").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//clicar on-call card
		MobileElement el5 = (MobileElement) driver.findElementByAccessibilityId("home_on_call_card_help_from_specialist_button");
		el5.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		//validar triage screen - ver se em Android consegue validar os textos dos cards
		String painChatButton = driver.findElementByXPath("//XCUIElementTypeButton[@name='on_call_physical_pain_card_button']/XCUIElementTypeStaticText[2]").getText();
		String headacheChatButton = driver.findElementByXPath("//XCUIElementTypeButton[@name='on_call_headaches_card_button']/XCUIElementTypeStaticText[2]").getText();
		String pelvicChatButton = driver.findElementByXPath("//XCUIElementTypeButton[@name='on_call_pelvic_health_card_button']/XCUIElementTypeStaticText[2]").getText();
		Assert.assertEquals("Chat with a Pain Specialist", painChatButton);
		Assert.assertEquals("Chat with a Headache Specialist", headacheChatButton);
		Assert.assertEquals("Chat with a Pelvic Specialist", pelvicChatButton);
		String terms = driver.findElementByXPath("//XCUIElementTypeButton[@name='on_call_terms_conditions']/XCUIElementTypeStaticText").getText();
		Assert.assertEquals("This practitioner is not intended to be a substitute for professional medical advice, diagnosis or treatment. Read Terms & Conditions", terms);
		//abrir terms and conditions
		MobileElement el6 = (MobileElement) driver.findElementByAccessibilityId("on_call_terms_conditions");
		el6.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		//validar ecrã terms and conditions
		MobileElement termsOnCallTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='On-Call']");
		MobileElement termsTxt1 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]");
		mobileActions.swipeByElements(termsTxt1, termsOnCallTxt);
		String termsHeader = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Terms & Conditions']").getText();
		Assert.assertEquals("Terms & Conditions", termsHeader);
		String termsTxt2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]").getText();
		Assert.assertEquals("All information provided by the On-call clinical pain specialist is for informational purposes only.  Such information is not intended to be and should not be used as a substitute for professional medical advice, diagnosis or treatment.  The On-call clinical pain specialist will not be able to provide a diagnosis, write you a prescription, or provide a plan of care or treatment.  If you choose to rely on any information provided by the On-call clinical pain specialist, you rely solely at your own risk.\n"
				+ "\n"
				+ "Always seek the advice of your physician or other qualified healthcare provider for any health questions or concerns you may have regarding your medical condition or treatment. If you have or think you may have a medical emergency, need urgent or emergency care, please call your doctor, go to the nearest hospital emergency department, or call 911 immediately.  Neither Sword Health, Inc. nor any of its affiliates and contracted entities assumes responsibility or liability for any damages, claims, liabilities, costs or obligations arising from the use of this app or any On-call clinical pain specialist.", termsTxt2);
		//voltar até a hub screen
		driver.findElementByAccessibilityId("ic arrow left").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		driver.findElementByAccessibilityId("ic arrow left").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//print do hub screen sem on-call chat ativo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("screen1.jpg"));
		byte[] screenshot1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//abrir physical pain chat
		MobileElement el7 = (MobileElement) driver.findElementByAccessibilityId("home_on_call_card");
		el7.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		MobileElement el8 = (MobileElement) driver.findElementByAccessibilityId("on_call_physical_pain_card");
		el8.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
		//voltar sem enviar mensagem
		driver.findElementByAccessibilityId("ic arrow left").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//abrir triage screen de novo
		MobileElement el9 = (MobileElement) driver.findElementByAccessibilityId("home_on_call_card_help_from_specialist_button");
		el9.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		//abrir headaches chat
		MobileElement el10 = (MobileElement) driver.findElementByAccessibilityId("on_call_headaches_card_button");
		el10.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
		//enviar mensagem
		MobileElement el11 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeTextView");
		el11.click();
		el11.sendKeys("Tests. Please ignore");
		MobileElement el12 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeButton[2]");
		el12.click();
		mobileActions.tapByCoordinates(172, 273);
		//voltar
		driver.findElementByAccessibilityId("ic arrow left").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		//validar on-call card novo - os ids dos cards são iguais em iOS
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
		MobileElement el13 = (MobileElement) driver.findElementByAccessibilityId("home_on_call_card");
		el13.click();
		//voltar
		driver.findElementByAccessibilityId("ic arrow left").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//fazer logout
		driver.findElementByAccessibilityId("header_menu_button").click();
		driver.findElementByAccessibilityId("menu_option_logout").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		//fazer login no mesmo user de novo
		MobileElement el14 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el14.click();
		el14.sendKeys("28Abril!");
		MobileElement el15 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el15.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el16 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not now']");
		el16.click();
		//validar que ainda mostra o mesmo on-call card
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Get on-demand support from a Clinical Pain Specialist via chat.']")));
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
	}

	@Test
	public void virtualPt() throws IOException {
		IOSDriver<MobileElement> driver = inicializarAppium();
		
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		//login
		driver.findElementByAccessibilityId("Allow").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
	//	MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("loginEmailTextfield");
	//	el1.clear();
	//	el1.sendKeys("luiza@marco.com");
		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el2.click();
		el2.sendKeys("10março!");
		MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el3.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el4 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not now']");
		el4.click();
		//abrir hub screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		driver.findElementByAccessibilityId("bottom_navigation_hub_tab").click();
		//validar hub screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		String onCallTitle = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_on_call_card']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]").getText();
		String onCallLabel1 = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_on_call_card']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]").getText();
		String onCallButton = driver.findElementByAccessibilityId("home_on_call_card_help_from_specialist_button").getText();
		Assert.assertEquals("On-Call", onCallTitle);
		Assert.assertEquals("Get on-demand support from a Clinical Pain Specialist via chat.", onCallLabel1);
		Assert.assertEquals("Chat with a Specialist", onCallButton);
		//clicar on-call card
		MobileElement el5 = (MobileElement) driver.findElementByAccessibilityId("home_on_call_card_help_from_specialist_button");
		el5.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		//validar triage screen - ver se em Android consegue validar os textos dos cards
		String painChatButton = driver.findElementByXPath("//XCUIElementTypeButton[@name='on_call_physical_pain_card_button']/XCUIElementTypeStaticText[2]").getText();
		String headacheChatButton = driver.findElementByXPath("//XCUIElementTypeButton[@name='on_call_headaches_card_button']/XCUIElementTypeStaticText[2]").getText();
		String pelvicChatButton = driver.findElementByXPath("//XCUIElementTypeButton[@name='on_call_pelvic_health_card_button']/XCUIElementTypeStaticText[2]").getText();
		Assert.assertEquals("Chat with a Pain Specialist", painChatButton);
		Assert.assertEquals("Chat with a Headache Specialist", headacheChatButton);
		Assert.assertEquals("Chat with a Pelvic Specialist", pelvicChatButton);
		String terms = driver.findElementByXPath("//XCUIElementTypeButton[@name='on_call_terms_conditions']/XCUIElementTypeStaticText").getText();
		Assert.assertEquals("This practitioner is not intended to be a substitute for professional medical advice, diagnosis or treatment. Read Terms & Conditions", terms);
		//abrir terms and conditions
		MobileElement el6 = (MobileElement) driver.findElementByAccessibilityId("on_call_terms_conditions");
		el6.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		//validar ecrã terms and conditions
		MobileElement termsOnCallTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='On-Call']");
		MobileElement termsTxt1 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]");
		mobileActions.swipeByElements(termsTxt1, termsOnCallTxt);
		String termsHeader = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Terms & Conditions']").getText();
		Assert.assertEquals("Terms & Conditions", termsHeader);
		String termsTxt2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]").getText();
		Assert.assertEquals("All information provided by the On-call clinical pain specialist is for informational purposes only.  Such information is not intended to be and should not be used as a substitute for professional medical advice, diagnosis or treatment.  The On-call clinical pain specialist will not be able to provide a diagnosis, write you a prescription, or provide a plan of care or treatment.  If you choose to rely on any information provided by the On-call clinical pain specialist, you rely solely at your own risk.\n"
				+ "\n"
				+ "Always seek the advice of your physician or other qualified healthcare provider for any health questions or concerns you may have regarding your medical condition or treatment. If you have or think you may have a medical emergency, need urgent or emergency care, please call your doctor, go to the nearest hospital emergency department, or call 911 immediately.  Neither Sword Health, Inc. nor any of its affiliates and contracted entities assumes responsibility or liability for any damages, claims, liabilities, costs or obligations arising from the use of this app or any On-call clinical pain specialist.", termsTxt2);
		//voltar até a hub screen
		driver.findElementByAccessibilityId("ic arrow left").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		driver.findElementByAccessibilityId("ic arrow left").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//print do hub screen sem on-call chat ativo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("screen1.jpg"));
		byte[] screenshot1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//abrir headaches chat
		MobileElement el7 = (MobileElement) driver.findElementByAccessibilityId("home_on_call_card");
		el7.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		MobileElement el8 = (MobileElement) driver.findElementByAccessibilityId("on_call_headaches_card");
		el8.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
		//voltar sem enviar mensagem
		driver.findElementByAccessibilityId("ic arrow left").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//abrir triage screen de novo
		MobileElement el9 = (MobileElement) driver.findElementByAccessibilityId("home_on_call_card_help_from_specialist_button");
		el9.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		//abrir pelvic chat
		MobileElement el10 = (MobileElement) driver.findElementByAccessibilityId("on_call_pelvic_health_card_button");
		el10.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
		//enviar mensagem
		MobileElement el11 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeTextView");
		el11.click();
		el11.sendKeys("Tests. Please ignore");
		MobileElement el12 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeButton[2]");
		el12.click();
		mobileActions.tapByCoordinates(172, 273);
		//voltar
		driver.findElementByAccessibilityId("ic arrow left").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Get on-demand support from a Clinical Pain Specialist via chat.']")));
		//validar on-call card novo - os ids dos cards são iguais em iOS
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
		MobileElement el13 = (MobileElement) driver.findElementByAccessibilityId("home_on_call_card");
		el13.click();
		//voltar
		driver.findElementByAccessibilityId("ic arrow left").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//fazer logout
		driver.findElementByAccessibilityId("header_menu_button").click();
		driver.findElementByAccessibilityId("menu_option_logout").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		//fazer login no mesmo user de novo
		MobileElement el14 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el14.click();
		el14.sendKeys("10março!");
		MobileElement el15 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el15.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el16 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not now']");
		el16.click();
		//validar que ainda mostra o mesmo on-call card
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		driver.findElementByAccessibilityId("bottom_navigation_hub_tab").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Get on-demand support from a Clinical Pain Specialist via chat.']")));
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
		MobileElement el17 = (MobileElement) driver.findElementByAccessibilityId("home_on_call_card");
		el17.click();
		MobileElement el18 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeButton");
		el18.click();
		MobileElement el19 = (MobileElement) driver.findElementByAccessibilityId("Photos and videos");
		el19.click();
		MobileElement el20 = (MobileElement) driver.findElementByAccessibilityId("Allow Access to All Photos");
		el20.click();
		MobileElement el21 = (MobileElement) driver.findElementByAccessibilityId("Screenshot, 23 February, 19:30"); //precisa atualizar pra uma que apareça no rolo
		el21.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView[@name='Tests. Please ignore']")));
		File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile3, new File("screen3.jpg"));
		driver.findElementByAccessibilityId("ic arrow left").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
	}

}