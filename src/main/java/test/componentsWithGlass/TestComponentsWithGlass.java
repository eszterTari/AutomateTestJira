package test.componentsWithGlass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pageFactory.*;
import util.RunEnvironment;
import util.Utils;

import static org.junit.jupiter.api.Assertions.*;

public class TestComponentsWithGlass {
    private WebDriver driver;
    private Login login;
    private NavigateToPages navigateToPages;
    private ProjectSettingsPage projectSettingsPage;

    @BeforeEach
    public void setup() {
        Utils.setup();
        this.driver = RunEnvironment.getWebDriver();
        login = new Login(this.driver);
        navigateToPages = new NavigateToPages(this.driver);
        projectSettingsPage = new ProjectSettingsPage(this.driver);
    }

    @Test
    public void testCheckCreatedComponentByProjectSettings() {
        String inputName = "Test Component";
        String inputAssigne1 = "Project default";
        //TODO: into csv
        String inputAssigne2 = "Component lead";
        String inputAssigne3 = "Project lead";
        String inputAssigne4 = "Unassigned";

        login.login();

        //GlassDocumentPage glassDocumentPage = new GlassDocumentPage(driver);

        //ComponentPage componentPage = new ComponentPage(driver);
        navigateToPages.goToProjectSettingsPage();
        navigateToPages.goToComponentsPageWithSideBar();

        projectSettingsPage.setComponentNameInput(inputName);
        projectSettingsPage.setComponentAssigneeInput(inputAssigne1);


        assertAll("Test Component form input fields",
                () -> assertEquals(inputName, projectSettingsPage.getTextFromComponentNameInput()),
                () -> assertTrue(projectSettingsPage.getAssigneeInputText().contains(inputAssigne1))
         );



        //TODO:Temp solution: If the test project is not exist
        if(!projectSettingsPage.isComponentExist(inputName))
            projectSettingsPage.clickOnAddComponent();
        else {
            projectSettingsPage.setComponentNameInput("");
            projectSettingsPage.setComponentAssigneeInput("");
        }

        //TODO: timing!!!!
        /*glassDocumentPage.clickOnAddComponent();
        assertTrue(glassDocumentPage.isComponentExist(inputName), "The component's name is not listed in the component list!");

        glassDocumentPage.removeProject(inputName);

        //Check component in Project's setting
        assertFalse(glassDocumentPage.isComponentExist(inputName), "The component's name is listed in the component list!");*/

        //Check component with Glass
        navigateToPages.goToGlassDocPage();

        assertTrue(projectSettingsPage.isComponentExist(inputName), "The " + inputName
                + " is not presented in the component list");


        //Check component with component sidebar menu
        navigateToPages.goToTheProject();
        navigateToPages.goToComponentsPageWithSideBar();

        assertTrue(projectSettingsPage.isComponentExist(inputName), "The " + inputName
                + " is not presented in the component list");
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/input.csv")
    void testHelloCsv(String a, String b) {
        System.out.println(a + " " + b);
    }

    @AfterEach
    public void tearDown() {
        //Utils.tearDown();
    }
}
