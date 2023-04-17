package appium.iOS;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import appium.iOS.MobileActionsiOS;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.CoreMatchers.*;


public class buttonsChangeiOS {
	
	private final static String VALIDATION_PATH = "/Users/luizaspiegel/Desktop/visualizations";
	private final static String BASELINE = "BASELINE_";
	private final static double MATCH_THRESHOLD = 0.1;
	
    private final static String CHECK_LOGIN = "login_screen";
    private final static String CHECK_RECOVER = "recover_screen";
    private final static String CHECK_EMAIL_SCREEN = "email_screen";
    private final static String CHECK_CREATE_PIN_SCREEN = "create_pin_screen";
    private final static String CHECK_DIFF_PIN_SCREEN = "diff_pin_screen";
    private final static String CHECK_BIOMETRICS_SCREEN = "biometrics_screen";
    private final static String CHECK_NEW_REMINDER1_SCREEN = "new_reminder1_screen";
    private final static String CHECK_NEW_REMINDER2_SCREEN = "new_reminder2_screen";
    private final static String CHECK_MY_REMINDERS_SCREEN = "my_reminders_screen";
    private final static String CHECK_UPDATE_REMINDER_SCREEN = "update_reminder_screen";
    private final static String CHECK_REMINDER_POPUP_SCREEN = "reminder_popup_screen";
    private final static String CHECK_REMINDERS_BADGE_SCREEN = "reminders_badge_screen";
    private final static String CHECK_WRONG_PIN_SCREEN = "wrong_pin_screen";
    private final static String CHECK_DIFF_PIN2_SCREEN = "diff_pin2_screen";
    private final static String CHECK_SHARE_SESSION_SCREEN = "share_session_screen";
    private final static String CHECK_SHARE_EXERCISE_SCREEN = "share_exercise_screen";
    private final static String CHECK_BADGE_HOME_SCREEN = "badge_home_screen";
    private final static String CHECK_BADGE_BADGES_SCREEN = "badge_badges_screen";
    private final static String CHECK_PERSONAL_GOAL_ACHIEVE_SCREEN = "personal_goal_achieve_screen";
    private final static String CHECK_PERSONAL_GOAL_SUCCESS_SCREEN = "personal_goal_success_screen";
    private final static String CHECK_PERSONAL_GOAL_UNMARK_SCREEN = "personal_goal_unmark_screen";
    private final static String CHECK_HUB_SCREEN = "hub_screen";
    private final static String CHECK_EMAIL_LOCKED_SCREEN = "email_locked_screen";
    private final static String CHECK_APP_PERMISSIONS_SCREEN = "app_permissions_screen";
    private final static String CHECK_PRESENT_CARD_SCREEN = "present_card_screen";
    private final static String CHECK_CAMERA_PERMISSION_SETTINGS_SCREEN = "camera_permission_settings_screen";
    private final static String CHECK_CAMERA_PERMISSION_RETRY_SCREEN = "camera_permission_retry_screen";
    
