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
		staticWait(3000);	
		waitUntilElementDisplayed(firstName());
		return firstName().getText();	
	}
	public String getContentSection() {
		recordScreenIframeSwitch();
		return CWBoxLbl().getText();	
	}
	public String getUserGroupName() {
		recordScreenIframeSwitch();
		return groupNameLbl().getText();	
	}
	//
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
	
		recordScreenIframeSwitch();
		return securityRole().getText();
	}
	public String getOrganizationEntity() {
		
		recordScreenIframeSwitch();
		return organizationEntity().getText();
	}
	public String getHomeOrganization() {
	
		recordScreenIframeSwitch();
		return homeOrganization().getText();
	}
	public String getHomePhone() {
		recordScreenIframeSwitch();
		return homePhone().getText();
	}

	public String getOrganization() {
		recordScreenIframeSwitch();
		return organizationLbl().getText();
	}
	
}
