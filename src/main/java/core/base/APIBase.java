package core.base;

import core.enums.HTTPMethod;
import core.listners.APIResponseFilter;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIBase {

    private RequestSpecBuilder requestSpecBuilder;
    private RequestSpecification requestSpecifications;

    public APIBase()
    {
        requestSpecBuilder = new RequestSpecBuilder();
    }

    public static void setBaseURL(String baseURL)
    {
        RestAssured.baseURI=baseURL;
    }

    public void addHeader(String key,String value)
    {
        requestSpecBuilder.addHeader(key,value);
    }

    public void addHeaders(Map<String,String> headers)
    {
        requestSpecBuilder.addHeaders(headers);
    }

    public void updateHeader(String key,String value)
    {
        requestSpecBuilder.addHeader(key,value);
    }

    public void deleteHeader()
    {

    }

    public void setBody(String body)
    {
       requestSpecBuilder.setBody(body);
    }

    public static void addGlobalHeaders(Map<String,String> headerList)
    {
        RestAssured.requestSpecification = new RequestSpecBuilder()
            .build().headers(headerList);
    }

    public static void addGlobalHeader(String key,String value)
    {
        RestAssured.requestSpecification.header(key,value);
    }

public Response makeAPICall(String APIEndPoint , HTTPMethod method)
{
    requestSpecifications = requestSpecBuilder.build();
    Response response = null;
    switch (method)
    {
        case GET:
            response= given().spec(requestSpecifications).log().all().filter(new APIResponseFilter()).get(APIEndPoint);
            break;
        case PUT:
            response= given().spec(requestSpecifications).log().all().filter(new APIResponseFilter()).put(APIEndPoint);
            break;
        case POST:
            response= given().spec(requestSpecifications).log().all().filter(new APIResponseFilter()).post(APIEndPoint);
            break;
        case DELETE:
            response= given().spec(requestSpecifications).log().all().filter(new APIResponseFilter()).delete(APIEndPoint);
    }
    return response;
}

}
