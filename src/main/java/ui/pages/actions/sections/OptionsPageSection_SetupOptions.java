package ui.pages.actions.sections;

import ui.BrowserDriver;
import ui.pages.actions.DefinePasswordPoliciesPage;
import ui.pages.actions.EsignatureRequirementsPage;
import ui.pages.repo.sections.SetupOptions_OptionsPageSectionRepo;

public class OptionsPageSection_SetupOptions extends SetupOptions_OptionsPageSectionRepo {
	BrowserDriver driver;

	public OptionsPageSection_SetupOptions(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);

	}

	public boolean isSetupOptionsSectionLoaded() {
		recordScreenIframeSwitch();
		return esignatureRequirementslnk().isDisplayed();
		
		
	}

	public DefinePasswordPoliciesPage openPasswordPolicies() {
		recordScreenIframeSwitch();
		passwordPolicieslnk().click();
		return new DefinePasswordPoliciesPage(driver);
	}

	public EsignatureRequirementsPage openEsignatureRequirements() {
		recordScreenIframeSwitch();
		esignatureRequirementslnk().click();
		return new EsignatureRequirementsPage(driver);
	}
}
