package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class TrainingPageRepo extends UiBase {
	protected BrowserElement addTrainingLnk(){
		return findByLinkText("Add a training item");
	}
	protected BrowserElement trainingSectionTxt(){
		return findById("divBODY");
	}
	
	
}
