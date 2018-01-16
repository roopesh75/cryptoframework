package ui.pages.repo;

import org.openqa.selenium.By;

import ui.BrowserElement;
import ui.BrowserElementImpl;
import ui.UiBase;

public class TrainingManagementPageRepo extends UiBase {
	protected BrowserElement returnlnk() {
		return findByLinkText("Return");
		//
	}
	
	protected BrowserElement returnToTrainingMenulnk() {
		return findByLinkText("Return to Training Menu");
	}
	protected BrowserElement singleResponseWindowTxt() {
		return findById("fauxPopupTitleText");
	}
	protected BrowserElement addQuestionlnk() {
		return findByLinkText("Add Question");
	}
	protected BrowserElement answerTextAreaTxtArea() {
		return findByName("AnswerText");
	}
	//
	protected BrowserElement questionWithSingleResponselnk() {
		return findByLinkText("Question with a Single Response");
	}
	protected BrowserElement qualifiedlnk() {
		return findByLinkText("Qualified");
	}
	
	protected BrowserElement returnReportsLnk() {
		return findByLinkText("Return to Reports");
	}

	protected BrowserElement reportTitleLbl() {
		return findById("ReportTitle");
	}

	protected BrowserElement incompleteLnk() {
		return findByLinkText("Incomplete");
	}

	protected BrowserElement completeLnk() {
		return findByLinkText("Complete");
	}
	protected BrowserElement saveQuestionLnk() {
		return findByLinkText("Save Question");
	}
	
	protected BrowserElement updateCompletion_popupLnk() {
		return getDriver().findElement(By.id("fauxPopBorderOut")).findByLinkText("Update Completion");
	}
	protected BrowserElement updateCompletionLnk() {
		return findByLinkText("Update Completion");
	}
	//

	protected BrowserElement rosterSltBox() {
		return findByName("Filter");
	}
	protected BrowserElement TableTxt() {
		return findByClass("PRINTTableBorder");
	}

	protected BrowserElement rosterCompletionDateLbl(int index) {
		return new BrowserElementImpl(getDriver().findElements(By.cssSelector("span[title='UTC-5:00']")).get(index),getDriver().getWebDriver());
	}
	protected BrowserElement rosterTrainingCompletionStatusLbl(int index) {
		return new BrowserElementImpl(getDriver().findElement(By.className("PRINTTableBorder")).findElements(By.className("EduText")).get(index),getDriver().getWebDriver());
	}
	
	protected BrowserElement addUsersToClassRosterLbl() {
		return findByXpath("//div[text()='Add Users to Class Roster']");
	}
	//elements from Add users to class roaster page
	protected BrowserElement keywordTxt() {
		return findByName("keyword");
	}
	protected BrowserElement searchLnk() {
		return findByLinkText("Search");
	}
	protected BrowserElement useridsDrpdwn() {
		return findByName("userids");
	}
	protected BrowserElement addUserBtn() {
		return findByXpath("//div[@id='txtaddbutton']");
	}
	
	protected BrowserElement continueLnk() {
		return findByLinkText("Continue");
	}
	//
	protected BrowserElement addedUsrDrpdwn() {
		return findByName("addeduser");
	}
	//View curriculum ->Training Items->Add a training item
	protected BrowserElement trainingTypesDrpdwn() {
		return findByName("courseTypeID");
	}
	//View curriculum ->Training Items->Add a training item
	protected BrowserElement titleOrCodeDrpdwn() {
		return findByName("LikeType");
	}
	//View curriculum ->Training Items->Add a training item
	protected BrowserElement availableTrainingDrpdwn() {
		return findByName("Course_IDs");
	}
	protected BrowserElement trainingToAddDrpdwn() {
		return findByName("addedcourse");
	}
	protected BrowserElement DateModified() {
		return findByXpath("//a[@title='Sort By DATE MODIFIED']");
	}
	
	
}
