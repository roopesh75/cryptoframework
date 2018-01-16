package ui.pages.actions.overlays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.BrowserDriver;
import ui.pages.actions.GroupManagementPage;
import ui.pages.repo.overlays.GroupUsers_GroupPageOverlayRepo;

/**
 * Created by vevinmoza on 9/28/17.
 */
public class GroupUsers_GroupPageOverlay extends GroupUsers_GroupPageOverlayRepo {
    BrowserDriver driver;

    public GroupUsers_GroupPageOverlay(BrowserDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public GroupManagementPage closeGroupOverlay() {
        recordScreenIframeSwitch();
        groupUsersCloselnk().click();
        switchToDefaultFrame();
        return new GroupManagementPage(driver);
    }

    public GroupUsers_GroupPageOverlay getUsers() {
        //fill in users code
        return this;
    }


    public boolean hasGroupUsersOverlay() {
       popUpIframeViaRecordScreenIframeSwitch();
        boolean state = usersTable().getText().indexOf("User Id") >= 0;
        switchToDefaultFrame();
        return state;
    }

    public String getUserCountInGroupUsers() {
       popUpIframeViaRecordScreenIframeSwitch();
        String usercount = usersInGroup().size() - 1 + "";
        switchToDefaultFrame();
        return usercount;
    }
}
