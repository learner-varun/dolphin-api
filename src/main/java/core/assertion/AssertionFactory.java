package core.assertion;

import core.constants.HTTPCodes;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.io.File;

import static core.configs.TestConfigs.API_SLA_TIME;
import static core.configs.TestConfigs.FAIL_TEST_ON_SLA_BREACH;

public class AssertionFactory {
    static boolean assertionChecker = true;
    Response response;
    SoftAssert softAssert;
    String finalErrorMessage = "";

    public AssertionFactory(Response response) {
        softAssert = new SoftAssert();
        this.response = response;
    }

    public void done() {
        if(FAIL_TEST_ON_SLA_BREACH && response.getTime()>API_SLA_TIME)
        {
            assertionChecker=false;
            finalErrorMessage = finalErrorMessage.trim() +"\n\tThe expected SLA is breached.\n\tExpected SLA : "+API_SLA_TIME +"\n\tActual Response is : "+response.getTime();
        }
        softAssert.assertTrue(assertionChecker, finalErrorMessage.trim());
        assertionChecker = true;
        softAssert.assertAll();
    }

    public AssertionFactory isSuccess(String reasonOfFailure) {
        if (!(verifyResponseCode(HTTPCodes.OK))) {
            finalErrorMessage = finalErrorMessage + reasonOfFailure + "\n\t";
            assertionChecker = false;
        }
        return this;
    }

    public AssertionFactory isCreated(String reasonOfFailure) {
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

    public AssertionFactory isNoContent(String reasonOfFailure) {
        if (!verifyResponseCode(HTTPCodes.NO_CONTENT)) {
            finalErrorMessage = finalErrorMessage + reasonOfFailure + "\n\t";
            assertionChecker = false;
        }
        return this;
    }

    public AssertionFactory isNotFound(String reasonOfFailure) {
        if (!verifyResponseCode(HTTPCodes.NOT_FOUND)) {
            finalErrorMessage = finalErrorMessage + reasonOfFailure + "\n\t";
            assertionChecker = false;
        }
        return this;
    }

    public AssertionFactory hasValue(String key, String value, String reasonOfFailure) {
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

    public AssertionFactory containsValue(String key, String value, String reasonOfFailure) {
        softAssert.assertTrue(response.getBody().jsonPath().get(key).toString().equalsIgnoreCase(value), reasonOfFailure);
        return this;
    }
    public AssertionFactory matchSchemaOfResponse(String fileName)
    {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/schema/"+fileName)));
        return this;
    }
}
