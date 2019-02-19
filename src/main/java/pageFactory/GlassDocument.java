package pageFactory;

import org.openqa.selenium.*;
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

    @FindBys(@FindBy(xpath = ".//table[@id='components-table']/tbody[@class='items']/tr"))
    private List<WebElement> componentTableRows;

    By componentNamePathFromRow = By.xpath(".//td[@class='components-table__name']/div/a");
    By dynamicTableMenuPathFromRow = By.xpath(".//td[@class='dynamic-table__actions']/div/a");


    //dynamic: div id=component-actions-10011, a id=deletecomponent_10011
    //By deleteButtonPath = By.xpath(".//div[starts-with(@id, 'component-actions-')]/ul/li/a[starts-with(@id, 'deletecomponent_')]");
    //TODO: nem mukodik!!!!! By deleteButtonPath = By.xpath("//a[contains(@id, 'deletecomponent_')]");
    By deleteButtonPath = By.linkText("Delete");
    //By deleteButtonPath = By.cssSelector("a[id *= 'deletecomponent']");
    //By deleteButtonPath = By.cssSelector("a:contains('deletecomponent')");

    By submitButtonOnDeleteForm = By.xpath("//input[@id='submit']");


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

    public boolean isProjectExist(String projectName) {
        try {
            for (WebElement row : componentTableRows) {
                if (row.findElement(componentNamePathFromRow).getText().equals(projectName))
                    return true;
            }
        } catch (WebDriverException exception) {
            return false;
        }
        return false;
    }

    public void removeProject(String projectName) {
        for (WebElement row : componentTableRows) {
            if (row.findElement(componentNamePathFromRow).getText().equals(projectName)) {
                //click menu: td class=dynamic-table__actions/div/a
                WebElement dynamicTableMenu = row.findElement(dynamicTableMenuPathFromRow);
                dynamicTableMenu.click();
                //dynamic: div id=component-actions-10011, a id=deletecomponent_10011
                wait.until(ExpectedConditions.presenceOfElementLocated(deleteButtonPath));
                WebElement deleteButton = driver.findElement(deleteButtonPath);
                //highlighterMethod(deleteButton, driver);
                //driver.switchTo().activeElement();
                deleteButton.click();

                //driver.switchTo().activeElement();
                wait.until(ExpectedConditions.visibilityOfElementLocated(submitButtonOnDeleteForm));
                //highlighterMethod(driver.findElement(submitButtonOnDeleteForm), driver);
                driver.findElement(submitButtonOnDeleteForm).click();
            }
        }
    }

    private void highlighterMethod(WebElement webElement, WebDriver webDriver) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", webElement);
        //js.executeScript("arguments[0].setAttribute('style', 'color: red;');", webElement);
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; border-color: red;');", webElement);
    }

}
