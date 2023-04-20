package appium.iOS;

import io.appium.java_client.MobileElement;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import io.appium.java_client.ios.IOSDriver;
import java.io.File;
import java.io.IOException;
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
	
	@Test
	public void virtualPtProduction() throws IOException {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();

		utilitiesiOS.login("f.silva@swordhealth.com", "Cabixuda12", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Weekly goal']")));
		//mostrar o card do lastest sessions
		MobileElement remindersCard = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Sessions']"); //corrigir depois
		MobileElement ptCard = driver.findElementByAccessibilityId("home_card_pt");
		mobileActions.swipeByElements(remindersCard, ptCard);
		//clicar na sessão mais recente
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@value='Next Session']")).size() > 0) {
			utilitiesiOS.clickByAccessibilityId("home_card_session_details_0_prev_date_button", driver);
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_1_see_more_button")));
		utilitiesiOS.clickByAccessibilityId("home_card_session_details_1", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Exercises']")));
		//validar o ecrã
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='For exercise details or to discuss it with your Physical Therapist, tap the three dots on each card.']");
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
		String score1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_score").getText();
		System.out.println("Exercise nº: " + exerciseNumber1 + " - " + exerciseName1 + " " + reps1);
		String exerciseNumber2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_number").getText();
		String exerciseName2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_name").getText();
		String reps2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_reps").getText();
		String score2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_score").getText();
		System.out.println("Exercise nº: " + exerciseNumber2 + " - " + exerciseName2 + " " + reps2);
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
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name='Session overview']")).size() > 0) {
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Goal:']");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Improve low back strength']");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Time:']");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name='30 minutes']");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Exercises:']");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name='26 exercises']");
			driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"What you’ll need\"]");
			String swordGoTitle = driver.findElementByAccessibilityId("sword_go_card_title").getText();
			String swordGoSubtitle = driver.findElementByAccessibilityId("sword_go_card_subtitle").getText();
			Assert.assertEquals("On the road? Can't use your Digital Therapist?", swordGoTitle);
			Assert.assertEquals("Start your session now with Go", swordGoSubtitle);
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
		} else {
		//validar se é a mesma sessão - isso só vai acontecer se não tiver next session
			String refreshExerciseName1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_name").getText();
			String refreshReps1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_reps").getText();
			String refreshScore1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_score").getText();
			Assert.assertEquals(exerciseName1, refreshExerciseName1);
			Assert.assertEquals(reps1, refreshReps1);
			Assert.assertEquals(score1, refreshScore1);
			String refreshExerciseName2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_name").getText();
			String refreshReps2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_reps").getText();
			String refreshScore2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_score").getText();
			Assert.assertEquals(exerciseName2, refreshExerciseName2);
			Assert.assertEquals(reps2, refreshReps2);
			Assert.assertEquals(score2, refreshScore2);
		}
		//validar abrir um set - o user da Filipa não tem sets feitos e nem prescritos -> copiar quando for fazer os de staging
	//	MobileElement el7 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_1_card"); //mudar quando tiver outro com set, esse só se aplica ao user de testes
	//	el7.click();
	//	String set1 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set 1']").getText();
	//		Assert.assertEquals("Set 1", set1);
		//selecionar outro dia
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
		//pull to refresh
		MobileElement overviewCard2 = driver.findElementByAccessibilityId("session_details_session_overview_card");
		MobileElement exerciseCard32 = driver.findElementByAccessibilityId("session_details_exercise_1_card");
		mobileActions.swipeByElements(overviewCard2, exerciseCard32);
		//validar que foi pro next session
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Session overview']")));
		//voltar pra definePinLoginChangePinHome
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
		byte[] session11 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		MobileElement latestSession = driver.findElementByAccessibilityId("session_details_carousel_date_card_1");
		MobileElement toLeft = driver.findElementByAccessibilityId("session_details_carousel_date_card_3");
		mobileActions.swipeByElements(latestSession, toLeft);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Next Session']")));
		MobileElement nextSession = driver.findElementByAccessibilityId("Next Session");
		MobileElement toRight = driver.findElementByAccessibilityId("session_details_carousel_date_card_2");
		mobileActions.swipeByElements(toRight, nextSession);
		//abrir outro dia - card mais a esquerda
		utilitiesiOS.clickByAccessibilityId("session_details_carousel_date_card_4", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Overview']")));
		byte[] session9 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result = driver
				.getImagesSimilarity(session9, session11, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		String baselineFilename = VALIDATION_PATH + "/" + BASELINE + ".png";
		File comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(lessThan(0.95)));
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
		byte[] session3 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result2 = driver
				.getImagesSimilarity(session3, session9, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		String baselineFilename2 = VALIDATION_PATH + "/" + BASELINE + "2.png";
		File comparison2 = new File(baselineFilename2);
		result2.storeVisualization(comparison2);
		assertThat(result2.getVisualization().length, is(greaterThan(0)));
		assertThat(result2.getScore(), is(lessThan(0.95)));
		System.out.println("Different sessions");
		//compartilhar sessão no chat
		utilitiesiOS.clickByAccessibilityId("session_details_session_overview_card_share_button", driver);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Would you like to share feedback from this session in chat?\"]");
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name='Yes']", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
		//voltar
		driver.findElementByAccessibilityId("bottom_navigation_home_tab").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Overview']")));
		//partilhar um exercício no chat
		utilitiesiOS.clickByAccessibilityId("session_details_exercise_0_card_see_more", driver);
		utilitiesiOS.clickByAccessibilityId("session_details_exercise_0_card_share", driver);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Would you like to share feedback from this exercise in chat?\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Yes']").click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Start typing...']")));

		ConfigurationsiOS.killDriver();
	}
	
	@Test
	public void virtualPtStaging() throws MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();

		driver.findElementByAccessibilityId("Allow").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Login']")));
	//	MobileElement el1 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeTextField");
	//	el1.clear();
	//	el1.sendKeys("l.spiegel+3@swordhealth.com");
		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("loginPasswordTextfield");
		el2.click();
		el2.sendKeys("Test1234!");
		MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("loginButton");
		el3.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create your PIN code']")));
		MobileElement el4 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Not now']");
		el4.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Luiza Almeida']")));
		//mostrar o card do lastest sessions
		MobileElement kitDeliveryCard = (MobileElement) driver.findElementByAccessibilityId("home_card_delivery_kit_status");
		MobileElement ptCard = (MobileElement) driver.findElementByAccessibilityId("home_card_pt");
		mobileActions.swipeByElements(kitDeliveryCard, ptCard);
		MobileElement weeklyGoalTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Weekly goal']");
		mobileActions.swipeByElements(weeklyGoalTxt, kitDeliveryCard);
		//clicar na sessão mais recente
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@value='Next Session']")).size() > 0) {
			driver.findElementByAccessibilityId("home_card_session_details_0_prev_date_button").click();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_1_see_more_button")));
		MobileElement el5 = (MobileElement) driver.findElementByAccessibilityId("home_card_session_details_1");
		el5.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Exercises']")));
		//validar o ecrã
		String chatWarning = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText").getText();
		Assert.assertEquals("For exercise details or to discuss it with your Physical Therapist, tap the three dots on each card.", chatWarning);
		//fechar o warning antes de fazer a validação
		MobileElement el6 = (MobileElement) driver.findElementByAccessibilityId("Close");
		el6.click();
		//validação overview
		String overview = driver.findElementByXPath("//XCUIElementTypeOther[@name='Overview']/XCUIElementTypeStaticText").getText();
		Assert.assertEquals("Overview", overview);
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
		//validação exercícios
		String exercises = driver.findElementByXPath("//XCUIElementTypeOther[@name='Exercises']/XCUIElementTypeStaticText").getText();
		Assert.assertEquals("Exercises", exercises);
		String exerciseNumber1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_number").getText();
		String exerciseName1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_name").getText();
		String reps1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_reps").getText();
		String score1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_score").getText();
			System.out.println("Exercise nº: " + exerciseNumber1 + " - " + exerciseName1 + " " + reps1);
		String exerciseNumber2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_number").getText();
		String exerciseName2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_name").getText();
		String reps2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_reps").getText();
		String score2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_score").getText();
			System.out.println("Exercise nº: " + exerciseNumber2 + " - " + exerciseName2 + " " + reps2);
		//fazer scroll
		MobileElement exerciseCard2 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_2_card");
		MobileElement overviewTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Overview']");
		mobileActions.swipeByElements(exerciseCard2, overviewTxt);
		//validar o ecrã
		String exerciseNumberRandom1 = driver.findElementByAccessibilityId("session_details_exercise_3_card_number").getText();
		String exerciseNameRandom1 = driver.findElementByAccessibilityId("session_details_exercise_3_card_name").getText();
		String repsRandom1 = driver.findElementByAccessibilityId("session_details_exercise_1_card_reps").getText();
			System.out.println("Exercise nº: " + exerciseNumberRandom1 + " - " + exerciseNameRandom1 + " " + repsRandom1);
		String exerciseNumberRandom2 = driver.findElementByAccessibilityId("session_details_exercise_4_card_number").getText();
		String exerciseNameRandom2 = driver.findElementByAccessibilityId("session_details_exercise_4_card_name").getText();
		String repsRandom2 = driver.findElementByAccessibilityId("session_details_exercise_4_card_reps").getText();
			System.out.println("Exercise nº: " + exerciseNumberRandom2 + " - " + exerciseNameRandom2 + " " + repsRandom2);
		//pull to refresh e abre next session
		MobileElement exerciseCard4 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_3_card");
		MobileElement exerciseCard8 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_7_card");
		mobileActions.swipeByElements(exerciseCard4, exerciseCard8);
		MobileElement exercisesTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Exercises']");
		mobileActions.swipeByElements(exercisesTxt, exerciseCard4);
		//validar next session
	//	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Session overview']")));
		if (driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name='Session overview']")).size() > 0) {
			String overviewGoal1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]").getText();
			String overviewGoal2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]").getText();
			String overviewTime1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[3]").getText();
			String overviewTime2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[4]").getText();
			String overviewExercises1 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[5]").getText();
			String overviewExercises2 = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[6]").getText();
			String overviewTitle = driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[7]").getText();
			String youllNeed = driver.findElementByXPath("//XCUIElementTypeOther[@name='What you’ll need']/XCUIElementTypeStaticText").getText();
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
			MobileElement youllNeedTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='What you’ll need']");
			MobileElement sessionOverviewTxt = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Session overview']");
			mobileActions.swipeByElements(youllNeedTxt, sessionOverviewTxt);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}			
			String nextSessionExsTitle = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Next session exercises']").getText();
			String nextSessionEx1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_name").getText();
			Assert.assertEquals("Next session exercises", nextSessionExsTitle);
			Assert.assertEquals("Leg raise with knee bend", nextSessionEx1);
		} else {
		//validar se é a mesma sessão - isso só vai acontecer se não tiver next session
			String refreshExerciseName1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_name").getText();
			String refreshReps1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_reps").getText();
			String refreshScore1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_score").getText();
			Assert.assertEquals(exerciseName1, refreshExerciseName1);
			Assert.assertEquals(reps1, refreshReps1);
			Assert.assertEquals(score1, refreshScore1);
			String refreshExerciseName2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_name").getText();
			String refreshReps2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_reps").getText();
			String refreshScore2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_score").getText();
			Assert.assertEquals(exerciseName2, refreshExerciseName2);
			Assert.assertEquals(reps2, refreshReps2);
			Assert.assertEquals(score2, refreshScore2);
		}
		//validar abrir um set
	//	MobileElement el7 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_1_card"); //mudar quando tiver outro com set, esse só se aplica ao user de testes
	//	el7.click();
	//	String set1 = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Set 1']").getText();
	//		Assert.assertEquals("Set 1", set1);
		//selecionar outro dia
		MobileElement el8 = (MobileElement) driver.findElementByAccessibilityId("session_details_carousel_date_card_2");
		el8.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@label='Exercises']")));
		//validar que está diferente - valores fixos para comparação do next session
		String exerciseNameComp1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_name").getText();
		String repsComp1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_reps").getText();
		String exerciseNameComp2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_name").getText();
		String repsComp2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_reps").getText();
		Assert.assertEquals("Leg raise with knee bend", exerciseNameComp1);
		Assert.assertNotEquals("8 reps", repsComp1);
		Assert.assertEquals("Leg to the side", exerciseNameComp2);
		Assert.assertNotEquals("8 reps", repsComp2);
		//pull to refresh
		MobileElement overviewCard2 = (MobileElement) driver.findElementByAccessibilityId("session_details_session_overview_card");
		MobileElement exerciseCard32 = (MobileElement) driver.findElementByAccessibilityId("session_details_exercise_1_card");
		mobileActions.swipeByElements(overviewCard2, exerciseCard32);
		//validar que foi pro next session
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Session overview']")));
		//voltar pra definePinLoginChangePinHome
		MobileElement backButton = (MobileElement) driver.findElementByAccessibilityId("ic arrow left");
		backButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_1_date_label")));
		//abrir outro dia clicando na setinha
		driver.findElementByAccessibilityId("home_card_session_details_1_next_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_0_prev_date_button")));
		driver.findElementByAccessibilityId("home_card_session_details_0_prev_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_1_prev_date_button")));
		driver.findElementByAccessibilityId("home_card_session_details_1_prev_date_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_2_see_more_button")));
		driver.findElementByAccessibilityId("home_card_session_details_2_see_more_button").click();
		//fazer scroll do carrossel
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Overview']")));
		MobileElement nextSession = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name='Sword Health']/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther");
		MobileElement session1 = (MobileElement) driver.findElementByAccessibilityId("session_details_carousel_date_card_2");
		mobileActions.swipeByElements(nextSession, session1);
		mobileActions.swipeByElements(session1, nextSession);
		//abrir outro dia - card mais a esquerda
		driver.findElementByAccessibilityId("session_details_carousel_date_card_1").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Overview']")));
		//voltar pra definePinLoginChangePinHome
		driver.findElementByAccessibilityId("ic arrow left").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_2")));
		//mudar até a sessão mais antiga - l.spiegel+3 só tem 2 sessões
	//	driver.findElementByAccessibilityId("home_card_session_details_3_prev_date_button").click();
	//	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_4_prev_date_button")));
	//	driver.findElementByAccessibilityId("home_card_session_details_4_prev_date_button").click();
	//	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_5_prev_date_button")));
	//	driver.findElementByAccessibilityId("home_card_session_details_5_prev_date_button").click();
	//	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_6_prev_date_button")));
	//	driver.findElementByAccessibilityId("home_card_session_details_6_prev_date_button").click();
	//	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_7_prev_date_button")));
	//	driver.findElementByAccessibilityId("home_card_session_details_7_prev_date_button").click();
	//	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_8_prev_date_button")));
	//	driver.findElementByAccessibilityId("home_card_session_details_8_prev_date_button").click();
	//	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("home_card_session_details_9_prev_date_button")));
	//	driver.findElementByAccessibilityId("home_card_session_details_9_prev_date_button").click();
		//abrir see more sessions - l.spiegel+3 não tem
	//	driver.findElementByAccessibilityId("home_card_session_details_10").click();
		driver.findElementByAccessibilityId("home_card_session_details_2_see_more_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Overview']")));
		//validar que abriu a sessão certa
		String moreSessionsExercise1 = driver.findElementByAccessibilityId("session_details_exercise_0_card_name").getText();
		String moreSessionsExercise2 = driver.findElementByAccessibilityId("session_details_exercise_1_card_name").getText();
		String moreSessionsExercise3 = driver.findElementByAccessibilityId("session_details_exercise_2_card_name").getText();
		Assert.assertEquals("Leg raise with knee bend", moreSessionsExercise1);
		Assert.assertEquals("Leg to the side", moreSessionsExercise2);
		Assert.assertEquals("Leg backwards", moreSessionsExercise3);
		//compartilhar sessão no chat
		driver.findElementByAccessibilityId("session_details_session_overview_card_share_button").click();
		String popupSessionTxt = driver.findElementByAccessibilityId("Would you like to share feedback from this session in chat?").getText();
		Assert.assertEquals("Would you like to share feedback from this session in chat?", popupSessionTxt);
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Yes']").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextView")));
		//voltar
		driver.findElementByAccessibilityId("bottom_navigation_home_tab").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Overview']")));
		//partilhar um exercício no chat
		driver.findElementByAccessibilityId("session_details_exercise_0_card_see_more").click();
		driver.findElementByAccessibilityId("session_details_exercise_0_card_share").click();
		String popupExerciseTxt = driver.findElementByAccessibilityId("Would you like to share feedback from this exercise in chat?").getText();
		Assert.assertEquals("Would you like to share feedback from this exercise in chat?", popupExerciseTxt);
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