package tests.compliancewire.basic.todolist;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;

public class TestCase20218 extends BaseTest {

	private static final Logger logger = Logger.getLogger(TestCase20218.class);

	@Test(alwaysRun = true, description = "Remove optional assignments from to do list.", groups = {})
	public void To_Do_List_Remove_optional_assignments() throws Exception {
		addToStorage("adminUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_AdminUsr");
		addToStorage("learnerUser",
				getRandomEntityID().substring(0, 7) + getRandomEntityID().substring(0, 5) + "_LearnerUser");
		addToStorage("Training1",getRandomEntityID().substring(0, 5) + "_CDTrng");
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
						getFromStorage("Training1"), "Auto Polaris 2.0").openReturn().openAssignmentPage().
				openAddAssignmentDefinition().
				addAnAssignmentDefinition("0","1",getFromStorage("Training1"),getFromStorage("learnerUser"),"elective").continueAndCheckAssignment().returnToAssignmentPage().logOut().
				signIn(getFromStorage("learnerUser"), getData("GENERIC.PASSWORD"),
						getData("GENERIC.AUTOMATION.COMPANYCODE"));
		todoPage.openSearch().searchTraining(getFromStorage("Training1")).openTrainingInfo();
		Assert(todoPage.getToDoPageContent().contains("Suggested Assignment"), "Precondition: At least 1 elective or 1 suggested assignment on user's To-Do list.");	
		todoPage.refreshPage();
		todoPage.openSearch().searchTraining(getFromStorage("Training1"));
		Assert(todoPage.getToDoPageContent().contains(getFromStorage("Training1")), "Searched training item will be displayed in the To-Do list.");
		todoPage.removeTraining();
		Assert(todoPage.getToDoPageContent().contains("Your report contains 0 records."), "Elective or suggested assignment will be removed from to do list.");

	}

}
