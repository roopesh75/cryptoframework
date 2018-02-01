package ui.pages.repo.overlays;

import ui.BrowserElement;
import ui.UiBase;

public class SearchForTrainingOverlayRepo extends UiBase{
	protected BrowserElement trainingCodeBox() {
		return findByName("TrainingCode");
	}
	protected BrowserElement searchlnk() {
		return findByLinkText("Search");
	}
	protected BrowserElement tableResults() {
		return findById("tblresults");
	}
	protected BrowserElement continuelnk() {
		return findByLinkText("Continue");
	}
	protected BrowserElement searchResultsTbl() {
		return findById("tblSearchResults");
	}
	protected BrowserElement closeOverlay() {
		return findByCssSelector("a[title='Close']");
	}
	//
}
