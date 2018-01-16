package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.repo.ChangePasswordPageRepo;

public class ChangePasswordPage extends ChangePasswordPageRepo {

	BrowserDriver driver;
	
	public ChangePasswordPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}

	public ChangePasswordPage changePassword(String currentPwd,String newPwd,String confirmNewPwd) {
		currentPwdtxt().sendKeys(currentPwd);
		newPwdtxt().sendKeys(newPwd);
		confirmNewPwdtxt().sendKeys(confirmNewPwd);
		submitbtn().click();
		waitForAjax("clicked on SubmitBtn");
		return this;
	}
	public String getAlertMessage() {
		waitForAjax("Failure message");
		return alertMessage().getAttribute("innerText");
	}
	
	public String getSuccessMessage() {
		waitForAjax("Success message");
		return successModalDialog().getAttribute("innerText");
		
	}
	
	public HomePage continuePwd() {
		continuebtn().click();
		waitForAjax("Success message");
		return new HomePage(driver);
	}
	
	 public boolean isPwdPoliciesPage() {
	        return currentPwdtxt().isDisplayed();
	    }
}
