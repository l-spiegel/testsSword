package appium.iOS;

import java.io.File;
import java.io.IOException;
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
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import io.appium.java_client.ios.IOSDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.CoreMatchers.*;

public class swordRegressionOnCalliOS {
	private IOSDriver<MobileElement> driver;
	
	private final static String VALIDATION_PATH = "/Users/luizaspiegel/Documents/image check/regression oncall/iOS";
	private final static String BASELINE = "BASELINE_";

	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
	}

	@Test
	public void preventive() throws IOException {
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		
		//login
		utilitiesiOS.login("frodobaggins@sword.com", "28Abril!", driver);
		//validar hub screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='On-Call']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Get on-demand support from a Clinical Pain Specialist via chat.\"]");
		String onCallButton = driver.findElementByAccessibilityId("home_on_call_card_help_from_specialist_button").getText();
		Assert.assertEquals("Chat with a Specialist", onCallButton);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Explore our programs\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Learn more about our programs below to find the best fit for you.\"]");
		if (driver.findElements(By.id("Explore our programs")).size() > 0) {
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Eligible\"]");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Digital Physical Therapy\"]");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Physical therapy you can do anytime, anywhere.\"]");
		}
		//abrir academy tab
		utilitiesiOS.clickByAccessibilityId("bottom_navigation_academy_tab", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Academy']")));
		//voltar pra hub tab
		utilitiesiOS.clickByAccessibilityId("bottom_navigation_hub_tab", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//clicar get started
		utilitiesiOS.clickByAccessibilityId("on_call_programs_card_0_get_started_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage[@name=\"Sword Health\"]")));
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//clicar on-call card
		utilitiesiOS.clickByAccessibilityId("home_on_call_card_help_from_specialist_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		//validar triage screen - ver se em Android consegue validar os textos dos cards
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Chat with a Pain Specialist\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Chat with a Headache Specialist\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Chat with a Pelvic Specialist\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"This practitioner is not intended to be a substitute for professional medical advice, diagnosis or treatment. Read Terms & Conditions\"]");
		//abrir terms and conditions
		utilitiesiOS.clickByAccessibilityId("on_call_terms_conditions", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Terms & Conditions\"]")));
		//validar ecrã terms and conditions
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
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("screen1.jpg"));
		byte[] screenshot1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//abrir physical pain chat
		utilitiesiOS.clickByAccessibilityId("home_on_call_card", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		utilitiesiOS.clickByAccessibilityId("on_call_physical_pain_card", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
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
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		//validar on-call card novo
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		MobileElement passField = driver.findElementByAccessibilityId("loginPasswordTextfield");
		passField.click();
		passField.sendKeys("28Abril!");
		utilitiesiOS.clickByAccessibilityId("loginButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Not now']", driver);
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

		ConfigurationsiOS.killDriver();
	}

	@Test
	public void virtualPt() throws IOException {
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		
		//login
		utilitiesiOS.login("luiza@marco.com", "10março!", driver);
		//abrir hub screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		utilitiesiOS.clickByAccessibilityId("bottom_navigation_hub_tab", driver);
		//validar hub screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='On-Call']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Get on-demand support from a Clinical Pain Specialist via chat.']");
		String onCallButton = driver.findElementByAccessibilityId("home_on_call_card_help_from_specialist_button").getText();
		Assert.assertEquals("Chat with a Specialist", onCallButton);
		//clicar on-call card
		utilitiesiOS.clickByAccessibilityId("home_on_call_card_help_from_specialist_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		//validar triage screen - ver se em Android consegue validar os textos dos cards
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Chat with a Pain Specialist']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Chat with a Headache Specialist']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Chat with a Pelvic Specialist']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='This practitioner is not intended to be a substitute for professional medical advice, diagnosis or treatment. Read Terms & Conditions']");
		//abrir terms and conditions
		utilitiesiOS.clickByAccessibilityId("on_call_terms_conditions", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		//validar ecrã terms and conditions
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
		FileUtils.copyFile(scrFile, new File("screen1.jpg"));
		byte[] screenshot1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//abrir headaches chat
		utilitiesiOS.clickByAccessibilityId("home_on_call_card", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='On-Call']")));
		utilitiesiOS.clickByAccessibilityId("on_call_headaches_card", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
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
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
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
		utilitiesiOS.clickByAccessibilityId("home_on_call_card", driver);
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));
		//fazer logout
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		utilitiesiOS.clickByAccessibilityId("menu_option_logout", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		//fazer login no mesmo user de novo
		driver.findElementByAccessibilityId("loginPasswordTextfield").sendKeys("10março!");
		utilitiesiOS.clickByAccessibilityId("loginButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Not now']", driver);
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
		utilitiesiOS.clickByAccessibilityId("home_on_call_card", driver);
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeButton", driver);
		utilitiesiOS.clickByAccessibilityId("Photos and videos", driver);
		utilitiesiOS.clickByAccessibilityId("Allow Access to All Photos", driver);
		utilitiesiOS.clickByAccessibilityId("Screenshot, 12 April, 11:02 AM", driver); //precisa atualizar pra uma que apareça no rolo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView[@name='Tests. Please ignore']")));
		File scrFile3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile3, new File("screen3.jpg"));
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_on_call_card")));

	}

}