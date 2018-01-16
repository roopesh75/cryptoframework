package tests.compliancewire.cfr_f.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import ui.utils.Tools;

public class TestCase19727 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase19727.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Training_Class_roster_view",
			groups = {"cfr_f","cfr_f.training"
			})
	public void Training_Class_roster_view(String userName) throws Exception {
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openTrainingPage().openAddTrainingItem().openInstructorLedCourse()
				.addInstructorLedCourse(getFromStorage("randomTag1") + "trng", getFromStorage("randomTag1") + "trng",
						"Auto Polaris 2.0")
				.openAddClass().addClass();
		addToStorage("classCode", classesPage.getClassCode());
		optionsPage.openTrainingPage().searchTraining(getFromStorage("randomTag1") + "trng");
		Assert(trainingManagementPage.getAllowOnlineRegistration()
				,("Allow users to self register for this course online"),
				"PreCondition: Instructor Led Course w/class with Self registration enabled");
		addToStorage("userInfo", getRandomEntityID().substring(0, 7));
		classesPage.openUsersPage().openAddUser()
				.addANewUser(getFromStorage("userInfo") + "usr2", getFromStorage("userInfo") + "usr2",
						getFromStorage("userInfo") + "usr2", getData("TestCase19751.ORGANIZATION"))
				.logOut()
				.signIn(getFromStorage("userInfo") + "usr2", getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openCatalog().searchTrainingItem(getFromStorage("randomTag1") + "trng")
				.trainingItemGeneralInfo(getFromStorage("randomTag1") + "trng");
		catalogPage.openTrainingItemClassInfo().openClassCode(getFromStorage("classCode")).registerForClass();
		catalogPage.electonicallySignIn(getFromStorage("userInfo") + "usr2", getData("GENERIC.PASSWORD"),
				getData("TC19751.SIGNATUREREASON"), "frameId");
		todoPage.openTrainingItem(getFromStorage("randomTag1") + "trng").openClassInformation();

		Assert(todoPage.getToDoPageContent(),(getFromStorage("userInfo") + "usr2")
				, "PreCondition: User self registered for the class.");
		Assert(todoPage.getToDoPageContent(),(Tools.getDateTime1()), "PreCondition: User self registered for the class.");
		homePage.logOut().signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openTrainingPage().searchTraining(getFromStorage("randomTag1") + "trng").openClasses()
				.openRoster().updateCompletion("incomplete");
		Assert(trainingManagementPage.getTrainingStatusOnRosterOrCurriculum(0),("Complete"), "PreCondition: User with roster completion for above ILC TI.");
		trainingPage.logOut().signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(userName).openSecurityRoles();
		Assert(userManagementPage.getUserId(),(userName), "Precondition: Admin User");

		usersPage.openTrainingPage().searchTraining(getFromStorage("randomTag1") + "trng");
		Assert(trainingManagementPage.getAllowOnlineRegistration(),("Allow users to self register for this course online"), "User will be navigated to Training General information screen.");

		trainingManagementPage.openClasses().openRoster().selectRosterHistory("Roster History");
		Assert(trainingManagementPage.getTrainingStatusOnRosterOrCurriculum(1),("Incomplete"),
				"The Class Roster History will be displayed an entry for the user who registered online and will show a status of Incomplete.");
		Assert(trainingManagementPage.getTrainingStatusOnRosterOrCurriculum(0),("Complete"), "Recent completion will be recorded for user");
		trainingManagementPage.selectRosterHistory("Current Roster");
		Assert(trainingManagementPage.getCurrentRosterView(),("Current Roster"), "The current roster screen will be displayed. ");
		addToStorage("prev_date", trainingManagementPage.getCompletionDateFromRosterView(1));
		trainingManagementPage.updateCompletion("complete");
		AssertGreater(Long.parseLong(trainingManagementPage.getCompletionDateFromRosterView(1)),Long.parseLong(getFromStorage("prev_date")), ""
				+ "The completion will be updated in the Current Roster Screen ");
		trainingManagementPage.selectRosterHistory("Roster History");
		Assert(trainingManagementPage.getTrainingStatusOnRosterOrCurriculum(0),("Complete"), "Roster History will be provided every recorded completion for the class.");
		Assert(trainingManagementPage.getTrainingStatusOnRosterOrCurriculum(1),("Complete"), "Roster History will be provided every recorded completion for the class.");
		Assert(trainingManagementPage.getTrainingStatusOnRosterOrCurriculum(2),("Incomplete"), "Roster History will be provided every recorded completion for the class.");
	}

}
