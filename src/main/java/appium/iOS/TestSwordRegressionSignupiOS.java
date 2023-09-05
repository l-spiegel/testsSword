package appium.iOS;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class TestSwordRegressionSignupiOS {

	private final static String VALIDATION_PATH = ConfigurationsiOS.VALIDATION_PATH;
	private final static String BASELINE = "COMP_";
	private final static String SCROLL_TESTS = "scroll_tests";
	private final static String CHECK_ELIGIBILITY_EMPTY_1 = "eligibility_page_empty1";
	private final static String CHECK_ELIGIBILITY_EMPTY_2 = "eligibility_page_empty2";
	private final static String CHECK_ELIGIBILITY_ERROR_2 = "eligibility_page_error2";
	private final static String CHECK_STATE_BOTTOM_SHEET = "eligibility_state_bottom_sheet";
	private final static String CHECK_ELIGIBILITY_FILLED_2 = "eligibility_page_filled2";
	private final static String CHECK_ELIGIBILITY_ERROR_1 = "eligibility_page_error1";
	private final static String CHECK_ELIGIBILITY_INVALID_ERROR = "eligibility_page_invalid_error";
	private final static String CHECK_ELIGIBILITY_FILLED_1 = "eligibility_page_filled1";
	private final static String CHECK_ELIGIBILITY_MINORS_2 = "eligibility_page_minors";
	private final static String CHECK_INSURANCE_EMPTY_SCREEN = "insurance_empty_screen";
	private final static String CHECK_INSURANCE_INVALID_CHAR_ERROR_SCREEN = "insurance_invalid_char_error_screen";
	private final static String CHECK_INSURANCE_MAX_CHAR_ERROR_SCREEN = "insurance_maximum_char_error_screen";
	private final static String CHECK_INSURANCE_FILLED_SCREEN = "insurance_filled_screen";

	private IOSDriver<MobileElement> driver;
	@Before
	public void startAppium() throws MalformedURLException {
		driver = ConfigurationsiOS.getDriver();
	}

	@Test
	public void signupMagnolia() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileActionsiOS mobileActions = new MobileActionsiOS(driver);
		UtilitiesiOS utilitiesiOS = new UtilitiesiOS();
		new VisualCheck(driver);

		utilitiesiOS.clickByAccessibilityId("Allow", driver);
		//tap signup
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Welcome to Sword']")));
		utilitiesiOS.clickByAccessibilityId("loginSignupButton", driver);
		//select magnolia client
		utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name=\"Magnolia\"]", driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Create account']")));
		//tap to go back to clients list
		utilitiesiOS.clickByAccessibilityId("ic arrow left", driver);
		//open magnolia again
		utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name=\"Magnolia\"]", driver);
		//validate eligibility page
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Create account\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Good news! Magnolia Tree offers Sword as a benefit.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"First name\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Last name\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Email\"]");
		VisualCheck.doVisualCheck(CHECK_ELIGIBILITY_EMPTY_1);
		//screenshot to compare with error
		byte[] eligibilityEmpty1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//scroll - email field to image
		MobileElement emailField = driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeOther");
		MobileElement imageHeader = driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther");
		mobileActions.swipeByElements(emailField, imageHeader);
		//continue to validate
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Date of birth\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"State\"]");
		//scroll to show the end of the page
		MobileElement secondCheckbox = driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"check_box_container\"])[2]");
		mobileActions.swipeByElements(secondCheckbox, emailField);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		VisualCheck.doVisualCheck(CHECK_ELIGIBILITY_EMPTY_2);
		//screenshot to compare with error
		byte[] eligibilityEmpty2 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//tap continue button
		utilitiesiOS.clickByAccessibilityId("signup_continue_button", driver);
		//visual check
		VisualCheck.doVisualCheck(CHECK_ELIGIBILITY_ERROR_2);
		//compare to eligibilityEmpty2
		byte[] eligibilityError2 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		SimilarityMatchingResult result = driver
				.getImagesSimilarity(eligibilityError2, eligibilityEmpty2, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.93)));
		String baselineFilename = VALIDATION_PATH + "/" + BASELINE + "eligibility2_empty_error" + ".png";
		File comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Eligibility screen 2 empty vs error - Similarity of: " + result.getScore());
		//tap checkboxes
		utilitiesiOS.clickByAccessibilityId("signup_checkbox_3", driver);
		utilitiesiOS.clickByAccessibilityId("signup_checkbox_2", driver);
		utilitiesiOS.clickByAccessibilityId("signup_checkbox_1", driver);
		utilitiesiOS.clickByAccessibilityId("signup_checkbox_0", driver);
		//tap state field
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[6]/XCUIElementTypeOther", driver);
		//validate bottom sheet
		VisualCheck.doVisualCheck(CHECK_STATE_BOTTOM_SHEET);
		//tap california
		utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name=\"California\"]", driver);
		VisualCheck.doVisualCheck(CHECK_ELIGIBILITY_FILLED_2);
		//compare eligibilityError2 with filled
		byte[] eligibilityFilled2 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(eligibilityFilled2, eligibilityError2, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.93)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "eligibility2_error_filled" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Eligibility screen 2 error vs filled - Similarity of: " + result.getScore());
		//scroll state field to last checkbox
		MobileElement stateField = driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[6]");
		MobileElement lastCheckbox = driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"check_box_container\"])[4]");
		mobileActions.swipeByElements(stateField, lastCheckbox);
		//fill date of birth as minor
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[5]/XCUIElementTypeOther", driver);
		String year = "2007";
		List<MobileElement> pw = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));
		// set third PickerWheel - year
		pw.get(2).sendKeys(year);
		utilitiesiOS.clickByAccessibilityId("Done", driver);
		//scroll first name field to state
		MobileElement firstNameField = driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]");
		mobileActions.swipeByElements(firstNameField, stateField);
		VisualCheck.doVisualCheck(CHECK_ELIGIBILITY_ERROR_1);
		//compare eligibilityEmpty1 with error
		byte[] eligibilityError1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(eligibilityError1, eligibilityEmpty1, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.89)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "eligibility1_empty_error" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Eligibility screen 1 empty vs error - Similarity of: " + result.getScore());
		//fill first name, last name and email fields with invalid characters
		MobileElement firstNameTxtField = driver.findElementByAccessibilityId("signup_first_name_textfield");
		firstNameTxtField.sendKeys("hjsdk9");
		MobileElement lastNameTxtField = driver.findElementByAccessibilityId("signup_last_name_textfield");
		lastNameTxtField.sendKeys("hjsdk9");
		MobileElement emailTxtField = driver.findElementByAccessibilityId("signup_email_textfield");
		emailTxtField.sendKeys("hjsdk9@jdhidcom");
		mobileActions.tapByCoordinates(299, 147);
		//scroll last name field to state field
		MobileElement lastNameField = driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[3]");
		mobileActions.swipeByElements(lastNameField, stateField);
		VisualCheck.doVisualCheck(CHECK_ELIGIBILITY_INVALID_ERROR);
		//compare invalid character error screen with required field error
		byte[] eligibilityInvalidError1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(eligibilityInvalidError1, eligibilityError1, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.92)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "eligibility1_required_field_error_invalid_errors" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Eligibility screen 1 required field error vs invalid errors - Similarity of: " + result.getScore());
		//fill correctly the fields
		firstNameTxtField.clear();
		firstNameTxtField.sendKeys("aeiou");
		lastNameTxtField.clear();
		lastNameTxtField.sendKeys("aeiou");
		emailTxtField.clear();
		emailTxtField.sendKeys("aeiou@aeiou.com");
		mobileActions.tapByCoordinates(299, 147);
		//scroll last name field to state field
		mobileActions.swipeByElements(lastNameField, stateField);
		VisualCheck.doVisualCheck(CHECK_ELIGIBILITY_FILLED_1);
		//visual validation with invalid error
		byte[] eligibilityFilled1 = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(eligibilityFilled1, eligibilityInvalidError1, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.87)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "eligibility1_invalid_error_filled" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Eligibility screen 1 invalid error vs filled - Similarity of: " + result.getScore());
		//scroll down
		mobileActions.swipeByElements(emailField, imageHeader);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		MobileElement secondCheckbox2 = driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"check_box_container\"])[2]");
		mobileActions.swipeByElements(secondCheckbox2, emailField);
		//chompare minors consent with filled checkboxes
		VisualCheck.doVisualCheck(CHECK_ELIGIBILITY_MINORS_2);
		byte[] eligibilityMinors = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(eligibilityMinors, eligibilityFilled2, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.53)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "eligibility2_filled_minors" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Eligibility screen 2 filled vs minors - Similarity of: " + result.getScore());
		//tap minors checkbox
		utilitiesiOS.clickByAccessibilityId("signup_checkbox_4", driver);
		//tap continue
		utilitiesiOS.clickByAccessibilityId("signup_continue_button", driver);
		//validate insurance information screen empty
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Insurance information\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Member ID\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"This number is on your insurance card (e.g. 123456)\"]");
		VisualCheck.doVisualCheck(CHECK_INSURANCE_EMPTY_SCREEN);
		byte[] insuranceEmpty = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		MobileElement memberIdField = driver.findElementByAccessibilityId("signup_insurance_member_id_textfield");
		//validate invalid characters error
		memberIdField.sendKeys("aaa");
		mobileActions.tapByCoordinates(340, 311);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"  Invalid character\"]");
		VisualCheck.doVisualCheck(CHECK_INSURANCE_INVALID_CHAR_ERROR_SCREEN);
		//compare invalid character with empty screen
		byte[] insuranceInvalidCharError = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(insuranceInvalidCharError, insuranceEmpty, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.92)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "insurance_empty_invalid_char_error" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Insurance screen empty vs invalid char error - Similarity of: " + result.getScore());
		//validate maximum characters error
		memberIdField.clear();
		memberIdField.sendKeys("123456789012345678901");
		mobileActions.tapByCoordinates(340, 311);
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"  Maximum character limit is 20\"]");
		VisualCheck.doVisualCheck(CHECK_INSURANCE_MAX_CHAR_ERROR_SCREEN);
		//compare maximum characters error with invalid character error
		byte[] insuranceMaxCharError = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(insuranceMaxCharError, insuranceInvalidCharError, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.95)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "insurance_invalid_char_error_max_char_error" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Insurance invalid char error vs maximum char error - Similarity of: " + result.getScore());
		//tap continue
		utilitiesiOS.clickByAccessibilityId("signup_continue_button", driver);
		//fill correctly
		memberIdField.clear();
		memberIdField.sendKeys("12345678901234567890");
		mobileActions.tapByCoordinates(340, 311);
		VisualCheck.doVisualCheck(CHECK_INSURANCE_FILLED_SCREEN);
		//compare filled with maximum characters error
		byte[] insuranceFilled = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(insuranceFilled, insuranceMaxCharError, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.94)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "insurance_max_char_error_filled" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Insurance maximum char error vs filled - Similarity of: " + result.getScore());
		//tap continue
		utilitiesiOS.clickByAccessibilityId("signup_continue_button", driver);
		//validate guardians screen

		//tap why am I seeing this?

		//validate bottom sheet

		//tap continue

		//compare required field error screen with empty screen

		//enter invalid first and last name and email

		//campare invalid fields error screen with required field errors

		//enter correct info

		//compare filled screen with invalid field errors

		//VisualCheck.doVisualCheck(SCROLL_TESTS);

		//ConfigurationsiOS.killDriver();
	}

}