package com.jetbrains;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Demo {

    Statement st = null;
    ResultSet res = null;

    public void displayQueries(Connection conn) throws SQLException
    {
        try
        {
            st = conn.createStatement();

            System.out.println("**********");
            System.out.println("Demo Queries");
            System.out.println("**********");

            System.out.println("1. Find all patients that were discharged but had negative experiences at any facility, list their names, facility, check-in date, discharge date and negative experiences");
//            res = st.executeQuery("");

            System.out.println("2. Find facilities that did not have a negative experience for a specific period (to be given).");
//            res = st.executeQuery("");

            System.out.println("3. For each facility, find the facility that is sends the most referrals to.");
//            res = st.executeQuery("");

            System.out.println("4. Find facilities that had no negative experience for patients with cardiac symptoms");
//            res = st.executeQuery("");

            System.out.println("5. Find the facility with the most number of negative experiences (overall i.e. of either kind).");
//            res = st.executeQuery("");

            System.out.println("6. Find each facility, list the patient encounters with the top five longest check-in phases (i.e. time from begin check-in to when treatment phase begins (list the name of patient, date, facility, duration and list of symptoms).");
//            res = st.executeQuery("");

            String userinput;
            Scanner in = new Scanner(System.in);

            System.out.println("**********");
            System.out.println("Do you want to go back to the menu? (y/n)");
            System.out.println("y: go to previous menu");
            System.out.println("n: exit ");
            System.out.println("**********");

            userinput = in.next();

            if (userinput.equals("y"))
            {
                Menu m = new Menu();
                m.menuOptions(conn);
            }
            else
            {
                System.out.println("Exiting.......");
                System.exit(0);
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
