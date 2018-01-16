package initializer;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import ui.support.Config;
import ui.utils.HTTPClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicDataProvider extends Data{
    private static final Logger logger = Logger.getLogger(DynamicDataProvider.class);

    @DataProvider(parallel = true)
    public  static Object[] createAdminUser() throws Exception{
        String response=new HTTPClient().post(Config.getToolsURL() + "/createNewAdminUser","{\"companyID\":\""+getData("GENERIC.AUTOMATION.COMPANYCODE")+"\",\"homeOrgID\":\""+
                getData("GENERIC.TOP_ORGANIZATION")+"\"}","json");

        logger.info("ThreadID: "+Thread.currentThread().getId()+"  "+Thread.currentThread().getName()+" "+"API RESPONSE: "+response);
        Matcher matcher = Pattern.compile("r\":\"(.*?)\"").matcher(response);

        String userName="blank";
        while(matcher.find()) {
            userName=matcher.group(1);

        }
        logger.info("ThreadID: "+Thread.currentThread().getId()+"  "+Thread.currentThread().getName()+" "+"USER NAME: "+userName);
     return new Object[]{userName};
    }
}
