package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.repo.CurriculumReportPageRepo;

public class CurriculumReportPage extends CurriculumReportPageRepo {

	BrowserDriver driver;

	public CurriculumReportPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	} 
	public AddCurriculumPage openAddCurriculum() {
		recordScreenIframeSwitch();
		addCurriculumlnk().click();
		return new AddCurriculumPage(driver);
	}
	public boolean isCurriculumReportPage() {
		recordScreenIframeSwitch();
		return addCurriculumlnk().isDisplayed();
	}
	public ViewCurriculumPage chooseCurriculum(String curriculum) {
		recordScreenIframeSwitch();
		findByLinkText(curriculum).click();
		return new ViewCurriculumPage(driver);
	}
	public String getPRINTTableBorder() {
		recordScreenIframeSwitch();
		return printTableBorderTxt().getText();
	}
	
	
}
