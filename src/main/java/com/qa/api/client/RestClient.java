package com.qa.api.client;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.FrameworkException;
import com.qa.api.manager.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.expect;

import java.io.File;
import java.util.Map;

/**
 * Here we will create all the generic methods. We will create wrapper methods here
 * We can overload get,post... methods here
 */
public class RestClient {

    //define Response spec
    private ResponseSpecification responseSpec200 = expect().statusCode(200);
    private ResponseSpecification responseSpec201 = expect().statusCode(201);
    private ResponseSpecification responseSpec204 = expect().statusCode(204);
    private ResponseSpecification responseSpec400 = expect().statusCode(400);
    private ResponseSpecification responseSpec401 = expect().statusCode(401);
    private ResponseSpecification responseSpec404 = expect().statusCode(404);
    private ResponseSpecification responseSpec422 = expect().statusCode(422);
    private ResponseSpecification responseSpec500 = expect().statusCode(500);


    private String baseUrl = ConfigManager.get("baseUrl");

    private RequestSpecification setupRequest(AuthType authType, ContentType contentType) {

        RequestSpecification request = RestAssured.given().log().all()
                .baseUri(baseUrl)
                .contentType(contentType)
                .accept(contentType);

        switch (authType) {
            case BEARER_TOKEN:
                request.header("Authorization", "Bearer " + ConfigManager.get("bearerToken"));
                break;
            case OAUTH2:
                request.header("Authorization", "Bearer " + generateOAuth2Token());
                break;
            case BASIC_AUTH:
                request.header("Authorization", "Basic ");
                break;
            case API_KEY:
                request.header("x-api-key", ConfigManager.get("apiKey"));
                break;
            case NO_AUTH:
                System.out.println("Auth is not required...");
                break;
            default:
                System.out.println("This auth is not supported...Please pass the right AuthType...");
                throw new FrameworkException("NO AUTH SUPPORTED");
        }

        return request;
    }

    private String generateOAuth2Token() {
        return RestAssured.given()
                .formParam("client_id", ConfigManager.get("clientId"))
                .formParam("client_secret", ConfigManager.get("clientSecret"))
                .formParam("grant_type", ConfigManager.get("grantType"))
                .post(ConfigManager.get("tokenUrl"))
                .then()
                .extract()
                .path("access_token");
    }

    //************************** CRUD Methods **************************//
    // These methods are public because it will be consumed by testng tests
    //Every CRUD operation will return the response
    // FRom here we will return and Testng will validate that response is correctly coming ot not

    // This method is only responsible for calling the API and giving the response thats it (This is only single responsibility principle)

    /**
     * This method is used to call the GET APIs
     *
     * @param endPoint
     * @param queryParams
     * @param pathParams
     * @param authType
     * @param contentType
     * @return it returns the get api response
     */
    public Response get(String endPoint, Map<String, String> queryParams,
                        Map<String, String> pathParams,
                        AuthType authType, ContentType contentType) {

        RequestSpecification requestSpecification = setupAuthAndContentType(authType, contentType);

        applyParams(requestSpecification, queryParams, pathParams);

        Response response = requestSpecification.get(endPoint).then().spec(responseSpec200).extract().response();
        response.prettyPrint();
        return response;
    }

    /**
     * This method is used to call POST api
     *
     * @param endPoint
     * @param body
     * @param queryParams
     * @param pathParams
     * @param authType
     * @param contentType
     * @param <T>
     * @return it returns the POST api response
     */
    public <T> Response post(String endPoint,
                             T body, //(we can pass pojo, string anything )
                             Map<String, String> queryParams,
                             Map<String, String> pathParams,
                             AuthType authType, ContentType contentType) {

        RequestSpecification requestSpecification = setupAuthAndContentType(authType, contentType);

        applyParams(requestSpecification, queryParams, pathParams);

        Response response = requestSpecification.body(body).post(endPoint).then().spec(responseSpec201).extract().response();
        response.prettyPrint();
        return response;
        // T menas anyType, I will give you any kind of object
    }

    /**
     * This method is used to call the POST API for the JSON file Body
     *
     * @param endPoint
     * @param file
     * @param queryParams
     * @param pathParams
     * @param authType
     * @param contentType
     * @return
     */
    public Response post(String endPoint,
                         File file, //(we can pass pojo, string anything )
                         Map<String, String> queryParams,
                         Map<String, String> pathParams,
                         AuthType authType, ContentType contentType) {

        RequestSpecification requestSpecification = setupAuthAndContentType(authType, contentType);

        applyParams(requestSpecification, queryParams, pathParams);

        Response response = requestSpecification.body(file).post(endPoint).then().spec(responseSpec201).extract().response();
        response.prettyPrint();
        return response;
        // T menas anyType, I will give you any kind of object
    }

    private RequestSpecification setupAuthAndContentType(AuthType authType, ContentType contentType) {
        return setupRequest(authType, contentType);
    }

    private void applyParams(RequestSpecification requestSpecification, Map<String, String> queryParams, Map<String, String> pathParams) {
        if (queryParams != null) {
            requestSpecification.queryParams(queryParams);
        }

        if (pathParams != null) {
            requestSpecification.pathParams(pathParams);
        }
    }


}
