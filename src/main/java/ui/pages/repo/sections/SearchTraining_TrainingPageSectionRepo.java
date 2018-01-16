package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class SearchTraining_TrainingPageSectionRepo extends UiBase {
	protected BrowserElement trainingCodeBox() {
		return findByName("TrainingCode");
	}
	protected BrowserElement searchlnk() {
		return findByLinkText("Search");
	}
	protected BrowserElement currCodeTxtBox() {
		return findById("CurriculumCode");
	}
	protected BrowserElement search_curr_lnk() {
		return findByXpath("//*[@id='CurrSearch']/table/tbody/tr[2]/td[3]/a");
	}
	protected BrowserElement searchTrainingOverlayLbl() {
		return findById("fauxPopupTitle");
	}
}
