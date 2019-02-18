package util;

import org.apache.commons.exec.OS;

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
                System.out.println("Launching chrome browser");
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
                System.out.println("Launching chrome browser");
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
}
