package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class TrainingPageSection_NewTrainingReportsRepo extends UiBase{

	protected BrowserElement completionReportTraininglnk() {
		return findByLinkText("Completions Report by Training");
	}
	protected BrowserElement assignmentReportTraininglnk() {
		return findByLinkText("Assignment Report by Training");
	}
	//
}
