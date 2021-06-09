package com.example.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Props {
    private static String driver;
    private static String url;
    private static String user;
    private static String pass;

    static {
        try {
            String propPath =new File("").getAbsolutePath() + "\\jdbc\\src\\main\\resources\\jdbc.properties";
            FileReader fReader = new FileReader(propPath);

            Properties props = new Properties();
            props.load(fReader);

            driver = props.getProperty("jdbc.driver");
            url = props.getProperty("jdbc.url");
            user = props.getProperty("jdbc.user");
            pass = props.getProperty("jdbc.password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDriver() {
        return driver;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPass() {
        return pass;
    }



}
