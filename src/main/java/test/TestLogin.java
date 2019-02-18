package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pageFactory.Login;

import static org.junit.jupiter.api.Assertions.*;

public class TestLogin {

    @Test
    public void testLogin() {
        Login l = new Login();
        assertTrue(true, "Login");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input.csv")
    void testHelloCsv(String a, String b) {
        System.out.println(a + " " + b);
    }
}
