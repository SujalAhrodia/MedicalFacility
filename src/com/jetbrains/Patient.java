package com.jetbrains;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Patient {
    int userinput;

    Scanner in = new Scanner(System.in);
    ResultSet temp = null;
    Statement st = null;

    public static boolean has_uid(Connection conn, int uid) {
	    try {
		    Statement st = conn.createStatement();
		    ResultSet rs =
			    st.executeQuery("SELECT user_id FROM patient WHERE user_id = "
					    + uid);

		    if (rs.next()) {
			    return true;
		    }

	    } catch (Exception e) {
		    System.out.println(e.toString());
	    }
	    return false;
    }

    public void routingMenu(Connection conn) throws SQLException
    {
        try
        {
            st = conn.createStatement();

            System.out.println("*************");
            System.out.println("1.  Check In ");
            System.out.println("2.  Check Out ");
            System.out.println("3.  Go Back ");
            System.out.println("*************");
            System.out.println("Please enter your selection: (1-3) ");

            userinput = in.nextInt();

            switch (userinput)
            {
                case 1:
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
                    checkinMenu(conn);
                    break;
                case 2:
                    System.out.println("Check out");
                    break;
                case 3:
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
    public void checkinMenu(Connection conn) throws SQLException
    {
        try
        {
            st = conn.createStatement();

            //print symptoms from symptoms table
            System.out.println("*************");
            System.out.println("List of Symptoms");
            System.out.println("*************");

            temp = st.executeQuery("select symptom_name from Symptom");

            int i=1;
            //array for choice of symptoms
            String[] symp = new String[100];

            while(temp.next())
            {
                String name = temp.getString("symptom_name");
                System.out.println(i + ".\t" + name);
                symp[i]=name;
                i++;
            }

            System.out.println(i + "\t" + "Other");
            i++;
            System.out.println(i + "\t" + "Done");
            System.out.println("*************");
            System.out.println("Enter your choice: (1-"+i+")");

            int userinput = in.nextInt();

            if( userinput == i )
            {
                //validate,record time, logout
                System.out.println("*** Logging out ***");
                Menu m = new Menu();
                m.menuOptions(conn);
            }
            else if (userinput == i-1)
            {
                System.out.println("Add information about new symptom");
                //To-do
            }
            else {
                metaData(symp[userinput], conn);
            }

        }
        catch(Exception e)
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
    public void metaData(String symp, Connection conn) throws SQLException
    {
        System.out.println("*************");
        System.out.println("Enter the following data for "+symp);
        System.out.println("*************");
        System.out.println("1. Body Part: ");
        System.out.println("2. Duration: ");
        System.out.println("3. Reoccurring: ");
        System.out.println("4. Severity: ");
        System.out.println("5. Cause (Incident): ");
        System.out.println("*************");
        System.out.println("Please enter your selection: (1-5)");

        try
        {
            userinput = in.nextInt();

            switch (userinput)
            {
                case 1:
                    System.out.println("Body Part");
                    break;
                case 2:
                    System.out.println("Duration");
                    break;
                case 3:
                    System.out.println("Reoccurring");
                    break;
                case 4:
                    System.out.println("Severity");
                    break;
                case 5:
                    System.out.println("Cause (Incident)");
                    break;
                default:
                    System.out.println("Invalid input!");
                    System.out.println("Please read the options carefully");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        checkinMenu(conn);
    }

}
