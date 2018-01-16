package tests.compliancewire.usergroup;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import org.testng.annotations.Test;

import ui.utils.RegularExpression;

/**
 * Created by vevinmoza on 3/18/16.
 */

public class TestCase19599 extends BaseTest {

    @Test(alwaysRun=true,dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Users_UserGroup_OR relationship "
    		+ "between multiple values of a domain hierarchy and equals to criteria",
            groups = {"USER_GROUP_CRITERIA"})
    public void Users_UserGroup_OR_relationship_between_multiple_values_of_a_domain_hierarchy_and_equals_to_criteria(String userName) throws Exception {
        loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE")).
                openToolsMenu().
                openUsersPage().
                openCreateNewGroup().
                createNewGroup(getRandomEntityID());

       
        groupManagementPage.openEditGroupCriteria();
       

        editGroupCriteriaPage.addCriteriaDomain(getData("GENERIC.CRITERIA_DOMAIN_HIERARCHY"), getData("GENERIC.OPERATOR_EQUALS_TO"), getData("TC19599.VALUE_QA1")).
                addCriteriaDomain(getData("GENERIC.CRITERIA_DOMAIN_HIERARCHY"), getData("GENERIC.OPERATOR_EQUALS_TO"), getData("TC19599.VALUE_QA2"));
        editGroupCriteriaPage.saveChanges();
        groupManagementPage.refreshGroupMembershipCriteria();
        addToStorage("user_count", groupManagementPage.getUserCount());
        //1
        Assert(groupManagementPage.getUserCount().matches(RegularExpression.NUMBER), "Group Information screen of the user group will be displayed with the user count.");
        groupManagementPage.openGroupUsers();
        //2
        Assert(getFromStorage("user_count").matches(groupManagementPage.getUserCountInGroupUsers()), "Users meeting either of the values of the criteria are added to the user group.");
        groupManagementPage.closeGroupUsersOverlay().openEditGroupCriteria();
        //3
        Assert(editGroupCriteriaPage.getMatchConditionsState() == true &&
                editGroupCriteriaPage.getOperatorForCriteria(getData("GENERIC.CRITERIA_DOMAIN_HIERARCHY")).
                        get(0).matches(getData("GENERIC.OPERATOR_EQUALS_TO")) &&
                editGroupCriteriaPage.
                        getRelationshipForCriteria(getData("GENERIC.CRITERIA_DOMAIN_HIERARCHY")).get(0).
                        matches(getData("GENERIC.RELATIONSHIP_OR")), "An \"or\" relationship is displayed for the chips of the 'domain hierarchy'" +
                " and 'equals to' operator criteria super-chip for 'Match All Conditions' checked");



        editGroupCriteriaPage.removeAllCriteria();
        //4
        Assert(editGroupCriteriaPage.getCriteriaContent().length() == 0, "All criteria saved to the user group will be removed.");
        //  Assert.assertTrue(editGroupCriteriaPage.getCriteriaContent().length() == 0, "All criteria saved to the user group will be removed.");
        editGroupCriteriaPage.saveChanges();
        groupManagementPage.refreshGroupMembershipCriteria();
        //5
        Assert(groupManagementPage.getUserCount().matches("0"), "Current User Count of the user group will be updated to '0'.");
        //  Assert.assertTrue(groupPage.getUserCount().matches("0"), "Current User Count of the user group will be updated to '0'.");
        groupManagementPage.openEditGroupCriteria().selectMatchAllConditions(false).
                addCriteriaDomain(getData("GENERIC.CRITERIA_DOMAIN_HIERARCHY"), getData("GENERIC.OPERATOR_EQUALS_TO"), getData("TC19599.VALUE_QA1")).
                addCriteriaDomain(getData("GENERIC.CRITERIA_DOMAIN_HIERARCHY"), getData("GENERIC.OPERATOR_EQUALS_TO"), getData("TC19599.VALUE_QA2"));
        //6
        Assert(editGroupCriteriaPage.getCriteriaContent().length()>0, "Criteria will be added to the user group.");
        // Assert.assertTrue(editGroupCriteriaPage.getCriteriaContent().length() > 0, "Criteria will be added to the user group.");
        editGroupCriteriaPage.saveChanges();
        groupManagementPage.refreshGroupMembershipCriteria();
        addToStorage("user_count1", groupManagementPage.getUserCount());
        //7
        Assert(groupManagementPage.getUserCount().matches(RegularExpression.NUMBER), "Group Information screen of the user group will be displayed with the user count.");
        //  Assert.assertTrue(groupPage.getUserCount().matches(RegularExpression.NUMBER), "Group Information screen of the user group will be displayed with the user count.");
        groupManagementPage.openGroupUsers();
        //8
        Assert(getFromStorage("user_count1").matches(groupManagementPage.getUserCountInGroupUsers()), "Users meeting either of the values of the criteria are added to the user group.");
        // Assert.assertTrue(getFromStorage("user_count1").matches(groupPage.getUserCountInGroupUsers()), "Users meeting either of the values of the criteria are added to the user group.");

        groupManagementPage.closeGroupUsersOverlay().openEditGroupCriteria();
        //9
        Assert(editGroupCriteriaPage.getMatchConditionsState() == false &&
                editGroupCriteriaPage.getOperatorForCriteria(getData("GENERIC.CRITERIA_DOMAIN_HIERARCHY")).
                        get(0).matches(getData("GENERIC.OPERATOR_EQUALS_TO")) &&
                editGroupCriteriaPage.
                        getRelationshipForCriteria(getData("GENERIC.CRITERIA_DOMAIN_HIERARCHY")).get(0).
                        matches(getData("GENERIC.RELATIONSHIP_OR")), "An \"or\"  relationship is displayed for the chips of the 'domain hierarchy' and " +
                "'equals to' operator criteria super-chip for 'Match All Conditions' unchecked");

    }



}
