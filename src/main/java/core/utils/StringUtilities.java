package core.utils;

public class StringUtilities {
    public static boolean isNull(String stringToCheck) {
        try {
            if (stringToCheck.isEmpty())
                return false;
        } catch (Exception e) {
            return true;
        }
        return false;
    }
}
