package ui.pages.repo.sections;

import org.openqa.selenium.By;

import ui.BrowserElement;
import ui.BrowserElementImpl;
import ui.UiBase;

public class SearchUsers_UsersPageSectionRepo extends UiBase {
	protected BrowserElement userTxtBox() {
		return findByName("UserID");
	}

	protected BrowserElement searchLnk() {
		return findByLinkText("Search");
	}

	protected BrowserElement grpNameBox() {
		return findByName("GroupName");
	}

	protected BrowserElement searchLnk(int index) {
		return new BrowserElementImpl(getDriver().findElements(By.linkText("Search")).get(index),getDriver().getWebDriver());
	}
}
