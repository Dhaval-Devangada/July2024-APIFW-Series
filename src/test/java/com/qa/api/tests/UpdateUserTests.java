package com.qa.api.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUserTests extends BaseTest {

    @Test
    public void updateUserWithBuilderTest() {

        //1.POST: Create a user
        User user = User.builder()
                .name("Kavya")
                .email(StringUtility.getRandomEmailId())
                .status("active")
                .gender("female")
                .build();

        Response response = restClient.post(BASE_URL_GOREST,"public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 201);

        // To verify response we need to fetch the USERID
        String userId = response.jsonPath().getString("id");
        System.out.println("user id====>" + userId);

        //2.GET:Fetch the same user id
        Response responseGet = restClient.get(BASE_URL_GOREST,"public/v2/users/" + userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(responseGet.getStatusCode(), 200);
        Assert.assertEquals(responseGet.jsonPath().getString("id"), userId);

        // update the user details using the setters
        user.setStatus("inactive");
        user.setGender("male");

        //3. PUT: Update the same user using the same user id
        Response responsePUT = restClient.put(BASE_URL_GOREST,"public/v2/users/"+userId, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(responsePUT.getStatusCode(), 200);
        Assert.assertEquals(responsePUT.jsonPath().getString("id"), userId);
        Assert.assertEquals(responsePUT.jsonPath().getString("status"), user.getStatus());
        Assert.assertEquals(responsePUT.jsonPath().getString("gender"), user.getGender());

    }
}
