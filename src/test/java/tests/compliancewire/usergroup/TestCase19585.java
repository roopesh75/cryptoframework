package tests.compliancewire.usergroup;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.support.Config;
import ui.utils.RegularExpression;

/**
 * Created by vevinmoza on 3/18/16.
 */

public class TestCase19585 extends BaseTest {

    @Test(alwaysRun=true,dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser",description = "Users_UserGroup_AND/OR relationship between multiple values of an alphanumeric custom field and does not contain criteria",groups = {"USER_GROUP_CRITERIA"})
    public void Users_UserGroup_AND_OR_relationship_between_multiple_values_of_an_alphanumeric_custom_field_and_does_not_contain_criteria(String userName) throws Exception {
        loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE")).
                openToolsMenu().
                openUsersPage().
                openCreateNewGroup().
                createNewGroup(getRandomEntityID());
       
        groupManagementPage.openEditGroupCriteria();
       
        editGroupCriteriaPage.addCriteria(getData("GENERIC.CRITERIA_ALPHANUMERIC_CUSTOM_FIELD"), getData("GENERIC.OPERATOR_NOT_CONTAIN"), getData("TC19585.VALUE_NOT_CONTAINS1")).
                addCriteria(getData("GENERIC.CRITERIA_ALPHANUMERIC_CUSTOM_FIELD"), getData("GENERIC.OPERATOR_NOT_CONTAIN"), getData("TC19585.VALUE_NOT_CONTAINS2"));
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
                editGroupCriteriaPage.getOperatorForCriteria(getData("GENERIC.CRITERIA_ALPHANUMERIC_CUSTOM_FIELD")).
                        get(0).matches(getData("GENERIC.OPERATOR_NOT_CONTAIN")) &&
                editGroupCriteriaPage.
                        getRelationshipForCriteria(getData("GENERIC.CRITERIA_ALPHANUMERIC_CUSTOM_FIELD")).get(0).
                        matches(getData("GENERIC.RELATIONSHIP_AND")), "An \"and\"  relationship is displayed for the chips of the 'alphanumeric custom field' " +
                "and 'does not contain' operator criteria super-chip for 'Match All Conditions' checked.");


        editGroupCriteriaPage.selectMatchAllConditions(false).saveChanges();
        //4
        Assert(groupManagementPage.isGroupPage(), "User will be navigated the Group Information screen of the User Group.");

        groupManagementPage.refreshGroupMembershipCriteria();
        addToStorage("user_count1", groupManagementPage.getUserCount());
        //5
        Assert(groupManagementPage.getUserCount().matches(RegularExpression.NUMBER), "Group Information screen of the user group will be displayed with the user count.");
        groupManagementPage.openGroupUsers();
        //6
        Assert(getFromStorage("user_count1").matches(groupManagementPage.getUserCountInGroupUsers()), "Users meeting either of the values of the criteria" +
                " are added to the user group.");

        groupManagementPage.closeGroupUsersOverlay().openEditGroupCriteria();
        //7
        Assert(editGroupCriteriaPage.getMatchConditionsState() == false &&
                editGroupCriteriaPage.getOperatorForCriteria(getData("GENERIC.CRITERIA_ALPHANUMERIC_CUSTOM_FIELD")).
                        get(0).matches(getData("GENERIC.OPERATOR_NOT_CONTAIN")) &&
                editGroupCriteriaPage.
                        getRelationshipForCriteria(getData("GENERIC.CRITERIA_ALPHANUMERIC_CUSTOM_FIELD")).get(0).
                        matches(getData("GENERIC.RELATIONSHIP_OR")), "An \"or\"  relationship is displayed for the chips of the 'alphanumeric custom field' " +
                "and 'does not contain' operator criteria super-chip for 'Match All Conditions' unchecked.");
    }

}