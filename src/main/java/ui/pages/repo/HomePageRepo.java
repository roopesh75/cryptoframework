package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class HomePageRepo extends UiBase {

	protected BrowserElement ansQuestionsLnk() {
		return findByLinkText("Answer Questions");
	}

	protected BrowserElement firstQuestionLnk() {
		return findByLinkText("What was the name of your first school?");
	}

	protected BrowserElement firstAnswerTxtBox() {
		return findById("ViewModel_SecurityQuestionAnswersForPasswordResetCommand_SecurityQuestionAnswer");
	}

	protected BrowserElement panelTitleTxt() {
		return findByClass("panel-title");
	}
	protected BrowserElement saveBtn() {
		return findByXpath("//button[text()='Save']");
	}
	protected BrowserElement okBtn() {
		return findByXpath("//button[text()='OK']");
	}
	protected BrowserElement continueBtn() {
		return findByXpath("//button[text()='Continue']");
	}
}
