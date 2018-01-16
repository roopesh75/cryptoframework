package ui.pages.actions;

import java.util.Set;

import ui.BrowserDriver;
import ui.pages.repo.AddNewUserPageRepo;

public class AddNewUserPage extends AddNewUserPageRepo {
	BrowserDriver driver;

	public AddNewUserPage(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
	}

	public UserManagementPage addANewUser(String userId) {
		recordScreenIframeSwitch();
		/*firstName().sendKeys("Alice");
		lastName().sendKeys("Patricia");
		employeeNo().sendKeys(userId);
		password().sendKeys("pass1234");
		confirmPassword().sendKeys("pass1234");*/
		enterFnameLnameUserid(userId);
		enterPwd();
		save().click();
		staticWait(2000);
		return new UserManagementPage(driver);
	}

	public UserManagementPage addANewUser(String... parameters) {
		recordScreenIframeSwitch();
		firstName().sendKeys(parameters[0]);
		lastName().sendKeys(parameters[1]);
		employeeNo().sendKeys(parameters[2]);
		password().sendKeys("Pass1234");
		confirmPassword().sendKeys("Pass1234");
		viewOrganizationTree().click();
		switchToNewWindow();
		if (parameters.length >= 4) {
			findByLinkText(parameters[3]).click();
			
		}
		continueAfterSelectLnk().click();
		Set<String> allWindows = getDriver().getWindowHandles();
		for (String currentWindow : allWindows) {
			driver.switchTo().window(currentWindow);
		}
		recordScreenIframeSwitch();
		save().click();
		return new UserManagementPage(driver);
	}
	
	public AddNewUserPage enterFnameLnameUserid(String userId) {
		recordScreenIframeSwitch();
		firstName().sendKeys("Alice");
		lastName().sendKeys("Patricia");
		employeeNo().sendKeys(userId);
		return this;
	}

	public String getPwdBoxAttribute() {
		return password().getAttribute("type");
	}
	public String getConfirmPwdBoxAttribute() {
		return confirmPassword().getAttribute("type");
	}
	public String getFirstName() {
		
		return firstName().getAttribute("value");
	}
	public String getLastName() {
		
		return lastName().getAttribute("value");
	}
	public String getUserId() {
		
		return employeeNo().getAttribute("value");
	}
	public AddNewUserPage enterPwd() {
		recordScreenIframeSwitch();
		password().sendKeys("Pass1234");
		confirmPassword().sendKeys("Pass1234");
		return this;
	}
	public String getAlertBoxText() {

		return switchToAlart().getText();
	}

	public UserManagementPage editUser(String... parameters) {
		recordScreenIframeSwitch();
		firstName().clear();
		firstName().sendKeys(parameters[0]);
		if (parameters.length == 2) {
			lastName().clear();
			lastName().sendKeys(parameters[1]);
		}
		saveChanges().click();
		//staticWait(3000);
		switchToDefaultFrame();
		return new UserManagementPage(driver);
	}

	public boolean isAddUsersPage() {
		recordScreenIframeSwitch();
		boolean pageState = firstName().isDisplayed();
		switchToDefaultFrame();
		return pageState;
	}
	public AddNewUserPage enterHomePhone() {
		recordScreenIframeSwitch();
		homePhone().sendKeys("7326941000");
		return this;
	}
	public UserManagementPage saveUser() {
		recordScreenIframeSwitch();
		save().click();
		return new UserManagementPage(driver);
	}
	

}
