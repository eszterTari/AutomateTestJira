package test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import pageFactory.CreateIssue;
import pageFactory.Login;
import util.RunEnvironment;
import util.Utils;

public class TestCreateIssue {

    Login login;
    CreateIssue createIssue;
    WebDriver driver;

    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        login = new Login(driver);
        createIssue = new CreateIssue(driver);
        driver.manage().window().maximize();

    }

    @DisplayName("Test create issue with exist project")
    @Test
    void testcreateAnIssueByButton() {
        login.login();
        createIssue.createAnIssueByButton("Toucan", "Task");
//        Assertions.assertTrue(login.wrongLogin(), "Test login with wrong inputs");
//        Assertions.assertFalse(login.isLoggedIn(), "Confirm you are not logged in");
    }


//    @AfterEach
//    public void tearDown() {
//        Utils.tearDown();
//    }
}
