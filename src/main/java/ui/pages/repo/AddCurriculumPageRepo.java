package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class AddCurriculumPageRepo extends UiBase {
	protected BrowserElement curriculumCodetxt() {
		return findByName("CurriculumCode");
	} 
	protected BrowserElement curriculumTitletxt() {
		return findByName("CurriculumTitle");
	} 
	protected BrowserElement homeIcon() {
		return findByClass("icon-home");
	} 
	protected BrowserElement homeOrganization(String homeOrganization) {
		return findByLinkText(homeOrganization);
	} 
	protected BrowserElement webAddresstxt() {
		return findByName("CurriculumManageForAdd.WebAddress");
	}
	protected BrowserElement descriptiontxt() {
		return findByName("CurriculumDescription");
	}
	protected BrowserElement savebtn() {
		return findById("NextButton");
	}
}
