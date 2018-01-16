package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class ClassesPageRepo extends UiBase {

	protected BrowserElement trainingNamelnk(String trainingName) {
		return findByLinkText(trainingName);
	}
	protected BrowserElement onlineRegistration() {
		return findByXpath("//tr[td='Online Registration:']/td[2]");
	}
	protected BrowserElement classCode() {
		return findByXpath("//tr[td='Class Code:']/td[2]");
		
	}
	protected BrowserElement classTitle() {
		return findByXpath("//tr[td='Class Title:']/td[2]");
		
	}
	protected BrowserElement rosterLnk() {
		return findByCssSelector("a[title='View Roster']");
		
	}
}
