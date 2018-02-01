package tests.compliancewire.basic.knowledgecenter;

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

public class TestCase20213 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20213.class);

	@Test(alwaysRun = true, description = "User can view the system in an alternate language", groups = {"basic","basic.knowledgecenter"})
	public void Knowledge_Center_Users_view_CW_in_alternate_languages() throws Exception {
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
		Assert(userManagementPage.openSecurityRoles().getSecurityRole().contains("Organization Administrator"), "Precondition: Admin User -" + getFromStorage("adminUser"));
		Assert(userManagementPage.openKnowledgeCenter().openUserProfile().openLanguageSettings().changeLanguage("36")
				.getSaveBtnTxt().contains("सहेजें"),
				"After selecting an alternate language the system will be displayed in the selected language");
		Assert(preferencesPage.openUserProfile().openOlChangePwdPage().getSubmitBtnTxt().contains("जमा करें"),
				"Areas and screens within the system will be displayed in the updated language");
		Assert(changePasswordPage.openOlToolsMenu().openOlUsersPage().getOlSearchUser().contains("खोज"),
				"Areas and screens within the system will be displayed in the updated language");
		Assert(usersPage.openOlKnowledgeCenter().logOut().getacceptBtn().contains("मैं स्वीकार करता हू"),
				"The application through user's login will still be displayed in the language updated");
		loginPage.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openUserProfile().openOlLanguageSettings().changeLanguage("1");
		Assert(preferencesPage.getSaveBtnTxt().contains("Save"),
				"After selecting 'English' language the system will be displayed in the default language. ");
		Assert(preferencesPage.openUserProfile().openChangePwdPage().getSubmitBtnTxt().contains("Submit"),
				"Areas and screens within the system will be displayed in the updated language");
		Assert(changePasswordPage.openToolsMenu().openUsersPage().getSearchUser().contains("Search"),
				"Areas and screens within the system will be displayed in the updated language");
	}
}
