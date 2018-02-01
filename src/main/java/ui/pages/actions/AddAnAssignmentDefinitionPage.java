package ui.pages.actions;

import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.repo.AddAnAssignmentDefinitionPageRepo;
import ui.utils.Tools;

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
		waitForAjax("waiting for check Assignment Button");
		checkAssignmentStatusBtn().click();
		return new AssignmentManagementPage(driver);
	}

	public AddAnAssignmentDefinitionPage addAnAssignmentDefinition(String...parameters) {
		new Select(selectAssignmentTypeDrpdwn()).selectByValue(parameters[0]);
		new Select(trainingSelectionDrpdwn()).selectByValue(parameters[1]);
		if (parameters[4].equalsIgnoreCase("addReasonAndStartDate")){
			assignDateTxtBox().clear();
			assignDateTxtBox().sendKeys(parameters[5]);
			trainingCodeTxtBox().click();
			new Select(assignmentCategorysltBox()).selectByVisibleText(parameters[6]);
		}
		else if (parameters[4].equalsIgnoreCase("elective")){
			suggestedRdBtn().click();	
		}
		if (parameters[1].equalsIgnoreCase("2")) {
			curriculumTitleTxtBox().sendKeys(parameters[2]);
			searchCurriculumBtn().click();
			waitForAjax("waiting for assignment");
			curriculumResultLbl().click();
			addCurriculumBtn().click();
		}
		else {
		trainingCodeTxtBox().sendKeys(parameters[2]);
		searchTrainingBtn().click();
		trnResultLbl().click();
		}
		userIdTxtBox().sendKeys(parameters[3]);
		searchUserBtn().click();
		userResultLbl().click();
		continueAssignmentBtn().click();
		waitForAjax("waiting for assignment");
		
	
		return new AddAnAssignmentDefinitionPage(driver);
	}
	public AssignmentManagementPage continueAndCheckAssignment(){
		continueBtn().click();
		waitForAjax("waiting for assignment status button");
		waitForAjax("waiting for check assignment status");
		checkAssignmentStatusBtn().click();
		return new AssignmentManagementPage(driver);
	}
	public String getassignmentTypeDefinition() {
		return assignmentTypeDefinitionPanel().getText();
		
	}

	public boolean isaddAnAssignmentDefinitionPage() {
		// TODO Auto-generated method stub
		return searchUserBtn().isDisplayed();
	}
	public AssignmentPage returnToAssignmentPage(){
		recordScreenIframeSwitch();
		returnLnk().click();
		return new AssignmentPage(driver);
	}
}
