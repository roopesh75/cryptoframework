package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class DefineCompletionReportPageRepo extends UiBase {
	protected BrowserElement trngCodeTxtbox() {
		return findByName("trainValue1");
	}
	protected BrowserElement userIdTxtbox() {
		return findByName("userValue1");
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
