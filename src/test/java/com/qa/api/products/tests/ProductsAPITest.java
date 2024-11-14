package com.qa.api.products.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductsAPITest extends BaseTest {

    @Test
    public void getProductsAPITest(){
        Response response = restClient.get(BASE_URL_PRODUCT,"/products", null, null, AuthType.NO_AUTH, ContentType.JSON);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void getProductsAPILimitTest(){
        Map<String, String> queryParam = Map.of("limit", "5");
        Response response = restClient.get(BASE_URL_PRODUCT,"/products", queryParam, null, AuthType.NO_AUTH, ContentType.JSON);
        Assert.assertEquals(response.statusCode(),200);
    }
}
