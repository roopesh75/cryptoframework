package initializer;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import ui.support.Config;

public class Data {
    private static final Logger logger = Logger.getLogger(Data.class);
    private static String dataFileCW = "data_compliance_wire.properties";
    private static String dataFileELMS = "data_elms.properties";
    private static Configuration dataConfig;

    public static String getData(String key) throws Exception {
        if(Config.getBanner().contains("ComplianceWire")){
            dataConfig = new PropertiesConfiguration(Config.loadAndGetResourceLocation(dataFileCW));
        }else if(Config.getBanner().contains("ELMS")){
            dataConfig = new PropertiesConfiguration(Config.loadAndGetResourceLocation(dataFileELMS));
        }
        return dataConfig.getString(key);
    }
}
