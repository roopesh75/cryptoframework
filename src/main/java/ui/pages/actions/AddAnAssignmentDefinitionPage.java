package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.repo.AddAnAssignmentDefinitionPageRepo;

public class AddAnAssignmentDefinitionPage extends AddAnAssignmentDefinitionPageRepo {
	BrowserDriver driver;

	public AddAnAssignmentDefinitionPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}

	public AssignmentManagementPage addAssignment(String...parameters) {
		trainingCodeTxtBox().sendKeys(parameters[0]);
		searchTrainingBtn().click();
		trnResultLbl().click();
		userIdTxtBox().sendKeys(parameters[1]);
		searchUserBtn().click();
		userResultLbl().click();
		continueAssignmentBtn().click();
		waitForAjax("waiting for assignment");
		continueBtn().click();
		checkAssignmentStatusBtn().click();
		return new AssignmentManagementPage(driver);
	}


}
