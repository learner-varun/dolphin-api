package testcases;

import core.assertion.AssertionFactory;
import core.utils.JSONUtilities;
import dataprovider.TestDataProvider;
import examples.constants.EndPoints;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;


public class CustomTest extends TestDataProvider {

    @Test(dataProvider= "userProfileData", groups = {"REGRESSION", "POST"}, priority = 1)
    public void postAPICall(ITestContext context, String body) {
        Response response = testBuilder()
                .body(body)
                .post(EndPoints.CREATE_EMPLOYEE);

        context.setAttribute("userId", Integer.toString(response.getBody().jsonPath().get("id")));

        JSONObject jsonbody = JSONUtilities.convertStringToJSON(body);

        new AssertionFactory(response)
                .isCreated("Response code is not 201")
                .hasValue("name",jsonbody.get("name").toString() , "Name is not matching")
                .done();
    }

    @Test(groups = {"REGRESSION", "GET"}, priority = 2)
    public void getApiTest(ITestContext context) {
        Response response = testBuilder()
                .get(EndPoints.GET_EMPLOYEE_DETAILS.replace("{id}", (CharSequence) context.getAttribute("userId")));

        new AssertionFactory(response)
                .isSuccess("Response code is not 200")
                .done();
    }

    @Test(dataProvider= "userProfileData", groups = {"REGRESSION", "UPDATE"}, priority = 3)
    public void updateAPICall(ITestContext context,String body) {
        Response response = testBuilder()
                .body(body)
                .put(EndPoints.UPDATE_EMPLOYEE.replace("{id}", (CharSequence) context.getAttribute("userId")));

        JSONObject jsonbody = JSONUtilities.convertStringToJSON(body);

        new AssertionFactory(response)
                .isSuccess("Response code is not 200")
                .hasValue("name", jsonbody.get("name").toString(), "Name is not matching")
                .done();
    }

    @Test(groups = {"REGRESSION", "GET"}, priority = 4)
    public void getApiTestAfterUpdate(ITestContext context) {
        Response response = testBuilder()
                .get(EndPoints.GET_EMPLOYEE_DETAILS.replace("{id}", (CharSequence) context.getAttribute("userId")));

        new AssertionFactory(response)
                .matchSchemaOfResponse("getAPIResponseSchema.json")
                .isSuccess("Response code is not 200")
                .done();
    }

    @Test(groups = {"REGRESSION", "DELETE"}, priority = 5)
    public void deleteAPICall(ITestContext context) {
        Response response = testBuilder()
                .delete(EndPoints.DELETE_EMPLOYEE.replace("{id}", (CharSequence) context.getAttribute("userId")));

        new AssertionFactory(response)
                .isNoContent("Response code is not 204")
                .done();
    }

    @Test(groups = {"REGRESSION", "GET"}, priority = 6)
    public void getApiTestAfterDelete(ITestContext context) {
        Response response = testBuilder()
                .get(EndPoints.GET_EMPLOYEE_DETAILS.replace("{id}", (CharSequence) context.getAttribute("userId")));

        new AssertionFactory(response)
                .isNotFound("Response code is not 404")
                .done();
    }
}
