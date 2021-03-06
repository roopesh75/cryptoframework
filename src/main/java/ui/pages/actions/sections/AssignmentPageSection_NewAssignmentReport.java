package ui.pages.actions.sections;

import ui.BrowserDriver;
import ui.pages.actions.DefineAssignmentReportPage;
import ui.pages.repo.sections.NewAssignmentReport_AssignmentPageSectionRepo;

public class AssignmentPageSection_NewAssignmentReport extends NewAssignmentReport_AssignmentPageSectionRepo {
	BrowserDriver driver;

	public AssignmentPageSection_NewAssignmentReport(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);

	}
	public DefineAssignmentReportPage openAssignmentReportByTraining(){
		recordScreenIframeSwitch();
		assignmentReportTraininglnk().click();
		return new DefineAssignmentReportPage(driver);
	}
}
