package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.net.MalformedURLException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class NewSwordRegressionSessionDetails {

	private final static String VALIDATION_PATH = "/Users/luizaspiegel/Documents/image check/regression session details/Android";
	private final static String BASELINE = "COMP_";

	private AndroidDriver<MobileElement> driver;

	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsAndroid.getDriver();
	}

	private final static String CHECK_HOME_NEXT_SESSION = "home_next_session_card";
	private final static String CHECK_HOME_NEXT_SESSION_STAGING = "home_next_session_card_staging";
	private final static String CHECK_HOME_SESSION_CARD_1 = "home_session_card_1";
	private final static String CHECK_HOME_SESSION_CARD_1_STAGING = "home_session_card_1_staging";
	private final static String CHECK_SESSION_DETAILS_WARNING = "session_details_warning";
	private final static String CHECK_SESSION_DETAILS_WARNING_STAGING = "session_details_warning_staging";
	private final static String CHECK_SESSION_DETAILS_LATEST_SESSION = "session_details_latest_session";
	private final static String CHECK_SESSION_DETAILS_LATEST_SESSION_STAGING = "session_details_latest_session_staging";
	private final static String CHECK_SESSION_DETAILS_NEXT_SESSION = "session_details_next_session";
	private final static String CHECK_SESSION_DETAILS_NEXT_SESSION_STAGING = "session_details_next_session_staging";
	private final static String CHECK_SESSION_DETAILS_SESSION_1_STAGING = "session_details_session_1_staging";
	private final static String CHECK_SESSION_DETAILS_SESSION_4 = "session_details_session_4";
	private final static String CHECK_SESSION_DETAILS_POPUP_SESSION = "session_details_popup_session";
	private final static String CHECK_SESSION_DETAILS_POPUP_SESSION_STAGING = "session_details_popup_session_staging";
	private final static String CHECK_SESSION_DETAILS_POPUP_EXERCISE = "session_details_popup_exercise";
	private final static String CHECK_SESSION_DETAILS_POPUP_EXERCISE_STAGING = "session_details_popup_exercise_staging";
	private final static String CHECK_SESSION_DETAILS_BOTTOM_SHEET_STAGING = "session_details_bottom_sheet_staging";
	private final static String CHECK_SESSION_DETAILS_BOTTOM_SHEET = "session_details_bottom_sheet";

	@Test
	public void virtualPt() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,50);
		MobileActions mobileActions = new MobileActions(driver);
		UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
		VisualCheck visualCheck = new VisualCheck(driver);

		utilitiesAndroid.login("f.silva@swordhealth.com", "Cabixuda12", driver);
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='Luiza Almeida']")).size() > 0) { //l.spiegel+3
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Luiza Almeida']")));
			//mostrar o card do lastest sessions
			MobileElement kitDeliveryCard = driver.findElementByAccessibilityId("home_card_delivery_kit_status");
			MobileElement ptCard = driver.findElementByAccessibilityId("home_card_pt");
			mobileActions.swipeByElements(kitDeliveryCard, ptCard);
			MobileElement weeklyGoalTxt = driver.findElementByAccessibilityId("home_card_weekly_goal");
			mobileActions.swipeByElements(weeklyGoalTxt, kitDeliveryCard);
			//clicar na sessão mais recente
			if (driver.findElements(By.xpath("//android.widget.TextView[@text='Next Session']")).size() > 0) {
//				visualCheck.doVisualCheck(CHECK_HOME_NEXT_SESSION_STAGING);
				utilitiesAndroid.clickByAccessibilityId("home_card_session_details_0_prev_date_button", driver);
			}
			//o id do see more button sumiu com a mudança do layout do botão
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc=\"home_card_session_details_1\"]")));
//			visualCheck.doVisualCheck(CHECK_HOME_SESSION_CARD_1_STAGING);
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_1", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Exercises']")));
			//validar o ecrã
			driver.findElementByXPath("//android.widget.TextView[@text='For exercise details or to discuss it with your Physical Therapist, tap the three dots on each card.']");
			visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_WARNING_STAGING);
			//fechar o warning
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.widget.ImageView", driver);
			//validação overview
			driver.findElementByXPath("//android.widget.TextView[@text='Overview']");
			if (driver.findElements(By.xpath("//android.widget.TextView[@text='No session results']")).size() > 0) {
				driver.findElementByXPath("//android.widget.TextView[@text='This session was done with Sword Go.']");
				System.out.println("Sword Go session");
			} else {
				if (driver.findElements(By.xpath("//android.view.View[@content-desc='session_details_session_overview_card']/android.widget.TextView[2]")).size() > 0) {
					String overviewStars = driver.findElementByXPath("//android.view.View[@content-desc='session_details_session_overview_card']/android.widget.TextView[2]").getText();
					System.out.println("STARS ACHIEVED: " + overviewStars);
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
			}
			//validação exercícios
			driver.findElementByXPath("//android.widget.TextView[@text='Exercises']");
			String exerciseNumber1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[1]/android.widget.TextView[1]").getText();
			String exerciseName1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[1]/android.widget.TextView[2]").getText();
			String reps1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[1]/android.widget.TextView[3]").getText();
			System.out.println("Exercise nº: " + exerciseNumber1 + " - " + exerciseName1 + " " + reps1);
			String exerciseNumber2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[1]").getText();
			String exerciseName2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[2]").getText();
			String reps2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[3]").getText();
			System.out.println("Exercise nº: " + exerciseNumber2 + " - " + exerciseName2 + " " + reps2);
			visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_LATEST_SESSION_STAGING);
			//fazer scroll
			MobileElement exerciseCard2 = driver.findElementByAccessibilityId("session_details_exercise_2_card");
			MobileElement overviewTxt = driver.findElementByXPath("//android.widget.TextView[@text='Overview']");
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
			MobileElement exerciseCard1 = driver.findElementByAccessibilityId("session_details_exercise_0_card");
			MobileElement exerciseCard5 = driver.findElementByAccessibilityId("session_details_exercise_5_card");
			mobileActions.swipeByElements(exerciseCard1, exerciseCard5);
			if (driver.findElements(By.xpath("//android.widget.TextView[@text='Overview']")).size() > 0) {
				overviewTxt = driver.findElementByXPath("//android.widget.TextView[@text='Overview']");
				MobileElement exercisesTxt = driver.findElementByXPath("//android.widget.TextView[@text='Exercises']");
				mobileActions.swipeByElements(overviewTxt, exercisesTxt);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			//validar next session
			driver.findElementByXPath("//android.widget.TextView[@text='Session overview']");
			driver.findElementByXPath("//android.widget.TextView[@text='Goal:']");
			driver.findElementByXPath("//android.widget.TextView[@text='Time:']");
			driver.findElementByXPath("//android.widget.TextView[@text='39 minutes']");
			driver.findElementByXPath("//android.widget.TextView[@text='Exercises:']");
			driver.findElementByXPath("//android.widget.TextView[@text='22 exercises']");
			driver.findElementByXPath("//android.widget.TextView[@text=\"What you’ll need\"]");
			driver.findElementByXPath("//android.widget.TextView[@text=\"On the road? Can't use your tablet?\"]");
			driver.findElementByXPath("//android.widget.TextView[@text='Start your session now with Go']");
			visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_NEXT_SESSION_STAGING);
			byte[] nextSession = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
			//fazer scroll e validar alguns exercícios
			MobileElement youllNeedTxt = driver.findElementByXPath("//android.widget.TextView[@text='What you’ll need']");
			MobileElement sessionOverviewTxt = driver.findElementByXPath("//android.widget.TextView[@text='Session overview']");
			mobileActions.swipeByElements(youllNeedTxt, sessionOverviewTxt);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			driver.findElementByXPath("//android.widget.TextView[@text='Next session exercises']");
			driver.findElementByXPath("//android.widget.TextView[@text='Trunk forward bend']");
			//selecionar outro dia
			utilitiesAndroid.clickByAccessibilityId("session_details_carousel_date_card_1", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Exercises']")));
			//validar que está diferente - sessão 8 e next session - valores fixos para comparação do next session
			byte[] session8 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
			SimilarityMatchingResult result1 = driver
					.getImagesSimilarity(session8, nextSession, new SimilarityMatchingOptions()
							.withEnabledVisualization());
			String baselineFilename = VALIDATION_PATH + "/" + "next_session_and_session8" + ".png";
			File comparison1 = new File(baselineFilename);
			result1.storeVisualization(comparison1);
			assertThat(result1.getVisualization().length, is(greaterThan(0)));
			assertThat(result1.getScore(), is(lessThan(0.95)));
			System.out.println("Different sessions");
			//pull to refresh
			MobileElement overviewTxt2 = driver.findElementByXPath("//android.widget.TextView[@text='Overview']");
			MobileElement exerciseCard42 = driver.findElementByAccessibilityId("session_details_exercise_1_card");
			mobileActions.swipeByElements(overviewTxt2, exerciseCard42);
			//validar que abriu next session de novo
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Session overview']")));
			//voltar pra home
			utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@content-desc='home_card_session_details_1_date_label']")));
			//abrir outro dia clicando na setinha
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_1_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_2_prev_date_button']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_2_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_3_next_date_button']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_3_next_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_2']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_2", driver);
			//fazer scroll do carrossel
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Overview']")));
			byte[] session82 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
			MobileElement latestSession = driver.findElementByAccessibilityId("session_details_carousel_date_card_0");
			MobileElement toLeft = driver.findElementByAccessibilityId("session_details_carousel_date_card_2");
			mobileActions.swipeByElements(latestSession, toLeft);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='session_details_carousel_date_card_0']")));
			mobileActions.swipeByElements(toLeft, latestSession);
			//abrir outro dia - card mais a esquerda
			utilitiesAndroid.clickByAccessibilityId("session_details_carousel_date_card_3", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Overview']")));
			byte[] session6 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
			SimilarityMatchingResult result2 = driver
					.getImagesSimilarity(session82, session6, new SimilarityMatchingOptions()
							.withEnabledVisualization());
			baselineFilename = VALIDATION_PATH + "/" + BASELINE + "session6_session8.png";
			File comparison2 = new File(baselineFilename);
			result2.storeVisualization(comparison2);
			assertThat(result2.getVisualization().length, is(greaterThan(0)));
			assertThat(result2.getScore(), is(lessThan(0.95)));
			System.out.println("Different sessions");
			//voltar pra home
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_2']")));
			//mudar até a sessão mais antiga
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_2_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_3']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_3_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_4']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_4_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_5']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_5_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_6']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_6_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_7']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_7_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_8']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_8_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_9']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_9", driver);
			//validar que abriu a sessão certa - user l.spiegel+3@swordhealth.com - sessão 1
			driver.findElementByXPath("//android.widget.TextView[@text='26%']");
			driver.findElementByXPath("//android.widget.TextView[@text='Leg raise with knee bend']");
			driver.findElementByXPath("//android.widget.TextView[@text='98%']");
			driver.findElementByXPath("//android.widget.TextView[@text='Leg to the side']");
			driver.findElementByXPath("//android.widget.TextView[@text='20%']");
			driver.findElementByXPath("//android.widget.TextView[@text='Leg backwards']");
			driver.findElementByXPath("//android.widget.TextView[@text='25%']");
			visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_SESSION_1_STAGING);
			byte[] session1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
			SimilarityMatchingResult result3 = driver
					.getImagesSimilarity(session6, session1, new SimilarityMatchingOptions()
							.withEnabledVisualization());
			baselineFilename = VALIDATION_PATH + "/" + BASELINE + "session1_session6.png";
			File comparison3 = new File(baselineFilename);
			result3.storeVisualization(comparison3);
			assertThat(result3.getVisualization().length, is(greaterThan(0)));
			assertThat(result3.getScore(), is(lessThan(0.95)));
			System.out.println("Different sessions");
			//compartilhar sessão no chat
			driver.findElementByAccessibilityId("session_details_session_overview_card_share_button").click();
			driver.findElementByXPath("//android.widget.TextView[@text='Would you like to share feedback from this session in chat?']");
			visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_POPUP_SESSION_STAGING);
			utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
			//voltar
			utilitiesAndroid.clickByAccessibilityId("bottom_navigation_home_tab", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_delivery_kit_status']")));
			MobileElement kitDeliveryCard2 = driver.findElementByAccessibilityId("home_card_delivery_kit_status");
			MobileElement ptCard2 = driver.findElementByAccessibilityId("home_card_pt");
			mobileActions.swipeByElements(kitDeliveryCard2, ptCard2);
			MobileElement weeklyGoalTxt2 = driver.findElementByAccessibilityId("home_card_weekly_goal");
			mobileActions.swipeByElements(weeklyGoalTxt2, kitDeliveryCard2);
			if (driver.findElements(By.xpath("//android.widget.TextView[@text='Next Session']")).size() > 0) {
				utilitiesAndroid.clickByAccessibilityId("home_card_session_details_0_prev_date_button", driver);
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_1']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_1", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Overview']")));
			//partilhar um exercício no chat
			utilitiesAndroid.clickByAccessibilityId("session_details_exercise_0_card_see_more", driver);
			visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_BOTTOM_SHEET_STAGING);
			utilitiesAndroid.clickByAccessibilityId("session_details_exercise_0_card_share", driver);
			driver.findElementByXPath("//android.widget.TextView[@text='Would you like to share feedback from this exercise in chat?']");
			visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_POPUP_EXERCISE_STAGING);
			utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		} else {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Marta Casaca']")));
			//mostrar o card do lastest sessions
			MobileElement sessionsTxt = driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");
			MobileElement weeklyGoalTxt = driver.findElementByXPath("//android.widget.TextView[@text='Weekly goal']");
			mobileActions.swipeByElements(sessionsTxt, weeklyGoalTxt);
			//clicar na sessão mais recente
			if (driver.findElements(By.xpath("//android.widget.TextView[@text='Next Session']")).size() > 0) {
//				visualCheck.doVisualCheck(CHECK_HOME_NEXT_SESSION);
				utilitiesAndroid.clickByAccessibilityId("home_card_session_details_0_prev_date_button", driver);
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_1']")));
			//visual check do card da sessão mais recente
//			visualCheck.doVisualCheck(CHECK_HOME_SESSION_CARD_1);
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_1", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Exercises']")));
			//validar o ecrã
			driver.findElementByXPath("//android.widget.TextView[@text='For exercise details or to discuss it with your Physical Therapist, tap the three dots on each card.']");
			visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_WARNING);
			//fechar o warning antes de fazer a validação
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.widget.ImageView", driver);
			//validação overview
			driver.findElementByXPath("//android.widget.TextView[@text='Overview']");
			if (driver.findElements(By.xpath("//android.widget.TextView[@text=\"This session was done with Sword Go.\"]")).size() > 0) {
				driver.findElementByXPath("//android.widget.TextView[@text='No session results']");
				System.out.println("Sword Go session");
			} else {
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
			}
			//validação exercícios
			driver.findElementByXPath("//android.widget.TextView[@text='Exercises']");
			String exerciseNumber1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[1]").getText();
			String exerciseName1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[2]").getText();
			String reps1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[3]").getText();
			System.out.println("Exercise nº: " + exerciseNumber1 + " - " + exerciseName1 + " " + reps1);
			String exerciseNumber2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[1]").getText();
			String exerciseName2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[2]").getText();
			String reps2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[3]").getText();
			System.out.println("Exercise nº: " + exerciseNumber2 + " - " + exerciseName2 + " " + reps2);
			visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_LATEST_SESSION);
			//fazer scroll
			MobileElement exerciseCard2 = driver.findElementByAccessibilityId("session_details_exercise_2_card");
			MobileElement overviewTxt = driver.findElementByXPath("//android.widget.TextView[@text='Overview']");
			mobileActions.swipeByElements(exerciseCard2, overviewTxt);
			//validar o ecrã
			String exerciseNumberRandom1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[1]").getText();
			String exerciseNameRandom1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[2]").getText();
			String repsRandom1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[3]").getText();
			System.out.println("Exercise nº: " + exerciseNumberRandom1 + " - " + exerciseNameRandom1 + " " + repsRandom1);
			String exerciseNumberRandom2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[1]").getText();
			String exerciseNameRandom2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[2]").getText();
			String repsRandom2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[3]").getText();
			System.out.println("Exercise nº: " + exerciseNumberRandom2 + " - " + exerciseNameRandom2 + " " + repsRandom2);
			//pull to refresh
			MobileElement exerciseCard4 = driver.findElementByAccessibilityId("session_details_exercise_4_card");
			MobileElement exerciseCard9 = driver.findElementByAccessibilityId("session_details_exercise_9_card");
			mobileActions.swipeByElements(exerciseCard4, exerciseCard9);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			MobileElement overviewCard = driver.findElementByXPath("//android.widget.TextView[@text='Overview']"); //nas sessões go o overview tá sem id
			MobileElement exerciseCard3 = driver.findElementByAccessibilityId("session_details_exercise_2_card");
			mobileActions.swipeByElements(overviewCard, exerciseCard3);
			//validar next session
			driver.findElementByXPath("//android.widget.TextView[@text='Goal:']");
			driver.findElementByXPath("//android.widget.TextView[@text='Improve low back strength']");
			driver.findElementByXPath("//android.widget.TextView[@text='Time:']");
			driver.findElementByXPath("//android.widget.TextView[@text='31 minutes']");
			driver.findElementByXPath("//android.widget.TextView[@text='Exercises:']");
			driver.findElementByXPath("//android.widget.TextView[@text='26 exercises']");
			driver.findElementByXPath("//android.widget.TextView[@text=\"What you’ll need\"]");
			driver.findElementByXPath("//android.widget.TextView[@text=\"On the road? Can't use your tablet?\"]");
			driver.findElementByXPath("//android.widget.TextView[@text='Start your session now with Go']");
			visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_NEXT_SESSION);
			byte[] nextSession = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
			//fazer scroll e validar alguns exercícios
			MobileElement youllNeedTxt = driver.findElementByXPath("//android.widget.TextView[@text='What you’ll need']");
			MobileElement sessionOverviewTxt = driver.findElementByXPath("//android.widget.TextView[@text='Session overview']");
			mobileActions.swipeByElements(youllNeedTxt, sessionOverviewTxt);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			driver.findElementByXPath("//android.widget.TextView[@text='Next session exercises']");
			driver.findElementByXPath("//android.widget.TextView[@text='Trunk forward bend']");
			//selecionar outro dia - sessão 12
			utilitiesAndroid.clickByAccessibilityId("session_details_carousel_date_card_1", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Exercises']")));
			//validar que está diferente - valores fixos para comparação do next session
			String exerciseNameComp1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[2]").getText();
			String repsComp1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView[3]").getText();
			String exerciseNameComp2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[2]").getText();
			String repsComp2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView[3]").getText();
			Assert.assertNotEquals("Trunk forward bend", exerciseNameComp1);
			Assert.assertNotEquals("6 reps", repsComp1);
			Assert.assertNotEquals("Trunk side bend", exerciseNameComp2);
			Assert.assertNotEquals("12 reps", repsComp2);
			byte[] session12 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
			SimilarityMatchingResult result1 = driver
					.getImagesSimilarity(session12, nextSession, new SimilarityMatchingOptions()
							.withEnabledVisualization());
			String baselineFilename = VALIDATION_PATH + "/" + "next_session_and_session12" + ".png";
			File comparison1 = new File(baselineFilename);
			result1.storeVisualization(comparison1);
			assertThat(result1.getVisualization().length, is(greaterThan(0)));
			assertThat(result1.getScore(), is(lessThan(0.95)));
			System.out.println("Different sessions");
			//pull to refresh
			MobileElement overviewTxt2 = driver.findElementByXPath("//android.widget.TextView[@text='Overview']");
			MobileElement exerciseCard42 = driver.findElementByAccessibilityId("session_details_exercise_1_card");
			mobileActions.swipeByElements(overviewTxt2, exerciseCard42);
			//validar que abriu o next session
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Session overview']")));
			//voltar pra home
			utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@content-desc='home_card_session_details_1_date_label']")));
			//abrir outro dia clicando na setinha
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_1_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_2_prev_date_button']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_2_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_3_next_date_button']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_3_next_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_2']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_2", driver);
			//fazer scroll do carrossel
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Overview']")));
			byte[] session122 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
			MobileElement latestSession = driver.findElementByAccessibilityId("session_details_carousel_date_card_0");
			MobileElement toLeft = driver.findElementByAccessibilityId("session_details_carousel_date_card_2");
			mobileActions.swipeByElements(latestSession, toLeft);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='session_details_carousel_date_card_0']")));
			mobileActions.swipeByElements(toLeft, latestSession);
			//abrir outro dia - card mais a esquerda
			utilitiesAndroid.clickByAccessibilityId("session_details_carousel_date_card_3", driver);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Overview']")));
			byte[] session10 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
			SimilarityMatchingResult result2 = driver
					.getImagesSimilarity(session122, session10, new SimilarityMatchingOptions()
							.withEnabledVisualization());
			baselineFilename = VALIDATION_PATH + "/" + BASELINE + "session10_session12.png";
			File comparison2 = new File(baselineFilename);
			result2.storeVisualization(comparison2);
			assertThat(result2.getVisualization().length, is(greaterThan(0)));
			assertThat(result2.getScore(), is(lessThan(0.95)));
			System.out.println("Different sessions");
			//voltar pra home
			utilitiesAndroid.clickByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.y0/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_2']")));
			//mudar até a sessão mais antiga
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_2_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_3_prev_date_button']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_3_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_4_prev_date_button']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_4_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_5_prev_date_button']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_5_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_6_prev_date_button']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_6_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_7_prev_date_button']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_7_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_8_prev_date_button']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_8_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_9_prev_date_button']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_9_prev_date_button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_10']")));
			//abrir see more sessions
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_10", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Overview']")));
			//validar que abriu a sessão certa
			visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_SESSION_4);
			byte[] session4 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
			SimilarityMatchingResult result3 = driver
					.getImagesSimilarity(session10, session4, new SimilarityMatchingOptions()
							.withEnabledVisualization());
			baselineFilename = VALIDATION_PATH + "/" + BASELINE + "session10_session4.png";
			File comparison3 = new File(baselineFilename);
			result3.storeVisualization(comparison3);
			assertThat(result3.getVisualization().length, is(greaterThan(0)));
			assertThat(result3.getScore(), is(lessThan(0.95)));
			System.out.println("Different sessions");
			//compartilhar sessão no chat
			utilitiesAndroid.clickByAccessibilityId("session_details_session_overview_card_share_button", driver);
			driver.findElementByXPath("//android.widget.TextView[@text='Would you like to share feedback from this session in chat?']");
			visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_POPUP_SESSION);
			utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText")));
			//voltar
			utilitiesAndroid.clickByAccessibilityId("bottom_navigation_home_tab", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_weekly_goal']")));
			MobileElement sessionsTxt2 = driver.findElementByXPath("//android.widget.TextView[@text='Sessions']");
			MobileElement weeklyGoalTxt2 = driver.findElementByXPath("//android.widget.TextView[@text='Weekly goal']");
			mobileActions.swipeByElements(sessionsTxt2, weeklyGoalTxt2);
			if (driver.findElements(By.xpath("//android.widget.TextView[@text='Next Session']")).size() > 0) {
				utilitiesAndroid.clickByAccessibilityId("home_card_session_details_0_prev_date_button", driver);
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='home_card_session_details_1']")));
			utilitiesAndroid.clickByAccessibilityId("home_card_session_details_1", driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Overview']")));
			//partilhar um exercício no chat
			utilitiesAndroid.clickByAccessibilityId("session_details_exercise_0_card_see_more", driver);
			visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_BOTTOM_SHEET);
			utilitiesAndroid.clickByAccessibilityId("session_details_exercise_0_card_share", driver);
			driver.findElementByXPath("//android.widget.TextView[@text='Would you like to share feedback from this exercise in chat?']");
			visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_POPUP_EXERCISE);
			utilitiesAndroid.clickByXPath("//android.widget.Button", driver);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		ConfigurationsAndroid.killDriver();
	}
}