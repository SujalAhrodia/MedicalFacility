package com.jetbrains;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PatientRouting {
    String userinput;

    Scanner in = new Scanner(System.in);
    ResultSet temp = null;

    public void menuOptions(Connection conn) throws SQLException
    {
        Statement st = null;

        try
        {
            st = conn.createStatement();

            System.out.println("*************");
            System.out.println("1.  Check In ");
            System.out.println("2.  Check Out ");
            System.out.println("3.  Go Back ");
            System.out.println("*************");
            System.out.println("Please enter your selection: (1-3) ");

            userinput = in.next();

            switch (userinput)
            {
                case "1":
                    System.out.println("Check in");
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
                    System.out.println("Choose a Facility :");
                    System.out.println("*************");
                    System.out.println("Facility Id:");
                    int facID= in.nextInt();

                    //check if the patient has already checked in at that facility
                    PatientCheckIn pci = new PatientCheckIn();
                    pci.menuOptions(conn);
                    break;
                case "2":
                    System.out.println("Check out");
                    break;
                case "3":
                    System.out.println("GO Back");
                    Menu menu = new Menu();
                    menu.menuOptions(conn);
                    break;

            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            if (st != null)
            {
                st.close();
            }
        }
    }
}
