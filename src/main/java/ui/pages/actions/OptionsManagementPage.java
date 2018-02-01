package ui.pages.actions;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.repo.OptionsManagementPageRepo;

public class OptionsManagementPage extends OptionsManagementPageRepo {
	BrowserDriver driver;

	public OptionsManagementPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public OptionsManagementPage openResetForgottenPasswordOption() {
		recordScreenIframeSwitch();
		resetForgottenPasswordLnk().click();
		return this;
	}

	public OptionsManagementPage chooseResetPasswordQuestions(int index) {
		popUpIframeViaRecordScreenIframeSwitch();
		resetForgotPasswordRadioBtn(index).click();
		if (index == 1) {
			forceUsersChkBox().click();
		}
		return this;
	}
	public OptionsManagementPage saveResetPasswordSetting(){
		recordScreenIframeSwitch();
		saveLnk().click();
		return this;
	}
	public boolean getResetPasswordRadioBtnStatus(int index){
		popUpIframeViaRecordScreenIframeSwitch();
		return resetForgotPasswordRadioBtn(index).isSelected();
	}
	public boolean getForceUsersAnswerPassSecuQuestionCheckBoxStatus(){
		popUpIframeViaRecordScreenIframeSwitch();
		return forceUsersChkBox().isSelected();
	}
	public OptionsManagementPage openMidLevelOrganization(String orgName){
		recordScreenIframeSwitch();
		midlevelOrganizationLnk(orgName).click();
		return this;
	}
	
	public String getSelectedOrganization(){
		recordScreenIframeSwitch();
		return selectedOrganizationLnk().getText();
		
	}
	public OptionsPage returnToOptions(){
		recordScreenIframeSwitch();
		returnToOptionsLnk().click();
		return new OptionsPage(driver);
		
	}
}
