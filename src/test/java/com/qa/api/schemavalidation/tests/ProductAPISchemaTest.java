package com.qa.api.schemavalidation.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.SchemaValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductAPISchemaTest extends BaseTest {

    @Test
    public void productsAPISchemaTest(){
//        RestAssured.given()
//                .baseUri("https://fakestoreapi.com")
//                .when()
//                .get("/products")
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/product-schema.json")); // Whenever we are using this particular method, InClassPath means it(class) should be part of either src/main/resources or src/test/resources

        Response response = restClient.get(BASE_URL_PRODUCT, "/products", null, null, AuthType.NO_AUTH, ContentType.ANY);
      Assert.assertTrue(SchemaValidator.validateSchema(response,"schema/product-schema.json"));


    }
}
