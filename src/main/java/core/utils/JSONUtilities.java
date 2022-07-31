package core.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static core.utils.FileUtilities.readDataFromFileToString;

public class JSONUtilities {
    public static JSONObject readDataFromJson(String filePath) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            jsonObject = (JSONObject) parser.parse(readDataFromFileToString(filePath));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject convertStringToJSON(String StringToConvert) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            jsonObject = (JSONObject) parser.parse(StringToConvert);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
