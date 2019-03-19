package test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pageFactory.CreateIssue;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageFactory.Browse_Issues;
import pageFactory.CreateIssueModal;
import pageFactory.Login;
import util.RunEnvironment;
import util.Utils;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCreateIssue {
    private Login login;
    private CreateIssue createIssue;
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
        login = new Login(driver);
        driver.manage().window().maximize();
        login.login();

    }

    @DisplayName("Test create issue with exist project")
    @Test
    public void testCreateAnIssueByButton() {
        createIssue = new CreateIssue(driver);
        createIssue.createAnIssueByButton("Toucan", "Task", "KRK");
        Assertions.assertTrue(createIssue.isIssueCreateSuccessfully(), "Confirm issue is successfully created.");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createIssue.csv", numLinesToSkip = 1)
    void createIssue(String project, String issueType, String summary) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("create_link"))).click();
        CreateIssueModal modal = new CreateIssueModal(driver);
        modal.setProject(project);
        modal.setIssueType(issueType);
        modal.setSummary(summary);
        CreateIssueModal.PopupMessage popupMessage = modal.submit();
        System.out.println(popupMessage.message);
        assertTrue(popupMessage.message.contains(project));
        assertTrue(popupMessage.message.contains("successfully created"));
        deleteIssue(popupMessage.urlOfCreatedIssue);
    }

    void deleteIssue(String url) {
        driver.get(url);
        Browse_Issues browse = new Browse_Issues(driver);
        browse.deleteSelectedIssue();
    }

    @AfterEach
    public void tearDown() {
        Utils.tearDown();
    }
}
