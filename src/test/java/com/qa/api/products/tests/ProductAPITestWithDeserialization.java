package com.qa.api.products.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.JsonUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ProductAPITestWithDeserialization extends BaseTest {

    @Test
    public void getProductsTest(){
        Response response = restClient.get("/products", null, null, AuthType.NO_AUTH, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(),200);
        Product[] product = JsonUtils.deserialize(response, Product[].class);

        System.out.println(Arrays.toString(product));

        for (Product p:product) {
            System.out.println("ID : " + p.getId());
            System.out.println("Title : " + p.getTitle());
            System.out.println("Price : " + p.getPrice());
            System.out.println("Rate : " + p.getRating().getRate());
            System.out.println("Count : " + p.getRating().getCount());
            System.out.println("------------------");
        }
    }
}
