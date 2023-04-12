package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SwordRegressionLoginLogoutiOS {
	private IOSDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
	}

	@Test
	public void errosELoginPage() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();

		driver.findElementByAccessibilityId("Allow").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		utilitiesiOS.clickByAccessibilityId("loginQRCodeButton", driver);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Application permissions']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='The app needs the following permissions to work correctly:']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='We use the camera to read your patient card QR code and sign you into Sword']");
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Next']", driver);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Present your card']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Hold the card still for a few seconds and don't cover the red square\"]");
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Next']", driver);
		utilitiesiOS.clickByAccessibilityId("OK", driver);
		utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Back']", driver);
		utilitiesiOS.clickByAccessibilityId("1PxIcChevronLeft", driver);
		utilitiesiOS.clickByAccessibilityId("1PxIcChevronLeft", driver);
		utilitiesiOS.clickByAccessibilityId("loginSignupButton", driver);
		//select client screen
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Select your organization']");
		MobileElement searchBar = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTextField");
		searchBar.click();
		searchBar.sendKeys("pep");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		mobileActions.tapByCoordinates(198, 348);
		utilitiesiOS.clickByAccessibilityId("ic close button", driver);
		utilitiesiOS.clickByAccessibilityId("Danaher Canada", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Let's confirm Sword is right for you\"]")));
		//voltar para login screen
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		//recover pass
		utilitiesiOS.clickByAccessibilityId("loginRecoverPasswordButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Recover my password']")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Enter the email associated with your account. Then, we'll send you an email showing you how to reset your password\"]");
		MobileElement recoverPassTextField = driver.findElementByAccessibilityId("recoverPasswordEmailTextfield");
		recoverPassTextField.click();
		recoverPassTextField.sendKeys("hjvhik");
		mobileActions.tapByCoordinates(198, 348);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='  Invalid email']");
		recoverPassTextField.click();
		recoverPassTextField.sendKeys("@jjho");
		utilitiesiOS.clickByAccessibilityId("recoverPasswordButton", driver);
		recoverPassTextField.clear();
		recoverPassTextField.sendKeys("dqd@jsks.com");
		utilitiesiOS.clickByAccessibilityId("recoverPasswordButton", driver);
		//ecrã de sucesso
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Check your email']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"If you have an active Sword account, you'll see an email from us showing you how to reset your password\"]");
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Ok']", driver);
		//erros do login
		//email válido + pass em branco
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Welcome to Sword']")));
		MobileElement loginButton = driver.findElementByAccessibilityId("loginButton");
		loginButton.click();
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='  Please enter your password']");
		//email vazio + senha vazia
		MobileElement emailTextfield = driver.findElementByAccessibilityId("loginEmailTextfield");
		emailTextfield.clear();
		loginButton.click();
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='  Please enter your email address']");
		//email invalido
		emailTextfield.sendKeys("uefhiwuehfiwe.com");
		mobileActions.tapByCoordinates(302, 186);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='  Invalid email']");
		//email certo e senha errada + ecrã de bloqueio
		emailTextfield.clear();
		emailTextfield.sendKeys("l.spiegel+3@swordhealth.com");
		MobileElement passwordTextfield = driver.findElementByAccessibilityId("loginPasswordTextfield");
		passwordTextfield.click();
		passwordTextfield.sendKeys("12345");
		loginButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//XCUIElementTypeStaticText[@name='Login'])")));
		loginButton.click();
		loginButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Uh-oh!']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"You've reached the maximum number of login attempts. Please try again in a few minutes.\"]");
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Ok']", driver);

		ConfigurationsiOS.killDriver();
	}
	
	@Test
	public void fazerLogin() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
	  
		driver.findElementByAccessibilityId("Allow").click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
	//	MobileElement el1 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTextField");
	//	el1.clear();
	//	el1.sendKeys("nove@maio.com");
		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el2.click();
		el2.sendKeys("10março!");
		MobileElement el5 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el5.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		String el6 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Create your PIN code']").getText();
			Assert.assertEquals("Create your PIN code", el6);
		String el7 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("You can use your PIN code to log in any time your session expires.", el7);
		MobileElement el8 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not now']");
		el8.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		MobileElement el10 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el10.click();
		MobileElement el11 = (MobileElement) driver.findElementByAccessibilityId("menu_option_logout");
		el11.click();
		//novo login
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		MobileElement el12 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el12.click();
		el12.sendKeys("10março!");
		MobileElement el13 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el13.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el14 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Create PIN']");
		el14.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		String el15 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Create your PIN code']").getText();
			Assert.assertEquals("Create your PIN code", el15);
		String el16 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set PIN code later']").getText();
			Assert.assertEquals("Set PIN code later", el16);
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set PIN code later']").click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		MobileElement el17 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el17.click();
		MobileElement el18 = (MobileElement) driver.findElementByAccessibilityId("menu_option_logout");
		el18.click();
		MobileElement el19 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el19.click();
		el19.sendKeys("10março!");
		el19.click();
		MobileElement el20 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el20.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el21 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Create PIN']");
		el21.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el22 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el22.click();
		el22.click();
		el22.click();
		MobileElement el23 = (MobileElement) driver.findElementByAccessibilityId("deleteKey");
		el23.click();
		MobileElement el24 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el24.click();
		el24.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		String el25 = driver.findElementByAccessibilityId("Confirm your PIN code").getText();
			Assert.assertEquals("Confirm your PIN code", el25);
		MobileElement el26 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el26.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el27 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
		el27.click();
		el27.click();
		el27.click();
		el27.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		MobileElement el28 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='7']");
		el28.click();
		el28.click();
		el28.click();
		el28.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")));
		String el30 = driver.findElementByAccessibilityId("Uh-oh! The PIN codes didn't match. Please try again.").getText();
			Assert.assertEquals("Uh-oh! The PIN codes didn't match. Please try again.", el30);
		MobileElement el31 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']");
		el31.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el33 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el33.click();
		el33.click();
		el33.click();
		el33.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		MobileElement el35 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
		el35.click();
		el35.click();
		el35.click();
		el35.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")));
		String el37 = driver.findElementByAccessibilityId("Want to use Face ID for future logins? You can activate it now, or activate it later in Settings.").getText();
			Assert.assertEquals("Want to use Face ID for future logins? You can activate it now, or activate it later in Settings.", el37);
		MobileElement el38 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Activate now']");
		el38.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		MobileElement el39 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el39.click();
		MobileElement el40 = (MobileElement) driver.findElementByAccessibilityId("menu_option_logout");
		el40.click();

		driver.quit();
	}
}