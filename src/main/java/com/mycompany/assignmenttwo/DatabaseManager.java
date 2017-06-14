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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author eeu43f
 */
public class DatabaseManager {
    
    
    private static String url;
    private static String username;
    private static String password;    
    String command;
    /**
     * Creates a input stream to communicate with database
     */
    public DatabaseManager()
    {
            try
            {
                InputStream stream = DatabaseManager.class.getResourceAsStream("/database.properties");
                SimpleDataSource.init(stream);
            }
            catch(Exception e){}
    }
    
    /**
     * gets connection between class and database
     * @return drivers necessary for interacting with database
     * @throws SQLException exception that can occur due to SQL error
     */
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url,
            username, password);
    }

    
    /**
     * All actions available on the student table
     * @param id - id of the student
     * @param name - name of the student
     * @param degreeScheme - degree student is studying
     * @param action - action requested to be done on student table
     * @throws SQLException - exception that may occur due to SQL error
     */
    public void student(String id, String name, String degreeScheme, String action) throws SQLException
    {
        Connection conn = SimpleDataSource.getConnection();
        Statement st = conn.createStatement();
        try
        {
            if(action.equals("add"))
            {
                command = "INSERT INTO `student`(`student_id`, `student_name`,`degree_scheme`) VALUES('" +id + "', '" + name + "','" + degreeScheme+"')";
                st.execute(command);
            }
            
            if(action.equals("delete"))
            {
                
                command = "DELETE FROM `student` WHERE `student_id` = '"+id +"'";
                st.execute(command);
            }
            
            if(action.equals("update"))
            {
                command = "UPDATE `student` SET `student_name` ='" + name + "', `degree_scheme` ='" + degreeScheme +
                        "' WHERE `student_id` = '" + id + "'";
                st.execute(command);
            }
        }
        finally
        {
            System.out.println(action + " completed");
        }
    }
    
    /**
     * All actions available on the module table
     * @param id - id of the module
     * @param name - name of the module
     * @param credits - value of module
     * @param action - action requested to be done on module table
     * @throws SQLException - exception that may occur due to SQL error
     */
    public void module(String id,  String name, String credits, String action) throws SQLException
    {
        Connection conn = SimpleDataSource.getConnection();
        Statement st = conn.createStatement();
        try
        {
            if(action.equals("add"))
            {
                command = "INSERT INTO `module`(`module_id`,`module_name`,`credits`) VALUES('" + id + "','" + name + "','" + credits + "')";
            }   st.execute(command);
            
            if(action.equals("delete"))
            {
                command = "DELETE FROM `module` WHERE `module_id` = '" + id + ";";
                st.execute(command);
            }
            if(action.equals("update"))
            {
                command = "UPDATE `module` SET `module_name` ="  + name + ", `credits`  ='" + credits +
                        "' WHERE `module_id` = '"  + id + "'";
                st.execute(command);
            }
        }
        finally{}
    }
    
    /**
     * All actions available on the staff table
     * @param id - id of the staff
     * @param name - name of the staff
     * @param grade - title of the staff
     * @param action - action requested to be done on staff table
     * @throws SQLException - exception that may occur due to SQL error
     */
    public void staff(String id, String name, String grade, String action) throws SQLException
    {
        Connection conn = SimpleDataSource.getConnection();
        Statement st = conn.createStatement();
        
        try
        {
            if(action.equals("add"))
            {
                command = "INSERT INTO `staff`(`staff_id`,`staff_name`,`grade`)  VALUES('" + id + "','" + name +"','" + grade +"')";
                st.execute(command);
            }
            if(action.equals("delete"))
            {
                command = "DELETE FROM `staff` WHERE `staff_id` = '" + id + "'";
                st.execute(command);
            }
            if(action.equals("update"))
            {
                command = "UPDATE `staff` SET `staff_name` ='" + name +  "', `grade`  = '" + grade +
                        "' WHERE `staff_id` = '" + id + "'";
                st.execute(command);
            }
            
        }
        finally{}
       
    }
    /**
     * All actions available on the registered table
     * @param studentID - id of the student
     * @param moduleID - id of the module
     * @param action - action requested to be done on registered table
     * @throws SQLException - exception that may occur due to SQL error
     */
    public void registered(String studentID, String moduleID, String action) throws SQLException
    {
        Connection conn = SimpleDataSource.getConnection();
        Statement st = conn.createStatement();
    
        System.out.println("yoyoyoyoyoyo");
        if(action.equals("add"))
        {
            command = "INSERT INTO `registered`(`student_id`,`module_id`) VALUES('" + studentID + "','" + moduleID + "')";
            st.execute(command);     
        }
        
        if(action.equals("delete"))
        {
            command = "DELETE FROM `registered` WHERE `student_id` = '" + studentID + "' AND `module_ID` = '" + moduleID + "'";
            st.execute(command);   
        }   
    }
    
    /**
     * All actions available on the teaches table
     * @param staffID - id of the staff
     * @param moduleID - id of the module
     * @param action - action requested to be done on teaches table
     * @throws SQLException - exception that may occur due to SQL error
     */
    public void teaches(String staffID, String moduleID, String action) throws SQLException
    {
        Connection conn = SimpleDataSource.getConnection();
        Statement st = conn.createStatement();
        
        if(action.equals("add"))
        {
            command = "INSERT INTO `teaches`(`staff_id`,`module_id`) VALUES('" + staffID + "','" + moduleID + "')";
            st.execute(command);     
        }
        
        if(action.equals("delete"))
        {
            command = "DELETE FROM `teaches` WHERE `staff_id` = '" + staffID + "' AND `module_ID` = '" + moduleID + "'";
            st.execute(command);
        }
    }
    
    /**
     * allows the printing of lists
     * @param type - the table that the user wants information from
     * @throws SQLException - an SQL error that can occur
     */
    public void list(String type) throws SQLException
    {
        String table = "";
        String getVariable1 ="";
        String getVariable2 ="";
        String getVariable3 ="";
        Connection conn = SimpleDataSource.getConnection();
        Statement st = conn.createStatement();   
        
        if(type.equals("student"))
        {
            table = "student";
            getVariable1 = "student_id";
            getVariable2 = "student_name";
            getVariable3 = "degree_scheme";
        }
        
        if(type.equals("module"))
        {
            table = "module";
            getVariable1 = "module_id";
            getVariable2 = "module_name";
            getVariable3 = "credits";
        }
        
        if(type.equals("staff"))
        {
            table = "staff";
            getVariable1 = "staff_id";
            getVariable2 = "staff_name";
            getVariable3 = "grade";
        }
        
        if(type.equals("registered"))
        {
            table = "registered";
            getVariable1 = "student_id";
            getVariable2 = "module_id";
        }
        
        if(type.equals("teaches"))
        {
            table = "teaches";
            getVariable1 = "staff_id";
            getVariable2 = "module_id";
        }
        
        //if one of the three tables that require 3 variables
        if(type.equals("student") || type.equals("module") || type.equals("staff"))
        {
            ResultSet rs = st.executeQuery("SELECT * FROM `" + table  + "`");

            System.out.println(getVariable1 + "\t" + getVariable2 + "\t \t" + getVariable3);
            System.out.println("*******************************************************");
            while (rs.next()) 
            {
                System.out.println(rs.getString(getVariable1) + "\t \t" + rs.getString(getVariable2) +"\t \t" + rs.getString(getVariable3));
            }
        }
        
        else
        {
            ResultSet rs = st.executeQuery("SELECT * FROM `" + table  + "`");

            System.out.println(getVariable1 + "\t" + getVariable2);
            System.out.println("**********************");
            while (rs.next()) 
            {
                System.out.println(rs.getString(getVariable1) + "\t \t" + rs.getString(getVariable2));
            }
        }
    }
    
    /**
     * allows printing of the specialised reports available
     * @param report - the report in question
     * @param input - the name of the thing they're checking against
     * @throws SQLException - SQL error that can occur
     */
    public void reports(int report, String input) throws SQLException
    {
        Connection conn = SimpleDataSource.getConnection();
        Statement st = conn.createStatement();   
        
        if(report == 1)
        {
            command =("SELECT `module`.`module_id`, `module`.`module_name` FROM `module`" + 
                "INNER JOIN `teaches` ON `module`.`module_id` = `teaches`.`module_id` INNER JOIN `staff`" +
                "ON `teaches`.`staff_id` = `staff`.`staff_id` WHERE `staff`.`staff_name` = '" + input +"'"); 
            ResultSet rs = st.executeQuery(command);
            System.out.println("module ID" + "\t" + "name");
            System.out.println("**********************");
            while (rs.next()) 
            {
                System.out.println(rs.getString("module_id") + "\t \t" + rs.getString("module_name"));
            }
        }
        if(report == 2)
        {
            command =("SELECT `student`.`student_id`,`student`.`student_name` FROM `student`" + 
                    "INNER JOIN `registered` ON  `student`.`student_id`=`registered`.`student_id`" +
                    "WHERE `module_id` = '" + input +"'");
            System.out.println(command);
            ResultSet rs = st.executeQuery(command);
            System.out.println("student ID" + "\t" + "student name");
            System.out.println("******************************");
            while (rs.next()) 
            {
                System.out.println(rs.getString("student_id") + "\t \t" + rs.getString("student_name"));
            }
        }
        
        if(report == 3)
        {
            command = ("SELECT `staff`.`staff_id`, `staff`.`staff_name`, `teaches`.`module_id`" +
                    "FROM `staff` INNER JOIN `teaches` ON `staff`.`staff_id` = `teaches`.`staff_id`" +
                    "INNER JOIN `registered` ON `teaches`.`module_id` = `registered`.`module_id`" +
                    "INNER JOIN `student` ON `registered`.`student_id` = `student`.`student_id`" +
                    "WHERE `student`.`student_name` = '" + input + "'");
            System.out.println(command);
            ResultSet rs = st.executeQuery(command);
            System.out.println("staff id" + "\t" + "staff name" + "\t" + "module id");
            System.out.println("***************************************************");
            while (rs.next()) 
            {
                System.out.println(rs.getString("staff_id") + "\t" + rs.getString("staff_name") + "\t" + rs.getString("module_id"));
            }
        }
        
        if(report == 4)
        {
            command = ("SELECT `staff`.`staff_id`, `staff`.`staff_name` FROM `staff`INNER JOIN `teaches`" +
                       "ON `staff`.`staff_id` = `teaches`.`staff_id`GROUP BY `teaches`.`staff_id`" +
                        "HAVING COUNT(`teaches`.`module_id`) > 1");
            ResultSet rs = st.executeQuery(command);
            System.out.println("Staff id" + "\t" + "staff name");
            System.out.println("******************************");
            
            while (rs.next()) 
            {
                System.out.println(rs.getString("staff_id") + "\t \t" + rs.getString("staff_name"));
            }
        }
    }
}
