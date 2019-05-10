package com.yusx.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    public static Properties readeProperties(String name){
        InputStream resourceAsStream = PropertiesReader.class.getClassLoader().getResourceAsStream(name);
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
