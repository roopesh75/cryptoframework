package ui.pages.repo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ui.BrowserElement;
import ui.BrowserElementImpl;
import ui.UiBase;

public class HistoryPageRepo extends UiBase {

	protected BrowserElement trainingitemlnk(String trainingName) {
		return findByLinkText(trainingName);
	}
	protected BrowserElement completionInfoPageContent() {
		return findByTagName("body");
	}
	protected BrowserElement dateInfoLbl(int index) {
		return new BrowserElementImpl(getDriver().findElements(By.cssSelector("span[title='UTC -5:00']")).get(index),getDriver().getWebDriver());
	}
	protected List<WebElement> dateInfosLbl() {
		return getDriver().findElements(By.cssSelector("span[title='UTC -5:00']"));
	}
	protected BrowserElement search() {
		return findByXpath("//*[@title='Search']");
	}
	protected BrowserElement completionLbl(){
		return findByXpath("//*[@id='main-panel']/div[5]/div/div[3]/div[2]/p/span");
	}
	protected BrowserElement signatureLbl(){
		return findByXpath("//*[@id='main-panel']/div[5]/div/div[6]/div[2]/p/span");
	}
	protected BrowserElement sortOptionsLnk(){
		return findById("span-sort");
	}
	
	protected BrowserElement completionDateOldestBtn(){
		return findByXpath("//button[text()='Completion Date (Oldest)']");
	}
}
