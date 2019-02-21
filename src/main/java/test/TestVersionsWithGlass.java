package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageFactory.Login;
import pageFactory.VersionsWithGlassPage;
import util.RunEnvironment;
import util.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestVersionsWithGlass {

    WebDriver driver;
    Login login;
    VersionsWithGlassPage versionsWithGlassPage;


    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        driver.manage().window().maximize();
        login = new Login(driver);
        versionsWithGlassPage = new VersionsWithGlassPage(driver);
        login.login();
    }

    @Disabled
    @Test
    public void listVersions() {
        assertEquals(versionsWithGlassPage.getAllVersionsOfAProject().size(), versionsWithGlassPage.getAllVersionsInGlass().size());
    }

    @Disabled
    @Test
    public void createdVersionExists() {
        String newlyCreatedVersionNumber = versionsWithGlassPage.createNewVersion();
        List<WebElement> versions = versionsWithGlassPage.getAllVersionsInGlass();
        for(WebElement version: versions) {
            if(version.getText().equals(newlyCreatedVersionNumber)) {
                assertTrue(true);
            }
        }
    }

    @Test
    public void selectVersion() {
        System.out.println(versionsWithGlassPage.selectVersion());
        assertTrue(versionsWithGlassPage.selectVersion().contains("Version "));
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        driver.wait(5000);
        Utils.tearDown();
    }

}
