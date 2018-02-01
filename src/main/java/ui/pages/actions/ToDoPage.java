package ui.pages.actions;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Set;

import ui.BrowserDriver;
import ui.pages.actions.sections.MenuNav_CommonSection;
import ui.pages.actions.sections.HomePageSection_CurriculumInfoNav;
import ui.pages.repo.ToDoPageRepo;

public class ToDoPage extends ToDoPageRepo {

	BrowserDriver driver;
	MenuNav_CommonSection menuNav_CommonSection;
	String windowHandle = null;
	 HomePageSection_CurriculumInfoNav curriculumInfoNav_homePage;

	public ToDoPage(BrowserDriver driver) {
		this.driver = driver;
		menuNav_CommonSection = new MenuNav_CommonSection(driver);
		setDriver(driver);
		 curriculumInfoNav_homePage = new HomePageSection_CurriculumInfoNav(driver);
	}

	public ToDoPage openTrainingItem(String trainingName) {
		waitUntilElementDisplayed(trainingItem(trainingName));
		trainingItem(trainingName).click();
		waitForAjax("waiting for esign page");
		return this;
	}

	public ToDoPage openSortOptions() {
		menuNav_CommonSection.openSort();
		return this;
	}

	public ToDoPage sortByTitleZtoA() {
		menuNav_CommonSection.sortByTitleZtoA();
		return this;
	}

	public ToDoPage openClassInformation() {
		classInformation().click();
		return this;
	}

	public List<String> getAssignedTrainingCodes() {
		return trainingCodesListLbl().stream().map(e -> e.getText()).collect(Collectors.toList());
	}

	public ToDoPage electronicallySign(String... parameters) {
		takeDebugScreenShot();
		waitUntilElementDisplayed(userIDtxt());
		userIDtxt().sendKeys(parameters[0]);
		pwdtxt().clear();
		pwdtxt().sendKeys(parameters[1]);
		electronicallySignlnk().click();
		ceContinuebtn().click();
		return this;
	}

	public String getToDoPageContent() {
		staticWait(1000);
		return toDoPagecontent().getText();
	}

	public String getPendingReports() {
		return trainingsPendingTxt().getText();
	}

	public String geteSignature(String userName) {

		staticWait(1000);
		return eSignature(userName).getText();
	}

	public ToDoPage searchTraining(String trainingItem) {
		menuNav_CommonSection.searchTraining(trainingItem);
		return this;
	}
	public ToDoPage openTrainingInfo(){
		toggleLnk().click();
		return this;
	}
	public ToDoPage openSearch() {
		menuNav_CommonSection.openSearch();
		return this;
	}
	public ToDoPage removeTraining() {
		toggleLnk().click();
		waitForAjax("waiting for training information");
		removeLnk().click();
		waitForAjax("waiting for remove overlay");
		removeLnk().click();
		waitForAjax("waiting for final removal");
		return this;
	}
	public ToDoPage testWebAddress() {
		
		testWebAddresslnk().click();
		staticWait(5000);
		Set<String> allWindows = getDriver().getWindowHandles();
		for (String currentWindow : allWindows) {
			driver.switchTo().window(currentWindow);
		}

		return this;
	}
	public ToDoPage completeCDWithWebAddress() {
		acknowledgeTxtBox().sendKeys("I ACKNOWLEDGE");
		continueBtn().click();
		continueLnk().click();
		return this;
	}
	
	public ToDoPage closeTestWebaddressBrowser(String windowHandle) {

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
	/*public String getWindowHandle() {
		return driver.getWindowHandle();
	}*/

	public String getTestWebAddressPageTitle() {
		return getDriver().getTitle();
	}
	public HistoryPage openHistoryPage() {
		curriculumInfoNav_homePage.openHistory();
		return new HistoryPage(driver);
	}

}
