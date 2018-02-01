package ui.pages.actions;

import java.util.Set;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.actions.sections.UserManagementPageSection_Content;
import ui.pages.actions.sections.UserManagementPageSection_UserAccountHistory;
import ui.pages.actions.sections.UserManagementPageSection_UserNavigation;
import ui.pages.repo.UserManagementPageRepo;

public class UserManagementPage extends UserManagementPageRepo {
	BrowserDriver driver;
	UserManagementPageSection_Content content_userManagementPage;
	UserManagementPageSection_UserNavigation userNavigation_userManagementPage;
	UserManagementPageSection_UserAccountHistory userAccountHistory_userManagementPage;
	AdminNav_CommonSection adminNav_CommonSection;
	String windowHandle;
	public UserManagementPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		content_userManagementPage = new UserManagementPageSection_Content(driver);
		userNavigation_userManagementPage = new UserManagementPageSection_UserNavigation(driver);
		userAccountHistory_userManagementPage = new UserManagementPageSection_UserAccountHistory(driver);
		adminNav_CommonSection = new AdminNav_CommonSection(driver);
		PageFactory.initElements(driver, this);
	}

	public TrainingPage openTrainingPage() {
		return adminNav_CommonSection.openTrainingPage();
	}

	public HomePage openKnowledgeCenter() {
		return adminNav_CommonSection.openKnowledgeCenter();
	}

	public UsersPage openUsersPage() {
		return adminNav_CommonSection.openUsersPage();
	}

	public UserManagementPage openGeneralInformation() {
		return userNavigation_userManagementPage.openGeneralInformation();
	}
	public OptionsPage openOptionsPage() {
		return adminNav_CommonSection.openOptionsPage();
	}

	public UserManagementPage openHistory() {
		return userNavigation_userManagementPage.openHistory();
	}

	public AddNewUserPage openEditUser() {
		return userNavigation_userManagementPage.openEditUser();
	}

	public UsersPage returnFromUserManagement() {
		return userNavigation_userManagementPage.returnFromUserManagement();
	}

	public String getContentSection() {
		return content_userManagementPage.getContentSection();
	}

	public String getOrganization() {
		return content_userManagementPage.getOrganization();
	}

	public String getOrganizationEntity() {
		return content_userManagementPage.getOrganizationEntity();
	}
	public String getUserId() {
		return content_userManagementPage.getUserId();
	}

	public String getFirstName() {
		return content_userManagementPage.getFirstName();
	}

	public String getUserGroupName() {
		return content_userManagementPage.getUserGroupName();
	}

	public String getLastName() {
		return content_userManagementPage.getLastName();
	}

	public String getCreatedOn() {
		return userAccountHistory_userManagementPage.getCreatedOn();
	}

	public String getCreatedBy() {
		return userAccountHistory_userManagementPage.getCreatedBy();
	}

	public String getSecurityRole() {
		return content_userManagementPage.getSecurityRole();
	}

	public String getPropertyEdited(int tableId) {
		return userAccountHistory_userManagementPage.getPropertyEdited(tableId);
	}

	public String getOldValue(int tableId) {
		return userAccountHistory_userManagementPage.getOldValue(tableId);
	}

	public String getNewValue(int tableId) {
		return userAccountHistory_userManagementPage.getNewValue(tableId);
	}

	public String getHomeOrganization() {
		return content_userManagementPage.getHomeOrganization();
	}

	public LoginPage logOut() {
		return adminNav_CommonSection.logOut();
	}

	public String getPRINTTableBorder() {
		recordScreenIframeSwitch();
		return PRINTTableBorderTxt().getText();
	}

	public UserManagementPage openSecurityRoles() {
		return userNavigation_userManagementPage.openSecurityRoles();
	}

	public String getHomePhone() {
		return content_userManagementPage.getHomePhone();
	}

	public String getReportTile() {
		recordScreenIframeSwitch();
		return reportTitleTxt().getText();
	}

	public UserManagementPage openAssignSecurityRole() {
		return userNavigation_userManagementPage.openAssignSecurityRole();

	}

	public UserManagementPage assignRole(String organization, String role) {
		popUpIframeViaRecordScreenIframeSwitch();
		findByLinkText(organization).click();
		recordScreenIframeSwitch();
		continueLnk().click();
		popUpIframeViaRecordScreenIframeSwitch();
		new Select(roleIdSltBox()).selectByVisibleText(role);
		recordScreenIframeSwitch();
		continueLnk().click();
		switchToDefaultFrame();
		return this;
	}

	public UserManagementPage openReferenceMaterial() {
		return userNavigation_userManagementPage.openReferenceMaterial();	
	}

	public UserManagementPage openAddDocument() {
		return userNavigation_userManagementPage.openAddDocument();
	}

	public UserManagementPage addDocumentInfo(String... parameters) {
		popUpIframeViaRecordScreenIframeSwitch();
		documentNameTxtBox().clear();
		documentNameTxtBox().sendKeys(parameters[0]);
		documentPathTxtBox().clear();
		documentPathTxtBox().sendKeys(parameters[1]);	
		return this;
	}

	public UserManagementPage testWebAddress() {
		popUpIframeViaRecordScreenIframeSwitch();
		windowHandle = driver.getWindowHandle();
		testLinkLnk().click();
		Set<String> allWindows = getDriver().getWindowHandles();
		for (String currentWindow : allWindows) {
			driver.switchTo().window(currentWindow);
		}
		return this;
	}

	public UserManagementPage closeTestWebaddressBrowser() {

		Set<String> allWindows = getDriver().getWindowHandles();
		for (String currentWindow : allWindows) {
			if (!currentWindow.equals(windowHandle)) {
				driver.switchTo().window(currentWindow);
				driver.close();
				driver.switchTo().window(windowHandle);
			}
		}
		return this;
	}

	public UserManagementPage addDocument() {
		recordScreenIframeSwitch();
		addDocumentLnk().click();
		return this;
	}
}
