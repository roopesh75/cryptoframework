package tests.compliancewire.cfr_f.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import ui.utils.MySQLAccess;

public class TestCase19731 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase19731.class);

	@Test(alwaysRun = true, description = 
			"User with appropriate security rights can create Curriculum Quick Reports.", groups = {"cfr_f","cfr_f.training"})
	public void Training_Security_rights_Curriculum_Quick_Reports() throws Exception {
		addToStorage("role", getSecurityRoleSeq()+getRandomEntityID().substring(0,5)+"_role");
		
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
		Assert(userManagementPage.getUserId(),getFromStorage("adminUser"), "Precondition: Admin User");
		usersPage.openTrainingPage().openAssignmentReportByTraining().
		runReportForTrainingOrCurriculum("Curriculum",getData("TC19731.CURRICULUM")+" ("+getData("TC19731.CURRICULUM")+")");
		Assert(trainingManagementPage.getReportTitle(),("Assignment Report by Training"), "Precondition: Assignment Report by Training");
		trainingManagementPage.returnFromAssignmentOrCompletionReport().openCompletionReportByTraining().runReportForCurriculum("In Curriculum",getData("TC19731.CURRICULUM")+" ("+getData("TC19731.CURRICULUM")+")");
		Assert(trainingManagementPage.getReportTitle(),("Completions Report by Training"), "Precondition: Completions Report by Training");	
		trainingManagementPage.returnFromAssignmentOrCompletionReport().openUsersPage().openSecurityRoles().addNewSecurityRole().createNewSecurityRole(getFromStorage("role"));
		Assert(securityRolePage.getSecurityRoles(),(getFromStorage("role")), "The Security role will be created with the rights. "
				);
		addToStorage("user", getRandomEntityID().substring(0,5)+"_usr");
		securityRolePage.returnFromSecurityRole().
		openAddUser().addANewUser(getFromStorage("user")).returnFromUserManagement().
		openSecurityRoles().chooseSecurityRole(getFromStorage("role")).
		assignSecurityRoleToUsr(getFromStorage("user"),"true").selectUserToOverride(getFromStorage("user")).applySecurityRole();		
		Assert(userManagementPage.getPRINTTableBorder(),(getFromStorage("user")), "New security role is assigned to the user.");
		addToStorage("reportName1", getRandomEntityID().substring(0,5)+"_rpt");
		addToStorage("reportName2", getRandomEntityID().substring(0,5)+"_rpt");
		userManagementPage.logOut()
		.signIn(getFromStorage("user"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
		.openToolsMenu().openTrainingPage().openAssignmentReportByTraining().saveCompletionReport(getFromStorage("reportName1"));	
		Assert(trainingPage.isTrainingPage(),true, "The Test User will be able create an assignment report by curriculum Quick Report.");
		trainingPage.openCompletionReportByTraining().saveCompletionReport(getFromStorage("reportName2"));
		Assert(trainingPage.isTrainingPage(),true, "The test user will be able to create a completions report by curriculum Quick Report.");
		trainingPage.searchCurriculum(getData("TC19731.CURRICULUM")).openReports().openCompletionReport(getFromStorage("reportName2"));
		Assert(trainingManagementPage.getReportTitle(),("Completions Report by Training"),
				"The View Curriculum screen will display both the quick reports.");
		trainingManagementPage.returnToReports().openCompletionReport(getFromStorage("reportName1"));
		Assert(trainingManagementPage.getReportTitle(),("Assignment Report by Training"), 
				"The View Curriculum screen will display both the quick reports.");
	}

}
