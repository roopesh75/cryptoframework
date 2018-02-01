package ui.pages.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.repo.DefineAssignmentReportPageRepo;

public class DefineAssignmentReportPage extends DefineAssignmentReportPageRepo {
	BrowserDriver driver;

	public DefineAssignmentReportPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isDefineAssignmentReportPage(){
		recordScreenIframeSwitch();
		return curriculumReportCheckBox().isDisplayed();
	}
	public TrainingManagementPage saveCompletionReport(String...parameters) {
		recordScreenIframeSwitch();
		curriculumReportCheckBox().click();
		reportNameTxtBox().sendKeys(parameters[0]);
		saveReportLnk().click();
		return new TrainingManagementPage(driver);	
	}
	public TrainingManagementPage runReportForTrainingOrCurriculum(String... parameters) {
		recordScreenIframeSwitch();
		trainingCodeSltBox().sendKeys(parameters[0]);
		trainingCodeValueSltBox().sendKeys(parameters[1]);
		if(parameters.length==3) {
			userId().sendKeys(parameters[2]);
		}
		
		if(parameters.length>3) {
			userId().sendKeys(parameters[2]);
			new Select(assignmentTypeSltBox()).selectByVisibleText(parameters[3]);
			assignmentStatusRdBtn().click();
			reasonsRdBtn().click();
			IfCatsViaRecordScreenIframeSwitch();
			reasonsSltBox(parameters[4]).click();
			recordScreenIframeSwitch();
			typesOfTrainingRdBtn().click();
			IfTypesViaRecordScreenIframeSwitch();
			trainingTypeSltBox(parameters[5]).click();
			recordScreenIframeSwitch();	
			ColumnsViaRecordScreenIframeSwitch();
			columnDisplayReportSltBox(parameters[6]).click();
			recordScreenIframeSwitch();
			dateRangeRdBtn().click();
			dateStartTxtBox().sendKeys(parameters[7]);
			dateEndTxtBox().sendKeys(parameters[8]);
		}
		runReportLnk().click();
		return new TrainingManagementPage(driver);	
	}
	
}
