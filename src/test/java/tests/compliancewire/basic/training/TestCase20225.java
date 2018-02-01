package tests.compliancewire.basic.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase20225 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20225.class);

	@Test(alwaysRun = true, description = "Add a new form training item."
			+ "Add a single response question to a form.", groups = {
					"basic","basic.training"})
	public void Training_Create_Form_and_add_questions() throws Exception {
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"),getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(getFromStorage("adminUser")).openSecurityRoles();
		Assert(userManagementPage.getUserId(), getFromStorage("adminUser"), "Precondition: Admin User -" + getFromStorage("adminUser"));
		userManagementPage.returnFromUserManagement().openTrainingPage().openAddTrainingItem();
		Assert(trainingManagementPage.getPRINTTableBorder().contains("Forms"), "Add training item page will be displayed.");
		addTrainingItemPage.openFormCourse().addForm(getFromStorage("randomTag1") + "Form",
				getFromStorage("randomTag1") + "Form", "Auto Polaris 2.0");
		Assert(trainingManagementPage.getTrainingTitle().contains(getFromStorage("randomTag1")), "Form training item will be created.");
		trainingManagementPage.openAddQuestion().openQuestionWithSingleResponse();
		Assert(trainingManagementPage.getSingleResponseWindowTitle(), "Add: Question with a Single Response",
				"Add Question with a single response window should displayed");
		trainingManagementPage.addQuestionAnswer("What is Your Name?", "My Name is Ajay Singh");
		Assert(trainingManagementPage.hasResponseTextBox(), true,
				"The option of requiring a response will be displayed. Response required will be the default. ");
		trainingManagementPage.openSaveQuestion();
		Assert(trainingManagementPage.getPRINTTableBorder(), ("What is Your Name"),
				"A question with a single response will be added to the form.");

	}
}
