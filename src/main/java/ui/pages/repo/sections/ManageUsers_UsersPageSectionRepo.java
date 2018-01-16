package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class ManageUsers_UsersPageSectionRepo extends UiBase {

    //@FindBy(linkText="Manage User Groups")
    protected BrowserElement manageUsrGrplink() {
    	return findByLinkText("Manage User Groups");
    	}

   // @FindBy(linkText="Create a New Group")
    protected BrowserElement createNewGrplink() {
    	return findByLinkText("Create a New Group");
    	}
    protected BrowserElement addUser() {
    	return findByLinkText("Add User");
    	}
    protected BrowserElement securityRoles() {
    	return findByLinkText("Security Roles");
    	}
    //
}
