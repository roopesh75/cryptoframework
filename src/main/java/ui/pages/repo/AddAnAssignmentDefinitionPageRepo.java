package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class AddAnAssignmentDefinitionPageRepo extends UiBase{
	protected BrowserElement trainingCodeTxtBox() {
		return findByName("InputTrainingCode");
	}
	protected BrowserElement searchTrainingBtn() {
		return findById("searchTrainingButton");
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
	protected BrowserElement continueAssignmentBtn() {
		return findById("btnAssignmentAddOrEditConfirm");
	}
	protected BrowserElement continueBtn() {
		return findById("btnContinue");
	}
	protected BrowserElement checkAssignmentStatusBtn() {
		return findByCssSelector("a[id^='btnAssignmentCheckStatus'");
	}
	
//
}
