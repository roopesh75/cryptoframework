package ui.pages.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.actions.overlays.UserProfile_HomePageOverlay;
import ui.pages.actions.sections.HomePageSection_CurriculumInfoNav;
import ui.pages.actions.sections.HomePageSection_SettingsNav;
import ui.pages.repo.PreferencesPageRepo;

public class PreferencesPage extends PreferencesPageRepo {
	BrowserDriver driver;
	HomePageSection_SettingsNav settingsNav_homePage;
	HomePageSection_CurriculumInfoNav curriculumInfoNav_homePage;

	public PreferencesPage(BrowserDriver driver) {
		this.driver = driver;
		settingsNav_homePage = new HomePageSection_SettingsNav(driver);
		setDriver(driver);
		PageFactory.initElements(driver, this);
		curriculumInfoNav_homePage = new HomePageSection_CurriculumInfoNav(driver);
	}

	public UserProfile_HomePageOverlay openUserProfile() {
		settingsNav_homePage.openUserProfile();
		return new UserProfile_HomePageOverlay(driver);
	}

	public PreferencesPage changeLanguage(String language) {
		new Select(languagesDrpdwn()).selectByValue(language);
		saveBtn().click();
		return this;
	}

	public PreferencesPage changeTimeDisplaySettings(String time) {
		new Select(timeDisplaySettingsDrpdwn()).selectByValue(time);
		dateTimeSaveBtn().click();
		return this;
	}

	public String getTimeDisplaySettings() {
		return new Select(timeDisplaySettingsDrpdwn()).getFirstSelectedOption().getText();

	}

	public CatalogPage openCatalog() {
		curriculumInfoNav_homePage.openCatalog();
		return new CatalogPage(driver);
	}
	public ToDoPage openToDoPage() {
		curriculumInfoNav_homePage.openToDoPage();
		return new ToDoPage(driver);
	}

	public String getSaveBtnTxt() {
		return saveBtn().getText();
	}

}
