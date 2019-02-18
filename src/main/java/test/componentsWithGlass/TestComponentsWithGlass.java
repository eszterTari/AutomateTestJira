package test.componentsWithGlass;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class TestComponentsWithGlass {

    @Test
    public void testLogin() {
        //Projects: browse_link, View all projects: id=project_view_all_link_lnk,
        //click on project name: original-title="Private Project 4"
        //click on settings: data-tooltip="Project settings"
        //permission: Glass View permission

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/input.csv")
    void testHelloCsv(String a, String b) {
        System.out.println(a + " " + b);
    }
}
