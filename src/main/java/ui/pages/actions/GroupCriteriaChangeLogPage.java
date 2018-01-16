package ui.pages.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.BrowserDriver;
import ui.pages.repo.GroupCriteriaChangeLogPageRepo;

public class GroupCriteriaChangeLogPage extends GroupCriteriaChangeLogPageRepo{

    BrowserDriver driver;

    public GroupCriteriaChangeLogPage(BrowserDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }


}
