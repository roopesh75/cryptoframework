package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class DefineAssignmentReportPageRepo extends UiBase{
//
	protected BrowserElement curriculumReportCheckBox() {
		return findById("IsQuickReport3");
	}
	
	protected BrowserElement reportNameTxtBox() {
		return findByName("SFN");
	}
	protected BrowserElement saveReportLnk() {
		return findByLinkText("Save this Report");
	}
	protected BrowserElement trainingCodeSltBox() {
		return findByName("training1");
	}
	protected BrowserElement trainingCodeValueSltBox() {
		return findByName("trainValue1");
	}
	protected BrowserElement runReportLnk() {
		return findByLinkText("Run this Report");
	}
	protected BrowserElement userId() {
		return findByName("userValue1");
	}
	protected BrowserElement assignmentTypeSltBox() {
		return findByName("OneTimeRecur");
	}
	protected BrowserElement assignmentStatusRdBtn() {
		return findByName("ASSIGNMENTPROGRESS");
	}
	protected BrowserElement reasonsRdBtn() {
		return findByName("AssignReason");
	}
	protected BrowserElement reasonsSltBox(String reason) {
		return findByXpath("//td[text()='"+reason+"']");
	}
	protected BrowserElement typesOfTrainingRdBtn() {
		return findByName("RADIOTYPES");
	}
	protected BrowserElement trainingTypeSltBox(String trainingType) {
		return findByXpath("//td[text()='"+trainingType+"']");
	}
	protected BrowserElement columnDisplayReportSltBox(String columnsForDisplay) {
		return findByXpath("//td[text()='"+columnsForDisplay+"']");
	}
	protected BrowserElement dateRangeRdBtn() {
		return findByName("chkDates");
	}
	protected BrowserElement dateStartTxtBox() {
		return findByName("txtStartDate");
	}
	protected BrowserElement dateEndTxtBox() {
		return findByName("txtEndDate");
	}

}
