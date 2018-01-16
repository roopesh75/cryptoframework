package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class CreateFormCoursePageRepo extends UiBase{

	protected BrowserElement codeTxtbox() {
		return findByName("coursecode");
	}
	protected BrowserElement titleTxtbox() {
		return findByName("Coursetitle");
	}
	protected BrowserElement viewOrganizationTree() {
		return findByCssSelector("a[title='View Organization Tree']");
	}
	protected BrowserElement continueAfterSelectLnk() {
		return findByLinkText("Continue");
	}
	protected BrowserElement approveDateTxtbox() {
		return findByName("ApproveDate");
	}
	protected BrowserElement effectiveDateTxtbox() {
		return findByName("EffectiveDate");
	}
	protected BrowserElement save() {
		return findByLinkText(" Save");
	}
	protected BrowserElement selectLanguagedrp() {
		return findById("ddlLanguage");
	}
	protected BrowserElement onlineRegnradio() {
		return findByXpath("//input[@name='ONLINEREG' and @value='1']");
	}
}
