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
						+ uid);

			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}

	public void routingMenu(Connection conn,int uid) {
		try {
			System.out.println("*************");
			System.out.println("1.  View Checked-in Patient List ");
			System.out.println("2.  Treated Patient List");
			System.out.println("3.  Add Symptom ");
			System.out.println("4.  Add Severity");
			System.out.println("5.  Assessment Rule");
			System.out.println("6.  Go Back ");
			System.out.println("*************");
			System.out.println("Please enter your selection: (1-3) ");

			userinput = in.next();

			switch (userinput) {
			case "1": 
				CheckInPatient.displayCheckedInPatients(conn,uid);
				break;
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
				this.addSeverity(conn);
				break;
			case "5":
				System.out.println("Add assessment rule");
				this.addAssessment(conn);
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

			System.out.println("*************");
			System.out.println("Body Parts");
			System.out.println("*************");
			ResultSet temp = st.executeQuery("SELECT * FROM Body_part");

			while(temp.next())
			{
				String id = temp.getString("part_name");
				String part_code = temp.getString("code");
				System.out.println("code: "+code+"\t name:"+id);
			}
			System.out.println("*************");
			System.out.println("Implied body part code: (leave blank for none)");
			String part = in.nextLine();

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
		    
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Symptom (symptom_name, code, priority, symptom_scale, part) VALUES (?,?,0,?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, code);
			pstmt.setString(3, part);
			pstmt.setString(4, scale);
			pstmt.setString(5, part);
			pstmt.execute();

			System.out.println("Symptom created");
					
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void addSeverity(Connection conn) {
		try {
			Statement st = conn.createStatement();
			System.out.println("*************");
			System.out.println("Symptom Scales");
			System.out.println("*************");

			ResultSet temp = st.executeQuery("SELECT scale_name FROM symptom_scale");

			while(temp.next())
			{
				String id = temp.getString("scale_name");
				System.out.println(id);
			}
			System.out.println("*************");
			System.out.println("Choose a Symptom Severity Scale :");
			String scale = in.next();

			System.out.println("Enter a new parameter string for the scale: ");
			String scale_param = in.next();

			System.out.println("Enter a numerical severity for the parameter: ");
			int severity = in.nextInt();

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Scale_parameter (scale_name, param, severity) VALUES (?,?,?)");
			pstmt.setString(1, scale);
			pstmt.setString(2, scale_param);
			pstmt.setInt(3, severity);
			pstmt.execute();
			System.out.println("Symptom severity added");

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void addAssessment(Connection conn) {
		try {
			System.out.println("Enter category N, H, or Q : ");
			String category = in.next();

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT seq.NEXTVAL FROM dual");
			int aid = -1;
			while (rs.next())
				aid = rs.getInt("nextval");

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Assessment (assessment_id, category) VALUES (?,?)");
			pstmt.setInt(1, aid);
			pstmt.setString(2, category);
			pstmt.execute();

			//addEvaluate(conn, aid);
			boolean cont = true;
			while (cont) {
				System.out.println("*************");
				System.out.println("1.  Add rules to this assessment.");
				System.out.println("2.  Go back.");
				System.out.println("*************");
				System.out.println("Please enter your selection: (1-2) ");

				userinput = in.next();
				switch(userinput) {
				case "1":
					addConsists_of(conn, aid);
					break;
				case "2":
					cont = false;
					break;
				}
			}

			System.out.println("Assessment added");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void addEvaluate(Connection conn, int aid, int uid) {
		try {
			Statement st = conn.createStatement();

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Evaluate (assessment_id, user_id) VALUES (?,?)");
			pstmt.setInt(1, aid);
			pstmt.setInt(2, uid);
			pstmt.execute();
			System.out.println("Evaluation added");

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void addConsists_of(Connection conn, int aid) {
		try {
			Statement st = conn.createStatement();
			System.out.println("*************");
			System.out.println("Symptoms");
			System.out.println("*************");

			ResultSet temp = st.executeQuery("SELECT code,symptom_name,symptom_scale FROM symptom");

			String scale = "";
			while(temp.next())
			{
				String id = temp.getString("code");
				String name = temp.getString("symptom_name");
				scale  = temp.getString("symptom_scale");
				System.out.println(id + " " + name);
			}
			System.out.println("*************");
			System.out.println("Choose a Symptom code:");
			String symptom = in.next();

			System.out.println("*************");
			System.out.println("Body Parts");
			System.out.println("*************");

			temp = st.executeQuery("SELECT code,part_name FROM body_part");

			while(temp.next())
			{
				String id = temp.getString("code");
				String name = temp.getString("part_name");
				System.out.println(id + " " + name);
			}
			System.out.println("*************");
			System.out.println("Choose a Body Part:");
			String part = in.next();

			System.out.println("*************");
			System.out.println("Severity");
			System.out.println("*************");

			temp = st.executeQuery("SELECT param FROM scale_parameter WHERE scale_name = '" + scale  + "'");

			while(temp.next())
			{
				String id = temp.getString("param");
				System.out.println(id);
			}
			System.out.println("*************");
			System.out.println("Choose a Symptom Severity :");
			String threshold = in.next();

			System.out.println("Should the rule trigger when GREATER or LESSER to the threshold?:");
			String dir = in.next();

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Consists_of (assessment_id, symptom, part, sympt_scale, direction, threshold) VALUES (?,?,?,?,?,?)");
			pstmt.setInt(1, aid);
			pstmt.setString(2, symptom);
			pstmt.setString(3, part);
			pstmt.setString(4, scale);
			pstmt.setString(5, dir);
			pstmt.setString(6, threshold);

			pstmt.execute();
			System.out.println("Consists_of added");

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	static void apply_rules(Connection conn, int pid) {
		String priority = "N";
		try {
			Statement st = conn.createStatement();

			ResultSet temp = st.executeQuery("SELECT assessment_id,category FROM assessment");

			int aid = 0, applied_aid = 0;
			String category = "";
			while(temp.next())
			{
				aid = temp.getInt("assessment_id");
				category = temp.getString("category");
				boolean apply = apply_one_rule(conn, pid, aid);

				// if this consists_of has the highest priority, then update the return
				if (apply) {
					priority = category;
					applied_aid = aid;
				}
			}

			System.out.println("Patient priority is " + priority);

			addEvaluate(conn, applied_aid, pid);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	static int get_severity(Connection conn, String scale, String value) {
		try {
			Statement st = conn.createStatement();
			// SELECT severity FROM scale_parameter WHERE param = value;
			ResultSet temp = st.executeQuery("SELECT severity FROM scale_parameter WHERE scale_name = '" + scale + "' AND param = '" + value + "'");

			int severity = -1;
			while(temp.next()) {
				severity = temp.getInt("severity");
			}

			return severity;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return -1;
	}

	static boolean apply_one_rule(Connection conn, int pid, int rule) {
		boolean condition = false;
		try {

			// for each consists_of
			// SELECT symptom,threshold,direction FROM consists_of WHERE assessment_id = pid;
			Statement st = conn.createStatement();

			ResultSet temp = st.executeQuery("SELECT symptom,threshold,direction FROM consists_of WHERE assessment_id = " + rule);

			String symptom = "", threshold = "", direction = "";
			while(temp.next()) {
				symptom = temp.getString("symptom");
				threshold = temp.getString("threshold");
				direction = temp.getString("direction");

				temp = st.executeQuery("SELECT symptom_scale FROM symptom");

				String scale = "";
				while(temp.next())
					scale  = temp.getString("symptom_scale");

				// check if the patient has this symptom
				// SELECT value FROM has_symptom WHERE patient = pid AND symptom = symptom;
				temp = st.executeQuery("SELECT value FROM has_symptom WHERE patient = " + pid + " AND symptom = '" + symptom + "'");

				// patient does not have this symptom
				if (!temp.next())
					continue;

				String value = "";
				while(temp.next()) {
					value = temp.getString("value");
				}

				// check if the severity hits the threshold
				int severity = get_severity(conn, scale, value);
				int thresh_severity = get_severity(conn, scale, value);

				// add it to the WIP assessment
				if (severity != -1 && thresh_severity != -1
				    && ((direction == "GREATER" && severity > thresh_severity)
					|| (direction == "LESSER" && severity < thresh_severity))
					)
					condition = true;
				else
					condition = false;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return condition;
	}
}
