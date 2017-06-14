/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assignmenttwo;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eeu43f
 */
public class DatabaseTextInterface 
{
    private DatabaseManager db;
    private int userInput = 1; 
    private int subMenuInput;
    Scanner in;
    
    /**
     * Initialises scanner and database manager. Starts the program
     * by calling the main menu method
     */
    public DatabaseTextInterface()
    {
        
        db = new DatabaseManager();
        in = new Scanner(System.in);
        
        mainMenu();
    }
    
    /**
     * Main navigation page, here the user can choose to go into further
     * sub navigation pages
     */
    private void mainMenu()
    {
        
        while(userInput != 0)
        {
            System.out.println("Main Menu\n" +
                                "********************\n" +
                                "1. Students\n" +
                                "2. Modules\n" +
                                "3. staff\n" +
                                "4. Registered\n" +
                                "5. Teaches\n" + 
                                "6. Reports\n" +
                                "0. Quit");
            
            userInput = in.nextInt();
            
            switch(userInput)
            {
                case 1: subMenu("student"); break;
                case 2: subMenu("module"); break;
                case 3: subMenu("staff"); break;
                case 4: subMenu("registered"); break;
                case 5: subMenu("teaches"); break;
                case 6: reports(); break;
                
                default : System.out.println("Please input a valid number"); break;
            }
        }
    }
    
    /**
     * sub menu system, gives more in depth options for the user
     * @param type - the table that was chosen
     */
    private void subMenu(String type)
    {
        System.out.println("Sub-Menu ("+ type + "s)\n" +
                            "*******************\n" +
                            "1. Add " + type + "\n" +
                            "2. Remove " + type + "\n" +
                            "3. Update " + type + "\n" +
                            "4. List " + type + "\n" +
                            "0. Return to main menu");
        
        subMenuInput = in.nextInt();
        in.nextLine();
        if(subMenuInput == 0)
        {
            return;
        }
        
        switch(subMenuInput)
        {
            case 1: addEntity(type); break;
            case 2: deleteEntity(type); break;
            case 3: updateEntity(type); break;
            case 4: list(type);
            default: break;
        }
    }
    
    /**
     * Gathers information to send to database manager in regards to 
     * adding an entity to the database
     * @param type - the table wanting to create a tuple for
     */
    private void addEntity(String type)
    {
        String value1 = "";
        String value2 = "";
        String value3 = "";
        try
        {
            if(type.equals("registered"))
            {
                System.out.println("Enter student ID");
                value1 = in.nextLine();
                
                System.out.println("Enter module ID");
                value2 = in.nextLine();
            }
            
            else if(type.equals("teaches"))
            {
                System.out.println("Enter staff ID");
                value1 = in.nextLine();
                
                System.out.println("Enter module ID");
                value2 = in.nextLine();
            
            }
            
            else
            {
                System.out.println("Enter ID ");
                value1 = in.nextLine();
                System.out.println("Enter Name ");
                value2 = in.nextLine();

                if(type.equals("student" ))
                {
                    System.out.println("Enter Degree Scheme");
                    value3 = in.nextLine();
                }

                if(type.equals("staff"))
                {
                    System.out.println("Enter Grade");
                    value3 = in.nextLine();
                }

                if(type.equals("module"))
                {
                    System.out.println("enter credits");
                    value3 = in.nextLine();
                }
            }
            switch(type)
            {
                case "student": db.student(value1, value2, value3, "add"); break;
                case "staff": db.staff(value1,value2,value3,"add"); break;
                case "module": db.module(value1,value2,value3,"add"); break;
                case "registered": db.registered(value1, value2, "add"); break;
                case "teaches": db.teaches(value1, value2, "add"); break;
            }
                
        }
        catch(Exception e)
        {
            System.out.println("Exception = " + e);
        }
    }
    
    /**
     * Gathers the information necessary to send to the database manager
     * in regards to deleting an entity
     * @param type - the table wanting a tuple to be deleted from
     */
    public void deleteEntity(String type)
    {
        String value1 ="";
        String value2 ="";
        String value3="";
        try
        {
            //only has fields, so has a specialised prompt along with teaches
            if(type.equals("registered"))
            {
                System.out.println("Enter studentID");
                value1 = in.nextLine();
                System.out.println("Enter moduleID");
                value2 = in.nextLine();
            }
            
            else if(type.equals("teaches"))
            {
                System.out.println("Enter studentID");
                value1 = in.nextLine();
                System.out.println("Enter moduleID");
                value2 = in.nextLine();
            }
            
            else
            {
                System.out.println("Enter ID");
                value1 = in.nextLine();
            }
            
            switch(type)
            {
                case "student": db.student(value1,value2,value3,"delete");
                case "staff": db.staff(value1, value2,value3, "delete");
                case "module":  db.module(value1,value2, value3, "delete");
                case "registered": db.registered(value1,value2,"delete");
                case "teaches": db.teaches(value1, value2, "delete");
            }
           
        }
        catch(Exception e)
        {
            System.out.println("Exception = " + e);
        }
    }
    
    /**
     * Gathers information required to send to database manager to 
     * request an update on an entity
     * @param type - the table in which the tuple is in
     */
    private void updateEntity(String type)
    {
        String value1 = "";
        String value2 = "";
        String value3 = "";
        
        try
        {
                System.out.println("Enter ID ");
                value1 = in.nextLine();
                System.out.println("Enter Name ");
                value2 = in.nextLine();

                if(type.equals("student" ))
                {
                    System.out.println("Enter Degree Scheme");
                    value3 = in.nextLine();
                }

                if(type.equals("staff"))
                {
                    System.out.println("Enter Grade");
                    value3 = in.nextLine();
                }

                if(type.equals("module"))
                {
                    System.out.println("enter credits");
                    value3 = in.nextLine();
                }
 
            switch(type)
            {
                case "student": db.student(value1, value2, value3, "update"); break;
                case "staff": db.staff(value1,value2,value3,"update"); break;
                case "module": db.module(value1,value2,value3,"update"); break;
            }

        }
        catch(SQLException e)
        {}
    }
    
    /**
     * the list command, to request information from the databse manager/database
     * @param type - the table in which data is requested from
     */
    private void list(String type)
    {
        try
        {
            db.list(type);   
        }
        catch(SQLException e){}
    }
    
    /**
     * specialised requests that is likely to be done by user
     */
    private void reports()
    {
        String name = "";
        System.out.println("Reports \n *******  \n 1. Modules taught by  \n 2. Students registered on ");
        System.out.println(" 3. Staff who teach student \n 4. Staff who teach more than");
        System.out.println("Enter choice");
        int choice = in.nextInt();
        in.nextLine();
        if(choice != 4)
        {
            System.out.println("Enter name");
            name = in.nextLine();
        }
        try
        {
            db.reports(choice, name);
        }
        catch(SQLException e){}
    }
}
