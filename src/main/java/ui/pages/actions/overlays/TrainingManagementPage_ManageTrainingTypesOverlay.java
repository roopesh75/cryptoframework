package ui.pages.actions.overlays;

import java.util.Set;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.GroupManagementPage;
import ui.pages.actions.TrainingManagementPage;
import ui.pages.repo.overlays.GroupManagementPage_AddUsersDirectlyOverlayRepo;
import ui.pages.repo.overlays.TrainingManagementPage_ManageTrainingTypesOverlayRepo;

public class TrainingManagementPage_ManageTrainingTypesOverlay extends TrainingManagementPage_ManageTrainingTypesOverlayRepo {

	BrowserDriver driver;
	String userId;
	public TrainingManagementPage_ManageTrainingTypesOverlay(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}
	
	public String getManageTrainingType() {
		staticWait(2000);
		popUpIframeViaRecordScreenIframeSwitch();
		return trainingType().getAttribute("innerText");
	}
	
	public TrainingManagementPage closeManageTrainingTypes() {
		//popUpIframeViaRecordScreenIframeSwitch();
		recordScreenIframeSwitch();
		manageTrainingTypesClose().click();
		return new TrainingManagementPage(driver);
	}
	
	public TrainingManagementPage activateTraining() {
		//popUpIframeViaRecordScreenIframeSwitch();
		//popUpIframeSwitch();
		activateVersionlnk().click();
		return new TrainingManagementPage(driver);
	}
	

}