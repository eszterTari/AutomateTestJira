package test.componentsWithGlass;

import org.junit.jupiter.api.*;
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
    private GlassDocumentPage glassDocumentPage;

    @BeforeAll
    public static void setupBefore() {
        Utils.setup();
    }

    @BeforeEach
    public void setup() {

        this.driver = RunEnvironment.getWebDriver();
        login = new Login(this.driver);
        navigateToPages = new NavigateToPages(this.driver);
        projectSettingsPage = new ProjectSettingsPage(this.driver);
        glassDocumentPage = new GlassDocumentPage(this.driver);
        //login.login();
    }

    @Test
    public void testCheckCreatedComponent() {
        String projectName = "Private Project 4";
        String inputName = "Test Component";
        String inputAssigne1 = "Project default";
        String inputAssigne2 = "Component lead";
        String inputAssigne3 = "Project lead";
        String inputAssigne4 = "Unassigned";


        login.login();

        navigateToPages.goToProjectSettingsPage(projectName);
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

        //TODO: timing!!!! How to create and remove
        /*glassDocumentPage.clickOnAddComponent();
        assertTrue(glassDocumentPage.isComponentExist(inputName), "The component's name is not listed in the component list!");

        glassDocumentPage.removeProject(inputName);

        //Check component in Project's setting
        assertFalse(glassDocumentPage.isComponentExist(inputName), "The component's name is listed in the component list!");*/

        /*** Check component with Glass ***/
        navigateToPages.clickOnGlassDocumentNavItem();

        assertTrue(glassDocumentPage.isComponentExist(inputName), "The " + inputName
                + " is not presented in the component list");


        /*** Check component via component sidebar menu in Project Settings ***/
        navigateToPages.goToTheProject(projectName);
        navigateToPages.goToComponentsPageWithSideBar();

        assertTrue(projectSettingsPage.isComponentExist(inputName), "The " + inputName
                + " is not presented in the component list");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/permissions/permissionsForUser.csv", numLinesToSkip = 1)
    void testVerifyLoggedUserPermissions(String projectName, int permBrowse, int permitCreate, int permitEdit) {
        //TODO: more people
        String userName = "user7";
        //String projectName = "Private Project 4";
        String browseProject = "Browse Projects";
        String createIssue = "Create Issues";
        String editIssue = "Edit Issues";

        //login.login();
        driver.navigate().to("https://jira.codecool.codecanvas.hu/login.jsp");
        if(!login.isLoggedIn()) login.loginWithDashboard(userName, System.getenv("password"));

        navigateToPages.goToTheProject(projectName);
        navigateToPages.clickOnGlassDocumentNavItem();
        glassDocumentPage.clickOnPermissionNavItem();

        int result1 = glassDocumentPage.hasCurrentUserPermissionFor(browseProject) ? 1 : 0;
        int result2 = glassDocumentPage.hasCurrentUserPermissionFor(createIssue) ? 1 : 0;
        int result3 = glassDocumentPage.hasCurrentUserPermissionFor(editIssue) ? 1 : 0;

        //assertTrue(result == permit);
        assertTrue(result1 == permBrowse);
        assertTrue(result2 == permitCreate);
        assertTrue(result3 == permitEdit);
    }

    @AfterEach
    public void tearDown() {
        //login.logout();
    }

    @AfterAll
    public static void tearDownAfter() {
        Utils.tearDown();
    }
}
