package tests.compliancewire.users;


import initializer.BaseTest;
import initializer.BrowserInitializer;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.support.Config;
import ui.utils.HTTPClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsersUiTest extends BaseTest{
    private static final Logger logger = Logger.getLogger(UsersUiTest.class);
    List<String> usersLst =
            Collections.synchronizedList(new ArrayList<String>());

    @DataProvider(name = "usersInfo")
    public Object[] users() {
        return new Object[]{"03138","42819","47700","49682","49490","45086","37895","48700","34801","43336","15735","45082","08589","39233","37244","45933","37753","24204","39134","41830","02295","48218","60765","13132","01583","07553","51232","38521","12230","19727","20165","38279","11749","80872","14549","61591","41981","47312","05374","30330","49658","53444","09350","61496","50665","34344","12331","37857","12085","60050","39878","51742","46750","50129","46548","30401","81178","14514","07522","91270","03951","08116","80222","47209","10395","10405","04073","42513","17403","31300","60778","81217","02583","46910","06116","13752","96946","06934","61613","51930","13415","03315","49463","34622","46660","34746","81055","90906","19732","03452","05652","11199","12064","49472","80814","42362","21253","01840","30961","80659","18266","43667","11009","53235","02981","46655"};
    }

    @Test(alwaysRun=true,dataProvider="usersInfo")
    public void testUsers(String userId)throws Exception{
        loginPage.signIn(userId,"FtYM$!Qyp",getData("GENERIC.AUTOMATION.COMPANYCODE"));
        homePage.logOut();
        Thread.sleep(4000);
        loginPage.openApplicationURL(Config.getApplicationUrl());
    }

    @AfterTest
    public void failedUsers(){
        logger.info("ThreadID: "+Thread.currentThread().getId()+"  "+Thread.currentThread().getName()+" "+usersLst);
    }

}
