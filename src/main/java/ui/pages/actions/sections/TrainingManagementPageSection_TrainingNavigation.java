package ui.pages.actions.sections;

import ui.BrowserDriver;
import ui.pages.actions.AddClassPage;
import ui.pages.actions.AddQuestionCustomExamPage;
import ui.pages.actions.ClassesPage;
import ui.pages.actions.ControlDocumentTrainingPage;
import ui.pages.actions.IEditTraining;
import ui.pages.actions.TrainingManagementPage;
import ui.pages.actions.TrainingPage;
import ui.pages.repo.sections.TrainingNavigation_TrainingManagementPageSectionRepo;

public class TrainingManagementPageSection_TrainingNavigation
		extends TrainingNavigation_TrainingManagementPageSectionRepo {
	BrowserDriver driver;

	public TrainingManagementPageSection_TrainingNavigation(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);

	}

	public IEditTraining openEditGeneralInformation() {
		recordScreenIframeSwitch();
		editGeneralInfolnk().click();
		return new ControlDocumentTrainingPage(driver);
	}

	public TrainingPage openReturn() {
		recordScreenIframeSwitch();
		returnlnk().click();
		return new TrainingPage(driver);

	}

	public TrainingManagementPage openOrganizations() {
		recordScreenIframeSwitch();
		organizationsLnk().click();
		return new TrainingManagementPage(driver);
	}

	public ClassesPage openClasses() {
		recordScreenIframeSwitch();
		classesLnk().click();
		return new ClassesPage(driver);

	}

	public AddClassPage openAddClass() {
		recordScreenIframeSwitch();
		addClassLnk().click();
		return new AddClassPage(driver);

	}

	public TrainingManagementPageSection_Content openHistory() {
		recordScreenIframeSwitch();
		historyLnk().click();
		return new TrainingManagementPageSection_Content(driver);
	}

	public TrainingManagementPage openGeneralInformation() {
		recordScreenIframeSwitch();
		generalInfoLnk().click();
		return new TrainingManagementPage(driver);
	}

	public AddQuestionCustomExamPage openCustomExam() {
		recordScreenIframeSwitch();
		customExamLnk().click();
		return new AddQuestionCustomExamPage(driver);
	}

	public TrainingManagementPage openActivateVersion() {
		recordScreenIframeSwitch();
		activateVersionLnk().click();
		return new TrainingManagementPage(driver);
	}

	public TrainingManagementPage openRoster() {
		recordScreenIframeSwitch();
		rosterLnk().click();
		return new TrainingManagementPage(driver);

	}
	public TrainingManagementPage openAddUser() {
		recordScreenIframeSwitch();
		addUserLnk().click();
		return new TrainingManagementPage(driver);

	}
	
	public TrainingManagementPage openAddTrainingItem() {
		recordScreenIframeSwitch();
		addTrainingItemLnk().click();
		return new TrainingManagementPage(driver);

	}
}
