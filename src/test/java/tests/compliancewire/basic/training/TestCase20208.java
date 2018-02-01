package tests.compliancewire.basic.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;

public class TestCase20208 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20208.class);

	@Test(alwaysRun = true, description = "Add a curriculum and Add training items to a curriculum", groups = { "basic",
			"basic.training" })
	public void Training_Curriculum_Create_And_Add_TI_items() throws Exception {
		addToStorage("curriculumInfo", getRandomEntityID().substring(0, 7));
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
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
		userManagementPage.returnFromUserManagement().openTrainingPage().openDisplayCurriculumList();
		Assert(curriculumReportPage.isCurriculumReportPage(), "Curriculum Report screen will be displayed");
		curriculumReportPage.openAddCurriculum().addACurriculum(getFromStorage("curriculumInfo") + "clm",
				getData("TC19676.ORGANIZATION"));
		Assert(viewCurriculumPage.getViewCurriculumPageContent().contains(getFromStorage("curriculumInfo")),
				"The Curriculum screen will be displayed with the curriculum information.");
		viewCurriculumPage.openTrainingItems().openAddTrainingItem().addTrainingItemToCurriculum("10199", "CONTAINS",
				"trng", "2");
		addToStorage("20208Training1", trainingManagementPage.getTrainingToAdd(0).get(0));
		addToStorage("20208Training2", trainingManagementPage.getTrainingToAdd(1).get(1));
		Assert(trainingManagementPage.continueToClassRosterOrCurriculum().sortByDateModified().sortByDateModified()
				.getTrainingStatusOnRosterOrCurriculum(0).contains(getFromStorage("20208Training1"))
				&& trainingManagementPage.getTrainingStatusOnRosterOrCurriculum(1)
						.contains(getFromStorage("20208Training2")),
				"Training Items List screen showing the selected training items added to the curriculum using the 'contains' option");
		trainingManagementPage.openAddTrainingItem().addTrainingItemToCurriculum("10196", "BEGINSWITH", "a", "2");
		addToStorage("20208Training3", trainingManagementPage.getTrainingToAdd(0).get(0));
		addToStorage("20208Training4", trainingManagementPage.getTrainingToAdd(1).get(1));
		Assert(trainingManagementPage.continueToClassRosterOrCurriculum().sortByDateModified().sortByDateModified()
				.getTrainingStatusOnRosterOrCurriculum(0).contains(getFromStorage("20208Training3"))
				&& trainingManagementPage.getTrainingStatusOnRosterOrCurriculum(1)
						.contains(getFromStorage("20208Training4")),
				"Training Items List screen showing the selected training items added to the curriculum using the 'begins with' option");
	}
}
