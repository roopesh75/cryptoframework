package ui.pages.actions.sections;

import ui.BrowserDriver;
import ui.pages.actions.HomePage;
import ui.pages.repo.sections.SettingsNav_HomePageSectionRepo;

public class HomePageSection_SettingsNav extends SettingsNav_HomePageSectionRepo {
	BrowserDriver driver;

    public HomePageSection_SettingsNav(BrowserDriver driver) {
        this.driver = driver;
        setDriver(driver);

    }
    public HomePage openToolsMenu(){
        waitForAjax("waiting for tools to appear");
        toolslnk().click();
        return new HomePage(driver);
    }
    public HomePage logOut(){
        waitForAjax("waiting for logout to appear");
        logoutlnk().click();
        return new HomePage(driver);
    }

    public HomePage openUserProfile(){
        waitForAjax("waiting for user profile to appear");
        userProfilelnk().click();
        return new HomePage(driver);
    }
    
    public boolean isHomePage(){
        waitForAjax("waiting for user profile to appear");
        return userProfilelnk().isDisplayed();
    }


}
