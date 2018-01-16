package ui.pages.repo.sections;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ui.BrowserElement;
import ui.BrowserElementImpl;
import ui.UiBase;

public class Content_TrainingManagementPageSectionRepo extends UiBase {
	protected BrowserElement description() {
		return findByXpath("//tr[td='Description:']/td[2]");
	}

	protected BrowserElement allowOnlineRegistrationtxt() {
		return findByXpath("//tr[td='Allow Online Registration:']/td[2]");
	}

	protected BrowserElement eduText(int index) {
		return new BrowserElementImpl(getDriver().findElements(By.className("EduText")).get(index),getDriver().getWebDriver());
	}

	protected BrowserElement customExamTrainingType() {
		return findByLinkText("Custom Exam");
	}
	protected List<WebElement> datesTxt() {
		return getDriver().findElements(By.cssSelector("span[title='UTC-5:00']"));
	}
	protected BrowserElement historyTbl(int index) {
		return new BrowserElementImpl(getDriver().findElements(By.className("PRINTTableBorder")).get(index),getDriver().getWebDriver());
	}
	
	protected BrowserElement completionDateTimeZoneTxt() {
		return findByXpath("//*[@id='content']/div/div[4]/table/tbody/tr[9]/td[3]/span");
	}
	protected BrowserElement signatureTimeZoneTxt() {
		return findByXpath("//*[@id='content']/div/div[4]/table/tbody/tr[13]/td[3]/span");
	}
	protected BrowserElement trainingStatus() {
		return findByXpath("//tr[td='Status:']/td[2]");
	}
	protected BrowserElement trainingType() {
		return findByXpath("//tr[td='Training Type:']/td[2]");
	}
	protected BrowserElement trainingTitle() {
		return findByXpath("//tr[td='Title:']/td[2]");
	}
	protected BrowserElement commentsTxt() {
		return findByXpath("//tr[td='Comments:']/td[2]");
	}
}
