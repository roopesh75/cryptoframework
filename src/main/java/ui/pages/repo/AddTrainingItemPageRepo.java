package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class AddTrainingItemPageRepo extends UiBase {
	protected BrowserElement instructorLedCourselnk() {
		return findByLinkText("Instructor Led Course");
	}
	protected BrowserElement controlDocumentlnk() {
		return findByLinkText("Control Document");
	}
	protected BrowserElement customExamlnk() {
		return findByLinkText("Custom Exam");
	}
	protected BrowserElement trainingTypesPageContent() {
		return findByTagName("body");
	}
	protected BrowserElement formlnk() {
		return findByLinkText("Forms");
	}
	
	
}

