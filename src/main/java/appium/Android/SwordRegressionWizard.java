package appium.Android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class SwordRegressionWizard {

    private final static String CHECK_EXERCISE_HISTORY_1 = "exercise_history_1";

    private final static String VALIDATION_PATH = "/Users/luizaspiegel/Documents/image check/regression wizard/Android";
    private final static String BASELINE = "COMPARISION_";

    private AndroidDriver<MobileElement> driver;
    @Before
    public void startAppium() throws MalformedURLException {
        driver = ConfigurationsAndroid.getDriver();
    }

    @Test
    public void live() throws Exception {
        MobileActions mobileActions = new MobileActions(driver);
        WebDriverWait wait = new WebDriverWait(driver,50);
        UtilitiesAndroid utilitiesAndroid = new UtilitiesAndroid();
        VisualCheck visualCheckAndroid = new VisualCheck(driver);

        //fazer login
        utilitiesAndroid.login("l.spiegel+3@swordhealth.com", "Test1234!", driver);
        //validar your program
        //teste visual
        //fazer scroll
        //validar o restante do your program
        //teste visual
        //next
        //validar your personal goals - só é visível se tiver program goals
        //teste visual
        //next
        //validar your weekly goal
        //teste visual
        //scroll
        //validar o resto do your weekly goal
        //teste visual
        //clicar em set a reminder
        //criar um reminder com dias = weekly goal
        //salvar
        //voltar
        //see my program
        //validar popup dos reminders
        //teste visual
        //clicar em ok
        //pull to refresh pra mostrar o wizard de novo
        //fazer scroll no your program
        //clicar em skip
        //validar que abriu a home

        ConfigurationsAndroid.killDriver();
    }
}
