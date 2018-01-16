package ui.pages.repo.overlays;

import org.openqa.selenium.By;
import ui.BrowserElement;
import ui.UiBase;

public class Esignature_DefinePasswordPoliciesPageOverlayRepo extends UiBase {
    protected BrowserElement eSignature() {
        return findByClass("EDUBody");
    }

    protected BrowserElement signatureUserNameBox() {
        return (BrowserElement) getDriver().findById("SignatureUSERID");
    }
    protected BrowserElement signaturePasswordBox() {
        return (BrowserElement) getDriver().findById("SignaturePassword");
    }
    protected BrowserElement electronicallySignlnk() {
        return (BrowserElement) getDriver().findByLinkText("Electronically Sign");
       //return (BrowserElement) getDriver().findByXpath("//a[text()='Electronically Sign']");
    }
    protected BrowserElement signatureReasondrp() {
        return findByName("SignatureReasonID");
    }

}
