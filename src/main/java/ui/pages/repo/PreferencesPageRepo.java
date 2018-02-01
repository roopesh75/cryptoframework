package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class PreferencesPageRepo extends UiBase {

	protected BrowserElement languagesDrpdwn() {
		return findByName("languages");
	}
	protected BrowserElement dateTimeSaveBtn() {
		return findById("btn-update-datetime");
	}
	protected BrowserElement saveBtn() {
		return findById("btn-update-lang");
	}
	protected BrowserElement timeDisplaySettingsDrpdwn() {
		return findById("TimeFormat");
	}
	
}
