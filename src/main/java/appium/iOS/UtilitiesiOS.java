package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class UtilitiesiOS {

    public void clickByAccessibilityId(String id, IOSDriver<MobileElement> driver) {
        driver.findElementByAccessibilityId(id).click();
    }
}
