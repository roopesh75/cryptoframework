package ui.pages.actions.sections;

import ui.BrowserDriver;
import ui.pages.actions.AddAnAssignmentDefinitionPage;
import ui.pages.repo.sections.ManageAssignment_AssignmentPageSectionRepo;

public class AssignmentPageSection_ManageAssignment extends ManageAssignment_AssignmentPageSectionRepo{
	BrowserDriver driver;

	public AssignmentPageSection_ManageAssignment(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);

	}

	public AddAnAssignmentDefinitionPage openAddAssignmentDefinition() {
		recordScreenIframeSwitch();
		addAssignmentDefinitionLnk().click();
		return new AddAnAssignmentDefinitionPage(driver);	
	}
}
