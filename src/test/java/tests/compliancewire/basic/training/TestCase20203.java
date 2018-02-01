package tests.compliancewire.basic.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase20203 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20203.class);

	@Test(alwaysRun = true, description = "Edit information for any effective Control document and add a web address "
			+ "document.", groups = {"basic","basic.training"})
	public void Training_CD_Edit_information_with_web_address() throws Exception {
		
		
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
		Assert(trainingManagementPage.getTrainingStatus().equalsIgnoreCase("Effective"), true,
				"Precondition: Control document with effective status");
		Assert(trainingManagementPage.getTrainingTitle().contains(getFromStorage("Training1")),
				"Control Document General Information screen will be displayed for the user");
		trainingManagementPage.openGeneralInformation().openEditGeneralInformation();
		controlDocumentTrainingPage.enterAssortedFields("2");
		Assert(controlDocumentTrainingPage.getWebAddress().contains("www.google.com"),
				"User is able to edit the CD info");
		Assert(controlDocumentTrainingPage.testWebAddress().getTestWebAddressPageTitle().contains("Google"),
				"The web address, when tested, will open in a new browser window");
		controlDocumentTrainingPage.closeTestWebaddressBrowser();
		Assert(controlDocumentTrainingPage.saveControlDocument("2").getTrainingType().contains("Control Document"),
				"The control document type training item will be saved and the general information screen will display all the new control document information");
		Assert(trainingManagementPage.getDescription().contains("Control Document"),
				"The control document type training item will be saved and the general information screen will display all the new control document information");
		Assert(trainingManagementPage.getComments().contains("comment"),
				"The control document type training item will be saved and the general information screen will display all the new control document information");
		Assert(trainingManagementPage.getCategory().contains("General"),
				"The control document type training item will be saved and the general information screen will display all the new control document information");
		Assert(trainingManagementPage.getLanguage().contains("English"),
				"The control document type training item will be saved and the general information screen will display all the new control document information");
		Assert(trainingManagementPage.getAbbreviation().contains("abbr"),
				"The control document type training item will be saved and the general information screen will display all the new control document information");
	}
}
