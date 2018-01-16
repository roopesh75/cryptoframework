package ui.pages.actions.sections;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.repo.sections.Content_GroupPageSectionRepo;

public class GroupManagementPageSection_Content extends Content_GroupPageSectionRepo {
    BrowserDriver driver;

    public GroupManagementPageSection_Content(BrowserDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public String getUserCount() {
       recordScreenIframeSwitch();
        String count=userCount().getText();
        switchToDefaultFrame();
        return count;
    }
}
