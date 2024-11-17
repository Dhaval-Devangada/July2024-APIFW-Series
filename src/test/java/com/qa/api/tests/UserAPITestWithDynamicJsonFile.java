package com.qa.api.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.StringUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserAPITestWithDynamicJsonFile extends BaseTest {

    @Test
    public void createUserWithJsonFileTest(){

        String jsonFilePath ="src/test/resources/jsons/user.json";

        //we first need to read this user.json file using jkson api/objectmapper
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode userNode=mapper.readTree(Files.readAllBytes(Paths.get(jsonFilePath))); //JsonNode means, It will capture complete text from our json file

            //Create new unique email id
            String uniqueEmail = StringUtility.getRandomEmailId();

            //Convert the userNode to ObjectNode
            //update the email id
            ObjectNode objectNode = (ObjectNode) userNode;
            //This ObjectNode will help us to update the JSON content. Whenever want to update any JSON content we use ObjectNode
            objectNode.put("email",uniqueEmail);

            //POST call does not understand what is object node, so we need to convert object node/JSON node to JSON String: With the help of object mapper
            String updatedJsonString=mapper.writeValueAsString(userNode);
            System.out.println("updated json string===>"+updatedJsonString);

            Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users", updatedJsonString, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
            Assert.assertEquals(response.getStatusCode(),201);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
