package com.qa.api.base;

import com.qa.api.client.RestClient;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

/**
 * We will nor write any @Test annotations here. Here we will write preconditions/ pre-requeties
 */
public class BaseTest {

    // We can use "BaseTest" class to maintain our constant as well and we can create constants in AppConstant as well
    //***************App Base URLs ******************//
    protected final static String BASE_URL_REQ_RES = "https://reqres.in";
    protected final static String BASE_URL_PRODUCT = "https://fakestoreapi.com";
    protected final static String BASE_URL_GOREST = "https://gorest.co.in";
    protected final static String BASE_URL_CONTACTS = "https://thinking-tester-contact-list.herokuapp.com";
    protected final static String BASE_URL_CIRCUIT = "https://ergast.com";
    protected final static String BASE_URL_BASIC_AUTH = "https://the-internet.herokuapp.com";
    protected final static String BASE_URL_AMADEUS = "https://test.api.amadeus.com";

    //***************App EndPoints ******************//


    protected RestClient restClient; // Will be used only in the class which extend this class

//    @Parameters({"baseUrl"})   //(These parameters helps us to read those parameters which we are reading from testng.xml file and if we are writing the parameter we need to supply the parameters)
    @BeforeTest
    public void setUp(){ //@Optional String baseUrl - Optional annotation is coming from testng
//        if(baseUrl!=null){
//            ConfigManager.set("baseUrl",baseUrl); // It will not write it fo you in the config.properties file but it will update in the memory
//        }
        RestAssured.filters(new AllureRestAssured());  // Allure is an kind of web server. It will host the all the html and json files there. - No need to add any listener for Allure report
        restClient = new RestClient();
    }
}
