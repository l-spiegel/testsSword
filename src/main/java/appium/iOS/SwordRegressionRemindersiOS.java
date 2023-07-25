package appium.iOS;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class SwordRegressionRemindersiOS {

	private final static String VALIDATION_PATH = ConfigurationsiOS.VALIDATION_PATH;
	private final static String BASELINE = "COMP_";

	private final static String CHECK_SET_REMINDER_4_MORE_DAYS_EMPTY_SCREEN = "set_reminder_4_more_days_empty";
	private final static String CHECK_NOTIFY_ME_SCREEN = "notify_me";
	private final static String CHECK_SET_REMINDER_4_MORE_DAYS_SET_SCREEN = "set_reminder_4_more_days_set";
	private final static String CHECK_MY_REMINDERS_2_SESSION_REMINDERS_SET_SCREEN = "my_reminders_2_session_reminders_set";
	private final static String CHECK_SET_REMINDER_2_MORE_DAYS_EMPTY_SCREEN = "set_reminder_2_more_days_empty";
	private final static String CHECK_MY_REMINDERS_3_SESSION_REMINDERS_SET_SCREEN = "my_reminders_3_session_reminders_set";
	private final static String CHECK_SET_REMINDER_2_MORE_DAYS_SET_SCREEN = "set_reminder_2_more_days_set";
	private final static String CHECK_SET_REMINDER_1_MORE_DAY_EMPTY_SCREEN = "set_reminder_1_more_days_empty";
	private final static String CHECK_SET_REMINDER_1_MORE_DAY_SET_SCREEN = "set_reminder_1_more_day_set";
	private final static String CHECK_MY_REMINDERS_5_SESSION_REMINDERS_SET_SCREEN = "my_reminders_5_session_reminders_set";
	private final static String CHECK_REMINDERS_BADGE_POPUP_SCREEN = "reminders_badge_popup";
	private final static String CHECK_UPDATE_REMINDER_BEFORE_UPDATE_SCREEN = "update_reminder_before_update";
	private final static String CHECK_UPDATE_REMINDER_AFTER_UPDATE_SCREEN = "update_reminder_after_update";
	private final static String CHECK_UPDATE_REMINDER_POPUP_SCREEN = "update_reminder_popup";
	private final static String CHECK_MY_REMINDERS_AFTER_UPDATE_REMINDER_SCREEN = "my_reminders_after_update_reminder";
	private final static String CHECK_MY_REMINDERS_AFTER_DELETE_REMINDER_SCREEN = "my_reminders_after_delete_reminder";
	private final static String CHECK_MY_REMINDERS_SETTING_SAME_DAY_REMINDER_SCREEN = "my_reminders_same_day_reminder";

	private IOSDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
	}

	@Test
	public void virtualPt() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		VisualCheck visualCheck = new VisualCheck(driver);

		//fazer login
		utilitiesiOS.newLogin("vinteum@sword.com", "Test1234!", driver);
		//wait carregar home
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		//verificar texto do card
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set reminders']");
		//clicar set new reminder
		utilitiesiOS.clickByAccessibilityId("home_card_weekly_goal_reminders_buttons", driver);
		//validar ecrã
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set a new reminder']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set reminders for at least 4 session days per week to stay on track!']");
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		//abrir menu
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		//clicar set reminders
		utilitiesiOS.clickByAccessibilityId("menu_option_set_reminders", driver);
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		//clicar set reminders de novo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header_close_button")));
		utilitiesiOS.clickByAccessibilityId("header_close_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Set reminders']")));
		utilitiesiOS.clickByAccessibilityId("menu_option_set_reminders", driver);
		//screenshot set reminder 4 more days empty screen
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		visualCheck.doVisualCheck(CHECK_SET_REMINDER_4_MORE_DAYS_EMPTY_SCREEN);
		byte[] newReminder4DaysEmpty = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//selecionar 2 dias
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_0", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_2", driver);
		//mudar a hora
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_time_option", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Time']")));
		
		String txt = "10";
		String txt2 = "15";
		String txt3 = "AM";
		List<MobileElement> pw = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));
		// set first PickerWheel - hour
		pw.get(0).sendKeys(txt);
		// set second PickerWheel - minutes
		pw.get(1).sendKeys(txt2);
		// set third PickerWheel - AM/PM
		pw.get(2).sendKeys(txt3);
		
		utilitiesiOS.clickByAccessibilityId("Done", driver);
		//abrir notify me
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='At time of session']");
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_notification_option", driver);
		//validar notify me
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Notify me']")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='At time of session']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='10 minutes before']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='30 minutes before']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='1 hour before']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='8 hours before']");
		//teste visual notify me
		visualCheck.doVisualCheck(CHECK_NOTIFY_ME_SCREEN);
		//mudar um valor
		utilitiesiOS.clickByAccessibilityId("session_reminders_notify_me_option_2", driver);
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		//validar que notify me mudou
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='30 minutes before']");
		//screenshot set reminder 4 more days set screen - comparação com o set reminder 4 more days empty screen
		visualCheck.doVisualCheck(CHECK_SET_REMINDER_4_MORE_DAYS_SET_SCREEN);
		byte[] newReminder4DaysSet = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result1 = driver
				.getImagesSimilarity(newReminder4DaysSet, newReminder4DaysEmpty, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result1.getVisualization().length, is(greaterThan(0)));
		assertThat(result1.getScore(), is(greaterThan(0.71)));
		String baselineFilename = VALIDATION_PATH + "/" + BASELINE + "set_reminder_4_empty_and_set" + ".png";
		File comparison1 = new File(baselineFilename);
		result1.storeVisualization(comparison1);
		System.out.println("Set new reminder 4 days empty and set - Similarity of: " + result1.getScore());
		//salvar
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		//validar my reminders
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='My reminders']");
		String remindersWeeklyGoalLabel1 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Set a reminder for 2 more session days", remindersWeeklyGoalLabel1);
		//screenshot my reminders 2 session reminders set
		visualCheck.doVisualCheck(CHECK_MY_REMINDERS_2_SESSION_REMINDERS_SET_SCREEN);
		byte[] myReminders2SessionRemindersSet = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//voltar pra home
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		utilitiesiOS.clickByAccessibilityId("header_close_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		//validar texto novo do card
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Manage reminders']");
		//clicar my reminders
		utilitiesiOS.clickByAccessibilityId("home_card_weekly_goal_reminders_buttons", driver);
		//criar novo reminder com a mesma hora do primeiro com 1 dia
		utilitiesiOS.clickByAccessibilityId("session_reminders_my_reminders_new_reminder_button", driver);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set reminders for at least 2 more session days per week to stay on track!']");
		//screenshot set reminder 2 more days empty screen - comparação com o set reminder 4 more days empty screen
		visualCheck.doVisualCheck(CHECK_SET_REMINDER_2_MORE_DAYS_EMPTY_SCREEN);
		byte[] newReminder2DaysEmpty = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result2 = driver
				.getImagesSimilarity(newReminder2DaysEmpty, newReminder4DaysEmpty, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result2.getVisualization().length, is(greaterThan(0)));
		assertThat(result2.getScore(), is(greaterThan(0.72)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "set_reminders_4_2_empty" + ".png";
		File comparison2 = new File(baselineFilename);
		result2.storeVisualization(comparison2);
		System.out.println("Set new reminders 4 and 2 more days - Similarity of: " + result2.getScore());
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_1", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_time_option", driver);
		List<MobileElement> pw2 = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));
		// set first PickerWheel
		pw2.get(0).sendKeys(txt);
		// set second PickerWheel
		pw2.get(1).sendKeys(txt2);
		// set third PickerWheel - AM/PM
		pw2.get(2).sendKeys(txt3);
	
		utilitiesiOS.clickByAccessibilityId("Done", driver);
		//screenshot set reminders 2 more days set screen - comparação com set reminder 2 more days empty screen
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		visualCheck.doVisualCheck(CHECK_SET_REMINDER_2_MORE_DAYS_SET_SCREEN);
		byte[] newReminder2DaysSet = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result3 = driver
				.getImagesSimilarity(newReminder2DaysSet, newReminder2DaysEmpty, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result3.getVisualization().length, is(greaterThan(0)));
		assertThat(result3.getScore(), is(greaterThan(0.79)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "set_reminder_2_empty_and_set" + ".png";
		File comparison3 = new File(baselineFilename);
		result3.storeVisualization(comparison3);
		System.out.println("Set new reminders 2 more days empty and set - Similarity of: " + result3.getScore());
		//teste visual - comparar o set reminder 2 more days set com o set reminder 4 more days set
		SimilarityMatchingResult result4 = driver
				.getImagesSimilarity(newReminder2DaysSet, newReminder4DaysSet, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result4.getVisualization().length, is(greaterThan(0)));
		assertThat(result4.getScore(), is(greaterThan(0.34)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "set_reminder_4_2_set" + ".png";
		File comparison4 = new File(baselineFilename);
		result4.storeVisualization(comparison4);
		System.out.println("Set new reminders 4 and 2 more days set - Similarity of: " + result4.getScore());
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//screenshot my reminders 3 session reminders set - comparação com o my reminders 2 session reminders set
		visualCheck.doVisualCheck(CHECK_MY_REMINDERS_3_SESSION_REMINDERS_SET_SCREEN);
		byte[] myReminders3SessionRemindersSet = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result5 = driver
				.getImagesSimilarity(myReminders3SessionRemindersSet, myReminders2SessionRemindersSet, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result5.getVisualization().length, is(greaterThan(0)));
		assertThat(result5.getScore(), is(greaterThan(0.53)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "my_reminders_3_2_sessions_set" + ".png";
		File comparison5 = new File(baselineFilename);
		result5.storeVisualization(comparison5);
		System.out.println("My reminders 2 and 3 session reminders set - Similarity of: " + result5.getScore());
		//voltar a home
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		//abrir reminders pelo menu
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		utilitiesiOS.clickByAccessibilityId("menu_option_set_reminders", driver);
		//verificar o texto dos reminders
		String reminderFor1Day = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Set a reminder for 1 more session day", reminderFor1Day);
		//criar novo reminder com 2 dias e notify me diferente
		utilitiesiOS.clickByAccessibilityId("session_reminders_my_reminders_new_reminder_button", driver);
		//screenshot set reminder 1 more day empty screen - comparar com o set reminder 2 more days empty screen
		visualCheck.doVisualCheck(CHECK_SET_REMINDER_1_MORE_DAY_EMPTY_SCREEN);
		byte[] newReminder1DayEmpty = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result6 = driver
				.getImagesSimilarity(newReminder1DayEmpty, newReminder2DaysEmpty, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result6.getVisualization().length, is(greaterThan(0)));
		assertThat(result6.getScore(), is(greaterThan(0.74)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "set_reminders_2_1_empty" + ".png";
		File comparison6 = new File(baselineFilename);
		result6.storeVisualization(comparison6);
		System.out.println("Set new reminders 2 and 1 more day empty - Similarity of: " + result6.getScore());
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set reminders for at least 1 more session day per week to stay on track!']");
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_3", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_4", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_notification_option", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_notify_me_option_4", driver);
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		//validar valor do notify me
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='8 hours before']");
		//screenshot set reminder 1 more day set screen - comparar com set reminder 1 more day empty screen
		visualCheck.doVisualCheck(CHECK_SET_REMINDER_1_MORE_DAY_SET_SCREEN);
		byte[] newReminder1DaySet = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result7 = driver
				.getImagesSimilarity(newReminder1DaySet, newReminder1DayEmpty, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result7.getVisualization().length, is(greaterThan(0)));
		assertThat(result7.getScore(), is(greaterThan(0.74)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "set_reminders_1_empty_and_set" + ".png";
		File comparison7 = new File(baselineFilename);
		result7.storeVisualization(comparison7);
		System.out.println("Set new reminders 1 more day empty and set - Similarity of: " + result7.getScore());
		//comparação do set reminder 1 more day set screen com o set reminder 2 more days set screen
		SimilarityMatchingResult result8 = driver
				.getImagesSimilarity(newReminder1DaySet, newReminder2DaysSet, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result8.getVisualization().length, is(greaterThan(0)));
		assertThat(result8.getScore(), is(greaterThan(0.34)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "set_reminders_2_1_set" + ".png";
		File comparison8 = new File(baselineFilename);
		result8.storeVisualization(comparison8);
		System.out.println("Set new reminders 2 and 1 more day set - Similarity of: " + result8.getScore());
		//salvar
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		String remindersWeeklyGoalLabel2 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Nice job! Your reminders are all set.", remindersWeeklyGoalLabel2);
		//screenshot my reminders 5 session reminders set
		visualCheck.doVisualCheck(CHECK_MY_REMINDERS_5_SESSION_REMINDERS_SET_SCREEN);
		byte[] myReminders5SessionRemindersSet = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//comparar com o my reminders com 3 session reminders set
		SimilarityMatchingResult result9 = driver
				.getImagesSimilarity(myReminders5SessionRemindersSet, myReminders3SessionRemindersSet, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result9.getVisualization().length, is(greaterThan(0)));
		assertThat(result9.getScore(), is(greaterThan(0.54)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "my_reminders_5_3_sessions_set" + ".png";
		File comparison9 = new File(baselineFilename);
		result9.storeVisualization(comparison9);
		System.out.println("My reminders 3 and 5 session reminders set - Similarity of: " + result9.getScore());
		//voltar pra home
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		utilitiesiOS.clickByAccessibilityId("header_close_button", driver);
		//validar badge dos reminders
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeButton[@name='Ok']")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Congrats! You earned a new badge!']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Reminders Scheduled']");
		//teste visual
		visualCheck.doVisualCheck(CHECK_REMINDERS_BADGE_POPUP_SCREEN);
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Ok']", driver);
		//abrir reminders
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		utilitiesiOS.clickByAccessibilityId("menu_option_set_reminders", driver);
		//abrir um reminder
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_2", driver);
		//validar update reminder
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Update reminder']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Your 4 weekly goal session days are set. You can schedule more session days.']");
		//screenshot update reminder antes de alterar
		visualCheck.doVisualCheck(CHECK_UPDATE_REMINDER_BEFORE_UPDATE_SCREEN);
		byte[] updateReminderBeforeUpdate = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//editar um reminder
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_6", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_time_option", driver);
		String txt4 = "22";
		String txt5 = "39";
		List<MobileElement> pw3 = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));
		// set first PickerWheel
		pw3.get(0).sendKeys(txt4);
		// set second PickerWheel
		pw3.get(1).sendKeys(txt5);
		
		utilitiesiOS.clickByAccessibilityId("Done", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_notification_option", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_notify_me_option_2", driver);
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		//screenshot update reminder depois de alterar
		visualCheck.doVisualCheck(CHECK_UPDATE_REMINDER_AFTER_UPDATE_SCREEN);
		//comparar com o update reminder de antes de alterar
		byte[] updateReminderAfterUpdate = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result10 = driver
				.getImagesSimilarity(updateReminderAfterUpdate, updateReminderBeforeUpdate, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result10.getVisualization().length, is(greaterThan(0)));
		assertThat(result10.getScore(), is(greaterThan(0.95)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "update_reminder_after_before_update" + ".png";
		File comparison10 = new File(baselineFilename);
		result10.storeVisualization(comparison10);
		System.out.println("Update reminder before and after update - Similarity of: " + result10.getScore());
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='There are changes to be saved. Are you sure you want to leave?']");
		//teste visual popup
		visualCheck.doVisualCheck(CHECK_UPDATE_REMINDER_POPUP_SCREEN);
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='No']", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_update_reminder_button", driver);
		//screenshot pra comparar o my reminder com o reminder atualizado
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		visualCheck.doVisualCheck(CHECK_MY_REMINDERS_AFTER_UPDATE_REMINDER_SCREEN);
		byte[] myRemindersAfterUpdate = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result11 = driver
				.getImagesSimilarity(myRemindersAfterUpdate, myReminders5SessionRemindersSet, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result11.getVisualization().length, is(greaterThan(0)));
		assertThat(result11.getScore(), is(greaterThan(0.80)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "my_reminders_after_update_reminder" + ".png";
		File comparison11 = new File(baselineFilename);
		result11.storeVisualization(comparison11);
		System.out.println("My reminders after update reminder vs before - Similarity of: " + result11.getScore());
		//deletar um reminder
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_2", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Update reminder']")));
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_delete_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		//screenshot my reminders depois do delete - comparar com o my reminders depois do update
		visualCheck.doVisualCheck(CHECK_MY_REMINDERS_AFTER_DELETE_REMINDER_SCREEN);
		byte[] myRemindersAfterDelete = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result12 = driver
				.getImagesSimilarity(myRemindersAfterDelete, myRemindersAfterUpdate, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result12.getVisualization().length, is(greaterThan(0)));
		assertThat(result12.getScore(), is(greaterThan(0.75)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "my_reminders_after_delete_reminder" + ".png";
		File comparison12 = new File(baselineFilename);
		result12.storeVisualization(comparison12);
		System.out.println("My reminders after delete reminder - Similarity of: " + result12.getScore());
		//tentar criar um novo reminder sem escolher um dia
		utilitiesiOS.clickByAccessibilityId("session_reminders_my_reminders_new_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Set a new reminder']")));
		MobileElement saveWithoutDay = driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button");
		saveWithoutDay.click();
		saveWithoutDay.click();
		//voltar para reminders
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		//screenshot my reminders - comparar com o my reminders depois do delete reminder
		byte[] myRemindersAfterTryingCreateEmptyReminder = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result13 = driver
				.getImagesSimilarity(myRemindersAfterTryingCreateEmptyReminder, myRemindersAfterDelete, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result13.getVisualization().length, is(greaterThan(0)));
		assertThat(result13.getScore(), is(greaterThan(0.99)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "my_reminders_trying_create_empty_reminder" + ".png";
		File comparison13 = new File(baselineFilename);
		result13.storeVisualization(comparison13);
		System.out.println("My reminders after trying to create an empty reminder - Similarity of: " + result13.getScore());
		//Fazer update, sair sem salvar e clicar em yes
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_1", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_6", driver);
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Yes']", driver);
		//comparar com o my reminders depois de tentar criar o reminder vazio
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		byte[] myRemindersAfterUpdateNotSaved = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result14 = driver
				.getImagesSimilarity(myRemindersAfterUpdateNotSaved, myRemindersAfterTryingCreateEmptyReminder, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result14.getVisualization().length, is(greaterThan(0)));
		assertThat(result14.getScore(), is(greaterThan(0.99)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "my_reminders_update_without_saving" + ".png";
		File comparison14 = new File(baselineFilename);
		result14.storeVisualization(comparison14);
		System.out.println("My reminders after not saving an update - Similarity of: " + result14.getScore());
		//criar novo reminder com mesmo dia, mas hora diferente de um anterior
		utilitiesiOS.clickByAccessibilityId("session_reminders_my_reminders_new_reminder_button", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_2", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		String remindersWeeklyGoalLabel3 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Set a reminder for 1 more session day", remindersWeeklyGoalLabel3);
		//screenshot comparar com my reminders depois de não salvar o update
		visualCheck.doVisualCheck(CHECK_MY_REMINDERS_SETTING_SAME_DAY_REMINDER_SCREEN);
		byte[] myRemindersSameDayReminder = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result15 = driver
				.getImagesSimilarity(myRemindersSameDayReminder, myRemindersAfterTryingCreateEmptyReminder, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result15.getVisualization().length, is(greaterThan(0)));
		assertThat(result15.getScore(), is(greaterThan(0.95)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "my_reminders_same_day_reminder" + ".png";
		File comparison15 = new File(baselineFilename);
		result15.storeVisualization(comparison15);
		System.out.println("My reminders after creating a same day reminder - Similarity of: " + result15.getScore());
		//voltar pra home
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		utilitiesiOS.clickByAccessibilityId("header_close_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));

		ConfigurationsiOS.killDriver();
	}
}