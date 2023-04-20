package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ConfigurationsiOS {

    private static IOSDriver<MobileElement> driver;

    public static IOSDriver<MobileElement> getDriver() throws MalformedURLException {
        if(driver == null) {
            createDriver();
        }
        return driver;
    }

    private static void createDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("platformVersion", "16.1.1");
        desiredCapabilities.setCapability("deviceName", "iPhone 11 Pro");
        desiredCapabilities.setCapability("udid", "00008030-001115363A7A802E");
        desiredCapabilities.setCapability("automationName", "XCUITest");
        desiredCapabilities.setCapability("xcodeOrgId", "698N4JU9B9");
        desiredCapabilities.setCapability("xcodeSigningId", "iPhone Developer");
        desiredCapabilities.setCapability("app", "/Users/luizaspiegel/Downloads/SWORDHealth1811.ipa");
        desiredCapabilities.setCapability("updatedWDABundleId", "com.luizateste2.wda.runner");
        desiredCapabilities.setCapability("showXcodeLog", "true");
        desiredCapabilities.setCapability("wdaLocalPort", "8205");
        desiredCapabilities.setCapability("usePrebuiltWDA", "true");

        try {
            driver = new IOSDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
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
