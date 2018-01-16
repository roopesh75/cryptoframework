package ui.pages.actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import ui.BrowserDriver;
import ui.pages.actions.sections.AdminNav_CommonSection;
import ui.pages.actions.sections.OptionsPageSection_SetupOptions;
import ui.pages.repo.OptionsPageRepo;


public class OptionsPage extends OptionsPageRepo {
    BrowserDriver driver;
    OptionsPageSection_SetupOptions setupOptions_optionsPage;
    AdminNav_CommonSection adminNav_CommonSection;
    public OptionsPage(BrowserDriver driver) {
        this.driver = driver;
        setupOptions_optionsPage = new OptionsPageSection_SetupOptions(driver);
        adminNav_CommonSection = new AdminNav_CommonSection(driver);
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }
    public boolean isOptionsPage() {
        return setupOptions_optionsPage.isSetupOptionsSectionLoaded();
    }

    public DefinePasswordPoliciesPage openPasswordPolicies() {
       // staticWait(1000);
    	return setupOptions_optionsPage.openPasswordPolicies();
    }
    
    public EsignatureRequirementsPage openEsignatureRequirements() {
        return setupOptions_optionsPage.openEsignatureRequirements();
    }
    public LogsPage openLogsPage() {
		adminNav_CommonSection.openLogsPage();
		return new LogsPage(driver);
	}
    public PreferencesPage openSupport() {
		adminNav_CommonSection.openSupport();
		return new PreferencesPage(driver);
	}
    public TrainingPage openTrainingPage() {
		adminNav_CommonSection.openTrainingPage();
		return new TrainingPage(driver);
	}
}
