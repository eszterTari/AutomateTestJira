package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrowseProjects {

    WebDriver driver;
    WebDriverWait wait;
    private static final int TIMEOUT = 5;
    private static final int POLLING = 100;

    @FindBy(id = "browse_link")
    WebElement projectsSubMenu;

    @FindBy(id = "project_view_all_link_lnk")
    WebElement viewAllProjects;

    @FindBy(linkText = "COALA Project")
    WebElement coalaProjectLink;

    @FindBy(linkText = "JETI Project")
    WebElement jetiProjectLink;

    @FindBy(linkText = "TOUCAN projekt")
    WebElement toucanProjectLink;

    @FindBy(xpath = "//div[contains(@class,'aui-sidebar-body')]//div[contains(@class,'project-title')]//a")
    WebElement projectNameOnDetailedPage;

    @FindBy(className = "subnavigator-title")
    WebElement subNavigatorTitle;

    @FindBy(id = "project-filter-text")
    WebElement searchBar;

    @FindBy(className = "projects-list")
    WebElement listedProjectsTable;

    @FindBy(xpath = "//*[@id=\"filter-projects\"]/div/h2")
    WebElement titleOfProjectFiltersPage;

    @FindBys(@FindBy(xpath = "//div[contains(@id,'browse-projects-sidebar')]//div[contains(@class,'category-nav')]" +
            "//ul[contains(@class, 'aui-nav')]" + "//li/a"))
    List<WebElement> projectCategoriesSideBar;

    @FindBys(@FindBy(xpath = "//div[contains(@id,'browse-projects-sidebar')]//div[contains(@class,'project-type-nav')]" +
            "//ul[contains(@class, 'project-types-filters')]" + "//li"))
    List<WebElement> projectTypesSideBar;

    List<String> projectNamesOfRequirements = new ArrayList<>(Arrays.asList("COALA", "JETI", "TOUCAN"));

    public BrowseProjects(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void viewAllProjects() {
        wait.until(ExpectedConditions.elementToBeClickable(projectsSubMenu));
        projectsSubMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(viewAllProjects));
        viewAllProjects.click();
    }

    public void detailedPageOfProject(WebElement projectName) {
        driver.get("https://jira.codecool.codecanvas.hu/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=all");
        wait.until(ExpectedConditions.elementToBeClickable(projectName));
        projectName.click();
    }

    public boolean detailedPageOfProjectOfRequirements() {
        List<WebElement> projectsOfRequirements = new ArrayList<>(Arrays.asList(coalaProjectLink, jetiProjectLink, toucanProjectLink));
        for (WebElement projectName : projectsOfRequirements) {
            detailedPageOfProject(projectName);
            wait.until(ExpectedConditions.elementToBeClickable(projectNameOnDetailedPage));
            String projectNameOnDetailedProjectPage = projectNameOnDetailedPage.getText();
            if (!projectNameOnDetailedProjectPage.contains(projectName.getText())) {
                return false;
            }
        }
        return true;
    }

    public boolean navigateToRequiredProjectsReports() {
        for (String projectName : projectNamesOfRequirements) {
            driver.get("https://jira.codecool.codecanvas.hu/projects/" + projectName + "?selectedItem=com.atlassian.jira.jira-projects-plugin:report-page");
            String subNavigatorTitleText = subNavigatorTitle.getText();
            if (!subNavigatorTitleText.equals("All reports")) {
                return false;
            }
        }
        return true;
    }

    public boolean useSearchBarToFindAProject(String searchWord) throws InterruptedException {
        driver.get("https://jira.codecool.codecanvas.hu/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=all");
        wait.until(ExpectedConditions.elementToBeClickable(searchBar));
        searchBar.click();
        searchBar.sendKeys(searchWord);
        wait.until(ExpectedConditions.visibilityOf(listedProjectsTable));
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfAllElements(listedProjectsTable.findElements(By.xpath("./tr"))));
        List<WebElement> rows = listedProjectsTable.findElements(By.xpath("./tr"));
        for (WebElement row : rows) {
            for (WebElement elem : row.findElements(By.xpath(".//td[1]"))) {
                if (!elem.getText().toLowerCase().contains(searchWord)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean filterByProjectTypes() {
        return clickOnEveryOptionInSideBar(projectTypesSideBar, titleOfProjectFiltersPage);
    }

    public boolean filterByProjectCategories() {
        return clickOnEveryOptionInSideBar(projectCategoriesSideBar, titleOfProjectFiltersPage);
    }

    public boolean clickOnEveryOptionInSideBar(List<WebElement> sideBar, WebElement titleOfFilteredPage) {
        driver.get("https://jira.codecool.codecanvas.hu/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=all");
        wait.until(ExpectedConditions.visibilityOfAllElements(sideBar));
        List<String> ids = new ArrayList<>();
        for (WebElement optionOfSidebar : sideBar) {
            ids.add(optionOfSidebar.getAttribute("id"));
        }
        for (String id : ids) {
            WebElement optionInSideBar = driver.findElement(By.id(id));
            wait.until(ExpectedConditions.elementToBeClickable(optionInSideBar));
            String optionInSideBarText = optionInSideBar.getText().toLowerCase();
            optionInSideBar.click();
            if (!titleOfFilteredPage.getText().toLowerCase().contains(optionInSideBarText)) {
                return false;
            }
        }
        return true;
    }
}
