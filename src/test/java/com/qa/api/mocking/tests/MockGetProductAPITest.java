package com.qa.api.mocking.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;


public class MockGetProductAPITest extends BaseTest {

    @Test
    public void getDummyProductsWithJsonFileTest(){
        APIMocks.getDummyProductsWithJsonFile();
        Response response = restClient.get(BASE_URL_LOCALHOST_PORT, "/api/products", null, null, AuthType.NO_AUTH, ContentType.ANY);
        response.then()
                .assertThat()
                .statusCode(200);
    }
}
