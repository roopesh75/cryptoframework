package ui.pages.actions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.repo.DefineEventLogReportPageRepo;

public class DefineEventLogReportPage extends DefineEventLogReportPageRepo {

	BrowserDriver driver;
	AdminNav_CommonSection adminNav_CommonSection;

	public DefineEventLogReportPage(BrowserDriver driver) {
		this.driver = driver;
		adminNav_CommonSection = new AdminNav_CommonSection(driver);
		setDriver(driver);
	}

	public DefineEventLogReportPage selectEvent(String... parameters) {
		recordScreenIframeSwitch();
		Select selectedEvent = new Select(selectEventdrp());
		Actions ac = new Actions(driver);
		for (int i = 0; i < parameters.length; i++) {
			selectedEvent.selectByValue(parameters[i]);
			ac.keyDown(Keys.CONTROL);
		}
		return this;
	}

	public DefineEventLogReportPage selectAllEvents() {
		recordScreenIframeSwitch();
		eventRadioBtn(0).click();
		return this;
	}

	public String isTemporaryReportChecked() {
		recordScreenIframeSwitch();
		return tempReportRadioBtn().getAttribute("checked");
	}

	public DefineEventLogReportPage conditionForReportRow1(String... parameters) {
		recordScreenIframeSwitch();
		new Select(selectUserType1()).selectByVisibleText(parameters[0]);
		new Select(selectOperatorType1()).selectByVisibleText(parameters[1]);
		usrBox1().sendKeys(parameters[2]);
		return this;
	}

	public String getOperatorRow1() {
		recordScreenIframeSwitch();
		return new Select(selectOperatorType1()).getFirstSelectedOption().getText();
	}

	public DefineEventLogReportPage fromDate(String... parameters) {
		recordScreenIframeSwitch();
		startDateTxtBox().sendKeys(parameters[0]);
		return this;
	}

	public DefineEventLogReportPage toDate(String... parameters) {
		recordScreenIframeSwitch();
		endDateTxtBox().clear();
		endDateTxtBox().sendKeys(parameters[0]);
		dateRangeRadioBtn(1).click();
		return this;
	}

	public DefineEventLogReportPage addUsr(String value) {
		recordScreenIframeSwitch();
		usrBox().sendKeys(value);
		switchToDefaultFrame();
		return this;
	}

	public UsersPage openUsersPage() {
		adminNav_CommonSection.openUsersPage();
		return new UsersPage(driver);
	}

	public EventLogReportPage openRunThisReport() {
		recordScreenIframeSwitch();
		runThisReportlnk().click();
		return new EventLogReportPage(driver);
	}

	public boolean isDefineEventLogReportPage() {
		recordScreenIframeSwitch();
		return runThisReportlnk().isDisplayed();
	}

	public String getEndDate() {
		recordScreenIframeSwitch();
		return endDateTxtBox().getAttribute("value");
	}
	public EventLogReportPage runSavedReport(String...parameters) {
		recordScreenIframeSwitch();
		savedReportRadioBtn().click();
		reportNameTxtBox().sendKeys(parameters[0]);
		saveAndRunReportLnk().click();
		return new EventLogReportPage(driver);	
	}

}
