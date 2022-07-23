package core.configs;

public class TestConfigs {
    public static String USER_DIR = System.getProperty("user.dir");
    public static String EXTENT_REPORT_PATH = USER_DIR.concat("/Report");
    public static String EXTENT_REPORT_FILE_NAME = "/index.html";
    public static String EXTENT_REPORT_FULL_PATH = EXTENT_REPORT_PATH.concat(EXTENT_REPORT_FILE_NAME);

    public static String REPORT_DOCUMENT_TITLE = "API Testing Report";
    public static String REPORT_REPORT_NAME = "Regression suite";

    public static String EXECUTION_START_TIME = "test";

    public static long API_SLA_TIME = 1000;
    public static int MAXIMUM_RETRY_COUNT = 0;
}
