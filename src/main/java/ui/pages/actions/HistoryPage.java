package ui.pages.actions;

import java.util.List;
import java.util.stream.Collectors;

import ui.BrowserDriver;
import ui.pages.actions.sections.MenuNav_CommonSection;
import ui.pages.repo.HistoryPageRepo;

public class HistoryPage extends HistoryPageRepo {

	BrowserDriver driver;
	MenuNav_CommonSection menuNav_CommonSection;
	public HistoryPage(BrowserDriver driver) {
		this.driver = driver;
		menuNav_CommonSection= new MenuNav_CommonSection(driver);
		setDriver(driver);
	}

	public String getCompletionInfoPageContent() {
		return completionInfoPageContent().getText();
	}
	public String getHistoryPageContent() {
		return historyPageContent().getText();
	}

	public String getCompletionDate() {
		
		return dateInfoLbl(0).getText();
	}
	public List<String> getCompletionDates() {
		dateInfosLbl().get(0).getText();
		return dateInfosLbl().stream().map(e -> e.getText()).collect(Collectors.toList());
	}

	public String getSignature() {
		
		return dateInfoLbl(1).getText();
	}

	public HistoryPage openTrainingItemGeneralInfo(String trainingName) {
		trainingitemlnk(trainingName).click();
		return this;
	}

	public String getCompletionDateTimeZone() {
		return completionLbl().getAttribute("title");
	}

	public HistoryPage openSortOptions() {
		menuNav_CommonSection.openSort();
		return this;
	}
	public HistoryPage sortByCompletionDateOldest() {
		menuNav_CommonSection.sortByCompletionDateOldestBtn();
		return this;
	}

	public String getSignatureTimeZone() {
		return signatureLbl().getAttribute("title");
	}

	public boolean isHistoryPage() {
		return search().isDisplayed();
	}
	public String getTrainingCompletedOnDate() {
		
		return trainingcompletionDate().getText();
	}
}
