package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class PinAllScreens {

    private AndroidDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsAndroid.getDriver();
    }

    @Test
    public void definePinLoginChangePinHome() {
        MobileActions mobileActions = new MobileActions(driver);
        WebDriverWait wait = new WebDriverWait(driver,20);
        UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();

        //login
        driver.findElementByXPath("//android.widget.EditText[1]").sendKeys("vinteum@sword.com");
        driver.findElementByXPath("//android.widget.EditText[2]").sendKeys("Test1234!");
        utilitiesAndroid.clickByAccessibilityId("loginButton", driver);
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        //definir pin
        MobileElement definePin0 = driver.findElementByXPath("//android.widget.TextView[@text='0']");
        definePin0.click();
        definePin0.click();
        definePin0.click();
        definePin0.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
        MobileElement confirmPin0 = driver.findElementByXPath("//android.widget.TextView[@text='0']");
        confirmPin0.click();
        confirmPin0.click();
        confirmPin0.click();
        confirmPin0.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        driver.findElementByXPath("//android.widget.TextView[@text='Want to use biometrics for future logins? You can activate it now, or activate it later in Settings.']");
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Spiegel']")));
        //change pin
        utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
        utilitiesAndroid.clickByAccessibilityId("menu_option_change_pin", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        String forgotPinButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView").getText();
        Assert.assertEquals("Forgot your PIN?", forgotPinButton);
        //voltar
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
        //clicar em change pin
        utilitiesAndroid.clickByAccessibilityId("menu_option_change_pin", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        //digitar 3 digitos corretos
        MobileElement number0EnterPin1 = driver.findElementByXPath("//android.widget.TextView[@text='0']");
        number0EnterPin1.click();
        number0EnterPin1.click();
        number0EnterPin1.click();
        //apagar 1 digito
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[12]").click();
        //terminar de inserir o pin correto
        number0EnterPin1.click();
        number0EnterPin1.click();
        //voltar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
        utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
        //clicar em change pin
        utilitiesAndroid.clickByAccessibilityId("menu_option_change_pin", driver);
        //inserir 4 digitos no enter pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        MobileElement number0EnterPin2 = driver.findElementByXPath("//android.widget.TextView[@text='0']");
        number0EnterPin2.click();
        number0EnterPin2.click();
        number0EnterPin2.click();
        number0EnterPin2.click();
        //inserir 4 digitos no create pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
        MobileElement number3CreatePin = driver.findElementByXPath("//android.widget.TextView[@text='3']");
        number3CreatePin.click();
        number3CreatePin.click();
        number3CreatePin.click();
        number3CreatePin.click();
        //inserir 4 digitos diferentes no confirm pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
        MobileElement number7ConfirmPin = driver.findElementByXPath("//android.widget.TextView[@text='7']");
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        number7ConfirmPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        String pinDoesntMatch2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        String retryButton2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View/android.widget.TextView").getText();
        Assert.assertEquals("Uh-oh! The PIN codes didn't match. Please try again.", pinDoesntMatch2);
        Assert.assertEquals("Retry", retryButton2);
        //clicar em retry
        driver.findElementByXPath("//android.widget.Button").click();
        //inserir 4 digitos no create
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
        MobileElement number9CreatePin = driver.findElementByXPath("//android.widget.TextView[@text='9']");
        number9CreatePin.click();
        number9CreatePin.click();
        number9CreatePin.click();
        number9CreatePin.click();
        //inserir os mesmos 4 digitos no confirm pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
        MobileElement number9ConfirmPin = driver.findElementByXPath("//android.widget.TextView[@text='9']");
        number9ConfirmPin.click();
        number9ConfirmPin.click();
        number9ConfirmPin.click();
        number9ConfirmPin.click();
        //validar ecra de sucesso
        String newPinSuccess2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        Assert.assertEquals("New PIN code set successfully", newPinSuccess2);
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        driver.findElementByAccessibilityId("header_menu_button").click();
        //clicar em change pin
        driver.findElementByAccessibilityId("menu_option_change_pin").click();
        //inserir o pin errado
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        MobileElement number1EnterPin = driver.findElementByXPath("//android.widget.TextView[@text='1']");
        number1EnterPin.click();
        number1EnterPin.click();
        number1EnterPin.click();
        number1EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        String wrongPin1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        Assert.assertEquals("Wrong PIN code! \n" +
                "You have 4 more attempt(s)", wrongPin1);
        //retry
        driver.findElementByXPath("//android.widget.Button").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number2EnterPin = driver.findElementByXPath("//android.widget.TextView[@text='2']");
        number2EnterPin.click();
        number2EnterPin.click();
        number2EnterPin.click();
        number2EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        String wrongPin2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        Assert.assertEquals("Wrong PIN code! \n" +
                "You have 3 more attempt(s)", wrongPin2);
        //retry
        driver.findElementByXPath("//android.widget.Button").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number3EnterPin = driver.findElementByXPath("//android.widget.TextView[@text='3']");
        number3EnterPin.click();
        number3EnterPin.click();
        number3EnterPin.click();
        number3EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        String wrongPin3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        Assert.assertEquals("Wrong PIN code! \n" +
                "You have 2 more attempt(s)", wrongPin3);
        //retry
        driver.findElementByXPath("//android.widget.Button").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number4EnterPin = driver.findElementByXPath("//android.widget.TextView[@text='4']");
        number4EnterPin.click();
        number4EnterPin.click();
        number4EnterPin.click();
        number4EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        String wrongPin4 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
        Assert.assertEquals("Wrong PIN code! \n" +
                "You have 1 more attempt(s)", wrongPin4); //tem um bug na string
        //retry
        driver.findElementByXPath("//android.widget.Button").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
        //inserir o pin errado
        MobileElement number5EnterPin = driver.findElementByXPath("//android.widget.TextView[@text='5']");
        number5EnterPin.click();
        number5EnterPin.click();
        number5EnterPin.click();
        number5EnterPin.click();
        //validar ecrã de erro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView")));
        String pinError = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[1]").getText();
        String loginAgain = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[2]").getText();
        String backToLoginButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View/android.widget.TextView").getText();
        Assert.assertEquals("Uh-oh! That's 5 failed attempts", pinError);
        Assert.assertEquals("For security reasons, please log in again and set a new PIN code.", loginAgain);
        Assert.assertEquals("Back to login", backToLoginButton);
        //back to login
        driver.findElementByXPath("//android.widget.Button").click();
        //fazer login
        driver.findElementByXPath("//android.widget.EditText[2]").sendKeys("Test1234!");
        driver.findElementByAccessibilityId("loginButton").click();
        //definir pin
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.Button").click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
        MobileElement number6CreatePin = driver.findElementByXPath("//android.widget.TextView[@text='6']");
        number6CreatePin.click();
        number6CreatePin.click();
        number6CreatePin.click();
        number6CreatePin.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
        MobileElement number6ConfirmPin = driver.findElementByXPath("//android.widget.TextView[@text='6']");
        number6ConfirmPin.click();
        number6ConfirmPin.click();
        number6ConfirmPin.click();
        number6ConfirmPin.click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView").click();
        //abrir settings
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        driver.findElementByAccessibilityId("header_menu_button").click();
        //abrir change pin
        driver.findElementByAccessibilityId("menu_option_change_pin").click();
        //clicar em forgot pin
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.TextView").click();
        //validar que voltou pra login screen
        String welcomeSword = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.widget.TextView[1]").getText();
        Assert.assertEquals("Welcome to Sword", welcomeSword);

        System.out.println("O TESTE PASSOU");

        ConfigurationsAndroid.killDriver();
    }
}
