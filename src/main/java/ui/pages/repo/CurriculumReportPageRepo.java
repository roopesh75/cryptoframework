package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class CurriculumReportPageRepo extends UiBase {
	protected BrowserElement addCurriculumlnk() {
		return findByLinkText("Add Curriculum");
	} 
	protected BrowserElement printTableBorderTxt() {
		return findByClass("PRINTTableBorder");
	} 
}
