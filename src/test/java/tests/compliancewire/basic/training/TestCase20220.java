package tests.compliancewire.basic.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;

public class TestCase20220 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20220.class);

	@Test(alwaysRun = true, description = "Add a sub-curriculum into another curriculum.", groups = {})
	public void Training_Curriculums_Add_sub_curriculum_to_a_curriculum() throws Exception {
		addToStorage("curr1", getSecurityRoleSeq()+"_CURRLM1");
		addToStorage("curr2", getSecurityRoleSeq()+"_CURRLM2");	
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		
		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(getFromStorage("adminUser")).openSecurityRoles();
		Assert(userManagementPage.openSecurityRoles().getSecurityRole().contains("Organization Administrator"),
				"Precondition: Admin User -" + getFromStorage("adminUser"));
		
		userManagementPage.returnFromUserManagement().openTrainingPage().openDisplayCurriculumList().openAddCurriculum()
		.addACurriculum(getFromStorage("curr1"), getData("TC20220.ORGANIZATION"));
		Assert(viewCurriculumPage.getCurriculumInfoContent().contains(getFromStorage("curr1")), "Curriculum#1.");
		addCurriculumPage.openReturn().openDisplayCurriculumList().openAddCurriculum().addACurriculum(getFromStorage("curr2"), getData("TC20220.ORGANIZATION"));
		
		Assert(viewCurriculumPage.getCurriculumInfoContent().contains(getFromStorage("curr2")), "Curriculum#2.");
		addCurriculumPage.openReturn().openDisplayCurriculumList();
		Assert(curriculumReportPage.getPRINTTableBorder().contains(getFromStorage("curr1")) && curriculumReportPage.getPRINTTableBorder().contains(getFromStorage("curr2")), "Curriculum Report will be displayed.");
		curriculumReportPage.chooseCurriculum(getFromStorage("curr1"));
 		Assert(viewCurriculumPage.getCurriculumInfoContent().contains(getFromStorage("curr1")), "View Curriculum: General Information page will be displayed.");
		viewCurriculumPage.openRelationships().addSubCurriculum(getFromStorage("curr2"));
		Assert(viewCurriculumPage.getViewCurriculumPageContent().contains(getFromStorage("curr2")), "The relationships screen will be refreshed and display the added curriculum in the Sub-curriculum(s) panel.");
	}
}
