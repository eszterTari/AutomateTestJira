package test.componentsWithGlass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pageFactory.ComponentForm;
import pageFactory.GlassDocument;
import pageFactory.Login;
import util.RunEnvironment;
import util.Utils;

import static org.junit.jupiter.api.Assertions.*;

public class TestComponentsWithGlass {
    private WebDriver driver;
    private Login login;

    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        login = new Login(driver);
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

        GlassDocument glassDocument = new GlassDocument(driver);

        ComponentForm componentForm = new ComponentForm(driver);
        componentForm.goToComponentsPageWithProjectSettings();

        componentForm.setComponentName(inputName);
        assertEquals(inputName, componentForm.getTextFromComponentNameInput());

        componentForm.setAssigneeInput(inputAssigne1);
        assertTrue(componentForm.getAssigneeInputText().contains(inputAssigne1));

        //TODO:Temp solution: If the test project is not exist
        if(!componentForm.isProjectExist(inputName)) componentForm.clickOnAddComponent();
        else {
            componentForm.setComponentName("");
            componentForm.setAssigneeInput("");
        }

        //TODO: timing!!!!
        /*glassDocument.clickOnAddComponent();
        assertTrue(glassDocument.isProjectExist(inputName), "The component's name is not listed in the component list!");

        glassDocument.removeProject(inputName);

        //Check component in Project's setting
        assertFalse(glassDocument.isProjectExist(inputName), "The component's name is listed in the component list!");*/

        //Check component with Glass
        glassDocument.clickOnGlassDocMenuItem();

        assertTrue(componentForm.isProjectExist(inputName), "The " + inputName + " is not presented in the component list");
    }

    @Test
    void testCheckCreatedComponentBySideMenu() {
        login.login();
        ComponentForm componentForm = new ComponentForm(driver);
        componentForm.goToTheProject();

        componentForm.gotToComponentsPageWithSideBar();
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
