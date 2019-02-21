package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public CreateIssue(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void createAnIssueByButton(String projectName, String issueType) {
        buttonCreateNavbar.click();
        wait.until(ExpectedConditions.elementToBeClickable(projectSelectInForm)).click();
        projectSelectInForm.sendKeys(Keys.BACK_SPACE);
        projectSelectInForm.sendKeys(projectName);
        wait.until(ExpectedConditions.textToBePresentInElement(projectSelectInForm, projectSelectInForm.getText()));
        projectSelectInForm.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(createbtnInForm));

        wait.until(ExpectedConditions.elementToBeClickable(issueTypeSelectInForm)).click();
        issueTypeSelectInForm.sendKeys(Keys.BACK_SPACE);
        issueTypeSelectInForm.sendKeys(issueType);
        wait.until(ExpectedConditions.textToBePresentInElement(issueTypeSelectInForm, issueTypeSelectInForm.getText()));
        issueTypeSelectInForm.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(createbtnInForm));

        wait.until(ExpectedConditions.visibilityOf(summarySelectInForm)).sendKeys(" KRK");
        wait.until(ExpectedConditions.textToBePresentInElement(summarySelectInForm, summarySelectInForm.getText()));
        wait.until(ExpectedConditions.elementToBeClickable(createbtnInForm));

        wait.until(ExpectedConditions.elementToBeClickable(cancelInForm)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(createbtnInForm)).click();
    }
}
