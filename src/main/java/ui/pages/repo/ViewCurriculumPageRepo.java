package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class ViewCurriculumPageRepo extends UiBase {
	
	protected BrowserElement curriculumTitle(){
		return findByXpath("//*[span='Curriculum Title']/div[1]");
	}
	protected BrowserElement viewCurriculumPageContent() {
		return findByTagName("body");
	}
	protected BrowserElement viewReports() {
		return findByLinkText("Reports");
	}
	protected BrowserElement trainingItemsLnk() {
		return findByLinkText("Training Items");
	}
	//

}
