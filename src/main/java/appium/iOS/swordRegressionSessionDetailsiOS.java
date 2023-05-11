package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import io.appium.java_client.ios.IOSDriver;
import java.io.File;
import java.net.MalformedURLException;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class swordRegressionSessionDetailsiOS {

	private final static String VALIDATION_PATH = "/Users/luizaspiegel/Documents/image check/regression session details/iOS";
	private final static String BASELINE = "COMP_";

	private IOSDriver<MobileElement> driver;

	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
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
	public void virtualPtProduction() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		VisualCheck visualCheck = new VisualCheck(driver);

		utilitiesiOS.login("f.silva@swordhealth.com", "Cabixuda12", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		//mostrar o card do lastest sessions
		MobileElement remindersCard = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']"); //corrigir depois
		MobileElement ptCard = driver.findElementByAccessibilityId("home_card_pt");
		mobileActions.swipeByElements(remindersCard, ptCard);
		//clicar na sessão mais recente
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@value='Next Session']")).size() > 0) {
			visualCheck.doVisualCheck(CHECK_HOME_NEXT_SESSION);
			utilitiesiOS.clickByAccessibilityId("home_card_session_details_0_prev_date_button", driver);
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_1_see_more_button")));
		visualCheck.doVisualCheck(CHECK_HOME_SESSION_CARD_1);
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_1", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Exercises']")));
		//validar o ecrã
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='For exercise details or to discuss it with your Physical Therapist, tap the three dots on each card.']");
		visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_WARNING);
		//fechar o warning antes de fazer a validação
		utilitiesiOS.clickByAccessibilityId("Close", driver);
		//validação overview
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Overview']");
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name=\"This session was done with Sword Go.\"]")).size() > 0) {
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"No session results\"]");
			System.out.println("Sword Go session");
		} else {
			if (driver.findElements(By.id("session_details_session_overview_card_stars_view")).size() > 0) {
				System.out.println("STARS CARD ON OVERVIEW SHOWN");
			} else {
				System.out.println("STARS CARD ON OVERVIEW NOT SHOWN!");
			}
			if (driver.findElements(By.id("session_details_session_overview_card_pain_view")).size() > 0) {
				System.out.println("PAIN CARD ON OVERVIEW SHOWN");
			} else {
				System.out.println("PAIN CARD ON OVERVIEW NOT SHOWN!");
			}
			if (driver.findElements(By.id("session_details_session_overview_card_fatigue_view")).size() > 0) {
				System.out.println("FATIGUE CARD ON OVERVIEW SHOWN");
			} else {
				System.out.println("FATIGUE CARD ON OVERVIEW NOT SHOWN!");
			}
		}
		//validação exercícios
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Exercises']");
		String exerciseNumber1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_number").getText();
		String exerciseName1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_name").getText();
		String reps1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_reps").getText();
		System.out.println("Exercise nº: " + exerciseNumber1 + " - " + exerciseName1 + " " + reps1);
		String exerciseNumber2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_number").getText();
		String exerciseName2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_name").getText();
		String reps2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_reps").getText();
		System.out.println("Exercise nº: " + exerciseNumber2 + " - " + exerciseName2 + " " + reps2);
		visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_LATEST_SESSION);
		//fazer scroll
		MobileElement exerciseCard1 = driver.findElementByAccessibilityId("session_details_exercise_1_card");
		MobileElement overviewTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Overview']");
		mobileActions.swipeByElements(exerciseCard1, overviewTxt);
		//validar o ecrã
		String exerciseNumberRandom1 = driver.findElementByAccessibilityId("session_details_exercise_3_card_number").getText();
		String exerciseNameRandom1 = driver.findElementByAccessibilityId("session_details_exercise_3_card_name").getText();
		String repsRandom1 = driver.findElementByAccessibilityId("session_details_exercise_3_card_reps").getText();
		System.out.println("Exercise nº: " + exerciseNumberRandom1 + " - " + exerciseNameRandom1 + " " + repsRandom1);
		String exerciseNumberRandom2 = driver.findElementByAccessibilityId("session_details_exercise_4_card_number").getText();
		String exerciseNameRandom2 = driver.findElementByAccessibilityId("session_details_exercise_4_card_name").getText();
		String repsRandom2 = driver.findElementByAccessibilityId("session_details_exercise_4_card_reps").getText();
		System.out.println("Exercise nº: " + exerciseNumberRandom2 + " - " + exerciseNameRandom2 + " " + repsRandom2);
		//pull to refresh e abre next session
		MobileElement exerciseCard2 = driver.findElementByAccessibilityId("session_details_exercise_2_card");
		MobileElement exerciseCard5 = driver.findElementByAccessibilityId("session_details_exercise_5_card");
		mobileActions.swipeByElements(exerciseCard2, exerciseCard5);
		MobileElement overviewTxt2 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='No session results']");
		mobileActions.swipeByElements(overviewTxt2, exerciseCard2);
		//validar next session
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Session overview']")));
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Goal:']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Improve low back strength']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Time:']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='30 minutes']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Exercises:']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='26 exercises']");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"What you’ll need\"]");
		String swordGoTitle = driver.findElementByAccessibilityId("sword_go_card_title").getText();
		String swordGoSubtitle = driver.findElementByAccessibilityId("sword_go_card_subtitle").getText();
		Assert.assertEquals("On the road? Can't use your tablet?", swordGoTitle);
		Assert.assertEquals("Start your session now with Go", swordGoSubtitle);
		visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_NEXT_SESSION);
		byte[] nextSession = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//fazer scroll e validar alguns exercícios
		MobileElement youllNeedTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='What you’ll need']");
		MobileElement sessionOverviewTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Session overview']");
		mobileActions.swipeByElements(youllNeedTxt, sessionOverviewTxt);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Next session exercises']");
		String nextSessionEx1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_name").getText();
		Assert.assertEquals("Trunk forward bend", nextSessionEx1);
		//selecionar outro dia - sessão 12
		utilitiesiOS.clickByAccessibilityId("session_details_carousel_date_card_2", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@label='Exercises']")));
		//validar que está diferente - valores fixos para comparação do next session
		String exerciseNameComp1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_name").getText();
		String repsComp1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_reps").getText();
		String exerciseNameComp2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_name").getText();
		String repsComp2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_reps").getText();
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
		MobileElement overviewCard2 = driver.findElementByAccessibilityId("session_details_session_overview_card");
		MobileElement exerciseCard32 = driver.findElementByAccessibilityId("session_details_exercise_1_card");
		mobileActions.swipeByElements(overviewCard2, exerciseCard32);
		//validar que foi pro next session
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Session overview']")));
		//voltar pra home
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_1_date_label")));
		//abrir outro dia clicando na setinha
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_1_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_2_prev_date_button")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_2_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_3_next_date_button")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_3_next_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_2")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_2", driver);
		//fazer scroll do carrossel
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Overview']")));
		byte[] session122 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		MobileElement latestSession = driver.findElementByAccessibilityId("session_details_carousel_date_card_1");
		MobileElement toLeft = driver.findElementByAccessibilityId("session_details_carousel_date_card_3");
		mobileActions.swipeByElements(latestSession, toLeft);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Next Session']")));
		MobileElement carouselNextSession = driver.findElementByAccessibilityId("Next Session");
		MobileElement toRight = driver.findElementByAccessibilityId("session_details_carousel_date_card_2");
		mobileActions.swipeByElements(toRight, carouselNextSession);
		//abrir outro dia - card mais a esquerda
		utilitiesiOS.clickByAccessibilityId("session_details_carousel_date_card_4", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Overview']")));
		byte[] session10 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result2 = driver
				.getImagesSimilarity(session10, session122, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "session10_session12.png";
		File comparison2 = new File(baselineFilename);
		result2.storeVisualization(comparison2);
		assertThat(result2.getVisualization().length, is(greaterThan(0)));
		assertThat(result2.getScore(), is(lessThan(0.95)));
		System.out.println("Different sessions");
		//voltar pra home
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_2")));
		//mudar até a sessão mais antiga
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_2_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_3_prev_date_button")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_3_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_4_prev_date_button")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_4_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_5_prev_date_button")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_5_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_6_prev_date_button")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_6_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_7_prev_date_button")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_7_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_8_prev_date_button")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_8_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_9_prev_date_button")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_9_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_10")));
		//abrir see more sessions
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_10", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Overview']")));
		//validar que abriu a sessão certa
		visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_SESSION_4);
		byte[] session4 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result3 = driver
				.getImagesSimilarity(session4, session10, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "session10_session4.png";
		File comparison3 = new File(baselineFilename);
		result3.storeVisualization(comparison3);
		assertThat(result3.getVisualization().length, is(greaterThan(0)));
		assertThat(result3.getScore(), is(lessThan(0.95)));
		System.out.println("Different sessions");
		//compartilhar sessão no chat
		utilitiesiOS.clickByAccessibilityId("session_details_session_overview_card_share_button", driver);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Would you like to share feedback from this session in chat?\"]");
		visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_POPUP_SESSION);
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Yes']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
		//voltar
		utilitiesiOS.clickByAccessibilityId("bottom_navigation_home_tab", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Overview']")));
		//partilhar um exercício no chat
		utilitiesiOS.clickByAccessibilityId("session_details_exercise_0_card_see_more", driver);
		visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_BOTTOM_SHEET);
		utilitiesiOS.clickByAccessibilityId("session_details_exercise_0_card_share", driver);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Would you like to share feedback from this exercise in chat?\"]");
		visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_POPUP_EXERCISE);
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Yes']", driver);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Start typing...']")));

		ConfigurationsiOS.killDriver();
	}
	
	@Test
	public void virtualPtStaging() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		VisualCheck visualCheck = new VisualCheck(driver);

		utilitiesiOS.login("l.spiegel+3@swordhealth.com", "Test1234!", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Luiza Almeida']")));
		//mostrar o card do lastest sessions
		MobileElement kitDeliveryCard = (MobileElement) driver.findElementByAccessibilityId("home_card_delivery_kit_status");
		MobileElement ptCard = (MobileElement) driver.findElementByAccessibilityId("home_card_pt");
		mobileActions.swipeByElements(kitDeliveryCard, ptCard);
		MobileElement weeklyGoalTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Weekly goal']");
		mobileActions.swipeByElements(weeklyGoalTxt, kitDeliveryCard);
		//clicar na sessão mais recente
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@value='Next Session']")).size() > 0) {
			visualCheck.doVisualCheck(CHECK_HOME_NEXT_SESSION_STAGING);
			utilitiesiOS.clickByAccessibilityId("home_card_session_details_0_prev_date_button", driver);
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_1_see_more_button")));
		visualCheck.doVisualCheck(CHECK_HOME_SESSION_CARD_1_STAGING);
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_1", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Exercises']")));
		//validar o ecrã
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"For exercise details or to discuss it with your Physical Therapist, tap the three dots on each card.\"]");
		visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_WARNING_STAGING);
		//fechar o warning antes de fazer a validação
		utilitiesiOS.clickByAccessibilityId("Close", driver);
		//validação overview
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Overview\"]");
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name=\"No session results\"]")).size() > 0) {
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name='This session was done with Sword Go.']");
			System.out.println("Sword Go session");
		} else {
			if (driver.findElements(By.id("session_details_session_overview_card_stars_view")).size() > 0) {
				System.out.println("STARS CARD ON OVERVIEW SHOWN");
			} else {
				System.out.println("STARS CARD ON OVERVIEW NOT SHOWN!");
			}
			if (driver.findElements(By.id("session_details_session_overview_card_pain_view")).size() > 0) {
				System.out.println("PAIN CARD ON OVERVIEW SHOWN");
			} else {
				System.out.println("PAIN CARD ON OVERVIEW NOT SHOWN!");
			}
			if (driver.findElements(By.id("session_details_session_overview_card_fatigue_view")).size() > 0) {
				System.out.println("FATIGUE CARD ON OVERVIEW SHOWN");
			} else {
				System.out.println("FATIGUE CARD ON OVERVIEW NOT SHOWN!");
			}
		}
		//validação exercícios
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Exercises']");
		String exerciseNumber1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_number").getText();
		String exerciseName1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_name").getText();
		String reps1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_reps").getText();
		System.out.println("Exercise nº: " + exerciseNumber1 + " - " + exerciseName1 + " " + reps1);
		String exerciseNumber2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_number").getText();
		String exerciseName2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_name").getText();
		String reps2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_reps").getText();
		System.out.println("Exercise nº: " + exerciseNumber2 + " - " + exerciseName2 + " " + reps2);
		visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_LATEST_SESSION_STAGING);
		//fazer scroll
		MobileElement exerciseCard1 = driver.findElementByAccessibilityId("session_details_exercise_1_card");
		MobileElement overviewTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Overview']");
		mobileActions.swipeByElements(exerciseCard1, overviewTxt);
		//validar o ecrã
		String exerciseNumberRandom1 = driver.findElementByAccessibilityId("session_details_exercise_3_card_number").getText();
		String exerciseNameRandom1 = driver.findElementByAccessibilityId("session_details_exercise_3_card_name").getText();
		String repsRandom1 = driver.findElementByAccessibilityId("session_details_exercise_1_card_reps").getText();
		System.out.println("Exercise nº: " + exerciseNumberRandom1 + " - " + exerciseNameRandom1 + " " + repsRandom1);
		String exerciseNumberRandom2 = driver.findElementByAccessibilityId("session_details_exercise_4_card_number").getText();
		String exerciseNameRandom2 = driver.findElementByAccessibilityId("session_details_exercise_4_card_name").getText();
		String repsRandom2 = driver.findElementByAccessibilityId("session_details_exercise_4_card_reps").getText();
		System.out.println("Exercise nº: " + exerciseNumberRandom2 + " - " + exerciseNameRandom2 + " " + repsRandom2);
		//pull to refresh
		MobileElement exerciseCard5 = driver.findElementByAccessibilityId("session_details_exercise_5_card");
		MobileElement exerciseCard12 = driver.findElementByAccessibilityId("session_details_exercise_1_card");
		mobileActions.swipeByElements(exerciseCard12, exerciseCard5);
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name=\"Overview\"]")).size() > 0) {
			overviewTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Overview\"]");
			MobileElement exercisesTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Exercises\"]");
			mobileActions.swipeByElements(overviewTxt, exercisesTxt);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validar next session
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Session overview\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Goal:\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Time:\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"38 minutes\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Exercises:\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"22 exercises\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"What you’ll need\"]");
		String swordGoTitle = driver.findElementByAccessibilityId("sword_go_card_title").getText();
		String swordGoSubtitle = driver.findElementByAccessibilityId("sword_go_card_subtitle").getText();
		Assert.assertEquals("On the road? Can't use your tablet?", swordGoTitle);
		Assert.assertEquals("Start your session now with Go", swordGoSubtitle);
		visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_NEXT_SESSION_STAGING);
		byte[] nextSession = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//fazer scroll e validar alguns exercícios
		MobileElement youllNeedTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='What you’ll need']");
		MobileElement sessionOverviewTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Session overview']");
		mobileActions.swipeByElements(youllNeedTxt, sessionOverviewTxt);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Next session exercises\"]");
		String nextSessionEx1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_name").getText();
		Assert.assertEquals("Trunk forward bend", nextSessionEx1);
		//selecionar outro dia
		utilitiesiOS.clickByAccessibilityId("session_details_carousel_date_card_2", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@label='Exercises']")));
		//validar que está diferente - sessão 8 e next sessoion - valores fixos para comparação do next session
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
		overviewTxt = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Overview\"]");
		MobileElement exerciseCard0 = driver.findElementByAccessibilityId("session_details_exercise_0_card");
		mobileActions.swipeByElements(overviewTxt, exerciseCard0);
		//validar que foi pro next session
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Session overview']")));
		//voltar pra home
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_1_date_label")));
		//abrir outro dia clicando na setinha
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_1_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_2")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_2_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_3")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_3_next_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_2")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_2_see_more_button", driver);
		//fazer scroll do carrossel
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Overview']")));
		byte[] session82 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		MobileElement carouselSession9 = driver.findElementByAccessibilityId("session_details_carousel_date_card_1");
		MobileElement carouselSession7 = driver.findElementByAccessibilityId("session_details_carousel_date_card_3");
		mobileActions.swipeByElements(carouselSession9, carouselSession7);
		MobileElement carouselNextSession = driver.findElementByAccessibilityId("Next Session");
		MobileElement carouselSession8 = driver.findElementByAccessibilityId("session_details_carousel_date_card_2");
		mobileActions.swipeByElements(carouselSession8, carouselNextSession);
		//abrir outro dia - card mais a esquerda
		utilitiesiOS.clickByAccessibilityId("session_details_carousel_date_card_4", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name=\"Overview\"]")));
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
		driver.findElementByAccessibilityId("ic arrow left").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_2")));
		//mudar até a sessão mais antiga
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_2_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_3")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_3_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_4")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_4_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_5")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_5_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_6")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_6_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_7")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_7_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_8")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_8_prev_date_button", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_9")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_9_prev_date_button", driver);
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_9", driver);
		//validar que abriu sessão certa - user l.spiegel+3 - sessão 1
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Overview']")));
		String session1Exercise1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_name").getText();
		String session1Exercise2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_name").getText();
		String session1Exercise3 = driver.findElementByAccessibilityId("session_details_exercise_2_card_name").getText();
		Assert.assertEquals("Leg raise with knee bend", session1Exercise1);
		Assert.assertEquals("Leg to the side", session1Exercise2);
		Assert.assertEquals("Leg backwards", session1Exercise3);
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
		utilitiesiOS.clickByAccessibilityId("session_details_session_overview_card_share_button", driver);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Would you like to share feedback from this session in chat?\"]");
		visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_POPUP_SESSION_STAGING);
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Yes']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
		//voltar
		utilitiesiOS.clickByAccessibilityId("bottom_navigation_home_tab", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Overview']")));
		//partilhar um exercício no chat
		utilitiesiOS.clickByAccessibilityId("session_details_exercise_0_card_see_more", driver);
		visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_BOTTOM_SHEET_STAGING);
		utilitiesiOS.clickByAccessibilityId("session_details_exercise_0_card_share", driver);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Would you like to share feedback from this exercise in chat?\"]");
		visualCheck.doVisualCheck(CHECK_SESSION_DETAILS_POPUP_EXERCISE_STAGING);
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Yes']").click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Start typing...']")));
			
		driver.quit();
	}
	
}