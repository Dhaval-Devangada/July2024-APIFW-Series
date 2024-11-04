package com.qa.api.contacts.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.ContactsCredentials;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactsAPITest extends BaseTest {

    private String tokenId;

    @BeforeMethod
    public void getToken() {
        ContactsCredentials credentials = ContactsCredentials.builder()
                .email("dhavu.devangada@gmail.com")
                .password("Happy@2024")
                .build();
        Response response = restClient.post("/users/login", credentials, null, null, AuthType.NO_AUTH, ContentType.JSON);
        tokenId = response.jsonPath().getString("token");
        System.out.println("Token ID ====>" + tokenId);
        ConfigManager.set("contacts_bearer_Token", tokenId);
    }

    @Test
    public void getContactsTest() {
        Response response = restClient.get("/contacts", null, null, AuthType.CONTACTS_BEARER_TOKEN, ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
