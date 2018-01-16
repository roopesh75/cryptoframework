package ui.pages.actions.sections;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.TrainingManagementPage;
import ui.pages.actions.ViewCurriculumPage;
import ui.pages.repo.sections.SearchTraining_TrainingPageSectionRepo;

public class TrainingPageSection_SearchTraining extends SearchTraining_TrainingPageSectionRepo {
	BrowserDriver driver;

	public TrainingPageSection_SearchTraining(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public TrainingManagementPage searchTraining(String trainingCode) {
		recordScreenIframeSwitch();
		trainingCodeBox().clear();
		trainingCodeBox().sendKeys(trainingCode);
		searchlnk().click();
		return new TrainingManagementPage(driver);
	}

	public boolean isTrainingPage() {
		recordScreenIframeSwitch();
		trainingCodeBox().isDisplayed();
		return trainingCodeBox().isDisplayed();
	}

	public ViewCurriculumPage searchCurriculum(String code) {
		recordScreenIframeSwitch();
		currCodeTxtBox().sendKeys(code);
		search_curr_lnk().click();
		return new ViewCurriculumPage(driver);
	}

}
