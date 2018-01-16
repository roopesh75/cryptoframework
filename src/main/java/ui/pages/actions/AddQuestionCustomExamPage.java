package ui.pages.actions;

import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.repo.AddQuestionCustomExamPageRepo;

public class AddQuestionCustomExamPage extends AddQuestionCustomExamPageRepo {
	BrowserDriver driver;

	public AddQuestionCustomExamPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}

	public AddQuestionCustomExamPage editExamDetails() {
		mouseHover(editDetailsLnk());
		editDetailsLnk().click();
		if (questionOrder().getAttribute("innerText").equalsIgnoreCase("Sequenced"))
			questionOrder().click();
		new Select(questionPool()).selectByValue("1");
		saveBtn().click();
		return this;
	}

	public TrainingManagementPage openReturn() {
		retunBtn().click();
		return new TrainingManagementPage(driver);
	}

	public AddQuestionCustomExamPage openSingleResponseQuestion() {
		singleResponseLnk().click();
		return this;
	}

	public AddQuestionCustomExamPage addSingleResponseQuestion() {
		questionTxt().sendKeys("11+11=");
		ansTxt("0").sendKeys("22");
		ansTxt("1").sendKeys("11");
		ansTxt("2").sendKeys("33");
		ansTxt("3").sendKeys("44");
		questionSaveBtn().click();
		cancelBtn().click();
		return this;
	}

	public AddQuestionCustomExamPage expandQuestion() {
		mouseHover(questionDetailBtn());
		questionDetailBtn().click();
		return this;
	}

	public String getNoOfPooledQuestionsToAsk() {
		return noofPooledQuestionsToAsk().getText();
	}

	public String getQuestionPooling() {
		return questionPoolingStatus().getText();

	}

	public String getMandatoryQuestion() {
		return mandatoryLbl().getText();

	}

	public Boolean getQuestionNumber() {
		return questionNumber().isDisplayed();

	}
}
