package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.actions.sections.TrainingPageSection_ManageTraining;
import ui.pages.actions.sections.TrainingPageSection_NewTrainingReports;
import ui.pages.actions.sections.TrainingPageSection_SearchTraining;
import ui.pages.repo.TrainingPageRepo;

public class TrainingPage extends TrainingPageRepo {
	BrowserDriver driver;
	TrainingPageSection_SearchTraining searchTraining_TrainingPageSection;
	AdminNav_CommonSection adminNav_CommonSection;
	TrainingPageSection_ManageTraining manageTraining_TrainingPageSection;
	TrainingPageSection_NewTrainingReports trainingPageSection_NewTrainingReports ;
	public TrainingPage(BrowserDriver driver) {
		this.driver = driver;
		searchTraining_TrainingPageSection = new TrainingPageSection_SearchTraining(driver);
		adminNav_CommonSection = new AdminNav_CommonSection(driver);
		trainingPageSection_NewTrainingReports = new TrainingPageSection_NewTrainingReports(driver);
		manageTraining_TrainingPageSection = new TrainingPageSection_ManageTraining(driver);
		setDriver(driver);
	}

	public TrainingManagementPage searchTraining(String trainingCode) {
		searchTraining_TrainingPageSection.searchTraining(trainingCode);
		return new TrainingManagementPage(driver);
	}

	public boolean isTrainingPage() {
		// TODO Auto-generated method stub
		return searchTraining_TrainingPageSection.isTrainingPage();
	}

	public LoginPage logOut() {
		return adminNav_CommonSection.logOut();
	}

	public AddTrainingItemPage openAddTrainingItem() {
		recordScreenIframeSwitch();
		return manageTraining_TrainingPageSection.openAddTrainingItem();
	}

	public String getAllSectionTxt() {
		recordScreenIframeSwitch();
		return trainingSectionTxt().getText();
	}
	public DefineCompletionReportPage openCompletionReportByTraining(){
		return trainingPageSection_NewTrainingReports.openCompletionReportByTraining();
	}
	public DefineAssignmentReportPage openAssignmentReportByTraining(){
		return trainingPageSection_NewTrainingReports.openAssignmentReportByTraining();
	}
	//
	public CurriculumReportPage openDisplayCurriculumList() {
		recordScreenIframeSwitch();
		return manageTraining_TrainingPageSection.openDisplayCurriculumList();
	}
	public UsersPage openUsersPage() {
		recordScreenIframeSwitch();
		return adminNav_CommonSection.openUsersPage();
	}

	public ViewCurriculumPage searchCurriculum(String code) {
		searchTraining_TrainingPageSection.searchCurriculum(code);
		return new ViewCurriculumPage(driver);
	}

	public AddTrainingItemPage openManageTrainingTypes() {
		recordScreenIframeSwitch();
		return manageTraining_TrainingPageSection.openManageTrainingTypes();
	}
}
