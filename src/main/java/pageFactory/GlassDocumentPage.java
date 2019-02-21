package pageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Utils;

import java.util.List;

public class GlassDocumentPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private NavigateToPages navigateToPages;
    private ComponentPage componentPage;

    private static final int TIMEOUT = 5;
    private static final int POLLING = 100;

    @FindBy(id = "glass-permissions-nav")
    private WebElement permissionsNavItem;

    @FindBys(@FindBy(xpath = "//*[@id='glass-permissions-panel']//table[@class='aui glass-perm']/tbody/tr"))
    private List<WebElement> glassPermissionTableRows;

    By permissionRowTitlePath = By.xpath(".//p[@class='title']/b");


    //@FindBy(xpath = ".//table[@id='components-table']/tbody[@class='items']/tr"))

    //@FindBys(@FindBy)

    public GlassDocumentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        wait = new WebDriverWait(this.driver, TIMEOUT, POLLING);
        navigateToPages = new NavigateToPages(this.driver);
        componentPage = new ComponentPage(this.driver);
    }

    /*** Component Form ***/
    public boolean isComponentExist(String componentName) {
        return componentPage.isComponentExist(componentName);
    }

    /*** Permissions ***/
    public void clickOnPermissionNavItem() {
        wait.until(ExpectedConditions.elementToBeClickable(permissionsNavItem)).click();
    }

    //Browse project, Create issue, Edit issue
    public boolean hasCurrentUserPermissionFor(String nameOfElement) {
        for (WebElement e: glassPermissionTableRows) {
            //Browse Projects
            if (e.findElement(permissionRowTitlePath).getText().equals(nameOfElement)
                && e.findElement(By.xpath(".//td[@class='td-icon'][1]/div")).getAttribute("class").equals("glass-true-icon")) {
                return true;
            }
        }
        return false;
    }


}
