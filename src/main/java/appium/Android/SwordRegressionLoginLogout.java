package appium.Android;

import java.net.MalformedURLException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

	@Test
	public void errosELoginPage() throws MalformedURLException {
		MobileActions mobileActions = new MobileActions(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		
		//login qr code
		utilitiesAndroid.clickByAccessibilityId("loginQRCodeButton", driver);
		driver.findElementByXPath("//android.widget.TextView[@text='Application permissions']");
		driver.findElementByXPath("//android.widget.TextView[@text='The app needs the following permissions to work correctly:']");
		driver.findElementByXPath("//android.widget.TextView[@text='We use the camera to read your patient card QR code and sign you into Sword']");
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		driver.findElementByXPath("//android.widget.TextView[@text='Present your card']");
		driver.findElementByXPath("//android.widget.TextView[@text=\"Hold the card still for a few seconds and don't cover the red square\"]");
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		driver.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button").click();
		//back no leitor de qr code
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View", driver);
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
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View[2]/android.widget.EditText/android.view.View/android.widget.ImageView", driver);
		utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='Danaher Canada']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Let's confirm Sword is right for you\"]")));
		//voltar para login screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View", driver);
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		//recover pass
		utilitiesAndroid.clickByAccessibilityId("loginRecoverPasswordButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Recover my password']")));
		driver.findElementByXPath("//android.widget.TextView[@text=\"Enter the email associated with your account. Then, we'll send you an email showing you how to reset your password\"]");
		MobileElement recoverPassTextField = driver.findElementByXPath("//android.widget.EditText");
		recoverPassTextField.click();
		recoverPassTextField.sendKeys("hjvhik");
		mobileActions.tapByCoordinates(769, 963);
		driver.findElementByXPath("//android.widget.TextView[@text='Invalid email']");
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
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		//erros do login
		//email vazio + senha vazia
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Sword']")));
		MobileElement loginButton = driver.findElementByAccessibilityId("loginButton");
		loginButton.click();
		driver.findElementByXPath("//android.widget.TextView[@text='Please enter your email address']");
		driver.findElementByXPath("//android.widget.TextView[@text='Please enter your password']");
		//email invalido
		MobileElement emailTextfield = driver.findElementByXPath("//android.widget.EditText[1]");
		emailTextfield.click();
		emailTextfield.sendKeys("uefhiwuehfiwe.com");
		mobileActions.tapByCoordinates(302, 186);
		driver.findElementByXPath("//android.widget.TextView[@text='Invalid email']");
		//email válido + pass em branco
		emailTextfield.clear();
		emailTextfield.sendKeys("jieojiow@jiwd.com");
		loginButton.click();
		driver.findElementByXPath("//android.widget.TextView[@text='Please enter your password']");
		//email certo e senha errada + ecrã de bloqueio
		emailTextfield.clear();
		emailTextfield.sendKeys("luiza@marco.com");
		MobileElement passwordTextfield = driver.findElementByXPath("//android.widget.EditText[2]");
		passwordTextfield.click();
		passwordTextfield.sendKeys("12345");
		loginButton.click();
		driver.findElementByXPath("//android.widget.TextView[@text='Your email or password is incorrect']");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.TextView[@text='Login'])")));
		utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
		utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
		driver.findElementByXPath("//android.widget.TextView[@text='Uh-oh!']");
		driver.findElementByXPath("//android.widget.TextView[@text=\"You've reached the maximum number of login attempts. Please try again in a few minutes.\"]");
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
	public void fazerLogin() throws MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();

		//login + create pin + set pin code later
		MobileElement el1 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[1]");
		el1.click();
		el1.clear();
		el1.sendKeys("f.silva@swordhealth.com");
		MobileElement el2 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el2.click();
		el2.sendKeys("Cabixuda12");
		utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
		driver.findElementByXPath("//android.widget.TextView[@text='Create your PIN code']");
		driver.findElementByXPath("//android.widget.TextView[@text='You can use your PIN code to log in any time your session expires.']");
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		driver.findElementByXPath("//android.widget.TextView[@text='Create your PIN code']");
		driver.findElementByXPath("//android.widget.TextView[@text='Set PIN code later']");
		utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='Set PIN code later']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Marta Casaca']")));
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		//logout button
		utilitiesAndroid.clickByAccessibilityId("menu_option_logout", driver);
		//login + not now
		driver.findElementByXPath("//android.widget.EditText[2]").sendKeys("Cabixuda12");
		utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
		driver.findElementByXPath("//android.widget.TextView[@text='Not now']").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Marta Casaca']")));
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		//logout button
		utilitiesAndroid.clickByAccessibilityId("menu_option_logout", driver);
		//login + define pin
		driver.findElementByXPath("//android.widget.EditText[2]").sendKeys("Cabixuda12");
		utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		MobileElement el19 = driver.findElementByXPath("//android.widget.TextView[@text='0']");
		el19.click();
		el19.click();
		el19.click();
		el19.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
		//voltar
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		//digitar pin
		MobileElement el22 = driver.findElementByXPath("//android.widget.TextView[@text='3']");
		el22.click();
		el22.click();
		el22.click();
		el22.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
		//começa a digitar pin diferente, apaga um e continua
		MobileElement el23 = driver.findElementByXPath("//android.widget.TextView[@text='1']");
		el23.click();
		MobileElement el24 = driver.findElementByXPath("//android.widget.TextView[@text='6']");
		el24.click();
		el24.click();
		//apagar um número
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View[12]", driver);
		MobileElement el26 = driver.findElementByXPath("//android.widget.TextView[@text='8']");
		el26.click();
		el26.click();
		//erro screen pins diferentes
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
		driver.findElementByXPath("//android.widget.TextView[@text=\"Uh-oh! The PIN codes didn't match. Please try again.\"]");
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		MobileElement el29 = driver.findElementByXPath("//android.widget.TextView[@text='0']");
		el29.click();
		el29.click();
		el29.click();
		el29.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
		MobileElement el30 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='0']");
		el30.click();
		el30.click();
		el30.click();
		el30.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
		driver.findElementByXPath("//android.widget.TextView[@text='Want to use biometrics for future logins? You can activate it now, or activate it later in Settings.']");
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Marta Casaca']")));
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		//logout button
		utilitiesAndroid.clickByAccessibilityId("menu_option_logout", driver);

		ConfigurationsAndroid.killDriver();
	}

	private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
		// TODO Auto-generated method stub
		
	}
}