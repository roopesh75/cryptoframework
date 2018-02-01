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

	public AddQuestionCustomExamPage addSingleResponseQuestion(boolean sequence) {
		questionTxt().sendKeys("11+11=");
		if(sequence==true) randomizedSequenceRadio().click();
		ansTxt("0").sendKeys("22");
		ansTxt("1").sendKeys("11");
		ansTxt("2").sendKeys("33");
		ansTxt("3").sendKeys("44");
		questionSaveBtn().click();
		cancelBtn().click();
		waitForAjax("waiting single response screen to disappear");
		return this;
	}
	public AddQuestionCustomExamPage addMultipleResponseQuestion(boolean sequence) {	
		multipleResponseLnk().click();
		questionTxt().sendKeys("Water has following Properties:");
		if(sequence==true) randomizedSequenceRadio().click();
		ansMultipleResponseTxt("0").sendKeys("liquid");
		ansChkBox("0").click();
		ansMultipleResponseTxt("1").sendKeys("colored");
		ansMultipleResponseTxt("2").sendKeys("gas");
		ansMultipleResponseTxt("3").sendKeys("solid");	
		waitForAjax("waiting single response screen to disappear");
		return this;
	}
	public boolean getCorrectAnswerStatus(int index){
		return ansChkBox(index+"").isSelected();
	}
	public AddQuestionCustomExamPage saveQuestion(){
		questionMultipleSaveBtn().click();
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
	public String getQuizQuestionPanel() {
		return quizQuestionTxt().getText();

	}
	
	public String getMandatoryQuestion() {
		return mandatoryLbl().getText();
	}

	public Boolean getQuestionNumber() {
		return questionNumber().isDisplayed();

	}
}
