package test;

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
        login.loginWithDashboard();
    }

    @Disabled
    @Test
    public void versions() {
        assertEquals(versionsWithGlassPage.getAllVersionsOfAProject().size(), versionsWithGlassPage.getAllVersionsInGlass().size());
    }

    @Test
    public void createdVersionExists() {
        String newlyCreatedVersionNumber = versionsWithGlassPage.createNewVersion();
        List<WebElement> versions = versionsWithGlassPage.getAllVersionsInGlass();
        for(WebElement version: versions) {
            if(version.getText().equals(newlyCreatedVersionNumber)) {
                assert(true);
            }
        }
    }

    @Disabled
    @Test
    public void selectVersion() {

    }


}
