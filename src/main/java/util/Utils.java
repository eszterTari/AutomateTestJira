package util;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    /**
     * Environment variables
     * driverPath
     * username
     * password
     * myDriver
     */
    public static void setup() {
        if (OS.isFamilyMac()) {

            if (System.getenv("myDriver").equals("chrome")){
                System.out.println("1*******************");
                System.out.println("Launching chrome browser");
                EnvironmentManagerMac.initChromeWebDriver();
                //driver.manage().window().maximize();
            }else {
                System.out.println("2*******************");
                System.out.println("Launching firefox browser");
                EnvironmentManagerMac.initFireFoxWebDriver();
                //driver.manage().window().maximize();
            }

        } else {

            if (System.getenv("myDriver").equals("chrome")){
                System.out.println("3*******************");
                System.out.println("Launching chrome browser");
                EnvironmentManagerWin.initChromeWebDriver();

            }else {
                System.out.println("4*******************");
                System.out.println("Launching firefox browser");
                EnvironmentManagerWin.initFireFoxWebDriver();

            }
        }
    }

    public  static void tearDown() {
        if (OS.isFamilyMac()) {
            EnvironmentManagerMac.shutDownDriver();
        } else {
            EnvironmentManagerWin.shutDownDriver();
        }
    }

//    public void click(WebElement element, WebDriver driver, int specifiedTimeout) {
//        WebDriverWait wait = new WebDriverWait(driver, specifiedTimeout);
//        ExpectedCondition<Boolean> elementIsClickable = arg0 -> {
//            try {
//                element.click();
//                return true;
//            } catch (Exception e) {
//                return false;
//            }
//        };
//        wait.until(elementIsClickable);
//    }

}
