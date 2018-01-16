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

public class TestCase20208 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20208.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Add a curriculum and Add training items to a curriculum", groups = { "basic", "basic.training" })
	public void Training_Curriculum_Create_And_Add_TI_items(String userName) throws Exception {
		addToStorage("curriculumInfo", getRandomEntityID().substring(0, 7));
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE")).openToolsMenu()
				.openUsersPage().searchUser(userName).openSecurityRoles();
		Assert(true, "Precondition: Admin User");
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
