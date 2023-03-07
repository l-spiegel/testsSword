package appium.iOS;

import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class swordRegressionBadgesiOS {

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

private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
	// TODO Auto-generated method stub
	}

	
	@Test
	public void virtualPt() throws MalformedURLException {
		IOSDriver<MobileElement> driver = inicializarAppium();
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		driver.findElementByAccessibilityId("Allow").click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
		//	MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("loginEmailTextfield");
		//	el1.clear();
		//	el1.sendKeys("f.silva@swordhealth.com");
		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el2.click();
		el2.sendKeys("Cabixuda12");
		MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el3.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el4 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not now']");
		el4.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		//fazer swipe pra mostrar o card das badges
		MobileElement remindersButton = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']"); //bug com o id do botão dos reminders
		MobileElement ptCard = (MobileElement) driver.findElementByAccessibilityId("home_card_pt");
		mobileActions.swipeByElements(remindersButton, ptCard);
		MobileElement weeklySessionsCard = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']"); //corrigir depois
		MobileElement sessionDetailsCard = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Progress']");
		mobileActions.swipeByElements(sessionDetailsCard, weeklySessionsCard); //corrigir depois
		MobileElement painChart = (MobileElement) driver.findElementByAccessibilityId("home_screen_pain_chart_card");
		MobileElement achievedStars = (MobileElement) driver.findElementByAccessibilityId("home_card_achieved_stars");
		mobileActions.swipeByElements(painChart, achievedStars);
		//validar presença do card com if
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name='My badges']")).size() > 0) {
			//validar título do card
			String myBadges = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[2]/XCUIElementTypeStaticText").getText();
				Assert.assertEquals("My badges", myBadges);
			//validar o botão see all badges
			String seeAllBadges = driver.findElementByXPath("//XCUIElementTypeButton[@name='home_badges_card_see_more_button']/XCUIElementTypeStaticText").getText();
				Assert.assertEquals("See all badges", seeAllBadges);
			//pegar os valores das 3 primeiras badges
			String lastAchieved = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_badges_card_badge_0']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
				System.out.println("LAST BADGE ACHIEVED: " + lastAchieved);
			String notAchieved1 = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_badges_card_badge_1']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
				System.out.println("FIRST NOT ACHIEVED BADGE: " + notAchieved1 + " ");
			String notAchieved2 = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_badges_card_badge_2']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
				System.out.println("SECOND NOT ACHIEVED BADGE: " + notAchieved2);
			//fazer swipe pra mostrar a última badge
			MobileElement secondNotAchievedBadge = (MobileElement) driver.findElementByAccessibilityId("home_badges_card_badge_2");
			MobileElement achievedBadge = (MobileElement) driver.findElementByAccessibilityId("home_badges_card_badge_0");
			mobileActions.swipeByElements(secondNotAchievedBadge, achievedBadge);
			//pegar o texto da última badge
			String notAchieved3 = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_badges_card_badge_3']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
				System.out.println("THIRD NOT ACHIEVED BADGE: " + notAchieved3);
			//abrir o popup de uma das badges not achieved
			MobileElement el10 = (MobileElement) driver.findElementByAccessibilityId("home_badges_card_badge_2");
			el10.click();
			//imprimir valores do badge
			String badgeDetailsTitle1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]").getText();
				System.out.println("POPUP BADGE TITLE: " + badgeDetailsTitle1);
			String badgeDetailsInfo1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]").getText(); 
				System.out.println("POPUP BADGE DESCRIPTION: " + badgeDetailsInfo1);
			//fechar popup pelo botão
			MobileElement el11 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Got it']");
			el11.click();
			//abrir my badges
			MobileElement el12 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='See all badges']");
			el12.click();
		} else {
			System.out.println("BADGES CARD NOT FOUND!");
		//	driver.quit();
		}
		//validar título
		String myBadges = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("My badges", myBadges);
		//validar título e badges do number of sessions
		String numberOfSessions = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]").getText();
			Assert.assertEquals("Number of sessions", numberOfSessions);
		String session1 = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_0_badge_0']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("1st Session", session1);
		String session2 = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_0_badge_1']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("3rd Session", session2);
		String session3 = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_0_badge_2']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("9th Session", session3);
		String session4 = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_0_badge_3']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("18th Session", session4);
		//pegar o número de badges
		String numberOfSessionsBadgesAchieved = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]").getText();
			System.out.println("BADGES ACHIEVED: " + numberOfSessionsBadgesAchieved);
		//clicar na badge de 18 sessões
		MobileElement el13 = (MobileElement) driver.findElementByAccessibilityId("badges_screen_section_0_badge_3");
		el13.click();
		//validar details do popup
		String badgeDetailsTitle2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]").getText();
			Assert.assertEquals("18th Session", badgeDetailsTitle2);
		String badgeDetailsInfo2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]").getText();
			Assert.assertEquals("Complete your 18th exercise session", badgeDetailsInfo2);
		//clicar fora do popup
		mobileActions.tapByCoordinates(248, 158);
		//Fazer scroll do number of weekly goals até o number of sessions
		MobileElement numberWeeklyGoalsTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Number of weekly goals']");
		MobileElement firstSessionTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Number of sessions']");
		mobileActions.swipeByElements(numberWeeklyGoalsTxt, firstSessionTxt);
		//validar título e nome das badges do number of weekly goals
		String weeklyGoals = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]").getText();
			Assert.assertEquals("Number of weekly goals", weeklyGoals);
		String weeklyGoal1 = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_1_badge_0']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("1st Weekly Goal", weeklyGoal1);
		String weeklyGoal2 = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_1_badge_1']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("2nd Weekly Goal", weeklyGoal2);
		String weeklyGoal3 = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_1_badge_2']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("3rd Weekly Goal", weeklyGoal3);
		String weeklyGoal4 = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_1_badge_3']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("4th Weekly Goal", weeklyGoal4);
		//pegar o número de badges
		String numberOfBadgesAchieved2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]").getText();
			System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved2);
		//clicar numa badge
		MobileElement el14 = (MobileElement) driver.findElementByAccessibilityId("badges_screen_section_1_badge_3");
		el14.click();
		//validar details do popup
		String badgeDetailsTitle3 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]").getText();
			Assert.assertEquals("4th Weekly Goal", badgeDetailsTitle3);
		String badgeDetailsInfo3 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]").getText();
			Assert.assertEquals("Achieve your weekly goal for the fourth time ", badgeDetailsInfo3);
		//clicar no botão do popup
		MobileElement el15 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeButton[@name='Got it']");
		el15.click();
		//fazer scroll do app badges até o number of weekly goals
		MobileElement appBadgesTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='App badges']");
		MobileElement session18Txt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Number of weekly goals']");
		mobileActions.swipeByElements(appBadgesTxt, session18Txt);
		//validar título e nome das badges do app badges
		String appBadges = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]").getText();
			Assert.assertEquals("App badges", appBadges);
		String remindersBadge = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_2_badge_0']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("Reminders Scheduled", remindersBadge);
		String messageInChatBadge = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_2_badge_1']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("1st Message In The Chat", messageInChatBadge);
		String videoWatchedBadge = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_2_badge_2']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("Watched 1st Video", videoWatchedBadge);
		//pegar o número de badges
		String numberOfBadgesAchieved3 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]").getText();
			System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved3);
		//fazer scroll pra mostrar até o final
		MobileElement appBadgesTxt2 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='App badges']");
		MobileElement journeyStepsTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Journey steps']");
		mobileActions.swipeByElements(journeyStepsTxt, appBadgesTxt2);
		//validar o título e nome das badges do journey steps
		String journeySteps = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]").getText();
			Assert.assertEquals("Journey steps", journeySteps);
		String enrollmentBadge = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_3_badge_0']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("Enrollment", enrollmentBadge);
		String reassessmen1tBadge = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_3_badge_1']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("1st Reassessment", reassessmen1tBadge);
		String reassessment2Badge = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_3_badge_2']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("2nd Reassessment", reassessment2Badge);
		String treatmentCompletedBadge = driver.findElementByXPath("//XCUIElementTypeCell[@name='badges_screen_section_3_badge_3']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			Assert.assertEquals("Treatment Completed", treatmentCompletedBadge);
		//pegar o número de badges
		String numberOfBadgesAchieved4 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]").getText();
			System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved4);
		//clicar na badge do enrollment
		MobileElement el16 = (MobileElement) driver.findElementByAccessibilityId("badges_screen_section_3_badge_0");
		el16.click();
		//voltar
		MobileElement el17 = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		el17.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My badges']")));
		driver.quit();
	}
	
}