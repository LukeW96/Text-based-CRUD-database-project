/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assignmenttwo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author eeu43f
 */
public class mySQLTester {
     public static void main(String[] args) throws IOException,
            ClassNotFoundException, SQLException 
    {
        // This is an amended version of Horstmann’s test program.
        
        InputStream stream = mySQLTester.class.getResourceAsStream("/database.properties");
                
        SimpleDataSource.init(stream);
        Connection conn = SimpleDataSource.getConnection();

        Statement st = conn.createStatement();
        try 
        {
            st.execute(
                    "CREATE TABLE IF NOT EXISTS "
                    + "accounts(balance DECIMAL(5, 2))");
            st.execute("INSERT INTO accounts VALUES (999.99)");
            st.execute("INSERT INTO accounts VALUES (666.66)");

            ResultSet rs = st.executeQuery("SELECT * FROM accounts");

            while (rs.next()) 
            {
                System.out.println(rs.getString("balance"));
            }
            st.execute("DROP TABLE accounts");
        }
        finally
        {
            System.out.println("Table created and then dropped!");
            st.close();
            conn.close();
        }
    }
}
