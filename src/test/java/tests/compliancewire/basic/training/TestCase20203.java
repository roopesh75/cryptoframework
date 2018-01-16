package tests.compliancewire.basic.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;

public class TestCase20203 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20203.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Edit information for any effective Control "
			+ "document.", groups = {
			"basic", "basic.training" })
	public void Edit_information_for_any_effective_Control_document(String userName) throws Exception {
		addToStorage("Training1", getRandomEntityID().substring(0,6)+"CDtrng");
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().searchUser(userName).openSecurityRoles();
		Assert(userManagementPage.getUserId(), userName, "Precondition: Admin User -"+userName);
		
		userManagementPage.returnFromUserManagement().
		openTrainingPage().openAddTrainingItem().openControlDocumentCourse().
		addControlDocumentTraining(getFromStorage("Training1"),getFromStorage("Training1"),"Auto Polaris 2.0");
		//Assert(true,true, "View training information screen should be displayed");		
		
		
		trainingManagementPage.openGeneralInformation().openEditGeneralInformation().addDescription("advanced");
		trainingManagementPage.openEditGeneralInformation().addCourseComments("newComment");
		Assert(trainingManagementPage.getDescription().contains("advanced"), "User is able to edit the CD info and View Training screen will display with the updated training item information.");
		Assert(trainingManagementPage.getComments().contains("newComment"), "User is able to edit the CD info and View Training screen will display with the updated training item information.");
				

	}
}
