package ui.pages.repo.sections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ui.BrowserElement;
import ui.BrowserElement;
import ui.UiBase;

public class SettingsNav_HomePageSectionRepo extends UiBase {

    //@FindBy(linkText="Tools")
    protected BrowserElement toolslnk() {
    	return findByLinkText("Tools");
    	}
    protected BrowserElement logoutlnk() {
        return findByCssSelector("ul[class='nav navbar-nav navbar-right']").findById("btn-logout");
    }

    protected BrowserElement userProfilelnk() {
        return findByCssSelector("ul[class='nav navbar-nav navbar-right']").findById("link-user");
    }
}
