package ui.pages.actions.sections;

import java.util.List;
import java.util.stream.Collectors;

import ui.BrowserDriver;
import ui.pages.repo.sections.Content_TrainingManagementPageSectionRepo;

public class TrainingManagementPageSection_Content extends Content_TrainingManagementPageSectionRepo {
	BrowserDriver driver;

	public TrainingManagementPageSection_Content(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}

	public void getFirstName() {

	}

	public String getDescription() {
		return description().getText();
	}

	public String getAllowOnlineRegistration() {
		return allowOnlineRegistrationtxt().getText();
	}

	public String getCustomExamTrainingType() {
		return customExamTrainingType().getText();
	}

	public List<String> getDates() {
		return datesTxt().stream().map(e -> e.getText()).collect(Collectors.toList());
	}

	public String getDescriptionHistory() {
		// TODO Auto-generated method stub
		return historyTbl(2).getText();
	}

	public String getCommentHistory() {
		// TODO Auto-generated method stub
		return historyTbl(3).getText();
	}

	public String getCompletionDate() {
		recordScreenIframeSwitch();
		return eduText(9).getText();
	}

	public String getSigniture() {
		recordScreenIframeSwitch();
		return eduText(15).getText();
	}

	public String getCompletionDateTimeZone() {
		// TODO Auto-generated method stub
		return completionDateTimeZoneTxt().getAttribute("title");
	}

	public String getSignatureTimeZone() {
		// TODO Auto-generated method stub
		return signatureTimeZoneTxt().getAttribute("title");
	}

	public String getTrainingStatus() {
		recordScreenIframeSwitch();
		return trainingStatus().getText().trim();

	}

	public String getTrainingType() {
		recordScreenIframeSwitch();

		return trainingType().getText().trim();

	}

	public String getTrainingTitle() {
		recordScreenIframeSwitch();
		return trainingTitle().getText().trim();

	}

	public String getComments() {
		// TODO Auto-generated method stub
		return commentsTxt().getText();
	}

}
