package tests.compliancewire.basic.training;


import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase20230 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20230.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "When a brand new custom exam is added, it will be added as a non Launchable training item with a Pending Status. Add mandatory question  and non mandatory questions to a custom exam.Activate a Pending custom exam", groups = {
			"basic", "basic.training" })
	public void Training_Custom_Exam_with_Mandatory_questions(String userName) throws Exception {
		addToStorage("customExamTrng", getRandomEntityID().substring(0, 7) + "CEtrng");
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(userName).openSecurityRoles();
		Assert(true, "Precondition: Admin User");
		Assert(userManagementPage.openTrainingPage().openAddTrainingItem().getAddATrainingItemPageContent()
				.contains("Please select the Type of Training Item you would like to add"),
				"Add Training item page will be displayed for the user");
		addTrainingItemPage.openCustomExamCourse().addCustomExam(getFromStorage("customExamTrng"),
				getFromStorage("customExamTrng"), "Auto Polaris 2.0");
		Assert(trainingManagementPage.getTrainingStatus().contains("Pending"),
				"User will be able to add the training item and Status of the training item will be Pending");
		trainingManagementPage.openKnowledgeCenter().openCatalog().searchTrainingItem(getFromStorage("customExamTrng"));
		Assert(catalogPage.getcatalogPageContent().contains("There are no more results to display"),
				"Catalog will not display the training for launching");
		homePage.openToolsMenu().openTrainingPage().searchTraining(getFromStorage("customExamTrng"));
		Assert(trainingManagementPage.getCustomExamTrainingType().contains("Custom Exam"),
				"General Information screen of the Custom exam training item will be displayed");
		Assert(trainingManagementPage.openCustomExam().editExamDetails().getNoOfPooledQuestionsToAsk().contains("0")
				&& addQuestionCustomExamPage.getQuestionPooling().contains("On"), "Custom Exam details is saved");
		Assert(addQuestionCustomExamPage.openSingleResponseQuestion().addSingleResponseQuestion().expandQuestion()
				.getMandatoryQuestion().contains("Mandatory"), "Question will be added as a Mandatory question.");
		Assert(addQuestionCustomExamPage.openReturn().openActivateVersion().activateTraining().getTrainingStatus()
				.contains("Effective"),
				"Custom exam will be activated. General Information screen will display Status: Effective and display a green dot icon");
	}
}
