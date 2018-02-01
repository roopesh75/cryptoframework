package tests.compliancewire.cfr_f.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import ui.utils.Tools;

public class TestCase19733 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase19733.class);

	@Test(alwaysRun = true, description = "Training History screen provides a chronological "
					+ "history of all changes made to the training item", groups = {
			"cfr_f", "cfr_f.training" })
	public void Training_History_TI_history() throws Exception {

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
				.openToolsMenu().openUsersPage();
		usersPage.searchUser(getFromStorage("adminUser")).openSecurityRoles();
		Assert(userManagementPage.openSecurityRoles().getSecurityRole().contains("Organization Administrator"), "Precondition: Admin User -" + getFromStorage("adminUser"));
		userManagementPage.returnFromUserManagement().openTrainingPage();
		addToStorage("Training1", getRandomEntityID().substring(0, 6) + "Trn");
		trainingPage.openAddTrainingItem().openControlDocumentCourse().addControlDocumentTraining(
				getFromStorage("Training1"), getFromStorage("Training1"), "Auto Polaris 2.0");
		trainingManagementPage.openHistory();
		trainingManagementPage.returnFromTrainingManagement().searchTraining(getFromStorage("Training1")).openEditGeneralInformation().addCourseComments("adding some new comments");
		trainingManagementPage.openHistory();
		Tools.getMilliSecondsForDate("m/d/yyyy hh:mm:ss a", trainingManagementPage.getDates().get(0));
		
		Assert(Long.parseLong(Tools.getMilliSecondsForDate("m/d/yyyy hh:mm:ss a", trainingManagementPage.getDates().get(0))) <
				Long.parseLong(Tools.getMilliSecondsForDate("m/d/yyyy hh:mm:ss a", trainingManagementPage.getDates().get(1))), "The History screen will provide a chronological history of all changes made.");
		trainingManagementPage.openGeneralInformation().openEditGeneralInformation().addDescription("advanced");
		trainingManagementPage.openEditGeneralInformation().addCourseComments("newComment").openHistory();
		Assert(trainingManagementPage.getDescriptionHistory().contains(getFromStorage("adminUser"))
				&& trainingManagementPage.getDescriptionHistory().contains("advanced")
				&& trainingManagementPage.getDescriptionHistory().contains("Course Description")
				&& trainingManagementPage.getCommentHistory().contains(getFromStorage("adminUser"))
				&& trainingManagementPage.getCommentHistory().contains("Comment")
				&& trainingManagementPage.getCommentHistory().contains("newComment"),
				"The property edited, old and new values, the user who made the change, and the date/time of the change will be displayed ");
	}

}
