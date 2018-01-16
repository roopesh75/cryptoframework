package ui.pages.actions.sections;

import ui.BrowserDriver;
import ui.pages.actions.AssignmentPage;
import ui.pages.actions.HomePage;
import ui.pages.actions.LoginPage;
import ui.pages.actions.LogsPage;
import ui.pages.actions.OptionsPage;
import ui.pages.actions.TrainingPage;
import ui.pages.actions.PreferencesPage;
import ui.pages.actions.UsersPage;
import ui.pages.repo.sections.AdminNav_CommonSectionRepo;

public class AdminNav_CommonSection extends AdminNav_CommonSectionRepo {
	BrowserDriver driver;

	public AdminNav_CommonSection(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);

	}

	public LogsPage openLogsPage() {
		netControlIframeSwitch();
		logslnk().click();
		return new LogsPage(driver);
	}

	public LoginPage logOut(String... parameters) {
		if(parameters.length==0){
		netControlIframeSwitch();}
		logoutlnk().click();
		return new LoginPage(driver);
	}

	public PreferencesPage openSupport() {
		netControlIframeSwitch();
		supportLnk().click();
		return new PreferencesPage(driver);
	}
	public HomePage openKnowledgeCenter() {
		netControlIframeSwitch();
		knowledgeCenterlnk().click();
		switchToDefaultFrame();
		return new HomePage(driver);
	}
	public OptionsPage openOptionsPage() {
		netControlIframeSwitch();
		optionsLnk().click();
		return new OptionsPage(driver);
	}

	public UsersPage openUsersPage() {
		netControlIframeSwitch();
		usersLnk().click();
		return new UsersPage(driver);
		
	}

	public TrainingPage openTrainingPage() {
		netControlIframeSwitch();
		trainingLnk().click();
		return new TrainingPage(driver);
	}

	public AssignmentPage openAssignmentPage() {
		netControlIframeSwitch();
		assignmentLnk().click();
		return new AssignmentPage(driver);
	}


}
