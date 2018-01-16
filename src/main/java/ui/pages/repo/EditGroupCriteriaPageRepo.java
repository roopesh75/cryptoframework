package ui.pages.repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import ui.BrowserElement;
import ui.BrowserElementImpl;
import ui.UiBase;

import java.util.List;
import java.util.stream.Collectors;

public class EditGroupCriteriaPageRepo extends UiBase {

    protected BrowserElement criteriaDrpDwn() {
        return findById("criteriaField");
    }

    protected BrowserElement linkTreelnk() {
        return findById("linkTreeView");
    }
    protected BrowserElement valueDrp() {
        return findByClass("selectize-dropdown-content");
    }


    protected BrowserElement operatorDrpDwn() {
        return findById("criteriaOperator");
    }

    protected BrowserElement criteriaContextTxt() {
        return findById("allMatch");
    }

    protected BrowserElement continueOrgStructureBtn() {
        return findById("orgTreeContinue");
    }

    protected BrowserElement addBtn() {
        return findById("btnAddCriteria");
    }

    protected BrowserElement saveChangesBtn() {
        return findById("btnEditCriteriaSaveChanges");
    }

    protected BrowserElement criteriaValueBox() {
        return findById("StartDate").isDisplayed() ? findById("StartDate") : findById("criteriavaluefield-selectized");
    }
    protected BrowserElement criteriaValueBoxEndDate() {
        return findById("EndDate");
    }
    protected BrowserElement matchAllConditionFlag() {
        return findById("MatchAllConditionFlag");
    }

    protected List<BrowserElement> operatorsForCriteria(String locatorCriteria) {
        return getDriver().findElements(By.id(locatorCriteria.replaceAll(" ", ""))).stream().
                map(e -> (BrowserElement) e.findElement(By.className("operator"))).collect(Collectors.toList());
    }

    protected List<BrowserElement> relationshipsForCriteria(String locatorCriteria) {
        return findById(locatorCriteria.replaceAll(" ", "")).findElements(By.cssSelector("div[class^='col-sm-1 col-md-1 col-lg-1']")).
                stream().map(e -> (BrowserElement) e).collect(Collectors.toList());
    }
    protected List<BrowserElement> criteraChipDivs() {
        return getDriver().findElements(By.cssSelector("div[class='alert alert-info alert-dismissible super-chip']")).
                stream().map(e -> (BrowserElement) e).collect(Collectors.toList());
    }
    protected BrowserElement criteriaRemoveBtn() {
        return findById("allMatch").findElement(By.className("close"));
    }

    protected List<BrowserElement> operators_alpha_numberic() {
        return getDriver().findElements(By.id("customfieldcriteria")).stream().map(e -> e.findElement(By.className("operator"))
        ).map(e -> new BrowserElementImpl(e, getDriver().getWebDriver())).collect(Collectors.toList());
    }
}
