package ui.pages.repo;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ui.BrowserElement;
import ui.BrowserElementImpl;
import ui.UiBase;

public class EventLogReportPageRepo extends UiBase {
	
	protected BrowserElement eventLogTopRow() {
		return findByXpath("//tr[@class='EduText']");
	}
	protected List<WebElement> eventLogRows() {
		List<WebElement> eventLogRows=getDriver().findElement(By.id("tblReport")).findElements(By.className("EduText"));
		eventLogRows.remove(0);
		eventLogRows.remove(eventLogRows.size()-1);
		return eventLogRows;
	}
	protected BrowserElement dateTimeSort() {
		return findByXpath("//a[@title='Sort By Date / Time']");
	}
	protected BrowserElement topRowDateTimeLbl() {
		return findByXpath("//*[@id='tblReport']/tbody/tr[3]/td[2]/span");
	}
	protected BrowserElement rowsReturned() {
		return findByXpath("//span[@id='RowsReturned']");
	}
	protected BrowserElement reportDrpdwn() {
		return findByName("FilterID");
	}
	protected BrowserElement selectGotoPageDrpdwn() {
		return findByName("SelectGotoPage");
	}
	 protected List<WebElement> returnLogsMenu(){
	        return getDriver().findElements(By.linkText("Return to Logs Menu"));
	    }

}
//
