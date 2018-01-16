package ui.pages.actions.sections;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.AddTrainingItemPage;
import ui.pages.actions.CurriculumReportPage;
import ui.pages.repo.sections.ManageTraining_TrainingPageSectionRepo;

public class TrainingPageSection_ManageTraining extends ManageTraining_TrainingPageSectionRepo {
	BrowserDriver driver;

	public TrainingPageSection_ManageTraining(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}
public AddTrainingItemPage openAddTrainingItem() {
	addTrainingItemlnk().click();
	return new AddTrainingItemPage(driver);
}
public CurriculumReportPage openDisplayCurriculumList() {
	displayCurriculumListlnk().click();
	return new CurriculumReportPage(driver);
}
public AddTrainingItemPage openManageTrainingTypes() {
	manageTrainingTypeslnk().click();
	return new AddTrainingItemPage(driver);
}
}
