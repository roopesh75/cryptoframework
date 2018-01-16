package ui.pages.actions.sections;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.EditGroupCriteriaPage;
import ui.pages.actions.GroupManagementPage;
import ui.pages.actions.GroupMembershipHistoryPage;
import ui.pages.actions.overlays.GroupManagementPage_AddExcludeUsersDirectlyOverlay;
import ui.pages.actions.overlays.GroupUsers_GroupPageOverlay;
import ui.pages.repo.sections.GroupNavigation_GroupPageSectionRepo;

public class GroupManagementPageSection_GroupNavigation extends GroupNavigation_GroupPageSectionRepo {
	BrowserDriver driver;

	public GroupManagementPageSection_GroupNavigation(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public EditGroupCriteriaPage openEditGroupCriteria() {
		recordScreenIframeSwitch();
		editGroupCriteriaLnk().click();
		return new EditGroupCriteriaPage(driver);
	}

	public GroupManagementPage openGroupMembershipCriteria() {
		staticWait(2000);
		recordScreenIframeSwitch();
		groupMembershipCriteriaLnk().click();
		return new GroupManagementPage(driver);
	}

	public GroupUsers_GroupPageOverlay openGroupUsers() {
		recordScreenIframeSwitch();
		groupUsersLnk().click();
		return new GroupUsers_GroupPageOverlay(driver);
	}

	public boolean isGroupPage() {
		recordScreenIframeSwitch();
		return editGroupCriteriaLnk().isDisplayed();
	}

	public GroupManagementPage_AddExcludeUsersDirectlyOverlay openAddUserDirectly(String userName) {
		recordScreenIframeSwitch();
		addUsersDirectlyLnk().click();
		return new GroupManagementPage_AddExcludeUsersDirectlyOverlay(driver,userName);

	}
	public GroupManagementPage_AddExcludeUsersDirectlyOverlay openExcludeUserDirectly(String userName) {
		recordScreenIframeSwitch();
		excludeUsersDirectlyLnk().click();
		return new GroupManagementPage_AddExcludeUsersDirectlyOverlay(driver,userName);

	}

	public GroupMembershipHistoryPage openGroupMemberShipHistory() {
		recordScreenIframeSwitch();
		grpMembershipHstryLnk().click();
		return new GroupMembershipHistoryPage(driver);
	}

}
