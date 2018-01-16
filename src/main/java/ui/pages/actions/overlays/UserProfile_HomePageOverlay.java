package ui.pages.actions.overlays;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.ChangePasswordPage;
import ui.pages.actions.UsersPage;
import ui.pages.repo.overlays.UserProfile_HomePageOverlayRepo;

public class UserProfile_HomePageOverlay extends UserProfile_HomePageOverlayRepo {
	BrowserDriver driver;

	public UserProfile_HomePageOverlay(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public ChangePasswordPage openChangePwdPage() {
		waitForAjax("Wait for UsersOverlay");
		changePasswordlnk().click();
		return new ChangePasswordPage(driver);
	}
	
}
