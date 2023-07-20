package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ConfigurationsAndroid {

    public final static String VALIDATION_PATH = "src/visual tests"; //GitHub path to baselines. It should be something like "src/visual tests/DEVICE/TEST" and must be changed for every test performed
    private static AndroidDriver<MobileElement> driver;

    public static AndroidDriver<MobileElement> getDriver() throws MalformedURLException {
        if(driver == null) {
            createDriver();
        }
        return driver;
    }

    private static void createDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:deviceName", "07111JEC201460"); //your device name foun with adb devices
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/luizaspiegel/Downloads/build release 510 2039/app-sword-release.apk"); //your local path to the test build
        desiredCapabilities.setCapability("appium:noReset", "false");
        desiredCapabilities.setCapability("appium:autoGrantPermissions", "true"); //if you want to show the app permissions, this must be turned to false

        try {
            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
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
