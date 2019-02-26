package pageFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.By.xpath;

public class CreateIssue {
    WebDriver driver;
    WebDriverWait wait;


    @FindBy(id = "create_link")
    WebElement buttonCreateNavbar;

    @FindBy(xpath ="//*[@id='project-single-select']//input[@id='project-field']")
    WebElement projectSelectInForm;

    @FindBy(xpath ="//*[@id='issuetype-single-select']//input[@id='issuetype-field']")
    WebElement issueTypeSelectInForm;

    @FindBy(id = "summary")
    WebElement summarySelectInForm;

    @FindBy(id = "create-issue-submit")
    WebElement createbtnInForm;

    @FindBy(xpath = "//a[@title='Press undefined+` to cancel']")
    WebElement cancelInForm;

    @FindBy(xpath = "//div[@id='aui-flag-container']")
    WebElement popupCreateIssue;

    public CreateIssue(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void createAnIssueByButton(String projectName, String issueType) {
        buttonCreateNavbar.click();

        try {
            wait.until((ExpectedCondition<Boolean>) driver -> ! projectSelectInForm.isEnabled());
            wait.until((ExpectedCondition<Boolean>) driver -> projectSelectInForm.isEnabled());
        } catch (Exception e) {
            // no problem
        }
        wait.until(ExpectedConditions.elementToBeClickable(projectSelectInForm)).click();
        projectSelectInForm.sendKeys(Keys.BACK_SPACE);
        projectSelectInForm.sendKeys(projectName);
        wait.until(ExpectedConditions.textToBePresentInElement(projectSelectInForm, projectSelectInForm.getText()));
        projectSelectInForm.sendKeys(Keys.ENTER);

        try {
            wait.until((ExpectedCondition<Boolean>) driver -> ! issueTypeSelectInForm.isEnabled());
            wait.until((ExpectedCondition<Boolean>) driver -> issueTypeSelectInForm.isEnabled());
        } catch (Exception e) {
            // no problem
        }
        wait.until(ExpectedConditions.elementToBeClickable(issueTypeSelectInForm)).click();
        issueTypeSelectInForm.sendKeys(Keys.BACK_SPACE);
        issueTypeSelectInForm.sendKeys(issueType);
        wait.until(ExpectedConditions.textToBePresentInElement(issueTypeSelectInForm, issueTypeSelectInForm.getText()));
        issueTypeSelectInForm.sendKeys(Keys.ENTER);

        try {
            wait.until((ExpectedCondition<Boolean>) driver -> ! summarySelectInForm.isEnabled());
            wait.until((ExpectedCondition<Boolean>) driver -> summarySelectInForm.isEnabled());
        } catch (Exception e) {
            // no problem
        }
        wait.until(ExpectedConditions.visibilityOf(summarySelectInForm)).sendKeys(" KRK");
        wait.until(ExpectedConditions.textToBePresentInElement(summarySelectInForm, summarySelectInForm.getText()));

//        wait.until(ExpectedConditions.elementToBeClickable(cancelInForm)).click();
        wait.until(ExpectedConditions.elementToBeClickable(createbtnInForm)).click();
//        acceptAlert(driver);

    }
    public void acceptAlert(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            //nothing to do
        }
    }

    public Boolean isIssueCreateSuccessfully() {
        By popupField = xpath("//div[@id='aui-flag-container']");
        wait.until(ExpectedConditions.presenceOfElementLocated(popupField));
        return popupCreateIssue.isDisplayed();
    }
}
