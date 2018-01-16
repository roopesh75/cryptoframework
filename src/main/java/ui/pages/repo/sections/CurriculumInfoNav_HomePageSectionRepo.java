package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class CurriculumInfoNav_HomePageSectionRepo extends UiBase {
	protected BrowserElement cataloglnk() {
		return findById("catalog");
	}
	protected BrowserElement toDolnk() {
		return findById("To-Do");
	}
	protected BrowserElement historylnk() {
		return findById("history");
	}
}
