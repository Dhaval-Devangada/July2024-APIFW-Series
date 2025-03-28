package com.qa.api.mocking;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

/**
 * Here we will start and stop the Wiremock dummy server
 * Our dummy API will be hosted here
 */
public class WireMockSetup {

    public static WireMockServer server;
    public static void createWireMockServer(){
        server = new WireMockServer(9091);
        WireMock.configureFor("localhost",9091); //host means if we are running this on any other machine than we need to give IP address of that machine
        server.start(); //dummy server will start
    }

    public static void stopMockServer(){
        server.stop();
    }
}

//Mocks and behaviour both are same thing.
// What is behaviour of the API/What exactly it will respond/request.
