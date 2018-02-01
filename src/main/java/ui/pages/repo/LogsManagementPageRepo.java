package ui.pages.repo;

import org.openqa.selenium.By;

import ui.BrowserElement;
import ui.BrowserElementImpl;
import ui.UiBase;

public class LogsManagementPageRepo extends UiBase {
	protected BrowserElement latestUpgradeInfoTxt(int index) {
		return new BrowserElementImpl(getDriver().findElement(By.className("PRINTTableBorder")).findElements(By.className("EduText")).get(index),
				getDriver().getWebDriver());

	}
	protected BrowserElement returnLogMenuLnk() {
		return findByLinkText("Return to Logs Menu");

	}
}
