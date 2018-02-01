package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class ChangePasswordPageRepo extends UiBase {

	 protected BrowserElement currentPwdtxt() {
	        return findByName("ViewModel.ResetUserLoginPasswordCommand.CurrentPassword");
	    }
	 protected BrowserElement newPwdtxt() {
	        return findByName("ViewModel.ResetUserLoginPasswordCommand.NewPassword");
	    }
	 protected BrowserElement confirmNewPwdtxt() {
	        return findByName("ViewModel.ResetUserLoginPasswordCommand.ConfirmNewPassword");
	    }
	 protected BrowserElement alertMessage() {
	        return findByClass("alert-error");
	    }
	 protected BrowserElement submitbtn() {
	        return findByXpath("//button[@type='submit']");
	    }
	
	 protected BrowserElement successModalDialog() {
	        return findByXpath("//div[@class='modal-content']");
	    }
	 protected BrowserElement continuebtn() {
	        return findByXpath("//button[text()='Continue']");
	    }
	 
	 protected BrowserElement olToolslnk() {
	    	return findByPartialLinkText("उपकरण");
	    	}
	
}
