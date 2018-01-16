package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class AddQuestionCustomExamPageRepo extends UiBase {

	protected BrowserElement editDetailsLnk() {
		return findByXpath("//a[@id='btn-quiz-edit']");
		////span[@class='a-btn-slide-text' and text()='Edit Details']
	}
	
	protected BrowserElement singleResponseLnk() {
		return findByXpath("//button[@class='btn btn-block btn-question-type']");
	}
	protected BrowserElement questionOrder() {
		return findByXpath("//label[@id='questionorder-toggle']");
	}
	protected BrowserElement questionPool() {
		return findById("allow-pooling");
	}
	protected BrowserElement saveBtn() {
		return findByXpath("//button[@class='btn btn-primary']");
	}
	protected BrowserElement noofPooledQuestionsToAsk() {
		return findByXpath("//label[text()='# of Pooled Questions to ask:']/following::div[1]");
	}
	protected BrowserElement questionPoolingStatus() {
		return findByXpath("//label[text()='Question Pooling:']/following::div[1]");
	}
	protected BrowserElement retunBtn() {
		return findByLinkText("Return");
	}
	protected BrowserElement questionTxt() {
		return findById("Question");
	}
	protected BrowserElement ansTxt(String index) {
		return findById("ViewModel_MultipleChoiceQuestionAnswerViewModels_"+index+"__AnswerText");
	}
	protected BrowserElement questionSaveBtn() {
		return findByXpath("//button[@id='btn-save-multiplechoice']");
	}
	protected BrowserElement cancelBtn() {
		return findByXpath("//button[@class='btn btn-default']");
	}
	protected BrowserElement questionDetailBtn() {
		return findByXpath("//a[@title='Question Detail']");
	}
	protected BrowserElement mandatoryLbl() {
		return findByXpath("//div[@class='small']/span");
	}
	protected BrowserElement questionNumber() {
		return findByXpath("//span[@data-label='question-order']");
	}
}
