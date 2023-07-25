package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
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

	private final static String CHECK_CREATE_PIN = "create_pin_screen";
	private final static String CHECK_CONFIRM_PIN = "confirm_pin_screen";
	private final static String CHECK_CREATE_PIN_INFO = "create_pin_info_screen";
	private final static String CHECK_PIN_DOESNT_MATCH_LOGIN = "pin_doesnt_match_login";
	private final static String CHECK_BIOMETRICS_LOGIN = "biometrics_login";
	private final static String CHECK_RECOVER_PASS_EMPTY = "recover_pass_empty";
	private final static String CHECK_RECOVER_PASS_SUCCESS = "recover_pass_success";
	private final static String CHECK_ACCOUNT_TEMP_LOCKED = "account_temp_locked";
	private final static String CHECK_QR_CODE_1 = "qr_code_1";
	private final static String CHECK_QR_CODE_2 = "qr_code_2";
	private final static String CHECK_RECOVER_PASS_INVALID = "recover_pass_invalid_email";
	private final static String CHECK_LOGIN_PASS_EMPTY = "login_empty_pass";
	private final static String CHECK_LOGIN_EMAIL_EMPTY = "login_empty_email";
	private final static String CHECK_LOGIN_EMAIL_INVALID = "login_invalid_email";
	private final static String CHECK_LOGIN_EMAIL_PASS_WRONG = "login_wrong_email_pass";
	private final static String CHECK_LOGIN_EMPTY_PASS_AFTER_BACK = "login_enter_pass_after_back";

	@Test
	public void errosELoginPage() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		VisualCheck visualCheck = new VisualCheck(driver);

		driver.findElementByAccessibilityId("Allow").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Welcome to Sword']")));
		utilitiesiOS.clickByAccessibilityId("loginQRCodeButton", driver);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Application permissions']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='The app needs the following permissions to work correctly:']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sword will only use the camera to scan the QR code on the card or tablet.']");
		visualCheck.doVisualCheck(CHECK_QR_CODE_1);
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Next']", driver);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Present your card']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Hold the card still for a few seconds and don't cover the red square\"]");
		visualCheck.doVisualCheck(CHECK_QR_CODE_2);
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
		//adicionar testes visuais da client list
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name=\"AAA Club Alliance\"]")).size() > 0) {
			utilitiesiOS.clickByAccessibilityId("AAA Club Alliance", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Good news! These benefits are offered by AAA Club Alliance.\"]")));
		} else {
			utilitiesiOS.clickByAccessibilityId("Magnolia", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Let's confirm Sword is right for you\"]")));
		}
		//voltar para login screen
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		//recover pass
		utilitiesiOS.clickByAccessibilityId("loginRecoverPasswordButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Recover my password']")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Enter the email associated with your account. Then, we'll send you an email showing you how to reset your password\"]");
		visualCheck.doVisualCheck(CHECK_RECOVER_PASS_EMPTY);
		MobileElement recoverPassTextField = driver.findElementByAccessibilityId("recoverPasswordEmailTextfield");
		recoverPassTextField.click();
		recoverPassTextField.sendKeys("hjvhik");
		mobileActions.tapByCoordinates(198, 348);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='  Invalid email']");
		visualCheck.doVisualCheck(CHECK_RECOVER_PASS_INVALID);
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
		visualCheck.doVisualCheck(CHECK_RECOVER_PASS_SUCCESS);
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Ok']", driver);
		//erros do login
		//email vazio
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Welcome to Sword']")));
		MobileElement emailTextField = driver.findElementByAccessibilityId("loginEmailTextfield");
		emailTextField.clear();
		MobileElement continueButton = driver.findElementByAccessibilityId("continueButton");
		continueButton.click();
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"  Please enter your email address\"]");
		visualCheck.doVisualCheck(CHECK_LOGIN_EMAIL_EMPTY);
		//email inválido
		emailTextField.sendKeys("jfeiow");
		continueButton.click();
		emailTextField.clear();
		emailTextField.sendKeys("jfioejirfo@rfoe");
		mobileActions.tapByCoordinates(302, 186);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"  Invalid email\"]");
		visualCheck.doVisualCheck(CHECK_LOGIN_EMAIL_INVALID);
		//email válido + senha vazia
		emailTextField.clear();
		emailTextField.sendKeys("luiza@marco.com");
		continueButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Log in']")));
		MobileElement loginButton = driver.findElementByAccessibilityId("loginButton");
		loginButton.click();
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"  Please enter your password\"]");
		visualCheck.doVisualCheck(CHECK_LOGIN_PASS_EMPTY);
		//mostrar senha
		driver.findElementByAccessibilityId("loginPasswordTextfield").sendKeys("12345");
		utilitiesiOS.clickByAccessibilityId("loginShowPasswordButton", driver);
		//esconder senha
		utilitiesiOS.clickByAccessibilityId("loginShowPasswordButton", driver);
		//email certo e senha errada + ecrã de bloqueio
		loginButton.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"  Your email or password is incorrect\"]");
		visualCheck.doVisualCheck(CHECK_LOGIN_EMAIL_PASS_WRONG);
		loginButton.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		loginButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeImage")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Uh-oh!']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"You've reached the maximum number of login attempts. Please try again in a few minutes.\"]");
		visualCheck.doVisualCheck(CHECK_ACCOUNT_TEMP_LOCKED);
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Ok']", driver);
		//voltar pro primeiro ecrã
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Continue\"]")));
		continueButton.click();
		//bug que a pass não volta vazia
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		visualCheck.doVisualCheck(CHECK_LOGIN_EMPTY_PASS_AFTER_BACK);

		ConfigurationsiOS.killDriver();
	}
	
	@Test
	public void fazerLogin() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		VisualCheck visualCheck = new VisualCheck(driver);
	  
		//login + not now
		utilitiesiOS.newLogin("luiza@marco.com", "10março!", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		utilitiesiOS.clickByAccessibilityId("menu_option_logout", driver);
		//login + create pin + set pin code later
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Continue']")));
		utilitiesiOS.clickByAccessibilityId("continueButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Log in']")));
		driver.findElementByAccessibilityId("loginPasswordTextfield").sendKeys("10março!");
		utilitiesiOS.clickByAccessibilityId("loginButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"You can use your PIN code to log in any time your session expires.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Create PIN\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Not now\"]");
		visualCheck.doVisualCheck(CHECK_CREATE_PIN_INFO);
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Create PIN']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set PIN code later']");
		visualCheck.doVisualCheck(CHECK_CREATE_PIN);
		utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name='Set PIN code later']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		utilitiesiOS.clickByAccessibilityId("menu_option_logout", driver);
		//login + define pin
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Continue']")));
		utilitiesiOS.clickByAccessibilityId("continueButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Log in']")));
		driver.findElementByAccessibilityId("loginPasswordTextfield").sendKeys("10março!");
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
		visualCheck.doVisualCheck(CHECK_CONFIRM_PIN);
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
		visualCheck.doVisualCheck(CHECK_PIN_DOESNT_MATCH_LOGIN);
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
		visualCheck.doVisualCheck(CHECK_BIOMETRICS_LOGIN);
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Activate now']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		utilitiesiOS.clickByAccessibilityId("menu_option_logout", driver);

		ConfigurationsiOS.killDriver();
	}
}