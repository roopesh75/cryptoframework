package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class AssignmentManagementPageRepo extends UiBase{
	
	  protected BrowserElement returnLnk() {
	        return findByLinkText("Return");
	    }
	  protected BrowserElement trainingItem(String trainingName) {
	        return findByXpath("//td[text()=' "+trainingName+"']");
	    }
	  
	  protected BrowserElement returnAssignmentMenuLnk() {
			return findByLinkText("Return to Assignments Menu");
		}
	  protected BrowserElement TableTxt() {
			return findByClass("PRINTTableBorder");
		}
	  
}
