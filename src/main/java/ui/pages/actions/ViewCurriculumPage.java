package ui.pages.actions;

import org.apache.bcel.generic.NEW;

import ui.BrowserDriver;
import ui.pages.repo.ViewCurriculumPageRepo;

public class ViewCurriculumPage extends ViewCurriculumPageRepo {
	BrowserDriver driver;

	public ViewCurriculumPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	} 
	public boolean isViewCurriculumPage() {
		recordScreenIframeSwitch();
		return curriculumTitle().isDisplayed();
	}
	public String getViewCurriculumPageContent() {
		staticWait(1000);
		return viewCurriculumPageContent().getText();
}
	public TrainingManagementPage openReports() {
		viewReports().click();	
		return new TrainingManagementPage(driver);
	}
	public TrainingManagementPage openTrainingItems() {
		trainingItemsLnk().click();	
		return new TrainingManagementPage(driver);
	}
}