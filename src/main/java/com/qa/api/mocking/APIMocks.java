package com.qa.api.mocking;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;


public class APIMocks {
    /**
     * API with GET call
     * Give me some response with 200 status code
     * Content should be application/json
     * <p>
     * These mocks are also called stubs
     */

    //***************************** Create stub/mock for get CALLs *****************************//
    public static void getDummyUser() {
        //There are n numbers of static methods are available inside the wiremock library
        //For the static imports we need to do static import

        //What kind of url you want to create fo your dummy APIS. We have already set up "base URL" using local host. Noew here we need to set end pooint
        //http://localhost:8089/api/users
        stubFor(get(urlEqualTo("/api/users"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "    \"name\": \"Dhaval\"\n" +
                                "}")
                ));
    }

    public static void getDummyUserWithJsonFile() {

        stubFor(get(urlEqualTo("/api/users"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("user.json")
                ));
    }
// .withBodyFile("/jsons/user.json") this will five us the error  (Expected status code (<200> or <404>) but was <500>.)
// If we want to pass the JSON from file then we need to create folder "__files" and under that folder we need to place our user.json file

    public static void getDummyUserWithQueryParams() {
        stubFor(get(urlPathEqualTo("/api/users"))
                .withQueryParam("name", equalTo("Tom"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("user.json"))
        );
    }

    public static void getDummyProductsWithJsonFile() {

        stubFor(get(urlEqualTo("/api/products"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("product.json")
                ));
    }

    //***************************** Create stub/mock for POST CALLs *****************************//

    // In the fake API it really dosen't matter that we supply the body or not
    public static void createDummyUser() {
        stubFor(post(urlEqualTo("/api/users"))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withStatusMessage("user is created")
                        .withBody("{\"id\":1,\"name\":\"Dhaval\"}")
                )
        );
    }

    public static void createDummyUserWithJsonFile() {
        stubFor(post(urlEqualTo("/api/users"))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withStatusMessage("user is created")
                        .withBodyFile("user.json")
                )
        );
    }

    //***************************** Create stub/mock for DELETE CALLs *****************************//
    //We are not writing any business logic in the mock APIs
    // Note: Response body is not coming in the response for DELETE mock call
    // body is not coming in the response - This feature is not enabled in the wire mock. That is why we have commented "withBody"

    public static void deleteDummyUser() {
        stubFor(delete(urlEqualTo("/api/users/1"))
                        .willReturn(aResponse()
                                        .withStatus(204)
                                        .withStatusMessage("USER DELETED")
                                        .withHeader("server", "NALServer")
//                        .withBody("Resource not found")

                        )
        );
    }
}
