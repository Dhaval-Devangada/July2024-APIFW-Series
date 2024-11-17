package com.qa.api.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;


public class CreateUserTest extends BaseTest {
    //Who will provide the parameterization approach -TestNg
    //We are parameterizing our testcases and removing hard code values from the tests
    //Most of the time we will be using the data provider in the POST call because we are creating the data/supply the data from our side
    @DataProvider
    public Object[][] getUserData(){
        //Data provider always return two dimensional  object array
        return new Object[][]{
                {"Dhaval","male","active"},
                {"Abhi","male","inactive"},
                {"Dhaval","male","active"}
        };
    }

    @Test(dataProvider = "getUserData") //We need to maintain the 3 holding parameters here. We can not change the sequence
    public void createUserTest(String name,String gender,String status) {
        User user = new User(null,name, StringUtility.getRandomEmailId(), gender, status);
        Response response = restClient.post(BASE_URL_GOREST,"public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test(dataProvider = "getUserData")
    public void createUserWithBuilderTest(String name,String gender,String status) {

        //POST:
        User user = User.builder()
                .name(name)
                .email(StringUtility.getRandomEmailId())
                .status(status)
                .gender(gender)
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
