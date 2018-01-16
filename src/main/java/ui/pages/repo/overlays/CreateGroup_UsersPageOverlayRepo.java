package ui.pages.repo.overlays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ui.BrowserElement;
import ui.UiBase;

public class CreateGroup_UsersPageOverlayRepo extends UiBase {

   // @FindBy(linkText="Continue")
    protected BrowserElement continuelnk() {
    	return findByLinkText("Continue");
    }

  //  @FindBy(xpath="//span[contains(text(), 'General')]")
    protected BrowserElement generallnk() {
    	return findByXpath("//span[contains(text(), 'General')]");
    }

   // @FindBy(id="GroupName")
    protected BrowserElement groupNameBox() {
    	return findById("GroupName");
    }

}
