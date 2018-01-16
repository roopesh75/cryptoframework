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

public class TestCase20204 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20204.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Add a class to an effective instructor led course training", groups = {
			"basic", "basic.training" })
	public void Add_class_to_an_effective_instructor_led_course_training(String userName) throws Exception {
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(userName).openSecurityRoles();
		Assert(true, "Precondition: Admin User -"+userName);
		userManagementPage.returnFromUserManagement().openTrainingPage().openAddTrainingItem().openInstructorLedCourse()
				.addInstructorLedCourse(getFromStorage("randomTag1") + "ILCtrng",
						getFromStorage("randomTag1") + "ILCtrng", "Auto Polaris 2.0");
		Assert(trainingManagementPage.getTrainingStatus().contains("Effective"),
				"Precondition: Instructor led course training with effective status");
		Assert(trainingManagementPage.getTrainingType().contains("Instructor Led Course"),
				"Instructor led course training general Information page will be displayed for the user");
		trainingManagementPage.openReturn().searchTraining(getFromStorage("randomTag1") + "ILCtrng").openClasses()
				.openAddClass().addClass();

		Assert(classesPage.getClassTitle().equalsIgnoreCase(getFromStorage("randomTag1") + "ILCtrng"),
				"Class will be added to the instructor led course training and the user is navigated to the class General Information");

	}
}
