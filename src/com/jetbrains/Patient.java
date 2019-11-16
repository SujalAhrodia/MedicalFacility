package com.jetbrains;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Patient {
    int userinput;
    int pid;

    Scanner in = new Scanner(System.in);
    ResultSet temp = null;
    Statement st = null;

    Patient(int pid) {
	    this.pid = pid;
    }

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

    public static int has_fid(Connection conn, int uid) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs =
                    st.executeQuery("SELECT fid FROM facility_has_user WHERE user_id = "
                            + uid);

            if (rs.next()) {
                return rs.getInt("fid");
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
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
                    boolean flag=true;
                    int facID=-1;

                    while(flag)
                    {
                        System.out.println("Check into a facility: --");
                        //print facilities from facilities table
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

                        facID= in.nextInt();

                        if (facID == has_fid(conn, this.pid))
                        {
                            System.out.println("Already checked in!");
                            System.out.println("Please choose another facility!");
                        }
                        else {
                            flag=false;
                        }
                    }
                    checkinMenu(conn, facID);
                    break;
                case 2:
                    System.out.println("Check out");
                    this.patientCheckout(conn);
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

    public void checkinMenu(Connection conn, int facID) throws SQLException
    {
	boolean cont = true;
	while (cont) {
		try
		{
			st = conn.createStatement();

			//print symptoms from symptoms table
			System.out.println("*************");
			System.out.println("List of Symptoms");
			System.out.println("*************");

			temp = st.executeQuery("select symptom_name,code from Symptom");

			int i=1;
			//array for choice of symptoms
			String[] symp = new String[100];
			String[] codes = new String[100];

			while(temp.next())
			{
				String name = temp.getString("symptom_name");
				System.out.println(i + ".\t" + name);
				symp[i]=name;
				codes[i] = temp.getString("code");
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
				//validate,
                Calendar calendar = Calendar.getInstance();
                java.sql.Date dateObj = new java.sql.Date(calendar.getTime().getTime());
                PreparedStatement pstmt = conn.prepareStatement("Update Patient SET checkin_time_start =TO_DATE('"+dateObj+"', 'YYYY/MM/DD') WHERE user_id="+this.pid);
                pstmt.executeUpdate();

                pstmt = conn.prepareStatement("Update Patient SET checkout_time = NULL WHERE user_id="+this.pid);
                pstmt.executeUpdate();

                pstmt = conn.prepareStatement("INSERT INTO Facility_has_user(fid, user_id) VALUES (?,?)");
                pstmt.setInt(1, facID);
                pstmt.setInt(2, this.pid);
                pstmt.executeUpdate();

                System.out.println("*** Added to patient to facility ***");
//1002 ,4
				System.out.println("*** Logging out ***");
                cont = false;

                Menu m = new Menu();
				m.menuOptions(conn);
			}
			else if (userinput == i-1)
			{
				System.out.println("Add information about new symptom ---");
				try {
					Statement st = conn.createStatement();
					System.out.println("*************");
					System.out.println("Enter the following information:");
					System.out.println("Symptom Name: ");
					String name= in.next();

					System.out.println("Symptom Code: ");
					String code = in.next();

					System.out.println("*************");
					System.out.println("Body Parts");
					System.out.println("*************");
					ResultSet temp = st.executeQuery("SELECT * FROM Body_part");

					while(temp.next())
					{
						String id = temp.getString("part_name");
						String part_code = temp.getString("code");
						System.out.println("code: "+part_code+"\t name:"+id);
					}
					System.out.println("*************");
					System.out.println("Implied body part code: (leave blank for none)");
					in.nextLine(); // remove the unprocessed \n
					String part = in.nextLine();
					if (part == "") part = "None";

					// select a symptom scale
					System.out.println("*************");
					System.out.println("List of Symptom Scales");
					System.out.println("*************");

					temp = st.executeQuery("SELECT scale_name FROM symptom_scale");

					while(temp.next())
					{
						String id = temp.getString("scale_name");
						System.out.println(id);
					}
					System.out.println("*************");
					System.out.println("Choose a Symptom Severity Scale :");
					System.out.println("*************");
					System.out.println("Enter Scale name:");
					String scale = in.next();
		    
					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Symptom (symptom_name, code, symptom_scale) VALUES (?,?,?)");
					pstmt.setString(1, name);
					pstmt.setString(2, code);
					pstmt.setString(3, scale);
					pstmt.execute();

					System.out.println("Symptom created");

					pstmt = conn.prepareStatement("INSERT INTO Implies (symptom, part) VALUES (?,?)");
					pstmt.setString(1, code);
					pstmt.setString(2, part);
					pstmt.execute();
					
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
			else {
				metaData(codes[userinput], conn);
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
    }
    public void metaData(String symp_code, Connection conn) throws SQLException
    {
	    String part = "";
	    List<String> part_list = new ArrayList<>();
	    try {
		    Statement st = conn.createStatement();
		    ResultSet q2 = st.executeQuery("SELECT part FROM Implies WHERE symptom='"+symp_code+"'");
		    while(q2.next()) {
			    part = q2.getString("part");
                part_list.add(part);
            }

        }
	    catch (Exception e)
	    {
		    System.out.println(e.toString());
	    }

	    String dur = "";
	    String re = "";
	    String severity = "";
	    String cause = "";
	    
	    boolean cont = true;
	    while (cont) {
		    System.out.println("*************");
		    System.out.println("Enter the following data for "+ symp_code);
		    System.out.println("*************");
		    
		    if (part_list.size() == 0)
			    System.out.println("1. Body Part ");
		    else if(part_list.size()>1)
		    {
                System.out.println("*************");

                for (int i=0;i<part_list.size();i++) {
                    System.out.println(i+1+":"+part_list.get(i));
                }
                System.out.println("*************");

                userinput = in.nextInt();
                part = part_list.get(userinput+1);
		    }
		    if (dur == "")
			    System.out.println("2. Duration ");
		    if (re == "")
			    System.out.println("3. Reoccurring ");
		    if (severity == "")
			    System.out.println("4. Severity ");
		    if (cause == "")
			    System.out.println("5. Cause (Incident) ");
		    
		    System.out.println("*************");
		    System.out.println("Please enter your selection: (1-5)");

		    try
		    {
			    userinput = in.nextInt();

			    switch (userinput)
			    {
			    case 1:
				    System.out.println("Body Part (If you don't want to specify, type 'None')");
				    part = in.next();
				    break;
			    case 2:
				    System.out.println("Duration");
				    //throw away the extra \n not processed
				    in.nextLine();
				    dur = in.nextLine();
				    break;
			    case 3:
				    System.out.println("Reoccurring (Y/N)");
				    re = in.next();
				    break;
			    case 4:
			    	ResultSet rs = st.executeQuery("select symptom_scale from Symptom where code='"+symp_code+"'");
			    	while(rs.next())
                    {
                        System.out.println("Choose Severity: "+rs.getString("symptom_scale"));
                    }
				    severity = in.next();
				    break;
			    case 5:
				    System.out.println("Cause (Incident)");
				    //throw away the extra \n not processed
				    in.nextLine();
				    cause = in.nextLine();
				    break;
			    default:
				    System.out.println("Invalid input!");
				    System.out.println("Please read the options carefully");
				    cont = false;
				    break;
			    }
		    }
		    catch (Exception e)
		    {
			    System.out.println(e.toString());
		    }

		    // if all the fields are set then exit the loop
		    if (part != "" && dur != "" && re != "" && severity != "" && cause != "")
			    break;
	    }

	    try
	    {
		    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Has_symptom (symptom, patient, value, duration, incident, recurring, part) VALUES (?,?,?,?,?,?,?)");
		    pstmt.setString(1, symp_code);
		    pstmt.setInt(2, pid);
		    pstmt.setString(3, severity);
		    pstmt.setString(4, dur);
		    pstmt.setString(5, cause);
		    pstmt.setString(6, re);
		    pstmt.setString(7, part);
//		    System.out.println(symp_code + " " + pid + " " + part);
		    pstmt.execute();
		    System.out.println("Patient data updated");
	    }
	    catch (Exception e)
	    {
		    System.out.println(e.toString());
	    }

    }

    void patientCheckout(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		Statement st = null;
		ResultSet rs;
    	try{
		        st = conn.createStatement();
			rs = st.executeQuery("SELECT seq.NEXTVAL FROM dual");
			int rid = -1;
			while (rs.next())
				rid = rs.getInt("nextval");

			pstmt = conn.prepareStatement("INSERT INTO Report(rid) VALUES (?)");
			pstmt.setInt(1,rid);
			pstmt.execute();
			pstmt = conn.prepareStatement("INSERT INTO Patient_has_Report(user_id,rid) VALUES (?,?)");
			pstmt.setInt(1,pid);
			pstmt.setInt(2,rid);
			pstmt.execute();
			Calendar calendar = Calendar.getInstance();
	        java.sql.Date dateObj = new java.sql.Date(calendar.getTime().getTime());
            pstmt = conn.prepareStatement("Update Patient SET checkout_time =TO_DATE('"+dateObj+"', 'YYYY/MM/DD') WHERE user_id="+pid);
	        pstmt.executeUpdate();
	        System.out.println("Facility ID saved!");
		}catch (Exception e){
			System.out.println(e.toString());
		}
    	finally {
			if(pstmt!=null) {pstmt.close();}
			if(st!=null) {st.close();}
		}
	}

}
