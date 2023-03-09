package appium.Android;

import appium.Android.ConfigurationsAndroid;
import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import appium.iOS.MobileActionsiOS;

public class swordRegressionBadges {

	private AndroidDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsAndroid.getDriver();
	}

	private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
	// TODO Auto-generated method stub
	}

	@Test
	public void virtualPt() throws MalformedURLException {
		MobileActions mobileActions = new MobileActions(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		MobileElement el1 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[1]");
		el1.clear();
		el1.sendKeys("l.spiegel+3@swordhealth.com");
		MobileElement el2 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el2.click();
		el2.sendKeys("Test1234!");
		driver.findElementByAccessibilityId("loginButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		driver.findElementByXPath("//android.widget.TextView[@text='Not now']").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Almeida']")));
		//fazer swipe pra mostrar o card das badges - ver se o de prod funciona bem
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Luiza Almeida']")).size() > 0) { //l.spiegel+3
			MobileElement programGoal = (MobileElement) driver.findElementByAccessibilityId("home_card_program_goal");
			MobileElement ptCard = (MobileElement) driver.findElementByAccessibilityId("home_card_pt");
			mobileActions.swipeByElements(programGoal, ptCard);
			MobileElement sessionTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");
			MobileElement weeklyGoalTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Weekly goal']");
			mobileActions.swipeByElements(sessionTxt, weeklyGoalTxt);
			MobileElement painChart = (MobileElement) driver.findElementByAccessibilityId("home_screen_pain_chart");
			MobileElement sessionsCard = (MobileElement) driver.findElementByAccessibilityId("home_card_session_details_0");
			mobileActions.swipeByElements(painChart, sessionsCard);
			MobileElement badgesTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='My badges']");
			mobileActions.swipeByElements(badgesTxt, painChart);
		} else { //f.silva
			MobileElement remindersButton = (MobileElement) driver.findElementByAccessibilityId("home_card_weekly_goal_reminders_button");
			MobileElement myProgressTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Progress']");
			mobileActions.swipeByElements(myProgressTxt, remindersButton);
			MobileElement starsAchievedCard = (MobileElement) driver.findElementByAccessibilityId("home_card_achieved_stars");
			MobileElement myBadgesTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='My badges']");
			mobileActions.swipeByElements(myBadgesTxt, starsAchievedCard);
		}
		//validar presença do card
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='My badges']")).size() > 0) {
			//validar título do card
			String myBadges = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
			Assert.assertEquals("My badges", myBadges);
			//validar o botão see all badges
			String seeAllBadges = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[3]/android.widget.TextView").getText();
			Assert.assertEquals("See all badges", seeAllBadges);
			//pegar os valores das 3 primeiras badges
			String lastAchieved = driver.findElementByXPath("//android.view.View[@content-desc='home_badges_card_badge_0']/android.widget.TextView").getText();
				System.out.println("LAST BADGE ACHIEVED: " + lastAchieved);
			String notAchieved1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[4]/android.view.View[2]/android.widget.TextView").getText();
				System.out.println("FIRST NOT ACHIEVED BADGE: " + notAchieved1 + " ");
			String notAchieved2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[4]/android.view.View[3]/android.widget.TextView").getText();
				System.out.println("SECOND NOT ACHIEVED BADGE: " + notAchieved2);
			//fazer swipe pra mostrar a última badge
			MobileElement secondNotAchievedBadge = (MobileElement) driver.findElementByAccessibilityId("home_badges_card_badge_2");
			MobileElement achievedBadge = (MobileElement) driver.findElementByAccessibilityId("home_badges_card_badge_0");
			mobileActions.swipeByElements(secondNotAchievedBadge, achievedBadge);
			//pegar o texto da última badge
			String notAchieved3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[4]/android.view.View[3]/android.widget.TextView").getText();
				System.out.println("THIRD NOT ACHIEVED BADGE: " + notAchieved3);
			//abrir o popup de uma das badges not achieved
			driver.findElementByAccessibilityId("home_badges_card_badge_2").click();
			//imprimir valores do badge
			String badgeDetailsTitle1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[1]").getText();
				System.out.println("POPUP BADGE TITLE: " + badgeDetailsTitle1);
			String badgeDetailsInfo1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]").getText(); 
				System.out.println("POPUP BADGE DESCRIPTION: " + badgeDetailsInfo1);
			//fechar popup pelo botão
			driver.findElementByXPath("//android.widget.Button").click();
			//abrir my badges
			driver.findElementByAccessibilityId("home_badges_card_see_more_button").click();
		} else {
			System.out.println("BADGES CARD NOT FOUND!");
		//	driver.quit();
		}
		//validar título
		String myBadges = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.widget.TextView").getText();
		Assert.assertEquals("My badges", myBadges);
		//validar título e badges do number of sessions
		String numberOfSessions = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[1]").getText();
		Assert.assertEquals("Number of sessions", numberOfSessions);
		//essa difernça de xpath para badges achieved e not achieved deve partir meus testes de acordo com o que tá ou não achieved nos users
		String session1 = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_0_badge_0']/android.widget.TextView").getText();
		Assert.assertEquals("1st Session", session1);
		String session2 = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_0_badge_1']/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("3rd Session", session2);
		String session3 = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_0_badge_2']/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("9th Session", session3);
		String session4 = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_0_badge_3']/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("18th Session", session4);
		//pegar o número de badges
		String numberOfSessionsBadgesAchieved = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
			System.out.println("BADGES ACHIEVED: " + numberOfSessionsBadgesAchieved);
		//clicar na badge de 18 sessões
		driver.findElementByAccessibilityId("badges_screen_section_0_badge_3").click();
		//validar details do popup
		String badgeDetailsTitle2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[1]").getText();
		Assert.assertEquals("18th Session", badgeDetailsTitle2);
		String badgeDetailsInfo2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]").getText();
		Assert.assertEquals("Complete your 18th exercise session", badgeDetailsInfo2);
		//clicar fora do popup
		mobileActions.tapByCoordinates(248, 158);
		//Fazer scroll do number of weekly goals até o number of sessions
		MobileElement numberWeeklyGoalsTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Number of weekly goals']");
		MobileElement firstSessionTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='1st Session']");
		mobileActions.swipeByElements(numberWeeklyGoalsTxt, firstSessionTxt);
		//validar título e nome das badges do number of weekly goals
		String weeklyGoals = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[1]").getText();
		Assert.assertEquals("Number of weekly goals", weeklyGoals);
		String weeklyGoal1 = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_1_badge_0']/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("1st Weekly Goal", weeklyGoal1);
		String weeklyGoal2 = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_1_badge_1']/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("2nd Weekly Goal", weeklyGoal2);
		String weeklyGoal3 = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_1_badge_2']/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("3rd Weekly Goal", weeklyGoal3);
		String weeklyGoal4 = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_1_badge_3']/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("4th Weekly Goal", weeklyGoal4);
		//pegar o número de badges
		String numberOfBadgesAchieved2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
			System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved2);
		//clicar numa badge
		driver.findElementByAccessibilityId("badges_screen_section_1_badge_3").click();
		//validar details do popup
		String badgeDetailsTitle3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[1]").getText();
		Assert.assertEquals("4th Weekly Goal", badgeDetailsTitle3);
		String badgeDetailsInfo3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]").getText();
		Assert.assertEquals("Achieve your weekly goal for the fourth time ", badgeDetailsInfo3);
		//clicar no botão do popup
		driver.findElementByXPath("//android.widget.Button").click();
		//fazer scroll do app badges até o number of weekly goals
		MobileElement appBadgesTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='App badges']");
		MobileElement session18Txt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='18th Session']");
		mobileActions.swipeByElements(appBadgesTxt, session18Txt);
		//validar título e nome das badges do app badges
		String appBadges = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[1]").getText();
		Assert.assertEquals("App badges", appBadges);
		String remindersBadge = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_2_badge_0']/android.widget.TextView").getText();
		Assert.assertEquals("Reminders Scheduled", remindersBadge);
		String messageInChatBadge = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_2_badge_1']/android.widget.TextView").getText();
		Assert.assertEquals("1st Message In The Chat", messageInChatBadge);
		String videoWatchedBadge = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_2_badge_2']/android.widget.TextView").getText();
		Assert.assertEquals("Watched 1st Video", videoWatchedBadge);
		//pegar o número de badges
		String numberOfBadgesAchieved3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
			System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved3);
		//fazer scroll pra mostrar até o final
		MobileElement appBadgesTxt2 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='App badges']");
		MobileElement journeyStepsTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Journey steps']");
		mobileActions.swipeByElements(journeyStepsTxt, appBadgesTxt2);
		//validar o título e nome das badges do journey steps
		String journeySteps = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[3]").getText();
		Assert.assertEquals("Journey steps", journeySteps);
		String enrollmentBadge = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_3_badge_0']/android.widget.TextView").getText();
		Assert.assertEquals("Enrollment", enrollmentBadge);
		String reassessmen1tBadge = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_3_badge_1']/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("1st Reassessment", reassessmen1tBadge);
		String reassessment2Badge = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_3_badge_2']/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("2nd Reassessment", reassessment2Badge);
		String treatmentCompletedBadge = driver.findElementByXPath("//android.view.View[@content-desc='badges_screen_section_3_badge_3']/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("Treatment Completed", treatmentCompletedBadge);
		//pegar o número de badges
		String numberOfBadgesAchieved4 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[4]").getText();
			System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved4);
		//clicar na badge do enrollment
		driver.findElementByAccessibilityId("badges_screen_section_3_badge_0").click();
		//voltar
		driver.findElementByXPath("//android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My badges']")));
		driver.findElementByAccessibilityId("header_menu_button").click();
		driver.findElementByAccessibilityId("menu_option_logout").click();

		ConfigurationsAndroid.killDriver();
	}
	
}