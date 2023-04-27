package appium.iOS;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SwordRegressionLoginLogoutiOS {
	private IOSDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
	}

	private final static String CHECK_CREATE_PIN = "create_pin_screen";
	private final static String CHECK_PIN_DOESNT_MATCH_LOGIN = "pin_doesnt_match_login";
	private final static String CHECK_BIOMETRICS_LOGIN = "biometrics_login";
	private final static String CHECK_RECOVER_PASS_SUCCESS = "recover_pass_success";
	private final static String CHECK_ACCOUNT_TEMP_LOCKED = "account_temp_locked";
	private final static By CREATE_PIN_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='You can use your PIN code to log in any time your session expires.']");
	private final static By PIN_DOESNT_MATCH_LOGIN_SCREEN = MobileBy.xpath("//android.widget.TextView[@text=\"Uh-oh! The PIN codes didn't match. Please try again.\"]");
	private final static By BIOMETRICS_LOGIN_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Want to use biometrics for future logins? You can activate it now, or activate it later in Settings.']");
	private final static By RECOVER_PASS_SUCCESS_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\"If you have an active Sword account, you'll see an email from us showing you how to reset your password\"]");

	private WebElement waitForElement(WebDriverWait wait, By selector) {
		WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(selector));

		try {Thread.sleep(750); } catch (InterruptedException ign) {}

		return el;
	}

	@Test
	public void errosELoginPage() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		VisualCheckiOS visualCheckiOS = new VisualCheckiOS(driver);

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
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name=\"Danaher Canada\"]")).size() > 0) {
			utilitiesiOS.clickByAccessibilityId("Danaher Canada", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Let's confirm Sword is right for you\"]")));
		} else {
			utilitiesiOS.clickByAccessibilityId("Client 1", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Let's confirm Sword is right for you\"]")));
		}
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
		waitForElement(wait, RECOVER_PASS_SUCCESS_SCREEN);
		visualCheckiOS.doVisualCheck(CHECK_RECOVER_PASS_SUCCESS);
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
		emailTextfield.sendKeys("vinteum@sword.com");
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
	  
		utilitiesiOS.login("vinteum@sword.com", "Test1234!", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		utilitiesiOS.clickByAccessibilityId("menu_option_logout", driver);
		//novo login
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		driver.findElementByAccessibilityId("loginPasswordTextfield").sendKeys("Test1234!");
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
		driver.findElementByAccessibilityId("loginPasswordTextfield").sendKeys("Test1234!");
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