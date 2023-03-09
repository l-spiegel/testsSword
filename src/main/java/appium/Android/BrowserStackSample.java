package appium.Android;
import java.net.URL;
import java.util.List;
import java.util.function.Function;
import java.net.MalformedURLException;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
public class BrowserStackSample {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
    	DesiredCapabilities caps = new DesiredCapabilities();
    	
    	// Set your access credentials
    	caps.setCapability("browserstack.user", "luizaspiegel_JUGV5Z");
    	caps.setCapability("browserstack.key", "rMjpFdLGPhBvamjjcwyS");
    	
    	// Set URL of the application under test
    	caps.setCapability("app", "bs://aa6db73f6033a90ad62aef52a02f9500b2f35c29");
    	
    	// Specify device and os_version for testing
    	caps.setCapability("device", "Google Pixel 7");
        caps.setCapability("os_version", "13.0");
        
    	// Set other BrowserStack capabilities
    	caps.setCapability("project", "First Java Project");
    	caps.setCapability("build", "browserstack-build-1");
    	caps.setCapability("name", "first_test");
       
    	
    	// Initialise the remote Webdriver using BrowserStack remote URL
    	// and desired capabilities defined above
        AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(
        		new URL("http://hub.browserstack.com/wd/hub"), caps);
        
        // Write your test case statements here
        MobileElement el1 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.widget.ScrollView/android.widget.EditText[1]");
		el1.clear();
		el1.sendKeys("f.silva@swordhealth.com");
		MobileElement el2 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.widget.ScrollView/android.widget.EditText[2]");
		el2.click();
		el2.sendKeys("Cabixuda12");
		MobileElement el7 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.widget.ScrollView/android.widget.EditText[2]/android.view.View/android.view.View/android.widget.Button");
		el7.click();
		MobileElement el8 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.Button");
		el8.click();
		WebDriverWait wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Create your PIN code']")));
		MobileElement el9 = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Not now']");
		el9.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My weekly goal']")));
		//fazer swipe pra mostrar o card das badges
		//Esse swipe é maior para um user com sessões feitas, um sem sessões feitas só precisa do primeiro swipe. Colocar um if pra saber se faz mais swipe??
		MobileElement remindersTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Set reminders']");
		MobileElement hello = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Hello,']");
		MobileActions mobileActions = new MobileActions(driver);
		mobileActions.swipeByElements(remindersTxt, hello);
		MobileElement starsAchiev = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Stars achieved per session']");
		MobileElement latestSess = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Latest sessions']");
		mobileActions.swipeByElements(starsAchiev, latestSess);
		//validar presença do card com if
		if (driver.findElements(By.xpath("//android.widget.TextView[@text='My badges']")).size() > 0) {
			//validar título do card
			String myBadges = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.TextView[4]").getText();
				Assert.assertEquals("My badges", myBadges);
			//validar o botão see all badges
			String seeAllBadges = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.view.View[2]/android.widget.TextView").getText();
				Assert.assertEquals("See all badges", seeAllBadges);
			//pegar os valores das 3 primeiras badges
			String lastAchieved = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.view.View[3]/android.widget.TextView").getText();
				System.out.println("LAST BADGE ACHIEVED: " + lastAchieved);
			String notAchieved1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.view.View[3]/android.view.View[1]/android.widget.TextView").getText();
				System.out.println("FIRST NOT ACHIEVED BADGE: " + notAchieved1);
			String notAchieved2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.widget.TextView").getText();
				System.out.println("SECOND NOT ACHIEVED BADGE: " + notAchieved2);
			//fazer swipe pra mostrar a última badge
			MobileElement secondNotAchievedBadge = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]");
			MobileElement achievedBadge = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.view.View[3]/android.widget.ImageView");
			mobileActions.swipeByElements(secondNotAchievedBadge, achievedBadge);
			//pegar o texto da última badge
			String notAchieved3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.widget.TextView").getText();
				System.out.println("THIRD NOT ACHIEVED BADGE: " + notAchieved3);
			//abrir o popup de uma das badges not achieved
			MobileElement el10 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.view.View[3]/android.view.View[1]");
			el10.click();
			//imprimir valores do badge
			String badgeDetailsTitle1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[1]").getText();
				System.out.println("POPUP BADGE TITLE: " + badgeDetailsTitle1);
			String badgeDetailsInfo1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]").getText(); 
				System.out.println("POPUP BADGE DESCRIPTION: " + badgeDetailsInfo1);
			//fechar popup pelo botão
			MobileElement el11 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button");
			el11.click();
			//abrir my badges
			MobileElement el12 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.view.View[2]");
			el12.click();
		} else {
			System.out.println("BADGES CARD NOT FOUND!");
			driver.quit();
		}
		//validar título
		String myBadges = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.view.View/android.widget.TextView").getText();
			Assert.assertEquals("My badges", myBadges);
		//validar título e badges do number of sessions
		//esses xpaths só são válidos pro user da Filipa, porque o xpath muda de uma badge atingida pra uma não atingida
		String numberOfSessions = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[1]").getText();
			Assert.assertEquals("Number of sessions", numberOfSessions);
		String session1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[3]").getText();
			Assert.assertEquals("1st Session", session1);
		String session2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[4]").getText();
			Assert.assertEquals("3rd Session", session2);
		String session3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[5]").getText();
			Assert.assertEquals("9th Session", session3);
		String session4 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.view.View[1]/android.widget.TextView").getText();
			Assert.assertEquals("18th Session", session4);
		//pegar o número de badges
		String numberOfSessionsBadgesAchieved = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[2]").getText();
			System.out.println("BADGES ACHIEVED: " + numberOfSessionsBadgesAchieved);
		//clicar na badge 18th session
		MobileElement el13 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.view.View[1]");
		el13.click();
		//validar details do popup
		String badgeDetailsTitle2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[1]").getText();
			Assert.assertEquals("18th Session", badgeDetailsTitle2);
		String badgeDetailsInfo2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]").getText();
			Assert.assertEquals("Complete your 18th exercise session", badgeDetailsInfo2);
		//clicar fora do popup
		mobileActions.tapByCoordinates(789, 1857);
		//Fazer scroll do number of weekly goals até o number of sessions
		MobileElement numberWeeklyGoalsTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='Number of weekly goals']");
		MobileElement firstSessionTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='1st Session']");
		mobileActions.swipeByElements(numberWeeklyGoalsTxt, firstSessionTxt);
		//validar título e nome das badges do number of weekly goals
		//esses xpaths só são válidos pro user da Filipa, porque o xpath muda de uma badge atingida pra uma não atingida
		String weeklyGoals = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[1]").getText();
			Assert.assertEquals("Number of weekly goals", weeklyGoals);
		String weeklyGoal1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[3]").getText();
			Assert.assertEquals("1st Weekly Goal", weeklyGoal1);
		String weeklyGoal2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView").getText();
			Assert.assertEquals("2nd Weekly Goal", weeklyGoal2);
		String weeklyGoal3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.view.View[3]/android.widget.TextView").getText();
			Assert.assertEquals("3rd Weekly Goal", weeklyGoal3);
		String weeklyGoal4 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.view.View[4]/android.widget.TextView").getText();
			Assert.assertEquals("4th Weekly Goal", weeklyGoal4);
		//pegar o número de badges
		String numberOfBadgesAchieved2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[2]").getText();
			System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved2);
		//clicar na badge 4th weekly goal
		MobileElement el14 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.view.View[4]");
		el14.click();
		//validar details do popup
		String badgeDetailsTitle3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[1]").getText();
			Assert.assertEquals("4th Weekly Goal", badgeDetailsTitle3);
		String badgeDetailsInfo3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.widget.TextView[2]").getText();
			Assert.assertEquals("Achieve your weekly goal for the fourth time ", badgeDetailsInfo3);
		//clicar no botão do popup
		MobileElement el15 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button");
		el15.click();
		//fazer scroll do app badges até o number of weekly goals
		MobileElement appBadgesTxt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='App badges']");
		MobileElement session18Txt = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='18th Session']");
		mobileActions.swipeByElements(appBadgesTxt, session18Txt);
		//validar título e nome das badges do app badges
		//esses xpaths só são válidos pro user da Filipa, porque o xpath muda de uma badge atingida pra uma não atingida
		String appBadges = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[1]").getText();
			Assert.assertEquals("App badges", appBadges);
		String remindersBadge = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[3]").getText();
			Assert.assertEquals("Reminders Scheduled", remindersBadge);
		String messageInChatBadge = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[4]").getText();
			Assert.assertEquals("1st Message In The Chat", messageInChatBadge);
		String videoWatchedBadge = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[5]").getText();
			Assert.assertEquals("Watched 1st Video", videoWatchedBadge);
		//pegar o número de badges
		String numberOfBadgesAchieved3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[2]").getText();
			System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved3);
		//validar o título e nome das badges do journey steps
		//esses xpaths só são válidos pro user da Filipa, porque o xpath muda de uma badge atingida pra uma não atingida
		String journeySteps = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[6]").getText();
			Assert.assertEquals("Journey steps", journeySteps);
		String enrollmentBadge = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[8]").getText();
			Assert.assertEquals("Enrollment", enrollmentBadge);
		String reassessmen1tBadge = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.view.View[2]/android.widget.TextView").getText();
			Assert.assertEquals("1st Reassessment", reassessmen1tBadge);
		String reassessment2Badge = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.view.View[3]/android.widget.TextView").getText();
			Assert.assertEquals("2nd Reassessment", reassessment2Badge);
		String treatmentCompletedBadge = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[9]").getText();
			Assert.assertEquals("Treatment Completed", treatmentCompletedBadge);
		//pegar o número de badges
		String numberOfBadgesAchieved4 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.TextView[7]").getText();
			System.out.println("BADGES ACHIEVED: " + numberOfBadgesAchieved4);
		//clicar na badge do enrollment
		MobileElement el16 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.widget.ScrollView/android.widget.ImageView[4]");
		el16.click();
		//voltar
		MobileElement el3 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.p0/android.view.View/android.view.View/android.view.View/android.widget.Button");
		el3.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='My badges']")));
        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();
		
	}
}