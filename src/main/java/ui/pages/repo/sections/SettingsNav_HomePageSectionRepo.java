package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class SettingsNav_HomePageSectionRepo extends UiBase {

	protected BrowserElement toolslnk() {
		return findByLinkText("Tools");
	}

	protected BrowserElement logoutlnk() {
		return findByCssSelector("ul[class='nav navbar-nav navbar-right']").findById("btn-logout");
	}

	protected BrowserElement userProfilelnk() {
		return findByCssSelector("ul[class='nav navbar-nav navbar-right']").findById("link-user");
	}
    protected BrowserElement olToolslnk() {
    	return findByPartialLinkText("उपकरण");
    	}
   
}
