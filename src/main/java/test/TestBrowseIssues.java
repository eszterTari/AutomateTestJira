package test.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pageFactory.Browse_Issues;
import pageFactory.Login;
import util.RunEnvironment;
import util.Utils;


public class TestBrowseIssues {

    Login login;
    Browse_Issues browseIssues;
    WebDriver driver;

    @BeforeEach
    public void setup(){
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        driver.manage().window().maximize();
        login = new Login(driver);
        browseIssues = new Browse_Issues(driver);
        login.login();
    }

    //@Disabled
    @DisplayName("Navigate to Browse Issues")
    @Test
    public void navigateToBrowseIssues() {
        browseIssues.navigateToBrowseIssues();
        Assertions.assertTrue(browseIssues.isIssuesSearchBtnVisible(), "Issues page is not visible");
    }

    @DisplayName("Validate that Toucan project is available")
    @Test
    public void checkToucanProjectAvailability() {
        Assertions.assertTrue(browseIssues.openIssuesOfTestProject("Toucan").isDisplayed(), "No such project available");
    }

    @DisplayName("Validate that Toucan project has 3 issues at least")
    @Test
    public void checkToucanProjectIssues(){
        browseIssues.openIssuesOfTestProject("Toucan");
        System.out.println("Number of issues for project: " + browseIssues.getNumbersOfIssues());
        Assertions.assertTrue(browseIssues.isMinimumNumberOfIssuesArePresent(3), "Minimum issue number not reached");
    }

    @DisplayName("Validate that Coala project is available")
    @Test
    public void checkCoalaProjectAvailability() {
        Assertions.assertTrue(browseIssues.openIssuesOfTestProject("Coala").isDisplayed(), "No such project available");
    }

    @DisplayName("Validate that Coala project has 3 issues at least")
    @Test
    public void checkCoalaProjectIssues(){
        browseIssues.openIssuesOfTestProject("Coala");
        System.out.println("Number of issues for project: " + browseIssues.getNumbersOfIssues());
        Assertions.assertTrue(browseIssues.isMinimumNumberOfIssuesArePresent(3), "Minimum issue number not reached");
    }

    @DisplayName("Validate that Jeti project is available")
    @Test
    public void checkJetiProjectAvailability() {
        Assertions.assertTrue(browseIssues.openIssuesOfTestProject("Jeti").isDisplayed(), "No such project available");
    }

    @DisplayName("Validate that Jeti project has 3 issues at least")
    @Test
    public void checkJetiProjectIssues(){
        browseIssues.openIssuesOfTestProject("Jeti");
        System.out.println("Number of issues for project: " + browseIssues.getNumbersOfIssues());
        Assertions.assertTrue(browseIssues.isMinimumNumberOfIssuesArePresent(3), "Minimum issue number not reached");
    }





    @AfterEach
    public void tearDown(){
        Utils.tearDown();
    }
}
