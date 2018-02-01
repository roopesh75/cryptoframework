package tests.compliancewire.basic.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import initializer.BaseTest;
import initializer.DynamicDataProvider;
import ui.utils.Tools;

public class TestCase20227 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20227.class);

	@Test(alwaysRun = true, description = "The Manage Training Types window displays pertinent information about the training type. From here"
			+ " the user can enable and disable training types. Add a "
			+ "control document training type to the system.", groups = {
			 })
	public void Training_Manage_Training_Types() throws Exception {
		addToStorage("Training1", getRandomEntityID().substring(0, 7)+"CDTrng");
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
	
		userManagementPage.openTrainingPage().openManageTrainingTypes();
		Assert(trainingManagementPage.isManageTrainingTypePage(), "Manage training types screen will be displayed");
		trainingManagementPage.openTrainingType("Offline Task");
		Assert(trainingManagementPage.getContentManageTrainingTypes().contains("Offline Task"), "Manage Training Types window will display with pertinent information about the training type. ");
		trainingManagementPage.makeDisabled();
		Assert(!trainingManagementPage.getPRINTTableBorder().contains("Offline Task"), "A training type will be disabled.");	
		trainingManagementPage.openDisabledTrainingTypes();
		Assert(trainingManagementPage.getPRINTTableBorder().contains("Offline Task"), "The Training Types List will re display to verify the status of the training type has been changed to 'Disabled'. ");
		trainingManagementPage.openTrainingType("Offline Task").makeEnabled();
		Assert(!trainingManagementPage.getPRINTTableBorder().contains("Offline Task"), "The disabled type will be re-enabled.");
		trainingManagementPage.openEnabledTrainingTypes();
		Assert(trainingManagementPage.getPRINTTableBorder().contains("Offline Task"), "Training Types List will re-display to verify the status of the training type has been changed to 'Enabled'.");
		addToStorage("newTrainingType", getRandomEntityID().substring(0,4)+"_type");
		trainingManagementPage.openAddTrainingType().addTrainingType("Control Document",getFromStorage("newTrainingType"),"abbr");
		Assert(trainingManagementPage.getPRINTTableBorder().contains(getFromStorage("newTrainingType")), "A control document training type will be added to the system.");
		trainingManagementPage.openTrainingType(getFromStorage("newTrainingType")).makeDisabled();
		
		
	}
}
