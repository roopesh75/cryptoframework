package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.repo.AssignmentManagementPageRepo;

public class AssignmentManagementPage extends AssignmentManagementPageRepo {
	BrowserDriver driver;

	public AssignmentManagementPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}
	public AssignmentPage returnToAssignmentPage(){
		recordScreenIframeSwitch();
		returnLnk().click();
		return new AssignmentPage(driver);
	}
}
