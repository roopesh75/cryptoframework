package tests.compliancewire.basic.training;

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

public class TestCase20207 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20207.class);

	@Test(alwaysRun = true, description = 
			"Administrator is able to Add a PASSING user completion to an instructor led course"
			+ " training with a pass/fail completion type", groups = {"basic","basic.training"})
	public void Training_ILC_Update_Completion() throws Exception {
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
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(getFromStorage("adminUser")).openSecurityRoles();
		Assert(userManagementPage.openSecurityRoles().getSecurityRole().contains("Organization Administrator"), "Precondition: Admin User -" + getFromStorage("adminUser"));
		userManagementPage.returnFromUserManagement().openAddUser()
				.addANewUser(getFromStorage("randomTag1") + "Usr", getFromStorage("randomTag1") + "Usr",
						getFromStorage("randomTag1") + "Usr", getData("TestCase19732.ORGANIZATION"))
				.returnFromUserManagement().openTrainingPage().openAddTrainingItem().openInstructorLedCourse()
				.addInstructorLedCourse(getFromStorage("randomTag1") + "ILCtrng",
						getFromStorage("randomTag1") + "ILCtrng", "Auto Polaris 2.0");
		Assert(trainingManagementPage.getTrainingStatus().contains("Effective")
				&& trainingManagementPage.getCompletionType().contains("Pass/Fail Course")
				&& trainingManagementPage.getCompletionExpires().contains("1 Month(s)"),
				"Precondition: Instructor led course training w/pass/fail completion type, w/completion expiration");
		trainingManagementPage.openClasses().openAddClass().addClass();

		Assert(classesPage.getClassTitle().equalsIgnoreCase(getFromStorage("randomTag1") + "ILCtrng"),
				"Precondition: Instructor led course training w/pass/fail completion type, w/completion expiration w/class");
		classesPage.openRosterFromLnk().openAddUser().addUserstoClassRoster(getFromStorage("randomTag1") + "Usr", "1");
		addToStorage("20207User1", trainingManagementPage.getUsersToAddTextCount().get(0));
		Assert(trainingManagementPage.continueToClassRosterOrCurriculum().getTrainingStatusOnRosterOrCurriculum(0)
				.contains(getFromStorage("20207User1"))
				&& trainingManagementPage.getTrainingStatusOnRosterOrCurriculum(0).contains("Incomplete"),
				"Precondition:Instructor led course training w/pass/fail completion type, w/completion expiration w/class, w/user in a roster and w/incomplete assignment.");

		Assert(trainingManagementPage.openIncompleteStatus().enterManageCompletionFields().getExpirationDate()
				.contains("Calculated") && trainingManagementPage.getCompletion().contains("Qualified"),
				"A passing completion, completion date and expiration date will be added and Expiration date will be calculated automatically");
		Assert(trainingManagementPage.incompleteUpdateCompletion().getTrainingStatusOnRosterOrCurriculum(0).contains(
				"Qualified") && trainingManagementPage.getTrainingStatusOnRosterOrCurriculum(0).contains("Complete"),
				"Roster information will be updated and the class roster screen will display with the updated information having status as 'Complete' and Completions as 'Qualified'");
	}
}
