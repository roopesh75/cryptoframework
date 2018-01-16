package ui.pages.repo.sections;

import org.openqa.selenium.By;

import ui.BrowserDriverImpl;
import ui.BrowserElement;
import ui.BrowserElementImpl;
import ui.UiBase;

public class UserAccountHistory_UserManagementPageSectionRepo extends UiBase {

	protected BrowserElement createdOn() {
		return findByXpath("//tr[td='Created On:']/td[2]");
	}

	protected BrowserElement createdBy() {
		return findByXpath("//tr[td='Created by:']/td[2]");
	}
	protected BrowserElement propertyEdited(int tableId) {
		return new BrowserElementImpl(getDriver().findElements(By.className("PRINTTableBorder")).get(tableId).findElements(By.tagName("tr")).get(2).findElements(By.tagName("td")).get(0),getDriver().getWebDriver());
	}
	protected BrowserElement oldValue(int tableId) {
		return new BrowserElementImpl(getDriver().findElements(By.className("PRINTTableBorder")).get(tableId).findElements(By.tagName("tr")).get(2).findElements(By.tagName("td")).get(1),getDriver().getWebDriver());
	}
	protected BrowserElement newValue(int tableId) {
		return new BrowserElementImpl(getDriver().findElements(By.className("PRINTTableBorder")).get(tableId).findElements(By.tagName("tr")).get(2).findElements(By.tagName("td")).get(2),getDriver().getWebDriver());
	}

}
