package ui.pages.actions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.repo.EditGroupCriteriaPageRepo;
import ui.utils.Tools;

import java.util.List;
import java.util.stream.Collectors;

public class EditGroupCriteriaPage extends EditGroupCriteriaPageRepo {

    BrowserDriver driver;

    public EditGroupCriteriaPage(BrowserDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public EditGroupCriteriaPage addCriteria(String... parameters) {
        fillPartialCriteriaHelper(parameters[0],parameters[1]);
        criteriaValueBox().sendKeys(parameters[2] + Keys.TAB);
        if(parameters[1].equalsIgnoreCase("between")) {
            criteriaValueBoxEndDate().sendKeys(parameters[3] + Keys.TAB);
        }
        return criteriaSubmit();
    }
    public EditGroupCriteriaPage addCriteriaDomain(String... parameters) {
        fillPartialCriteriaHelper(parameters[0],parameters[1]);
        criteriaValueBox().sendKeys(parameters[2]);
        waitUntilElementDisplayed(valueDrp());
        valueDrp().click();
        return criteriaSubmit();
    }

    private void fillPartialCriteriaHelper(String... parameters){
        criteriaDrpDwn().sendKeys(parameters[0]);
        operatorDrpDwn().sendKeys(parameters[1]);
    }
    private EditGroupCriteriaPage criteriaSubmit(){
        clickJs(scrollToView(addBtn()));
        scrollToView(addBtn()).click();
        waitForAjax("waiting after clicking add on edit criteria");
        return this;
    }
    public EditGroupCriteriaPage selectMatchAllConditions(boolean state) {
        if (state) {
            if (matchAllConditionFlag().isSelected()) {
                return new EditGroupCriteriaPage(driver);
            } else {
                matchAllConditionFlag().click();
                return new EditGroupCriteriaPage(driver);
            }
        } else {
            if (!matchAllConditionFlag().isSelected()) {
                return this;
            } else {
                matchAllConditionFlag().click();
                return this;
            }
        }
    }

    public GroupManagementPage saveChanges() {
        scrollToView(saveChangesBtn()).click();
        waitFor(8);
        //need to be fixed for ajax Jquery is missing on page
        //waitForAjax("waiting for save to complete successfully");
        Tools.runUGM();
        return new GroupManagementPage(driver);
    }

    public EditGroupCriteriaPage removeAllCriteria() {
        int criteriaChips = criteraChipDivs().size();
        while (criteriaChips > 0) {
            criteriaRemoveBtn().click();
            criteriaChips--;
        }

        waitForAjax("waiting for removal of criteria's");
        return this;
    }

    public boolean getMatchConditionsState() {
        return matchAllConditionFlag().isSelected();
    }

    public List<String> getOperatorForCriteria(String criteria) {
        return operatorsForCriteria(criteria).stream().map(e -> e.getText()).collect(Collectors.toList());
    }

    public List<String> getRelationshipForCriteria(String criteria) {
         return relationshipsForCriteria(criteria).stream().map(e -> e.getText()).collect(Collectors.toList());
    }

    public boolean isEditCriteriaPage() {
        return criteriaDrpDwn().isDisplayed();
    }

    public String getCriteriaContent() {
        return criteriaContextTxt().getText().trim();
    }

}
