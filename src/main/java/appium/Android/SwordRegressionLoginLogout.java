package appium.Android;

import java.net.MalformedURLException;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class SwordRegressionLoginLogout {

	private AndroidDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsAndroid.getDriver();
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

	@Test
	public void errosELoginPage() throws Exception {
		MobileActions mobileActions = new MobileActions(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		VisualCheck visualCheck = new VisualCheck(driver);
		
		//login qr code
		utilitiesAndroid.clickByAccessibilityId("loginQRCodeButton", driver);
		driver.findElementByXPath("//android.widget.TextView[@text='Application permissions']");
		driver.findElementByXPath("//android.widget.TextView[@text='The app needs the following permissions to work correctly:']");
		driver.findElementByXPath("//android.widget.TextView[@text='Sword will only use the camera to scan the QR code on the card or tablet.']");
		visualCheck.doVisualCheck(CHECK_QR_CODE_1);
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		driver.findElementByXPath("//android.widget.TextView[@text='Present your card']");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Hold the card still for a few seconds and don't cover the red square\"]");
		visualCheck.doVisualCheck(CHECK_QR_CODE_2);
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		driver.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button").click();
		//back no leitor de qr code
		utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='Back']", driver);
		MobileElement el11 = driver.findElementByAccessibilityId("Navigate up");
		el11.click();
		el11.click();
		//select client screen
		utilitiesAndroid.clickByAccessibilityId("loginSignupButton", driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//android.widget.TextView[@text='Select your organization']");
		MobileElement searchBar = driver.findElementByXPath("//android.widget.EditText");
		searchBar.click();
		searchBar.sendKeys("pep");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		mobileActions.tapByCoordinates(780, 1070);
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p1/android.view.View/android.view.View[2]/android.widget.EditText/android.view.View/android.widget.ImageView", driver);
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Danaher Canada']")).size() > 0) {
			utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='Danaher Canada']", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Let's confirm Sword is right for you\"]")));
		} if (driver.findElements(By.xpath("//android.widget.TextView[@text='AAA Club Alliance']")).size() > 0) {
			utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='AAA Club Alliance']", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Good news! These benefits are offered by AAA Club Alliance.\"]")));
		} else {
			utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='Magnolia']", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Good news! Magnolia Tree offers Sword as a benefit.\"]")));
		}
		//voltar para login screen
		//colocar um if aqui pra validar se é nativo ou webview
		//xpath de webview
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p1/android.view.View/android.view.View/android.view.View", driver);
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		//xpath do signup nativo
		//utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p1/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button", driver);
		//utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p1/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		//recover pass
		utilitiesAndroid.clickByAccessibilityId("loginRecoverPasswordButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Recover my password']")));
		driver.findElementByXPath("//android.widget.TextView[@text=\"Enter the email associated with your account. Then, we'll send you an email showing you how to reset your password\"]");
		visualCheck.doVisualCheck(CHECK_RECOVER_PASS_EMPTY);
		MobileElement recoverPassTextField = driver.findElementByXPath("//android.widget.EditText");
		recoverPassTextField.click();
		recoverPassTextField.sendKeys("hjvhik");
		mobileActions.tapByCoordinates(769, 963);
		driver.findElementByXPath("//android.widget.TextView[@text='Invalid email']");
		visualCheck.doVisualCheck(CHECK_RECOVER_PASS_INVALID);
		recoverPassTextField.click();
		recoverPassTextField.sendKeys("jwjwj@jjho");
		utilitiesAndroid.clickByAccessibilityId("recoverPasswordButton", driver);
		recoverPassTextField.clear();
		recoverPassTextField.sendKeys("dqd@jsks.com");
		utilitiesAndroid.clickByAccessibilityId("recoverPasswordButton", driver);
		//ecrã de sucesso
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
		driver.findElementByXPath("//android.widget.TextView[@text='Check your email']");
		driver.findElementByXPath("//android.widget.TextView[@text=\"If you have an active Sword account, you'll see an email from us showing you how to reset your password\"]");
		visualCheck.doVisualCheck(CHECK_RECOVER_PASS_SUCCESS);
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		//erros do login
		//email vazio + senha vazia
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Sword']")));
		MobileElement continueButton = driver.findElementByAccessibilityId("continueButton");
		continueButton.click();
		driver.findElementByXPath("//android.widget.TextView[@text='Please enter your email address']");
		visualCheck.doVisualCheck(CHECK_LOGIN_EMAIL_EMPTY);
		//email invalido
		MobileElement emailTextfield = driver.findElementByXPath("//android.widget.EditText[1]");
		emailTextfield.click();
		emailTextfield.sendKeys("uefhiwuehfiwe.com");
		mobileActions.tapByCoordinates(302, 186);
		driver.findElementByXPath("//android.widget.TextView[@text='Invalid email']");
		visualCheck.doVisualCheck(CHECK_LOGIN_EMAIL_INVALID);
		//email válido + pass em branco
		emailTextfield.clear();
		emailTextfield.sendKeys("luiza@marco.com");
		continueButton.click();
		utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//android.widget.TextView[@text='Please enter your password']");
		visualCheck.doVisualCheck(CHECK_LOGIN_PASS_EMPTY);
		//email certo e senha errada + ecrã de bloqueio
		MobileElement passwordTextfield = driver.findElementByXPath("//android.widget.EditText[2]");
		passwordTextfield.click();
		passwordTextfield.sendKeys("12345");
		continueButton.click();
		driver.findElementByXPath("//android.widget.TextView[@text='Your email or password is incorrect']");
		visualCheck.doVisualCheck(CHECK_LOGIN_EMAIL_PASS_WRONG);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.TextView[@text='Login'])")));
		utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
		utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
		driver.findElementByXPath("//android.widget.TextView[@text='Uh-oh!']");
		driver.findElementByXPath("//android.widget.TextView[@text=\"You've reached the maximum number of login attempts. Please try again in a few minutes.\"]");
		visualCheck.doVisualCheck(CHECK_ACCOUNT_TEMP_LOCKED);
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		//adicionar teste de email inválido e senha inválida
		driver.findElementByXPath("//android.widget.EditText[1]").clear();
		driver.findElementByXPath("//android.widget.EditText[1]").sendKeys("fhuihfie@iohfo.com");
		driver.findElementByXPath("//android.widget.EditText[2]").click();
		driver.findElementByXPath("//android.widget.EditText[2]").sendKeys("1234");
		utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
		utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
		utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
		driver.findElementByXPath("//android.widget.TextView[@text='Your email or password is incorrect']");

		ConfigurationsAndroid.killDriver();
	}
	
	@Test
	public void fazerLogin() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		VisualCheck visualCheck = new VisualCheck(driver);

		//login + create pin + set pin code later
		MobileElement enterEmail = driver.findElementByXPath("//android.widget.EditText");
		enterEmail.clear();
		enterEmail.sendKeys("luiza@marco.com");
		utilitiesAndroid.clickByAccessibilityId("continueButton", driver);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		MobileElement enterPass = driver.findElementByXPath("//android.widget.EditText");
		enterPass.click();
		enterPass.sendKeys("10março!");
		utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
		driver.findElementByXPath("//android.widget.TextView[@text='Create your PIN code']");
		driver.findElementByXPath("//android.widget.TextView[@text='You can use your PIN code to log in any time your session expires.']");
		visualCheck.doVisualCheck(CHECK_CREATE_PIN_INFO);
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		driver.findElementByXPath("//android.widget.TextView[@text='Create your PIN code']");
		driver.findElementByXPath("//android.widget.TextView[@text='Set PIN code later']");
		visualCheck.doVisualCheck(CHECK_CREATE_PIN);
		utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='Set PIN code later']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Weekly goal']")));
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		//logout button
		utilitiesAndroid.clickByAccessibilityId("menu_option_logout", driver);
		//login + not now
		utilitiesAndroid.clickByAccessibilityId("continueButton", driver);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//android.widget.EditText").sendKeys("10março!");
		utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
		driver.findElementByXPath("//android.widget.TextView[@text='Not now']").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Weekly goal']")));
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		//logout button
		utilitiesAndroid.clickByAccessibilityId("menu_option_logout", driver);
		//login + define pin
		utilitiesAndroid.clickByAccessibilityId("continueButton", driver);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//android.widget.EditText").sendKeys("10março!");
		utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		MobileElement createPin0 = driver.findElementByXPath("//android.widget.TextView[@text='0']");
		createPin0.click();
		createPin0.click();
		createPin0.click();
		createPin0.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
		//voltar
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		//digitar pin
		MobileElement confirmPin3 = driver.findElementByXPath("//android.widget.TextView[@text='3']");
		confirmPin3.click();
		confirmPin3.click();
		confirmPin3.click();
		confirmPin3.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
		visualCheck.doVisualCheck(CHECK_CONFIRM_PIN);
		//começa a digitar pin diferente, apaga um e continua
		utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='1']", driver);
		MobileElement confirmPin6 = driver.findElementByXPath("//android.widget.TextView[@text='6']");
		confirmPin6.click();
		confirmPin6.click();
		//apagar um número
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p1/android.view.View/android.view.View[11]", driver);
		MobileElement confirmPin8 = driver.findElementByXPath("//android.widget.TextView[@text='8']");
		confirmPin8.click();
		confirmPin8.click();
		//erro screen pins diferentes
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
		driver.findElementByXPath("//android.widget.TextView[@text=\"Uh-oh! The PIN codes didn't match. Please try again.\"]");
		visualCheck.doVisualCheck(CHECK_PIN_DOESNT_MATCH_LOGIN);
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		createPin0 = driver.findElementByXPath("//android.widget.TextView[@text='0']");
		createPin0.click();
		createPin0.click();
		createPin0.click();
		createPin0.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
		MobileElement confirmPin0 = driver.findElementByXPath("//android.widget.TextView[@text='0']");
		confirmPin0.click();
		confirmPin0.click();
		confirmPin0.click();
		confirmPin0.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
		driver.findElementByXPath("//android.widget.TextView[@text='Want to use biometrics for future logins? You can activate it now, or activate it later in Settings.']");
		visualCheck.doVisualCheck(CHECK_BIOMETRICS_LOGIN);
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Weekly goal']")));
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		//logout button
		utilitiesAndroid.clickByAccessibilityId("menu_option_logout", driver);

		ConfigurationsAndroid.killDriver();
	}

}