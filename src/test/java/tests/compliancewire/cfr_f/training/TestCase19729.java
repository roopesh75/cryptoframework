package tests.compliancewire.cfr_f.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase19729 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase19729.class);

	@Test(alwaysRun = true,
			description = "A user is unable to create a training report without the appropriate rights", groups = {"cfr_f","cfr_f.training"
			})
	public void Training_Training_Training_report_generation_rights() throws Exception {

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
		usersPage.searchUser(getData("TC19729.USER1")).openSecurityRoles();
		Assert(userManagementPage.getPRINTTableBorder().contains(getData("TC19729.ROLE1")), "Precondition: Test User#1 without rights to create a training report.");
		userManagementPage.returnFromUserManagement().searchUser(getData("TC19729.USER2")).openSecurityRoles();
		Assert(userManagementPage.getPRINTTableBorder().contains(getData("TC19729.ROLE2")), "Precondition: Test User#2 without the rights to access Training tab.");
		userManagementPage.returnFromUserManagement().searchUser(getData("TC19729.USER3")).openSecurityRoles();
		Assert(userManagementPage.getPRINTTableBorder().contains(getData("TC19729.ROLE3")), "Precondition:  Test User#3 with rights to create a training report.");
		userManagementPage.returnFromUserManagement().openSecurityRoles().chooseSecurityRole(getData("TC19729.ROLE1"));
		Assert(securitySettingsPage.getSelectedRole().contains(getData("TC19729.ROLE1")), "Precondition: without rights to create a training report.");
		securitySettingsPage.returnFromSecuritySettings().chooseSecurityRole(getData("TC19729.ROLE2"));
		Assert(securitySettingsPage.getSelectedRole().contains(getData("TC19729.ROLE2")), "Precondition: without the rights to access Training tab.");
		securitySettingsPage.returnFromSecuritySettings().chooseSecurityRole(getData("TC19729.ROLE3"));
		Assert(securitySettingsPage.getSelectedRole().contains(getData("TC19729.ROLE3")), "Precondition: with rights to create a training report.");
		securitySettingsPage.logOut("true");
		loginPage.signIn(getData("TC19729.USER1"), getData("TC19729.USER1"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
		.openToolsMenu().openTrainingPage();
		Assert(trainingPage.getAllSectionTxt().contains("Training Report"), "The Training Report link (and wizard) will NOT be displayed on the Training Menu, ");
		Assert(!trainingPage.logOut().signIn(getData("TC19729.USER2"), getData("TC19729.USER2"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().getOverlayTxt().contains("Training"), "Training tab will not be available for the user and user will not be able to create the training report.");
		Assert(homePage.logOut().signIn(getData("TC19729.USER3"), getData("TC19729.USER3"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openTrainingPage().getAllSectionTxt().contains("Training Report"), "The Training Report link (and wizard) will be displayed on the Training tab and user will be able to create a training report.");
		
	}

}
