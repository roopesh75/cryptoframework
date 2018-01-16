package ui.pages.repo.overlays;

import ui.BrowserElement;
import ui.UiBase;
import ui.pages.actions.InstructorLedCoursePage;

public class TrainingManagementPage_ManageTrainingTypesOverlayRepo extends UiBase {
	protected BrowserElement trainingType() {
		return findByXpath("//tr[td='Type Description:']/td[2]");
	}
	protected BrowserElement manageTrainingTypesClose() {
		return findByXpath("//img[@title='Close']");
	}
	protected BrowserElement activateVersionlnk() {
		return findByLinkText("Activate Version");
	}
	
	
}
	
