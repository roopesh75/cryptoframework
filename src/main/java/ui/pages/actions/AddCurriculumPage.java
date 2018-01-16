package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.repo.AddClassPageRepo;
import ui.pages.repo.AddCurriculumPageRepo;

public class AddCurriculumPage extends AddCurriculumPageRepo {
	BrowserDriver driver;

	public AddCurriculumPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	} 
	public ViewCurriculumPage addACurriculum(String... parameters) {
		recordScreenIframeSwitch();
		curriculumCodetxt().sendKeys(parameters[0]);
		curriculumTitletxt().sendKeys(parameters[0]);
		homeIcon().click();
		staticWait(1000);
		homeOrganization(parameters[1]).click();
		webAddresstxt().sendKeys("www.fda.com");
		descriptiontxt().sendKeys("This is a sample curriculum added by automation team");
		staticWait(1000);
		savebtn().click();
		return new ViewCurriculumPage(driver);
	}
}
