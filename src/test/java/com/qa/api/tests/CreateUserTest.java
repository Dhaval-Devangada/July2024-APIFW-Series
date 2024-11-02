package com.qa.api.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;


public class CreateUserTest extends BaseTest {

    @Test
    public void createUserTest(){
        User user = new User("dhaval","dhaval@gmail.com","male","active");
        Response response = restClient.post("public/v2/users",user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(),201);
    }

    @Test
    public void createUserWithBuilderTest(){
        User user = User.builder()
                .name("Kavya")
                .email("kavya@gmail.com")
                .status("inactive")
                .gender("female")
                .build();

        Response response = restClient.post("public/v2/users",user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(),201);
    }

    @Test
    public void createUserUsingJsonFileTest(){
        File userJsonFile = new File("./src/test/resources/jsons/user.json");
        Response response = restClient.post("public/v2/users",userJsonFile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(),201);
    }

}
