package tests.compliancewire.cfr_f.login;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase19720 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase19720.class);

	@Test(priority=23,enabled=false,  
			description = "Login_Users login_Login missing forgotten password questions", groups = {"cfr_f","cfr_f.login"
			 })
	public void Login_Users_login_Login_missing_forgotten_password_questions() throws Exception {
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));
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
				.openToolsMenu().openOptionsPage().openClassRoomOptions().openResetForgottenPasswordOption()
				.chooseResetPasswordQuestions(1);
		Assert(optionsManagementPage.getResetPasswordRadioBtnStatus(1) && optionsManagementPage.getForceUsersAnswerPassSecuQuestionCheckBoxStatus(),
				true,"Precondition: Reset Forgotten Password option enabled  & Force answer question option enabled ");
		optionsManagementPage.saveResetPasswordSetting();
		addToStorage("userInfo", getRandomEntityID().substring(0, 6));
		trainingManagementPage.openUsersPage().openAddUser().addANewUser(getFromStorage("userInfo") + "usr2",
				getFromStorage("userInfo") + "usr2", getFromStorage("userInfo") + "usr2",
				getData("TestCase19751.ORGANIZATION"));
		userManagementPage.logOut().signIn(getFromStorage("userInfo") + "usr2", getData("GENERIC.PASSWORD"),
				getData("GENERIC.AUTOMATION.COMPANYCODE"));

		Assert(homePage.getPanelTitle(),"Security Questions","After login, a Warning message will indicate that questions must be answered in order to continue. ");
		homePage.openAnswerQuestions().addQuestionAnswer();
		Assert(homePage.isHomePage(),true,"User will be navigated to the Knowledge Center. ");
		homePage.logOut().signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE")).openAnswerQuestions().addQuestionAnswer()
				.openToolsMenu().openOptionsPage().openClassRoomOptions().openResetForgottenPasswordOption()
				.chooseResetPasswordQuestions(0).saveResetPasswordSetting();
	}

}
