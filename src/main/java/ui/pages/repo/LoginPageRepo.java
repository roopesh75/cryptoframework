package ui.pages.repo;


import org.openqa.selenium.By;


import org.openqa.selenium.WebDriver;
import ui.BrowserDriverImpl;
import ui.BrowserElement;
import ui.UiBase;
import ui.*;

import java.util.List;

/**
 * Created by vevinmoza on 9/27/17.
 */
public class LoginPageRepo extends UiBase {

    protected BrowserElement userNameBox() {
        return (BrowserElement) getDriver().findElement(By.id("UserID"));
    }

    protected BrowserElement passwordBox() {
        return findById("Password");
    }

    protected BrowserElement companyNameBox() {
        return findById("CompanyCode");
    }

    protected BrowserElement acceptBtn() {
        return findById("btnLogin");
    }


}
