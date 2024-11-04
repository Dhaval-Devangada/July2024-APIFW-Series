package com.qa.api.constants;

/**
 * Below is ENUM not a CLASS
 * Suppose we want to maintain all the Auth Types here/Browsers here
 */
public enum AuthType {
    //We can write enum without values and with values as well
    // In Rest assured we have one enum which is Content Type

    BEARER_TOKEN,
    CONTACTS_BEARER_TOKEN,
    OAUTH2,
    BASIC_AUTH,
    API_KEY,
    NO_AUTH

}
