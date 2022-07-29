package core.assertion;

import core.constants.HTTPCodes;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class AssertFactory {
    static boolean assertionChecker = true;
    Response response;
    SoftAssert softAssert;
    String finalErrorMessage = "";

    public AssertFactory(Response response) {
        softAssert = new SoftAssert();
        this.response = response;
    }

    public void done() {
        softAssert.assertTrue(assertionChecker, finalErrorMessage);
        assertionChecker = true;
        softAssert.assertAll();
    }

    public AssertFactory isSuccess(String reasonOfFailure) {
        if (!(verifyResponseCode(HTTPCodes.OK))) {
            finalErrorMessage = finalErrorMessage + reasonOfFailure + "\n\t";
            assertionChecker = false;
        }
        return this;
    }

    public AssertFactory isCreated(String reasonOfFailure) {
        if (!verifyResponseCode(HTTPCodes.CREATED)) {
            finalErrorMessage = finalErrorMessage + reasonOfFailure + "\n\t";
            assertionChecker = false;
        }
        return this;
    }

    private boolean verifyResponseCode(int responseCode) {
        if (response.getStatusCode() == responseCode) {
            return true;
        } else {
            return false;
        }
    }

    public AssertFactory isNoContent(String reasonOfFailure) {
        if (!verifyResponseCode(HTTPCodes.NO_CONTENT)) {
            finalErrorMessage = finalErrorMessage + reasonOfFailure + "\n\t";
            assertionChecker = false;
        }
        return this;
    }

    public AssertFactory isNotFound(String reasonOfFailure) {
        if (!verifyResponseCode(HTTPCodes.NOT_FOUND)) {
            finalErrorMessage = finalErrorMessage + reasonOfFailure + "\n\t";
            assertionChecker = false;
        }
        return this;
    }

    public AssertFactory hasValue(String key, String value, String reasonOfFailure) {
        if (!(getValueFromResponse(response, key).equalsIgnoreCase(value))) {
            finalErrorMessage = finalErrorMessage + reasonOfFailure + "\n\t";
            assertionChecker = false;
        }
        return this;
    }

    public String getValueFromResponse(Response response, String key) {
        try {
            return response.getBody().jsonPath().get(key).toString();
        } catch (Exception e) {
            return "";
        }
    }

    public AssertFactory containsValue(String key, String value, String reasonOfFailure) {
        softAssert.assertTrue(response.getBody().jsonPath().get(key).toString().equalsIgnoreCase(value), reasonOfFailure);
        return this;
    }
}
