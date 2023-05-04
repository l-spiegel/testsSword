package appium.Android;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
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
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class SwordRegressionReminders {

	private final static String VALIDATION_PATH = "/Users/luizaspiegel/Documents/image check/regression reminders/Android";
	private final static String BASELINE = "COMP_";

	private AndroidDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsAndroid.getDriver();
	}
	
	@Test
	public void virtualPt() throws IOException {
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();

		//fazer login
		utilitiesAndroid.login("luiza@marco.com", "10março!", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Weekly goal']")));
		//clicar set new reminder
		utilitiesAndroid.clickByAccessibilityId("home_card_weekly_goal_reminders_button", driver);
		//validar ecrã
		driver.findElementByXPath("//android.widget.TextView[@text='Set a new reminder']");
		driver.findElementByXPath("//android.widget.TextView[@text='Set reminders for at least 4 more session days per week to stay on track!']");
		//voltar
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//android.view.View[@content-desc='home_card_weekly_goal']");
		//abrir menu
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		//clicar set reminders
		utilitiesAndroid.clickByAccessibilityId("menu_option_set_reminders", driver);
		//voltar
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		//clicar set reminders de novo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='PIN code and Biometrics']")));
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']")));
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Set reminders']")));
		utilitiesAndroid.clickByAccessibilityId("menu_option_set_reminders", driver);
		//selecionar 2 dias
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_0", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_2", driver);
		//mudar a hora
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_time_option", driver);
		utilitiesAndroid.clickByAccessibilityId("10", driver);
		utilitiesAndroid.clickByAccessibilityId("15", driver);
		driver.findElementById("android:id/am_label").click();
		driver.findElementById("android:id/button1").click();
		//abrir notify me
		driver.findElementByXPath("//android.widget.TextView[@text='At time of session']");
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_notification_option", driver);
		//validar notify me
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Notify me']")));
		driver.findElementByXPath("//android.widget.TextView[@text='At time of session']");
		driver.findElementByXPath("//android.widget.TextView[@text='10 minutes before']");
		driver.findElementByXPath("//android.widget.TextView[@text='30 minutes before']");
		driver.findElementByXPath("//android.widget.TextView[@text='1 hour before']");
		driver.findElementByXPath("//android.widget.TextView[@text='8 hours before']");
		//mudar um valor
		utilitiesAndroid.clickByAccessibilityId("session_reminders_notify_me_option_2", driver);
		//voltar
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		//validar que notify me mudou
		driver.findElementByXPath("//android.widget.TextView[@text='30 minutes before']");
		//salvar
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		//validar my reminders
		File myReminders1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(myReminders1, new File("reminders1.jpg"));
		driver.findElementByXPath("//android.widget.TextView[@text='My reminders']");
		String remindersWeeklyGoalLabel1 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Set a reminder for 2 more session days", remindersWeeklyGoalLabel1);
		List<MobileElement> listOfReminders1 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
		// Returns the size of the list / number of reminders in the list
		System.out.println("Number of reminders: " + listOfReminders1.size());
		//voltar pra definePinLoginChangePinHome
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']")));
		//validar texto novo do card
		driver.findElementByXPath("//android.widget.TextView[@text='Manage reminders']");
		//clicar manage reminders
		utilitiesAndroid.clickByAccessibilityId("home_card_weekly_goal_reminders_button", driver);
		//criar novo reminder com a mesma hora do primeiro com 1 dia
		utilitiesAndroid.clickByAccessibilityId("session_reminders_my_reminders_new_reminder_button", driver);
		driver.findElementByXPath("//android.widget.TextView[@text='Set reminders for at least 2 more session days per week to stay on track!']");
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_1", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_time_option", driver);
		utilitiesAndroid.clickByAccessibilityId("10", driver);
		utilitiesAndroid.clickByAccessibilityId("15", driver);
		driver.findElementById("android:id/am_label").click();
		driver.findElementById("android:id/button1").click();		
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		File myReminders2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(myReminders2, new File("reminders2.jpg"));
		List<MobileElement> listOfReminders2 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
		// Returns the size of the list / number of reminders in the list
		System.out.println("Number of reminders: " + listOfReminders2.size());
		//voltar a definePinLoginChangePinHome
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		//abrir reminders pelo menu
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		utilitiesAndroid.clickByAccessibilityId("menu_option_set_reminders", driver);
		//verificar o texto dos reminders
		String reminderFor1Day = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Set a reminder for 1 more session day", reminderFor1Day);
		//criar novo reminder com 2 dias e notify me diferente
		utilitiesAndroid.clickByAccessibilityId("session_reminders_my_reminders_new_reminder_button", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_3", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_4", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_notification_option", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_notify_me_option_4", driver);
		driver.findElementByXPath("//android.widget.Button").click();
		//validar valor do notify me
		driver.findElementByXPath("//android.widget.TextView[@text='8 hours before']");
		//salvar
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		String remindersWeeklyGoalLabel2 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Nice job! Your reminders are all set.", remindersWeeklyGoalLabel2);
		File myReminders3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(myReminders3, new File("reminders3.jpg"));
		List<MobileElement> listOfReminders3 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
		System.out.println("Number of reminders: " + listOfReminders3.size());
		//voltar pra definePinLoginChangePinHome
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		//validar popup da badge dos reminders
		if (driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View")).size() > 0) {
			driver.findElementByXPath("//android.widget.TextView[@text='Congrats! You earned a new badge!']");
			driver.findElementByXPath("//android.widget.TextView[@text='Reminders Scheduled']");
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button", driver);
		}
		//abrir reminders
		utilitiesAndroid.clickByAccessibilityId("home_card_weekly_goal_reminders_button", driver);
		//abrir um reminder
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_3", driver);
		//validar update reminder
		driver.findElementByXPath("//android.widget.TextView[@text='Update reminder']");
		driver.findElementByXPath("//android.widget.TextView[@text='Your 4 weekly goal session days are set. You can schedule more session days.']");
		//editar um reminder
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_6", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_time_option", driver);
		driver.findElementById("android:id/minutes").click();
		utilitiesAndroid.clickByAccessibilityId("40", driver);
		driver.findElementById("android:id/pm_label").click();
		driver.findElementById("android:id/button1").click();
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_notification_option", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_notify_me_option_2", driver);
		driver.findElementByXPath("//android.widget.Button").click();
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		driver.findElementByXPath("//android.widget.TextView[@text='There are changes to be saved. Are you sure you want to leave?']");
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_update_reminder_button", driver);
		//deletar um reminder
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_3", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Update reminder']")));
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_delete_reminder_button", driver); //como posso comparar a lista antes e depois??
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		File myReminders4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(myReminders4, new File("reminders4.jpg"));
		byte[] myRemindersComp1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		List<MobileElement> deleteReminder1 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
		if (deleteReminder1.size() < listOfReminders3.size()) {
			System.out.println("Reminder deleted, total reminders: " + deleteReminder1.size());
		} else {
			System.out.println("Reminder not deleted. Something went wrong");
			driver.closeApp();
		}
		//tentar criar um novo reminder sem escolher um dia
		utilitiesAndroid.clickByAccessibilityId("session_reminders_my_reminders_new_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Set a new reminder']")));
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		//voltar para reminders
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		//Fazer update, sair sem salvar e clicar em yes
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_1", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_6", driver);
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button", driver);
		File myReminders5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(myReminders5, new File("reminders5.jpg"));
		//comparar myReminders4 e myReminders5
		byte[] myRemindersComp2 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result = driver
				.getImagesSimilarity(myRemindersComp1, myRemindersComp2, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.95)));
		String baselineFilename = VALIDATION_PATH + "/" + BASELINE + ".png";
		File comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Similarity of: " + result.getScore());
		//criar novo reminder com mesmo dia e hora diferente de um anterior
		utilitiesAndroid.clickByAccessibilityId("session_reminders_my_reminders_new_reminder_button", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_2", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		String remindersWeeklyGoalLabel3 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Set a reminder for 1 more session day", remindersWeeklyGoalLabel3);
		File myReminders6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(myReminders6, new File("reminders6.jpg"));
		List<MobileElement> listOfReminders4 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
		System.out.println("Number of reminders: " + listOfReminders4.size());
		//voltar pra definePinLoginChangePinHome
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']")));

		ConfigurationsAndroid.killDriver();
	}

	private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
		// TODO Auto-generated method stub
		
	}
}