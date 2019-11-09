package com.jetbrains;

import java.sql.*;
import java.util.Scanner;

public class Staff {
	String userinput;

	Scanner in = new Scanner(System.in);
	public static boolean has_uid(Connection conn, int uid) {
		try {
			Statement st = conn.createStatement();
			ResultSet rs =
				st.executeQuery("SELECT user_id FROM staff WHERE user_id = "
						+ uid + ";");

			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}

	public void routingMenu(Connection conn) {
		try {
			System.out.println("*************");
			System.out.println("1.  Checked-in Patient List ");
			System.out.println("2.  Treated Patient List");
			System.out.println("3.  Add Symptom ");
			System.out.println("4.  Add Severity");
			System.out.println("5.  Assessment Rule");
			System.out.println("6.  Go Back ");
			System.out.println("*************");
			System.out.println("Please enter your selection: (1-3) ");

			userinput = in.next();

			switch (userinput) {
			case "2":
				System.out.println("Treated Patient List");
				TreatedPatient treatedPatient = new TreatedPatient();
				treatedPatient.treatedPatientMenu(conn);
				break;
			case "3":
				System.out.println("Add Symptom");
				this.addSymptom(conn);
				break;
			case "4":
				System.out.println("Add Severity");
				break;
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}

	public void addSymptom(Connection conn) {
		try {
			Statement st = conn.createStatement();
			System.out.println("*************");
			System.out.println("Enter the following information:");
			System.out.println("Symptom Name: ");
			String name= in.next();

			System.out.println("Symptom Code: ");
			String code = in.next();

			System.out.println("Priority: ");
			String priority = in.next();

			// select a symptom scale
			System.out.println("*************");
			System.out.println("List of Symptom Scales");
			System.out.println("*************");

			ResultSet temp = st.executeQuery("SELECT scale_name FROM symptom_scale");

			while(temp.next())
			{
				int id = temp.getInt("scale_name");
				System.out.println(id);
			}
			System.out.println("*************");
			System.out.println("Choose a Symptom Severity Scale :");
			System.out.println("*************");
			System.out.println("Enter Scale name:");
			String scale = in.next();
		    
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Symptom (symptom_name, code, priority, symptom_scale) VALUES (?,?,?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, code);
			pstmt.setString(3, priority);
			pstmt.setString(4, scale);
			pstmt.execute();

			System.out.println("Symptom created");
					
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
