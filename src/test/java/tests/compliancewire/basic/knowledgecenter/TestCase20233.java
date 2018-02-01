package tests.compliancewire.basic.knowledgecenter;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import ui.utils.Tools;

/**
 * Created by vevinmoza on 3/18/16.
 */

public class TestCase20233 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20233.class);

	@Test(alwaysRun = true,
			description = "A user can manage his curriculum vitae, adding category information of a CV", groups = {})
	public void Curriculum_Vitae_Manage_CV() throws Exception {
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
		Assert(userManagementPage.getUserId(), getFromStorage("adminUser"), "Precondition: User");
				usersPage.openOptionsPage().openEsignatureRequirements().openEditRequirements().uncheckReqCurriculumViate(getFromStorage("adminUser"),
				getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"), "RecScr");
		
		esignatureRequirementsPage.openEditRequirements().checkReqCurriculumViate().saveChanges()
				.electronicallySignIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"),
						"RecScr");
		usersPage.openKnowledgeCenter().openUserProfile().openCurriculumVitae();
		Assert(curriculumVitaePage.isCurriculumVitaePage(), "Curriculum Vitae page will be displayed.");
		curriculumVitaePage.addPatents("Patent 9087666",Tools.getDateTime("MM/dd/yyyy"),"Patent for Compliance Wire");
		Assert(curriculumVitaePage.getDate(),(Tools.getDateTime("MM/dd/yyyy")), "Data entered will be saved.");
		curriculumVitaePage.approveCV().electonicallySignIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
				getData("TC20233.SIGNATUREREASON"), "frameId");
		
		Assert(curriculumVitaePage.getCurriculumVitaeInfo().contains("Effective"), "User able to e-sign successfully and CV status will be changed to Effective.");
		curriculumVitaePage.addHonours("Honors in Electrical Engg.",Tools.getDateTime("MM/dd/yyyy"),"Did research on Compliance Wire");
		Assert(curriculumVitaePage.getCurriculumVitaeInfo().contains("Pending"), "CV Status will be changed to Pending.");
		
		homePage.openToolsMenu().openOptionsPage().openEsignatureRequirements().openEditRequirements()
		.uncheckReqCurriculumViate(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
				getData("TC19721.SIGNATUREREASON"), "RecScr");

	}

}
