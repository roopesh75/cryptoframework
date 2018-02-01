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

	public String getDescription() {
		return description().getText();
	}

	public String getAbbreviation() {
		return abbreviationTxt().getText();
	}

	public String getAllowOnlineRegistration() {
		return allowOnlineRegistrationtxt().getText();
	}

	public String getCustomExamTrainingType() {
		return customExamTrainingType().getText();
	}

	public List<String> getDates() {
		List<String> dates = datesTxt().stream().map(e -> e.getText()).collect(Collectors.toList());
		dates.remove(0);
		return dates;
	}

	public String getDescriptionHistory() {
		return historyTbl(3).getText();
	}
	public String getQuizRevisionPageContent() {
		return quizRevisionPage().getText();
	}
	public String getCommentHistory() {
		return historyTbl(4).getText();
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
		return completionDateTimeZoneTxt().getAttribute("title");
	}

	public String getSignatureTimeZone() {
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
		return commentsTxt().getText();
	}

	public String getLanguage() {
		return languageTxt().getText();
	}

	public String getWebAddress() {
		return webAddressTxt().getText();
	}

	public String getEffectiveDate() {
		return effectiveDateTxt().getText();
	}

	public String getApprovalDate() {
		return approvalDateTxt().getText();
	}

	public String getVersionDescription() {
		return versionDescriptionTxt().getText();
	}

	public String getCode() {
		return codeTxt().getText();
	}

	public String getCategory() {
		return categoryTxt().getText();
	}

	public String getCompletionType() {
		recordScreenIframeSwitch();
		return completionType().getText().trim();

	}

	public String getCompletionExpires() {
		recordScreenIframeSwitch();
		return completionExpires().getText().trim();

	}

	public String getContentScreenTitle() {
		recordScreenIframeSwitch();
		return contentScreenTitleLbl().getText();
	}

}
