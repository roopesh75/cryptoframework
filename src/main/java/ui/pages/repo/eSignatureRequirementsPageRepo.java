package ui.pages.repo;

import org.openqa.selenium.By;

import ui.BrowserElement;
import ui.BrowserElementImpl;
import ui.UiBase;

public class eSignatureRequirementsPageRepo extends UiBase {

	protected BrowserElement editRequirementslnk() {
		return findByLinkText("Edit Requirements");
	}
	protected BrowserElement reqEsigPasswordPolicieschkbox() {
		return findByName("PP");
	}
	protected BrowserElement reqEsigCustomExamCompletionschkbox() {
		return findByName("SAQuiz");
	}
	protected BrowserElement reqEsigCtrlDocchkbox() {
		return findByName("CD");
	}
	protected BrowserElement reqEsigCurrViatechkbox() {
		return findByName("CurrVitae");
	}
	protected BrowserElement reqEsigStudentOnlineRegchkbox() {
		
		return findByName("STUDENTONLINEREG");
	}
	
	protected BrowserElement saveChangeslnk() {
		return findByLinkText("Save Changes");
	}

	protected BrowserElement electronicSignature(String userName) {
		return findByXpath("//*[contains(text(),'"+userName+"')]");
	}
	protected BrowserElement returnlnk() {
		return findByLinkText("Return");
	}
	
	protected BrowserElement esignatureRequirementstxt(int index) {
		return new BrowserElementImpl(getDriver().findElements(By.className("EduText")).get(index),getDriver().getWebDriver());
		//input[@name='PP']/following::td[2]
	}
	
}
