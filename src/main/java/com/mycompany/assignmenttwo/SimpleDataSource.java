/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assignmenttwo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author eeu43f
 */
public class SimpleDataSource
{
    /**
     * Initializes the data source.
     *
     * @param fileName the name of the property file that contains the database
     * driver, url, username and password
     */
   
    /*
    public static void init(String fileName) throws IOException, ClassNotFoundException {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream(fileName);
        props.load(in);

        String driver = props.getProperty("jdbc.driver");
        url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        Class.forName(driver);
    }
    */
    
    /**
     * 
     * @param stream - the input stream
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static void init(InputStream stream) throws IOException, ClassNotFoundException 
    {
        Properties props = new Properties();
        props.load(stream);

        String driver = props.getProperty("jdbc.driver");
        url = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        password = props.getProperty("jdbc.password");

        Class.forName(driver);
    }

    /**
     * Gets a connection to the database.
     *
     * @return the database connection
     */
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url,
                username, password);
    }

    private static String url;
    private static String username;
    private static String password;    
}
