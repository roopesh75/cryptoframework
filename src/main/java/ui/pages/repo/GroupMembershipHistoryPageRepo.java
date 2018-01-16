package ui.pages.repo;

import org.openqa.selenium.By;

import ui.BrowserElement;
import ui.BrowserElementImpl;
import ui.UiBase;

public class GroupMembershipHistoryPageRepo extends UiBase {
	protected BrowserElement groupHistoryRowText(int index) {
		return new BrowserElementImpl(
				findById("groupMembership-result-rows").findElements(By.id("data-row")).get(index),
				getDriver().getWebDriver());
	}

	protected BrowserElement groupHistoryTopRowText() {
		return findById("header-row");
	}
}
