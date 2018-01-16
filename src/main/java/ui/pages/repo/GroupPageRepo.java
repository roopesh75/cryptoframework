package ui.pages.repo;

import org.openqa.selenium.By;

import ui.BrowserElement;
import ui.BrowserElementImpl;
import ui.UiBase;


public class GroupPageRepo extends UiBase {
	protected BrowserElement removeLnk(){
		return findByCssSelector("a[title='Remove User']");
	}
	protected BrowserElement removeUserLnk(){
		return findByLinkText("Remove User");
	}
	protected BrowserElement UserAddTbl(int index){
		return new BrowserElementImpl(getDriver().findElements(By.className("PRINTTableBorder")).get(index),getDriver().getWebDriver());
	}//

}
