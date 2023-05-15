package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class swordRegressionBadges {

	private AndroidDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsAndroid.getDriver();
	}

	private final static String CHECK_HOME_BADGES = "home_badges_screen";
	private final static String CHECK_HOME_BADGES_SCROLL = "home_badges_scroll_screen";
	private final static String CHECK_HOME_BADGE_NOT_ACHIEVED = "home_badge_not_achieved";
	private final static String CHECK_BADGE_SCREEN_1 = "badge_screen_1";
	private final static String CHECK_BADGE_SCREEN_4 = "badge_screen_4";
	private final static String CHECK_BADGE_POPUP_1 = "badge_screen_popup_1";
	private final static String CHECK_BADGE_POPUP_2 = "badge_screen_popup_2";

	private void wait(org.openqa.selenium.support.ui.ExpectedCondition<WebElement> presenceOfElementLocated) {
	// TODO Auto-generated method stub
	}

	@Test
	public void production() throws Exception {
		MobileActions mobileActions = new MobileActions(driver);
		WebDriverWait wait = new WebDriverWait(driver,20);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		VisualCheck visualCheck = new VisualCheck(driver);
		
		utilitiesAndroid.login("l.spiegel+3@swordhealth.com", "Test1234!", driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//fazer swipe pra mostrar o card das badges - ver se o de prod funciona bem
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Luiza Almeida']")).size() > 0) { //l.spiegel+3
			MobileElement programGoal = driver.findElementByAccessibilityId("home_card_program_goal");
			MobileElement ptCard = driver.findElementByAccessibilityId("home_card_pt");
			mobileActions.swipeByElements(programGoal, ptCard);
			MobileElement sessionTxt = driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");
			MobileElement weeklyGoalTxt = driver.findElementByXPath("//android.widget.TextView[@text='Weekly goal']");
			mobileActions.swipeByElements(sessionTxt, weeklyGoalTxt);
			MobileElement progressTxt = driver.findElementByXPath("//android.widget.TextView[@text='Progress']");
			MobileElement sessionsCard = driver.findElementByAccessibilityId("home_card_session_details_0");
			mobileActions.swipeByElements(progressTxt, sessionsCard);
			MobileElement painChart = driver.findElementByAccessibilityId("home_screen_pain_chart");
			mobileActions.swipeByElements(painChart, sessionsCard);
			MobileElement badgesTxt = driver.findElementByXPath("//android.widget.TextView[@text='My badges']");
			mobileActions.swipeByElements(badgesTxt, painChart);
		} else { //f.silva
			MobileElement ptCard = driver.findElementByAccessibilityId("home_card_pt");
			MobileElement sessionsTxt = driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");
			mobileActions.swipeByElements(sessionsTxt, ptCard);
			MobileElement painCard = driver.findElementByAccessibilityId("home_screen_pain_chart");
			MobileElement sessionsCard = driver.findElementByAccessibilityId("home_card_session_details_0");
			mobileActions.swipeByElements(painCard, sessionsCard);
		}
		//validar presença do card
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='My badges']")).size() > 0) {
			//validar título do card
			driver.findElementByXPath("//android.widget.TextView[@text='My badges']");
			//validar o botão see all badges
			driver.findElementByXPath("//android.widget.TextView[@text='See more']");
			//pegar os valores das 3 primeiras badges
			String lastAchieved = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[4]/android.view.View[1]/android.widget.TextView").getText();
			System.out.println("LAST BADGE ACHIEVED: " + lastAchieved);
			String notAchieved1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[4]/android.view.View[2]/android.widget.TextView").getText();
			System.out.println("FIRST NOT ACHIEVED BADGE: " + notAchieved1 + " ");
			String notAchieved2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[4]/android.view.View[3]/android.widget.TextView").getText();
			System.out.println("SECOND NOT ACHIEVED BADGE: " + notAchieved2);
			VisualCheck.doVisualCheck(CHECK_HOME_BADGES);
			//clicar na badge achieved - tem um bug que abre o popup
			utilitiesAndroid.clickByAccessibilityId("home_badges_card_badge_0", driver);
			if (driver.findElements(By.xpath("//android.widget.TextView[@text='Got it']")).size() > 0) {
				System.out.println("BUG DO POPUP DA BADGE ACHIEVED");
				utilitiesAndroid.clickByXPath("//android.widget.TextView[@text='Got it']", driver);
			} else {
				System.out.println("BUG DO POPUP DA BADGE ACHIEVED CORRIGIDO");
			}
			//fazer swipe pra mostrar a última badge
			MobileElement secondNotAchievedBadge = driver.findElementByAccessibilityId("home_badges_card_badge_2");
			MobileElement achievedBadge = driver.findElementByAccessibilityId("home_badges_card_badge_0");
			mobileActions.swipeByElements(secondNotAchievedBadge, achievedBadge);
			//pegar o texto da última badge
			String notAchieved3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[4]/android.view.View[3]/android.widget.TextView").getText();
			System.out.println("THIRD NOT ACHIEVED BADGE: " + notAchieved3);
			VisualCheck.doVisualCheck(CHECK_HOME_BADGES_SCROLL);
			//abrir o popup de uma das badges not achieved
			utilitiesAndroid.clickByAccessibilityId("home_badges_card_badge_2", driver);
			//imprimir valores do badge
			String badgeDetailsTitle1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[1]").getText();
			System.out.println("POPUP BADGE TITLE: " + badgeDetailsTitle1);
			String badgeDetailsInfo1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]").getText(); 
			System.out.println("POPUP BADGE DESCRIPTION: " + badgeDetailsInfo1);
			VisualCheck.doVisualCheck(CHECK_HOME_BADGE_NOT_ACHIEVED);
			//fechar popup pelo botão
			utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
			//abrir my badges
			utilitiesAndroid.clickByAccessibilityId("home_badges_card_see_more_button", driver);
		} else {
			System.out.println("BADGES CARD NOT FOUND!");
			driver.quit();
		}
		//validar título
		driver.findElementByXPath("//android.widget.TextView[@text='My badges']");
		//validar título e badges do number of sessions
		driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");
		driver.findElementByXPath("//android.widget.TextView[@text='1st Session']");
		driver.findElementByXPath("//android.widget.TextView[@text='3rd Session']");
		driver.findElementByXPath("//android.widget.TextView[@text='9th Session']");
		driver.findElementByXPath("//android.widget.TextView[@text='18th Session']");
		//pegar o número de badges
		String numberOfSessionsBadgesAchieved = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
		System.out.println("BADGES ACHIEVED: " + numberOfSessionsBadgesAchieved);
		VisualCheck.doVisualCheck(CHECK_BADGE_SCREEN_1);
		//clicar na badge de 18 sessões
		utilitiesAndroid.clickByAccessibilityId("badges_screen_section_0_badge_3", driver);
		//validar details do popup
		driver.findElementByXPath("//android.widget.TextView[@text='18th Session']");
		driver.findElementByXPath("//android.widget.TextView[@text='Complete your 18th exercise session']");
		VisualCheck.doVisualCheck(CHECK_BADGE_POPUP_1);
		//clicar fora do popup
		mobileActions.tapByCoordinates(248, 158);
		//Fazer scroll do number of weekly goals até o number of sessions
		MobileElement numberWeeklyGoalsTxt = driver.findElementByXPath("//android.widget.TextView[@text='Weekly goals']");
		MobileElement firstSessionTxt = driver.findElementByXPath("//android.widget.TextView[@text='1st Session']");
		mobileActions.swipeByElements(numberWeeklyGoalsTxt, firstSessionTxt);
		//validar título e nome das badges do number of weekly goals
		driver.findElementByXPath("//android.widget.TextView[@text='Weekly goals']");
		driver.findElementByXPath("//android.widget.TextView[@text='1st Weekly Goal']");
		driver.findElementByXPath("//android.widget.TextView[@text='2nd Weekly Goal']");
		driver.findElementByXPath("//android.widget.TextView[@text='3rd Weekly Goal']");
		driver.findElementByXPath("//android.widget.TextView[@text='4th Weekly Goal']").getText();
		//pegar o número de badges
		String numberOfBadgesAchieved2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
		System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved2);
		//clicar numa badge
		utilitiesAndroid.clickByAccessibilityId("badges_screen_section_1_badge_3", driver);
		//validar details do popup
		driver.findElementByXPath("//android.widget.TextView[@text='4th Weekly Goal']");
		driver.findElementByXPath("//android.widget.TextView[@text='Achieve your weekly goal for the fourth time ']");
		VisualCheck.doVisualCheck(CHECK_BADGE_POPUP_2);
		//clicar no botão do popup
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		//fazer scroll do app badges até o number of weekly goals
		MobileElement appBadgesTxt = driver.findElementByXPath("//android.widget.TextView[@text='Getting started']");
		MobileElement session18Txt = driver.findElementByXPath("//android.widget.TextView[@text='18th Session']");
		mobileActions.swipeByElements(appBadgesTxt, session18Txt);
		//validar título e nome das badges do app badges
		driver.findElementByXPath("//android.widget.TextView[@text='Getting started']");
		driver.findElementByXPath("//android.widget.TextView[@text='Reminders Scheduled']");
		driver.findElementByXPath("//android.widget.TextView[@text='1st Message In The Chat']");
		driver.findElementByXPath("//android.widget.TextView[@text='Watched 1st Video']");
		//pegar o número de badges
		String numberOfBadgesAchieved3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[2]").getText();
		System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved3);
		//fazer scroll pra mostrar até o final
		MobileElement appBadgesTxt2 = driver.findElementByXPath("//android.widget.TextView[@text='Getting started']");
		MobileElement journeyStepsTxt = driver.findElementByXPath("//android.widget.TextView[@text='Program milestones']");
		mobileActions.swipeByElements(journeyStepsTxt, appBadgesTxt2);
		//validar o título e nome das badges do journey steps
		driver.findElementByXPath("//android.widget.TextView[@text='Program milestones']");
		driver.findElementByXPath("//android.widget.TextView[@text='Enrollment']");
		driver.findElementByXPath("//android.widget.TextView[@text='1st Reassessment']");
		driver.findElementByXPath("//android.widget.TextView[@text='2nd Reassessment']");
		driver.findElementByXPath("//android.widget.TextView[@text='Treatment Completed']");
		//pegar o número de badges
		String numberOfBadgesAchieved4 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView[4]").getText();
		System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved4);
		VisualCheck.doVisualCheck(CHECK_BADGE_SCREEN_4);
		//clicar na badge do enrollment
		utilitiesAndroid.clickByAccessibilityId("badges_screen_section_3_badge_0", driver);
		//voltar
		utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My badges']")));
		utilitiesAndroid.clickByAccessibilityId("header_menu_button", driver);
		utilitiesAndroid.clickByAccessibilityId("menu_option_logout", driver);

		ConfigurationsAndroid.killDriver();
	}
	
}