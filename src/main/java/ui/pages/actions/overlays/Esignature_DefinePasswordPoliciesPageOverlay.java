package ui.pages.actions.overlays;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import ui.BrowserDriver;
import ui.pages.actions.DefinePasswordPoliciesPage;
import ui.pages.actions.IESigOverlay;
import ui.pages.repo.overlays.Esignature_DefinePasswordPoliciesPageOverlayRepo;

public class Esignature_DefinePasswordPoliciesPageOverlay extends Esignature_DefinePasswordPoliciesPageOverlayRepo {
	BrowserDriver driver;

	public Esignature_DefinePasswordPoliciesPageOverlay(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public String getEsignatureOverlay(String frameName) {
		if (frameName.equalsIgnoreCase("RecScr"))
			popUpIframeViaRecordScreenIframeSwitch();
		else if (frameName.equalsIgnoreCase("frameId"))
			frameIdIframeSwitch();
		return eSignature().getText();
	}

	public IESigOverlay electronicallySignIn(String... parameters) {
		if (parameters[3].equalsIgnoreCase("RecScr"))
			popUpIframeViaRecordScreenIframeSwitch();
		else if (parameters[3].equalsIgnoreCase("frameId"))
			frameIdIframeSwitch();
		// staticWait(1000);
		signatureUserNameBox().sendKeys(parameters[0]);
		signaturePasswordBox().clear();
		signaturePasswordBox().sendKeys(parameters[1]);
		new Select(signatureReasondrp()).selectByVisibleText(parameters[2]);
		if (parameters[3].equalsIgnoreCase("RecScr"))
			recordScreenIframeSwitch();
		electronicallySignlnk().click();
		waitForAjax("clicked on Electronically Sign");
		return new DefinePasswordPoliciesPage(driver);
	}

	public String getPwdBoxAttribute() {

		signaturePasswordBox().sendKeys("1234");
		return signaturePasswordBox().getAttribute("type");

	}
}
