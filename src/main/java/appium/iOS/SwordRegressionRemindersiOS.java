package appium.iOS;

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
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class SwordRegressionRemindersiOS {
	
	@Test
	public void virtualPt() throws MalformedURLException {
		IOSDriver<MobileElement> driver = inicializarAppium();
	  
		//fazer login
		driver.findElementByAccessibilityId("Allow").click();
		WebDriverWait wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
	//	MobileElement el1 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeOther[@name='loginEmailTextfield']/XCUIElementTypeTextField");
	//	el1.clear();
	//	el1.sendKeys("luiza@marco.com");
		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el2.sendKeys("10março!");
		MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el3.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el4 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not now']");
		el4.click();
		//wait carregar home
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		//verificar texto do card
		String el6 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set reminders']").getText();
			Assert.assertEquals("Set reminders", el6);
		//clicar set new reminder
		MobileElement el7 = (MobileElement) driver.findElementByAccessibilityId("home_card_weekly_goal_reminders_buttons");
		el7.click();
		//validar ecrã
		String newReminder = driver.findElementByAccessibilityId("Set a new reminder").getText();
			Assert.assertEquals("Set a new reminder", newReminder);
		String newReminderDays = driver.findElementByAccessibilityId("Set reminders for at least 4 session days per week to stay on track!").getText();
			Assert.assertEquals("Set reminders for at least 4 session days per week to stay on track!", newReminderDays);
		//voltar
		MobileElement el8 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el8.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		//abrir menu
		MobileElement el9 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el9.click();
		//clicar set reminders
		MobileElement el10 = (MobileElement) driver.findElementByAccessibilityId("menu_option_set_reminders");
		el10.click();
		//voltar
		MobileElement el13 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el13.click();
		//clicar set reminders de novo
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header_close_button")));
		MobileElement close = (MobileElement) driver.findElementByAccessibilityId("header_close_button");
		close.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		MobileElement menu = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		menu.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Set reminders']")));
		MobileElement el14 = (MobileElement) driver.findElementByAccessibilityId("menu_option_set_reminders");
		el14.click();
		//selecionar 2 dias
		MobileElement el16 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_week_day_0");
		el16.click();
		MobileElement el17 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_week_day_2");
		el17.click();
		//mudar a hora
		MobileElement el18 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_time_option");
		el18.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Time']")));
		
		String txt = "10";
		String txt2 = "15";
		List<MobileElement> pw = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));
		// set first PickerWheel
		pw.get(0).sendKeys(txt);
		// set second PickerWheel
		pw.get(1).sendKeys(txt2);
		
		MobileElement el21 = (MobileElement) driver.findElementByAccessibilityId("Done");
		el21.click();
		//abrir notify me
		String notifyMe1 = driver.findElementByAccessibilityId("At time of session").getText();
			Assert.assertEquals("At time of session", notifyMe1);
		MobileElement el23 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_notification_option");
		el23.click();
		//validar notify me
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Notify me']")));
		String notifyMe2 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Notify me']").getText();
			Assert.assertEquals("Notify me", notifyMe2);
		String atTime = driver.findElementByAccessibilityId("session_reminders_notify_me_option_0").getText();
			Assert.assertEquals("At time of session", atTime);
		String min10 = driver.findElementByAccessibilityId("session_reminders_notify_me_option_1").getText();
			Assert.assertEquals("10 minutes before", min10);
		String min30 = driver.findElementByAccessibilityId("session_reminders_notify_me_option_2").getText();
			Assert.assertEquals("30 minutes before", min30);
		String hour1 = driver.findElementByAccessibilityId("session_reminders_notify_me_option_3").getText();
			Assert.assertEquals("1 hour before", hour1);
		String hour8 = driver.findElementByAccessibilityId("session_reminders_notify_me_option_4").getText();
			Assert.assertEquals("8 hours before", hour8);
		//mudar um valor
		MobileElement el30 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_notify_me_option_2");
		el30.click();
		//voltar - agora salva
		MobileElement el31 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el31.click();
		//validar que notify me mudou
		String el32 = driver.findElementByAccessibilityId("30 minutes before").getText();
			Assert.assertEquals("30 minutes before", el32);
		//salvar
		MobileElement el37 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button");
		el37.click();
		//validar my reminders
		String el38 = driver.findElementByAccessibilityId("My reminders").getText();
			Assert.assertEquals("My reminders", el38);
		String remindersWeeklyGoalLabel1 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
			Assert.assertEquals("Set a reminder for 2 more session days", remindersWeeklyGoalLabel1);
	//	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable")));
		//a lista vem vazia porque não encontra a tabela
		List<MobileElement> listOfReminders1 = driver.findElements(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell"));
		// Returns the size of the list / number of reminders in the list
		System.out.println("Number of reminders: " + listOfReminders1.size());
		//voltar pra home
		MobileElement el39 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el39.click();
		MobileElement el40 = (MobileElement) driver.findElementByAccessibilityId("header_close_button");
		el40.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		//validar texto novo do card
		String el41 = driver.findElementByXPath("//XCUIElementTypeButton[@name='home_card_weekly_goal_reminders_buttons']/XCUIElementTypeStaticText[2]").getText();
			Assert.assertEquals("Manage reminders", el41);
		//clicar my reminders
		MobileElement el43 = (MobileElement) driver.findElementByAccessibilityId("home_card_weekly_goal_reminders_buttons");
		el43.click();
		//criar novo reminder com a mesma hora do primeiro com 1 dia
		MobileElement el44 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_my_reminders_new_reminder_button");
		el44.click();
		String newReminder2Days = driver.findElementByAccessibilityId("Set reminders for at least 2 more session days per week to stay on track!").getText();
			Assert.assertEquals("Set reminders for at least 2 more session days per week to stay on track!", newReminder2Days);
		MobileElement el45 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_week_day_1");
		el45.click();
		MobileElement el46 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_time_option");
		el46.click();
		List<MobileElement> pw2 = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));
		// set first PickerWheel
		pw2.get(0).sendKeys(txt);
		// set second PickerWheel
		pw2.get(1).sendKeys(txt2);
	
		MobileElement el49 = (MobileElement) driver.findElementByAccessibilityId("Done");
		el49.click();
		MobileElement el50 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button");
		el50.click();
		//ele não encontra a tabela, o que explica vir a zero
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable")));
		List<MobileElement> listOfReminders2 = driver.findElements(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell"));
		// Returns the size of the list / number of reminders in the list
		System.out.println("Number of reminders: " + listOfReminders2.size()); //será que tem como reduzir 1 pra descontar o typeCell dos dias?
		//voltar a home
		MobileElement el51 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el51.click();
		//abrir reminders pelo menu
		MobileElement el52 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el52.click();
		MobileElement el53 = (MobileElement) driver.findElementByAccessibilityId("menu_option_set_reminders");
		el53.click();
		//verificar o texto dos reminders
		String reminderFor1Day = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
			Assert.assertEquals("Set a reminder for 1 more session day", reminderFor1Day);
		//criar novo reminder com 2 dias e notify me diferente
		MobileElement el54 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_my_reminders_new_reminder_button");
		el54.click();
		MobileElement el55 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_week_day_3");
		el55.click();
		MobileElement el56 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_week_day_4");
		el56.click();
		MobileElement el57 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_notification_option");
		el57.click();
		MobileElement el58 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_notify_me_option_4");
		el58.click();
		MobileElement el59 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el59.click();
		//validar valor do notify me
		String el60 = driver.findElementByAccessibilityId("8 hours before").getText();
			Assert.assertEquals("8 hours before", el60);
		//salvar
		MobileElement el61 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button");
		el61.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		String remindersWeeklyGoalLabel2 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
			Assert.assertEquals("Nice job! Your reminders are all set.", remindersWeeklyGoalLabel2);
		List<MobileElement> listOfReminders3 = driver.findElements(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell"));
			System.out.println("Number of reminders: " + listOfReminders3.size());
		//voltar pra home
		MobileElement el62 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el62.click();
		MobileElement el63 = (MobileElement) driver.findElementByAccessibilityId("header_close_button");
		el63.click();
		//validar badge dos reminders
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeButton[@name='Ok']")));
		String badgeTitle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
		String badgeName = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
		Assert.assertEquals("Congrats! You earned a new badge!", badgeTitle);
		Assert.assertEquals("Reminders Scheduled", badgeName);
		MobileElement ok = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Ok']");
		ok.click();
		//abrir reminders
		MobileElement el64 = (MobileElement) driver.findElementByAccessibilityId("header_menu_button");
		el64.click();
		MobileElement el65 = (MobileElement) driver.findElementByAccessibilityId("menu_option_set_reminders");
		el65.click();
		//abrir um reminder
		MobileElement el66 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_2");
		el66.click();
		//validar update reminder
		String updateReminder = driver.findElementByAccessibilityId("Update reminder").getText();
			Assert.assertEquals("Update reminder", updateReminder);
		String weeklyGoalSet = driver.findElementByAccessibilityId("Your 4 weekly goal session days are set. You can schedule more session days.").getText();
			Assert.assertEquals("Your 4 weekly goal session days are set. You can schedule more session days.", weeklyGoalSet);
		//editar um reminder
		MobileElement el67 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_week_day_6");
		el67.click();
		MobileElement el68 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_time_option");
		el68.click();
		String txt3 = "22";
		String txt4 = "39";
		List<MobileElement> pw3 = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));
		// set first PickerWheel
		pw3.get(0).sendKeys(txt3);
		// set second PickerWheel
		pw3.get(1).sendKeys(txt4);
		
		MobileElement el71 = (MobileElement) driver.findElementByAccessibilityId("Done");
		el71.click();
		MobileElement el72 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_notification_option");
		el72.click();
		MobileElement el73 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_notify_me_option_2");
		el73.click();
		MobileElement el74 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el74.click();
		MobileElement el742 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el742.click();
		String updatePopup = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("There are changes to be saved. Are you sure you want to leave?", updatePopup);
		driver.findElementByXPath("//XCUIElementTypeButton[@name='No']").click();
		MobileElement el75 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_update_reminder_button");
		el75.click();
		//deletar um reminder
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		MobileElement el76 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_2");
		el76.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Update reminder']")));
		MobileElement el77 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_delete_reminder_button");
		el77.click(); //como posso comparar a lista antes e depois??
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		List<MobileElement> deleteReminder1 = driver.findElements(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell"));
			System.out.println(deleteReminder1.size());
		//tentar criar um novo reminder sem escolher um dia
		MobileElement el78 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_my_reminders_new_reminder_button");
		el78.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Set a new reminder']")));
		MobileElement el79 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button");
		el79.click();
		el79.click();
		//voltar para reminders
		MobileElement el80 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el80.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		//Fazer update, sair sem salvar e clicar em yes
		MobileElement el81 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_1");
		el81.click();
		MobileElement el82 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_week_day_6");
		el82.click();
		MobileElement el83 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el83.click();
		MobileElement el84 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Yes']");
		el84.click();
		//criar novo reminder com mesmo dia e hora diferente de um anterior
		MobileElement el85 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_my_reminders_new_reminder_button");
		el85.click();
		MobileElement el86 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_week_day_2");
		el86.click();
		MobileElement el87 = (MobileElement) driver.findElementByAccessibilityId("session_reminders_reminder_save_reminder_button");
		el87.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My reminders']")));
		String remindersWeeklyGoalLabel3 = driver.findElementByAccessibilityId("session_reminders_my_reminders_weekly_goal_label").getText();
			Assert.assertEquals("Set a reminder for 1 more session day", remindersWeeklyGoalLabel3);
		List<MobileElement> deleteReminder2 = driver.findElements(By.xpath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable"));
			System.out.println(deleteReminder2.size());
		//voltar pra home
		MobileElement el88 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el88.click();
		MobileElement el89 = (MobileElement) driver.findElementByAccessibilityId("header_close_button");
		el89.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		
		driver.quit();
	}

	private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
		// TODO Auto-generated method stub
		
	}

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
		capabilities.setCapability("app", "/Users/luizaspiegel/Downloads/SWORDHealthRelease430-117.ipa");
		capabilities.setCapability("updatedWDABundleId", "com.luizateste2.wda.runner");
		capabilities.setCapability("showXcodeLog", "true");
		capabilities.setCapability("wdaLocalPort", "8205");
		capabilities.setCapability("appium:usePrebuiltWDA", "true");
	    
		IOSDriver<MobileElement> driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
		return driver;
	}
}