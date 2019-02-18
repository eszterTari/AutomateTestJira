package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EnvironmentManagerWin {
    private static String driverPath = System.getenv("driverPath");
    private static WebDriver driver;


    public static void initChromeWebDriver() {

        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();

        RunEnvironment.setWebDriver(driver);
    }

    public static void initFireFoxWebDriver() {
        //TODO set driver for firefox
    }

    public static void shutDownDriver() {
        if(driver!=null) {
            System.out.println("Closing chrome browser");
            RunEnvironment.getWebDriver().quit();
        }

    }
}
