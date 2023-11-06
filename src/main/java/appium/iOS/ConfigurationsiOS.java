package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ConfigurationsiOS {

    public final static String VALIDATION_PATH = "src/visual tests/iPhone 11 Pro/Login"; //GitHub path to baselines. It should be something like "src/visual tests/DEVICE/TEST" and must be changed for every test performed
    private static IOSDriver<MobileElement> driver;

    public static IOSDriver<MobileElement> getDriver() throws MalformedURLException {
        if(driver == null) {
            createDriver();
        }
        return driver;
    }

    private static void createDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("platformVersion", "17.0.3"); //your device iOS version
        desiredCapabilities.setCapability("deviceName", "iPhone 11 Pro"); //your device model
        desiredCapabilities.setCapability("udid", "00008030-001115363A7A802E"); //your device UDID
        desiredCapabilities.setCapability("automationName", "XCUITest");
        desiredCapabilities.setCapability("xcodeOrgId", "698N4JU9B9");
        desiredCapabilities.setCapability("xcodeSigningId", "iPhone Developer");
        desiredCapabilities.setCapability("app", "/Users/luizaspiegel/Downloads/SWORDHealth640.ipa"); //local path to the test build
        desiredCapabilities.setCapability("updatedWDABundleId", "com.luizateste2.wda.runner"); //your WDA bundle id
        desiredCapabilities.setCapability("showXcodeLog", "true");
        desiredCapabilities.setCapability("wdaLocalPort", "8205");
        desiredCapabilities.setCapability("useNewWDA", "true");

        try {
            driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void killDriver() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
