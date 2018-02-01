package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class TrainingNavigation_TrainingManagementPageSectionRepo extends UiBase{

	protected BrowserElement editGeneralInfolnk() {
		return findByLinkText("Edit General Information");
	}
	protected BrowserElement returnlnk() {
		return findByLinkText("Return");
	}
	protected BrowserElement organizationsLnk() {
		return findByLinkText("Organizations");
	}
	protected BrowserElement classesLnk() {
		return findByLinkText("Classes");
	}
	protected BrowserElement disabledTypesLnk() {
		return findByLinkText("Disabled Types");
	}
	protected BrowserElement enabledTypesLnk() {
		return findByLinkText("Enabled Types");
	}
	protected BrowserElement quizLnk() {
		return findByLinkText("Quiz");
	}
	protected BrowserElement addTrainingTypeLnk() {
		return findByLinkText("Add Training Type");
	}
	protected BrowserElement addClassLnk() {
		return findByLinkText("Add Class");
	}
	protected BrowserElement historyLnk() {
		return findByLinkText("History");
	}
	protected BrowserElement addTrItemEquiLnk() {
		return findByLinkText("Add Training Item Equivalency");
	}

	protected BrowserElement generalInfoLnk() {
		return findByLinkText("General Information");
	}
	protected BrowserElement customExamLnk() {
		return findByLinkText("Custom Exam");
	}
	protected BrowserElement activateVersionLnk() {
		return findByLinkText("Activate Version");
	}
	protected BrowserElement newVersionCreateLnk() {
		return findByLinkText("Create New Version");
	}
	protected BrowserElement rosterLnk() {
		return findByLinkText("Roster");
	}
	protected BrowserElement addUserLnk() {
		return findByLinkText("Add User");
	}
	protected BrowserElement equivalenciesLnk() {
		return findByLinkText("Equivalencies");
	}
	
	protected BrowserElement addTrainingItemLnk() {
		return findByLinkText("Add Training Item");
	}
	
	protected BrowserElement manageStatusLnk() {
		return findByLinkText("Manage Status");
	}
}
