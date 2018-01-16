package ui.pages.actions;

import org.openqa.selenium.support.ui.Select;
import ui.BrowserDriver;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.repo.DefineEventLogReportPageRepo;

public class DefineEventLogReportPage extends DefineEventLogReportPageRepo {

	BrowserDriver driver;
	AdminNav_CommonSection adminNav_CommonSection;

	public DefineEventLogReportPage(BrowserDriver driver) {
		this.driver = driver;
		adminNav_CommonSection= new AdminNav_CommonSection(driver);
		setDriver(driver);
	}

	 
    public DefineEventLogReportPage selectEvent(String value) {
    	recordScreenIframeSwitch();
        new Select(selectEventdrp()).selectByValue(value);
        return this;
    }
    public DefineEventLogReportPage addUsr(String value) {
    	recordScreenIframeSwitch();
        usrBox().sendKeys(value);
        switchToDefaultFrame();
        return this;
    }
    public UsersPage openUsersPage(){
		adminNav_CommonSection.openUsersPage();
		return new UsersPage(driver);
	}
    public EventLogReportPage openRunThisReport(){
    	recordScreenIframeSwitch();
        runThisReportlnk().click();
        return new EventLogReportPage(driver);
    }
	public boolean isDefineEventLogReportPage() {
		recordScreenIframeSwitch();
		return runThisReportlnk().isDisplayed();
	}

}
