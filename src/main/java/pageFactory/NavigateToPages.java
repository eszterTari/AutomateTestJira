package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Utils;

public class NavigateToPages {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int TIMEOUT = 10;

    @FindBy(id = "browse_link")
    private WebElement projectsMenuItem;

    @FindBy(id = "project_view_all_link_lnk")
    private WebElement viewAllProjects;

    @FindBy(xpath = ".//*[@original-title = 'Private Project 4']")
    private WebElement project;

    @FindBy(className = "aui-sidebar-footer")
    //@FindBy(xpath = "//div[@class='aui-sidebar-footer']/a[@data-tooltip='Project settings']")
    //@FindBy(xpath = "//div[@class='aui-sidebar-footer']/a[@href='/plugins/servlet/project-config/PP4']")
    private WebElement projectSettingsMenuItem;

    @FindBy(id = "administer_project_components")
    private WebElement componentsAdminMenuItem;

    @FindBy(xpath = "//a[@data-link-id='com.atlassian.jira.jira-projects-plugin:components-page']")
    private WebElement componentsSideMenuItem;

    @FindBy(xpath = "//a[@data-link-id='com.codecanvas.glass:glass']")
    private WebElement glassDocMenuItem;

    public NavigateToPages(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        wait = new WebDriverWait(this.driver, TIMEOUT);//, POLLING);
        //wait = new WebDriverWait(driver, 20);
    }

    private By projectSettingsMenuPath = By.xpath("//a[@class='aui-button aui-button-subtle aui-sidebar-settings-button']");
    private By componentsSideMenuPath = By.xpath("//li[contains(@class,'aui-nav-selected')]//a[contains(@class,'aui-nav-item')]");

    //TODO: input any kind of project
    public void goToTheProject() {
        wait.until(ExpectedConditions.elementToBeClickable(projectsMenuItem));
        projectsMenuItem.click();

        wait.until(ExpectedConditions.elementToBeClickable(viewAllProjects));
        viewAllProjects.click();

        wait.until(ExpectedConditions.elementToBeClickable(project));
        project.click();
    }

    public void goToComponentsPageWithProjectSettings() {
        goToTheProject();

        //TODO: not working
        //waitForPageLoadComplete(driver, 1000);
        //driver.manage().timeouts().pageLoadTimeout(5, SECONDS);
        wait.until(ExpectedConditions.visibilityOf(projectSettingsMenuItem.findElement(By.tagName("a"))));
        //wait.until(ExpectedConditions.presenceOfElementLocated(projectSettingsMenuPath)).click();
        projectSettingsMenuItem.click();

        wait.until(ExpectedConditions.elementToBeClickable(componentsAdminMenuItem)).click();
    }

    public void gotToComponentsPageWithSideBar() {
        //TODO: not working
        //waitForPageLoadComplete(driver, 10000);
        //wait.until(ExpectedConditions.visibilityOf(componentsSideMenuItem));
        wait.until(ExpectedConditions.visibilityOf(componentsSideMenuItem.findElement(By.xpath("..")))).click();
        //wait.until(ExpectedConditions.presenceOfElementLocated(componentsSideMenuPath)).click();
        //componentsSideMenuItem.click();
    }

    public void gotToGlassDocPage() {
        if (!glassDocMenuItem.isDisplayed()) {
            goToTheProject();
        }

        wait.until(ExpectedConditions.elementToBeClickable(glassDocMenuItem));
        glassDocMenuItem.click();
        Utils.checkAlert(this.driver);
    }
}
