package core.configs;

import java.util.HashMap;
import java.util.Map;

public class TestConfigs {
    public static final String TEST_RESOURCE_FOLDER = "src/test/resources";
    public static final String DEAFULT_CONFIG_JSON_FILE = "default.json";
    public static String USER_DIR = System.getProperty("user.dir");
    public static String EXTENT_REPORT_PATH = USER_DIR.concat("/Report");
    public static String EXTENT_REPORT_FILE_NAME = "/index.html";
    public static String EXTENT_REPORT_FULL_PATH = EXTENT_REPORT_PATH.concat(EXTENT_REPORT_FILE_NAME);
    public static String PROJECT_NAME = "API Testing Report";
    public static String SUITE_NAME = "Regression suite";
    public static String EXECUTION_START_TIME = "test";
    public static boolean ENABLE_EXTEND_REPORT;
    public static boolean ADD_SKIPPED_CASES_TO_REPORT;
    public static String ENVIRONMENT_NAME;
    public static String DOMAIN_NAME = "";
    public static String PORT_NO = "";
    public static String PROTOCOL = "";
    public static long API_SLA_TIME = 1000;
    public static int MAXIMUM_RETRY_COUNT = 3;

    public static Map<String, String> SYSTEM_PROPERTIES = new HashMap<String, String>();
    public static Map<String, String> DEFAULT_HEADERS = new HashMap<String, String>();
}
