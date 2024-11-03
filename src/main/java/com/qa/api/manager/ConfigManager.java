package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    // In API automation that you are creating/maintain static or non-static
    // This will help us to load the properties and help us to read the properties from config.properties
    private static Properties properties = new Properties();

    // static block - is a block , when we load/execute this particular class at that time this static block will get executed first
    // Even before the main method(if we have that in this class), this STATIC block will get executed first

    static {
        // Load this "ConfigManager class and get it's all resources as a stream". Here we are using the concept of reflection (We need to pass the path of "Configurations" file)
        // Reflection in java - At the run time if any class property you want to access
        //ConfigManager.class.getClassLoader(): This obtains the class loader that loaded the ConfigManager class
        //getResourceAsStream("config/config.properties"): This method searches for the resource (config/config.properties) in the classpath and returns an InputStream that can be used to read from the file.
        // getResourceAsStream() will go and check for all the resources and under that
        // Reflections means if we really want to get blue print of any class (how many methods,variables,constructors are there in that class)
        try(InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config/config.properties")) {
            // let's assume that this properties file could not be loaded

            if(input!=null){
                properties.load(input);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }

    public static void set(String key,String value){
         properties.setProperty(key, value);
    }

}
