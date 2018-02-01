package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class QuizPageRepo extends UiBase{
	protected BrowserElement addQuizLnk() {
		return findByPartialLinkText("Add Quiz");
	}
	protected BrowserElement saveQuizBtn() {
		return findById("btn-add-quiz");
	}
	protected BrowserElement quizInfoTxt() {
		return findById("body-results");
	}
	protected BrowserElement initialRevisionLnk() {
		return findByPartialLinkText("Initial Quiz");
	}
	protected BrowserElement quizRevisionPage() {
		return findByTagName("body");
	}
}
