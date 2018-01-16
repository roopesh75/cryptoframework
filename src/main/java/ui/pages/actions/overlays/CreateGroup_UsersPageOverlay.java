package ui.pages.actions.overlays;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.GroupManagementPage;
import ui.pages.repo.overlays.CreateGroup_UsersPageOverlayRepo;

public class CreateGroup_UsersPageOverlay extends CreateGroup_UsersPageOverlayRepo {

	BrowserDriver driver;

	public CreateGroup_UsersPageOverlay(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public GroupManagementPage createNewGroup(String... parameters) {
		String groupName = parameters[0];
		recordScreenIframeSwitch();
		waitUntilElementDisplayed(continuelnk());
		continuelnk().click();
		popUpIframeViaRecordScreenIframeSwitch();
		groupNameBox().sendKeys(groupName);
		generallnk().click();
		recordScreenIframeSwitch();
		continuelnk().click();
		return new GroupManagementPage(driver);
	}

}
