package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    WebDriver driver;

    @FindBy(id = "login-form-username")
    WebElement userName;

    @FindBy(id = "login-form-password")
    WebElement password;

    @FindBy(id = "login-form-submit")
    WebElement loginBtn;

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginWithDashboard() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/login.jsp");
        userName.sendKeys(System.getenv("username"));
        password.sendKeys(System.getenv("password"));
        loginBtn.click();
    }
}
