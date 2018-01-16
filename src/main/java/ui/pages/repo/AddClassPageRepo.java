package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class AddClassPageRepo extends UiBase {

	protected BrowserElement classTitleTxtbox() {
		return findByName("class_title");
	}
	protected BrowserElement startDate() {
		return findByName("Start_Date");
	}
	
	protected BrowserElement endDate() {
		return findByName("end_Date");
	}
	protected BrowserElement endTime() {
		return findByName("end_time1");
	}
	protected BrowserElement saveClass() {
		return findByLinkText(" Save Class");
	}
	protected BrowserElement timeZonedrp() {
		return findByName("time_zone");
	}
	protected BrowserElement onlineSelfRegndrp() {
		return findByName("OnlineReg");
	}
	
	
	
}
