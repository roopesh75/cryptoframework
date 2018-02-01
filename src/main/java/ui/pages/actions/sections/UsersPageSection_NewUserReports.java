package ui.pages.actions.sections;

import ui.BrowserDriver;
import ui.pages.actions.DefineCompletionReportPage;
import ui.pages.repo.sections.NewUserReports_UsersPageSectionRepo;

public class UsersPageSection_NewUserReports extends NewUserReports_UsersPageSectionRepo {
	BrowserDriver driver;

    public UsersPageSection_NewUserReports(BrowserDriver driver) {
        this.driver = driver;
        setDriver(driver);
    }
    public DefineCompletionReportPage openCompletionReportByUser(){
    	completionReportLnk().click();
    	return new DefineCompletionReportPage(driver);
    }
}
