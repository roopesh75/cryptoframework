package ui.pages.repo.overlays;

import ui.BrowserElement;
import ui.UiBase;

public class UserProfile_HomePageOverlayRepo extends UiBase { 

	protected BrowserElement changePasswordlnk() {
		return findByLinkText("Change Password");
	}
}
