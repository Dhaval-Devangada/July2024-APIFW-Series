package com.qa.api.mocking.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class MockGetUserAPITest extends BaseTest {
    /**
     * Get call mock test
     */
    @Test
    public void getDummyUserTest(){
        APIMocks.getDummyUser();
        Response response = restClient.get(BASE_URL_LOCALHOST_PORT, "/api/users", null, null, AuthType.NO_AUTH, ContentType.ANY);
        response.then()
                .assertThat()
                .statusCode(200)
                .body("name",equalTo("Dhaval"));
    }

    /**
     * JSON File Test
     */
    @Test
    public void getDummyUserWithJsonFileTest(){
        APIMocks.getDummyUserWithJsonFile();
        Response response = restClient.get(BASE_URL_LOCALHOST_PORT, "/api/users", null, null, AuthType.NO_AUTH, ContentType.ANY);
        response.then()
                .assertThat()
                .statusCode(200)
                .body("name",equalTo("Sheela"));
    }

    /**
     * Query Params Test
     */
    @Test
    public void getDummyUserWithQueryParamTest(){
        APIMocks.getDummyUserWithQueryParams();

        Map<String,String> queryParams=Map.of("name","Tom");
        Response response = restClient.get(BASE_URL_LOCALHOST_PORT, "/api/users", queryParams, null, AuthType.NO_AUTH, ContentType.ANY);
        response.then()
                .assertThat()
                .statusCode(200)
                .body("name",equalTo("Sheela"));
    }


}
