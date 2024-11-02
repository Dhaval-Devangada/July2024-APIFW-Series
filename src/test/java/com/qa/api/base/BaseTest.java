package com.qa.api.base;

import com.qa.api.client.RestClient;
import org.testng.annotations.BeforeMethod;

/**
 * We will nor write any @Test annotations here. Here we will write preconditions/ pre-requeties
 */
public class BaseTest {

    protected RestClient restClient; // Will be used only in the class which extend this class

    @BeforeMethod
    public void setUp(){
        restClient = new RestClient();
    }
}
