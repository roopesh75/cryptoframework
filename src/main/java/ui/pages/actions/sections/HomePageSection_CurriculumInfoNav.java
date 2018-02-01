package ui.pages.actions.sections;

import ui.BrowserDriver;
import ui.pages.actions.CatalogPage;
import ui.pages.actions.HistoryPage;
import ui.pages.actions.ToDoPage;
import ui.pages.repo.sections.CurriculumInfoNav_HomePageSectionRepo;

public class HomePageSection_CurriculumInfoNav extends CurriculumInfoNav_HomePageSectionRepo {
	BrowserDriver driver;

    public HomePageSection_CurriculumInfoNav(BrowserDriver driver) {
        this.driver = driver;
        setDriver(driver);

    }
    
    public CatalogPage openCatalog() {
    	cataloglnk().click();
    	return new CatalogPage(driver);
    }
    
    public HistoryPage openHistory() {
    	historylnk().click();
    	return new HistoryPage(driver);
    }
    public ToDoPage openToDoPage() {
    	toDolnk().click();
    	return new ToDoPage(driver);
    }
    
}
