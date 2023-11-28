package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class SwordGoFeedbackBugInvestigation {
	private IOSDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
	}
	
	@Test
	public void swordGo() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);

		//login
		utilitiesiOS.newLogin("dptgotest@test.com", "test1234!", driver);
		//abrir session details
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_9", driver);
		//esperar carregar
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//iniciar sessão go
		utilitiesiOS.clickByAccessibilityId("sword_go_card", driver);
		//COMEÇO DO LOOP - esperar até abrir o ecrã dos materiais necessários
		for (int i=1; i<21; i++) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//iniciar a sessão
			utilitiesiOS.clickByAccessibilityId("I'm ready", driver);
			//esperar a primeira explicação inteira e mais um pouco
			try {
				Thread.sleep(35000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//tap next exercise
			utilitiesiOS.clickByAccessibilityId("Next exercise", driver);
			//esperar a segunda explicação inteira e mais um pouco
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//tap no ecrã
			utilitiesiOS.clickByAccessibilityId("Sword Health", driver);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//finalizar a sessão
			utilitiesiOS.clickByAccessibilityId("Finish", driver);
			utilitiesiOS.clickByAccessibilityId("Yes", driver);
			//abrir o ecrã do feedback
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='How did you feel today?']")));
			//dar feedback
			mobileActions.tapByCoordinates(93, 368);
			mobileActions.tapByCoordinates(95, 616);
			//scroll pra mostrar o resto do ecrã
			MobileElement rateExperience = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='How would you rate your experience today?']");
			MobileElement description = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Your feedback matters – this helps us monitor your progress, make adjustments, and more.']");
			mobileActions.swipeByElements(rateExperience, description);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			utilitiesiOS.clickByXPath("(//XCUIElementTypeImage[@name=\"ic_star_rating\"])[5]", driver);
			utilitiesiOS.clickByAccessibilityId("Submit", driver);
			//esperar carregar o session details
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//iniciar sessão go
			utilitiesiOS.clickByAccessibilityId("sword_go_card", driver);
			//FINAL DO LOOP
			System.out.println(i + "x");
		}

		ConfigurationsiOS.killDriver();
	}
}