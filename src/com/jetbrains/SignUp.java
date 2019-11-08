package com.jetbrains;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class SignUp {
    String userinput;

    Scanner in = new Scanner(System.in);
    PreparedStatement pstmt = null;

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

        System.out.println("** Address ** ");
        System.out.println("Number: ");
        int no = in.nextInt();

        System.out.println("Street Name: ");
        String sname = in.next();

        System.out.println("City: ");
        String city = in.next();

        System.out.println("State: ");
        String state = in.next();

        System.out.println("Country: ");
        String country = in.next();

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
                    pstmt = conn.prepareStatement("INSERT INTO Login_user (Fname, Lname, ph_no , DoB) VALUE (?,?,?,?)");

                    pstmt.setString (1, fname);
                    pstmt.setString (2, lname);
                    pstmt.setInt (3, ph);
                    pstmt.setString (4, dob);

                    pstmt.execute();
                    pstmt = conn.prepareStatement("INSERT INTO Address(addr_number, addr_street, addr_city, addr_state, addr_country) VALUES (?,?,?,?,?)");

                    pstmt.setInt (1, no);
                    pstmt.setString (2, sname);
                    pstmt.setString (3, city);
                    pstmt.setString (4, state);
                    pstmt.setString (5, country);

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
        finally
        {
            if(pstmt != null)
            {
                pstmt.close();
            }
        }
    }
}
