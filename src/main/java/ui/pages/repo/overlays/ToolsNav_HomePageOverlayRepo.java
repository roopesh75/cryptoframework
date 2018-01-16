package ui.pages.repo.overlays;

import ui.BrowserElement;
import ui.UiBase;

public class ToolsNav_HomePageOverlayRepo extends UiBase {

	// @FindBy(linkText="Users")
	protected BrowserElement userslnk() {
		return findByLinkText("Users");
	}

	protected BrowserElement traininglnk() {
		return findByLinkText("Training");
	}

	protected BrowserElement optionslnk() {
		return findByLinkText("Options");
	}

	protected BrowserElement logslnk() {
		return findByLinkText("Logs");
	}
	protected BrowserElement overlayTxt() {
		return findByClass("popover-content");
	}
}
