package ui.pages.actions;

import java.util.Set;

import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.repo.CustomExamPageRepo;
import ui.pages.repo.InstructorLedCoursePageRepo;

public class CustomExamPage extends CustomExamPageRepo implements ICustomExam {
	BrowserDriver driver;

	public CustomExamPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}
	
	public TrainingManagementPage addCustomExam(String... parameters) {
		recordScreenIframeSwitch();
		codeTxtbox().sendKeys(parameters[0]);
		titleTxtbox().sendKeys(parameters[1]);
		viewOrganizationTree().click();
		switchToNewWindow();
		findByLinkText(parameters[2]).click();
		continueAfterSelectLnk().click();
		Set<String> allWindows = getDriver().getWindowHandles();
		for (String currentWindow : allWindows) {
			driver.switchTo().window(currentWindow);
		}
		recordScreenIframeSwitch();
		new Select(selectLanguagedrp()).selectByValue("1");
		save().click();
		return new TrainingManagementPage(driver);
		
	}

	@Override
	public String getManageTrainingType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
