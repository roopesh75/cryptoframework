package tests.compliancewire.cfr_f.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase19729 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase19729.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", 
			description = "Training_Training_Training report generation rights", groups = {"cfr_f","cfr_f.training"
			})
	public void Training_Training_Training_report_generation_rights(String userName) throws Exception {

		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage();
		usersPage.searchUser(getData("TC19729.USER1")).openSecurityRoles();
		Assert(true, "Precondition: Test User#1 without rights to create a training report.");
		userManagementPage.returnFromUserManagement().searchUser(getData("TC19729.USER2")).openSecurityRoles();
		Assert(true, "Precondition: Test User#2 without the rights to access Training tab.");
		userManagementPage.returnFromUserManagement().searchUser(getData("TC19729.USER3")).openSecurityRoles();
		Assert(true, "Precondition:  Test User#3 with rights to create a training report.");
		userManagementPage.returnFromUserManagement().openSecurityRoles().chooseSecurityRole(getData("TC19729.ROLE1"));
		Assert(true, "Precondition: without rights to create a training report.");
		securitySettingsPage.returnFromSecuritySettings().chooseSecurityRole(getData("TC19729.ROLE2"));
		Assert(true, "Precondition: without the rights to access Training tab.");
		securitySettingsPage.returnFromSecuritySettings().chooseSecurityRole(getData("TC19729.ROLE3"));
		Assert(true, "Precondition: with rights to create a training report.");
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
