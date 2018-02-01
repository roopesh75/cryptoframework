package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class SetupOptions_OptionsPageSectionRepo extends UiBase {
	// @FindBy(linkText="e-Signature Requirements")
	protected BrowserElement esignatureRequirementslnk() {
		return findByLinkText("e-Signature Requirements");
	}

	protected BrowserElement classRoomOptionslnk() {
		return findByLinkText("Classroom Options / Company Preferences");
	}

	// Classroom Options / Company Preferences
	protected BrowserElement passwordPolicieslnk() {
		return findByLinkText("Password Policies");
	}
	protected BrowserElement organizationNodeManagementlnk() {
		return findByLinkText("Organization Node Management");
	}
	
}
