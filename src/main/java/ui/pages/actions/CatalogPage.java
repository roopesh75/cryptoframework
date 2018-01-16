package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.actions.overlays.Esignature_DefinePasswordPoliciesPageOverlay;
import ui.pages.actions.sections.HomePageSection_CurriculumInfoNav;
import ui.pages.repo.CatalogPageRepo;

public class CatalogPage extends CatalogPageRepo implements IESigOverlay {

	BrowserDriver driver;
	Esignature_DefinePasswordPoliciesPageOverlay esignatureCatalogPageOverlay;
	EsignatureRequirementsPage esignatureRequirementsPage;
	HomePageSection_CurriculumInfoNav curriculumInfoNav_HomePageSection;

	public CatalogPage(BrowserDriver driver) {
		this.driver = driver;
		esignatureRequirementsPage = new EsignatureRequirementsPage(driver);
		esignatureCatalogPageOverlay = new Esignature_DefinePasswordPoliciesPageOverlay(driver);
		curriculumInfoNav_HomePageSection = new HomePageSection_CurriculumInfoNav(driver);
		setDriver(driver);
	}

	public CatalogPage searchTrainingItem(String trainingName) {
		searchCatalogtxt().clear();
		searchCatalogtxt().sendKeys(trainingName);
		searchbtn().click();
		return this;
	}

	public CatalogPage trainingItemGeneralInfo(String trainingName) {
		waitForAjax("waiting on catalog page to load");
		trainingitemlnk(trainingName).click();
		waitForAjax("waiting on catalog page link to open");
		return this;
	}

	public CatalogPage openTrainingItemClassInfo() {
		classInformationlnk().click();
		return this;
	}

	public CatalogPage openClassCode(String classCode) {
		classCodelnk(classCode).click();
		return this;
	}

	public CatalogPage registerForClass() {
		registerForClasslnk().click();
		waitForAjax("Success message");
		return this;
	}

	public String getEsignatureOverlay(String frameName) {
		return esignatureCatalogPageOverlay.getEsignatureOverlay(frameName);
	}

	public IESigOverlay electonicallySignIn(String... parameters) {
		return esignatureCatalogPageOverlay.electronicallySignIn(parameters[0], parameters[1], parameters[2],
				parameters[3]);
	}

	public String getPwdBoxAttribute() {
		frameIdIframeSwitch();
		return esignatureCatalogPageOverlay.getPwdBoxAttribute();
	}

	public CatalogPage completeCustomExam() {
		launchlnk().click();
		continuebtn().click();
		trueOption().click();
		continuebtn().click();
		// continuebtn().click();
		return this;
	}

	public boolean isEsignatureWindow() {
		return userIDtxt().isDisplayed();
	}

	public String getPasswordBoxAttribute() {
		pwdtxt().sendKeys("1234");
		return pwdtxt().getAttribute("type");

	}

	public CatalogPage electronicallySign(String userName, String password) {
		userIDtxt().sendKeys(userName);
		pwdtxt().clear();
		pwdtxt().sendKeys(password);
		electronicallySignlnk().click();
		ceContinuebtn().click();
		return this;
	}

	public HistoryPage openHistoryPage() {
		curriculumInfoNav_HomePageSection.openHistory();
		return new HistoryPage(driver);
	}

	public boolean isCatalogPage() {
		return searchbtn().isDisplayed();
	}

	public String getcatalogPageContent() {
		
		waitForAjax("catalog page");
	//	staticWait(1000);
		return catalogPageContent().getText();
	}
}
