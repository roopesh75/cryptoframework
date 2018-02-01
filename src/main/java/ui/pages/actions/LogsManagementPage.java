package ui.pages.actions;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.repo.LogsManagementPageRepo;

public class LogsManagementPage extends LogsManagementPageRepo {
	BrowserDriver driver;

	public LogsManagementPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}
	public String getLatestUpgradeInfo(int rowIndex){
		recordScreenIframeSwitch();
		return latestUpgradeInfoTxt(rowIndex).getText();
	}
	public LogsPage returnToLogsMenu(){
		recordScreenIframeSwitch();
		returnLogMenuLnk().click();
		return new LogsPage(driver);
	}

}
