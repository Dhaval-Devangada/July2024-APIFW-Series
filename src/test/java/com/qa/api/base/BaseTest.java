package com.qa.api.base;

import com.qa.api.client.RestClient;
import com.qa.api.manager.ConfigManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * We will nor write any @Test annotations here. Here we will write preconditions/ pre-requeties
 */
public class BaseTest {

    protected RestClient restClient; // Will be used only in the class which extend this class

    @Parameters({"baseUrl"})   //(These parameters helps us to read those parameters which we are reading from testng.xml file and if we are writing the parameter we need to supply the parameters)
    @BeforeTest
    public void setUp(@Optional String baseUrl){ //Optional annotation is coming from testng
        if(baseUrl!=null){
            ConfigManager.set("baseUrl",baseUrl); // It will not write it fo you in the config.properties file but it will update in the memory
        }
        restClient = new RestClient();
    }
}
