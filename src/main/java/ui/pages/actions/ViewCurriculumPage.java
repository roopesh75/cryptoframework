package ui.pages.actions;

import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.repo.ViewCurriculumPageRepo;

public class ViewCurriculumPage extends ViewCurriculumPageRepo {
	BrowserDriver driver;
	AdminNav_CommonSection adminNav_CommonSection;

	public ViewCurriculumPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		adminNav_CommonSection = new AdminNav_CommonSection(driver);
	}
	public ViewCurriculumPage openRelationships(){
		recordScreenIframeSwitch();
		relationshipsLnk().click();
		return this;
	}
	public ViewCurriculumPage addSubCurriculum(String curriculum){
		recordScreenIframeSwitch();
		toggleLnk().click();
		addSubCurriculumLnk().click();
		new Select(currListSltBox()).selectByVisibleText(curriculum);
		addRelationshipBtn().click();
		addRelationshipBtn2().click();
		return this;
	}
	public boolean isViewCurriculumPage() {
		recordScreenIframeSwitch();
		return curriculumTitle().isDisplayed();
	}
	
	public String getCurriculumInfoContent() {
		staticWait(1000);
		return currInfoContent().getText();
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

	public ViewCurriculumPage setCurriculumStatus(String... parameters) {
		recordScreenIframeSwitch();

		manageStatusApprovalDate().sendKeys(parameters[0]);
		curriculumStatus().click();
		manageStatusEffectiveDate().sendKeys(parameters[0]);
		curriculumStatus().click();
		setStatusBtn().click();
		staticWait(1000);
		curriculumConfirmBtn().click();
		return this;
	}

	public String getViewCurriculumStatus() {
		staticWait(1000);
		return viewCurriculumPageContent().getText();
	}

	public String getCurriculumStatus() {
		recordScreenIframeSwitch();
		staticWait(2000);
		return curriculumStatus().getText();
	}

	public AssignmentPage openAssignments() {
		return adminNav_CommonSection.openAssignmentPage();
	}

}