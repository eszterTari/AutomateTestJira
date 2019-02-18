package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestCreateIssue {
    private static WebDriver driver;

    @BeforeAll
    static void initAll() {
        System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://jira.codecool.codecanvas.hu");
    }

    @ParameterizedTest
    @CsvFileSource(resources="/createIssue.csv")
    void createIssue(){

    }
}
