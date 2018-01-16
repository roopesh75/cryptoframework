package ui.pages.actions;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.overlays.UserProfile_HomePageOverlay;
import ui.pages.actions.sections.HomePageSection_SettingsNav;
import ui.pages.repo.PreferencesPageRepo;

public class PreferencesPage extends PreferencesPageRepo {
	BrowserDriver driver;
	HomePageSection_SettingsNav settingsNav_homePage;

	public PreferencesPage(BrowserDriver driver) {
		this.driver = driver;
		settingsNav_homePage = new HomePageSection_SettingsNav(driver);
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public UserProfile_HomePageOverlay openUserProfile() {
		settingsNav_homePage.openUserProfile();
		return new UserProfile_HomePageOverlay(driver);
	}

}
