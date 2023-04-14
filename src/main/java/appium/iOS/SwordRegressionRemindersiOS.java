package appium.iOS;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import appium.Android.ConfigurationsAndroid;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class SwordRegressionRemindersiOS {

	private final static String VALIDATION_PATH = "/Users/luizaspiegel/Documents/image check/regression reminders/iOS";
	private final static String BASELINE = "remindersList_";

	private IOSDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
	}

	@Test
	public void virtualPt() throws IOException {
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();

		//fazer login
		utilitiesiOS.login("luiza@marco.com", "10março!", driver);
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
		//mudar um valor
		utilitiesiOS.clickByAccessibilityId("session_reminders_notify_me_option_2", driver);
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		//validar que notify me mudou
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='30 minutes before']");
		//salvar
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		//validar my reminders
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='My reminders']");
		String remindersWeeklyGoalLabel1 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Set a reminder for 2 more session days", remindersWeeklyGoalLabel1);
		File myReminders1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(myReminders1, new File("reminders1.jpg"));
		byte[] myRemindersComp1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
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
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		byte[] myRemindersComp2 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result = driver
				.getImagesSimilarity(myRemindersComp2, myRemindersComp1, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(lessThan(0.95)));
		String baselineFilename = VALIDATION_PATH + "/" + BASELINE + "1.png";
		File comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Similarity of: " + result.getScore());
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
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set reminders for at least 1 more session day per week to stay on track!']");
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_3", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_4", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_notification_option", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_notify_me_option_4", driver);
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		//validar valor do notify me
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='8 hours before']");
		//salvar
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		String remindersWeeklyGoalLabel2 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Nice job! Your reminders are all set.", remindersWeeklyGoalLabel2);
		byte[] myRemindersComp3 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result2 = driver
				.getImagesSimilarity(myRemindersComp3, myRemindersComp2, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result2.getVisualization().length, is(greaterThan(0)));
		assertThat(result2.getScore(), is(lessThan(0.95)));
		String baselineFilename2 = VALIDATION_PATH + "/" + BASELINE + "2.png";
		File comparison2 = new File(baselineFilename2);
		result2.storeVisualization(comparison2);
		System.out.println("Similarity of: " + result2.getScore());
		//voltar pra home
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		utilitiesiOS.clickByAccessibilityId("header_close_button", driver);
		//validar badge dos reminders
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeButton[@name='Ok']")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Congrats! You earned a new badge!']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Reminders Scheduled']");
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Ok']", driver);
		//abrir reminders
		utilitiesiOS.clickByAccessibilityId("header_menu_button", driver);
		utilitiesiOS.clickByAccessibilityId("menu_option_set_reminders", driver);
		//abrir um reminder
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_2", driver);
		//validar update reminder
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Update reminder']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Your 4 weekly goal session days are set. You can schedule more session days.']");
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
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='There are changes to be saved. Are you sure you want to leave?']");
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='No']", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_update_reminder_button", driver);
		//comparar com o my reminders com 3 reminders
		byte[] myRemindersComp4 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result3 = driver
				.getImagesSimilarity(myRemindersComp4, myRemindersComp3, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result3.getVisualization().length, is(greaterThan(0)));
		assertThat(result3.getScore(), is(lessThan(0.95)));
		String baselineFilename3 = VALIDATION_PATH + "/" + BASELINE + "3.png";
		File comparison3 = new File(baselineFilename3);
		result3.storeVisualization(comparison3);
		System.out.println("Similarity of: " + result3.getScore());
		//deletar um reminder
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_2", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Update reminder']")));
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_delete_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		//apagar essa desgraça de lista e meter uma comparação de imagens com o do update
		byte[] myRemindersComp5 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result4 = driver
				.getImagesSimilarity(myRemindersComp5, myRemindersComp4, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result4.getVisualization().length, is(greaterThan(0)));
		assertThat(result4.getScore(), is(lessThan(0.95)));
		String baselineFilename4 = VALIDATION_PATH + "/" + BASELINE + "4.png";
		File comparison4 = new File(baselineFilename4);
		result4.storeVisualization(comparison4);
		System.out.println("Similarity of: " + result4.getScore());
		//tentar criar um novo reminder sem escolher um dia
		utilitiesiOS.clickByAccessibilityId("session_reminders_my_reminders_new_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Set a new reminder']")));
		MobileElement saveWithoutDay = driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button");
		saveWithoutDay.click();
		saveWithoutDay.click();
		//voltar para reminders
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		//Fazer update, sair sem salvar e clicar em yes
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_1", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_6", driver);
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Yes']", driver);
		//comparar com o do delete reminder
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		byte[] myRemindersComp6 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result5 = driver
				.getImagesSimilarity(myRemindersComp6, myRemindersComp5, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result5.getVisualization().length, is(greaterThan(0)));
		assertThat(result5.getScore(), is(greaterThan(0.95)));
		String baselineFilename5 = VALIDATION_PATH + "/" + BASELINE + "5.png";
		File comparison5 = new File(baselineFilename5);
		result5.storeVisualization(comparison5);
		System.out.println("Similarity of: " + result5.getScore());
		//criar novo reminder com mesmo dia e hora diferente de um anterior
		utilitiesiOS.clickByAccessibilityId("session_reminders_my_reminders_new_reminder_button", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_week_day_2", driver);
		utilitiesiOS.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		String remindersWeeklyGoalLabel3 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Set a reminder for 1 more session day", remindersWeeklyGoalLabel3);
		//apagar a desgraça dessa lista e comparar com o anterior
		byte[] myRemindersComp7 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result6 = driver
				.getImagesSimilarity(myRemindersComp7, myRemindersComp6, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result6.getVisualization().length, is(greaterThan(0)));
		assertThat(result6.getScore(), is(lessThan(0.95)));
		String baselineFilename6 = VALIDATION_PATH + "/" + BASELINE + "6.png";
		File comparison6 = new File(baselineFilename6);
		result6.storeVisualization(comparison6);
		System.out.println("Similarity of: " + result6.getScore());
		//voltar pra home
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		utilitiesiOS.clickByAccessibilityId("header_close_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));

		ConfigurationsiOS.killDriver();
	}

	private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
		// TODO Auto-generated method stub
		
	}
}