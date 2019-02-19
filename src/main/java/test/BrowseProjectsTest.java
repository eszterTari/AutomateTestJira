package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageFactory.BrowseProjects;
import pageFactory.Login;
import util.RunEnvironment;
import util.Utils;

import static org.junit.jupiter.api.Assertions.*;

public class BrowseProjectsTest {

    WebDriver driver;
    Login login;
    BrowseProjects browseProjects;

    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        driver.manage().window().maximize();
        login = new Login(driver);
        browseProjects = new BrowseProjects(driver);
        login.loginWithDashboard();
    }

    @Test
    public void viewAllProjects_test() {
        String expectedPageTitle = "Browse projects - Jira";
        browseProjects.viewAllProjects();
        assertEquals(expectedPageTitle, browseProjects.getPageTitle());
    }

    @Test
    public void detailedPageOfProjectOfRequirements_test() {
        assertEquals(true, browseProjects.detailedPageOfProjectOfRequirements());
    }

    @AfterEach
    public void tearDown() {
        Utils.tearDown();
    }

}