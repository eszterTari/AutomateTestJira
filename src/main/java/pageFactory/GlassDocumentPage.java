package pageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GlassDocumentPage {
    //TODO:Check the permission
    //Projects: browse_link,
    //View all projects: id=project_view_all_link_lnk,
    //click on project name: original-title="Private Project 4"
    //click on settings: data-tooltip="Project settings"
    //permission: Glass View permission
    private WebDriver driver;
    private WebDriverWait wait;
    private NavigateToPages navigateToPages;

    private static final int TIMEOUT = 5;
    private static final int POLLING = 100;


    public GlassDocumentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        wait = new WebDriverWait(this.driver, TIMEOUT, POLLING);
        navigateToPages = new NavigateToPages(this.driver);
    }



    private void highlighterMethod(WebElement webElement, WebDriver webDriver) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", webElement);
        //js.executeScript("arguments[0].setAttribute('style', 'color: red;');", webElement);
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; border-color: red;');", webElement);
    }

}
