package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.repo.AddTrainingItemPageRepo;

public class AddTrainingItemPage extends AddTrainingItemPageRepo {
	BrowserDriver driver;

	public AddTrainingItemPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}

	public InstructorLedCoursePage openInstructorLedCourse() {
		recordScreenIframeSwitch();
		instructorLedCourselnk().click();
		return new InstructorLedCoursePage(driver);
	}

	public ControlDocumentTrainingPage openControlDocumentCourse() {
		recordScreenIframeSwitch();
		controlDocumentlnk().click();
		return new ControlDocumentTrainingPage(driver);
	}

	public ICustomExam openCustomExamCourse() {
		recordScreenIframeSwitch();
		customExamlnk().click();
		if (trainingTypesPageContent().getText().contains("Enabled Types")) {
			return new TrainingManagementPage(driver);
		} else
			return new CustomExamPage(driver);
	}

	public String getAddATrainingItemPageContent() {
		return trainingTypesPageContent().getText(); 
		}
		
	public CreateFormCoursePage openFormCourse() {
		recordScreenIframeSwitch();
		formlnk().click();
		return new CreateFormCoursePage(driver);
	}
}
