package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class ControlDocumentTrainingPageRepo extends UiBase {
	protected BrowserElement courseDescriptionBox() {
		return findByName("coursedesc");
	}

	protected BrowserElement courseCommentsBox() {
		return findByName("coursecomments");
	}

	protected BrowserElement saveChanges() {
		return findByLinkText(" Save Changes");
	}

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

	protected BrowserElement courseCommentTxtBox() {
		return findByName("coursecomments");
	}

	protected BrowserElement organizationTxtBox() {
		return findByName("ORGNAME");
	}

	protected BrowserElement save(String index) {
		switch (index) {
		case "1":
			return findByLinkText(" Save");
		case "2":
			return findByLinkText(" Save Changes");
		default:
			return findByLinkText("");
		}
	}

	protected BrowserElement selectLanguagedrp() {
		return findById("ddlLanguage");
	}

	protected BrowserElement onlineRegnradio() {
		return findByXpath("//input[@name='ONLINEREG' and @value='1']");
	}

	protected BrowserElement webAddressTxtbox() {
		return findByName("CDHREF");
	}

	protected BrowserElement testWebAddresslnk() {
		return findByLinkText("Test Web Address");
	}

	protected BrowserElement changeTab() {
		return findByCssSelector("body");
	}

	protected BrowserElement descriptionTxt() {
		return findByName("coursedesc");
	}

	protected BrowserElement selectCategorydrp(int index) {
		if (index == 1) {
			return findByName("CourseCategory");
		} else {
			return findByName("coursecategory");
		}
	}

	protected BrowserElement versionDescTxt() {
		return findByName("versiondesc");
	}

	protected BrowserElement abbreviationTxt() {
		return findByName("courseabbr");
	}

}
