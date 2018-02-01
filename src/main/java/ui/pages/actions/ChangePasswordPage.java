package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.actions.overlays.ToolsNav_HomePageOverlay;
import ui.pages.actions.sections.HomePageSection_SettingsNav;
import ui.pages.repo.ChangePasswordPageRepo;

public class ChangePasswordPage extends ChangePasswordPageRepo {

	BrowserDriver driver;
	ToolsNav_HomePageOverlay toolsNav_HomePageOverlay;
	HomePageSection_SettingsNav settingsNav_homePage;

	public ChangePasswordPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		toolsNav_HomePageOverlay = new ToolsNav_HomePageOverlay(driver);
		settingsNav_homePage = new HomePageSection_SettingsNav(driver);
	}

	public ChangePasswordPage changePassword(String currentPwd, String newPwd, String confirmNewPwd) {
		currentPwdtxt().sendKeys(currentPwd);
		newPwdtxt().sendKeys(newPwd);
		confirmNewPwdtxt().sendKeys(confirmNewPwd);
		submitbtn().click();
		waitForAjax("clicked on SubmitBtn");
		return this;
	}

	public String getAlertMessage() {
		waitForAjax("Failure message");
		waitUntilElementDisplayed(alertMessage());
		return alertMessage().getAttribute("innerText");
	}

	public String getSuccessMessage() {
		waitForAjax("Success message");
		return successModalDialog().getAttribute("innerText");

	}

	public HomePage continuePwd() {
		continuebtn().click();
		waitForAjax("Success message");
		return new HomePage(driver);
	}

	public boolean isPwdPoliciesPage() {
		return currentPwdtxt().isDisplayed();
	}

	public String getSubmitBtnTxt() {
		return submitbtn().getText();
	}

	public UsersPage openOlUsersPage() {

		return toolsNav_HomePageOverlay.openOlUsersPage();
	}

	public ToolsNav_HomePageOverlay openOlToolsMenu() {
		settingsNav_homePage.openOlToolsMenu();
		return new ToolsNav_HomePageOverlay(driver);
	}

	public ToolsNav_HomePageOverlay openToolsMenu() {
		settingsNav_homePage.openToolsMenu();
		return new ToolsNav_HomePageOverlay(driver);
	}
}
