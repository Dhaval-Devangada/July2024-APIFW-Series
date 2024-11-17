package com.qa.api.amadeus.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class AmadeusAPITests extends BaseTest {

    @Test
    public void getFlightDetailsTest(){
        Map<String,String> queryParams= Map.of("origin","PAR","maxPrice","200");
        Response response = restClient.get(BASE_URL_AMADEUS, "/v1/shopping/flight-destinations", queryParams, null, AuthType.OAUTH2, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(),200);
    }


}