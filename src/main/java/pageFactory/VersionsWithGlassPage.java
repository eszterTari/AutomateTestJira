package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class VersionsWithGlassPage {

    WebDriver driver;
    Wait wait;
    String releasesPageOfAProject = "https://jira.codecool.codecanvas.hu/projects/PP5?selectedItem=com.atlassian.jira.jira-projects-plugin:release-page&status=no-filter";
    String glassPageOfAProject = "https://jira.codecool.codecanvas.hu/projects/PP5?selectedItem=com.codecanvas.glass:glass";

    @FindBy( className = "versions-table__name" )
    List<WebElement> allVersionsOfAProject;

    @FindBy( xpath = "//*[@id=\"versions-table\"]/tbody[2]/tr[1]/td[1]/div/a" )
    WebElement linkOfAVersion;

    @FindBy( id = "aui-uid-2" )
    WebElement versionsTabInGlass;

    @FindBy( xpath = "//*[@id=\"releases-add__version\"]/div[1]/input" )
    WebElement inputFieldForNewVersion;

    @FindBy( xpath = "//*[@id=\"releases-add__version\"]/div[4]/input")
    WebElement descriptionFieldForNewVersion;

    @FindBy( xpath = "//*[@id=\"releases-add__version\"]/div[5]/button")
    WebElement buttonToAddNewVersion;

    @FindBy( xpath = "//*[@id=\"release-report\"]/header/div/div[1]/h2" )
    WebElement versionHeader;

    public VersionsWithGlassPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 3000);
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getAllVersionsOfAProject() {
        driver.get(releasesPageOfAProject);
        return allVersionsOfAProject;
    }

    public List<WebElement> getAllVersionsInGlass() {
        driver.get(glassPageOfAProject);
        versionsTabInGlass.click();
        return allVersionsOfAProject;
    }

    public String createNewVersion() {
        final String newlyCreatedVersionNumber = "4.0";
        driver.get(releasesPageOfAProject);
        inputFieldForNewVersion.sendKeys(newlyCreatedVersionNumber);
        descriptionFieldForNewVersion.sendKeys("This is a version number for testing purposes only and can be deleted.");
        buttonToAddNewVersion.click();
        return newlyCreatedVersionNumber;
    }

    public String selectVersion() {
        driver.get(glassPageOfAProject);
        versionsTabInGlass.click();
        System.out.println(allVersionsOfAProject.size());
        if(allVersionsOfAProject.size() > 1) {
            linkOfAVersion.click();
        }
        driver.switchTo().frame(1);
        wait.until(ExpectedConditions.textToBePresentInElementValue(versionHeader, "Version "));
        return versionHeader.getText();
    }
}


/*@FindBy( linkText= "Glass Documentation" )
    WebElement glassDocumentationTab;

    @FindBy( id = "fixVersions-textarea" )
    WebElement textareaOfIssueVersionsInEditModal;

    @FindBy( xpath = "/html/body/div[7]/div[2]/div[1]/div/form/div[1]/div/div[7]/div[1]/span" )
    WebElement dropdownForIssueVersions;

    @FindBy( id = "edit-issue-submit" )
    WebElement updateButtonOnIssueEditModal;*/