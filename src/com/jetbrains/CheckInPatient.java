package com.jetbrains;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class CheckInPatient {
	
	static String userinput;

	static Scanner in = new Scanner(System.in);
	public static void displayCheckedInPatients(Connection conn, int staffId) throws SQLException {
		Statement st = null;
		try {
			 st = conn.createStatement();
			// check if user is not medical staff show error and go back
			ResultSet q = st.executeQuery("SELECT medical FROM Staff WHere user_id ="+staffId);
			while(q.next()) {
				if(q.getString("medical").equals("N")) {
					System.out.println("Only medical Staff is allowed to view this information");
					Staff s = new Staff();
	                s.routingMenu(conn,staffId);
	                return;
				}
			}
			
			// show checked-in patients if user is a medical staff
            System.out.println("*************");
            System.out.println("Checked-In Patients");
            System.out.println("*************");
			
			ResultSet rs =
				st.executeQuery("SELECT Fname from Login_user where user_id IN (SELECT user_id from patient where checkout_time IS NULL AND checkin_time_start IS NOT NULL)");
			
			// TODO clear checkin/checkout time on logout
			
			int i=1;
	        //list of patients checked-in
	        String[] patients = new String[100];

	        while(rs.next())
	            {
	                String name = rs.getString("Fname");
	                System.out.println(i + ".\t" + name);
	                patients[i] = name;
	                i++;
	            }
	        System.out.println("*************");
            System.out.println("Select the patient:");	
            userinput = in.next();
            System.out.println("*************");
            int patientId = Integer.parseInt(userinput);
            System.out.println("*************");
            System.out.println("1.  Enter Vitals ");
            System.out.println("2.  Treat Patient ");
            System.out.println("3.  Go Back ");
            System.out.println("*************");
            System.out.println("Select the operation to perform:(1-3)");
            userinput = in.next();
            System.out.println("*************");
            switch (userinput) {
            case "1":
                enterVitals(conn,staffId,patientId);
                break;
            case "2":
                treatPatient(conn,staffId,patientId);
                break;
            case "3":
            	System.out.println("GO Back");
            	Staff s = new Staff();
                s.routingMenu(conn,staffId);
                break;
            default:
                System.out.println("Invalid input!");
                System.out.println("Please read the options carefully");
        }
            
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
	        if (st != null) { st.close(); }
	    }
		
	}
	
	public static void enterVitals(Connection conn,int userId,int patientId) throws SQLException {
		System.out.println("*************");
        System.out.println("Enter the following information:");
        System.out.println("Body Temperature:");
        int temp= in.nextInt();

        System.out.println("Systolic blood pressure:");
        int sysBP = in.nextInt();
        
        System.out.println("Diastolic blood pressure:");
        int diaBP = in.nextInt();
 
        System.out.println("*************");
        System.out.println("1. Record");
        System.out.println("2. Go Back");
        System.out.println("Choose operation to be performed:");
        userinput = in.next();
        switch (userinput) {
        case "1":
		recordVitals(conn,temp,sysBP,diaBP,patientId,userId);
		break;
        case "2":
        	System.out.println("GO Back");
		break;
        default:
            System.out.println("Invalid input!");
            System.out.println("Please read the options carefully");
    }
		
	}
public static void treatPatient(Connection conn,int userId,int patientId) throws SQLException {
		
		// Query to fetch body part associated to patient symptom
		PreparedStatement pstmt;
		List<String> staffBodyPart =  new ArrayList<>();
		List<String> patientBodyPart =  new ArrayList<>();
		
		Statement st = conn.createStatement();
		try {
			ResultSet q1 = st.executeQuery("SELECT part FROM Associated_to WHERE service IN (SELECT service from Provides WHERE dept_code IN (SELECT dept_code from Works_in Where user_id = "+userId +"))");
			 while(q1.next()) {
				 String part =q1.getString("part");
				 staffBodyPart.add(part);
				 System.out.println("staff"+part);
			 }
			
			ResultSet q2 = st.executeQuery("SELECT part FROM Implies WHERE symptom IN (SELECT symptom from Has_symptom WHERE patient="+patientId+")");
			while(q2.next()) {
				 String part =q2.getString("part");
				 patientBodyPart.add(part);
				 System.out.println("patient1"+part);
			 }
			
			if(staffBodyPart.containsAll(patientBodyPart)){
				pstmt = conn.prepareStatement("Update Patient SET isTreated = 'Y' WHERE user_id="+patientId);
       		 pstmt.execute();
       		System.out.println("Patient moved to treated list");
			}else {
				System.out.println("Inadequate Privilege");
			}

	        
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
	        if (st != null) { st.close(); }
	    }	
	}
	
	public static void recordVitals(Connection conn,int temp,int sysBP,int diaBP,int pid,int userId) throws SQLException {
		PreparedStatement pstmt;
		ResultSet rs;
		Statement st = null;
		try {
			st = conn.createStatement();
            rs = st.executeQuery("SELECT seq.NEXTVAL FROM dual");
			int vid = -1;
			while (rs.next())
				vid = rs.getInt("nextval");
			pstmt = conn.prepareStatement("INSERT INTO Vital_Signals (vital_id, temperature, sys_blood_pressure, dia_blood_pressure) VALUES (?,?,?,?)");
			pstmt.setInt (1, vid);
	        pstmt.setInt(2, temp);
	        pstmt.setInt(3, sysBP);
	        pstmt.setInt (4, diaBP);
	      
	        pstmt.execute();

	        System.out.println("Vitals Recorded!");
	        System.out.println("Vitals Updating for patient!");
	        pstmt = conn.prepareStatement("INSERT INTO Vital_recordings (vital_id, patient, staff) VALUES (?,?,?)");
			pstmt.setInt (1, vid);
	        pstmt.setInt(2, pid);
	        pstmt.setInt(3, userId);
	      
	        pstmt.execute();
	        System.out.println("Patient vitals updated!");
	        
	        
	        Calendar calendar = Calendar.getInstance();
	        java.sql.Date dateObj = new java.sql.Date(calendar.getTime().getTime());
	        //add check-in-end to patient table
	        pstmt = conn.prepareStatement("Update Patient SET checkin_time_end =TO_DATE('"+dateObj+"', 'YYYY/MM/DD') WHERE user_id="+pid);
	        pstmt.executeUpdate();
	        System.out.println("Patient check-in complete at:"+ dateObj+"for"+pid);
	        
	        // enforce assessment rules
	        Staff.apply_rules(conn, pid);
	        
	        System.out.println("Going Back");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        if (st != null) { st.close(); }
	    }
	}
	
 	
	
}
