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
	protected BrowserElement addClassLnk() {
		return findByLinkText("Add Class");
	}
	protected BrowserElement historyLnk() {
		return findByLinkText("History");
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

	protected BrowserElement rosterLnk() {
		return findByLinkText("Roster");
	}
	protected BrowserElement addUserLnk() {
		return findByLinkText("Add User");
	}
	
	//View Curriculum Page ->Training Items
	protected BrowserElement addTrainingItemLnk() {
		return findByLinkText("Add Training Item");
	}
	
}
