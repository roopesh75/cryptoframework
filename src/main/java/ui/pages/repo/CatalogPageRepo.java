/**
 * 
 */
package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

/**
 * @author ranekere
 *
 */
public class CatalogPageRepo extends UiBase {

	protected BrowserElement searchCatalogtxt() {
		return findByName("SearchGeneral");
	}
	protected BrowserElement searchbtn() {
		return findById("searchButton");
	}
	
	protected BrowserElement trainingitemlnk(String trainingName) {
		return findByLinkText(trainingName);
	}
	protected BrowserElement userIDtxt() {
		return findByName("ViewModel.CheckSignatureIDRequest.UserName");
	}
	protected BrowserElement pwdtxt() {
		return findByName("ViewModel.CheckSignatureIDRequest.Password");
	}
 
    protected BrowserElement electronicallySignlnk() {
		return findByPartialLinkText("Electronically Sign");
	}
	
	protected BrowserElement classInformationlnk() {
		return findByLinkText("Class Information");
	}
	protected BrowserElement classCodelnk(String classCode) {
		return findByLinkText(classCode);
	}
	protected BrowserElement registerForClasslnk() {
		return findByLinkText("Register for Class");
	}
	protected BrowserElement launchlnk() {
		return findByXpath("//button[@class='btn btn-default btnLaunch']");
	}
	protected BrowserElement continuebtn() {
		return findByXpath("//button[@title='Continue']");
	}
	protected BrowserElement trueOption() {
		return findByXpath("//div[@class='info-box-content answer-text bg-gray']");
	}
	protected BrowserElement ceContinuebtn() {
		return findByXpath("//button[@id='btn-goto-todo']");
	}
	protected BrowserElement catalogPageContent() {
		return findByTagName("body");
	}
}
