package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowseProjects {

    WebDriver driver;
    WebDriverWait wait;
    private static final int TIMEOUT = 5;
    private static final int POLLING = 100;

    @FindBy(id = "browse_link")
    WebElement projectsSubMenu;

    @FindBy(id = "project_view_all_link_lnk")
    WebElement viewAllProjects;

    public BrowseProjects(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void viewAllProjects() {
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        wait.until(ExpectedConditions.elementToBeClickable(projectsSubMenu));
        projectsSubMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(viewAllProjects));
        viewAllProjects.click();
    }
}
