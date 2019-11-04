package com.jetbrains;


import java.sql.*;
import java.util.Scanner;

public class Signin {
    String userinput;

    Scanner in = new Scanner(System.in);

    public void menuOptions(Connection con) throws SQLException
    {
        Statement st = con.createStatement();

        //print facilities from facilities table
        System.out.println("*************");
        System.out.println("List of Facilities");
        System.out.println("*************");

        ResultSet temp = st.executeQuery("select fid, fac_name from Facility");
        
        while(temp.next())
        {
            int id = temp.getInt("fid");
            String name = temp.getString("fac_name");
            System.out.println(id + "\t" + name);
        }

        System.out.println("*************");
        System.out.println("Enter the following information:");
        System.out.println("Facility Id:");
        int facID= in.nextInt();


        System.out.println("Last Name:");
        String lname = in.next();

        System.out.println("Date of Birth:");
        //to-do

        System.out.println("City of Address:");
        String city = in.next();

        System.out.println("Patient: (y/n)");
        String o = in.next();

        System.out.println("*************");
        System.out.println("1.  Sign In ");
        System.out.println("2.  Go Back ");
        System.out.println("*************");
        System.out.println("Please enter your selection: (1/2) ");
    }
}
