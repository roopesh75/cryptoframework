package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.actions.sections.TrainingManagementPageSection_TrainingNavigation;
import ui.pages.repo.ClassesPageRepo;

public class ClassesPage extends ClassesPageRepo {

	BrowserDriver driver;
	AdminNav_CommonSection adminNav_CommonSection;
	TrainingManagementPageSection_TrainingNavigation trainingNavigation_TrainingManagementPageSection;
	public ClassesPage(BrowserDriver driver) {
		this.driver = driver;
		adminNav_CommonSection = new AdminNav_CommonSection(driver);
		trainingNavigation_TrainingManagementPageSection= new TrainingManagementPageSection_TrainingNavigation(driver);
		setDriver(driver);
	}

	public String getonlineRegistration() {
		return onlineRegistration().getText();
	}

	public ClassesPage openTrainingItem(String trainingName) {
		recordScreenIframeSwitch();
		trainingNamelnk(trainingName).click();
		return this;
	}

	public LoginPage logOut() {
		return adminNav_CommonSection.logOut();
	}

	public UsersPage openUsersPage() {
		adminNav_CommonSection.openUsersPage();
		return new UsersPage(driver);
	}
	public String getClassCode() {
		recordScreenIframeSwitch();
		return classCode().getText().trim();
	}
	public String getClassTitle() {
		recordScreenIframeSwitch();
		
		return classTitle().getText().trim();
	}
	public OptionsPage openOptionsPage() {
		adminNav_CommonSection.openOptionsPage();
		return new OptionsPage(driver);
	}
	public AddClassPage openAddClass() {
		return trainingNavigation_TrainingManagementPageSection.openAddClass();

	}

	public TrainingManagementPage openRoster() {
		recordScreenIframeSwitch();
		rosterLnk().click();
		return new TrainingManagementPage(driver);
		
	}
}
