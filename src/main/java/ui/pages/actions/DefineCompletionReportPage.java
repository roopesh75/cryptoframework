package ui.pages.actions;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.repo.DefineCompletionReportPageRepo;

public class DefineCompletionReportPage extends DefineCompletionReportPageRepo{
	BrowserDriver driver;

	public DefineCompletionReportPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}


	public TrainingManagementPage runCompletionReport(String...parameters) {
		recordScreenIframeSwitch();
		trngCodeTxtbox().sendKeys(parameters[1]);
		userIdTxtbox().sendKeys(parameters[0]);
		runReportLnk().click();
		return new TrainingManagementPage(driver);	
	}


	public TrainingManagementPage saveCompletionReport(String... parameters) {
		// TODO Auto-generated method stub
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
