package tests.compliancewire.basic.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase20237 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase20237.class);

	@Test(alwaysRun = true, description = " Creating a new version of a training item "
					+ "brings the user into the edit screen. User can change all relevant information "
					+ "related to the training item before creating the new version"
			+ " User can change all relevant information related to the training item before creating the new version", groups = { "basic","basic.training" })
	public void Training_Modify_the_Training_item() throws Exception {
		addToStorage("Training1", getRandomEntityID().substring(0, 6) + "CDtrng");
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
		Assert(userManagementPage.getUserId(), getFromStorage("adminUser"), "Precondition: Admin User -" + getFromStorage("adminUser"));
		userManagementPage.returnFromUserManagement().openTrainingPage().openAddTrainingItem()
				.openControlDocumentCourse().addControlDocumentTraining(getFromStorage("Training1"),
						getFromStorage("Training1"), "Auto Polaris 2.0");
		Assert(trainingManagementPage.getTrainingTitle().contains(getFromStorage("Training1")), "General Information screen of the training item will be displayed.");
		trainingManagementPage.openCreateNewVersion()
				.enterCodeTitleSelectOrganization(getRandomEntityID().substring(0, 3) + "CDtrng",
						getRandomEntityID().substring(0, 3) + "CDtrng")
				.enterApprovalEffectiveDates().enterAssortedFields("2").saveControlDocument("2");
		Assert(trainingManagementPage.getComments().contains("Control Document"),
				"User is able to edit the CD info and View Training screen will display with the updated training item information.");

	}

}
