package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
    
    protected final String BASE_URL = "https://jira.codecool.codecanvas.hu";
    protected final int WAIT_TIMEOUT = 20;
    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageObject(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, this.WAIT_TIMEOUT);
        PageFactory.initElements(this.driver, this);
    }

    public void init() {
        this.driver.get(this.BASE_URL);
    }
}