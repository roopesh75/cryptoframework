package ui.pages.repo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ui.BrowserElement;
import ui.UiBase;

public class SecuritySettingsPageRepo extends UiBase {
	protected BrowserElement assignSecurityRoleLnk() {
		return findByLinkText("Assign Security Role");
	}
	protected BrowserElement editSecurityRoleLnk() {
		return findByLinkText("Edit Security Role");
	}
	protected BrowserElement removeSecurityRoleLnk() {
		return findByLinkText("Remove Security Role");
	}
	protected BrowserElement continueOrgLnk() {
		return findByLinkText("Continue");
	}

	protected BrowserElement userIdTxtBox() {
		return findById("searchByUsername_txtUserName");
	}

	protected BrowserElement searchUsrLnk() {
		return findById("lnkSearch");
	}

	protected BrowserElement securityRoleOverride() {
		return findById("chkOverride");
	}

	protected BrowserElement UsrChkBox(String userName) {
		return findByXpath("//table[@class='PRINTTableBorder']//td[text()='"+userName+"']/preceding::input[1]");
	}
	protected BrowserElement securityRoleBox() {
		return findById("txtSecurityRoleName");
	}
	protected BrowserElement viewUsersChkBox() {
		return findById("Privilege23");
	}
	protected BrowserElement viewUsersLnk() {
		return findByClass("CWBox").findByLinkText("Users");
	}

	protected BrowserElement defineEditSecurityRoleChkBox() {
		return findById("Privilege21");
	}
	////
	protected BrowserElement viewTrainingItemsRostersChkBox() {
		return findById("Privilege24");
	}
	
	protected BrowserElement viewCurriculumChkBox() {
		return findById("Privilege51");
	}
	protected BrowserElement manageCurriculumChkBox() {
		return findById("Privilege19");
	}
	protected BrowserElement viewCompletionChkBox() {
		return findById("Privilege32");
	}
	protected BrowserElement viewAssignmentsByTrainingChkBox() {
		return findById("Privilege33");
	}
	protected BrowserElement createAssignmentChkBox() {
		return findById("Privilege16");
	}
	protected BrowserElement createCompletionChkBox() {
		return findById("Privilege14");
	}
	protected BrowserElement manageQuickReportChkBox() {
		return findById("Privilege49");
	}

	protected BrowserElement addLnk() {
		return findByLinkText("Add");
	}
	protected BrowserElement saveLnk() {
		return findById("lnkBottomSave");
	}
	protected BrowserElement returnlnk() {
		return findByLinkText("Return");
	}
	
	protected List<WebElement> permissionChkBoxes(){
		return getDriver().getWebDriver().findElements(By.cssSelector("input[type='checkbox']"));
	}
	protected BrowserElement applySecurityRoleLnk(){
		
		return findByXpath("//*[@id='popupButtonBar']/table/tbody/tr/td/a");
	}
	protected BrowserElement securityRoleChosenText(){
		return findById("lblSecurityRoleName");
	}
	//
}
