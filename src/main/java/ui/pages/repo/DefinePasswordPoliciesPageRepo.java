package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class DefinePasswordPoliciesPageRepo extends UiBase {

    protected BrowserElement editPasswordPolicieslnk() {
        return findByLinkText("Edit Password Policies");
    }

    protected BrowserElement minLengthdrp() {
        return findByName("MinLen");
    }
    protected BrowserElement maxLengthdrp() {
        return findByName("MaxLen");
    }
    protected BrowserElement saveChangeslnk() {
        return findByLinkText("Save Changes");
    }
    
    protected BrowserElement reqPwdContainLetterschk() {
        return findByName("pwdAlpha");
    }
    
    protected BrowserElement reqPwdUppercaseLetterschk() {
        return findByName("pwdUpper");
    }
   
    protected BrowserElement reqPwdContainNumberschk(){
        return findByName("pwdNumeric");
    }
    protected BrowserElement passwordComplexitytbl(){
        return findByXpath("//td[text()='Password Complexity']/following::td[3]");
    }
    protected BrowserElement changePasswordtbl(){
        return findByXpath("//td[text()='When users change their password']/following::td[2]");
    }
    protected BrowserElement passwordLengthstbl(){
        return findByXpath("//div[@class='CWBoxTT1']/following::div");
    }
    protected BrowserElement lockUserradio(){
        return findByXpath("//input[@name='pwdHist']/following::input[1]");
    }
    protected BrowserElement allowOlderPwdradio(){
        return findByXpath("//input[@name='pwdHist']");
    }
    protected BrowserElement lockPwdNumbedrp(){
        return findByName("pwdHistCnt");
    }
    
}
