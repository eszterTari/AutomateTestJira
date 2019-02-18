package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateIssueModal {

    @FindBy(xpath="//*[@id='aui-flag-container']//a[@class='issue-created-key issue-link']")
    WebElement successPopup;

    public CreateIssueModal() {

    }
}
