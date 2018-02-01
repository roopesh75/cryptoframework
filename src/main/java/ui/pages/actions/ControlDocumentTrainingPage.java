package ui.pages.actions;

import java.util.Set;

import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.repo.ControlDocumentTrainingPageRepo;
import ui.utils.Tools;

public class ControlDocumentTrainingPage extends ControlDocumentTrainingPageRepo implements IEditTraining {

	BrowserDriver driver;
	String windowHandle = null;

	public ControlDocumentTrainingPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}

	public TrainingManagementPage addDescription(String description) {
		recordScreenIframeSwitch();
		courseDescriptionBox().clear();
		courseDescriptionBox().sendKeys(description);
		saveChanges().click();
		return new TrainingManagementPage(driver);
	}

	public TrainingManagementPage addControlDocumentTraining(String... parameters) {
		/*
		 * recordScreenIframeSwitch(); codeTxtbox().sendKeys(parameters[0]);
		 * titleTxtbox().sendKeys(parameters[1]); viewOrganizationTree().click();
		 * switchToNewWindow(); findByLinkText(parameters[2]).click();
		 * continueAfterSelectLnk().click(); Set<String> allWindows =
		 * getDriver().getWindowHandles(); for (String currentWindow : allWindows) {
		 * driver.switchTo().window(currentWindow); }
		 */
		enterCodeTitleSelectOrganization(parameters[0], parameters[1], parameters[2]);
		recordScreenIframeSwitch();
		if (parameters.length > 3) {
			webAddressTxtbox().sendKeys(parameters[3]);
			testWebAddress();
			
			closeTestWebaddressBrowser();
			recordScreenIframeSwitch();
		}
		new Select(selectLanguagedrp()).selectByValue("1");
		save("1").click();
		return new TrainingManagementPage(driver);
	}

	public ControlDocumentTrainingPage enterCodeTitleSelectOrganization(String... parameters) {
		recordScreenIframeSwitch();
		codeTxtbox().sendKeys(parameters[0]);
		titleTxtbox().sendKeys(parameters[1]);
		if (parameters.length > 2) {
			viewOrganizationTree().click();
			switchToNewWindow();
			findByLinkText(parameters[2]).click();
			continueAfterSelectLnk().click();
			Set<String> allWindows = getDriver().getWindowHandles();
			for (String currentWindow : allWindows) {
				driver.switchTo().window(currentWindow);
			}
		}
		return this;
	}

	public ControlDocumentTrainingPage enterApprovalEffectiveDates() {
		recordScreenIframeSwitch();
		approveDateTxtbox().clear();
		approveDateTxtbox().sendKeys(Tools.getCurrentDate("MM/dd/yyyy"));
		effectiveDateTxtbox().clear();
		effectiveDateTxtbox().sendKeys(Tools.getCurrentDate("MM/dd/yyyy"));
		return this;
	}

	public ControlDocumentTrainingPage enterAssortedFields(String... parameters) {
		recordScreenIframeSwitch();
		abbreviationTxt().sendKeys("abbr");
		versionDescTxt().sendKeys("This is a new version");
		new Select(selectCategorydrp(Integer.parseInt(parameters[0]))).selectByValue("8468");
		if (selectLanguagedrp().isEnabled())
			new Select(selectLanguagedrp()).selectByValue("1");

		webAddressTxtbox().sendKeys("www.google.com");
		descriptionTxt().sendKeys("This is a test to create Control Document");
		courseCommentsBox().sendKeys("This is a sample comment while creating Control Document");
		return this;
	}

	public String getCode() {
		recordScreenIframeSwitch();
		return codeTxtbox().getAttribute("value");
	}

	public String getWebAddress() {
		recordScreenIframeSwitch();
		return webAddressTxtbox().getAttribute("value");
	}

	public String getApproveDate() {
		recordScreenIframeSwitch();
		return approveDateTxtbox().getAttribute("value");
	}

	public String getEffectiveDate() {
		recordScreenIframeSwitch();
		return effectiveDateTxtbox().getAttribute("value");
	}

	public String getTitle() {
		recordScreenIframeSwitch();
		return titleTxtbox().getAttribute("value");
	}

	public String getOrganization() {
		recordScreenIframeSwitch();
		return organizationTxtBox().getAttribute("value");
	}

	public ControlDocumentTrainingPage testWebAddress() {
		recordScreenIframeSwitch();
		windowHandle = driver.getWindowHandle();
		testWebAddresslnk().click();
		staticWait(5000);
		Set<String> allWindows = getDriver().getWindowHandles();
		for (String currentWindow : allWindows) {
			driver.switchTo().window(currentWindow);
		}

		return this;
	}

	public ControlDocumentTrainingPage closeTestWebaddressBrowser() {

		Set<String> allWindows = getDriver().getWindowHandles();
		for (String currentWindow : allWindows) {
			if (!currentWindow.equals(windowHandle)) {
				driver.switchTo().window(currentWindow);
				driver.close();
				driver.switchTo().window(windowHandle);
			}

		}
		return this;

	}

	public String getTestWebAddressPageTitle() {
		return getDriver().getTitle();
	}

	public Boolean isControlDocumentPage() {
		recordScreenIframeSwitch();
		return codeTxtbox().isDisplayed();
	}

	public TrainingManagementPage saveControlDocument(String index) {
		recordScreenIframeSwitch();
		save(index).click();
		return new TrainingManagementPage(driver);
	}

	@Override
	public TrainingManagementPage addCourseComments(String courseComments) {
		recordScreenIframeSwitch();
		courseCommentTxtBox().clear();
		courseCommentTxtBox().sendKeys(courseComments);
		saveChanges().click();
		return new TrainingManagementPage(driver);
	}

}
