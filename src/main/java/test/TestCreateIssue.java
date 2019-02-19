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
import pageFactory.CreateIssueModal;
import pageFactory.Login;

public class TestCreateIssue {
    private static WebDriver driver;

    @BeforeAll
    static void initAll() {
        System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
//        driver.get("https://jira.codecool.codecanvas.hu");
    }

    @BeforeEach
    void beforeEach(){
        new Login(driver).loginWithDashboard();
    }

    @ParameterizedTest
    @CsvFileSource(resources="/createIssue.csv", numLinesToSkip = 1)
    void createIssue(String project, String issueType, String summary){
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.id("create_link"))).click();
        CreateIssueModal modal = new CreateIssueModal(driver);
        modal.setProject(project);
        modal.setIssueType(issueType);
        modal.setSummary(summary);
        String message = modal.submit();
        System.out.println(message);
        assertTrue(message.contains(project));
    }

    void deleteIssue(String url){

    }
}
