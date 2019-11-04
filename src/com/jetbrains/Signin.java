package com.jetbrains;


import java.sql.*;
import java.util.Scanner;

public class Signin {
    String userinput;

    Scanner in = new Scanner(System.in);
    ResultSet temp = null;

    public void menuOptions(Connection conn) throws SQLException
    {
        Statement st = conn.createStatement();

        try
        {
            while (true)
            {
                //print facilities from facilities table
                System.out.println("*************");
                System.out.println("List of Facilities");
                System.out.println("*************");

                temp = st.executeQuery("select fid, fac_name from Facility");

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
                String dob = in.next();

                System.out.println("City of Address:");
                String city = in.next();

                System.out.println("Patient: (y/n)");
                String o = in.next();

                System.out.println("*************");
                System.out.println("1.  Sign In ");
                System.out.println("2.  Go Back ");
                System.out.println("*************");
                System.out.println("Please enter your selection: (1/2) ");

                userinput = in.next();

                switch (userinput) {
                    case "1":
                        System.out.println("Sign In");
                        temp = null;

                        temp = st.executeQuery("select * from Login_user where Lname='"+lname+"' && DoB='"+dob+"'");

                        // fetch
                        String dbL = null;
                        String dbdob = null;
                        while (temp.next()) {
                            dbL = temp.getString("Lname");
                            dbdob = temp.getString("DoB");
                        }

                        if (lname.equals(dbL) && dob.equals(dbdob))
                        {
                            System.out.println("Successful Login!");
                            //route to patient 
                            break;
                        }
                        else
                        {
                            System.out.println("Sign In Incorrect!");
                            System.out.println("Please enter again");
                            continue;
                        }
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

        }
        catch (Exception e )
        {
            System.out.println(e.getMessage());
        }
    }
}
