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

	@Test
	public void staging() {
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
	public void live() {
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();

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
		MobileElement achievedStars = driver.findElementByAccessibilityId("home_card_achieved_stars");
		mobileActions.swipeByElements(painChart, achievedStars);
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
}