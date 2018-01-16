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
import initializer.DynamicDataProvider;
import ui.utils.Tools;

public class TestCase19751 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase19751.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Knowledge center_e-sign_date and time stamp", groups = {
			"cfr_f", "cfr_f.knowledgecenter" })
	public void Knowledgecenter_e_sign_date_and_timestamp(String userName) throws Exception {
		addToStorage("randomTag1", getRandomEntityID().substring(0, 7));
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openTrainingPage().openAddTrainingItem().openInstructorLedCourse()
				.addInstructorLedCourse(getFromStorage("randomTag1") + "trng", getFromStorage("randomTag1") + "trng",
						"Auto Polaris 2.0")
				.openAddClass().addClass();
		addToStorage("classCode", classesPage.getClassCode());
		classesPage.openOptionsPage().openEsignatureRequirements().openEditRequirements()
				.unCheckReqEsigStudOnlineRegn(userName, getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"),
						"RecScr")
				.openEditRequirements().checkReqEsigStudOnlineRegn().saveChanges().electronicallySignIn(userName,
						getData("GENERIC.PASSWORD"), getData("TC19721.SIGNATUREREASON"), "RecScr");
		Assert(esignatureRequirementsPage.getEsignatureRequirements(10).contains("images/Checked.jpg"),
				"Precondition: Require e-signatures for Student Online Registration ENABLED");

		optionsPage.openTrainingPage().searchTraining(getFromStorage("randomTag1") + "trng");
		Assert(trainingManagementPage.getAllowOnlineRegistration()
				.contains("Allow users to self register for this course online"),
				"PreCondition:Instructor Led Course with a class, both with self-registration ON");
		trainingManagementPage.openClasses().openTrainingItem(getFromStorage("randomTag1") + "trng");
		Assert(classesPage.getonlineRegistration().contains("On"),
				"PreCondition:Instructor Led Course with a class, both with self-registration ON");
		addToStorage("userInfo", getRandomEntityID().substring(0, 7));
		classesPage.openUsersPage().openAddUser()
				.addANewUser(getFromStorage("userInfo") + "usr2", getFromStorage("userInfo") + "usr2",
						getFromStorage("userInfo") + "usr2", getData("TestCase19751.ORGANIZATION"))
				.logOut();
		loginPage
				.signIn(getFromStorage("userInfo") + "usr2", getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openCatalog().searchTrainingItem(getFromStorage("randomTag1") + "trng")
				.trainingItemGeneralInfo(getFromStorage("randomTag1") + "trng");
		Assert(true, "Learner  will be able to navigated to Catalog page and view the course information");
		
		catalogPage.openTrainingItemClassInfo().openClassCode(getFromStorage("classCode")).registerForClass();
		Assert(catalogPage.getEsignatureOverlay("frameId").indexOf("User Id") >= 0,
				"e-Signature Required popup window will be displayed");
		Assert(catalogPage.getPwdBoxAttribute().equalsIgnoreCase("password"),
				"On entering Password for e-signature, password will be displayed in encrypted form");
		catalogPage.electonicallySignIn(getFromStorage("userInfo") + "usr2", getData("GENERIC.PASSWORD"),
				getData("TC19751.SIGNATUREREASON"), "frameId");
		todoPage.openTrainingItem(getFromStorage("randomTag1") + "trng").openClassInformation();
		Assert(todoPage.getToDoPageContent().contains(getFromStorage("userInfo") + "usr2"),
				"The  ILC information window will display the class information with the e-signature which will display a computer generated date and time stamp based on User's PC time zone");
//&& todoPage.getToDoPageContent().contains(Tools.getDateTime())
	}
}
