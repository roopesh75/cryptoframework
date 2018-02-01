package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class AddAnAssignmentDefinitionPageRepo extends UiBase{
	protected BrowserElement trainingCodeTxtBox() {
		return findByName("InputTrainingCode");
	}
	protected BrowserElement curriculumTitleTxtBox() {
		return findByName("InputCurriculumTitle");
	}
	protected BrowserElement assignDateTxtBox() {
		return findById("txtAssignDate");
	}
	protected BrowserElement assignmentCategorysltBox() {
		return findById("ddlAssignmentCategory");
	}
	protected BrowserElement suggestedRdBtn() {
		return findById("rbtSuggested");
	}
	
	protected BrowserElement searchTrainingBtn() {
		return findById("searchTrainingButton");
	}
	protected BrowserElement returnLnk() {
		return findByLinkText("Return");
	}
	
	protected BrowserElement searchCurriculumBtn() {
		return findById("searchCurriculumButton");
	}
	protected BrowserElement assignmentTypeDefinitionPanel() {
		return findByXpath("//span[text()='Assignment Type & Definition']");
	}
	protected BrowserElement userIdTxtBox() {
		return findById("UserSearchUserID");
	}
	protected BrowserElement searchUserBtn() {
		return findById("btnUserSearch");
	}
	protected BrowserElement userResultLbl() {
		return findByClass("search-user-result-item");
	}
	protected BrowserElement trnResultLbl() {
		return findByClass("search-training-result-item");
	}
	protected BrowserElement curriculumResultLbl() {
		return findByClass("search-curriculum-result-item");
	}
	
	protected BrowserElement continueAssignmentBtn() {
		return findById("btnAssignmentAddOrEditConfirm");
	}
	protected BrowserElement continueBtn() {
		return findById("btnContinue");
	}
	protected BrowserElement checkAssignmentStatusBtn() {
		return findByCssSelector("a[id^='btnAssignmentCheckStatus'");
	}
	protected BrowserElement selectAssignmentTypeDrpdwn() {
		return findByClass("form-control");
	}
	protected BrowserElement trainingSelectionDrpdwn() {
		return findByName("TrainingTypeId");
	}
	protected BrowserElement addCurriculumBtn() {
		return findById("btnLeft");
	}
	
	
//
}
