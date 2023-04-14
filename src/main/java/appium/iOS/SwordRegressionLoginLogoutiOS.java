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
		emailTextfield.sendKeys("luiza@marco.com");
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
		MobileElement el2 = driver.findElementByAccessibilityId("loginPasswordTextfield");
		el2.click();
		el2.sendKeys("10março!");
		utilitiesiOS.clickByAccessibilityId("loginButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='You can use your PIN code to log in any time your session expires.']");
		utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Not now']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		utilitiesiOS.clickByAccessibilityId("menu_option_logout", driver);
		//novo login
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		MobileElement el12 = driver.findElementByAccessibilityId("loginPasswordTextfield");
		el12.click();
		el12.sendKeys("10março!");
		utilitiesiOS.clickByAccessibilityId("loginButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Create PIN']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Create your PIN code']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set PIN code later']");
		utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Set PIN code later']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		utilitiesiOS.clickByAccessibilityId("menu_option_logout", driver);
		MobileElement el19 = driver.findElementByAccessibilityId("loginPasswordTextfield");
		el19.click();
		el19.sendKeys("10março!");
		utilitiesiOS.clickByAccessibilityId("loginButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Create PIN']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement createPin0 = driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		createPin0.click();
		createPin0.click();
		createPin0.click();
		utilitiesiOS.clickByAccessibilityId("deleteKey", driver);
		createPin0.click();
		createPin0.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement createPin2 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='2']");
		createPin2.click();
		createPin2.click();
		createPin2.click();
		createPin2.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		MobileElement confirmPin7 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='7']");
		confirmPin7.click();
		confirmPin7.click();
		confirmPin7.click();
		confirmPin7.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Uh-oh! The PIN codes didn't match. Please try again.\"]");
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Retry']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement createPin5 = driver.findElementByXPath("//XCUIElementTypeButton[@name='5']");
		createPin5.click();
		createPin5.click();
		createPin5.click();
		createPin5.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		MobileElement confirmPin5 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='5']");
		confirmPin5.click();
		confirmPin5.click();
		confirmPin5.click();
		confirmPin5.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Want to use Face ID for future logins? You can activate it now, or activate it later in Settings.\"]");
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Activate now']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		utilitiesiOS.clickByAccessibilityId("menu_option_logout", driver);

		ConfigurationsiOS.killDriver();
	}
}