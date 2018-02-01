package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.repo.AssignmentManagementPageRepo;

public class AssignmentManagementPage extends AssignmentManagementPageRepo {
	BrowserDriver driver;
	AdminNav_CommonSection adminNav_CommonSection;

	public AssignmentManagementPage(BrowserDriver driver) {
		this.driver = driver;
		adminNav_CommonSection = new AdminNav_CommonSection(driver);
		setDriver(driver);
		adminNav_CommonSection = new AdminNav_CommonSection(driver);
	}
	public AssignmentPage returnToAssignmentPage(){
		recordScreenIframeSwitch();
		returnLnk().click();
		return new AssignmentPage(driver);
	}
	
	public String getTrainingItem(String trainingName){
		recordScreenIframeSwitch();
		return trainingItem(trainingName).getText().trim();
	}	
	public String getPRINTTableBorder(){
		recordScreenIframeSwitch();
		return TableTxt().getText();
	}
	public AssignmentPage returnFromAssignmentReportsToAssignmentPage() {
		recordScreenIframeSwitch();
		returnAssignmentMenuLnk().click();
		return new AssignmentPage(driver);
	}	
	public LoginPage logOut() {
		return adminNav_CommonSection.logOut();
	}
	
}
