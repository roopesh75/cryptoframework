package tests.compliancewire.basic.training;

/**
 * @Roopesh
 * check the below code
 * trainingManagementPage.closeManageTrainingTypes().returnFromTrainingManagement().openAddTrainingItem()
				.openCustomExamCourse();
		customExamPage.addCustomExam(getFromStorage("randomTag1") + "CEtrng", getFromStorage("randomTag1") + "CEtrng",
				"Auto Polaris 2.0");
 * 
 */
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase20231 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20231.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Add mandatory question to a custom exam", groups = {"basic", "basic.training"})
	public void Add_mandatory_question_to_customexam(String userName) throws Exception {
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE")).openToolsMenu()
				.openUsersPage().searchUser(userName).openSecurityRoles();
		Assert(true, "Precondition: Admin User");
		
		 userManagementPage.returnFromUserManagement().openTrainingPage().
		 openAddTrainingItem().openCustomExamCourse().addCustomExam(getFromStorage(
		 "randomTag1") + "CEtrng", getFromStorage("randomTag1") + "CEtrng",
		 "Auto Polaris 2.0").openCustomExam().editExamDetails();
		Assert(addQuestionCustomExamPage.getNoOfPooledQuestionsToAsk().contains("0")&&addQuestionCustomExamPage.getQuestionPooling().contains("On"), "Precondition -2:Custom Exam with Question Pooling Enabled");
		addQuestionCustomExamPage.openReturn();
		Assert(trainingManagementPage.getCustomExamTrainingType().contains("Custom Exam"), "General Information screen of the Custom exam training item will be displayed");
		trainingManagementPage.openCustomExam().openSingleResponseQuestion().addSingleResponseQuestion()
				.expandQuestion();
		Assert(addQuestionCustomExamPage.getMandatoryQuestion().contains("Mandatory"), "Question will be added as a Mandatory question.");
	}
}
