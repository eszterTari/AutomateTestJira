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

        if(!projectSettingsPage.isComponentExist(inputName))
            projectSettingsPage.clickOnAddComponent();
        else {
            projectSettingsPage.setComponentNameInput("");
            projectSettingsPage.setComponentAssigneeInput("");
        }

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
        String userName = "user7";
        String browseProject = "Browse Projects";
        String createIssue = "Create Issues";
        String editIssue = "Edit Issues";

        driver.navigate().to("https://jira.codecool.codecanvas.hu/login.jsp");
        if(!login.isLoggedIn()) login.loginWithDashboard(userName, System.getenv("password"));

        navigateToPages.goToTheProject(projectName);
        navigateToPages.clickOnGlassDocumentNavItem();
        glassDocumentPage.clickOnPermissionNavItem();

        int result1 = glassDocumentPage.hasCurrentUserPermissionFor(browseProject) ? 1 : 0;
        int result2 = glassDocumentPage.hasCurrentUserPermissionFor(createIssue) ? 1 : 0;
        int result3 = glassDocumentPage.hasCurrentUserPermissionFor(editIssue) ? 1 : 0;

        assertTrue(result1 == permBrowse);
        assertTrue(result2 == permitCreate);
        assertTrue(result3 == permitEdit);
    }

    @AfterAll
    public static void tearDownAfter() {
        Utils.tearDown();
    }
}
