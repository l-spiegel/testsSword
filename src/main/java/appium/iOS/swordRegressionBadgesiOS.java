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

	private IOSDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
	}

	@Test
	public void staging() throws MalformedURLException {
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();

		utilitiesiOS.login("l.spiegel+4@swordhealth.com", "Test1234!", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		//fazer swipe pra mostrar o card das badges
		MobileElement totalStarsCard = driver.findElementByAccessibilityId("home_card_achieved_stars");
		MobileElement ptCard = driver.findElementByAccessibilityId("home_card_pt");
		mobileActions.swipeByElements(totalStarsCard, ptCard);
		//validar presença do card com if
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name='My badges']")).size() > 0) {
			//validar título do card
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name='My badges']");
			//validar o botão see all badges
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name='See more']");
			//pegar os valores das 3 primeiras badges
			String lastAchieved = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_badges_card_badge_0']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			System.out.println("LAST BADGE ACHIEVED: " + lastAchieved);
			String notAchieved1 = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_badges_card_badge_1']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			System.out.println("FIRST NOT ACHIEVED BADGE: " + notAchieved1 + " ");
			String notAchieved2 = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_badges_card_badge_2']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			System.out.println("SECOND NOT ACHIEVED BADGE: " + notAchieved2);
			//fazer swipe pra mostrar a última badge
			MobileElement secondNotAchievedBadge = driver.findElementByAccessibilityId("home_badges_card_badge_2");
			MobileElement achievedBadge = driver.findElementByAccessibilityId("home_badges_card_badge_0");
			mobileActions.swipeByElements(secondNotAchievedBadge, achievedBadge);
			//pegar o texto da última badge
			String notAchieved3 = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_badges_card_badge_3']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			System.out.println("THIRD NOT ACHIEVED BADGE: " + notAchieved3);
			//abrir o popup de uma das badges not achieved
			utilitiesiOS.clickByAccessibilityId("home_badges_card_badge_2", driver);
			//imprimir valores do badge
			String badgeDetailsTitle1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]").getText();
			System.out.println("POPUP BADGE TITLE: " + badgeDetailsTitle1);
			String badgeDetailsInfo1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]").getText(); 
			System.out.println("POPUP BADGE DESCRIPTION: " + badgeDetailsInfo1);
			//fechar popup pelo botão
			utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Got it']", driver);
			//abrir my badges
			utilitiesiOS.clickByAccessibilityId("See more", driver);
		} else {
			System.out.println("BADGES CARD NOT FOUND!");
			ConfigurationsiOS.killDriver();
		}
		//validar título
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='My badges']");
		//validar título e badges do number of sessions
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='1st Session']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='3rd Session']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='9th Session']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='18th Session']");
		//pegar o número de badges
		String numberOfSessionsBadgesAchieved = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]").getText();
		System.out.println("BADGES ACHIEVED: " + numberOfSessionsBadgesAchieved);
		//clicar na badge de 18 sessões
		utilitiesiOS.clickByAccessibilityId("badges_screen_section_0_badge_3", driver);
		//validar details do popup
		driver.findElementByXPath("(//XCUIElementTypeStaticText[@name='18th Session'])[2]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Complete your 18th exercise session']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Got it']");
		//clicar fora do popup
		mobileActions.tapByCoordinates(248, 158);
		//Fazer scroll do number of weekly goals até o number of sessions
		MobileElement weeklyGoalsTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Weekly goals']");
		MobileElement sessionsTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']");
		mobileActions.swipeByElements(weeklyGoalsTxt, sessionsTxt);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validar título e nome das badges do number of weekly goals
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Weekly goals']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='1st Weekly Goal']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='2nd Weekly Goal']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='3rd Weekly Goal']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='4th Weekly Goal']");
		//pegar o número de badges
		String numberOfBadgesAchieved2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]").getText();
		System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved2);
		//clicar numa badge
		utilitiesiOS.clickByAccessibilityId("badges_screen_section_1_badge_3", driver);
		//validar details do popup
		driver.findElementByXPath("(//XCUIElementTypeStaticText[@name='4th Weekly Goal'])[2]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Achieve your weekly goal for the fourth time ']");
		//clicar no botão do popup
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Got it']", driver);
		//fazer scroll do app badges até o number of weekly goals
		MobileElement gettingStartedTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Getting started']");
		mobileActions.swipeByElements(gettingStartedTxt, weeklyGoalsTxt);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validar título e nome das badges do app badges
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Getting started']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Reminders Scheduled']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='1st Message In The Chat']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Watched 1st Video']");
		//pegar o número de badges
		String numberOfBadgesAchieved3 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]").getText();
		System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved3);
		//fazer scroll pra mostrar até o final
		MobileElement programMilestonesTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Program milestones']");
		mobileActions.swipeByElements(programMilestonesTxt, gettingStartedTxt);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validar o título e nome das badges do journey steps
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Program milestones']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Enrollment']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='1st Reassessment']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='2nd Reassessment']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Treatment Completed']");
		//pegar o número de badges
		String numberOfBadgesAchieved4 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]").getText();
		System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved4);
		//clicar na badge do enrollment
		utilitiesiOS.clickByAccessibilityId("badges_screen_section_2_badge_2", driver);
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My badges']")));

		ConfigurationsiOS.killDriver();
	}

	@Test
	public void live() throws MalformedURLException {
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();

		utilitiesiOS.login("f.silva@swordhealth.com", "Cabixuda12", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		//fazer swipe pra mostrar o card das badges
		MobileElement remindersButton = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']");
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