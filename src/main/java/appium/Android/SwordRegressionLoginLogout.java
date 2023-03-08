package appium.Android;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.remote.MobileCapabilityType;


public class SwordRegressionLoginLogout {

	private AndroidDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsAndroid.getDriver();
	}

	@Test
	public void errosELoginPage() throws MalformedURLException {

		WebDriverWait wait = new WebDriverWait(driver,20);
		
		//login qr code
		driver.findElementByAccessibilityId("loginQRCodeButton").click();
		String el2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[1]").getText();
		Assert.assertEquals("Application permissions", el2);
		String el3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[2]").getText();
		Assert.assertEquals("The app needs the following permissions to work correctly:", el3);
		String el4 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[4]").getText();
		Assert.assertEquals("We use the camera to read your patient card QR code and sign you into Sword", el4);
		driver.findElementByXPath("//android.widget.Button").click();
		String el6 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[1]").getText();
		Assert.assertEquals("Present your card", el6);
		String el7 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[2]").getText();
		Assert.assertEquals("Hold the card still for a few seconds and don't cover the red square", el7);
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View/android.widget.Button").click();
		driver.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button").click();
		//back no leitor de qr code
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.TextView").click();
		MobileElement el11 = (MobileElement) driver.findElementByAccessibilityId("Navigate up");
		el11.click();
		el11.click();
		//abrir sing up
		driver.findElementByAccessibilityId("loginSignupButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Relieve back and joint pain from home']")));
		//voltar pra home
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button").click();
		//abrir recover pass
		driver.findElementByAccessibilityId("loginRecoverPasswordButton").click();
		//validar recover pass - falta fazer click fora do textfield com email inválido
		String el15 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.TextView").getText();
		Assert.assertEquals("Recover my password", el15);
		String el16 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("Enter the email associated with your account. Then, we'll send you an email showing you how to reset your password", el16);
		MobileElement el17 = (MobileElement) driver.findElementByXPath("//android.widget.EditText");
		el17.click();
		el17.sendKeys("jasod");
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.Button").click();
		String el19 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView[2]").getText();
		Assert.assertEquals("Invalid email", el19);
		MobileElement el20 = (MobileElement) driver.findElementByXPath("//android.widget.EditText");
		el20.click();
		el20.clear();
		el20.sendKeys("hgu@jdnfs.");
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.Button").click();
		MobileElement el23 = (MobileElement) driver.findElementByXPath("//android.widget.EditText");
		el23.click();
		el23.clear();
		el23.sendKeys("jbduoa@hoer.com");
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.Button").click();
		String el25 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView[1]").getText();
		Assert.assertEquals("Check your email", el25);
		String el26 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView[2]").getText();
		Assert.assertEquals("If you have an active Sword account, you'll see an email from us showing you how to reset your password", el26);
		driver.findElementByXPath("//android.widget.Button").click();
		//erros do login - falta email vazion, senha vazia, email inválido + clicar fora do textfield
		MobileElement el28 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[1]");
		el28.click();
		el28.clear();
		el28.sendKeys("l.spiegel+3@swordhealth.com");
		MobileElement el29 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el29.click();
		el29.sendKeys("1234");
		driver.findElementByAccessibilityId("loginButton").click();
		String el32 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[3]").getText();
		Assert.assertEquals("Your email or password is incorrect", el32);
		driver.findElementByAccessibilityId("loginButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Sword']")));
		driver.findElementByAccessibilityId("loginButton").click();
		String el35 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView[1]").getText();
		Assert.assertEquals("Uh-oh!", el35);
		String el36 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView[2]").getText();
		Assert.assertEquals("You've reached the maximum number of login attempts. Please try again in a few minutes.", el36);
		driver.findElementByXPath("//android.widget.Button").click();
		//adicionar teste de email inválido e senha inválida
		MobileElement el38 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[1]");
		el38.clear();
		el38.sendKeys("fhuihfie@iohfo.com");
		MobileElement el39 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el39.click();
		el39.sendKeys("1234");
		driver.findElementByAccessibilityId("loginButton").click();
		driver.findElementByAccessibilityId("loginButton").click();
		driver.findElementByAccessibilityId("loginButton").click();
		String incorrectEmail = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[3]").getText();
		Assert.assertEquals("Your email or password is incorrect", incorrectEmail);
		
		ConfigurationsAndroid.killDriver();
	}
	
	@Test
	public void fazerLogin() throws MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver,20);
	  
		MobileElement el1 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[1]");
		el1.click();
		el1.clear();
		el1.sendKeys("l.spiegel+4@swordhealth.com");
		MobileElement el2 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el2.click();
		el2.sendKeys("Test1234!");
		driver.findElementByAccessibilityId("loginButton").click();
		String createPin = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("Create your PIN code", createPin);
		String createPinInfo = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
		Assert.assertEquals("You can use your PIN code to log in any time your session expires.", createPinInfo); //string nova
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.Button").click();
		String createPinCodeScreen = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.TextView").getText();
		Assert.assertEquals("Create your PIN code", createPinCodeScreen);
		String pinCodeLater = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[13]/android.widget.TextView").getText();
		Assert.assertEquals("Set PIN code later", pinCodeLater);
		driver.findElementByXPath("//android.widget.TextView[@text='Set PIN code later']").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Almeida']")));
		driver.findElementByAccessibilityId("header_menu_button").click();
		//logout button
		driver.findElementByAccessibilityId("menu_option_logout").click();
		MobileElement el11 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el11.click();
		el11.sendKeys("Test1234!");
		driver.findElementByAccessibilityId("loginButton").click();
		driver.findElementByXPath("//android.widget.TextView[@text='Not now']").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Almeida']")));
		driver.findElementByAccessibilityId("header_menu_button").click();
		//logout button
		driver.findElementByAccessibilityId("menu_option_logout").click();
		MobileElement el16 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el16.click();
		el16.sendKeys("Test1234!");
		driver.findElementByAccessibilityId("loginButton").click();
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View/android.widget.Button").click();
		MobileElement el19 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='0']");
		el19.click();
		el19.click();
		el19.click();
		el19.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
		String el20 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.TextView").getText();
		Assert.assertEquals("Confirm your PIN code", el20);
		//volta
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.view.View/android.widget.Button").click();
		//digita pin
		MobileElement el22 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='3']");
		el22.click();
		el22.click();
		el22.click();
		el22.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
		//começa a digitar pin diferente, apaga um e continua
		MobileElement el23 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='1']");
		el23.click();
		MobileElement el24 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='6']");
		el24.click();
		el24.click();
		//apagar um número
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[12]").click();
		MobileElement el26 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='8']");
		el26.click();
		el26.click();
		//erro screen pins diferentes
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
		String el27 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
		Assert.assertEquals("Uh-oh! The PIN codes didn't match. Please try again.", el27);
		driver.findElementByXPath("//android.widget.Button").click();
		MobileElement el29 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='0']");
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
		String biometricsTxt = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[1]").getText();
		Assert.assertEquals("Want to use biometrics for future logins? You can activate it now, or activate it later in Settings.", biometricsTxt);
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View/android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Almeida']")));
		driver.findElementByAccessibilityId("header_menu_button").click();
		//logout button
		driver.findElementByAccessibilityId("menu_option_logout").click();

		ConfigurationsAndroid.killDriver();
	}

	private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
		// TODO Auto-generated method stub
		
	}
}