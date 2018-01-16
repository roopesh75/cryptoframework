package ui.pages.repo;

import ui.BrowserElement;
import ui.UiBase;

public class DefineEventLogReportPageRepo extends UiBase {
    protected BrowserElement selectEventdrp() {
        return findByName("selEvents");
    }
    protected BrowserElement runThisReportlnk() {
        return findByLinkText("Run this Report");
    }
    protected BrowserElement usrBox() {
        return findById("userValue1");
    }


}
