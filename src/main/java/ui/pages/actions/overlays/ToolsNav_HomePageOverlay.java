package ui.pages.actions.overlays;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.LogsPage;
import ui.pages.actions.OptionsPage;
import ui.pages.actions.TrainingPage;
import ui.pages.actions.UsersPage;
import ui.pages.repo.overlays.ToolsNav_HomePageOverlayRepo;

public class ToolsNav_HomePageOverlay extends ToolsNav_HomePageOverlayRepo {
	BrowserDriver driver;

	public ToolsNav_HomePageOverlay(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public UsersPage openUsersPage() {
		waitForAjax("Wait for UsersOverlay");
		userslnk().click();
		return new UsersPage(driver);
	}

	public TrainingPage openTrainingPage() {
		waitForAjax("Wait for TrainingOverlay");
		traininglnk().click();
		return new TrainingPage(driver);

	}

	public OptionsPage openOptionsPage() {
		waitForAjax("Wait for UsersOverlay");
		optionslnk().click();
		return new OptionsPage(driver);
	}

	public LogsPage openLogsPage() {
		waitForAjax("Wait for UsersOverlay");
		logslnk().click();
		return new LogsPage(driver);
	}

	public String getOverlayTxt() {
		// TODO Auto-generated method stub
		return overlayTxt().getText();
	}

}