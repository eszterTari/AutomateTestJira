package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Browse_Issues {

    @FindBy(xpath = "//a[@title='Search for issues and view recent issues']")
    WebElement issuesFromMenuItem;

    @FindBy(xpath = "//a[@id='issues_new_search_link_lnk']")
    WebElement searchAllIssues;

}
