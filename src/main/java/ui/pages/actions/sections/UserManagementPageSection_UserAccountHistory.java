package ui.pages.actions.sections;

import ui.BrowserDriver;
import ui.pages.repo.sections.UserAccountHistory_UserManagementPageSectionRepo;

public class UserManagementPageSection_UserAccountHistory extends UserAccountHistory_UserManagementPageSectionRepo {
	BrowserDriver driver;

	public UserManagementPageSection_UserAccountHistory(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}

	public String getCreatedOn() {
		recordScreenIframeSwitch();
		return createdOn().getText();
	}

	public String getCreatedBy() {
		recordScreenIframeSwitch();
		return createdBy().getText();
	}

	public String getPropertyEdited(int tableId) {
		recordScreenIframeSwitch();
		return propertyEdited(tableId).getText();
	}

	public String getOldValue(int tableId) {
		recordScreenIframeSwitch();
		return oldValue(tableId).getText();
	}

	public String getNewValue(int tableId) {
		recordScreenIframeSwitch();
		return newValue(tableId).getText();
	}
}
