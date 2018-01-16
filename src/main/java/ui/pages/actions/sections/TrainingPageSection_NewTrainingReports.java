package ui.pages.actions.sections;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.DefineAssignmentReportPage;
import ui.pages.actions.DefineCompletionReportPage;
import ui.pages.repo.sections.TrainingPageSection_NewTrainingReportsRepo;

public class TrainingPageSection_NewTrainingReports extends TrainingPageSection_NewTrainingReportsRepo {
	BrowserDriver driver;

	public TrainingPageSection_NewTrainingReports(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}
	
	public DefineCompletionReportPage openCompletionReportByTraining(){
		recordScreenIframeSwitch();
		completionReportTraininglnk().click();
		return new DefineCompletionReportPage(driver);
	}
	public DefineAssignmentReportPage openAssignmentReportByTraining(){
		recordScreenIframeSwitch();
		assignmentReportTraininglnk().click();
		return new DefineAssignmentReportPage(driver);
	}

}
