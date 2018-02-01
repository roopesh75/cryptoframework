package ui.pages.actions.sections;

import ui.BrowserDriver;
import ui.pages.repo.MenuNav_CommonSectionRepo;

public class MenuNav_CommonSection extends MenuNav_CommonSectionRepo {
	BrowserDriver driver;

	public MenuNav_CommonSection(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);

	}
	public MenuNav_CommonSection openSort(){
		sortOptionsLnk().click();
		return this;
	}
	public MenuNav_CommonSection openSearch(){
		searchLnk().click();
		waitForAjax("waiting for training search box");
		return this;
	}
	public MenuNav_CommonSection sortByTitleZtoA(){
		titleZtoABtn().click();
		return this;
	}
	public MenuNav_CommonSection sortByCompletionDateOldestBtn(){
		completionDateOldestBtn().click();
		return this;
	}
	public MenuNav_CommonSection searchTraining(String trainingItem) {
		searchTrainingTxtBox().clear();
		searchTrainingTxtBox().sendKeys(trainingItem);
		searchBtn().click();
		waitForAjax("waiting for training");
		return this;
		
	}
}
