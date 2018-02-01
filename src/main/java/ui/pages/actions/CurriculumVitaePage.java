package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.actions.overlays.Esignature_DefinePasswordPoliciesPageOverlay;
import ui.pages.repo.CurriculumVitaePageRepo;

public class CurriculumVitaePage extends CurriculumVitaePageRepo {
	BrowserDriver driver;
	Esignature_DefinePasswordPoliciesPageOverlay esignatureCatalogPageOverlay;

	public CurriculumVitaePage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		esignatureCatalogPageOverlay = new Esignature_DefinePasswordPoliciesPageOverlay(driver);
	}
	
	public CurriculumVitaePage addHonours(String... parameters){
		addOnCurculmBtn(6).click();
		titleTxtBox().sendKeys(parameters[0]);
		dateTxtBox().sendKeys(parameters[1]);
		descriptionTxtBox().sendKeys(parameters[2]);
		addBtn().click();
		okBtn().click();
		return this;
	}
	
	public CurriculumVitaePage addPatents(String... parameters){
		addOnCurculmBtn(9).click();
		titleTxtBox().sendKeys(parameters[0]);
		dateTxtBox().sendKeys(parameters[1]);
		descriptionTxtBox().sendKeys(parameters[2]);
		addBtn().click();
		waitForAjax("waiting to adding patent");
		okBtn().click();
		waitForAjax("waiting for overlay to disappear");
		return this;
	}
	
	public CurriculumVitaePage approveCV(){
		scrollTop();
		approveCvLnk().click();
		return this;
	}
	public IESigOverlay electonicallySignIn(String... parameters) {
		return esignatureCatalogPageOverlay.electronicallySignIn(parameters[0], parameters[1], parameters[2],
				parameters[3]);
	}

	public boolean isCurriculumVitaePage() {
		return addOnCurculmBtn(6).isDisplayed();
	}
	public String getCurriculumVitaeInfo() {
		return mainPanelTxt().getText();
	}
	
	public String getDate() {
		return dateLbl().getText();
	}
}
