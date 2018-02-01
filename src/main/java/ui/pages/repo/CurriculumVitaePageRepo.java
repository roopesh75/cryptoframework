package ui.pages.repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ui.BrowserElement;
import ui.UiBase;

public class CurriculumVitaePageRepo extends UiBase {
	protected BrowserElement titleTxtBox() {
		return findById("ViewModel_CvElementSaveRequest_Title1");
	}
	protected BrowserElement approveCvLnk() {
		return findByLinkText("Approve this CV");
	}
	protected BrowserElement dateLbl() {
		return findByClass("panel-label");
	}
	protected BrowserElement mainPanelTxt() {
		return findById("main-panel");
	}
	protected BrowserElement acknowledgeTxtBox() {
		return findById("IACKNOWLEDGE");
	}
	protected BrowserElement continueLnk() {
		return findByLinkText("Continue");
	}
	
	protected BrowserElement dateTxtBox() {
		return findById("StartDate");
	}

	protected BrowserElement okBtn() {
		return findByXpath("//button[text()='OK']");
	}
	
	protected BrowserElement descriptionTxtBox() {
		return findById("ViewModel_CvElementSaveRequest_ElementText");
	}

	protected BrowserElement addBtn() {
		return findById("btnAddCV");
	}
	protected WebElement addOnCurculmBtn(int index) {
		return getDriver().findElements(By.linkText("Add")).get(index);
	}

}
