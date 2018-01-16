package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class ManageTraining_TrainingPageSectionRepo extends UiBase {
	protected BrowserElement addTrainingItemlnk() {
		return findByLinkText("Add a training item");
	}
	protected BrowserElement displayCurriculumListlnk(){
		return findByLinkText("Display a list of Curriculums");
	}
	protected BrowserElement manageTrainingTypeslnk() {
		return findByLinkText("Manage Training Types");
	}
}
