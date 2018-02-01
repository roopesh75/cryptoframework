package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class ViewCurriculumPageRepo extends UiBase {
	
	protected BrowserElement curriculumTitle(){
		return findByXpath("//*[span='Curriculum Title']/div[1]");
	}
	protected BrowserElement currListSltBox(){
		return findById("RelationshipSearch");
	}
	protected BrowserElement addRelationshipBtn(){
		return findById("AddRelationshipButton1");
	}
	protected BrowserElement addRelationshipBtn2(){
		return findById("AddRelationshipButton2");
	}
	protected BrowserElement viewCurriculumPageContent() {
		return findByTagName("body");
	}
	protected BrowserElement currInfoContent() {
		return findById("curriculaEdit");
	}
	protected BrowserElement toggleLnk() {
		return findByCssSelector("a[class='btn dropdown-toggle']");
	}
	protected BrowserElement addSubCurriculumLnk() {
		return findByPartialLinkText("Add Sub-curriculum");
	}
	// 
	protected BrowserElement relationshipsLnk() {
		return findByLinkText("Relationships");
	}
	
	protected BrowserElement viewReports() {
		return findByLinkText("Reports");
	}
	protected BrowserElement trainingItemsLnk() {
		return findByLinkText("Training Items");
	}
	//Manage Status
	protected BrowserElement manageStatusLnk() {
		return findByLinkText("Manage Status");
	}
	protected BrowserElement manageStatusApprovalDate() {
		return findByName("ManageStatus.ApprovalDate");
	}
	protected BrowserElement manageStatusEffectiveDate() {
		return findByName("ManageStatus.EffectiveDate");
	}
	protected BrowserElement setStatusBtn() {
		return findById("SetStatusButton");
	}
	protected BrowserElement curriculumConfirmBtn() {
		return findById("ConfirmButton");
	}
	protected BrowserElement curriculumStatus() {
		return findByXpath("//span[text()='Status']/following::span[1]");
	}
	
	
}
