package ui.pages.actions;

import java.util.List;
import java.util.stream.Collectors;

import ui.BrowserDriver;
import ui.BrowserElement;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.repo.SecuritySettingsPageRepo;

public class SecuritySettingsPage extends SecuritySettingsPageRepo {
	BrowserDriver driver;
	AdminNav_CommonSection adminNav_CommonSection;

	public SecuritySettingsPage(BrowserDriver driver) {
		this.driver = driver;
		adminNav_CommonSection = new AdminNav_CommonSection(driver);
		setDriver(driver);
	}

	public boolean isSecuritySettingsPage() {
		return assignSecurityRoleLnk().isDisplayed();
	}

	public SecuritySettingsPage assignSecurityRoleToUsr(String... parameters) {
		assignSecurityRoleLnk().click();
		continueOrgLnk().click();
		popUpIframeSwitch();
		userIdTxtBox().sendKeys(parameters[0]);

		searchUsrLnk().click();
		if (parameters.length > 1) {
			if (parameters[1].equalsIgnoreCase("true")) {
				securityRoleOverride().click();
			}
		}
		return this;
	}

	public SecuritySettingsPage applySecurityRole() {
		switchToDefaultFrame();
		applySecurityRoleLnk().click();
		return this;
	}

	public boolean hasAssignSecurityRoleBtn() {
		switchToDefaultFrame();
		return applySecurityRoleLnk().isDisplayed();
	}

	public LogsPage openLogsPage() {
		adminNav_CommonSection.openLogsPage();
		return new LogsPage(driver);
	}

	public boolean getOverideableStateSecurityRole(String userName) {
		popUpIframeSwitch();
		boolean pageState = UsrChkBox(userName).isEnabled();
		switchToDefaultFrame();
		return pageState;
	}

	public SecuritySettingsPage selectUserToOverride(String userName) {
		popUpIframeSwitch();
		UsrChkBox(userName).click();
		switchToDefaultFrame();
		return this;
	}

	public void createNewSecurityRoleViewUserEditSecurityRole(String roleId) {
		securityRoleBox().sendKeys(roleId);
		viewUsersChkBox().click();
		defineEditSecurityRoleChkBox().click();
		addLnk().click();
	}
	public void createNewSecurityRole(String roleId) {
		securityRoleBox().sendKeys(roleId);
		viewTrainingItemsRostersChkBox().click();
		viewCurriculumChkBox().click();
		manageCurriculumChkBox().click();
		viewCompletionChkBox().click();
		viewAssignmentsByTrainingChkBox().click();
		createAssignmentChkBox().click();
		createCompletionChkBox().click();
		manageQuickReportChkBox().click();
		addLnk().click();
	}

	public SecurityRolePage returnFromSecuritySettings() {
		returnlnk().click();
		return new SecurityRolePage(driver);
	}

	public SecuritySettingsPage viewUsersList() {
		recordScreenIframeSwitch();
		viewUsersLnk().click();
		switchToDefaultFrame();
		return this;
	}

	public void editSecurityRole() {
		editSecurityRoleLnk().click();
		defineEditSecurityRoleChkBox().click();
		saveLnk().click();
	}

	public void removeSecurityRole() {
		removeSecurityRoleLnk().click();
		acceptAlert();
	}

	public LoginPage logOut(String... parameters) {
		adminNav_CommonSection.logOut(parameters);
		return new LoginPage(driver);
	}

	public int getCountEnabledPermissions() {
		List<String> status = permissionChkBoxes().stream().map(e -> e.getAttribute("disabled")).filter(e -> e != null)
				.collect(Collectors.toList());
		return (permissionChkBoxes().size() - status.size());
	}

	public String getSelectedRole() {
		String txt = securityRoleChosenText().getText();
		return txt;

	}

	public boolean isAssignSecurityRoleCheckBoxEnabled(String userName) {
		// TODO Auto-generated method stub
		return getOverideableStateSecurityRole(userName);
	}

}
