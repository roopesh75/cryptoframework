package ui.pages.actions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.overlays.GroupManagementPage_AddExcludeUsersDirectlyOverlay;
import ui.pages.actions.overlays.GroupUsers_GroupPageOverlay;
import ui.pages.actions.sections.GroupManagementPageSection_Content;
import ui.pages.actions.sections.GroupManagementPageSection_GroupNavigation;
import ui.pages.repo.GroupPageRepo;

public class GroupManagementPage extends GroupPageRepo {
    BrowserDriver driver;
    GroupManagementPageSection_Content content_groupManagementPage;
    GroupManagementPageSection_GroupNavigation groupNavigation_groupManagementPage;
    GroupUsers_GroupPageOverlay groupUsers_GroupPageOverlay;
    
    public GroupManagementPage(BrowserDriver driver) {
        this.driver = driver;
        setDriver(driver);
        content_groupManagementPage = new GroupManagementPageSection_Content(driver);
        groupNavigation_groupManagementPage = new GroupManagementPageSection_GroupNavigation(driver);
        groupUsers_GroupPageOverlay=new GroupUsers_GroupPageOverlay(driver);
        PageFactory.initElements(driver, this);
    }

    public EditGroupCriteriaPage openEditGroupCriteria() {
        groupNavigation_groupManagementPage.openEditGroupCriteria();
        return new EditGroupCriteriaPage(driver);
    }

    public GroupManagementPage refreshGroupMembershipCriteria() {
        return groupNavigation_groupManagementPage.openGroupMembershipCriteria();
    }

    public GroupManagementPage openGroupUsers() {
        groupNavigation_groupManagementPage.openGroupUsers();
        return this;
    }

    public GroupManagementPage closeGroupUsersOverlay() {
        new GroupUsers_GroupPageOverlay(driver).closeGroupOverlay();
        return this;
    }

    public boolean isGroupPage() {
        return groupNavigation_groupManagementPage.isGroupPage();
    }

    public List<String> getGroupUsers() {
        openGroupUsers().getGroupUsers();
        return new ArrayList<>();
    }


    public boolean hasGroupUsersOverlay() {
        return groupUsers_GroupPageOverlay.hasGroupUsersOverlay();
    }

    public String getUserCount() {
       return content_groupManagementPage.getUserCount();
    }

    public String getUserCountInGroupUsers() {
       return groupUsers_GroupPageOverlay.getUserCountInGroupUsers();
    }


	public GroupManagementPage_AddExcludeUsersDirectlyOverlay openExcludeUser(String userName) {
		groupNavigation_groupManagementPage.openExcludeUserDirectly(userName);
		return new GroupManagementPage_AddExcludeUsersDirectlyOverlay(driver,userName);		
	}

	public GroupMembershipHistoryPage openGroupMemberShipHistory() {
		groupNavigation_groupManagementPage.openGroupMemberShipHistory();
		return new GroupMembershipHistoryPage(driver);
		
	}

	public GroupManagementPage_AddExcludeUsersDirectlyOverlay openAddUserDirectly(String userName) {
		return groupNavigation_groupManagementPage.openAddUserDirectly(userName);
		
	}

	public GroupManagementPage removeUser() {
		recordScreenIframeSwitch();
		removeLnk().click();
		removeUserLnk().click();
		return new GroupManagementPage(driver);
	}
	public String getUserAddedTxt(int index){
		return UserAddTbl(index).getText();
	}

}
