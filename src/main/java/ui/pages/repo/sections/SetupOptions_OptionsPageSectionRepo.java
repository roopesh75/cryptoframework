package ui.pages.repo.sections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ui.BrowserElement;
import ui.UiBase;


public class SetupOptions_OptionsPageSectionRepo extends UiBase {
    // @FindBy(linkText="e-Signature Requirements")
    protected BrowserElement esignatureRequirementslnk() {
        return findByLinkText("e-Signature Requirements");
    }

    protected BrowserElement passwordPolicieslnk() {
        return findByLinkText("Password Policies");
    }
}
