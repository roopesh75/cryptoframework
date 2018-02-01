package ui.pages.repo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ui.BrowserElement;
import ui.UiBase;

public class ToDoPageRepo extends UiBase {

	protected BrowserElement trainingItem(String trainingName) {
		return findByXpath("//td[text()='"+trainingName+"']");
	}
	protected BrowserElement toggleLnk() {
		return findByClass("fa-angle-down");
	}
	protected BrowserElement removeLnk() {
		return findByLinkText("Remove");
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
	protected List<WebElement> trainingCodesListLbl() {
		return getDriver().findElements(By.cssSelector("td[class='cell2 iconDesCell ']"));
	}
	protected BrowserElement eSignature(String userName) {
		return findByXpath("//span[contains(text(),'"+userName+"')]");
		
	}
	protected BrowserElement testWebAddresslnk() {
		return findByLinkText("Click Here to open this item in a new window");
	}
	
	protected BrowserElement continueBtn() {
		return findById("btnIacknowledge");
	}
	protected BrowserElement acknowledgeTxtBox() {
		return findByName("IACKNOWLEDGE");
	}
	protected BrowserElement continueLnk() {
		return findByLinkText("Continue");
	}
}
