package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class SwordRegressionExerciseHistoryiOS {

    private IOSDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsiOS.getDriver();
    }

    @Test
    public void live() {
        WebDriverWait wait = new WebDriverWait(driver,20);
        UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
        MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
        VisualCheckiOS visualCheckiOS = new VisualCheckiOS(driver);

        //fazer login
        UtilitiesiOS.login("f.silva@swordhealth.com", "Cabixuda12", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
        //abrir session details
        MobileElement sessionsTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']"); //corrigir depois
        MobileElement ptCard = driver.findElementByAccessibilityId("home_card_pt");
        mobileActions.swipeByElements(sessionsTxt, ptCard);
        if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@value='Next Session']")).size() > 0) {
            utilitiesiOS.clickByAccessibilityId("home_card_session_details_0_prev_date_button", driver);
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_1_see_more_button")));
        utilitiesiOS.clickByAccessibilityId("home_card_session_details_1", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Exercises']")));
        //abrir exercise history do exercício 1
        //validar ecrã
        //teste visual
        //clicar no vídeo
        //validar que fechou - teste visual
        //abrir exercise history do exercício 2
        //validar ecrã
        //teste visual
        //clicar no vídeo
        //validar que fechou - teste visual
        //voltar pra home

        ConfigurationsiOS.killDriver();
    }

}
