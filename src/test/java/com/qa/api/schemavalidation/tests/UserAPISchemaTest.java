package com.qa.api.schemavalidation.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtility;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserAPISchemaTest extends BaseTest {

    @Test
    public void userAPISchemaTest(){
//        RestAssured.given()
//                .baseUri("https://gorest.co.in")
//                .when()
//                .get("/public/v2/users/7527404")
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/user-schema.json")); // Whenever we are using this particular method, InClassPath means it(class) should be part of either src/main/resources or src/test/resources

        //POST:
        User user = User.builder()
                .name("Kavya")
                .email(StringUtility.getRandomEmailId())
                .status("inactive")
                .gender("female")
                .build();

        Response response = restClient.post(BASE_URL_GOREST,"public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 201);

        // To verify response we need to fetch the USERID
        String userId = response.jsonPath().getString("id");
        System.out.println("user id====>" + userId);

        Response responseGet = restClient.get(BASE_URL_GOREST, "/public/v2/users/" + userId, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
        Assert.assertTrue(SchemaValidator.validateSchema(responseGet,"schema/user-schema.json"));

    }
}
