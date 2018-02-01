package ui.pages.actions;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.ibm.icu.text.SimpleDateFormat;

import ui.BrowserDriver;
import ui.pages.actions.overlays.ISearchForTrainingOverlay;
import ui.pages.actions.overlays.SearchForTrainingOverlay;
import ui.pages.actions.overlays.TrainingManagementPage_ManageTrainingTypesOverlay;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.actions.sections.TrainingManagementPageSection_Content;
import ui.pages.actions.sections.TrainingManagementPageSection_TrainingNavigation;
import ui.pages.repo.TrainingManagementPageRepo;

public class TrainingManagementPage extends TrainingManagementPageRepo
		implements ISearchForTrainingOverlay, ICustomExam {
	BrowserDriver driver;
	TrainingManagementPageSection_Content content_TrainingManagementPageSection;
	TrainingManagementPageSection_TrainingNavigation trainingNavigation_TrainingManagementPageSection;
	ISearchForTrainingOverlay searchForTrainingOverlay;
	AdminNav_CommonSection adminNav_CommonSection;
	TrainingManagementPage_ManageTrainingTypesOverlay trainingManagementPage_ManageTrainingTypesOverlay;

	public TrainingManagementPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		content_TrainingManagementPageSection = new TrainingManagementPageSection_Content(driver);
		trainingNavigation_TrainingManagementPageSection = new TrainingManagementPageSection_TrainingNavigation(driver);
		searchForTrainingOverlay = new SearchForTrainingOverlay(driver);

		adminNav_CommonSection = new AdminNav_CommonSection(driver);
		trainingManagementPage_ManageTrainingTypesOverlay = new TrainingManagementPage_ManageTrainingTypesOverlay(
				driver);
	}

	public IEditTraining openEditGeneralInformation() {
		return trainingNavigation_TrainingManagementPageSection.openEditGeneralInformation();

	}

	public TrainingManagementPage openGeneralInformation() {
		return trainingNavigation_TrainingManagementPageSection.openGeneralInformation();

	}
	public AssignmentPage openAssignmentPage() {
		return adminNav_CommonSection.openAssignmentPage();

	}

	public TrainingManagementPage openOrganizations() {
		return trainingNavigation_TrainingManagementPageSection.openOrganizations();

	}

	public TrainingManagementPage closeSearchTrainingOverlay() {
		return searchForTrainingOverlay.closeSearchTrainingOverlay();
	}

	public TrainingManagementPage searchTraining(String trainingCode) {
		return searchForTrainingOverlay.searchTraining(trainingCode);
	}

	public TrainingManagementPage chooseVersion(String trainingCode, String version) {
		return searchForTrainingOverlay.chooseVersion(trainingCode, version);
	}

	public TrainingPage openReturn() {
		trainingNavigation_TrainingManagementPageSection.openReturn();
		return new TrainingPage(driver);
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return content_TrainingManagementPageSection.getDescription();
	}

	public String getSearchStatus() {
		// TODO Auto-generated method stub
		return searchForTrainingOverlay.getSearchStatus();
	}

	public TrainingPage returnFromTrainingManagement() {
		recordScreenIframeSwitch();
		returnlnk().click();
		return new TrainingPage(driver);
	}
	public TrainingManagementPage returnFromQuizRevision() {
		returnlnk().click();
		return this;
	}

	public String getReturnedRecordsCount() {
		return rowsReturned().getText();
	}

	public TrainingPage returnFromAssignmentOrCompletionReport() {
		recordScreenIframeSwitch();
		returnToTrainingMenulnk().click();
		return new TrainingPage(driver);
	}

	public String getAllowOnlineRegistration() {
		// TODO Auto-generated method stub
		return content_TrainingManagementPageSection.getAllowOnlineRegistration();
	}

	public String getCompletionDate() {
		// TODO Auto-generated method stub
		return content_TrainingManagementPageSection.getCompletionDate();
	}

	public String getSigniture() {
		return content_TrainingManagementPageSection.getSigniture();
	}

	public TrainingManagementPage openHistory() {
		return trainingNavigation_TrainingManagementPageSection.openHistory();
	}

	public QuizPage openQuiz() {
		return trainingNavigation_TrainingManagementPageSection.openQuiz();
	}

	public ClassesPage openClasses() {
		return trainingNavigation_TrainingManagementPageSection.openClasses();
	}

	public String getCustomExamTrainingType() {
		recordScreenIframeSwitch();
		return content_TrainingManagementPageSection.getCustomExamTrainingType();
	}

	public String getTrainingTitle() {
		return content_TrainingManagementPageSection.getTrainingTitle();
	}

	public UsersPage openUsersPage() {
		adminNav_CommonSection.openUsersPage();
		return new UsersPage(driver);
	}
	public AssignmentPage openAssignmentsPage() {
		adminNav_CommonSection.openAssignmentPage();
		return new AssignmentPage(driver);
	}
	public AddClassPage openAddClass() {
		return trainingNavigation_TrainingManagementPageSection.openAddClass();

	}

	public List<String> getDates() {
		return content_TrainingManagementPageSection.getDates();
	}

	public String getDescriptionHistory() {
		return content_TrainingManagementPageSection.getDescriptionHistory();

	}


	public String getWebAddress() {
		return content_TrainingManagementPageSection.getWebAddress();

	}

	public String getCommentHistory() {
		return content_TrainingManagementPageSection.getCommentHistory();

	}

	public TrainingManagementPage openQualifiedTraining(String userName) {
		recordScreenIframeSwitch();
		findByLinkText(userName).click();
		return new TrainingManagementPage(driver);
	}

	public String getCompletionDateTimeZone() {
		// TODO Auto-generated method stub
		return content_TrainingManagementPageSection.getCompletionDateTimeZone();
	}

	public String getSignatureTimeZone() {
		// TODO Auto-generated method stub
		return content_TrainingManagementPageSection.getSignatureTimeZone();
	}

	public String getTrainingStatus() {
		return content_TrainingManagementPageSection.getTrainingStatus();
	}

	public String getTrainingType() {
		return content_TrainingManagementPageSection.getTrainingType();
	}

	public String getCompletionType() {
		return content_TrainingManagementPageSection.getCompletionType();
	}

	public String getCompletionExpires() {
		return content_TrainingManagementPageSection.getCompletionExpires();
	}

	public TrainingManagementPage openCompletionReport(String reportName) {
		findByLinkText(reportName).click();
		return new TrainingManagementPage(driver);
	}

	public TrainingManagementPage returnToReports() {
		returnReportsLnk().click();
		return new TrainingManagementPage(driver);
	}

	public String getReportTitle() {
		return reportTitleLbl().getText();
	}

	public void updateCompletion(String trainingStatus) {
		recordScreenIframeSwitch();
		if (trainingStatus.equalsIgnoreCase("incomplete")) {
			incompleteLnk().click();
			updateCompletionLnk().click();
		} else {
			completeLnk().click();
			updateCompletionLnk().click();
			updateCompletion_popupLnk().click();
		}

	}

	public TrainingManagementPage incompleteUpdateCompletion() {
		popUpIframeViaRecordScreenIframeSwitch();
		new Select(completionDrpdwn()).selectByValue("1");
		recordScreenIframeSwitch();
		updateCompletionLnk().click();
		return this;
	}

	public TrainingManagementPage enterManageCompletionFields() {
		popUpIframeViaRecordScreenIframeSwitch();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		completionDate().clear();
		completionDate().sendKeys(date1);
		completionDate().click();
		return this;
	}

	public String getCompletion() {
		return new Select(completionDrpdwn()).getFirstSelectedOption().getText();
	}

	public String getExpirationDate() {
		return expirationDate().getAttribute("value");
	}

	public TrainingManagementPage selectRosterHistory(String... parameters) {
		new Select(rosterSltBox()).selectByVisibleText(parameters[0]);
		return new TrainingManagementPage(driver);

	}

	public String getTrainingStatusOnRosterOrCurriculum(int index) {
		return rosterTrainingCompletionStatusLbl(index).getText();
	}

	public TrainingManagementPage openIncompleteStatus() {
		recordScreenIframeSwitch();
		staticWait(10000);
		incompleteLnk().click();
		return this;
	}

	public String getCurrentRosterView() {
		return new Select(rosterSltBox()).getFirstSelectedOption().getText();
	}

	public String getCompletionDateFromRosterView(int index) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/M/yyyy HH:mm:ss a");
		try {
			return simpleDateFormat.parse(rosterCompletionDateLbl(index).getText()).getTime() + "";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}

	public HomePage openKnowledgeCenter() {
		adminNav_CommonSection.openKnowledgeCenter();
		return new HomePage(driver);
	}

	public String getManageTrainingType() {
		return trainingManagementPage_ManageTrainingTypesOverlay.getManageTrainingType();
	}

	public TrainingManagementPage closeManageTrainingTypes() {
		return trainingManagementPage_ManageTrainingTypesOverlay.closeManageTrainingTypes();
	}

	public AddQuestionCustomExamPage openCustomExam() {
		return trainingNavigation_TrainingManagementPageSection.openCustomExam();

	}

	@Override
	public TrainingManagementPage addCustomExam(String... parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	public TrainingManagementPage openActivateVersion() {
		return trainingNavigation_TrainingManagementPageSection.openActivateVersion();

	}

	public LoginPage logOut() {
		return adminNav_CommonSection.logOut();
	}

	public TrainingManagementPage activateTraining() {
		return trainingManagementPage_ManageTrainingTypesOverlay.activateTraining();
	}

	public TrainingManagementPage openAddQuestion() {
		recordScreenIframeSwitch();
		addQuestionlnk().click();
		return this;
	}

	public TrainingManagementPage openQuestionWithSingleResponse() {
		popUpIframeViaRecordScreenIframeSwitch();
		questionWithSingleResponselnk().click();
		return this;

	}

	public TrainingManagementPage addQuestionAnswer(String...parameters) {
		popUpIframeViaRecordScreenIframeSwitch();
		switchToFrame("ifrmEditor");
		switchToFrame("InfraWebHtmlEditor_tw");
		findByCssSelector("body").sendKeys(parameters[0]);
		popUpIframeViaRecordScreenIframeSwitch();
		answerTextAreaTxtArea().sendKeys(parameters[1]+Keys.ENTER);
		if (parameters.length>2) {
			answerTextAreaTxtArea().sendKeys(parameters[2]+Keys.ENTER);
			answerTextAreaTxtArea().sendKeys(parameters[3]+Keys.ENTER);
		}
		return this;
	}

	public TrainingManagementPage openSaveQuestion() {
		recordScreenIframeSwitch();
		saveQuestionLnk().click();
		return this;
	}

	public String getPRINTTableBorder() {
		recordScreenIframeSwitch();
		return TableTxt().getText();
	}

	public String getPRINTTableBorder(int index) {
		recordScreenIframeSwitch();
		return TableIndexTxt(index).getText();
	}

	public String getSingleResponseWindowTitle() {
		recordScreenIframeSwitch();
		return singleResponseWindowTxt().getText();
	}

	public boolean hasResponseTextBox() {
		popUpIframeViaRecordScreenIframeSwitch();
		return answerTextAreaTxtArea().isDisplayed();
	}

	public String getComments() {
		return content_TrainingManagementPageSection.getComments();
	}

	public ControlDocumentTrainingPage openCreateNewVersion() {
		trainingNavigation_TrainingManagementPageSection.openCreateNewVersion();
		return new ControlDocumentTrainingPage(driver);
	}

	public String getCategory() {

		return content_TrainingManagementPageSection.getCategory();
	}

	public String getLanguage() {

		return content_TrainingManagementPageSection.getLanguage();
	}

	public TrainingManagementPage openRoster() {
		return trainingNavigation_TrainingManagementPageSection.openRoster();

	}

	public TrainingManagementPage openAddUser() {
		return trainingNavigation_TrainingManagementPageSection.openAddUser();

	}

	public Boolean isAddUsersToClassRosterPage() {
		recordScreenIframeSwitch();
		return addUsersToClassRosterLbl().isDisplayed();

	}

	public TrainingManagementPage addUserstoClassRoster(String... parameters) {
		recordScreenIframeSwitch();
		Actions ac = new Actions(driver);
		keywordTxt().sendKeys(parameters[0]);
		searchLnk().click();
		Select userIds = new Select(useridsDrpdwn());
		if (userIds.getOptions().size() > 0) {
			for (int i = 0; i < Integer.parseInt(parameters[1]); i++) {
				userIds.selectByIndex(i);
				ac.keyDown(Keys.CONTROL);
			}
			addUserBtn().click();
		}
		return this;

	}

	public TrainingManagementPage continueToClassRosterOrCurriculum() {
		recordScreenIframeSwitch();
		continueLnk().click();
		return this;
	}

	public List<String> getUsersToAddTextCount() {
		recordScreenIframeSwitch();
		Select selectedUserIds = new Select(addedUsrDrpdwn());
		List<String> arrList = new ArrayList<String>();
		for (int i = 0; i < selectedUserIds.getOptions().size(); i++) {
			arrList.add(selectedUserIds.getOptions().get(i).getText());
		}
		return arrList;
	}

	public TrainingManagementPage openAddTrainingItem() {
		return trainingNavigation_TrainingManagementPageSection.openAddTrainingItem();

	}

	public TrainingManagementPage addTrainingItemToCurriculum(String... parameters) {
		recordScreenIframeSwitch();
		Actions ac = new Actions(driver);
		new Select(trainingTypesDrpdwn()).selectByValue(parameters[0]);
		new Select(titleOrCodeDrpdwn()).selectByValue(parameters[1]);
		keywordTxt().sendKeys(parameters[2]);
		searchLnk().click();
		Select trainingTypes = new Select(availableTrainingDrpdwn());
		if (trainingTypes.getOptions().size() > 0) {
			for (int i = 0; i < Integer.parseInt(parameters[3]); i++) {
				trainingTypes.selectByIndex(i);
				ac.keyDown(Keys.CONTROL);
			}
			addUserBtn().click();
		}
		return this;

	}

	public List<String> getTrainingToAdd(int index) {
		recordScreenIframeSwitch();
		Select selectedTrainings = new Select(trainingToAddDrpdwn());
		List<String> arrList = new ArrayList<String>();
		String[] trainingTitle;
		for (int i = 0; i < selectedTrainings.getOptions().size(); i++) {
			trainingTitle = selectedTrainings.getOptions().get(i).getText().split(" ");
			arrList.add(trainingTitle[0]);
		}
		return arrList;
	}

	public TrainingManagementPage sortByDateModified() {
		recordScreenIframeSwitch();
		DateModified().click();
		return this;
	}

	public ViewCurriculumPage openManageStatus() {
		return trainingNavigation_TrainingManagementPageSection.openManageStatus();

	}

	public String getAbbreviation() {
		return content_TrainingManagementPageSection.getAbbreviation();

	}

	public TrainingManagementPage openEquivalancies() {
		return trainingNavigation_TrainingManagementPageSection.openEquivalancies();

	}

	public TrainingManagementPage openAddTrainingItemEquivalency() {
		return trainingNavigation_TrainingManagementPageSection.openAddTrainingItemEquivalency();
	}

	public String getContentScreenTitle() {
		return content_TrainingManagementPageSection.getContentScreenTitle();
	}

	public String getResultsFromSearchOverlay() {
		return searchForTrainingOverlay.getResultsFromSearchOverlay();
	}

	public String getTrainingEquivalencyAddedTime() {
		return trainingEquivalencyAddedTimeLbl().getText();
	}

	public TrainingManagementPage openTrainingType(String trainingType) {
		recordScreenIframeSwitch();
		findByLinkText(trainingType).click();
		return this;
	}

	public TrainingManagementPage openDisabledTrainingTypes() {
		trainingNavigation_TrainingManagementPageSection.openDisabledTrainingTypes();
		return this;
	}

	public TrainingManagementPage makeDisabled() {
		recordScreenIframeSwitch();
		disableTrainingLnk().click();
		acceptAlert();
		return this;
	}

	public TrainingManagementPage makeEnabled() {
		enableTrainingLnk().click();
		return this;
	}

	public TrainingManagementPage openEnabledTrainingTypes() {
		trainingNavigation_TrainingManagementPageSection.openEnabledTrainingTypes();
		return this;
	}

	public TrainingManagementPage openAddTrainingType() {
		trainingNavigation_TrainingManagementPageSection.openAddTrainingType();
		return this;
	}

	public TrainingManagementPage addTrainingType(String... parameters) {
		popUpIframeViaRecordScreenIframeSwitch();
		new Select(systemTypeSltBox()).selectByVisibleText(parameters[0]);
		typeNameTxtBox().sendKeys(parameters[1]);
		abbrTxtBox().sendKeys(parameters[2]);
		recordScreenIframeSwitch();
		createTrainingTypeLnk().click();
		return this;
	}

	public boolean isManageTrainingTypePage() {
		// TODO Auto-generated method stub
		return trainingNavigation_TrainingManagementPageSection.isManageTrainingTypePage();
	}

	public String getContentManageTrainingTypes() {
		popUpIframeViaRecordScreenIframeSwitch();
		return eduBodyTxt().getText();
	}


}
