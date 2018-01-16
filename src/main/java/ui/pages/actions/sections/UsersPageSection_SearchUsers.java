package ui.pages.actions.sections;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.UserManagementPage;
import ui.pages.repo.sections.SearchUsers_UsersPageSectionRepo;

public class UsersPageSection_SearchUsers extends SearchUsers_UsersPageSectionRepo {
	BrowserDriver driver;

	public UsersPageSection_SearchUsers(BrowserDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }
    public UserManagementPage searchUser(String userName){
    	recordScreenIframeSwitch();
    	userTxtBox().sendKeys(userName);
    	searchLnk().click();
    	return new UserManagementPage(driver);
    }
	public UserManagementPage searchGroup(String groupName) {
		recordScreenIframeSwitch();
		grpNameBox().sendKeys(groupName);
    	searchLnk(1).click();
		return new UserManagementPage(driver);
	}
}
