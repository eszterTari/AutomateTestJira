package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "login-form-username")
    WebElement userName;

    @FindBy(id = "login-form-password")
    WebElement password;

    @FindBy(id = "login-form-submit")
    WebElement loginBtn;

    @FindBy(id = "user-options")
    WebElement userOptions;

    @FindBy(id = "log_out")
    WebElement logoutBtn;

    @FindBy(xpath = "//*[@id='login-form']//div[@class='aui-message error']")
    WebElement errorMessage;

    @FindBy(id = "data-displayname")
    WebElement logCheck;

    @FindBy(xpath = "//*[@id='content']//h1[text(), 'Logout']")
    WebElement logoutSign;

    public Login(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void login() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/login.jsp");
        userName.sendKeys(System.getenv("username"));
        password.sendKeys(System.getenv("password"));
        loginBtn.click();
    }

    public boolean isLoggedIn() {
        return !userOptions.getText().equals("Log In");
    }

    public void loginWithDashboard(String username, String password) {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/login.jsp");
        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }

        userName.sendKeys(username);
        this.password.sendKeys(password);
        loginBtn.click();
    }

    public void logout() {
        userOptions.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("log_out")));
        logoutBtn.click();
    }

    public boolean wrongLogin() {
        return errorMessage.isDisplayed();
    }

}
