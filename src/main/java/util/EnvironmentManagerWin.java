package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EnvironmentManagerWin {
    private static String driverPath = System.getenv("driverPath");
    private static WebDriver driver;


    public static void initChromeWebDriver() {

        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();

        RunEnvironment.setWebDriver(driver);
    }

    public static void initFireFoxWebDriver() {
        System.setProperty("webdriver.gecko.driver", driverPath );
        driver = new FirefoxDriver();

        RunEnvironment.setWebDriver(driver);
    }

    public static void shutDownDriver() {
        if(driver!=null) {
            System.out.println("Closing browser");
            RunEnvironment.getWebDriver().quit();
        }

    }
}
