package tests.compliancewire.basic.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import initializer.BaseTest;
import initializer.DynamicDataProvider;
import ui.utils.Tools;

public class TestCase20229 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20229.class);

	@Test(alwaysRun = true, description = "Add a training item equivalency (substitute) to a training item.", groups = {
			 })
	public void Training_Add_a_training_item_equivalency() throws Exception {
		addToStorage("Training1", getRandomEntityID().substring(0, 7)+"CDTrng");
		addToStorage("Training2", getRandomEntityID().substring(0, 7)+"CDTrng");
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
		
		userManagementPage.returnFromUserManagement().openTrainingPage().openAddTrainingItem().openControlDocumentCourse().addControlDocumentTraining(getFromStorage("Training1"),
				getFromStorage("Training1"), "Auto Polaris 2.0");
		Assert(trainingManagementPage.getTrainingTitle().contains(getFromStorage("Training1")),"Training item #1.");
		trainingManagementPage.returnFromTrainingManagement().openAddTrainingItem().openControlDocumentCourse().addControlDocumentTraining(getFromStorage("Training2"),
				getFromStorage("Training2"), "Auto Polaris 2.0");
		Assert(trainingManagementPage.getTrainingTitle().contains(getFromStorage("Training2")),"Training item #2.");
		trainingManagementPage.openEquivalancies().openAddTrainingItemEquivalency();
		Assert(trainingManagementPage.getContentScreenTitle().contains("Equivalencies"),"Equivalencies screen will be displayed");
		trainingManagementPage.searchTraining(getFromStorage("Training1"));
		Assert(trainingManagementPage.getResultsFromSearchOverlay().contains(getFromStorage("Training1")),"The training item will get listed.");
		trainingManagementPage.chooseVersion(getFromStorage("Training1"), "1.0.0");
		Assert(trainingManagementPage.getPRINTTableBorder(3).contains(getFromStorage("Training1")),"Equivalencies screen will display a record for the substitute training item. ");
		Assert(trainingManagementPage.getPRINTTableBorder(3).contains(getFromStorage("adminUser")),"Created By column will display the correct time and user details");
		AssertTime("m/d/yyyy hh:mm:ss a", trainingManagementPage.getTrainingEquivalencyAddedTime(), Tools.getDateTime("m/d/yyyy hh:mm:ss a"), "Created On column will display the correct time and user details");

	}
}
