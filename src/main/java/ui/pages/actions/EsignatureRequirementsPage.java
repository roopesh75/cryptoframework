package ui.pages.actions;

import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.overlays.Esignature_DefinePasswordPoliciesPageOverlay;
import ui.pages.repo.eSignatureRequirementsPageRepo;

public class EsignatureRequirementsPage extends eSignatureRequirementsPageRepo implements IESigOverlay {

	BrowserDriver driver;
	Esignature_DefinePasswordPoliciesPageOverlay esignatureEsignatureaRequirementsPageOverlay;

	public EsignatureRequirementsPage(BrowserDriver driver) {
		this.driver = driver;
		esignatureEsignatureaRequirementsPageOverlay = new Esignature_DefinePasswordPoliciesPageOverlay(driver);

		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isEsignautreRequirementsPage() {
		recordScreenIframeSwitch();
		return editRequirementslnk().isDisplayed();
	}

	public EsignatureRequirementsPage openEditRequirements() {
		recordScreenIframeSwitch();
		// staticWait(1000);
		editRequirementslnk().click();
		return this;
	}

	public EsignatureRequirementsPage checkReqEsigPasswordPolicies() {
		recordScreenIframeSwitch();
		boolean state = reqEsigPasswordPolicieschkbox().isSelected();
		if (!state)
			reqEsigPasswordPolicieschkbox().click();
		return this;
	}

	public EsignatureRequirementsPage checkReqEsigStudOnlineRegn() {
		recordScreenIframeSwitch();
		boolean state = reqEsigStudentOnlineRegchkbox().isSelected();
		if (!state)
			reqEsigStudentOnlineRegchkbox().click();

		return this;
	}

	public EsignatureRequirementsPage unCheckReqEsigStudOnlineRegn(String... parameters) {
		recordScreenIframeSwitch();
		boolean state = reqEsigStudentOnlineRegchkbox().isSelected();
		if (state) {
			reqEsigStudentOnlineRegchkbox().click();
			saveChangeslnk().click();
			electronicallySignIn(parameters[0], parameters[1], parameters[2], parameters[3]);
		} else
			returnlnk().click();
		return this;

	}

	public EsignatureRequirementsPage uncheckReqEsigPasswordPolicies(String... parameters) {
		recordScreenIframeSwitch();
		boolean state = reqEsigPasswordPolicieschkbox().isSelected();
		if (state) {
			reqEsigPasswordPolicieschkbox().click();
			saveChangeslnk().click();
			electronicallySignIn(parameters[0], parameters[1], parameters[2], parameters[3]);
		} else
			returnlnk().click();
		return this;

	}

	/*
	 * public EsignatureRequirementsPage unCheckReqEsigPasswordPolicies() {
	 * switchToDefaultFrame(); switchToFrame(RECORD_SCREEN_IFRAME); boolean
	 * state = reqEsigPasswordPolicieschkbox().isSelected(); if (state)
	 * reqEsigPasswordPolicieschkbox().click(); return this; }
	 */

	public EsignatureRequirementsPage saveChanges() {
		recordScreenIframeSwitch();
		saveChangeslnk().click();
		return this;
	}

	public String getEsignatureOverlay(String frameName) {
		return esignatureEsignatureaRequirementsPageOverlay.getEsignatureOverlay(frameName);
	}

	public IESigOverlay electronicallySignIn(String... parameters) {
		return esignatureEsignatureaRequirementsPageOverlay.electronicallySignIn(parameters[0], parameters[1],
				parameters[2], parameters[3]);
	}

	public String getPwdBoxAttribute() {
		popUpIframeViaRecordScreenIframeSwitch();
		return esignatureEsignatureaRequirementsPageOverlay.getPwdBoxAttribute();
	}

	public String getEsignature(String userName) {
		recordScreenIframeSwitch();
		return electronicSignature(userName).getAttribute("innerText");

	}

	public OptionsPage returnOptionsPage() {
		recordScreenIframeSwitch();
		returnlnk().click();
		return new OptionsPage(driver);
	}

	public String getEsignatureRequirements(int index) {
		recordScreenIframeSwitch();
		
		return esignatureRequirementstxt(index).getAttribute("innerHTML");
	}

	public EsignatureRequirementsPage checkReqEsigCustomExamComp() {
		recordScreenIframeSwitch();
		boolean state = reqEsigCustomExamCompletionschkbox().isSelected();
		if (!state)
			reqEsigCustomExamCompletionschkbox().click();
		return this;
	}

	public EsignatureRequirementsPage uncheckReqEsigCustomExamComp(String... parameters) {
		recordScreenIframeSwitch();
		boolean state = reqEsigCustomExamCompletionschkbox().isSelected();
		if (state) {
			reqEsigCustomExamCompletionschkbox().click();
			saveChangeslnk().click();
			electronicallySignIn(parameters[0], parameters[1], parameters[2], parameters[3]);
		} else
			returnlnk().click();
		return this;

	}

	public EsignatureRequirementsPage checkReqEsigCtrlDoc() {
		recordScreenIframeSwitch();
		boolean state = reqEsigCtrlDocchkbox().isSelected();
		if (!state)
			reqEsigCtrlDocchkbox().click();
		return this;
	}

	public EsignatureRequirementsPage uncheckReqEsigCtrlDoc(String... parameters) {
		recordScreenIframeSwitch();
		boolean state = reqEsigCtrlDocchkbox().isSelected();
		if (state) {
			reqEsigCtrlDocchkbox().click();
			saveChangeslnk().click();
			electronicallySignIn(parameters[0], parameters[1], parameters[2], parameters[3]);
		} else
			returnlnk().click();
		return this;

	}

}
