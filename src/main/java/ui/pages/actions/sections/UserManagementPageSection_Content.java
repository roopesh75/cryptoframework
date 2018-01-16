package ui.pages.actions.sections;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.repo.sections.Content_UserManagementPageSectionRepo;

public class UserManagementPageSection_Content extends Content_UserManagementPageSectionRepo {
	BrowserDriver driver;

	public UserManagementPageSection_Content(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public String getFirstName() {
		recordScreenIframeSwitch();
		return firstName().getText();
		
	}
	public String getLastName() {
		recordScreenIframeSwitch();
		return lastName().getText();
		// TODO Auto-generated method stub
		
	}
	public String getUserId() {
		recordScreenIframeSwitch();
		return userId().getText();
	}

	public String getSecurityRole() {
		// TODO Auto-generated method stub
		recordScreenIframeSwitch();
		return securityRole().getText();
	}
	public String getHomeOrganization() {
		// TODO Auto-generated method stub
		recordScreenIframeSwitch();
		return homeOrganization().getText();
	}
	public String getHomePhone() {
		recordScreenIframeSwitch();
		return homePhone().getText();
	}
	
}
