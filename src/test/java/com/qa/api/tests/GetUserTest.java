package com.qa.api.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest extends BaseTest {

    @Test
    public void getUsersTest(){

        Map<String,String> queryParams= new HashMap<>();
        queryParams.put("name","Dharani");
        queryParams.put("status","active");

        Response response = restClient.get("public/v2/users", queryParams, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void getSingleUserTest(){
        Response response = restClient.get("public/v2/users/7501222", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(),200);
    }


}
