package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class ToDoPageRepo extends UiBase {

	protected BrowserElement trainingItem(String trainingName) {
		return findByXpath("//td[text()='"+trainingName+"']");
	}
	protected BrowserElement classInformation() {
		return findByLinkText("Class Information");
	}
	protected BrowserElement toDoPagecontent() {
		return findByTagName("body");
	}
	protected BrowserElement userIDtxt() {
		return findByName("ViewModel.CheckSignatureIDRequest.UserName");
	}
	protected BrowserElement pwdtxt() {
		return findByName("ViewModel.CheckSignatureIDRequest.Password");
	}
 
    protected BrowserElement electronicallySignlnk() {
		return findByPartialLinkText("Electronically Sign");
	}
	protected BrowserElement ceContinuebtn() {
		return findById("redirect-launchpage");
	}
	protected BrowserElement trainingsPendingTxt() {
		return findByClass("panel-body");
	}
	
}
