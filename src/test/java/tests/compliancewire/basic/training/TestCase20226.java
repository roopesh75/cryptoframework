package tests.compliancewire.basic.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase20226 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20226.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Add a single response question to a form.", groups = {
			"basic", "basic.training" })
	public void Add_a_single_response_question_to_a_form(String userName) throws Exception {
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(userName).openSecurityRoles();
		Assert(userManagementPage.getUserId(), userName, "Precondition: Admin User -" + userName);

		userManagementPage.returnFromUserManagement().openTrainingPage();
		Assert(trainingPage.isTrainingPage(), true, "View training information screen should be displayed");
		trainingPage
				.openAddTrainingItem().openFormCourse().addForm(getFromStorage("randomTag1") + "Form",
						getFromStorage("randomTag1") + "Form", "Auto Polaris 2.0")
				.openAddQuestion().openQuestionWithSingleResponse();
		Assert(trainingManagementPage.getSingleResponseWindowTitle(), "Add: Question with a Single Response",
				"Add Question with a single response window should displayed");
		trainingManagementPage.addQuestionAnswer("What is Your Name?", "My Name is Ajay Singh");
		Assert(trainingManagementPage.hasResponseTextBox(), true,
				"The option of requiring a response will be displayed. Response required will be the default. ");
		trainingManagementPage.openSaveQuestion();
		Assert(trainingManagementPage.getFormItems(), ("What is Your Name"),
				"A question with a single response will be added to the form.");

	}
}
