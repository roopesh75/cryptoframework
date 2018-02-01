package tests.compliancewire.basic.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import ui.utils.Tools;

public class TestCase20216 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20216.class);

	@Test(alwaysRun = true, description = "A user can create an assignment report by training via the traditional method of creating reports.", groups = {})
	public void Training_Assignment_report_by_Training_Create() throws Exception {
		
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUser");
		addToStorage("Training1",getRandomEntityID().substring(0, 5) + "_CDTrng");
		addToStorage("adminUser2",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUser2");
		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE")).openToolsMenu().openUsersPage().searchUser(getFromStorage("adminUser")).openSecurityRoles();
		Assert(userManagementPage.getUserId().contains(getFromStorage("adminUser")), "Precondition: Admin User");
		userManagementPage.returnFromUserManagement().openAddUser().
				addANewUser(getFromStorage("adminUser2") + "fn", getFromStorage("adminUser2") + "ln",
						getFromStorage("adminUser2"), getData("GENERIC.TOP_ORGANIZATION")).openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).openTrainingPage().openAddTrainingItem().openControlDocumentCourse()
				.addControlDocumentTraining(getFromStorage("Training1"),
						getFromStorage("Training1"), "Auto Polaris 2.0").openReturn().openAssignmentPage().
				openAddAssignmentDefinition().
				addAnAssignmentDefinition("1","1",getFromStorage("Training1"),
						getFromStorage("adminUser2"),"addReasonAndStartDate",Tools.getCurrentDate("M/dd/yyyy"),"ULCompliance").continueAndCheckAssignment()
				.returnToAssignmentPage().openAssignmentReportByTraining().runReportForTrainingOrCurriculum(getFromStorage("Training1"),getFromStorage("Training1"),getFromStorage("adminUser2"));
		Assert(trainingManagementPage.getPRINTTableBorder().contains(getFromStorage("Training1")) && trainingManagementPage.getPRINTTableBorder().contains(getFromStorage("adminUser2")), "Assignment for Training Item and User by selecting 'Reason for Assignment'");
		assignmentManagementPage.returnFromAssignmentReportsToAssignmentPage().openAssignmentReportByTraining();
		Assert(defineAssignmentReportPage.isDefineAssignmentReportPage(), "An assignment report by training page will be appear.");
		defineAssignmentReportPage.
		runReportForTrainingOrCurriculum(getFromStorage("Training1"),getFromStorage("Training1"),
				getFromStorage("adminUser2"),"Show Recurring Assignments Only","ULCompliance","Control Document","Home Organization",Tools.getCurrentDate("M/d/yyyy"),Tools.getCurrentDate("M/d/yyyy"));
		Assert(trainingManagementPage.getPRINTTableBorder().contains(getFromStorage("Training1")) && trainingManagementPage.getPRINTTableBorder().contains(getFromStorage("adminUser2"))
				&& trainingManagementPage.getPRINTTableBorder().contains("Auto Polaris 2.0"), "The records returned correctly and information displayed will be dependent on the selections made by the Admin User.");
		
		}
}
