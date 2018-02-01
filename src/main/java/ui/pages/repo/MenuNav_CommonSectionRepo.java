package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class MenuNav_CommonSectionRepo extends UiBase{
	protected BrowserElement sortOptionsLnk(){
		return findById("span-sort");
	}
	protected BrowserElement searchLnk(){
		return findById("span-search");
	}
	protected BrowserElement completionDateOldestBtn(){
		return findByXpath("//button[text()='Completion Date (Oldest)']");
	}
	protected BrowserElement titleZtoABtn(){
		return findByXpath("//button[text()='Title (z to a)']");
	}
	protected BrowserElement searchTrainingTxtBox(){
		return findById("SearchGeneral");
	}
	protected BrowserElement searchBtn(){
		return findById("searchButton");
	}
}
