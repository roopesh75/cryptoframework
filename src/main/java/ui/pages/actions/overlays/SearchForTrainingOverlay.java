package ui.pages.actions.overlays;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.TrainingManagementPage;
import ui.pages.actions.TrainingPage;
import ui.pages.repo.overlays.SearchForTrainingOverlayRepo;

public class SearchForTrainingOverlay extends SearchForTrainingOverlayRepo implements ISearchForTrainingOverlay {
	BrowserDriver driver;

	public SearchForTrainingOverlay(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public TrainingManagementPage searchTraining(String trainingCode) {
		recordScreenIframeSwitch();
		trainingCodeBox().sendKeys(trainingCode);
		searchlnk().click();
		return new TrainingManagementPage(driver);
	}

	public String getSearchStatus() {
		popUpIframeViaRecordScreenIframeSwitch();
		return searchResultsTbl().getText();
	}

	public TrainingPage closeSearchTrainingOverlay() {
		recordScreenIframeSwitch();
		closeOverlay().click();
		return new TrainingPage(driver);
	}

}
