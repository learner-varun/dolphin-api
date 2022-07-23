package core.assertion;

import core.constants.HTTPCodes;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class AssertFactory {
    Response response;

    SoftAssert softAssert;
    boolean assertionChecker = true;
    String finalErrorMessage = "";

    public AssertFactory check(Response response) {
        softAssert = new SoftAssert();
        this.response = response;
        return this;
    }

    public void done() {
        softAssert.assertTrue(assertionChecker, finalErrorMessage);
        finalErrorMessage = "";
        this.softAssert.assertAll();
        assertionChecker = true;

    }

    public AssertFactory isSuccess(String reasonOfFailure) {
        assertionChecker = verifyResponseCode(HTTPCodes.OK);
        if (!assertionChecker) {
            finalErrorMessage = finalErrorMessage + reasonOfFailure + "\n\t";
        }
        return this;
    }
    public AssertFactory isCreated(String reasonOfFailure) {
        assertionChecker = verifyResponseCode(HTTPCodes.CREATED);
        if (!assertionChecker) {
            finalErrorMessage = finalErrorMessage + reasonOfFailure + "\n\t";
        }
        return this;
    }

     private boolean verifyResponseCode(int responseCode)
    {
       if(response.getStatusCode() == responseCode)
       {
           return true;
       }else
       {
           return false;
       }
    }

    public AssertFactory isNoContent(String reasonOfFailure) {
        assertionChecker = verifyResponseCode(HTTPCodes.NO_CONTENT);
        if (!assertionChecker) {
            finalErrorMessage = finalErrorMessage + reasonOfFailure + "\n\t";
        }
        return this;
    }
}
