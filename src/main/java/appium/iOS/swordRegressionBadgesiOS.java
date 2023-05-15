package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class swordRegressionBadgesiOS {

	private IOSDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
	}

	private final static String CHECK_HOME_BADGES = "home_badges_screen";
	private final static String CHECK_HOME_BADGES_SCROLL = "home_badges_scroll_screen";
	private final static String CHECK_HOME_BADGE_NOT_ACHIEVED = "home_badge_not_achieved";
	private final static String CHECK_BADGE_SCREEN_1 = "badge_screen_1";
	private final static String CHECK_BADGE_SCREEN_2 = "badge_screen_2";
	private final static String CHECK_BADGE_SCREEN_3 = "badge_screen_3";
	private final static String CHECK_BADGE_SCREEN_4 = "badge_screen_4";
	private final static String CHECK_BADGE_POPUP_1 = "badge_screen_popup_1";
	private final static String CHECK_BADGE_POPUP_2 = "badge_screen_popup_2";

	@Test
	public void staging() throws Exception {
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		new VisualCheck(driver);

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
			VisualCheck.doVisualCheck(CHECK_HOME_BADGES);
			//fazer swipe pra mostrar a última badge
			MobileElement secondNotAchievedBadge = driver.findElementByAccessibilityId("home_badges_card_badge_2");
			MobileElement achievedBadge = driver.findElementByAccessibilityId("home_badges_card_badge_0");
			mobileActions.swipeByElements(secondNotAchievedBadge, achievedBadge);
			//pegar o texto da última badge
			String notAchieved3 = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_badges_card_badge_3']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			System.out.println("THIRD NOT ACHIEVED BADGE: " + notAchieved3);
			VisualCheck.doVisualCheck(CHECK_HOME_BADGES_SCROLL);
			//abrir o popup de uma das badges not achieved
			utilitiesiOS.clickByAccessibilityId("home_badges_card_badge_2", driver);
			//imprimir valores do badge
			String badgeDetailsTitle1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]").getText();
			System.out.println("POPUP BADGE TITLE: " + badgeDetailsTitle1);
			String badgeDetailsInfo1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]").getText(); 
			System.out.println("POPUP BADGE DESCRIPTION: " + badgeDetailsInfo1);
			VisualCheck.doVisualCheck(CHECK_HOME_BADGE_NOT_ACHIEVED);
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
		VisualCheck.doVisualCheck(CHECK_BADGE_SCREEN_1);
		//clicar na badge de 18 sessões
		utilitiesiOS.clickByAccessibilityId("badges_screen_section_0_badge_3", driver);
		//validar details do popup
		driver.findElementByXPath("(//XCUIElementTypeStaticText[@name='18th Session'])[2]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Complete your 18th exercise session']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Got it']");
		VisualCheck.doVisualCheck(CHECK_BADGE_POPUP_1);
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
		VisualCheck.doVisualCheck(CHECK_BADGE_POPUP_2);
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
		VisualCheck.doVisualCheck(CHECK_BADGE_SCREEN_4);
		//clicar na badge do enrollment
		utilitiesiOS.clickByAccessibilityId("badges_screen_section_2_badge_2", driver);
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My badges']")));

		ConfigurationsiOS.killDriver();
	}

	@Test
	public void live() throws Exception {
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		VisualCheck visualCheck = new VisualCheck(driver);

		utilitiesiOS.login("f.silva@swordhealth.com", "Cabixuda12", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		//fazer swipe pra mostrar o card das badges
		MobileElement remindersButton = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']");
		MobileElement ptCard = driver.findElementByAccessibilityId("home_card_pt");
		mobileActions.swipeByElements(remindersButton, ptCard);
		MobileElement weeklySessionsCard = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']"); //corrigir depois
		MobileElement sessionDetailsCard = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Progress']");
		mobileActions.swipeByElements(sessionDetailsCard, weeklySessionsCard); //corrigir depois
		MobileElement painChart = driver.findElementByAccessibilityId("home_screen_pain_chart_card");
		MobileElement myBadgesTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"My badges\"]");
		mobileActions.swipeByElements(myBadgesTxt, painChart);
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
			VisualCheck.doVisualCheck(CHECK_HOME_BADGES);
			//clicar na badge achieved - tem um bug que abre o popup
			utilitiesiOS.clickByAccessibilityId("home_badges_card_badge_0", driver);
			if (driver.findElements(By.xpath("//XCUIElementTypeButton[@name='Got it']")).size() > 0) {
				System.out.println("BUG DO POPUP DA BADGE ACHIEVED");
				utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Got it']", driver);
			} else {
				System.out.println("SEM BUG DO POPUP DA BADGE ACHIEVED");
			}
			//fazer swipe pra mostrar a última badge
			MobileElement secondNotAchievedBadge = driver.findElementByAccessibilityId("home_badges_card_badge_2");
			MobileElement achievedBadge = driver.findElementByAccessibilityId("home_badges_card_badge_0");
			mobileActions.swipeByElements(secondNotAchievedBadge, achievedBadge);
			//pegar o texto da última badge
			String notAchieved3 = driver.findElementByXPath("//XCUIElementTypeCell[@name='home_badges_card_badge_3']/XCUIElementTypeOther/XCUIElementTypeStaticText").getText();
			System.out.println("THIRD NOT ACHIEVED BADGE: " + notAchieved3);
			VisualCheck.doVisualCheck(CHECK_HOME_BADGES_SCROLL);
			//abrir o popup de uma das badges not achieved
			utilitiesiOS.clickByAccessibilityId("home_badges_card_badge_2", driver);
			//imprimir valores do badge
			String badgeDetailsTitle1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[1]").getText();
			System.out.println("POPUP BADGE TITLE: " + badgeDetailsTitle1);
			String badgeDetailsInfo1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[2]").getText();
			System.out.println("POPUP BADGE DESCRIPTION: " + badgeDetailsInfo1);
			VisualCheck.doVisualCheck(CHECK_HOME_BADGE_NOT_ACHIEVED);
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
		String numberOfSessionsBadgesAchieved = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"3/4\"]").getText();
		System.out.println("SESSIONS BADGES ACHIEVED: " + numberOfSessionsBadgesAchieved);
		VisualCheck.doVisualCheck(CHECK_BADGE_SCREEN_1);
		//clicar na badge de 18 sessões
		utilitiesiOS.clickByAccessibilityId("eighteenth_session_badge_inactive", driver); //mudou o accessibility id
		//validar details do popup
		driver.findElementByXPath("(//XCUIElementTypeStaticText[@name='18th Session'])[2]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Complete your 18th exercise session']");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Got it\"]");
		VisualCheck.doVisualCheck(CHECK_BADGE_POPUP_1);
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
		String numberOfBadgesAchieved2 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"1/4\"]").getText();
		System.out.println("WEEKLY GOAL BADGES ACHIEVED: " + numberOfBadgesAchieved2);
		//clicar numa badge
		utilitiesiOS.clickByAccessibilityId("fourth_weekly_goal_achieved_badge_inactive", driver); //mudou o accessibility id
		//validar details do popup
		driver.findElementByXPath("(//XCUIElementTypeStaticText[@name='4th Weekly Goal'])[2]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Achieve your weekly goal for the fourth time ']");
		VisualCheck.doVisualCheck(CHECK_BADGE_POPUP_2);
		//clicar no botão do popup
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Got it']", driver);
		//fazer scroll do getting started até o number of weekly goals
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
		String numberOfBadgesAchieved3 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"3/3\"]").getText();
		System.out.println("GETTING STARTED BADGES ACHIEVED: " + numberOfBadgesAchieved3);
		//fazer scroll pra mostrar até o final
		MobileElement programMilestonesTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Program milestones']");
		mobileActions.swipeByElements(programMilestonesTxt, gettingStartedTxt);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validar o título e nome das badges do program milestones
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Program milestones']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Enrollment']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='1st Reassessment']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='2nd Reassessment']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Treatment Completed']");
		//pegar o número de badges
		String numberOfBadgesAchieved4 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"2/4\"]").getText();
		System.out.println("PROGRAM MILESTONES BADGES ACHIEVED: " + numberOfBadgesAchieved4);
		VisualCheck.doVisualCheck(CHECK_BADGE_SCREEN_4);
		//clicar na badge do enrollment
		utilitiesiOS.clickByAccessibilityId("badges_screen_section_2_badge_2", driver);
		//voltar
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='My badges']")));

		ConfigurationsiOS.killDriver();
	}
}