package appium;

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


public class buttonsChange {
	
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
    
    private final static By LOGIN_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Login']");
    private final static By RECOVER_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Recover my password']");
    private final static By EMAIL_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Check your email']");
    private final static By CREATE_PIN_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Create your PIN code']");
    private final static By DIFF_PIN_SCREEN = MobileBy.xpath("//android.widget.ImageView");
    private final static By BIOMETRICS_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Want to use biometrics for future logins? You can activate it now, or activate it later in Settings.']");
    private final static By NEW_REMINDER1_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Set a new reminder']");
    private final static By NEW_REMINDER2_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='30 minutes before']");
    private final static By MY_REMINDERS_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='My reminders']");
    private final static By UPDATE_REMINDER_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Update reminder']");
    private final static By REMINDER_POPUP_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='There are changes to be saved. Are you sure you want to leave?']");
    private final static By REMINDERS_BADGE_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Congrats! You earned a new badge!']");
    private final static By WRONG_PIN_SCREEN = MobileBy.xpath("//android.widget.ImageView");
    private final static By DIFF_PIN2_SCREEN = MobileBy.xpath("//android.widget.ImageView");
    private final static By SHARE_SESSION_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Would you like to share feedback from this session in chat?']");
    private final static By SHARE_EXERCISE_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Would you like to share feedback from this exercise in chat?']");
    private final static By BADGE_HOME_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Complete your 3rd exercise session']");
    private final static By BADGE_BADGES_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Complete your 18th exercise session']");
    private final static By PERSONAL_GOAL_ACHIEVE_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Did you achieve this goal?']");
    private final static By PERSONAL_GOAL_SUCCESS_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='You achieved one of your personal goals. Now, go celebrate!']");
    private final static By PERSONAL_GOAL_UNMARK_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Would you like to reopen this personal goal?']");
    private final static By HUB_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='On-Call']");
    private final static By EMAIL_LOCKED_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Uh-oh!']");
    private final static By APP_PERMISSIONS_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Application permissions']");
    private final static By PRESENT_CARD_SCREEN = MobileBy.xpath("//android.widget.TextView[@text='Present your card']");
    private final static By CAMERA_PERMISSION_SETTINGS_SCREEN = MobileBy.xpath("//android.widget.ImageView");
    private final static By CAMERA_PERMISSION_RETRY_SCREEN = MobileBy.xpath("//android.widget.ImageView");
    
    AndroidDriver<MobileElement> driver;

	private AndroidDriver<MobileElement> inicializarAppium() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "android");
	    desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
	    desiredCapabilities.setCapability("appium:deviceName", "07111JEC201460");
	    desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/luizaspiegel/Downloads/app-sword-qa1589.apk");
	    desiredCapabilities.setCapability("appium:noReset", "false");
	    desiredCapabilities.setCapability("appium:autoGrantPermissions", "false");
	//    desiredCapabilities.setCapability("app", getResource("/Users/luizaspiegel/Downloads/app-sword-qa1517.apk").toString()); //colocar a build de testes ou de baseline
	    
	    driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
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
		AndroidDriver<MobileElement> driver = inicializarAppium();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		MobileActions mobileActions = new MobileActions(driver);
		
		//login screen
		waitForElement(wait, LOGIN_SCREEN);
		doVisualCheck(CHECK_LOGIN);
		//recover password
		MobileElement el14 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Recover']");
		el14.click();
		waitForElement(wait, RECOVER_SCREEN);
		doVisualCheck(CHECK_RECOVER);
		//check your email
		driver.findElementByXPath("//android.widget.EditText").sendKeys("shbsjd@jdioj.com");
		driver.findElementByXPath("//android.widget.Button").click();
		waitForElement(wait, EMAIL_SCREEN);
		doVisualCheck(CHECK_EMAIL_SCREEN);
		//create pin code email
		driver.findElementByXPath("//android.widget.Button").click();
		driver.findElementByXPath("//android.widget.EditText[1]").sendKeys("l.spiegel+3@swordhealth.com");
		driver.findElementByXPath("//android.widget.EditText[2]").sendKeys("Test1234!");
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.Button").click();
		waitForElement(wait, CREATE_PIN_SCREEN);
		doVisualCheck(CHECK_CREATE_PIN_SCREEN);
		//pin codes diferentes email
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		MobileElement el1 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='0']");
		el1.click();
		el1.click();
		el1.click();
		el1.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
		MobileElement el2 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='7']");
		el2.click();
		el2.click();
		el2.click();
		el2.click();
		waitForElement(wait, DIFF_PIN_SCREEN);
		doVisualCheck(CHECK_DIFF_PIN_SCREEN);
		//biometria
		driver.findElementByXPath("//android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		MobileElement el3 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='0']");
		el3.click();
		el3.click();
		el3.click();
		el3.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
		MobileElement el4 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='0']");
		el4.click();
		el4.click();
		el4.click();
		el4.click();
		waitForElement(wait, BIOMETRICS_SCREEN);
		doVisualCheck(CHECK_BIOMETRICS_SCREEN);
		//set new reminder
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Almeida']")));
		driver.findElementByAccessibilityId("header_menu_button").click();
		driver.findElementByAccessibilityId("menu_option_set_reminders").click();
		waitForElement(wait, NEW_REMINDER1_SCREEN);
		doVisualCheck(CHECK_NEW_REMINDER1_SCREEN);
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_0").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_notification_option").click();
		driver.findElementByAccessibilityId("session_reminders_notify_me_option_2").click();
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.Button").click();
		waitForElement(wait, NEW_REMINDER2_SCREEN);
		doVisualCheck(CHECK_NEW_REMINDER2_SCREEN);
		//my reminders
		driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button").click();
		waitForElement(wait, MY_REMINDERS_SCREEN);
		doVisualCheck(CHECK_MY_REMINDERS_SCREEN);
		//update reminder
		driver.findElementByAccessibilityId("session_reminders_reminder_1").click();
		waitForElement(wait, UPDATE_REMINDER_SCREEN);
		doVisualCheck(CHECK_UPDATE_REMINDER_SCREEN);
		//pop-up reminders alteração sem salvar
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_3").click();
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.widget.Button").click();
		waitForElement(wait, REMINDER_POPUP_SCREEN);
		doVisualCheck(CHECK_REMINDER_POPUP_SCREEN);
		//pop-up badge sem carrossel - reminders
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_4").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_6").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_update_reminder_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.widget.Button").click();
		driver.findElementByAccessibilityId("header_menu_button").click();
		waitForElement(wait, REMINDERS_BADGE_SCREEN);
		doVisualCheck(CHECK_REMINDERS_BADGE_SCREEN);
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button").click();
		//change pin senha errada
		driver.findElementByAccessibilityId("header_menu_button").click();
		driver.findElementByAccessibilityId("menu_option_change_pin").click();
		MobileElement el5 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='1']");
		el5.click();
		el5.click();
		el5.click();
		el5.click();
		waitForElement(wait, WRONG_PIN_SCREEN);
		doVisualCheck(CHECK_WRONG_PIN_SCREEN);
		driver.findElementByXPath("//android.widget.Button").click();
		//pin codes diferentes no change pin
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Enter your PIN code']")));
		MobileElement el6 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='0']");
		el6.click();
		el6.click();
		el6.click();
		el6.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		MobileElement el7 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='1']");
		el7.click();
		el7.click();
		el7.click();
		el7.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Confirm your PIN code']")));
		MobileElement el8 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='5']");
		el8.click();
		el8.click();
		el8.click();
		el8.click();
		waitForElement(wait, DIFF_PIN2_SCREEN);
		doVisualCheck(CHECK_DIFF_PIN2_SCREEN);
		driver.findElementByXPath("//android.widget.Button").click();
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[12]/android.view.View/android.widget.Button").click();
		//pop-up share feedback da sessão no chat
		MobileElement kitDelivery = (MobileElement) driver.findElementByAccessibilityId("home_card_delivery_kit_status");
		MobileElement ptCard = (MobileElement) driver.findElementByAccessibilityId("home_card_pt");
		mobileActions.swipeByElements(kitDelivery, ptCard);
		MobileElement weeklyGoal = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Weekly goal']");
		mobileActions.swipeByElements(weeklyGoal, kitDelivery);
		MobileElement sessions = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");
	//	mobileActions.swipeByElements(sessions, weeklyGoal);
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Next Session']")).size() > 0) {
			driver.findElementByAccessibilityId("home_card_session_details_0_prev_date_button").click();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@content-desc='home_card_session_details_1_see_more_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_1").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Exercises']")));
		driver.findElementByAccessibilityId("session_details_session_overview_card_share_button").click();
		waitForElement(wait, SHARE_SESSION_SCREEN);
		doVisualCheck(CHECK_SHARE_SESSION_SCREEN);
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]").click();
		//pop-up share feedback exercício no chat
		driver.findElementByAccessibilityId("session_details_exercise_0_card_share_button").click();
		waitForElement(wait, SHARE_EXERCISE_SCREEN);
		doVisualCheck(CHECK_SHARE_EXERCISE_SCREEN);
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]").click();
		//pop-ups badges não atingidos - home
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.widget.Button").click();
		MobileElement progress = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Progress']");
		MobileElement sessions2 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");
		mobileActions.swipeByElements(progress, sessions2);
		MobileElement personalGoals = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Personal goals']");
		MobileElement painChart = (MobileElement) driver.findElementByAccessibilityId("home_screen_pain_chart");
		mobileActions.swipeByElements(personalGoals, painChart);
		MobileElement myBadges = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='My badges']");
		mobileActions.swipeByElements(myBadges, painChart);
		driver.findElementByAccessibilityId("home_badges_card_badge_1").click();
		waitForElement(wait, BADGE_HOME_SCREEN);
		doVisualCheck(CHECK_BADGE_HOME_SCREEN);
		//pop-ups badges não atingidos - badges
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button").click();
		driver.findElementByAccessibilityId("home_badges_card_see_more_button").click();
		driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_0_badge_3']/android.view.View").click();
		waitForElement(wait, BADGE_BADGES_SCREEN);
		doVisualCheck(CHECK_BADGE_BADGES_SCREEN);
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button").click();
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.Button").click();
		//pop-ups personal goals
		driver.findElementByAccessibilityId("home_card_personal_goals_0").click();
		waitForElement(wait, PERSONAL_GOAL_ACHIEVE_SCREEN);
		doVisualCheck(CHECK_PERSONAL_GOAL_ACHIEVE_SCREEN);
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button").click();
		waitForElement(wait, PERSONAL_GOAL_SUCCESS_SCREEN);
		doVisualCheck(CHECK_PERSONAL_GOAL_SUCCESS_SCREEN);
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button").click();
		driver.findElementByAccessibilityId("home_card_personal_goals_1").click();
		waitForElement(wait, PERSONAL_GOAL_UNMARK_SCREEN);
		doVisualCheck(CHECK_PERSONAL_GOAL_UNMARK_SCREEN);
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button").click();
		//hub screen
		driver.findElementByAccessibilityId("bottom_navigation_hub_tab").click();
		waitForElement(wait, HUB_SCREEN);
		doVisualCheck(CHECK_HUB_SCREEN);
		//logout
		driver.findElementByAccessibilityId("header_menu_button").click();
		driver.findElementByAccessibilityId("menu_option_logout").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Login']")));
		//muitas tentativas de login com email/senha errado
		driver.findElementByXPath("//android.widget.EditText[2]").sendKeys("1234");
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Login']")));
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Login']")));
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.Button").click();
		waitForElement(wait, EMAIL_LOCKED_SCREEN);
		doVisualCheck(CHECK_EMAIL_LOCKED_SCREEN);
		//permissões da app - qr code
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.widget.Button").click();
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.Button").click();
		waitForElement(wait, APP_PERMISSIONS_SCREEN);
		doVisualCheck(CHECK_APP_PERMISSIONS_SCREEN);
		//present your card screen
		driver.findElementByXPath("//android.widget.Button").click();
		waitForElement(wait, PRESENT_CARD_SCREEN);
		doVisualCheck(CHECK_PRESENT_CARD_SCREEN);
		driver.findElementByXPath("//android.widget.Button").click();
		//não dar autorização a camera
		driver.findElementById("com.android.permissioncontroller:id/permission_deny_button").click();
		waitForElement(wait, CAMERA_PERMISSION_SETTINGS_SCREEN);
		doVisualCheck(CHECK_CAMERA_PERMISSION_SETTINGS_SCREEN);
		//dar autorização a camera retry
		driver.findElementByXPath("//android.widget.Button").click();
		try {
			Thread.sleep(15000);
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

