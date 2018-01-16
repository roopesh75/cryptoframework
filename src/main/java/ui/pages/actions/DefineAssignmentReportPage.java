package ui.pages.actions;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.repo.DefineAssignmentReportPageRepo;

public class DefineAssignmentReportPage extends DefineAssignmentReportPageRepo {
	BrowserDriver driver;

	public DefineAssignmentReportPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}


	public TrainingManagementPage saveCompletionReport(String...parameters) {
		recordScreenIframeSwitch();
		curriculumReportCheckBox().click();
		reportNameTxtBox().sendKeys(parameters[0]);
		saveReportLnk().click();
		return new TrainingManagementPage(driver);	
	}
	public TrainingManagementPage runReportForCurriculum(String... parameters) {
		recordScreenIframeSwitch();
		trainingCodeSltBox().sendKeys(parameters[0]);
		trainingCodeValueSltBox().sendKeys(parameters[1]);
		runReportLnk().click();
		return new TrainingManagementPage(driver);	
	}
}
