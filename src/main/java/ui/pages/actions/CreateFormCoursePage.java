package ui.pages.actions;

import java.util.Set;

import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.repo.CreateFormCoursePageRepo;

public class CreateFormCoursePage extends CreateFormCoursePageRepo {
	BrowserDriver driver;

	public CreateFormCoursePage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}

	public TrainingManagementPage addForm(String... parameters) {
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
}
