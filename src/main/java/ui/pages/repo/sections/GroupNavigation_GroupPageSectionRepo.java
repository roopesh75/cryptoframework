package ui.pages.repo.sections;

import ui.BrowserElement;
import ui.UiBase;

public class GroupNavigation_GroupPageSectionRepo extends UiBase {

    protected BrowserElement editGroupCriteriaLnk() {
        return findByLinkText("Edit Group Criteria");
    }

    protected BrowserElement groupMembershipCriteriaLnk() {
        return findByLinkText("Group Membership Criteria");
    }

    protected BrowserElement groupUsersLnk() {
        return findByLinkText("Group Users");
    }

	protected BrowserElement addUsersDirectlyLnk() {
		return findByLinkText("Add Users directly");
	}

	protected BrowserElement excludeUsersDirectlyLnk() {
		return findByLinkText("Exclude Users");
	}
	protected BrowserElement grpMembershipHstryLnk() {
		return findByLinkText("Group Membership History");
	}


}
