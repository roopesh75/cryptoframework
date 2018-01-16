package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class AdminNav_CommonSectionRepo extends UiBase {
	protected BrowserElement logoutlnk() {
		return findByLinkText("Logout");
	}

	protected BrowserElement logslnk() {
		return findByLinkText("Logs");
	}

	protected BrowserElement knowledgeCenterlnk() {
		return findByLinkText("Knowledge Center");
	}

	protected BrowserElement optionsLnk() {
		return findByLinkText("Options");
	}

	protected BrowserElement usersLnk() {
		return findByLinkText("Users");
	}

	protected BrowserElement trainingLnk() {
		return findByLinkText("Training");
	}

	protected BrowserElement supportLnk() {
		return findByLinkText("Support");
	}
	protected BrowserElement assignmentLnk() {
		return findByLinkText("Assignments");
	}
}
