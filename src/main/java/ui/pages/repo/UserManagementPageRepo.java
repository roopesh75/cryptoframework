package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;
import ui.pages.actions.UserManagementPage;
import ui.utils.browser.Browser;

public class UserManagementPageRepo extends UiBase{

	protected BrowserElement documentNameTxtBox(){
		return findByName("DocumentName");
	}
	protected BrowserElement addDocumentLnk(){
		return findByLinkText("Add a Document");
	}
	
	protected BrowserElement documentPathTxtBox(){
		return findByName("Path");
	}
	protected BrowserElement testLinkLnk(){
		return findByLinkText("Test Link");
	}
	
	protected BrowserElement PRINTTableBorderTxt(){
		return findByClass("PRINTTableBorder");
	}
	protected BrowserElement reportTitleTxt(){
		return findById("ReportTitle");
	}
	protected BrowserElement continueLnk(){
		return findByLinkText("Continue");
	}

	protected BrowserElement roleIdSltBox(){
		return findByName("RoleID");
	}

}
