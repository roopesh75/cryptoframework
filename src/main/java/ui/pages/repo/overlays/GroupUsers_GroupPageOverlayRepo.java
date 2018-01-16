package ui.pages.repo.overlays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ui.BrowserElement;
import ui.BrowserElementImpl;
import ui.UiBase;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vevinmoza on 9/28/17.
 */
public class GroupUsers_GroupPageOverlayRepo extends UiBase {

   // @FindBy(css="a[title='Close']")
    protected BrowserElement toolslnk() {
    	return findByCssSelector("a[title='Close']");
    }

   // @FindBy(id="tblUSERS")
    protected BrowserElement usersTable() {
    	return findById("tblUSERS");
    }


    protected List<BrowserElement> usersInGroup(){
        return getDriver().findElements(By.tagName("tr")).stream().map(e-> new BrowserElementImpl(e)).collect(Collectors.toList());
    }


    protected BrowserElement groupUsersCloselnk(){
        return findById("fauxPopupTitleClose").findElement(By.tagName("a"));
    }

}
