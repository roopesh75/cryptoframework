package tests.compliancewire.cfr_f.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase19733 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase19733.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Training_History_TI_history", groups = {
			"cfr_f", "cfr_f.training" })
	public void Training_History_TI_history(String userName) throws Exception {

		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage();
		usersPage.searchUser(userName).openSecurityRoles();
		Assert(true, "Precondition: Admin User");
		userManagementPage.returnFromUserManagement().openTrainingPage();
		addToStorage("Training1", getRandomEntityID().substring(0, 6) + "Trn");
		trainingPage.openAddTrainingItem().openControlDocumentCourse().addControlDocumentTraining(
				getFromStorage("Training1"), getFromStorage("Training1"), "Auto Polaris 2.0");
		trainingManagementPage.openHistory();
		trainingManagementPage.getDates();
		Assert(true, "The History screen will provide a chronological history of all changes made.");
		trainingManagementPage.openGeneralInformation().openEditGeneralInformation().addDescription("advanced");
		trainingManagementPage.openEditGeneralInformation().addCourseComments("newComment").openHistory();
		Assert(trainingManagementPage.getDescriptionHistory().contains(userName)
				&& trainingManagementPage.getDescriptionHistory().contains("advanced")
				&& trainingManagementPage.getDescriptionHistory().contains("Course Description")
				&& trainingManagementPage.getCommentHistory().contains(userName)
				&& trainingManagementPage.getCommentHistory().contains("Comment")
				&& trainingManagementPage.getCommentHistory().contains("newComment"),
				"The property edited, old and new values, the user who made the change, and the date/time of the change will be displayed ");
	}

}
