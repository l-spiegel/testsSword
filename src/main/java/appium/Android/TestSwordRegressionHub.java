package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSwordRegressionHub {

	private final static String VALIDATION_PATH = ConfigurationsAndroid.VALIDATION_PATH;
	private final static String BASELINE = "COMP_";
	private final static String CHECK_HUB_WITHOUT_PROGRAMS_SCREEN = "hub_without_programs";
	private final static String CHECK_HUB_SCREEN_ELIGIBLE_TOP_SCREEN = "hub_eligible_top";
	private final static String CHECK_HUB_SCREEN_ELIGIBLE_BOTTOM_SCREEN = "hub_eligible_bottom";

	private AndroidDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsAndroid.getDriver();
	}

	@Test
	public void hubScreenAllEligible() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActions mobileActions = new MobileActions(driver);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();

		//login
		utilitiesAndroid.newLogin("luiza.preventive@sword.com", "Test1234!", driver);
		//validar hub screen
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//android.widget.TextView[@text='On-Call']");
		driver.findElementByXPath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']");
		driver.findElementByXPath("//android.widget.TextView[@text='Chat with a Specialist']");
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Explore our programs']")).size() < 1) {
			VisualCheck.doVisualCheck(CHECK_HUB_WITHOUT_PROGRAMS_SCREEN);
			System.out.println("Hub screen without program cards");
			ConfigurationsAndroid.killDriver();
		} else {
			driver.findElementByXPath("//android.widget.TextView[@text='Explore our programs']");
			driver.findElementByXPath("//android.widget.TextView[@text='Learn more about our programs below to find the best fit for you.']");
			driver.findElementByXPath("//android.widget.TextView[@text='Digital Physical Therapy']");
			driver.findElementByXPath("//android.widget.TextView[@text='Physical therapy you can do anytime, anywhere.']");
			driver.findElementByXPath("//android.widget.TextView[@text='Eligible']");
			driver.findElementByXPath("//android.widget.TextView[@text='Learn more']");
			driver.findElementByXPath("//android.widget.TextView[@text='Get started']");
			VisualCheck.doVisualCheck(CHECK_HUB_SCREEN_ELIGIBLE_TOP_SCREEN);
			//open dpt learn more
			utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_0_learn_more_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Who are recovering from a recent surgery or injury']")));
			//back to hub screen
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//open dpt get started
			utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_0_get_started_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Digital Physical Therapy']")));
			//back to hub screen
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//scroll to show more cards
			MobileElement programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
			MobileElement homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
			mobileActions.swipeByElements(programsCard0, homeOnCallCard);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			MobileElement programsCard2 = driver.findElementByAccessibilityId("on_call_programs_card_2");
			mobileActions.swipeByElements(programsCard2, programsCard0);
			VisualCheck.doVisualCheck(CHECK_HUB_SCREEN_ELIGIBLE_BOTTOM_SCREEN);
			//validate bloom program card
			driver.findElementByXPath("//android.widget.TextView[@text='Bloom']");
			driver.findElementByXPath("//android.widget.TextView[@text='Innovative, digital pelvic health therapy from the comfort of home.']");
			driver.findElementByXPath("//android.widget.TextView[@text='Eligible']");
			driver.findElementByXPath("//android.widget.TextView[@text='Learn more']");
			driver.findElementByXPath("//android.widget.TextView[@text='Get started']");
			//validate move program card
			driver.findElementByXPath("//android.widget.TextView[@text='Move']");
			driver.findElementByXPath("//android.widget.TextView[@text='Get moving, stay moving, and keep pain away with your own personal training program.']");
			driver.findElementByXPath("//android.widget.TextView[@text='Eligible']");
			driver.findElementByXPath("//android.widget.TextView[@text='Learn more']");
			driver.findElementByXPath("//android.widget.TextView[@text='Get started']");
			//open bloom learn more
			utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_1_learn_more_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Designed for women:']")));
			//back to hub screen
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//scroll to show bloom card
			programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
			homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
			mobileActions.swipeByElements(programsCard0, homeOnCallCard);
			//open bloom get started
			utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_1_get_started_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Bloom']")));
			//back to hub screen
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//scroll to show move card
			programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
			homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
			mobileActions.swipeByElements(programsCard0, homeOnCallCard);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			programsCard2 = driver.findElementByAccessibilityId("on_call_programs_card_2");
			mobileActions.swipeByElements(programsCard2, programsCard0);
			//open move learn more
			utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_2_learn_more_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Who want to learn and try exercise']")));
			//back to hub screen
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//scroll to show move card
			programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
			homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
			mobileActions.swipeByElements(programsCard0, homeOnCallCard);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			programsCard2 = driver.findElementByAccessibilityId("on_call_programs_card_2");
			mobileActions.swipeByElements(programsCard2, programsCard0);
			//open move get started
			utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_2_get_started_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Move']")));
			//back to hub screen
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);

			ConfigurationsAndroid.killDriver();
		}
	}

	@Test
	public void hubScreenEnrolled() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActions mobileActions = new MobileActions(driver);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		VisualCheck visualCheck = new VisualCheck(driver);

		//login
		utilitiesAndroid.newLogin("luiza.preventive@sword.com", "Test1234!", driver);
		//validar hub screen
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//android.widget.TextView[@text='On-Call']");
		driver.findElementByXPath("//android.widget.TextView[@text='Get on-demand support from a Clinical Pain Specialist via chat.']");
		driver.findElementByXPath("//android.widget.TextView[@text='Chat with a Specialist']");
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Explore our programs']")).size() < 0) {
			visualCheck.doVisualCheck(CHECK_HUB_WITHOUT_PROGRAMS_SCREEN);
			System.out.println("Hub screen without program cards");
			ConfigurationsAndroid.killDriver();
		}
		driver.findElementByXPath("//android.widget.TextView[@text='Explore our programs']");
		driver.findElementByXPath("//android.widget.TextView[@text='Learn more about our programs below to find the best fit for you.']");
		driver.findElementByXPath("//android.widget.TextView[@text='Digital Physical Therapy']");
		driver.findElementByXPath("//android.widget.TextView[@text='Physical therapy you can do anytime, anywhere.']");
		driver.findElementByXPath("//android.widget.TextView[@text='Eligible']");
		driver.findElementByXPath("//android.widget.TextView[@text='Learn more']");
		driver.findElementByXPath("//android.widget.TextView[@text='Get started']");
		visualCheck.doVisualCheck(CHECK_HUB_SCREEN_ELIGIBLE_TOP_SCREEN);
		//open dpt learn more
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_0_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Who are recovering from a recent surgery or injury']")));
		//back to hub screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//open dpt get started
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_0_get_started_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Digital Physical Therapy']")));
		//back to hub screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//scroll to show more cards
		MobileElement programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
		MobileElement homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
		mobileActions.swipeByElements(programsCard0, homeOnCallCard);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		MobileElement programsCard2 = driver.findElementByAccessibilityId("on_call_programs_card_2");
		mobileActions.swipeByElements(programsCard2, programsCard0);
		visualCheck.doVisualCheck(CHECK_HUB_SCREEN_ELIGIBLE_BOTTOM_SCREEN);
		//validate bloom program card
		driver.findElementByXPath("//android.widget.TextView[@text='Bloom']");
		driver.findElementByXPath("//android.widget.TextView[@text='Innovative, digital pelvic health therapy from the comfort of home.']");
		driver.findElementByXPath("//android.widget.TextView[@text='Eligible']");
		driver.findElementByXPath("//android.widget.TextView[@text='Learn more']");
		driver.findElementByXPath("//android.widget.TextView[@text='Get started']");
		//validate move program card
		driver.findElementByXPath("//android.widget.TextView[@text='Move']");
		driver.findElementByXPath("//android.widget.TextView[@text='Get moving, stay moving, and keep pain away with your own personal training program.']");
		driver.findElementByXPath("//android.widget.TextView[@text='Eligible']");
		driver.findElementByXPath("//android.widget.TextView[@text='Learn more']");
		driver.findElementByXPath("//android.widget.TextView[@text='Get started']");
		//open bloom learn more
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_1_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Designed for women:']")));
		//back to hub screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//scroll to show bloom card
		programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
		homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
		mobileActions.swipeByElements(programsCard0, homeOnCallCard);
		//open bloom get started
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_1_get_started_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Bloom']")));
		//back to hub screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//scroll to show move card
		programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
		homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
		mobileActions.swipeByElements(programsCard0, homeOnCallCard);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		programsCard2 = driver.findElementByAccessibilityId("on_call_programs_card_2");
		mobileActions.swipeByElements(programsCard2, programsCard0);
		//open move learn more
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_2_learn_more_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Who want to learn and try exercise']")));
		//back to hub screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//scroll to show move card
		programsCard0 = driver.findElementByAccessibilityId("on_call_programs_card_0");
		homeOnCallCard = driver.findElementByAccessibilityId("home_on_call_card");
		mobileActions.swipeByElements(programsCard0, homeOnCallCard);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		programsCard2 = driver.findElementByAccessibilityId("on_call_programs_card_2");
		mobileActions.swipeByElements(programsCard2, programsCard0);
		//open move get started
		utilitiesAndroid.clickByAccessibilityId("on_call_programs_card_2_get_started_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Welcome to Move']")));
		//back to hub screen
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.widget.Button", driver);

		ConfigurationsAndroid.killDriver();
	}
}