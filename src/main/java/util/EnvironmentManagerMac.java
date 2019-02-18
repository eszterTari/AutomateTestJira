package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EnvironmentManagerMac {

    private static String driverPath = System.getenv("driverPath");
    private static WebDriver driver;


    public static void initChromeWebDriver() {

        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", driverPath);

        RunEnvironment.setWebDriver(driver);
    }

    public static void initFireFoxWebDriver() {
        //TODO TODO set driver for firefox
    }

    public static void shutDownDriver() {
        if(driver!=null) {
            System.out.println("Closing chrome browser");
            RunEnvironment.getWebDriver().quit();
        }

    }
}
