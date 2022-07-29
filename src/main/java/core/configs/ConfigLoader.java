package core.configs;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;

import java.util.HashMap;

import static core.configs.TestConfigs.*;
import static core.utils.JSONUtilities.readDataFromJson;

public class ConfigLoader {

    public static void loadConfig(String filePath) {
        JSONObject configData = readDataFromJson(filePath);

        JSONObject testConfigs = (JSONObject) configData.get("testConfigs");
        DOMAIN_NAME = testConfigs.get("domainURL").toString();
        PORT_NO = testConfigs.get("portNumber").toString();
        PROTOCOL = testConfigs.get("protocol").toString();
        ENVIRONMENT_NAME = testConfigs.get("environment").toString();
        PROJECT_NAME = testConfigs.get("projectName").toString();
        SUITE_NAME = testConfigs.get("suiteName").toString();
        if (Boolean.parseBoolean(testConfigs.get("failOnRetry").toString())) {
            MAXIMUM_RETRY_COUNT = Integer.parseInt(testConfigs.get("retryCount").toString());
        } else {
            MAXIMUM_RETRY_COUNT = 0;
        }

        JSONObject reportConfig = (JSONObject) configData.get("report");
        ENABLE_EXTEND_REPORT = Boolean.parseBoolean(reportConfig.get("enableExtendReport").toString());
        ADD_SKIPPED_CASES_TO_REPORT = Boolean.parseBoolean(reportConfig.get("addSkippedTestToReport").toString());

        JSONObject systemInfo = (JSONObject) configData.get("systemInfo");
        SYSTEM_PROPERTIES = new Gson().fromJson(String.valueOf(systemInfo), new TypeToken<HashMap<String, Object>>() {
        }.getType());

        JSONObject defaultHeaderList = (JSONObject) configData.get("defaultHeaders");
        DEFAULT_HEADERS = new Gson().fromJson(String.valueOf(defaultHeaderList), new TypeToken<HashMap<String, Object>>() {
        }.getType());
    }

}
