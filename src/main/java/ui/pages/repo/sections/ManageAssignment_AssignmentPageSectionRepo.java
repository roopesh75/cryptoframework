package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class ManageAssignment_AssignmentPageSectionRepo extends UiBase{
	protected BrowserElement addAssignmentDefinitionLnk(){
		return findByLinkText("Add an Assignment Definition");
	}
}
