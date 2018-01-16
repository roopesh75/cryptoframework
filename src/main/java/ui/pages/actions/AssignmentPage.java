package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.actions.sections.AssignmentPageSection_ManageAssignment;
import ui.pages.actions.sections.AssignmentPageSection_NewAssignmentReport;
import ui.pages.repo.AssignmentPageRepo;

public class AssignmentPage extends AssignmentPageRepo {
	BrowserDriver driver;
	AssignmentPageSection_ManageAssignment assignmentPageSection_ManageAssignment;
	AssignmentPageSection_NewAssignmentReport assignmentPageSection_NewAssignmentReport;
	AdminNav_CommonSection adminNav_CommonSection;

	public AssignmentPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		assignmentPageSection_ManageAssignment = new AssignmentPageSection_ManageAssignment(driver);
		assignmentPageSection_NewAssignmentReport = new AssignmentPageSection_NewAssignmentReport(driver);
		adminNav_CommonSection = new AdminNav_CommonSection(driver);
	}

	public AddAnAssignmentDefinitionPage openAddAssignmentDefinition() {
		recordScreenIframeSwitch();
		assignmentPageSection_ManageAssignment.openAddAssignmentDefinition();
		return new AddAnAssignmentDefinitionPage(driver);

	}

	public LoginPage logOut() {
		return adminNav_CommonSection.logOut();
	}
}
