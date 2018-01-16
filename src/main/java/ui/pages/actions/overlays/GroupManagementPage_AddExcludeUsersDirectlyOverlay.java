package ui.pages.actions.overlays;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.GroupManagementPage;
import ui.pages.repo.overlays.GroupManagementPage_AddUsersDirectlyOverlayRepo;

public class GroupManagementPage_AddExcludeUsersDirectlyOverlay extends GroupManagementPage_AddUsersDirectlyOverlayRepo {

	BrowserDriver driver;
	String userId;
	public GroupManagementPage_AddExcludeUsersDirectlyOverlay(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}
	public GroupManagementPage_AddExcludeUsersDirectlyOverlay(BrowserDriver driver,String userId) {
		this.driver = driver;
		this.userId=userId;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public GroupManagementPage addExcludeUser() {
		popUpIframeViaRecordScreenIframeSwitch();
		userIdTxtBox().sendKeys(userId);	
		searchLnk().click();
		userNameChkBox(userId).click();
		recordScreenIframeSwitch();
		addUsersLnk().click();
		return new GroupManagementPage(driver);
	}

}