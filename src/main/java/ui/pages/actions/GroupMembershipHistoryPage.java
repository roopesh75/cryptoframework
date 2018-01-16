package ui.pages.actions;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.repo.GroupMembershipHistoryPageRepo;

public class GroupMembershipHistoryPage extends GroupMembershipHistoryPageRepo {
	BrowserDriver driver;
	String userId;

	public GroupMembershipHistoryPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public GroupMembershipHistoryPage(BrowserDriver driver, String userId) {
		this.driver = driver;
		this.userId = userId;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public String getRow(int index) {
		return groupHistoryRowText(index).getText();

	}
	public String getTopRow() {
		return groupHistoryTopRowText().getText();

	}
	//

}
