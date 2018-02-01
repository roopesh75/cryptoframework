package ui.pages.actions;

import java.util.Set;

import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.repo.InstructorLedCoursePageRepo;
import ui.utils.Tools;

public class InstructorLedCoursePage extends InstructorLedCoursePageRepo {
	BrowserDriver driver;

	public InstructorLedCoursePage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}
	
	public TrainingManagementPage addInstructorLedCourse(String... parameters) {
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
		completionExpiresTxt().sendKeys("1");
		totalCourseTimeTxt().sendKeys("1");
		courseFeeTxt().sendKeys("1000");
		courseDescriptionTxt().sendKeys("This is a sample description for ILC course on "+Tools.getCurrentDate("MM/dd/yyyy"));
		courseCommentsTxt().sendKeys("This is the new comment for ILC course on "+Tools.getCurrentDate("MM/dd/yyyy"));
		onlineRegnradio().click();
		save().click();
		return new TrainingManagementPage(driver);
		
	}
	
	
}
