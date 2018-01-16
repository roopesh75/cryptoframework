package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;
import ui.utils.browser.Browser;

public class UserManagementPageRepo extends UiBase{
	
	protected BrowserElement userSecurityRoleTable(){
		return findByClass("PRINTTableBorder");
	}

}
