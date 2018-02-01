package tests.compliancewire.basic.catalog;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import ui.utils.Tools;

public class TestCase20193 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase20193.class);

	@Test(alwaysRun = true, description = "When an instructor led course and class are both defined to allow online registration,"
			+ " if a user is not registered in a class for the course, course info and classes will be displayed. "
			+ "User will register and electronically sign", groups = {})
	public void Catalog_ILC_register_for_class() throws Exception {
		addToStorage("20193Tag1", getRandomEntityID().substring(0, 5));
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		Assert(loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("20193Tag1") + "20193Usr", getFromStorage("20193Tag1") + "20193Usr",
						getFromStorage("20193Tag1") + "20193Usr", getData("TestCase19732.ORGANIZATION"))
				.openSecurityRoles().getSecurityRole().contains("Learner"),
				"Precondition: Learner User" + getFromStorage("20193Tag1") + "20193Usr");
		userManagementPage.returnFromUserManagement().openTrainingPage().openAddTrainingItem().openInstructorLedCourse()
				.addInstructorLedCourse(getFromStorage("20193Tag1") + "ILCtrng",
						getFromStorage("20193Tag1") + "ILCtrng", "Auto Polaris 2.0");
		Assert(trainingManagementPage.getTrainingTitle().contains(getFromStorage("20193Tag1") + "ILCtrng")
				&& trainingManagementPage.getAllowOnlineRegistration()
						.contains("Allow users to self register for this course online"),
				"Precondition: Instructor Led Course with online Self registration enabled");
		Assert(trainingManagementPage.openClasses().openAddClass().addClass().getClassTitle()
				.contains(getFromStorage("20193Tag1")) && classesPage.getonlineRegistration().contains("On"),
				"Precondition: Instructor led course with class defined to allow online registration");
		addToStorage("20193ClassCode", classesPage.getClassCode());
		classesPage.openOptionsPage().openEsignatureRequirements().openEditRequirements()
				.unCheckReqEsigStudOnlineRegn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("TC19721.SIGNATUREREASON"), "RecScr")
				.openEditRequirements().checkReqEsigStudOnlineRegn().saveChanges()
				.electronicallySignIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("TC19721.SIGNATUREREASON"), "RecScr");
		Assert(esignatureRequirementsPage.getEsignatureRequirements(10).contains("images/Checked.jpg"),
				"Precondition: Require e-signatures for Student Online Registration ENABLED");
		esignatureRequirementsPage.logOut();
		Assert(loginPage
				.signIn(getFromStorage("20193Tag1") + "20193Usr", getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openUserProfile().openDateTimeFormat().changeTimeDisplaySettings("3|1:30:55 PM UTC-5")
				.getTimeDisplaySettings().contains("1:30:55 PM UTC-5"),
				"Precondition:Learner with the Preferred Date/Time format set as “h:mm:ss tt UTC-5 and PC Time Zone set");
		preferencesPage.openCatalog().searchTrainingItem(getFromStorage("20193Tag1") + "ILCtrng")
				.trainingItemGeneralInfo(getFromStorage("20193Tag1") + "ILCtrng");
		Assert(catalogPage.getcatalogPageContent().contains(getFromStorage("20193Tag1") + "ILCtrng"),
				"View Training Information window will display the course information and classes available for online registration for this course");
		Assert(catalogPage.openTrainingItemClassInfo().openClassCode(getFromStorage("20193ClassCode"))
				.registerForClass().getEsignatureOverlay("frameId").indexOf("User Id") >= 0,
				"e-Signature window will be displayed");
		catalogPage.electronicallySignIn(getFromStorage("20193Tag1") + "20193Usr", getData("GENERIC.PASSWORD"),
				getData("TC19751.SIGNATUREREASON"), "frameId");
		addToStorage("systemTime", Tools.getDateTime("MM/dd/yyyy hh:mm:ss a"));
		addToStorage("timeZone", Tools.getSystemTimeZone(false));
		Assert(todoPage.openTrainingItem(getFromStorage("20193Tag1") + "ILCtrng").openClassInformation()
				.geteSignature(getFromStorage("20193Tag1") + "20193Usr")
				.contains(getFromStorage("20193Tag1") + "20193Usr")
				&& todoPage.geteSignature(getFromStorage("20193Tag1") + "20193Usr")
						.contains(getFromStorage("20193Tag1"))
				&& todoPage.geteSignature(getFromStorage("20193Tag1") + "20193Usr")
						.contains(getFromStorage("20193Tag1"))
				&& todoPage.geteSignature(getFromStorage("20193Tag1") + "20193Usr")
						.contains(getFromStorage("systemTime").split(" ")[0])
				&& todoPage.geteSignature(getFromStorage("20193Tag1") + "20193Usr").contains(getFromStorage("timeZone"))
				&& todoPage.geteSignature(getFromStorage("20193Tag1") + "20193Usr").contains("I Confirm"),
				"User will be registered after electronically signing and the electronic signature will be comprised of the signer information (First Name, Last Name, User ID), date and time stamp will display based on  the Learner's PC time zone in the preferred date/time format, and the meaning/reason associated with signature");
		AssertTime("MM/dd/yyyy hh:mm:ss a",
				todoPage.geteSignature(getFromStorage("20193Tag1") + "20193Usr").replace("|", "-").split("-")[1].trim(),
				getFromStorage("systemTime"),
				"Electronic signature applied at Catalog page and the one displayed in ToDo page match. PC SYSTEM TIME: "
						+ getFromStorage("systemTime"));

	}

}
