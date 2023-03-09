package appium.Android;

import java.net.MalformedURLException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class swordRegressionSessionDetails {

	private AndroidDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsAndroid.getDriver();
	}
	
	@Test
	public void virtualPtStaging() throws MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver,50);
		MobileActions mobileActions = new MobileActions(driver);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Login']")));
		MobileElement el1 = driver.findElementByXPath("//android.widget.EditText[1]");
		el1.clear();
		el1.sendKeys("l.spiegel+3@swordhealth.com");
		MobileElement el2 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el2.click();
		el2.sendKeys("Test1234!");
		driver.findElementByAccessibilityId("loginButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		driver.findElementByXPath("//android.widget.TextView[@text='Not now']").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Almeida']")));
		//mostrar o card do lastest sessions
		MobileElement kitDeliveryCard = (MobileElement) driver.findElementByAccessibilityId("home_card_delivery_kit_status");
		MobileElement ptCard = (MobileElement) driver.findElementByAccessibilityId("home_card_pt");
		mobileActions.swipeByElements(kitDeliveryCard, ptCard);
		MobileElement weeklyGoalTxt = (MobileElement) driver.findElementByAccessibilityId("home_card_weekly_goal");
		mobileActions.swipeByElements(weeklyGoalTxt, kitDeliveryCard);
		//clicar na sessão mais recente
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Next Session']")).size() > 0) {
			driver.findElementByAccessibilityId("home_card_session_details_0_prev_date_button").click();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@content-desc='home_card_session_details_1_see_more_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_1").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Exercises']")));
		//validar o ecrã
		String chatWarning = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[1]").getText();
		Assert.assertEquals("For exercise details or to discuss it with your Physical Therapist, tap the three dots on each card.", chatWarning);
		//fechar o warning antes de fazer a validação
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.ImageView").click();
		//validação overview
		String overview = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[1]").getText();
		Assert.assertEquals("Overview", overview);
		if (driver.findElements(By.xpath("//android.view.View[@content-desc='session_details_session_overview_card']/android.widget.TextView[2]")).size() > 0) {
			String overviewStars = driver.findElementByXPath("//android.view.View[@content-desc='session_details_session_overview_card']/android.widget.TextView[2]").getText();
			System.out.println("STARS ACHHIEVED: " + overviewStars);
		} else {
			System.out.println("STARS CARD ON OVERVIEW NOT SHOWN!");
		}
		if (driver.findElements(By.xpath("//android.view.View[@content-desc='session_details_session_overview_card']/android.widget.TextView[4]")).size() > 0) {
			String overviewPain = driver.findElementByXPath("//android.view.View[@content-desc='session_details_session_overview_card']/android.widget.TextView[4]").getText();
			System.out.println("PAIN SCORE: " + overviewPain);
		} else {
			System.out.println("PAIN CARD ON OVERVIEW NOT SHOWN!");
		}
		if (driver.findElements(By.xpath("//android.view.View[@content-desc='session_details_session_overview_card']/android.widget.TextView[6]")).size() > 0) {
			String overviewFatigue = driver.findElementByXPath("//android.view.View[@content-desc='session_details_session_overview_card']/android.widget.TextView[6]").getText();
			System.out.println("FATIGUE SCORE: " + overviewFatigue);
		} else {
			System.out.println("FATIGUE CARD ON OVERVIEW NOT SHOWN!");
		}	
		//validação exercícios
		String exercises = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[2]").getText();
		Assert.assertEquals("Exercises", exercises);
		String exerciseNumber1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[1]").getText();
		String exerciseName1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[2]").getText();
		String reps1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[3]").getText();
		String score1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[4]").getText();
			System.out.println("Exercise nº: " + exerciseNumber1 + " - " + exerciseName1 + " " + reps1);
		String exerciseNumber2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[1]").getText();
		String exerciseName2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[2]").getText();
		String reps2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[3]").getText();
		String score2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[4]").getText();
			System.out.println("Exercise nº: " + exerciseNumber2 + " - " + exerciseName2 + " " + reps2);
		//fazer scroll
		MobileElement exerciseCard2 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_2_card");
		MobileElement overviewTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Overview']");
		mobileActions.swipeByElements(exerciseCard2, overviewTxt);
		//validar o ecrã
		String exerciseNumberRandom1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[1]").getText();
		String exerciseNameRandom1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[2]").getText();
		String repsRandom1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[3]").getText();
			System.out.println("Exercise nº: " + exerciseNumberRandom1 + " - " + exerciseNameRandom1 + " " + repsRandom1);
		String exerciseNumberRandom2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[4]/android.widget.TextView[1]").getText();
		String exerciseNameRandom2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[4]/android.widget.TextView[2]").getText();
		String repsRandom2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[4]/android.widget.TextView[3]").getText();
			System.out.println("Exercise nº: " + exerciseNumberRandom2 + " - " + exerciseNameRandom2 + " " + repsRandom2);
		//pull to refresh
		MobileElement exerciseCard22 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_2_card");
		MobileElement exerciseCard7 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_7_card");
		mobileActions.swipeByElements(exerciseCard22, exerciseCard7);
		MobileElement overviewCard = (MobileElement) driver.findElementByAccessibilityId("session_details_session_overview_card");
		MobileElement exerciseCard3 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_2_card");
		mobileActions.swipeByElements(overviewCard, exerciseCard3);
		//validar next session ou sessão mais recente
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Session overview']")).size() > 0) {
			String overviewGoal1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[2]").getText();
			String overviewGoal2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[3]").getText();
			String overviewTime1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[4]").getText();
			String overviewTime2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[5]").getText();
			String overviewExercises1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[6]").getText();
			String overviewExercises2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[7]").getText();
			String overviewTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[1]").getText();
			String youllNeed = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[8]").getText();
			String swordGoTitle = driver.findElementByAccessibilityId("sword_go_card_title").getText();
			String swordGoSubtitle = driver.findElementByAccessibilityId("sword_go_card_subtitle").getText();
			Assert.assertEquals("Goal:", overviewGoal1);
			Assert.assertEquals("Reduce discomfort and control pain", overviewGoal2);
			Assert.assertEquals("Time:", overviewTime1);
			Assert.assertEquals("6 minutes", overviewTime2);
			Assert.assertEquals("Exercises:", overviewExercises1);
			Assert.assertEquals("9 exercises", overviewExercises2);
			Assert.assertEquals("Session overview", overviewTitle);
			Assert.assertEquals("What you’ll need", youllNeed);
			Assert.assertEquals("On the road? Can't use your Digital Therapist?", swordGoTitle);
			Assert.assertEquals("Start your session now with GO", swordGoSubtitle);
			//fazer scroll e validar alguns exercícios
			MobileElement youllNeedTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='What you’ll need']");
			MobileElement sessionOverviewTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Session overview']");
			mobileActions.swipeByElements(youllNeedTxt, sessionOverviewTxt);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}			
			String nextSessionExsTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView").getText();
			String nextSessionEx1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[1]/android.widget.TextView[2]").getText();
			Assert.assertEquals("Next session exercises", nextSessionExsTitle);
			Assert.assertEquals("Leg raise with knee bend", nextSessionEx1);
		} else {
			//validar se é a mesma sessão - não vai ser por causa do next session
			String refreshExerciseName1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView[2]").getText();
			String refreshReps1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView[3]").getText();
			String refreshScore1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.TextView[4]").getText();
			String refreshExerciseName2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.widget.TextView[2]").getText();
			String refreshReps2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.widget.TextView[3]").getText();
			String refreshScore2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.widget.TextView[4]").getText();
			Assert.assertEquals(exerciseName1, refreshExerciseName1);
			Assert.assertEquals(reps1, refreshReps1);
			Assert.assertEquals(score1, refreshScore1);
			Assert.assertEquals(exerciseName2, refreshExerciseName2);
			Assert.assertEquals(reps2, refreshReps2);
			Assert.assertEquals(score2, refreshScore2);
		}
		//selecionar outro dia
		driver.findElementByAccessibilityId("session_details_carousel_date_card_1").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Exercises']")));
		//validar que está diferente - valores fixos para comparação do next session
		String exerciseNameComp1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[2]").getText();
		String repsComp1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[3]").getText();
		String exerciseNameComp2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[2]").getText();
		String repsComp2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[3]").getText();
	//	Assert.assertEquals("Leg raise with knee bend", exerciseNameComp1);
		Assert.assertNotEquals("8 reps", repsComp1);
	//	Assert.assertEquals("Leg to the side", exerciseNameComp2);
		Assert.assertNotEquals("8 reps", repsComp2);
		//pull to refresh
		MobileElement overviewTxt2 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Overview']");
		MobileElement exerciseCard4 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_1_card");
		mobileActions.swipeByElements(overviewTxt2, exerciseCard4);
		//validar que abriu next session de novo
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Session overview']")));
		//voltar pra home
		driver.findElementByXPath("//android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@content-desc='home_card_session_details_1_date_label']")));
		//abrir outro dia clicando na setinha
		driver.findElementByAccessibilityId("home_card_session_details_1_prev_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_2_next_date_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_2_next_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_1_next_date_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_1_see_more_button").click();
		//fazer scroll do carrossel
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Overview']")));
		MobileElement nextSession = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[3]");
		MobileElement firstSession = (MobileElement) driver.findElementByAccessibilityId("session_details_carousel_date_card_1");
		mobileActions.swipeByElements(nextSession, firstSession);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Session 2']")));
		mobileActions.swipeByElements(firstSession, nextSession);
		//abrir outro dia - card mais a esquerda
		driver.findElementByAccessibilityId("session_details_carousel_date_card_1").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Overview']")));
		//voltar pra home
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_1']")));
		//mudar até a sessão mais antiga
		driver.findElementByAccessibilityId("home_card_session_details_1_prev_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_2']")));
		driver.findElementByAccessibilityId("home_card_session_details_2").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Overview']")));
		//validar que abriu a sessão certa - user l.spiegel+3@swordhealth.com
		String moreSessionsExercise1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[2]").getText();
		String moreSessionsExercise2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[2]").getText();
		String moreSessionsExercise3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[4]/android.widget.TextView[2]").getText();
		Assert.assertEquals("Leg raise with knee bend", moreSessionsExercise1);
		Assert.assertEquals("Leg to the side", moreSessionsExercise2);
		Assert.assertEquals("Leg backwards", moreSessionsExercise3);
		//compartilhar sessão no chat
		driver.findElementByAccessibilityId("session_details_session_overview_card_share_button").click();
		String popupSessionTxt = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("Would you like to share feedback from this session in chat?", popupSessionTxt);
		driver.findElementByXPath("//android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
		//voltar
		driver.findElementByAccessibilityId("bottom_navigation_home_tab").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_delivery_kit_status']")));
		MobileElement kitDeliveryCard2 = (MobileElement) driver.findElementByAccessibilityId("home_card_delivery_kit_status");
		MobileElement ptCard2 = (MobileElement) driver.findElementByAccessibilityId("home_card_pt");
		mobileActions.swipeByElements(kitDeliveryCard2, ptCard2);
		MobileElement weeklyGoalTxt2 = (MobileElement) driver.findElementByAccessibilityId("home_card_weekly_goal");
		mobileActions.swipeByElements(weeklyGoalTxt2, kitDeliveryCard2);
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Next Session']")).size() > 0) {
			driver.findElementByAccessibilityId("home_card_session_details_0_prev_date_button").click();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@content-desc='home_card_session_details_1_see_more_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_1").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Overview']")));
		//partilhar um exercício no chat
		driver.findElementByAccessibilityId("session_details_exercise_0_card_see_more").click();
		driver.findElementByAccessibilityId("session_details_exercise_0_card_share").click();
		String popupExerciseTxt = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView").getText();
		Assert.assertEquals("Would you like to share feedback from this exercise in chat?", popupExerciseTxt);
		driver.findElementByXPath("//android.widget.Button").click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@text='Start typing...']")));
		//fazer logout
		driver.findElementByAccessibilityId("bottom_navigation_home_tab").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Almeida']")));
		driver.findElementByAccessibilityId("header_menu_button").click();
		driver.findElementByAccessibilityId("menu_option_logout").click();

		ConfigurationsAndroid.killDriver();
		
	}
	
	@Test
	public void virtualPtLive() throws MalformedURLException {
		MobileActions mobileActions = new MobileActions(driver);
		WebDriverWait wait = new WebDriverWait(driver,50);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Connexion']"))); //voltar pra inglês
		MobileElement el1 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[1]");
		el1.clear();
		el1.sendKeys("f.silva@swordhealth.com");
		MobileElement el2 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[2]");
		el2.click();
		el2.sendKeys("Cabixuda12");
		driver.findElementByAccessibilityId("loginButton").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Créez votre code PIN']"))); //voltar pra inglês
		driver.findElementByXPath("//android.widget.TextView[@text='Pas maintenant']").click(); //voltar pra inglês
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Marta Casaca']")));
		//mostrar o card do lastest sessions
		MobileElement sessionsTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");
		MobileElement weeklyGoalTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Objectif hebdomadaire']"); //voltar pra inglês
		mobileActions.swipeByElements(sessionsTxt, weeklyGoalTxt);
		//clicar na sessão mais recente
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Prochaine Séance']")).size() > 0) { //voltar pra inglês
			driver.findElementByAccessibilityId("home_card_session_details_0_prev_date_button").click();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@content-desc='home_card_session_details_1_see_more_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_1").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Exercices']"))); //voltar pra inglês
		//validar o ecrã
		String chatWarning = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[1]").getText();
	//	Assert.assertEquals("For exercise details or to discuss it with your Physical Therapist, tap the three dots on each card.", chatWarning);
		//fechar o warning antes de fazer a validação
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.widget.ImageView").click();
		//validação overview
		String overview = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[1]").getText();
	//	Assert.assertEquals("Overview", overview);
		if (driver.findElements(By.xpath("//android.view.View[@content-desc='session_details_session_overview_card']/android.widget.TextView[2]")).size() > 0) {
			String overviewStars = driver.findElementByXPath("//android.view.View[@content-desc='session_details_session_overview_card']/android.widget.TextView[2]").getText();
			System.out.println("STARS ACHHIEVED: " + overviewStars);
		} else {
			System.out.println("STARS CARD ON OVERVIEW NOT SHOWN!");
		}
		if (driver.findElements(By.xpath("//android.view.View[@content-desc='session_details_session_overview_card']/android.widget.TextView[4]")).size() > 0) {
			String overviewPain = driver.findElementByXPath("//android.view.View[@content-desc='session_details_session_overview_card']/android.widget.TextView[4]").getText();
			System.out.println("PAIN SCORE: " + overviewPain);
		} else {
			System.out.println("PAIN CARD ON OVERVIEW NOT SHOWN!");
		}
		if (driver.findElements(By.xpath("//android.view.View[@content-desc='session_details_session_overview_card']/android.widget.TextView[6]")).size() > 0) {
			String overviewFatigue = driver.findElementByXPath("//android.view.View[@content-desc='session_details_session_overview_card']/android.widget.TextView[6]").getText();
			System.out.println("FATIGUE SCORE: " + overviewFatigue);
		} else {
			System.out.println("FATIGUE CARD ON OVERVIEW NOT SHOWN!");
		}	
		//validação exercícios
		String exercises = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[2]").getText();
	//	Assert.assertEquals("Exercises", exercises);
		String exerciseNumber1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[1]").getText();
		String exerciseName1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[2]").getText();
		String reps1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[3]").getText();
		String score1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[4]").getText();
			System.out.println("Exercise nº: " + exerciseNumber1 + " - " + exerciseName1 + " " + reps1);
		String exerciseNumber2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[1]").getText();
		String exerciseName2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[2]").getText();
		String reps2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[3]").getText();
		String score2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[4]").getText();
			System.out.println("Exercise nº: " + exerciseNumber2 + " - " + exerciseName2 + " " + reps2);
		//fazer scroll
		MobileElement exerciseCard2 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_2_card");
		MobileElement overviewTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Aperçu']"); //voltar pra inglês
		mobileActions.swipeByElements(exerciseCard2, overviewTxt);
		//validar o ecrã
		String exerciseNumberRandom1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[1]").getText();
		String exerciseNameRandom1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[2]").getText();
		String repsRandom1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[3]").getText();
			System.out.println("Exercise nº: " + exerciseNumberRandom1 + " - " + exerciseNameRandom1 + " " + repsRandom1);
		String exerciseNumberRandom2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[1]").getText();
		String exerciseNameRandom2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[2]").getText();
		String repsRandom2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[3]").getText();
			System.out.println("Exercise nº: " + exerciseNumberRandom2 + " - " + exerciseNameRandom2 + " " + repsRandom2);
		//pull to refresh
		MobileElement exerciseCard4 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_4_card");
		MobileElement exerciseCard9 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_9_card");
		mobileActions.swipeByElements(exerciseCard4, exerciseCard9);
		MobileElement overviewCard = (MobileElement) driver.findElementByAccessibilityId("session_details_session_overview_card");
		MobileElement exerciseCard3 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_2_card");
		mobileActions.swipeByElements(overviewCard, exerciseCard3);
		//validar next session ou sessão mais recente
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Aperçu de la séance']")).size() > 0) { //voltar pra inglês
			String overviewGoal1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[2]").getText();
			String overviewGoal2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[3]").getText();
			String overviewTime1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[4]").getText();
			String overviewTime2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[5]").getText();
			String overviewExercises1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[6]").getText();
			String overviewExercises2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[7]").getText();
			String overviewTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[1]").getText();
			String youllNeed = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[8]").getText();
	//		String swordGoTitle = driver.findElementByAccessibilityId("sword_go_card_title").getText();
	//		String swordGoSubtitle = driver.findElementByAccessibilityId("sword_go_card_subtitle").getText();
	//		Assert.assertEquals("Goal:", overviewGoal1);
	//		Assert.assertEquals("Improve low back strength", overviewGoal2);
	//		Assert.assertEquals("Time:", overviewTime1);
	//		Assert.assertEquals("31 minutes", overviewTime2);
	//		Assert.assertEquals("Exercises:", overviewExercises1);
	//		Assert.assertEquals("26 exercises", overviewExercises2);
	//		Assert.assertEquals("Session overview", overviewTitle);
	//		Assert.assertEquals("What you’ll need", youllNeed);
	//		Assert.assertEquals("On the road? Can't use your Digital Therapist?", swordGoTitle);
	//		Assert.assertEquals("Start your session now with GO", swordGoSubtitle);
			//fazer scroll e validar alguns exercícios
			MobileElement youllNeedTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Ce dont vous aurez besoin']"); //voltar pra inglês
			MobileElement sessionOverviewTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Aperçu de la séance']"); //voltar pra inglês
			mobileActions.swipeByElements(youllNeedTxt, sessionOverviewTxt);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}			
			String nextSessionExsTitle = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView[4]").getText();
			String nextSessionEx1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[1]/android.widget.TextView[2]").getText();
	//		Assert.assertEquals("Next session exercises", nextSessionExsTitle);
	//		Assert.assertEquals("Trunk forward bend", nextSessionEx1);
		} else {
			//validar se é a mesma sessão - não vai ser por causa do next session
			String refreshExerciseName1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[2]").getText();
			String refreshReps1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[3]").getText();
			String refreshScore1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[4]").getText();
			String refreshExerciseName2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[2]").getText();
			String refreshReps2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.widget.TextView[3]").getText();
			String refreshScore2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.widget.TextView[4]").getText();
	//		Assert.assertEquals(exerciseName1, refreshExerciseName1);
	//		Assert.assertEquals(reps1, refreshReps1);
	//		Assert.assertEquals(score1, refreshScore1);
	//		Assert.assertEquals(exerciseName2, refreshExerciseName2);
	//		Assert.assertEquals(reps2, refreshReps2);
	//		Assert.assertEquals(score2, refreshScore2);
		}
		//validar abrir um set - MUDAR DE POSIÇÃO
	
		//selecionar outro dia
		driver.findElementByAccessibilityId("session_details_carousel_date_card_1").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Exercices']"))); //voltar pra inglês
		//validar que está diferente - valores fixos para comparação do next session
		String exerciseNameComp1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[2]").getText();
		String repsComp1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[3]").getText();
		String exerciseNameComp2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[2]").getText();
		String repsComp2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[3]").getText();
		Assert.assertNotEquals("Trunk forward bend", exerciseNameComp1);
		Assert.assertNotEquals("6 reps", repsComp1);
		Assert.assertNotEquals("Trunk side bend", exerciseNameComp2);
		Assert.assertNotEquals("12 reps", repsComp2);
		//pull to refresh
		MobileElement overviewTxt2 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Aperçu']"); //voltar pra inglês
		MobileElement exerciseCard42 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_1_card");
		mobileActions.swipeByElements(overviewTxt2, exerciseCard42);
		//validar que abriu o next session
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Aperçu de la séance']"))); //voltar pra inglês
		//voltar pra home
		driver.findElementByXPath("//android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@content-desc='home_card_session_details_1_date_label']")));
		//abrir outro dia clicando na setinha
		driver.findElementByAccessibilityId("home_card_session_details_1_prev_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_2_prev_date_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_2_prev_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_3_next_date_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_3_next_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@content-desc='home_card_session_details_2_see_more_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_2_see_more_button").click();
		//fazer scroll do carrossel
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Aperçu']"))); //voltar pra inglês
		MobileElement latestSession = (MobileElement) driver.findElementByAccessibilityId("session_details_carousel_date_card_0");
		MobileElement toLeft = (MobileElement) driver.findElementByAccessibilityId("session_details_carousel_date_card_2");
		mobileActions.swipeByElements(latestSession, toLeft);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='session_details_carousel_date_card_0']")));
		mobileActions.swipeByElements(toLeft, latestSession);
		//abrir outro dia - card mais a esquerda
		driver.findElementByAccessibilityId("session_details_carousel_date_card_3").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Aperçu']"))); //voltar pra inglês
		//voltar pra home
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_2']")));
		//mudar até a sessão mais antiga
		driver.findElementByAccessibilityId("home_card_session_details_2_prev_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_3_prev_date_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_3_prev_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_4_prev_date_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_4_prev_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_5_prev_date_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_5_prev_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_6_prev_date_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_6_prev_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_7_prev_date_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_7_prev_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_8_prev_date_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_8_prev_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_9_prev_date_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_9_prev_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_10']")));
		//abrir see more sessions
		driver.findElementByAccessibilityId("home_card_session_details_10").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Aperçu']"))); //voltar pra inglês
		//validar que abriu a sessão certa - do user da Filipa
		String moreSessionsExercise1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[2]").getText();
		String moreSessionsExercise2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[2]").getText();
		String moreSessionsExercise3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.x0/android.view.View/android.view.View/android.view.View[3]/android.view.View[4]/android.widget.TextView[2]").getText();
	//	Assert.assertEquals("Trunk side bend", moreSessionsExercise1);
	//	Assert.assertEquals("Trunk forward bend", moreSessionsExercise2);
	//	Assert.assertEquals("Trunk backwards bend", moreSessionsExercise3);
		//compartilhar sessão no chat
		driver.findElementByAccessibilityId("session_details_session_overview_card_share_button").click();
		String popupSessionTxt = driver.findElementByXPath("//android.widget.TextView").getText();
	//	Assert.assertEquals("Would you like to share feedback from this session in chat?", popupSessionTxt);
		driver.findElementByXPath("//android.widget.Button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
		//voltar
		driver.findElementByAccessibilityId("bottom_navigation_home_tab").click(); //em Android abre a home e não o session details
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']")));
		MobileElement sessionsTxt2 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");
		MobileElement weeklyGoalTxt2 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Objectif hebdomadaire']"); //voltar pra inglês
		mobileActions.swipeByElements(sessionsTxt2, weeklyGoalTxt2);
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Prochaine Séance']")).size() > 0) { //voltar pra inglês
			driver.findElementByAccessibilityId("home_card_session_details_0_prev_date_button").click();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@content-desc='home_card_session_details_1_see_more_button']")));
		driver.findElementByAccessibilityId("home_card_session_details_1").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Aperçu']"))); //voltar pra inglês
		//partilhar um exercício no chat
		driver.findElementByAccessibilityId("session_details_exercise_0_card_see_more").click();
		driver.findElementByAccessibilityId("session_details_exercise_0_card_share").click();
		String popupExerciseTxt = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView").getText();
	//	Assert.assertEquals("Would you like to share feedback from this exercise in chat?", popupExerciseTxt);
		driver.findElementByXPath("//android.widget.Button").click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@text='Commencez à taper...']"))); //voltar pra inglês
		//fazer logout
		driver.findElementByAccessibilityId("bottom_navigation_home_tab").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Marta Casaca']")));
		driver.findElementByAccessibilityId("header_menu_button").click();
		driver.findElementByAccessibilityId("menu_option_logout").click();

		ConfigurationsAndroid.killDriver();
	}
}