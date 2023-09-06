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
	private final static String CHECK_GUARDIANS_EMPTY_SCREEN = "guardians_empty_screen";
	private final static String CHECK_GUARDIANS_REQUIRED_FIELD_ERROR_SCREEN = "guardians_required_fields_error_screen";
	private final static String CHECK_GUARDIANS_INVALID_CHAR_ERROR_SCREEN = "guardians_invalid_char_error_screen";
	private final static String CHECK_GUARDIANS_FILLED_SCREEN = "guardians_filled_screen";
	private final static String CHECK_COVERAGE_EMPTY_SCREEN = "coverage_empty_screen";
	private final static String CHECK_COVERAGE_IM_COVERED_SCREEN = "coverage_covered_option";
	private final static String CHECK_COVERAGE_SELECT_OPTION_ERROR_SCREEN = "coverage_select_option_error";
	private final static String CHECK_COVERAGE_DEPENDENT_EMPTY_SCREEN = "coverage_dependent_empty_screen";
	private final static String CHECK_COVERAGE_DEPENDENT_REQUIRED_FIELDS_ERROR = "coverage_dependent_required_fields_error";
	private final static String CHECK_COVERAGE_RELATIONSHIP_BOTTOM_SHEET = "coverage_relationship_bottom_sheet";
	private final static String CHECK_COVERAGE_DEPENDENT_INVALID_CHAR_ERROR = "coverage_dependent_invalid_char_error";
	private final static String CHECK_COVERAGE_DEPENDENT_FILLED_SCREEN = "coverage_dependent_filled_screen";
	private final static String CHECK_FINISH_ACCOUNT_EMPTY_SCREEN = "finish_account_empty_screen";
	private final static String CHECK_FINISH_ACCOUNT_PHONE_BOTTOM_SHEET = "finish_account_phone_bottom_sheet";
	private final static String CHECK_FINISH_ACCOUNT_PASS_ERROR = "finish_account_pass_error";
	private final static String CHECK_FINISH_ACCOUNT_COUNTRIES_BOTTOM_SHEET = "finish_account_countries_bottom_sheet";
	private final static String CHECK_FINISH_ACCOUNT_FILLED_SCREEN = "finish_account_filled_screen";
	private final static String CHECK_FINISH_ACCOUNT_PHONE_ERROR = "finish_account_phone_error";

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
		String eligibilityYear = "2007";
		List<MobileElement> pw = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));
		// set third PickerWheel - year
		pw.get(2).sendKeys(eligibilityYear);
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
		assertThat(result.getScore(), is(greaterThan(0.90)));
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
		emailTxtField.sendKeys("seis3@setembro.com"); //no fim do teste o user será criado e terá que ser um novo email sempre que correr o teste até o final
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
		assertThat(result.getScore(), is(greaterThan(0.88)));
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
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Your guardian's information\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Guardian’s first name\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Guardian’s last name\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Guardian’s email\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@label=\"Why am I seeing this?\"]");
		VisualCheck.doVisualCheck(CHECK_GUARDIANS_EMPTY_SCREEN);
		byte[] guardiansEmpty = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//tap why am I seeing this?
		utilitiesiOS.clickByAccessibilityId("signup_guardian_seeing_this_button", driver);
		//validate bottom sheet
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Why am I seeing this?\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Members under 18 years old requires permission from the guardian to participate in Sword Program. Your guardian will need to sign a consent that will be received by email.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Ok\"]");
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Ok\"]", driver);
		//tap continue
		utilitiesiOS.clickByAccessibilityId("signup_continue_button", driver);
		//compare required field error screen with empty screen
		VisualCheck.doVisualCheck(CHECK_GUARDIANS_REQUIRED_FIELD_ERROR_SCREEN);
		byte[] guardiansRequiredError = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(guardiansRequiredError, guardiansEmpty, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.89)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "guardians_empty_required_error" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Guardians info page empty vs required field error - Similarity of: " + result.getScore());
		//enter invalid first and last name and email
		MobileElement guardiansFirstNameField = driver.findElementByAccessibilityId("signup_guardian_first_name_textfield");
		MobileElement guardiansLastNameField = driver.findElementByAccessibilityId("signup_guardian_last_name_textfield");
		MobileElement guardiansEmailField = driver.findElementByAccessibilityId("signup_guardian_email_textfield");
		guardiansFirstNameField.sendKeys("abc1");
		guardiansLastNameField.sendKeys("abc1");
		guardiansEmailField.sendKeys("abc1abc.com");
		mobileActions.tapByCoordinates(301, 480);
		VisualCheck.doVisualCheck(CHECK_GUARDIANS_INVALID_CHAR_ERROR_SCREEN);
		//campare invalid fields error screen with required field errors
		byte[] guardiansInvalidError = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(guardiansInvalidError, guardiansRequiredError, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.89)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "guardians_required_field_invalid_char_errors" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Guardians info page required field error vs invalid character error - Similarity of: " + result.getScore());
		//enter correct info
		guardiansFirstNameField.clear();
		guardiansFirstNameField.sendKeys("Abc");
		guardiansLastNameField.clear();
		guardiansLastNameField.sendKeys("Abc");
		guardiansEmailField.clear();
		guardiansEmailField.sendKeys("abd@abc.com");
		mobileActions.tapByCoordinates(301, 480);
		VisualCheck.doVisualCheck(CHECK_GUARDIANS_FILLED_SCREEN);
		//compare filled screen with invalid field errors
		byte[] guardiansFilled = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(guardiansFilled, guardiansInvalidError, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.87)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "guardians_invalid_error_filled" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Guardians info page invalid character error vs filled - Similarity of: " + result.getScore());
		//tap continue
		utilitiesiOS.clickByAccessibilityId("signup_continue_button", driver);
		//validate health coverage screen empty
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Your health coverage\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@label=\"My employer, health plan or provider has told me that I'm covered for Sword\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@label=\"I'm covered as a dependent of someone receiving Sword as a benefit\"]");
		VisualCheck.doVisualCheck(CHECK_COVERAGE_EMPTY_SCREEN);
		byte[] coverageEmpty = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//tap continue
		utilitiesiOS.clickByAccessibilityId("signup_continue_button", driver);
		//validate error screen
		VisualCheck.doVisualCheck(CHECK_COVERAGE_SELECT_OPTION_ERROR_SCREEN);
		byte[] coverageSelectOpetionError = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(coverageSelectOpetionError, coverageEmpty, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.95)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "coverage_empty_select_option_error" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Health coverage page empty vs select option error - Similarity of: " + result.getScore());
		//tap I'm covered option
		utilitiesiOS.clickByAccessibilityId("signup_coverage_describes_option_0", driver);
		VisualCheck.doVisualCheck(CHECK_COVERAGE_IM_COVERED_SCREEN);
		//compare covered with select option error
		byte[] coverageCoveredOption = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(coverageCoveredOption, coverageSelectOpetionError, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.93)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "coverage_select_option_error_covered" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Health coverage page select option error vs I'm covered - Similarity of: " + result.getScore());
		//tap I'm dependent option
		utilitiesiOS.clickByAccessibilityId("signup_coverage_describes_option_1", driver);
		//compare dependent top screen with covered
		byte[] coverageDependentOptionTop = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(coverageDependentOptionTop, coverageCoveredOption, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.87)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "coverage_covered_dependent" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Health coverage page I'm covered vs I'm dependent - Similarity of: " + result.getScore());
		//scroll
		MobileElement coverageLastName = driver.findElementByAccessibilityId("signup_coverage_last_name_textfield");
		MobileElement coverageTitle = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Your health coverage\"]");
		mobileActions.swipeByElements(coverageLastName, coverageTitle);
		//validate the extra fields
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Who is the person directly receiving Sword as a benefit?\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"First name\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Last name\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Date of birth\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Relationship\"]");
		VisualCheck.doVisualCheck(CHECK_COVERAGE_DEPENDENT_EMPTY_SCREEN);
		byte[] coverageDependentEmpty = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//tap continue
		utilitiesiOS.clickByAccessibilityId("signup_continue_button", driver);
		//validate error screen
		VisualCheck.doVisualCheck(CHECK_COVERAGE_DEPENDENT_REQUIRED_FIELDS_ERROR);
		//compare dependent required field error with dependent empty screen
		byte[] coverageDependentRequiredError = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(coverageDependentRequiredError, coverageDependentEmpty, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.86)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "coverage_dependent_empty_required_field_error" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Health coverage page I'm dependent empty vs required field error - Similarity of: " + result.getScore());
		//fill the fields but first and last name with invalid characters
		MobileElement coverageFirstNameField = driver.findElementByAccessibilityId("signup_coverage_first_name_textfield");
		MobileElement coverageLastNameField = driver.findElementByAccessibilityId("signup_coverage_last_name_textfield");
		coverageFirstNameField.sendKeys("aaaa1");
		coverageLastNameField.sendKeys("bbb1");
		//date picker
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther", driver);
		String coverageYear = "1990";
		List<MobileElement> pw2 = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));
		// set third PickerWheel - year
		pw2.get(2).sendKeys(coverageYear);
		utilitiesiOS.clickByAccessibilityId("Done", driver);
		//tap relationship
		utilitiesiOS.clickByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther[4]", driver);
		//validate bottom sheet
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Child\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Employee\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Life partner\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Other relationship\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Spouse\"]");
		VisualCheck.doVisualCheck(CHECK_COVERAGE_RELATIONSHIP_BOTTOM_SHEET);
		//tap child option
		utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name=\"Child\"]", driver);
		//validate invalid character errors
		driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"  Invalid character\"])[1]");
		driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"  Invalid character\"])[2]");
		VisualCheck.doVisualCheck(CHECK_COVERAGE_DEPENDENT_INVALID_CHAR_ERROR);
		//compare dependent invalid char error with dependent required fields error
		byte[] coverageDependentInvalidError = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(coverageDependentInvalidError, coverageDependentRequiredError, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.85)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "coverage_dependent_required_field_error_invalid_char_error" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Health coverage page I'm dependent required field error vs invalid char error - Similarity of: " + result.getScore());
		//fill correct info in the first and last name fields
		coverageFirstNameField.clear();
		coverageFirstNameField.sendKeys("aaa");
		coverageLastNameField.clear();
		coverageLastNameField.sendKeys("bbb");
		mobileActions.tapByCoordinates(332, 197);
		VisualCheck.doVisualCheck(CHECK_COVERAGE_DEPENDENT_FILLED_SCREEN);
		//compare dependent filled with invalid char error screen
		byte[] coverageDependentFilledScreen = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(coverageDependentFilledScreen, coverageDependentInvalidError, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.92)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "coverage_dependent_invalid_char_error_filled" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Health coverage page I'm dependent invalid char error vs filled screen - Similarity of: " + result.getScore());
		//tap continue
		utilitiesiOS.clickByAccessibilityId("signup_continue_button", driver);
		//validate finish account
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Finish your account setup\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Password\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Must be at least 8 characters\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Phone number\"]");
		driver.findElementByXPath("//XCUIElementTypeButton[@name=\"Why do we need your phone number?\"]");
		driver.findElementByXPath("(//XCUIElementTypeStaticText[@name=\"Create account\"])[2]");
		VisualCheck.doVisualCheck(CHECK_FINISH_ACCOUNT_EMPTY_SCREEN);
		byte[] finishAccountEmpty = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		//tap "why do we need you phone number?"
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"Why do we need your phone number?\"]", driver);
		//validate the bottom sheet
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Why do we need your phone number? \"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"This will allow us to send you important program info and updates. By providing your phone number, you give us permission to contact you via text message.\"]");
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Ok\"]");
		VisualCheck.doVisualCheck(CHECK_FINISH_ACCOUNT_PHONE_BOTTOM_SHEET);
		//tap outside the bottom sheet
		mobileActions.tapByCoordinates(133, 282);
		//enter an invalid password
		driver.findElementByXPath("//XCUIElementTypeSecureTextField").sendKeys("1234567");
		//tap outside the pass field
		mobileActions.tapByCoordinates(328, 217);
		//validate the pass error
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"  Minimum of characters is 8\"]");
		//tap the eye
		utilitiesiOS.clickByAccessibilityId("show_password_button", driver);
		//enter an invalid phone number
		MobileElement finishAccountPhoneField = driver.findElementByAccessibilityId("signup_phone_textfield");
		finishAccountPhoneField.sendKeys("0000000000");
		//tap to close the keyboard
		mobileActions.tapByCoordinates(336, 490);
		VisualCheck.doVisualCheck(CHECK_FINISH_ACCOUNT_PASS_ERROR);
		//compare the pass error with the empty screen
		byte[] finishAccountPassError = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(finishAccountPassError, finishAccountEmpty, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.80)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "finish_account_empty_pass_error" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Finish account page empty vs pass error - Similarity of: " + result.getScore());
		//enter a valid password
		driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeTextField").clear();
		driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"Sword Health\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeTextField").sendKeys("Test1234!");
		//tap the eye
		utilitiesiOS.clickByAccessibilityId("show_password_button", driver);
		//tap create account button
		utilitiesiOS.clickByAccessibilityId("signup_create_account_button", driver);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		//validate the phone error
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"  Invalid phone number\"]");
		VisualCheck.doVisualCheck(CHECK_FINISH_ACCOUNT_PHONE_ERROR);
		//compare the phone error with pass error
		byte[] finishAccountPhoneError = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(finishAccountPhoneError, finishAccountPassError, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.78)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "finish_account_pass_error_phone_error" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Finish account page pass error vs phone error - Similarity of: " + result.getScore());
		//tap the country
		utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name=\"+1\"]", driver);
		//validate the bottom sheet
		VisualCheck.doVisualCheck(CHECK_FINISH_ACCOUNT_COUNTRIES_BOTTOM_SHEET);
		//tap Portugal
		utilitiesiOS.clickByXPath("//XCUIElementTypeStaticText[@name=\"Portugal (+351)\"]", driver);
		//enter a valid phone number
		finishAccountPhoneField.clear();
		finishAccountPhoneField.sendKeys("999999999");
		//tap the eye icon again
		utilitiesiOS.clickByAccessibilityId("show_password_button", driver);
		//validate the filled screen
		VisualCheck.doVisualCheck(CHECK_FINISH_ACCOUNT_FILLED_SCREEN);
		//compare filled screen with phone error
		byte[] finishAccountFilled = Base64.encodeBase64(driver.getScreenshotAs(OutputType.BYTES));
		result = driver
				.getImagesSimilarity(finishAccountFilled, finishAccountPhoneError, new SimilarityMatchingOptions()
						.withEnabledVisualization());
		assertThat(result.getVisualization().length, is(greaterThan(0)));
		assertThat(result.getScore(), is(greaterThan(0.92)));
		baselineFilename = VALIDATION_PATH + "/" + BASELINE + "finish_account_phone_error_filled" + ".png";
		comparison = new File(baselineFilename);
		result.storeVisualization(comparison);
		System.out.println("Finish account page phone error vs filled - Similarity of: " + result.getScore());
		utilitiesiOS.clickByAccessibilityId("signup_create_account_button", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeStaticText[@name='Welcome to Sword']")));
		utilitiesiOS.clickByXPath("//XCUIElementTypeButton[@name=\"ic arrow left\"]", driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Explore our programs\"]");

		ConfigurationsiOS.killDriver();
	}

}