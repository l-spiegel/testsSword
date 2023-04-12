package appium.Android;

import java.net.MalformedURLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class SwordRegressionReminders {

	private AndroidDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsAndroid.getDriver();
	}
	
	@Test
	public void virtualPt() throws MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
	  
		//fazer login
		utilitiesAndroid.login("f.silva@swordhealth.com", "Cabixuda12", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Marta Casaca']")));
		//clicar set new reminder
		utilitiesAndroid.clickByAccessibilityId("home_card_weekly_goal_reminders_button", driver);
		//validar ecrã
		driver.findElementByXPath("//android.widget.TextView[@text='Set a new reminder']");
		driver.findElementByXPath("//android.widget.TextView[@text='Set reminders for at least 4 more session days per week to stay on track!']");
		//voltar
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
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
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
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
		String notifyMe1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[9]/android.widget.TextView[2]").getText();
		Assert.assertEquals("At time of session", notifyMe1);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_notification_option", driver);
		//validar notify me
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Notify me']")));
		String notifyMe2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView").getText();
		Assert.assertEquals("Notify me", notifyMe2);
		String atTime = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.widget.TextView").getText();
		Assert.assertEquals("At time of session", atTime);
		String min10 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView").getText();
		Assert.assertEquals("10 minutes before", min10);
		String min30 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.widget.TextView").getText();
		Assert.assertEquals("30 minutes before", min30);
		String hour1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[4]/android.widget.TextView").getText();
		Assert.assertEquals("1 hour before", hour1);
		String hour8 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[5]/android.widget.TextView").getText();
		Assert.assertEquals("8 hours before", hour8);
		//mudar um valor
		utilitiesAndroid.clickByAccessibilityId("session_reminders_notify_me_option_2", driver);
		//voltar
		driver.findElementByXPath("//android.widget.Button").click();
		//validar que notify me mudou
		String notifyMe3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[9]/android.widget.TextView[2]").getText();
		Assert.assertEquals("30 minutes before", notifyMe3);
		//salvar
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		//validar my reminders
		String el38 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView").getText();
		Assert.assertEquals("My reminders", el38);
		String remindersWeeklyGoalLabel1 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Set a reminder for 2 more session days", remindersWeeklyGoalLabel1);
		List<MobileElement> listOfReminders1 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
		// Returns the size of the list / number of reminders in the list
			System.out.println("Number of reminders: " + listOfReminders1.size());
		//voltar pra home
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']")));
		//validar texto novo do card
		String el41 = driver.findElementByXPath("//android.view.View[@content-desc='home_card_weekly_goal']/android.view.View[2]/android.widget.TextView").getText();
		Assert.assertEquals("Manage reminders", el41);
		//clicar manage reminders
		utilitiesAndroid.clickByAccessibilityId("home_card_weekly_goal_reminders_button", driver);
		//criar novo reminder com a mesma hora do primeiro com 1 dia
		utilitiesAndroid.clickByAccessibilityId("session_reminders_my_reminders_new_reminder_button", driver);
		String newReminder2Days = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView").getText();
		Assert.assertEquals("Set reminders for at least 2 more session days per week to stay on track!", newReminder2Days);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_1", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_time_option", driver);
		utilitiesAndroid.clickByAccessibilityId("10", driver);
		utilitiesAndroid.clickByAccessibilityId("15", driver);
		driver.findElementById("android:id/am_label").click();
		driver.findElementById("android:id/button1").click();		
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]")));
		List<MobileElement> listOfReminders2 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
		// Returns the size of the list / number of reminders in the list
			System.out.println("Number of reminders: " + listOfReminders2.size());
		//voltar a home
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
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
		String el60 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[9]/android.widget.TextView[2]").getText();
		Assert.assertEquals("8 hours before", el60);
		//salvar
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		String remindersWeeklyGoalLabel2 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Nice job! Your reminders are all set.", remindersWeeklyGoalLabel2);
		List<MobileElement> listOfReminders3 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
			System.out.println("Number of reminders: " + listOfReminders3.size());
		//voltar pra home
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		//validar popup da badge dos reminders
		if (driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View")).size() > 0) {
			String remindersBadgeTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView").getText();
				Assert.assertEquals("Congrats! You earned a new badge!", remindersBadgeTitle);
			String remindersBadgeTxt = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.view.View/android.widget.TextView").getText();
				Assert.assertEquals("Reminders Scheduled", remindersBadgeTxt);
			MobileElement ok = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button");
			ok.click();
		}
		//abrir reminders
		utilitiesAndroid.clickByAccessibilityId("home_card_weekly_goal_reminders_button", driver);
		//abrir um reminder
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_3", driver);
		//validar update reminder
		String updateReminder = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView").getText();
		Assert.assertEquals("Update reminder", updateReminder);
		String weeklyGoalSet = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView").getText();
		Assert.assertEquals("Your 4 weekly goal session days are set. You can schedule more session days.", weeklyGoalSet);
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
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		String updatePopup = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("There are changes to be saved. Are you sure you want to leave?", updatePopup);
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]").click();
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_update_reminder_button", driver);
		//deletar um reminder
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_3", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Update reminder']")));
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_delete_reminder_button", driver); //como posso comparar a lista antes e depois??
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		List<MobileElement> deleteReminder1 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
			System.out.println("Number of reminders: " + deleteReminder1.size());
		//tentar criar um novo reminder sem escolher um dia
		utilitiesAndroid.clickByAccessibilityId("session_reminders_my_reminders_new_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Set a new reminder']")));
		MobileElement el79 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button");
		el79.click();
		el79.click();
		//voltar para reminders
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		//Fazer update, sair sem salvar e clicar em yes
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_1", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_6", driver);
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button").click();
		//criar novo reminder com mesmo dia e hora diferente de um anterior
		utilitiesAndroid.clickByAccessibilityId("session_reminders_my_reminders_new_reminder_button", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_week_day_2", driver);
		utilitiesAndroid.clickByAccessibilityId("session_reminders_reminder_save_reminder_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		String remindersWeeklyGoalLabel3 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
			Assert.assertEquals("Set a reminder for 1 more session day", remindersWeeklyGoalLabel3);
		List<MobileElement> deleteReminder2 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
			System.out.println("Number of reminders: " + deleteReminder2.size());
		//voltar pra home
		utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']")));

		ConfigurationsAndroid.killDriver();
	}

	private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
		// TODO Auto-generated method stub
		
	}
}