package testcases;

import core.assertion.AssertFactory;
import core.utils.PojoUtilities;
import dataprovider.TestDataProvider;
import examples.constants.EndPoints;
import examples.pojo.CreateUser;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static core.faker.FakeDataFactory.fakeData;

public class CustomTest extends TestDataProvider {


    @Test(groups = {"REGRESSION", "POST"}, priority = 1)
    public void postAPICall(ITestContext context) {
        CreateUser createUser = new CreateUser();
        String name = fakeData.name().fullName();
        createUser.setName(name);
        createUser.setEmail(fakeData.internet().safeEmailAddress());
        boolean gender = fakeData.random().nextBoolean();
        if (gender) {
            createUser.setGender("male");
        } else {
            createUser.setGender("female");
        }
        createUser.setStatus("active");

        Response response = testBuilder()
                .body(PojoUtilities.generateBodyJson(createUser))
                .post(EndPoints.CREATE_EMPLOYEE);
        context.setAttribute("userId", Integer.toString(response.getBody().jsonPath().get("id")));

        new AssertFactory(response)
                .isCreated("Response code is not 201")
                .hasValue("name", name, "Name is not matching")
                .done();
    }

    @Test(groups = {"REGRESSION", "GET"}, priority = 2)
    public void getApiTest(ITestContext context) {
        Response response = testBuilder()
                .get(EndPoints.GET_EMPLOYEE_DETAILS.replace("{id}", (CharSequence) context.getAttribute("userId")));

        new AssertFactory(response)
                .isSuccess("Response code is not 200")
                .done();
    }

    @Test(groups = {"REGRESSION", "UPDATE"}, priority = 3)
    public void updateAPICall(ITestContext context) {
        CreateUser createUser = new CreateUser();
        String name = fakeData.name().fullName();
        createUser.setName(name);
        createUser.setEmail(fakeData.internet().safeEmailAddress());
        boolean gender = fakeData.random().nextBoolean();
        if (gender) {
            createUser.setGender("male");
        } else {
            createUser.setGender("female");
        }
        createUser.setStatus("active");

        Response response = testBuilder()
                .body(PojoUtilities.generateBodyJson(createUser))
                .put(EndPoints.UPDATE_EMPLOYEE.replace("{id}", (CharSequence) context.getAttribute("userId")));
        new AssertFactory(response)
                .isSuccess("Response code is not 200")
                .hasValue("name", name, "Name is not matching")
                .done();
    }

    @Test(groups = {"REGRESSION", "GET"}, priority = 4)
    public void getApiTestAfterUpdate(ITestContext context) {
        Response response = testBuilder()
                .get(EndPoints.GET_EMPLOYEE_DETAILS.replace("{id}", (CharSequence) context.getAttribute("userId")));

        new AssertFactory(response)
                .matchSchemaOfResponse("getAPIResponseSchema.json")
                .isSuccess("Response code is not 200")
                .done();
    }

    @Test(groups = {"REGRESSION", "DELETE"}, priority = 5)
    public void deleteAPICall(ITestContext context) {
        Response response = testBuilder()
                .delete(EndPoints.DELETE_EMPLOYEE.replace("{id}", (CharSequence) context.getAttribute("userId")));

        new AssertFactory(response)
                .isNoContent("Response code is not 204")
                .done();
    }

    @Test(groups = {"REGRESSION", "GET"}, priority = 6)
    public void getApiTestAfterDelete(ITestContext context) {
        Response response = testBuilder()
                .get(EndPoints.GET_EMPLOYEE_DETAILS.replace("{id}", (CharSequence) context.getAttribute("userId")));

        new AssertFactory(response)
                .isNotFound("Response code is not 404")
                .done();
    }
}
