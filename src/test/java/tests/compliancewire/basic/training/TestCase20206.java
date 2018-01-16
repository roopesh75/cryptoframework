package tests.compliancewire.basic.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase20206 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase20206.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Add an Instructor Led Course training item and Add a class to an effective instructor led course training. Add users to an instructor led course roster through the use of keyword search", groups = {})
	public void Training_ILC_Create_and_Add_a_class_users_to_class(String userName) throws Exception {
		addToStorage("randomTag1", getRandomEntityID().substring(0, 5));
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(userName).openSecurityRoles();
		Assert(userManagementPage.getUserId(), userName, "Precondition: Admin User -" + userName);
		userManagementPage.returnFromUserManagement().openAddUser()
				.addANewUser(getFromStorage("randomTag1") + "Usr11", getFromStorage("randomTag1") + "Usr11",
						getFromStorage("randomTag1") + "Usr11", getData("TestCase19732.ORGANIZATION"))
				.returnFromUserManagement().openAddUser()
				.addANewUser(getFromStorage("randomTag1") + "Usr12", getFromStorage("randomTag1") + "Usr12",
						getFromStorage("randomTag1") + "Usr12", getData("TestCase19732.ORGANIZATION"))
				.returnFromUserManagement().openAddUser()
				.addANewUser(getFromStorage("randomTag1") + "Usr@1", getFromStorage("randomTag1") + "Usr@1",
						getFromStorage("randomTag1") + "Usr@1", getData("TestCase19732.ORGANIZATION"))
				.returnFromUserManagement().openAddUser()
				.addANewUser(getFromStorage("randomTag1") + "Usr@2", getFromStorage("randomTag1") + "Usr@2",
						getFromStorage("randomTag1") + "Usr@2", getData("TestCase19732.ORGANIZATION"))
				.returnFromUserManagement().openTrainingPage().openAddTrainingItem().openInstructorLedCourse()
				.addInstructorLedCourse(getFromStorage("randomTag1") + "ILCtrng",
						getFromStorage("randomTag1") + "ILCtrng", "Auto Polaris 2.0");

		Assert(trainingManagementPage.getTrainingType().contains("Instructor Led Course")
				&& trainingManagementPage.getTrainingStatus().contains("Effective")
				&& trainingManagementPage.getTrainingTitle().contains(getFromStorage("randomTag1")),
				"User will be navigated to the General Information screen with all the information");

		Assert(trainingManagementPage.openClasses().openAddClass().addClass().getClassTitle()
				.contains(getFromStorage("randomTag1")),
				"Class will be added to the instructor led course training and the user is navigated to the class General Information");

		Assert(trainingManagementPage.openRoster().openAddUser().isAddUsersToClassRosterPage(),
				"Add Users to Class Roster screen will be displayed");
		trainingManagementPage.addUserstoClassRoster(getFromStorage("randomTag1") + "Usr1", "2");

		addToStorage("20206User1", trainingManagementPage.getUsersToAddTextCount().get(0));
		addToStorage("20206User2", trainingManagementPage.getUsersToAddTextCount().get(1));
		Assert(trainingManagementPage.continueToClassRosterOrCurriculum().getTrainingStatusOnRosterOrCurriculum(0)
				.contains(getFromStorage("20206User1"))
				&& trainingManagementPage.getTrainingStatusOnRosterOrCurriculum(1)
						.contains(getFromStorage("20206User2")),
				"The selected users with any combination of characters (1 or more numeric, alphanumeric) will be added to the class roster");
		trainingManagementPage.openAddUser().addUserstoClassRoster(getFromStorage("randomTag1") + "Usr@", "2");
		addToStorage("20206User3", trainingManagementPage.getUsersToAddTextCount().get(0));
		addToStorage("20206User4", trainingManagementPage.getUsersToAddTextCount().get(1));
		Assert(trainingManagementPage.continueToClassRosterOrCurriculum().getTrainingStatusOnRosterOrCurriculum(0)
				.contains(getFromStorage("20206User3"))
				&& trainingManagementPage.getTrainingStatusOnRosterOrCurriculum(1)
						.contains(getFromStorage("20206User4")),
				"The selected users with  special character will be added to the class roster");

	}

}
