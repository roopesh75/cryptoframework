package ui.pages.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.repo.DefineCompletionReportPageRepo;

public class DefineCompletionReportPage extends DefineCompletionReportPageRepo{
	BrowserDriver driver;

	public DefineCompletionReportPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isDefineCompletionReportPage(){
		recordScreenIframeSwitch();
		return selectUserType().isEnabled();
	}
	public TrainingManagementPage runCustomCompletionReport_Enabled_Disabled(String... parameters){
		recordScreenIframeSwitch();	
		new Select(selectUserType()).selectByVisibleText(parameters[0]);
		new Select(selectUserAttr1()).selectByVisibleText(parameters[1]);
		new Select(selectUserOperator1()).selectByVisibleText(parameters[2]);
		userIdValue1Txtbox().sendKeys(parameters[3]);		
		new Select(selectUserAttr2()).selectByVisibleText(parameters[1]);
		new Select(selectUserOperator2()).selectByVisibleText(parameters[2]);
		userIdValue2Txtbox().sendKeys(parameters[4]);
		runReportLnk().click();		
		return new TrainingManagementPage(driver);
	}
	
	public TrainingManagementPage runCompletionReport(String...parameters) {
		recordScreenIframeSwitch();
		trngCodeTxtbox().sendKeys(parameters[1]);
		userIdValue1Txtbox().sendKeys(parameters[0]);
		runReportLnk().click();
		return new TrainingManagementPage(driver);	
	}


	public TrainingManagementPage saveCompletionReport(String... parameters) {
		curriculumReportCheckBox().click();
		reportNameTxtBox().sendKeys(parameters[0]);
		saveReportLnk().click();
		return new TrainingManagementPage(driver);	
	}


	public TrainingManagementPage runReportForCurriculum(String...parameters) {
		recordScreenIframeSwitch();
		trainingCodeSltBox().sendKeys(parameters[0]);
		trainingCodeValueSltBox().sendKeys(parameters[1]);
		runReportLnk().click();
		return new TrainingManagementPage(driver);		
	}
}
