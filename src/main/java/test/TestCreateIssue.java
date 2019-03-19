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
    public void testCreateAnIssueByButton() {
        login.login();
        createIssue.createAnIssueByButton("Toucan", "Task", "KRK");
        Assertions.assertTrue(createIssue.isIssueCreateSuccessfully(), "Confirm issue is successfully created.");
    }

    @DisplayName("Test create all combination: Available Projects: TOUCAN, JETI, COALA" +
                 "Expected Issue Types: Story, Task, Bug, Sub-task")
    @Test
    public void testCreateIssueAllCombine() {
        login.login();

    }


    @AfterEach
    public void tearDown() {
        Utils.tearDown();
    }
}
