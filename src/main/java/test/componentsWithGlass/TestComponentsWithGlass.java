package test.componentsWithGlass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pageFactory.GlassDocument;
import pageFactory.Login;
import util.RunEnvironment;
import util.Utils;

import static org.junit.jupiter.api.Assertions.*;

public class TestComponentsWithGlass {
    private WebDriver driver;
    private Login login;

    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        login = new Login(driver);
    }

    @Test
    public void testGoToComponentsPage() {
        login.loginWithDashboard();

        GlassDocument glassDocument = new GlassDocument(driver);
        glassDocument.gotToComponentPageProjectSettings();



    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input.csv")
    void testHelloCsv(String a, String b) {
        System.out.println(a + " " + b);
    }

    @AfterEach
    public void tearDown() {
        //Utils.tearDown();
    }
}
