package com.jetbrains;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class SignUp {
    String userinput;

    Scanner in = new Scanner(System.in);

    public void menuOptions(Connection conn) throws SQLException
    {
        System.out.println("*************");
        System.out.println("Enter the following information:");
        System.out.println("First Name: ");
        String fname= in.next();

        System.out.println("Last Name: ");
        String lname = in.next();

        System.out.println("Date of Birth: (format: YYYY-MM-DD)");
        //to-do
        String dob = in.next();

        System.out.println("City of Address: ");
        String city = in.next();

        System.out.println("Phone number: ");
        int ph = in.nextInt();

        System.out.println("*************");
        System.out.println("1.  Sign Up ");
        System.out.println("2.  Go Back ");
        System.out.println("*************");
        System.out.println("Please enter your selection: (1/2) ");

        try
        {
            userinput = in.next();

            switch (userinput) {
                case "1":
                    System.out.println("Sign Up");
                    //store info in db
                    //Random rand = new Random();
                    //int id = rand.nextInt(1000);

                    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Login_user (Fname, Lname, ph_no , DoB) VALUE (?,?,?,?)");
                    //pstmt.setInt (1, id);
                    pstmt.setString (1, fname);
                    pstmt.setString (2, lname);
                    pstmt.setInt (3, ph);
                    pstmt.setString (4, dob);

                    pstmt.execute();

                    //show msg
                    System.out.println("Profile Created!");

                    //goto sign in page
                    SignIn si = new SignIn();
                    si.menuOptions(conn);
                    break;
                case "2":
                    System.out.println("GO Back");
                    Menu menu = new Menu();
                    menu.menuOptions(conn);
                    break;
                default:
                    System.out.println("Invalid input!");
                    System.out.println("Please read the options carefully");
            }
        }
        catch (Exception e )
        {
            System.out.println(e.toString());
        }
    }
}
