package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pageFactory.Login;
import util.RunEnvironment;
import util.Utils;

import static org.junit.jupiter.api.Assertions.*;

public class TestLogin {

    Login login;
    WebDriver driver;

    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        login = new Login(driver);
    }

    @Test
    //@CsvFileSource(resources = "/login/data-source-login.csv")
    void testHelloCsv() {
        login.loginWithDashboard();

    }

    @AfterEach
    public void tearDown() {
        Utils.tearDown();
    }
}
