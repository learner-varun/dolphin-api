package core.base;

import core.enums.LogLevel;
import core.listners.APIResponseFilter;
import core.logger.LoggerFactory;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static core.configs.TestConfigs.*;
import static io.restassured.RestAssured.given;

public class TestEngine {
    private static RequestSpecBuilder requestSpecBuilder;
    LoggerFactory loggerFactory = new LoggerFactory(TestEngine.class);
    private RequestSpecification requestSpecification;

    public RequestSpecification testBuilder() {
        setBaseUrl();
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecification = requestSpecBuilder.build();
        DEFAULT_HEADERS.forEach((key, value) -> requestSpecBuilder.addHeader(key, value));
        return given().spec(requestSpecification).log().all().filter(new APIResponseFilter());
    }

    private void setBaseUrl() {
        if (PORT_NO.isEmpty()) {
            RestAssured.baseURI = PROTOCOL + "://" + DOMAIN_NAME;
        } else {
            RestAssured.baseURI = PROTOCOL + "://" + DOMAIN_NAME + ":" + PORT_NO;
        }
        loggerFactory.logMessage(LogLevel.INFO, "URL SETT : " + PROTOCOL + "://" + DOMAIN_NAME + ":" + PORT_NO);
    }
}
