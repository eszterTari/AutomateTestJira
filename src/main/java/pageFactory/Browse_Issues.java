package pageFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browse_Issues {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@title='Search for issues and view recent issues']")
    WebElement issuesFromMenuItem;

    @FindBy(xpath = "//a[@id='issues_new_search_link_lnk']")
    WebElement searchAllIssues;

    @FindBy(xpath = "//button[contains(text(),'Search')]")
    WebElement searchBtnToValidateIssuesPage;

    @FindBy(xpath = "//span[contains(text(),'Project:')]")
    WebElement filterForProject;

    @FindBy(xpath = "//input[@id='searcher-pid-input']")
    WebElement projectFilterSearchBar;

    @FindBy(xpath = "//span[@class='aui-icon aui-icon-small aui-iconfont-refresh-small']")
    WebElement refreshResults;

    @FindBy(xpath = "import java.util.concurrent.TimeUnit;\n")
    WebElement exportIssueBtn;

    @FindBy(xpath = "//a[@id='project-name-val']")
    WebElement projectName;

    @FindBy(xpath = "//div[@class='showing']/span")
    WebElement issueCounter;

    public Browse_Issues(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public void navigateToBrowseIssues(){
        issuesFromMenuItem.click();
        wait.until(ExpectedConditions.elementToBeClickable(searchAllIssues));
        searchAllIssues.click();
        wait.until(ExpectedConditions.elementToBeClickable(searchBtnToValidateIssuesPage));
    }

    public Boolean isIssuesSearchBtnVisible(){
        if (searchBtnToValidateIssuesPage.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public String getProjectNameValue(){
        return projectName.getText();
    }

    public WebElement openIssuesOfTestProject(String projectNameToTest){
        navigateToBrowseIssues();
        filterForProject.click();
        String lowerCase = projectNameToTest.toLowerCase();
        String upperCase = projectNameToTest.toUpperCase();
        projectFilterSearchBar.sendKeys(lowerCase, Keys.ENTER);
        filterForProject.click();
        wait.until(ExpectedConditions.textToBePresentInElement(projectName, upperCase));
        System.out.println(projectName.getText());
        return projectName;
    }

    public Boolean isMinimumNumberOfIssuesArePresent(int minimumIssueNumber){
        if (getNumbersOfIssues() >= minimumIssueNumber) {
            return true;
        } else {
            return false;
        }
    }

    public void closeIssuesOfTestProjekt(String projectNameToTest){
        String upperCase = projectNameToTest.toUpperCase();
        if (projectName.getText().equals(upperCase)) {
            filterForProject.click();
            projectFilterSearchBar.sendKeys(projectNameToTest, Keys.ENTER);
        } else {
            System.out.println("No such project opened like " + projectNameToTest);
        }

    }

    public int getNumbersOfIssues(){
        wait.until(ExpectedConditions.visibilityOf(issueCounter));
        String issueCount = issueCounter.getText();
        String[] parts = issueCount.split(" ");
        int numberOfIssues;
        try {
            numberOfIssues = Integer.parseInt(parts[2]);
        }
        catch (NumberFormatException e) {
            numberOfIssues = 0;
        }
        return numberOfIssues;
    }




}
