package com.qa.api.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;


public class CreateUserTest extends BaseTest {

    @Test
    public void createUserTest() {
        User user = new User(null,"dhaval", StringUtility.getRandomEmailId(), "male", "active");
        Response response = restClient.post(BASE_URL_GOREST,"public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test
    public void createUserWithBuilderTest() {

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

        //GET:(TO verify that user has been created successfully)
        Response responseGet = restClient.get(BASE_URL_GOREST,"public/v2/users/" + userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(responseGet.getStatusCode(), 200);
        Assert.assertEquals(responseGet.jsonPath().getString("id"), userId);
        Assert.assertEquals(responseGet.jsonPath().getString("name"), user.getName());
        Assert.assertEquals(responseGet.jsonPath().getString("email"), user.getEmail());
    }

    @Test(enabled = false)
    public void createUserUsingJsonFileTest() {
        File userJsonFile = new File("./src/test/resources/jsons/user.json");
        Response response = restClient.post(BASE_URL_GOREST,"public/v2/users", userJsonFile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 201);
    }

}
