package tests.compliancewire.basic.userprofile;

/**
 * @Roopesh
 * check the below code
 * trainingManagementPage.closeManageTrainingTypes().returnFromTrainingManagement().openAddTrainingItem()
				.openCustomExamCourse();
		customExamPage.addCustomExam(getFromStorage("randomTag1") + "CEtrng", getFromStorage("randomTag1") + "CEtrng",
				"Auto Polaris 2.0");
 * 
 */
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase19701 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19701.class);

	@Test(alwaysRun = true,  description = "A user can manage his curriculum vitae, adding category information of a CV", groups = {})
	public void Activate_Pending_custom_exam() throws Exception {
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));
		loginPage.signIn("testusr100", "1234", getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser("testusr100").openSecurityRoles();
		Assert(true, "Precondition-1: Admin User");

		userManagementPage.returnFromUserManagement().openTrainingPage().openAddTrainingItem().openCustomExamCourse()
				.addCustomExam(getFromStorage("randomTag1") + "CEtrng", getFromStorage("randomTag1") + "CEtrng",
						"Auto Polaris 2.0")
				.openCustomExam().openSingleResponseQuestion().addSingleResponseQuestion();
		Assert(addQuestionCustomExamPage.getQuestionNumber(), "Precondition -2-1:Custom exam with at least 1 question");
		Assert(addQuestionCustomExamPage.openReturn().getTrainingStatus().contains("Pending"),
				"Precondition -2-2:Custom exam status is Pending");
		Assert(trainingManagementPage.returnFromTrainingManagement()
				.searchTraining(getFromStorage("randomTag1") + "CEtrng").getTrainingTitle()
				.contains(getFromStorage("randomTag1") + "CEtrng"),
				"The general information screen of the training item is displayed");
		Assert(trainingManagementPage.openActivateVersion().activateTraining().getTrainingStatus()
				.contains("Effective"),
				"Custom exam will be activated. General Information screen will display Status: Effective and display a green dot icon");

	}
}
