package com.jetbrains;

import java.sql.*;
import java.util.Scanner;


public class Menu {
    String userinput;

    Scanner in = new Scanner(System.in);

    public void menuOptions(Connection conn)
    {
        System.out.println("MENU");
        System.out.println("*************");
        System.out.println("1.  Sign In ");
        System.out.println("2.  Sign Up  (Patient)");
        System.out.println("3.  Demo Queries ");
        System.out.println("4.  Exit ");
        System.out.println("*************");
        System.out.println("Please enter your selection: (1-4) ");

        try
        {
            userinput = in.next();

            switch (userinput) {
                case "1":
                    System.out.println("Sign In");
                    SignIn si = new SignIn();
                    si.menuOptions(conn);
                    break;
                case "2":
                    System.out.println("Sign Up");
                    SignUp su = new SignUp();
                    su.menuOptions(conn);
                    break;
                case "3":
                    System.out.println("Demo");
                    break;
                case "4":
                    System.out.println("Exiting.......");
                    System.exit(0);
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
