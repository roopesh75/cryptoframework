package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class AddNewUserPageRepo extends UiBase {
	protected BrowserElement firstName() {
		return findByName("firstname");
	}

	protected BrowserElement lastName() {
		return findByName("lastname");
	}

	protected BrowserElement employeeNo() {
		return findByName("employeeno");
	}

	protected BrowserElement password() {
		return findByName("Password");
	}

	protected BrowserElement confirmPassword() {
		return findByName("confirmpassword");
	}

	protected BrowserElement save() {
		return findByLinkText(" Save");
	}

	protected BrowserElement saveChanges() {
		return findByLinkText(" Save Changes");
	}

	protected BrowserElement viewOrganizationTree() {
		return findByCssSelector("a[title='View Organization Tree']");
	}

	protected BrowserElement continueAfterSelectLnk() {
		return findByLinkText("Continue");
	}
	protected BrowserElement homePhone() {
		return findByName("homephone");
	}

	
	// 

}
