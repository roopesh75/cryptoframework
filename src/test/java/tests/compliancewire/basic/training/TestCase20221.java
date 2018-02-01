package tests.compliancewire.basic.training;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import initializer.BaseTest;

public class TestCase20221 extends BaseTest {
	private static final Logger logger = Logger.getLogger(TestCase20221.class);

	@Test(alwaysRun = true, description = "The multiple-choice questions will require one correct answer, and 3 distracters. Add a Multiple Choice question.", groups = {})
	public void Training_CD_Quiz_with_multiple_choice_questions() throws Exception {
		
		addToStorage("Training1", getRandomEntityID().substring(0, 7)+"_CDTrng");
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
		
		userManagementPage.returnFromUserManagement().openTrainingPage().openAddTrainingItem().openControlDocumentCourse().addControlDocumentTraining(getFromStorage("Training1"),
				getFromStorage("Training1"), "Auto Polaris 2.0").openQuiz().addQuiz().openReturn().openQuiz();
		Assert(quizPage.getQuizRevisionInfo().contains("disabled"), "Control document w/new (disabled) quiz.");
		addQuestionCustomExamPage.openReturn();
		Assert(trainingManagementPage.getTrainingTitle().contains(getFromStorage("Training1")), "General information of training should be displayed");
		trainingManagementPage.openQuiz().openInitialRevision();
		Assert(addQuestionCustomExamPage.getQuizQuestionPanel().contains("Revision Notes:"), "Quiz details should be displayed");
		addQuestionCustomExamPage.addMultipleResponseQuestion(true);
		Assert(addQuestionCustomExamPage.getCorrectAnswerStatus(0), "Add Multiple Response screen is displayed and Correct answer checkbox is checked.");
		addQuestionCustomExamPage.saveQuestion().editExamDetails().expandQuestion();
		Assert(addQuestionCustomExamPage.getQuizQuestionPanel().contains("Water has following Properties:"), "A Multiple Choice question will be added and displayed in the Quiz details screen.");
		
	}
}
