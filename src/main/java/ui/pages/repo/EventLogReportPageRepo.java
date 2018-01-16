package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class EventLogReportPageRepo extends UiBase {
	
	protected BrowserElement eventLogTopRow() {
		return findByXpath("//tr[@class='EduText']");
	}
	
	protected BrowserElement dateTimeSort() {
		return findByXpath("//a[@title='Sort By Date / Time']");
	}
	protected BrowserElement topRowDateTimeLbl() {
		return findByXpath("//*[@id='tblReport']/tbody/tr[3]/td[2]/span");
	}
}
//
