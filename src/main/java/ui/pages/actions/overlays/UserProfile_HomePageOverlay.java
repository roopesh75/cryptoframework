package ui.pages.actions.overlays;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.ChangePasswordPage;
import ui.pages.actions.CurriculumVitaePage;
import ui.pages.actions.PreferencesPage;
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

	public CurriculumVitaePage openCurriculumVitae() {
		curriculumVitaelnk().click();
		return new CurriculumVitaePage(driver);
		
	}

	public PreferencesPage openLanguageSettings() {
		waitForAjax("Wait for UsersOverlay");
		languageSettingslnk().click();
		return new PreferencesPage(driver);
	}

	public PreferencesPage openOlLanguageSettings() {
		waitForAjax("Wait for UsersOverlay");
		olLanguageSettingslnk().click();
		return new PreferencesPage(driver);
	}

	public ChangePasswordPage openOlChangePwdPage() {
		waitForAjax("Wait for UsersOverlay");
		olChangePasswordlnk().click();
		return new ChangePasswordPage(driver);
	}
	public PreferencesPage openDateTimeFormat() {
		waitForAjax("Wait for UsersOverlay");
		dateTimeFormatlnk().click();
		return new PreferencesPage(driver);
	}

}
