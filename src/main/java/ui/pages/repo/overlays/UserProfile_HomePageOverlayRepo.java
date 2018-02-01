package ui.pages.repo.overlays;

import ui.BrowserElement;
import ui.UiBase;

public class UserProfile_HomePageOverlayRepo extends UiBase { 

	protected BrowserElement changePasswordlnk() {
		return findByLinkText("Change Password");
	}
	protected BrowserElement curriculumVitaelnk() {
		return findByLinkText("Curriculum Vitae");
	}
	protected BrowserElement languageSettingslnk() {
		return findByLinkText("Language Settings");
	}
	protected BrowserElement dateTimeFormatlnk() {
		return findByLinkText("Date/Time Format");
	}
	protected BrowserElement olChangePasswordlnk() {
		return findByLinkText("पासवर्ड बदलें");
	}
	protected BrowserElement olLanguageSettingslnk() {
		return findByLinkText("भाषा सेटिंग्स");
	}
}