    private final static By LOGIN_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Login']");
    private final static By RECOVER_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Recover my password']");
    private final static By EMAIL_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Check your email']");
    private final static By CREATE_PIN_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']");
    private final static By DIFF_PIN_SCREEN = MobileBy.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage");
    private final static By BIOMETRICS_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Want to use Face ID for future logins? You can activate it now, or activate it later in Settings.']");
    private final static By NEW_REMINDER1_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Set a new reminder']");
    private final static By NEW_REMINDER2_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='30 minutes before']");
    private final static By MY_REMINDERS_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='My reminders']");
    private final static By UPDATE_REMINDER_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Update reminder']");
    private final static By REMINDER_POPUP_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='There are changes to be saved. Are you sure you want to leave?']");
    private final static By REMINDERS_BADGE_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Congrats! You earned a new badge!']");
    private final static By WRONG_PIN_SCREEN = MobileBy.xpath("//XCUIElementTypeImage");
    private final static By DIFF_PIN2_SCREEN = MobileBy.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage");
    private final static By SHARE_SESSION_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Would you like to share feedback from this session in chat?']");
    private final static By SHARE_EXERCISE_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Would you like to share feedback from this exercise in chat?']");
    private final static By BADGE_HOME_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Complete your 3rd exercise session']");
    private final static By BADGE_BADGES_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Complete your 18th exercise session']");
    private final static By PERSONAL_GOAL_ACHIEVE_SCREEN = MobileBy.xpath("//XCUIElementTypeButton[@name='Yes']");
    private final static By PERSONAL_GOAL_SUCCESS_SCREEN = MobileBy.xpath("//XCUIElementTypeButton[@name='Close']");
    private final static By PERSONAL_GOAL_UNMARK_SCREEN = MobileBy.xpath("//XCUIElementTypeButton[@name='Yes']");
    private final static By HUB_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='On-Call']");
    private final static By EMAIL_LOCKED_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Uh-oh!']");
    private final static By APP_PERMISSIONS_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Application permissions']");
    private final static By PRESENT_CARD_SCREEN = MobileBy.xpath("//XCUIElementTypeStaticText[@name='Present your card']");
    private final static By CAMERA_PERMISSION_SETTINGS_SCREEN = MobileBy.xpath("//XCUIElementTypeImage");
    private final static By CAMERA_PERMISSION_RETRY_SCREEN = MobileBy.xpath("//XCUIElementTypeImage");
    
    IOSDriver<MobileElement> driver;

    private IOSDriver<MobileElement> inicializarAppium() throws MalformedURLException {
    	//Setting Desired Capabilities
    	DesiredCapabilities capabilities = new DesiredCapabilities();
    	capabilities.setCapability("platformName", "iOS");
   		capabilities.setCapability("platformVersion", "16.1.1");
   		capabilities.setCapability("deviceName", "iPhone 11 Pro");
   		capabilities.setCapability("udid", "00008030-001115363A7A802E");
    	capabilities.setCapability("automationName", "XCUITest");
    	capabilities.setCapability("xcodeOrgId", "698N4JU9B9");
   		capabilities.setCapability("xcodeSigningId", "iPhone Developer");
   		capabilities.setCapability("app", "/Users/luizaspiegel/Downloads/SWORDHealth1515.ipa");
   		capabilities.setCapability("updatedWDABundleId", "com.luizateste2.wda.runner");
   		capabilities.setCapability("showXcodeLog", "true");
    	capabilities.setCapability("wdaLocalPort", "8205");
    	capabilities.setCapability("appium:usePrebuiltWDA", "true");
    		
    	driver = new IOSDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
   	}

    private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
    		// TODO Auto-generated method stub
	    
	}

	private Object getResource(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private WebElement waitForElement(WebDriverWait wait, By selector) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(selector));

        try { Thread.sleep(750); } catch (InterruptedException ign) {}
       
        return el;
    }

	
	@Test
	public void buttons() throws Exception {
		IOSDriver<MobileElement> driver = inicializarAppium();
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		//login screen
		driver.findElementByAccessibilityId("Allow").click();
		waitForElement(wait, LOGIN_SCREEN);
		doVisualCheck(CHECK_LOGIN);
		//recover password
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Recover']").click();
		waitForElement(wait, RECOVER_SCREEN);
		doVisualCheck(CHECK_RECOVER);
		//check your email
		driver.findElementByAccessibilityId("recoverPasswordEmailTextfield").sendKeys("shbsjd@jdioj.com");
		driver.findElementByAccessibilityId("recoverPasswordButton").click();
		waitForElement(wait, EMAIL_SCREEN);
		doVisualCheck(CHECK_EMAIL_SCREEN);
		//create pin code email
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Ok']").click();
		driver.findElementByAccessibilityId("loginPasswordTextfield").sendKeys("Test1234!");
		driver.findElementByAccessibilityId("loginButton").click();
		waitForElement(wait, CREATE_PIN_SCREEN);
		doVisualCheck(CHECK_CREATE_PIN_SCREEN);
		//pin codes diferentes email
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Create PIN']").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el1 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
		el1.click();
		el1.click();
		el1.click();
		el1.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		MobileElement el2 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='7']");
		el2.click();
		el2.click();
		el2.click();
		el2.click();
		waitForElement(wait, DIFF_PIN_SCREEN);
		doVisualCheck(CHECK_DIFF_PIN_SCREEN);
		//biometria
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el3 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
		el3.click();
		el3.click();
		el3.click();
		el3.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		MobileElement el4 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='0']");
		el4.click();
		el4.click();
		el4.click();
		el4.click();
		waitForElement(wait, BIOMETRICS_SCREEN);
		doVisualCheck(CHECK_BIOMETRICS_SCREEN);
		//set new reminder
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Activate later']").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Your Physical Therapist']")));
		try {
			  Thread.sleep(5000);
		} catch (InterruptedException e) {
			  Thread.currentThread().interrupt();
		}
		driver.findElementByAccessibilityId("header_menu_button").click();
		driver.findElementByXPath("//XCUIElementTypeCell[@name='menu_option_set_reminders']/XCUIElementTypeOther/XCUIElementTypeOther").click();
		waitForElement(wait, NEW_REMINDER1_SCREEN);
		doVisualCheck(CHECK_NEW_REMINDER1_SCREEN);
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_0").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_notification_option").click();
		driver.findElementByAccessibilityId("session_reminders_notify_me_option_2").click();
		driver.findElementByAccessibilityId("1PxIcChevronLeft").click();
		waitForElement(wait, NEW_REMINDER2_SCREEN);
		doVisualCheck(CHECK_NEW_REMINDER2_SCREEN);
		//my reminders
		driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button").click();
		waitForElement(wait, MY_REMINDERS_SCREEN);
		doVisualCheck(CHECK_MY_REMINDERS_SCREEN);
		//update reminder
		driver.findElementByAccessibilityId("session_reminders_reminder_0").click();
		waitForElement(wait, UPDATE_REMINDER_SCREEN);
		doVisualCheck(CHECK_UPDATE_REMINDER_SCREEN);
		//pop-up reminders alteração sem salvar
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_3").click();
		driver.findElementByAccessibilityId("1PxIcChevronLeft").click();
		waitForElement(wait, REMINDER_POPUP_SCREEN);
		doVisualCheck(CHECK_REMINDER_POPUP_SCREEN);
		//pop-up badge sem carrossel - reminders
		driver.findElementByXPath("//XCUIElementTypeButton[@name='No']").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_4").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_6").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_update_reminder_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		driver.findElementByAccessibilityId("1PxIcChevronLeft").click();
		driver.findElementByAccessibilityId("header_menu_button").click();
		waitForElement(wait, REMINDERS_BADGE_SCREEN);
		doVisualCheck(CHECK_REMINDERS_BADGE_SCREEN);
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Ok']").click();
		//change pin senha errada
		driver.findElementByAccessibilityId("header_menu_button").click();
		driver.findElementByAccessibilityId("menu_option_change_pin").click();
		MobileElement el5 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='1']");
		el5.click();
		el5.click();
		el5.click();
		el5.click();
		waitForElement(wait, WRONG_PIN_SCREEN);
		doVisualCheck(CHECK_WRONG_PIN_SCREEN);
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']").click();
		//pin codes diferentes no change pin
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Enter your PIN code']")));
		MobileElement el6 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='0']");
		el6.click();
		el6.click();
		el6.click();
		el6.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el7 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='1']");
		el7.click();
		el7.click();
		el7.click();
		el7.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Confirm your PIN code']")));
		MobileElement el8 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='5']");
		el8.click();
		el8.click();
		el8.click();
		el8.click();
		waitForElement(wait, DIFF_PIN2_SCREEN);
		doVisualCheck(CHECK_DIFF_PIN2_SCREEN);
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Retry']").click();
		driver.findElementByAccessibilityId("1PxIcChevronLeft").click();
		driver.findElementByAccessibilityId("header_menu_button").click();
		//pop-up share feedback da sessão no chat
		MobileElement kitDelivery = (MobileElement) driver.findElementByAccessibilityId("home_card_delivery_kit_status");
		MobileElement ptCard = (MobileElement) driver.findElementByAccessibilityId("home_card_pt");
		mobileActions.swipeByElements(kitDelivery, ptCard);
		MobileElement weeklyGoal = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Weekly goal']");
		mobileActions.swipeByElements(weeklyGoal, kitDelivery);
		MobileElement sessions = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']");
		mobileActions.swipeByElements(sessions, weeklyGoal);
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@value='Next Session']")).size() > 0) {
			driver.findElementByAccessibilityId("home_card_session_details_0_prev_date_button").click();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_1_see_more_button")));
		driver.findElementByAccessibilityId("home_card_session_details_1").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Exercises']")));
		driver.findElementByAccessibilityId("session_details_session_overview_card_share_button").click();
		waitForElement(wait, SHARE_SESSION_SCREEN);
		doVisualCheck(CHECK_SHARE_SESSION_SCREEN);
		driver.findElementByXPath("//XCUIElementTypeButton[@name='No']").click();
		//pop-up share feedback exercício no chat
		driver.findElementByAccessibilityId("session_details_exercise_0_card_share").click();
		waitForElement(wait, SHARE_EXERCISE_SCREEN);
		doVisualCheck(CHECK_SHARE_EXERCISE_SCREEN);
		driver.findElementByXPath("//XCUIElementTypeButton[@name='No']").click();
		//pop-ups badges não atingidos - definePinLoginChangePinHome
		driver.findElementByAccessibilityId("1PxIcChevronLeft").click();
		MobileElement progress = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Progress']");
		MobileElement sessions2 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']");
		mobileActions.swipeByElements(progress, sessions2);
		MobileElement personalGoals = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Personal goals']");
		MobileElement painChart = (MobileElement) driver.findElementByXPath("//XCUIElementTypeCell[@name='home_screen_pain_chart_card']");
		mobileActions.swipeByElements(personalGoals, painChart);
		MobileElement myBadges = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='My badges']");
		mobileActions.swipeByElements(myBadges, painChart);
		driver.findElementByAccessibilityId("home_badges_card_badge_1").click();
		waitForElement(wait, BADGE_HOME_SCREEN);
		doVisualCheck(CHECK_BADGE_HOME_SCREEN);
		//pop-ups badges não atingidos - badges
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Got it']").click();
		driver.findElementByAccessibilityId("home_badges_card_see_more_button").click();
		driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_0_badge_3']/XCUIElementTypeOther").click();
		waitForElement(wait, BADGE_BADGES_SCREEN);
		doVisualCheck(CHECK_BADGE_BADGES_SCREEN);
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Got it']").click();
		driver.findElementByAccessibilityId("1PxIcChevronLeft").click();
		//pop-ups personal goals
		driver.findElementByAccessibilityId("home_card_personal_goals_0").click();
		waitForElement(wait, PERSONAL_GOAL_ACHIEVE_SCREEN);
		doVisualCheck(CHECK_PERSONAL_GOAL_ACHIEVE_SCREEN);
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Yes']").click();
		waitForElement(wait, PERSONAL_GOAL_SUCCESS_SCREEN);
		doVisualCheck(CHECK_PERSONAL_GOAL_SUCCESS_SCREEN);
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Close']").click();
		driver.findElementByAccessibilityId("home_card_personal_goals_1").click();
		waitForElement(wait, PERSONAL_GOAL_UNMARK_SCREEN);
		doVisualCheck(CHECK_PERSONAL_GOAL_UNMARK_SCREEN);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Yes']").click();
		//hub screen
		driver.findElementByAccessibilityId("bottom_navigation_hub_tab").click();
		waitForElement(wait, HUB_SCREEN);
		doVisualCheck(CHECK_HUB_SCREEN);
		//logout
		driver.findElementByAccessibilityId("header_menu_button").click();
		driver.findElementByAccessibilityId("menu_option_logout").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		//muitas tentativas de login com email/senha errado
		driver.findElementByAccessibilityId("loginPasswordTextfield").sendKeys("1234");
		MobileElement el9 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el9.click();
		el9.click();
		el9.click();
		waitForElement(wait, EMAIL_LOCKED_SCREEN);
		doVisualCheck(CHECK_EMAIL_LOCKED_SCREEN);
		//permissões da app - qr code
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Ok']").click();
		driver.findElementByAccessibilityId("loginQRCodeButton").click();
		waitForElement(wait, APP_PERMISSIONS_SCREEN);
		doVisualCheck(CHECK_APP_PERMISSIONS_SCREEN);
		//present your card screen
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Next']").click();
		waitForElement(wait, PRESENT_CARD_SCREEN);
		doVisualCheck(CHECK_PRESENT_CARD_SCREEN);
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Next']").click();
		//não dar autorização a camera
		driver.findElementByAccessibilityId("Don’t Allow").click();
		waitForElement(wait, CAMERA_PERMISSION_SETTINGS_SCREEN);
		doVisualCheck(CHECK_CAMERA_PERMISSION_SETTINGS_SCREEN);
		//dar autorização a camera retry
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Settings']").click();
		try {
			  Thread.sleep(10000);
		} catch (InterruptedException e) {
			  Thread.currentThread().interrupt();
		}
		waitForElement(wait, CAMERA_PERMISSION_RETRY_SCREEN);
		doVisualCheck(CHECK_CAMERA_PERMISSION_RETRY_SCREEN);
		
		driver.quit();
	}
		
	private void doVisualCheck(String checkName) throws Exception {
		
		String baselineFilename = VALIDATION_PATH + "/" + BASELINE + checkName + ".png";
		File baselineImg = new File(baselineFilename);

		// If no baseline image exists for this check, we should create a baseline image
		if (!baselineImg.exists()) {
			System.out.println(String.format("No baseline found for '%s' check; capturing baseline instead of checking", checkName));
			File newBaseline = driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(newBaseline, new File(baselineFilename));
			return;
		}

		// Otherwise, if we found a baseline, get the image similarity from Appium. In getting the similarity,
		// we also turn on visualization so we can see what went wrong if something did.

		SimilarityMatchingOptions opts = new SimilarityMatchingOptions();
		opts.withEnabledVisualization();

		SimilarityMatchingResult res = driver.getImagesSimilarity(baselineImg, driver.getScreenshotAs(OutputType.FILE), opts);

		// If the similarity is not high enough, consider the check to have failed
		if (res.getScore() < MATCH_THRESHOLD) {
			File failViz = new File(VALIDATION_PATH + "/FAIL_" + checkName + ".png");
			res.storeVisualization(failViz);

			throw new Exception(
					String.format("Visual check of '%s' failed; similarity match was only %f, and below the threshold of %f. Visualization written to %s.",
							checkName, res.getScore(), MATCH_THRESHOLD, failViz.getAbsolutePath()));
		}

		// Otherwise, it passed!
		System.out.println(String.format("Visual check of '%s' passed; similarity match was %f",
				checkName, res.getScore()));
		File successViz = new File(VALIDATION_PATH + "/SUCCESS_" + checkName + ".png");
		res.storeVisualization(successViz);
	}
}

