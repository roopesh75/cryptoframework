package tests.compliancewire.basic.todolist;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;

public class TestCase20219 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase20219.class);

	@Test(alwaysRun = true, description = "To-Do list report can be Sorted and viewed online", groups = {})
	public void To_Do_To_Do_List_Sort_View() throws Exception {
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		addToStorage("learnerUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_LearnerUser");
		addToStorage("Training1","1"+ getRandomEntityID().substring(0, 5) + "_CDTrng");
		addToStorage("Training2","2" +getRandomEntityID().substring(0, 5) + "_CDTrng");
		loginPage
				.signIn(getData("GENERIC.USER"), getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE"))
				.openToolsMenu().openUsersPage().openAddUser()
				.addANewUser(getFromStorage("adminUser") + "fn", getFromStorage("adminUser") + "ln",
						getFromStorage("adminUser"), getData("GENERIC.TOP_ORGANIZATION"))
				.openSecurityRoles().openAssignSecurityRole()
				.assignRole(getData("GENERIC.TOP_ORGANIZATION"), getData("GENERIC.ROLE_ADMIN")).logOut()
				.signIn(getFromStorage("adminUser"), getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE")).openToolsMenu().openUsersPage().openAddUser().
				addANewUser(getFromStorage("learnerUser") + "fn", getFromStorage("learnerUser") + "ln",
						getFromStorage("learnerUser"), getData("GENERIC.TOP_ORGANIZATION")).openTrainingPage().openAddTrainingItem().openControlDocumentCourse()
				.addControlDocumentTraining(getFromStorage("Training1"),
						getFromStorage("Training1"), "Auto Polaris 2.0").openReturn().openAddTrainingItem().openControlDocumentCourse()
				.addControlDocumentTraining(getFromStorage("Training2"),
						getFromStorage("Training2"), "Auto Polaris 2.0").openAssignmentPage().
				openAddAssignmentDefinition().
				addAssignment(getFromStorage("Training1"),getFromStorage("learnerUser")).returnToAssignmentPage().openAddAssignmentDefinition().
				addAssignment(getFromStorage("Training2"),getFromStorage("learnerUser")).returnToAssignmentPage().logOut().
				signIn(getFromStorage("learnerUser"), getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"));
		Assert(todoPage.getAssignedTrainingCodes().contains(getFromStorage("Training1")) && todoPage.getAssignedTrainingCodes().contains(getFromStorage("Training2")), "Precondition: Learner with at least 2 assignments in To-Do list.");	
		Assert(todoPage.getAssignedTrainingCodes().contains(getFromStorage("Training1")) && todoPage.getAssignedTrainingCodes().contains(getFromStorage("Training2")), "To-Do list will be displayed.");	
		todoPage.openSortOptions().sortByTitleZtoA();	
		Assert(Integer.parseInt(todoPage.getAssignedTrainingCodes().get(0).substring(0,1))>Integer.parseInt(todoPage.getAssignedTrainingCodes().get(1).substring(0,1)), "To-Do list will be displayed in the sort manner based on the option selected");

	}

}
