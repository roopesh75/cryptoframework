package tests.compliancewire.cfr_f.users;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

/**
 * Created by vevinmoza on 3/18/16.
 */

public class TestCase19736 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19736.class);

	@Test(alwaysRun = true,
			description = "Security role at a particular organizational level overrides any carried down "
					+ "security roles from upper organizations",
			groups = {
			"cfr_f", "cfr_f.users" })
	public void Users_SecurityRole_Overriding_SecurityRole() throws Exception {
		
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getData("TC19736.USER"), getData("TC19736.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(getData("TC19736.USER")).openSecurityRoles();

		Assert(userManagementPage.getPRINTTableBorder().contains(getData("TC19736.ROLE1")) && userManagementPage.getPRINTTableBorder().contains(getData("TC19736.ROLE2")),
				"Precondition:  Test user with full rights ( not an org admin)  in any middle org and a learner security role in a lower org.");
		userManagementPage.returnFromUserManagement().openTrainingPage().searchTraining(getData("TC19736.TRAINING1")).openOrganizations();
		Assert(userManagementPage.getContentSection().contains(getData("TC19736.TRAINING1")) && userManagementPage.getContentSection()
				.contains(getData("TC19736.ORG1")), "Precondition:  Training assigned in the same org" + getData("TC19736.TRAINING1"));
		trainingManagementPage.returnFromTrainingManagement().searchTraining(getData("TC19736.TRAINING2")).openOrganizations();
		Assert(userManagementPage.getContentSection().contains(getData("TC19736.TRAINING2")) && 
				userManagementPage.getOrganization().contains(getData("TC19736.ORG2"))
				, "Precondition:  Training assigned in the same org" + getData("TC19736.TRAINING2"));
		trainingPage.logOut().signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openTrainingPage().searchTraining(getData("TC19736.TRAINING3")).openOrganizations();
		Assert(userManagementPage.getContentSection().contains(getData("TC19736.TRAINING3")) && 
				userManagementPage.getOrganization().contains(getData("TC19736.ORG3")), "Precondition:  Training in any middle org");
		trainingManagementPage.returnFromTrainingManagement().searchTraining(getData("TC19736.TRAINING4")).openOrganizations();
		Assert(userManagementPage.getContentSection().contains(getData("TC19736.TRAINING4")) && 
				userManagementPage.getOrganization().contains(getData("TC19736.ORG4")), "Precondition:  Training in any middle org");
		trainingPage.logOut()
				.signIn(getData("TC19736.USER"), getData("TC19736.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openTrainingPage();
		
		Assert(trainingPage.isTrainingPage(), "Training page will be displayed.");
		trainingPage.searchTraining(getData("TC19736.TRAINING1")).openEditGeneralInformation()
				.addDescription("control1");
		Assert(trainingManagementPage.getDescription().contains("control"),
				"User will be able to view and edit training assigned in the same org containing the role with full rights.");
		trainingManagementPage.openReturn();
		trainingPage.searchTraining(getData("TC19736.TRAINING2")).openEditGeneralInformation()
				.addDescription("control2");
		Assert(trainingManagementPage.getDescription().contains("control"),
				"User will be able to view and edit training assigned in the same org containing the role with full rights.");
		trainingManagementPage.openReturn();
		Assert(trainingPage.searchTraining(getData("TC19736.TRAINING3")).getSearchStatus()
				.contains("No Training" + " Items found"),
				"User will not be able to view and edit org containing the Learner role and orgs below that level.");
		trainingManagementPage.closeSearchTrainingOverlay();
		Assert(trainingPage.searchTraining(getData("TC19736.TRAINING4")).getSearchStatus()
				.contains("No " + "Training Items found"),
				"User will not be able to view and edit org containing the Learner role and orgs below that level.");
		trainingManagementPage.closeSearchTrainingOverlay();

	}
}
