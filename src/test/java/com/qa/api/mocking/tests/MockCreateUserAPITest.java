package com.qa.api.mocking.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class MockCreateUserAPITest extends BaseTest {

    @Test
    public void createDummyUserTest(){
        APIMocks.createDummyUser();

        String dummyJson = "{\"name\":\"Dhaval\"}";

        Response response = restClient.post(BASE_URL_LOCALHOST_PORT, "api/users", dummyJson, null, null, AuthType.NO_AUTH, ContentType.JSON);
        response.then()
                .assertThat()
                .statusCode(201)
                .statusLine(equalTo("HTTP/1.1 201 user is created"))
                .body("id",equalTo(1));
    }

    @Test
    public void createDummyUserWithJsonFileTest(){
        APIMocks.createDummyUserWithJsonFile();

        String dummyJson = "{\"name\":\"Sheela\"}";

        Response response = restClient.post(BASE_URL_LOCALHOST_PORT, "api/users", dummyJson, null, null, AuthType.NO_AUTH, ContentType.JSON);
        response.then()
                .assertThat()
                .statusCode(201)
                .statusLine(equalTo("HTTP/1.1 201 user is created"))
                .body("id",equalTo(101)); // add id in the user.json file
    }


}
