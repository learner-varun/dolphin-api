package testcases;

import core.assertion.AssertFactory;
import core.base.APIBase;
import core.enums.HTTPMethod;
import core.listners.TestNGActions;
import core.utils.PojoUtilities;
import examples.constants.EndPoints;
import examples.pojo.CreateUser;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static core.faker.FakeDataFactory.*;

public class APITest extends TestNGActions {

    @Test(groups = {"REGRESSION","GET"},dependsOnMethods = {"postAPICall"},priority = 3)
    public void getApiTest(ITestContext context)
    {
        AssertFactory assertFactory = new AssertFactory();
        APIBase apiBase = new APIBase();
        apiBase.addHeader("Authorization","Bearer ddd19ff8b9c103063c5a7dd89701e5544645b675aa9b9ea98252d5aabb561766");

       Response response = apiBase.makeAPICall(EndPoints.GET_EMPLOYEE_DETAILS.replace("{id}", (CharSequence) context.getAttribute("userId")), HTTPMethod.GET);

        assertFactory.check(response)
                .isSuccess("Response code is not 200")
                .done();
    }

    @Test(groups = {"REGRESSION","GET"},priority = 6)
    public void getApiTest1(ITestContext context)
    {
        AssertFactory assertFactory = new AssertFactory();
        APIBase apiBase = new APIBase();

        Response response = apiBase.makeAPICall(EndPoints.GET_EMPLOYEE_DETAILS.replace("{id}","2853"), HTTPMethod.GET);

        assertFactory.check(response)
                .isSuccess("Response code is not 200")
                .done();
    }

    @Test(groups = {"REGRESSION","GET"},priority = 5)
    public void getApiTest2(ITestContext context)
    {
        AssertFactory assertFactory = new AssertFactory();
        APIBase apiBase = new APIBase();
        Response response = apiBase.makeAPICall(EndPoints.GET_ALL_EMPLOYEE, HTTPMethod.GET);

        assertFactory.check(response)
                .isSuccess("Response code is not 200")
                .done();
    }

    @Test(groups = {"REGRESSION","POST"},priority = 1)
    public void postAPICall(ITestContext context)
    {
        AssertFactory assertFactory = new AssertFactory();
        APIBase apiBase = new APIBase();
        CreateUser createUser = new CreateUser();
        createUser.setName(fakeData.name().fullName());
        createUser.setEmail(fakeData.internet().safeEmailAddress());
        boolean gender = fakeData.random().nextBoolean();
        if(gender)
        {createUser.setGender("male");}else{createUser.setGender("female");}
        createUser.setStatus("active");
        apiBase.setBody(PojoUtilities.generateBodyJson(createUser));
        apiBase.addHeader("Authorization","Bearer ddd19ff8b9c103063c5a7dd89701e5544645b675aa9b9ea98252d5aabb561766");
        apiBase.addHeader("Content-Type","application/json");
        Response response = apiBase.makeAPICall(EndPoints.CREATE_EMPLOYEE, HTTPMethod.POST);
        context.setAttribute("userId",Integer.toString(response.getBody().jsonPath().get("id")));
        assertFactory.check(response)
                .isCreated("Response code is not 201")
                .done();
    }

    @Test(groups = {"REGRESSION","UPDATE"},priority = 2)
    public void updateAPICall(ITestContext context)
    {
        AssertFactory assertFactory = new AssertFactory();
        APIBase apiBase = new APIBase();
        CreateUser createUser = new CreateUser();
        createUser.setName(fakeData.name().fullName());
        createUser.setEmail(fakeData.internet().safeEmailAddress());
        boolean gender = fakeData.random().nextBoolean();
        if(gender)
        {createUser.setGender("male");}else{createUser.setGender("female");}
        createUser.setStatus("active");

        apiBase.setBody(PojoUtilities.generateBodyJson(createUser));

        apiBase.addHeader("Authorization","Bearer ddd19ff8b9c103063c5a7dd89701e5544645b675aa9b9ea98252d5aabb561766");
        apiBase.addHeader("Content-Type","application/json");
        Response response = apiBase.makeAPICall(EndPoints.UPDATE_EMPLOYEE.replace("{id}", (CharSequence) context.getAttribute("userId")), HTTPMethod.PUT);
        assertFactory.check(response)
                .isSuccess("Response code is not 200")
                .done();
    }

    @Test(groups = {"REGRESSION","DELETE"},priority = 4)
    public void deleteAPICall(ITestContext context)
    {
        AssertFactory assertFactory = new AssertFactory();
        APIBase apiBase = new APIBase();
        apiBase.addHeader("Authorization","Bearer ddd19ff8b9c103063c5a7dd89701e5544645b675aa9b9ea98252d5aabb561766");
        apiBase.addHeader("Content-Type","application/json");
        Response response = apiBase.makeAPICall(EndPoints.DELETE_EMPLOYEE.replace("{id}", (CharSequence)context.getAttribute("userId")), HTTPMethod.DELETE);

        assertFactory.check(response)
                .isNoContent("Response code is not 204")
                .done();
    }
}
