package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.repo.QuizPageRepo;

public class QuizPage extends QuizPageRepo {
	BrowserDriver driver;

	public QuizPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}

	public AddQuestionCustomExamPage addQuiz() {
		addQuizLnk().click();
		saveQuizBtn().click();
		return new AddQuestionCustomExamPage(driver);
	}
	public AddQuestionCustomExamPage openInitialRevision() {
		initialRevisionLnk().click();
		return new AddQuestionCustomExamPage(driver);
	}

	public String getQuizRevisionInfo() {
		return quizInfoTxt().getAttribute("outerHTML");
	}
	public String getQuizRevisionPageContent() {
		return quizRevisionPage().getText();
	}
}
