package ui.pages.repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ui.BrowserElement;
import ui.BrowserElementImpl;
import ui.UiBase;

public class DefineEventLogReportPageRepo extends UiBase {
    protected BrowserElement selectEventdrp() {
        return findByName("selEvents");
    }
    protected BrowserElement startDateTxtBox() {
        return findByName("txtStartDate");
    }
    protected BrowserElement savedReportRadioBtn() {
		return findById("IsQuickReport1");
	}
    protected BrowserElement reportNameTxtBox() {
		return findByName("SFN");
	}
    protected BrowserElement endDateTxtBox() {
        return findByName("txtEndDate");
    }
    protected BrowserElement usrBox1() {
        return findByName("userValue1");
    }
    protected BrowserElement selectUserType1() {
        return findByName("user1");
    }
    protected BrowserElement selectOperatorType1() {
        return findByName("operatoru1");
    }
    protected BrowserElement runThisReportlnk() {
        return findByLinkText("Run this Report");
    }
    protected BrowserElement usrBox() {
        return findById("userValue1");
    }
    protected BrowserElement tempReportRadioBtn() {
        return findById("IsQuickReport0");
    }
    protected WebElement dateRangeRadioBtn(int index) {
        return getDriver().findElements(By.name("chkDates")).get(index);
    }
    protected BrowserElement eventRadioBtn(int index) {
        return new BrowserElementImpl(getDriver().findElements(By.name("Events")).get(index),getDriver().getWebDriver());
    }
    protected BrowserElement saveAndRunReportLnk() {
		return findByLinkText("Save and run this report");
	}

}
