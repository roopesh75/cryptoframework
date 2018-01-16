package tests.compliancewire.basic.training;

/**
 * @Roopesh
 * check the below code
 * trainingManagementPage.closeManageTrainingTypes().returnFromTrainingManagement().openAddTrainingItem()
				.openCustomExamCourse();
		customExamPage.addCustomExam(getFromStorage("randomTag1") + "CEtrng", getFromStorage("randomTag1") + "CEtrng",
				"Auto Polaris 2.0");
 * 
 */
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import ui.utils.Tools;

public class TestCase20205 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20205.class);

	@Test(alwaysRun = true, dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = ""
			+ "Add a control document type training item that contains a web address", groups = {"basic", "basic.training"})
	public void Add_control_document_type_training_item_that_contains_web_address(String userName) throws Exception {
		loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE")).openToolsMenu()
				.openUsersPage().searchUser(userName).openSecurityRoles();
		Assert(true, "Precondition-1: Admin User");
		userManagementPage.returnFromUserManagement().openTrainingPage();
		addToStorage("Training1", getRandomEntityID().substring(0, 6) + "CDtrng");
		trainingPage.openAddTrainingItem().openControlDocumentCourse();
		Assert(controlDocumentTrainingPage.isControlDocumentPage(),
				"Control Document training item details page should be displayed");
		controlDocumentTrainingPage.enterCodeTitleSelectOrganization(getFromStorage("Training1"),
				getFromStorage("Training1"), "Auto Polaris 2.0");
		Assert(controlDocumentTrainingPage.getCode().contains(getFromStorage("Training1"))
				&& controlDocumentTrainingPage.getTitle().contains(getFromStorage("Training1"))
				&& controlDocumentTrainingPage.getOrganization().contains("Auto Polaris 2.0"),
				"Admin user will be able select/enter the data");
		controlDocumentTrainingPage.enterApprovalEffectiveDates();
		Assert(controlDocumentTrainingPage.getApproveDate().contains(Tools.getCurrentDate())
				&& controlDocumentTrainingPage.getEffectiveDate().contains(Tools.getCurrentDate()),
				"Admin user will be able select/enter the data");
		controlDocumentTrainingPage.enterAssortedFields();
		Assert(controlDocumentTrainingPage.getWebAddress().contains("www.google.com"),
				"Admin user will be able select/enter the data");
		Assert(controlDocumentTrainingPage.testWebAddress().getTestWebAddressPageTitle().contains("Google"),"The web address, when tested, will open in a new browser window");
		controlDocumentTrainingPage.closeTestWebaddressBowser();
		Assert(controlDocumentTrainingPage.saveControlDocument().getTrainingType().contains("Control Document"),
				"The control document type training item will be saved and the general information screen will display all the new control document information");

	}
}
