package test.componentsWithGlass;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestComponentsWithGlass {

    @Test
    public void testLogin() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "login/login.properties";

        Properties catalogProps = new Properties();
        try {
            catalogProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String username = catalogProps.getProperty("username");
        String password = catalogProps.getProperty("password");
        System.out.println(username + ": " + password);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input.csv")
    void testHelloCsv(String a, String b) {
        System.out.println(a + " " + b);
    }
}
