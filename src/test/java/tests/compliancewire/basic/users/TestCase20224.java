package tests.compliancewire.basic.users;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;

public class TestCase20224 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20224.class);

	@Test(alwaysRun = true, description = "Add a document and link that document to the user", groups = {})
	public void Users_Users_Reference_Document() throws Exception {
		addToStorage("learnerUser", getRandomEntityID().substring(0, 7)+ "_LearnerUsr");
		addToStorage("documentName", getRandomEntityID().substring(0,6)+"_DocName");
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
		
		
		userManagementPage.returnFromUserManagement().openAddUser()
		.addANewUser(getFromStorage("learnerUser") + "fn", getFromStorage("learnerUser") + "ln",
				getFromStorage("learnerUser"), getData("GENERIC.TOP_ORGANIZATION")).openUsersPage().searchUser(getFromStorage("learnerUser")).openSecurityRoles();
		
		Assert(userManagementPage.getSecurityRole().contains("Learner"), "Precondition: Learner User");
		userManagementPage.returnFromUserManagement().searchUser(getFromStorage("learnerUser"));
		Assert(userManagementPage.getFirstName().equalsIgnoreCase(getFromStorage("learnerUser")+"fn"), "General Information of user will be displayed.");
		userManagementPage.openReferenceMaterial().openAddDocument();
		userManagementPage.addDocument();
		AssertDesktop(userManagementPage.getAlertText().contains("Please enter a Document Name"), "A document name is required.");
		userManagementPage.acceptAlert();
		userManagementPage.addDocumentInfo(getRandomEntityID().substring(0,6)+"_DocName","").addDocument();
		AssertDesktop(userManagementPage.getAlertText().contains("Please enter a Document Path"), "A path (where the electronic copy of the document is maintained) is required.");
		userManagementPage.acceptAlert();
		Assert(userManagementPage.addDocumentInfo(getFromStorage("documentName"),"https://www.google.com").testWebAddress().getTitle().contains("Google"), "The document will be opened in a new browser window.");
		userManagementPage.closeTestWebaddressBrowser().addDocument();
		Assert(userManagementPage.getPRINTTableBorder().contains(getFromStorage("documentName")), "An external document will be added to the user’s record.");

	}
}
