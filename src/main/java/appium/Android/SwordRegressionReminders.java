package appium;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.remote.MobileCapabilityType;

public class SwordRegressionReminders {
	
	@Test
	public void virtualPt() throws MalformedURLException {
		AndroidDriver<MobileElement> driver = inicializarAppium();
		WebDriverWait wait = new WebDriverWait(driver,20);
	  
		//fazer login
		MobileElement el1 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[1]");
		el1.clear();
		el1.sendKeys("l.spiegel+4@swordhealth.com");
		MobileElement el2 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el2.click();
		el2.sendKeys("Test1234!");
		driver.findElementByAccessibilityId("loginButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		driver.findElementByXPath("//android.widget.TextView[@text='Not now']").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Almeida']")));
		//verificar texto do card
		String el6 = driver.findElementByXPath("//android.view.View[@content-desc='home_card_weekly_goal']/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("Set reminders", el6);
		//clicar set new reminder
		driver.findElementByAccessibilityId("home_card_weekly_goal_reminders_button").click();
		//validar ecrã
		String newReminder = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView").getText();
		Assert.assertEquals("Set a new reminder", newReminder);
		String newReminderDays = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView").getText();
		Assert.assertEquals("Set reminders for at least 4 more session days per week to stay on track!", newReminderDays);
		//voltar
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']")));
		//abrir menu
		driver.findElementByAccessibilityId("header_menu_button").click();
		//clicar set reminders
		driver.findElementByAccessibilityId("menu_option_set_reminders").click();
		//voltar
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button").click();
		//clicar set reminders de novo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='PIN code and Biometrics']")));
		driver.findElementByAccessibilityId("header_menu_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']")));
		driver.findElementByAccessibilityId("header_menu_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Set reminders']")));
		driver.findElementByAccessibilityId("menu_option_set_reminders").click();
		//selecionar 2 dias
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_0").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_2").click();
		//mudar a hora
		driver.findElementByAccessibilityId("session_reminders_reminder_time_option").click();
		driver.findElementByAccessibilityId("10").click();
		driver.findElementByAccessibilityId("15").click();
		driver.findElementById("android:id/am_label").click();
		driver.findElementById("android:id/button1").click();
		//abrir notify me
		String notifyMe1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[9]/android.widget.TextView[2]").getText();
		Assert.assertEquals("At time of session", notifyMe1);
		driver.findElementByAccessibilityId("session_reminders_reminder_notification_option").click();
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
		driver.findElementByAccessibilityId("session_reminders_notify_me_option_2").click();
		//voltar
		driver.findElementByXPath("//android.widget.Button").click();
		//validar que notify me mudou
		String notifyMe3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[9]/android.widget.TextView[2]").getText();
		Assert.assertEquals("30 minutes before", notifyMe3);
		//salvar
		driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button").click();
		//validar my reminders
		String el38 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView").getText();
		Assert.assertEquals("My reminders", el38);
		String remindersWeeklyGoalLabel1 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Set a reminder for 2 more session days", remindersWeeklyGoalLabel1);
		List<MobileElement> listOfReminders1 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
		// Returns the size of the list / number of reminders in the list
			System.out.println("Number of reminders: " + listOfReminders1.size());
		//voltar pra home
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button").click();
		driver.findElementByAccessibilityId("header_menu_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']")));
		//validar texto novo do card
		String el41 = driver.findElementByXPath("//android.view.View[@content-desc='home_card_weekly_goal']/android.view.View[2]/android.widget.TextView").getText();
		Assert.assertEquals("Manage reminders", el41);
		//clicar manage reminders
		driver.findElementByAccessibilityId("home_card_weekly_goal_reminders_button").click();
		//criar novo reminder com a mesma hora do primeiro com 1 dia
		driver.findElementByAccessibilityId("session_reminders_my_reminders_new_reminder_button").click();
		String newReminder2Days = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView").getText();
		Assert.assertEquals("Set reminders for at least 2 more session days per week to stay on track!", newReminder2Days);
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_1").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_time_option").click();
		driver.findElementByAccessibilityId("10").click();
		driver.findElementByAccessibilityId("15").click();
		driver.findElementById("android:id/am_label").click();
		driver.findElementById("android:id/button1").click();		
		driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]")));
		List<MobileElement> listOfReminders2 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
		// Returns the size of the list / number of reminders in the list
			System.out.println("Number of reminders: " + listOfReminders2.size());
		//voltar a home
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button").click();
		//abrir reminders pelo menu
		driver.findElementByAccessibilityId("header_menu_button").click();
		driver.findElementByAccessibilityId("menu_option_set_reminders").click();
		//verificar o texto dos reminders
		String reminderFor1Day = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Set a reminder for 1 more session day", reminderFor1Day);
		//criar novo reminder com 2 dias e notify me diferente
		driver.findElementByAccessibilityId("session_reminders_my_reminders_new_reminder_button").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_3").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_4").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_notification_option").click();
		driver.findElementByAccessibilityId("session_reminders_notify_me_option_4").click();
		driver.findElementByXPath("//android.widget.Button").click();
		//validar valor do notify me
		String el60 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[9]/android.widget.TextView[2]").getText();
		Assert.assertEquals("8 hours before", el60);
		//salvar
		driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		String remindersWeeklyGoalLabel2 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
		Assert.assertEquals("Nice job! Your reminders are all set.", remindersWeeklyGoalLabel2);
		List<MobileElement> listOfReminders3 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
			System.out.println("Number of reminders: " + listOfReminders3.size());
		//voltar pra home e ver se o card não aparece
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button").click();
		driver.findElementByAccessibilityId("header_menu_button").click();
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
		driver.findElementByAccessibilityId("home_card_weekly_goal_reminders_button").click();
		//abrir um reminder
		driver.findElementByAccessibilityId("session_reminders_reminder_3").click();
		//validar update reminder
		String updateReminder = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView").getText();
		Assert.assertEquals("Update reminder", updateReminder);
		String weeklyGoalSet = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView").getText();
		Assert.assertEquals("Your 4 weekly goal session days are set. You can schedule more session days.", weeklyGoalSet);
		//editar um reminder
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_6").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_time_option").click();
		driver.findElementById("android:id/minutes").click();
		driver.findElementByAccessibilityId("40").click();
		driver.findElementById("android:id/pm_label").click();
		driver.findElementById("android:id/button1").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_notification_option").click();
		driver.findElementByAccessibilityId("session_reminders_notify_me_option_2").click();
		driver.findElementByXPath("//android.widget.Button").click();
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button").click();
		String updatePopup = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("There are changes to be saved. Are you sure you want to leave?", updatePopup);
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_update_reminder_button").click();
		//deletar um reminder
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		driver.findElementByAccessibilityId("session_reminders_reminder_3").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Update reminder']")));
		driver.findElementByAccessibilityId("session_reminders_reminder_delete_reminder_button").click(); //como posso comparar a lista antes e depois??
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		List<MobileElement> deleteReminder1 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
			System.out.println("Number of reminders: " + deleteReminder1.size());
		//tentar criar um novo reminder sem escolher um dia
		driver.findElementByAccessibilityId("session_reminders_my_reminders_new_reminder_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Set a new reminder']")));
		MobileElement el79 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button");
		el79.click();
		el79.click();
		//voltar para reminders
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		//Fazer update, sair sem salvar e clicar em yes
		driver.findElementByAccessibilityId("session_reminders_reminder_1").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_6").click();
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button").click();
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button").click();
		//criar novo reminder com mesmo dia e hora diferente de um anterior
		driver.findElementByAccessibilityId("session_reminders_my_reminders_new_reminder_button").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_week_day_2").click();
		driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My reminders']")));
		String remindersWeeklyGoalLabel3 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
			Assert.assertEquals("Set a reminder for 1 more session day", remindersWeeklyGoalLabel3);
		List<MobileElement> deleteReminder2 = driver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View"));
			System.out.println("Number of reminders: " + deleteReminder2.size());
		//voltar pra home
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button").click();
		driver.findElementByAccessibilityId("header_menu_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']")));
		
		driver.quit();
	}

	private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
		// TODO Auto-generated method stub
		
	}

	private AndroidDriver<MobileElement> inicializarAppium() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "android");
	    desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
	    desiredCapabilities.setCapability("appium:deviceName", "07111JEC201460");
	    desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/luizaspiegel/Downloads/app-sword-qa1651.apk");
	    desiredCapabilities.setCapability("appium:noReset", "false");
	    desiredCapabilities.setCapability("appium:autoGrantPermissions", "true");
	    
	    AndroidDriver<MobileElement> driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}