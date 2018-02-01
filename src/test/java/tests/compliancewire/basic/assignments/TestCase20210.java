package tests.compliancewire.basic.assignments;

/**
 * @Roopesh
 * 1.date time stamp is not captured as part of expected result  7 
 * 2.Test step 5 should be split to 2 separate steps "Verify that Password is displayed in encrypted form for e-signature"
 * 3.Create web services to check and uncheck password policies 
 */
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import ui.utils.Tools;

public class TestCase20210 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20210.class);

	@Test(alwaysRun = true, description = "Add a one time assignment.", groups = {"basic","basic.assignments"})
	public void Assignments_Add_one_time_Assignment() throws Exception {
		addToStorage("20210Tag1", getRandomEntityID().substring(0, 7));
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
		Assert(userManagementPage.openSecurityRoles().getSecurityRole().contains("Organization Administrator"), "Precondition: Admin User -" + getFromStorage("adminUser"));
		Assert(userManagementPage.returnFromUserManagement().openAddUser()
				.addANewUser(getFromStorage("20210Tag1") + "Usr", getFromStorage("20210Tag1") + "Usr",
						getFromStorage("20210Tag1") + "Usr", getData("TestCase19732.ORGANIZATION"))
				.openSecurityRoles().getSecurityRole().contains("Learner"), "Precondition: Learner User");
		userManagementPage.returnFromUserManagement().openTrainingPage().openDisplayCurriculumList().openAddCurriculum()
				.addACurriculum(getFromStorage("20210Tag1") + "clm", getData("TC19676.ORGANIZATION"));
		viewCurriculumPage.openTrainingItems().openAddTrainingItem()
				.addTrainingItemToCurriculum("0", "CONTAINS", "trng", "1").continueToClassRosterOrCurriculum()
				.openManageStatus().setCurriculumStatus(Tools.getDateTime("MM/dd/YYYY"));
		Assert(viewCurriculumPage.getCurriculumStatus().contains("Effective"),
				"PreCondition:Curriculum w/training w/status of curriculum set to effective");
		Assert(viewCurriculumPage.openAssignments().openAddAssignmentDefinition().getassignmentTypeDefinition()
				.contains("Assignment Type & Definition"), " Add an Assignment definition screen will be displayed.");
		addAnAssignmentDefinitionPage.addAnAssignmentDefinition("0", "2", getFromStorage("20210Tag1") + "clm",
				getFromStorage("20210Tag1") + "Usr","").continueAndCheckAssignment();
		Assert(assignmentManagementPage.getTrainingItem(getFromStorage("20210Tag1") + "clm")
				.contains(getFromStorage("20210Tag1") + "clm"),
				"One-Time assignment will be selected successfully.Curriculum will be searched successfully.User will be searched successfully.The user will be prompted to confirm and the assignments will be created");
		assignmentManagementPage.returnToAssignmentPage().openAssignmentReportByTraining().runReportForTrainingOrCurriculum(
				"Curriculum", getFromStorage("20210Tag1") + "clm" + " (" + getFromStorage("20210Tag1") + "clm" + ")",
				getFromStorage("20210Tag1") + "Usr");
		Assert(trainingManagementPage.getPRINTTableBorder().contains(getFromStorage("20210Tag1") + "Usr"),
				"Assignment Report will be generated for the training item and user.");
	}
}
