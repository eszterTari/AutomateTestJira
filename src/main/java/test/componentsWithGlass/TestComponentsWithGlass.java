package test.componentsWithGlass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pageFactory.ComponentPage;
import pageFactory.GlassDocumentPage;
import pageFactory.Login;
import pageFactory.NavigateToPages;
import util.RunEnvironment;
import util.Utils;

import static org.junit.jupiter.api.Assertions.*;

public class TestComponentsWithGlass {
    private WebDriver driver;
    private Login login;
    private NavigateToPages navigateToPages;

    @BeforeEach
    public void setup() {
        Utils.setup();
        this.driver = RunEnvironment.getWebDriver();
        login = new Login(this.driver);
        navigateToPages = new NavigateToPages(this.driver);
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

        GlassDocumentPage glassDocumentPage = new GlassDocumentPage(driver);

        ComponentPage componentPage = new ComponentPage(driver);
        navigateToPages.goToComponentsPageWithProjectSettings();

        componentPage.setComponentName(inputName);
        componentPage.setAssigneeInput(inputAssigne1);

        assertAll("Test Component form input fields",
                () -> assertEquals(inputName, componentPage.getTextFromComponentNameInput()),
                () -> assertTrue(componentPage.getAssigneeInputText().contains(inputAssigne1))
         );



        //TODO:Temp solution: If the test project is not exist
        if(!componentPage.isProjectExist(inputName)) componentPage.clickOnAddComponent();
        else {
            componentPage.setComponentName("");
            componentPage.setAssigneeInput("");
        }

        //TODO: timing!!!!
        /*glassDocumentPage.clickOnAddComponent();
        assertTrue(glassDocumentPage.isProjectExist(inputName), "The component's name is not listed in the component list!");

        glassDocumentPage.removeProject(inputName);

        //Check component in Project's setting
        assertFalse(glassDocumentPage.isProjectExist(inputName), "The component's name is listed in the component list!");*/

        //Check component with Glass
        navigateToPages.gotToGlassDocPage();

        assertTrue(componentPage.isProjectExist(inputName), "The " + inputName
                + " is not presented in the component list");


        //check component with component side bar
        navigateToPages.goToTheProject();
        navigateToPages.gotToComponentsPageWithSideBar();

        assertTrue(componentPage.isProjectExist(inputName), "The " + inputName
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
