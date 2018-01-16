package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.repo.ToDoPageRepo;

public class ToDoPage extends ToDoPageRepo {

	BrowserDriver driver;

	public ToDoPage(BrowserDriver driver) {
		this.driver = driver;

		setDriver(driver);
	}
	public ToDoPage openTrainingItem(String trainingName) {
		trainingItem(trainingName).click();
		return this;
	}
	public ToDoPage openClassInformation() {
		classInformation().click();
		return this;
	}
	public ToDoPage electronicallySign(String...parameters) {
		userIDtxt().sendKeys(parameters[0]);
		pwdtxt().clear();
		pwdtxt().sendKeys(parameters[1]);
		electronicallySignlnk().click();
		ceContinuebtn().click();
		return this;
	}
	public String getToDoPageContent() {
		return toDoPagecontent().getText();	
	}
	public String getPendingReports() {
		return trainingsPendingTxt().getText();
	}
}
