package ui.pages.actions;

import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.actions.overlays.Esignature_DefinePasswordPoliciesPageOverlay;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.repo.DefinePasswordPoliciesPageRepo;

public class DefinePasswordPoliciesPage extends DefinePasswordPoliciesPageRepo implements IESigOverlay {

	BrowserDriver driver;
	Esignature_DefinePasswordPoliciesPageOverlay esignatureDefinePasswordPoliciesPageOverlay;
	AdminNav_CommonSection adminNav_CommonSection;

	public DefinePasswordPoliciesPage(BrowserDriver driver) {
		this.driver = driver;
		esignatureDefinePasswordPoliciesPageOverlay = new Esignature_DefinePasswordPoliciesPageOverlay(driver);
		adminNav_CommonSection = new AdminNav_CommonSection(driver);
		setDriver(driver);
	}

	public boolean isDefinePasswordPoliciesPage() {
		recordScreenIframeSwitch();
		return editPasswordPolicieslnk().isDisplayed();
	}

	public DefinePasswordPoliciesPage openEditPasswordPolicies() {
		recordScreenIframeSwitch();
		editPasswordPolicieslnk().click();
		return this;
	}

	public DefinePasswordPoliciesPage setMinPwdLength(String value) {
		recordScreenIframeSwitch();
		new Select(minLengthdrp()).selectByValue(value);
		return this;
	}
	public DefinePasswordPoliciesPage setMaxPwdLength(String value) {
		recordScreenIframeSwitch();
		new Select(maxLengthdrp()).selectByValue(value);
		return this;
	}
	public DefinePasswordPoliciesPage saveChanges() {
		recordScreenIframeSwitch();
		saveChangeslnk().click();
		switchToDefaultFrame();
		return this;
	}

	public String getEsignatureOverlay(String frameName) {
		return esignatureDefinePasswordPoliciesPageOverlay.getEsignatureOverlay(frameName);
	}

	
	public IESigOverlay electonicallySignIn(String...parameters) {
		return esignatureDefinePasswordPoliciesPageOverlay.electronicallySignIn(parameters[0],parameters[1],parameters[2],parameters[3]);
	}

	public String getPwdBoxAttribute() {
		popUpIframeViaRecordScreenIframeSwitch();
		return esignatureDefinePasswordPoliciesPageOverlay.getPwdBoxAttribute();
	}

	public boolean isEditPasswordPoliciesPage() {
		recordScreenIframeSwitch();
		return saveChangeslnk().isDisplayed();
		}

	public DefinePasswordPoliciesPage setPasswordComplexity() {
		recordScreenIframeSwitch();
		if(!reqPwdContainLetterschk().isSelected())	reqPwdContainLetterschk().click();
		if(!reqPwdUppercaseLetterschk().isSelected()) reqPwdUppercaseLetterschk().click();
		if(!reqPwdContainNumberschk().isSelected())reqPwdContainNumberschk().click();
		return this;
	}

	public DefinePasswordPoliciesPage unsetPasswordComplexity() {
		recordScreenIframeSwitch();
		if(reqPwdContainLetterschk().isSelected())	reqPwdContainLetterschk().click();
		if(reqPwdUppercaseLetterschk().isSelected()) reqPwdUppercaseLetterschk().click();
		if(reqPwdContainNumberschk().isSelected()) reqPwdContainNumberschk().click();
		return this;
	}
	public String getPasswordComplexity() {
		recordScreenIframeSwitch();
		return passwordComplexitytbl().getAttribute("innerText");
		}
	public String getPasswordLengths() {
		recordScreenIframeSwitch();
		return passwordLengthstbl().getText();
		}
	public HomePage knowledgeCenter() {
		adminNav_CommonSection.openKnowledgeCenter();
		return new HomePage(driver);
	}

	public DefinePasswordPoliciesPage setReusePwdPolicy(String pwdReuseAttempts) {
		recordScreenIframeSwitch();
		boolean state =lockUserradio().isSelected();
		if (!state) {
		lockUserradio().click();
		new Select(lockPwdNumbedrp()).selectByValue(pwdReuseAttempts);
		}
		return this;
	}

	public String getReusePwdPolicy() {
		recordScreenIframeSwitch();
		return changePasswordtbl().getAttribute("innerText");
	}

	public DefinePasswordPoliciesPage allowOlderPassword() {
		recordScreenIframeSwitch();
		boolean state =allowOlderPwdradio().isSelected();
		if (!state) allowOlderPwdradio().click();
		return this;
	}
}
