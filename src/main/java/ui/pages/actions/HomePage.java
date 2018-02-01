package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.actions.overlays.ToolsNav_HomePageOverlay;
import ui.pages.actions.overlays.UserProfile_HomePageOverlay;
import ui.pages.actions.sections.HomePageSection_CurriculumInfoNav;
import ui.pages.actions.sections.HomePageSection_SettingsNav;
import ui.pages.repo.HomePageRepo;

public class HomePage extends HomePageRepo {
    BrowserDriver driver;
    HomePageSection_SettingsNav settingsNav_homePage;
    HomePageSection_CurriculumInfoNav curriculumInfoNav_homePage;

    public HomePage(BrowserDriver driver) {
        this.driver = driver;
        setDriver(driver);
        settingsNav_homePage = new HomePageSection_SettingsNav(driver);
        curriculumInfoNav_homePage = new HomePageSection_CurriculumInfoNav(driver);

    }

    public ToolsNav_HomePageOverlay openToolsMenu() {
        settingsNav_homePage.openToolsMenu();
        return new ToolsNav_HomePageOverlay(driver);
    }
    
    public UsersPage openUsers(){
        openToolsMenu().openUsersPage();
        return new UsersPage(driver);
    }
   
    public LoginPage logOut(){
        settingsNav_homePage.logOut();

        return new LoginPage(driver);
    }

    public UserProfile_HomePageOverlay  openUserProfile() {
        settingsNav_homePage.openUserProfile();
        return new UserProfile_HomePageOverlay(driver);
    }
    
    public boolean  isHomePage() {
        return settingsNav_homePage.isHomePage();
    }
    public CatalogPage openCatalog() {
    	curriculumInfoNav_homePage.openCatalog();
		return new CatalogPage(driver);
	}
    public String getOverlayTxt() {	
		return new ToolsNav_HomePageOverlay(driver).getOverlayTxt();
	}
	public ToDoPage getToDoPage(){
		return new ToDoPage(driver);
	}
	public HistoryPage openHistoryPage() {
		curriculumInfoNav_homePage.openHistory();
		return new HistoryPage(driver);
	}

	public String getPendingReports() {
		return getToDoPage().getPendingReports();
	}

	public HomePage openAnswerQuestions() {
		ansQuestionsLnk().click();
		return this;
	}

	public HomePage addQuestionAnswer() {
		firstQuestionLnk().click();
		firstAnswerTxtBox().sendKeys("a");
		saveBtn().click();
		okBtn().click();
		continueBtn().click();
		return this;
	}
	public String getPanelTitle(){
		return panelTitleTxt().getText();
	}
	
	
	
}
