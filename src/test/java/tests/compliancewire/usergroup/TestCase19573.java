package tests.compliancewire.usergroup;

import initializer.BaseTest;
import initializer.DynamicDataProvider;
import initializer.Retry;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.support.Config;
import ui.utils.RegularExpression;

/**
 * Created by vevinmoza on 3/18/16.
 */

public class TestCase19573 extends BaseTest {
    private static final Logger logger = Logger.getLogger(TestCase19573.class);

    @Test(alwaysRun=true,dataProviderClass = DynamicDataProvider.class, dataProvider = "createAdminUser", description = "Users_UserGroup_AND/OR relationship between multiple values of UserId and does not begin with criteria", groups = {"USER_GROUP_CRITERIA"})
    public void Users_UserGroup_AND_OR_relationship_between_multiple_values_of_UserId_and_does_not_begin_with_criteria(String userName) throws Exception {
        loginPage.signIn(userName, getData("GENERIC.PASSWORD"), getData("GENERIC.AUTOMATION.COMPANYCODE")).
                openToolsMenu().
                openUsersPage().
                openCreateNewGroup().
                createNewGroup(getRandomEntityID());
       
        groupManagementPage.openEditGroupCriteria();
       
        editGroupCriteriaPage.addCriteria(getData("GENERIC.CRITERIA_USER_ID"), getData("GENERIC.OPERATOR_NOT_BEGIN_WITH"), getData("TC19573.VALUE_USER_NOT_BEGINS1")).
                addCriteria(getData("GENERIC.CRITERIA_USER_ID"), getData("GENERIC.OPERATOR_NOT_BEGIN_WITH"), getData("TC19573.VALUE_USER_NOT_BEGINS2"));
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
                editGroupCriteriaPage.getOperatorForCriteria(getData("GENERIC.CRITERIA_USER_ID")).
                        get(0).matches(getData("GENERIC.OPERATOR_NOT_BEGIN_WITH")) &&
                editGroupCriteriaPage.
                        getRelationshipForCriteria(getData("GENERIC.CRITERIA_USER_ID")).get(0).
                        matches(getData("GENERIC.RELATIONSHIP_AND")), "An \"and\"  relationship is displayed for the chips of the 'User Id' " +
                "and 'does not begin with' operator criteria super-chip for 'Match All Conditions' checked.");


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
                editGroupCriteriaPage.getOperatorForCriteria(getData("GENERIC.CRITERIA_USER_ID")).
                        get(0).matches(getData("GENERIC.OPERATOR_NOT_BEGIN_WITH")) &&
                editGroupCriteriaPage.
                        getRelationshipForCriteria(getData("GENERIC.CRITERIA_USER_ID")).get(0).
                        matches(getData("GENERIC.RELATIONSHIP_OR")), "An \"or\"  relationship is displayed for the chips of the 'User Id' and 'does not begin with'" +
                " operator criteria super-chip for 'Match All Conditions' unchecked.");
    }


}
