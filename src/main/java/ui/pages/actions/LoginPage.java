package ui.pages.actions;

import ui.BrowserDriver;
import ui.pages.repo.LoginPageRepo;

/**
 * Created by vevinmoza on 9/27/17.
 */
public class LoginPage extends LoginPageRepo {

	BrowserDriver driver;

	public LoginPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}

	public HomePage signIn(String userId, String password, String companyCode) {
		/*userNameBox().sendKeys(userId);
		passwordBox().sendKeys(password);
		companyNameBox().clear();
		companyNameBox().sendKeys(companyCode);
		acceptBtn().click();
		waitForAjax("clicked on AcceptBtn");
		*/
		enterUserIDPwdInfo(userId, password);
		acceptLogin(companyCode);
		return new HomePage(driver);
	}

	public LoginPage openApplicationURL(String applicationURL) {
		getDriver().get(applicationURL);
		return this;
	}

	public LoginPage enterUserIDPwdInfo(String userId, String password) {
		waitUntilElementDisplayed(userNameBox());
		userNameBox().sendKeys(userId);
		passwordBox().sendKeys(password);
		return this;
	}

	public String getPwdBoxAttribute() {
		return passwordBox().getAttribute("type");
	}

	public HomePage acceptLogin(String companyCode) {
		companyNameBox().clear();
		companyNameBox().sendKeys(companyCode);
		acceptBtn().click();
		waitForAjax("clicked on AcceptBtn");
		return new HomePage(driver);
	}
	
	public String getacceptBtn() {
		return acceptBtn().getAttribute("innerText");
	}

}
