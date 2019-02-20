package pageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GlassDocument {
    //TODO:Check the permission
    //Projects: browse_link,
    //View all projects: id=project_view_all_link_lnk,
    //click on project name: original-title="Private Project 4"
    //click on settings: data-tooltip="Project settings"
    //permission: Glass View permission
    private WebDriver driver;
    private WebDriverWait wait;
    private NavigateToPage navigateToPage;

    private static final int TIMEOUT = 5;
    private static final int POLLING = 100;



    @FindBy(id = "browse_link")
    private WebElement projectsMenuItem;

    @FindBy(id = "project_view_all_link_lnk")
    private WebElement viewAllProjects;

    @FindBy(xpath = ".//*[@original-title = 'Private Project 4']")
    private WebElement project;

    @FindBy(xpath = "//a[@data-link-id='com.codecanvas.glass:glass']")
    private WebElement glassDocMenuItem;



    public GlassDocument(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        wait = new WebDriverWait(this.driver, TIMEOUT, POLLING);
        navigateToPage = new NavigateToPage(this.driver);
    }

    public void clickOnGlassDocMenuItem() {
        if (!glassDocMenuItem.isDisplayed()) {
            navigateToPage.goToTheProject();
        }

        wait.until(ExpectedConditions.elementToBeClickable(glassDocMenuItem));
        glassDocMenuItem.click();
        checkAlert();
    }

    private void highlighterMethod(WebElement webElement, WebDriver webDriver) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", webElement);
        //js.executeScript("arguments[0].setAttribute('style', 'color: red;');", webElement);
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; border-color: red;');", webElement);
    }

    public void checkAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            //exception handling
        }
    }

}
