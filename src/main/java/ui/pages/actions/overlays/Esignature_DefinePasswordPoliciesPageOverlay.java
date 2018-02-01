package ui.pages.actions.overlays;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import ui.BrowserDriver;
import ui.pages.actions.DefinePasswordPoliciesPage;
import ui.pages.actions.IESigOverlay;
import ui.pages.repo.overlays.Esignature_DefinePasswordPoliciesPageOverlayRepo;

public class Esignature_DefinePasswordPoliciesPageOverlay extends Esignature_DefinePasswordPoliciesPageOverlayRepo {
	BrowserDriver driver;

	public Esignature_DefinePasswordPoliciesPageOverlay(BrowserDriver driver) {
		this.driver = driver;
		setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public String getEsignatureOverlay(String frameName) {
		if (frameName.equalsIgnoreCase("RecScr"))
			popUpIframeViaRecordScreenIframeSwitch();
		else if (frameName.equalsIgnoreCase("frameId"))
			frameIdIframeSwitch();
		//waitUntilElementDisplayed(eSignature());
		takeDebugScreenShot();
		//Test fix
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver().getWebDriver())
    		    .withTimeout(30, TimeUnit.SECONDS)
    		    .pollingEvery(5, TimeUnit.SECONDS)
    		    .ignoring(NoSuchElementException.class);
    	
    	WebElement eSignature = wait.until(new Function<WebDriver, WebElement>() 
    	{
    	  public WebElement apply(WebDriver driver) {
    	  return driver.findElement(By.className("EDUBody"));
    	}
    	});
    	
		return eSignature.getText();
	}

	public IESigOverlay electronicallySignIn(String... parameters) {
		try{
			acceptAlert();
		}
		catch(Exception e){	
			if (parameters[3].equalsIgnoreCase("RecScr")){
				popUpIframeViaRecordScreenIframeSwitch();}
			else if (parameters[3].equalsIgnoreCase("frameId")){	
				frameIdIframeSwitch();}
			//take screenshot after frame switch due to failure
			takeDebugScreenShot();
			waitUntilElementDisplayed(signatureUserNameBox());
			signatureUserNameBox().sendKeys(parameters[0]);
			signaturePasswordBox().clear();
			signaturePasswordBox().sendKeys(parameters[1]);
			new Select(signatureReasondrp()).selectByVisibleText(parameters[2]);
			if (parameters[3].equalsIgnoreCase("RecScr"))
				recordScreenIframeSwitch();
			electronicallySignlnk().click();
			waitUntilElementInvisible("Electronically Sign");
			return new DefinePasswordPoliciesPage(driver);
		}
		
		return new DefinePasswordPoliciesPage(driver);
		
	}

	public String getPwdBoxAttribute() {

		signaturePasswordBox().sendKeys("1234");
		return signaturePasswordBox().getAttribute("type");

	}
}
