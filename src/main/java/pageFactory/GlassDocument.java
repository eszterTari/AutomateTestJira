package pageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GlassDocument {

    //Projects: browse_link,
    //View all projects: id=project_view_all_link_lnk,
    //click on project name: original-title="Private Project 4"
    //click on settings: data-tooltip="Project settings"
    //permission: Glass View permission

    private WebDriverWait wait;
    private static final int TIMEOUT = 5;
    private static final int POLLING = 100;

    private WebDriver driver;

    @FindBy(id = "browse_link")
    private WebElement projectsMenuItem;

    @FindBy(id = "project_view_all_link_lnk")
    private WebElement viewAllProjects;

    @FindBy(xpath = ".//*[@original-title = 'Private Project 4']")
    private WebElement project;

    @FindBy(className = "aui-sidebar-footer")
    private WebElement projectSettingsMenuItem;

    @FindBy(id = "administer_project_components")
    private WebElement componentsAdminMenuItem;

    @FindBy(xpath = ".//form[@id='components-add__component']/div[@class='components-add__confirm']/button")
    private WebElement addComponentButton;

    @FindBy(xpath = ".//form[@id='components-add__component']/div[@class='components-add__name']/input")
    private WebElement componentNameInput;

    @FindBy(xpath = ".//form[@id='components-add__component']/div[@class='components-add__assignee']/*/input")
    private WebElement componentAssigneeInput;

    @FindBys(@FindBy(xpath = ".//table[@id='components-table']/tbody[@class='items']"))
    private List<WebElement> componentTableRows;

    public GlassDocument(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
    }

    public void gotToComponentPageProjectSettings() {
        wait.until(ExpectedConditions.elementToBeClickable(projectsMenuItem));
        projectsMenuItem.click();

        wait.until(ExpectedConditions.elementToBeClickable(viewAllProjects));
        viewAllProjects.click();

        wait.until(ExpectedConditions.elementToBeClickable(project));
        project.click();

        wait.until(ExpectedConditions.elementToBeClickable(projectSettingsMenuItem));
        projectSettingsMenuItem.click();

        wait.until(ExpectedConditions.elementToBeClickable(componentsAdminMenuItem));
        componentsAdminMenuItem.click();
    }

    public void setComponentName(String componentName) {
        componentNameInput.sendKeys(componentName);
    }

    public String getTextFromComponentNameInput() {
        return componentNameInput.getAttribute("value");
    }

    public void setAssigneeInput(String assignee) {
        wait.until(ExpectedConditions.elementToBeClickable(componentAssigneeInput));
        componentAssigneeInput.click();
        componentAssigneeInput.sendKeys(assignee);

        wait.until(ExpectedConditions.textToBePresentInElementValue(componentAssigneeInput, assignee));
        componentAssigneeInput.sendKeys(Keys.ENTER);
    }

    public String getAssigneInputText() {
        return componentAssigneeInput.getAttribute("value");
    }

    public void clickOnAddComponent() {
        wait.until(ExpectedConditions.elementToBeClickable(addComponentButton));
        addComponentButton.click();
    }

    private void highlighterMethod(WebElement webElement, WebDriver webDriver) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", webElement);
        js.executeScript("arguments[0].setAttribute('style', 'color: red;');", webElement);
    }

}
