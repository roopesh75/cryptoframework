package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class DefineCompletionReportPageRepo extends UiBase {
	protected BrowserElement trngCodeTxtbox() {
		return findByName("trainValue1");
	}
	
	protected BrowserElement selectUserType() {
		return findByName("EnabledUser");
	}
	protected BrowserElement selectUserAttr1() {
		return findByName("user1");
	}
	protected BrowserElement selectUserOperator1() {
		return findByName("operatoru1");
	}
	protected BrowserElement userIdValue1Txtbox() {
		return findByName("userValue1");
	}
	protected BrowserElement selectUserAttr2() {
		return findByName("user2");
	}
	protected BrowserElement selectUserOperator2() {
		return findByName("operatoru2");
	}
	protected BrowserElement userIdValue2Txtbox() {
		return findByName("userValue2");
	}	
	protected BrowserElement runReportLnk() {
		return findByLinkText("Run this Report");
	}
	
	
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


}
