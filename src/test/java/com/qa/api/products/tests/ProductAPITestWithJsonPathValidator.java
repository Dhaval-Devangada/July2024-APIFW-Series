package com.qa.api.products.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidator;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ProductAPITestWithJsonPathValidator extends BaseTest {

    @Test
    public void getProductTest(){
        Response response = restClient.get("/products", null, null, AuthType.NO_AUTH, ContentType.JSON);
        Assert.assertEquals(response.statusCode(),200);

        List<Number> prices=JsonPathValidator.readList(response,"$[?(@.price>50)].price");
        System.out.println(prices);

        List<Integer> ids=JsonPathValidator.readList(response,"$[?(@.price>50)].id");
        System.out.println(ids);

        List<String> titles=JsonPathValidator.readList(response,"$[?(@.price>50)].title");
        System.out.println(titles);

        List<Double> rates=JsonPathValidator.readList(response,"$[?(@.price>50)].rating.rate");
        System.out.println(rates);

        List<Integer> counts=JsonPathValidator.readList(response,"$[?(@.price>50)].rating.count");
        System.out.println(counts);

        //getMap
        List<Map<String,Object>> jewleryList = JsonPathValidator.readList(response, "$[?(@.category ==  'jewelery')].['title','price']");
        System.out.println(jewleryList.size());

        for (Map<String,Object> product: jewleryList){
            String title=(String)product.get("title");
            Number price=(Number)product.get("price");

            System.out.println("title : "+ title);
            System.out.println("price : "+ price);
            System.out.println("-------------------");
        }

        //get min price
        Double minPrice=JsonPathValidator.read(response,"min($[*].price)");
        System.out.println("min price: "+ minPrice);
    }
}
