package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageFactory.Browse_Issues;
import pageFactory.CreateIssueModal;
import pageFactory.Login;
import util.RunEnvironment;
import util.Utils;

public class TestCreateIssue {
    private static WebDriver driver;
    private static Login login;

    @BeforeAll
    static void initAll() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        driver.manage().window().maximize();
        login = new Login(driver);

    }

    @BeforeEach
    void beforeEach() {
        login.login();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createIssue.csv", numLinesToSkip = 1)
    void createIssue(String project, String issueType, String summary) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("create_link"))).click();
        CreateIssueModal modal = new CreateIssueModal(driver);
        modal.setProject(project);
        modal.setIssueType(issueType);
        modal.setSummary(summary);
        String message = modal.submit();
        System.out.println(message);
        assertTrue(message.contains(project));
    }

    void deleteIssue(String url) {

    }
}
