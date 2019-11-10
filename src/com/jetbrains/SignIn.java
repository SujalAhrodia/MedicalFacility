package com.jetbrains;


import java.sql.*;
import java.util.Scanner;

public class SignIn {
    String userinput;

    Scanner in = new Scanner(System.in);
    ResultSet temp = null;

    public void menuOptions(Connection conn) throws SQLException
    {
        Statement st = null;

        try
        {
            st = conn.createStatement();

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

                System.out.println("Date of Birth: (format: YYYY-MM-DD)");
                //format needs to be updated
                String dob = in.next();

                System.out.println("*************");
                System.out.println("1.  Sign In ");
                System.out.println("2.  Go Back ");
                System.out.println("*************");
                System.out.println("Please enter your selection: (1/2) ");

                userinput = in.next();

                switch (userinput)
                {
		            case "1":
                        System.out.println("Sign In");
                        temp = null;

                        temp = st.executeQuery("SELECT * FROM login_user WHERE Lname='"
					       +lname+"' AND dob='"+dob+"'");

			            int id = -1;

			            while (temp.next())
			            {
			                id = temp.getInt("user_id");
                        }

                        System.out.println("Found uid " + id);

                        if (Patient.has_uid(conn, id)) {
                            System.out.println("Successful Patient login");
                            //route to patient
                            Patient p = new Patient();
                            p.routingMenu(conn);
                        }
            			else if (Staff.has_uid(conn,id))
			            {
			                System.out.println("Staff login");
			                Staff s = new Staff();
			                s.routingMenu(conn,id);
			            }
                        else
                        {
				            System.out.println("Sign In Incorrect!");
				            System.out.println("Please enter again");
				            continue;
                        }
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

        } catch (Exception e)
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
