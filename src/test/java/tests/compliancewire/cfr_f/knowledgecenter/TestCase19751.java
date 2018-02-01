package tests.compliancewire.cfr_f.knowledgecenter;

/**
 * @Roopesh
 * 1.date time stamp is not captured as part of expected result  7 
 * 2.Test step 5 should be split to 2 separate steps "Verify that Password is displayed in encrypted form for e-signature"
 * 3.Create web services to check and uncheck password policies 
 */
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import ui.utils.Tools;

public class TestCase19751 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19751.class);

	@Test(alwaysRun = true, description = "E-signatures in ComplianceWire display a computer generated date and time stamp when "
			+ "the signature was executed", groups = { "cfr_f", "cfr_f.knowledgecenter" })
	public void Knowledgecenter_e_sign_date_and_timestamp() throws Exception {
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
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openTrainingPage().openAddTrainingItem().openInstructorLedCourse()
				.addInstructorLedCourse(getFromStorage("randomTag1") + "ILCtrng",
						getFromStorage("randomTag1") + "ILCtrng", "Auto Polaris 2.0")
				.openAddClass().addClass();
		addToStorage("classCode", classesPage.getClassCode());
		classesPage.openOptionsPage().openEsignatureRequirements().openEditRequirements()
				.unCheckReqEsigStudOnlineRegn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("TC19721.SIGNATUREREASON"), "RecScr")
				.openEditRequirements().checkReqEsigStudOnlineRegn().saveChanges()
				.electronicallySignIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("TC19721.SIGNATUREREASON"), "RecScr");
		Assert(esignatureRequirementsPage.getEsignatureRequirements(10),("images/Checked.jpg"),
				"Precondition: Require e-signatures for Student Online Registration ENABLED");

		optionsPage.openTrainingPage().searchTraining(getFromStorage("randomTag1") + "ILCtrng");
		Assert(trainingManagementPage.getAllowOnlineRegistration()
				.contains("Allow users to self register for this course online"),
				"Precondition:Instructor Led Course with a class, both with self-registration ON");
		trainingManagementPage.openClasses().openTrainingItem(getFromStorage("randomTag1") + "ILCtrng");
		Assert(classesPage.getonlineRegistration().contains("On"),
				"Precondition:Instructor Led Course with a class, both with self-registration ON");
		addToStorage("userInfo", getRandomEntityID().substring(0, 7));
		classesPage.openUsersPage().openAddUser()
				.addANewUser(getFromStorage("userInfo") + "fn", getFromStorage("userInfo") + "ln",
						getFromStorage("userInfo") + "usr", getData("TestCase19751.ORGANIZATION"))
				.logOut();
		loginPage
				.signIn(getFromStorage("userInfo") + "usr", getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openCatalog().searchTrainingItem(getFromStorage("randomTag1") + "ILCtrng")
				.trainingItemGeneralInfo(getFromStorage("randomTag1") + "ILCtrng");
		Assert(catalogPage.getcatalogPageContent().contains(getFromStorage("randomTag1") + "ILCtrng"),
				"Learner  will be able to navigated to Catalog page and view the course information");

		catalogPage.openTrainingItemClassInfo().openClassCode(getFromStorage("classCode")).registerForClass();
		Assert(catalogPage.getEsignatureOverlay("frameId").indexOf("User Id") >= 0,
				"e-Signature Required popup window will be displayed");
		Assert(catalogPage.getPwdBoxAttribute().equalsIgnoreCase("password"),
				"On entering Password for e-signature, password will be displayed in encrypted form");
		catalogPage.electronicallySignIn(getFromStorage("userInfo") + "usr", getData("GENERIC.PASSWORD"),
				getData("TC19751.SIGNATUREREASON"), "frameId");
		addToStorage("systemTime", Tools.getDateTime("MM/dd/yyyy hh:mm:ss a"));
		
		todoPage.openTrainingItem(getFromStorage("randomTag1") + "ILCtrng").openClassInformation();
		Assert(todoPage.getToDoPageContent().contains(getFromStorage("randomTag1") + "ILCtrng")
				&& todoPage.getToDoPageContent().contains(getFromStorage("classCode"))
				&& todoPage.geteSignature(getFromStorage("userInfo") + "usr")
						.contains(getFromStorage("userInfo") + "fn")
				&& todoPage.geteSignature(getFromStorage("userInfo") + "usr")
						.contains(getFromStorage("userInfo") + "ln")
				&& todoPage.geteSignature(getFromStorage("userInfo") + "usr")
						.contains(getFromStorage("userInfo") + "usr")
				&& todoPage.geteSignature(getFromStorage("userInfo") + "usr")
						.contains(getFromStorage("systemTime").split(" ")[0])
				&& todoPage.geteSignature(getFromStorage("userInfo") + "usr").contains("I Confirm"),
				"The  ILC information window will display the class information with the e-signature which will display a computer generated date and time stamp based on User's PC time zone");
		AssertTime("MM/dd/yyyy hh:mm:ss a",
				todoPage.geteSignature(getFromStorage("userInfo") + "usr").replace("|", "-").split("-")[1].trim(),
				getFromStorage("systemTime"),
				"Electronic signature applied at Catalog page and the one displayed in ToDo page match. PC SYSTEM TIME: "
						+ getFromStorage("systemTime"));
	}
}
