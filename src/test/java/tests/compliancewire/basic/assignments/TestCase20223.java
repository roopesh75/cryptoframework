package tests.compliancewire.basic.assignments;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import ui.utils.Tools;

public class TestCase20223 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20223.class);

	@Test(alwaysRun = true, description = "Add an automated recurring assignment", groups = {})
	public void Assignments_Add_recurring_assignment() throws Exception {
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		addToStorage("learnerUser", getRandomEntityID().substring(0, 7) + "_LearnerUsr");
		addToStorage("Training1", getRandomEntityID().substring(0, 7) + "_CDTrng");

		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(getFromStorage("adminUser")).openSecurityRoles();
		Assert(userManagementPage.openSecurityRoles().getSecurityRole().contains("Organization Administrator"),
				"Precondition: Admin User -" + getFromStorage("adminUser"));

		userManagementPage.returnFromUserManagement().openAddUser()
				.addANewUser(getFromStorage("learnerUser") + "fn", getFromStorage("learnerUser") + "ln",
						getFromStorage("learnerUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openUsersPage().searchUser(getFromStorage("learnerUser")).openSecurityRoles();

		Assert(userManagementPage.getSecurityRole().contains("Learner"), "Precondition: User");

		userManagementPage.returnFromUserManagement().openTrainingPage().openAddTrainingItem()
				.openControlDocumentCourse().addControlDocumentTraining(getFromStorage("Training1"),
						getFromStorage("Training1"), "Auto Polaris 2.0");
		Assert(trainingManagementPage.getTrainingStatus().equalsIgnoreCase("Effective"), true,
				"Precondition: Training");
	
		trainingManagementPage.returnFromTrainingManagement().openAssignmentPage().openAddAssignmentDefinition();
		Assert(addAnAssignmentDefinitionPage.isaddAnAssignmentDefinitionPage(),"Add an Assignment Definition page will be displayed");
		addAnAssignmentDefinitionPage.addAnAssignmentDefinition("1", "1",
				getFromStorage("Training1"), getFromStorage("learnerUser"),"addReasonAndStartDate",Tools.getCurrentDate("M/d/yyyy"),"ULCompliance");
		Assert(addAnAssignmentDefinitionPage.isaddAnAssignmentDefinitionPage(),"Recurring Assignment, Reason Chosen, Training and User chosen");
		addAnAssignmentDefinitionPage.continueAndCheckAssignment();
		Assert( assignmentManagementPage.getPRINTTableBorder().contains(getFromStorage("Training1")),"Assignment job status is displayed and recurring assignment  added.");
		
	}
}
