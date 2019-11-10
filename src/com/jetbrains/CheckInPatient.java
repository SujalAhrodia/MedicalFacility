package com.jetbrains;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Scanner;

public class CheckInPatient {
	
	static String userinput;

	static Scanner in = new Scanner(System.in);
	public static void displayCheckedInPatients(Connection conn, int staffId) {
		try {
			Statement st = conn.createStatement();
			// check if user is not medical staff show error and go back
			ResultSet q = st.executeQuery("SELECT designation FROM Staff WHere user_id ="+staffId+";");
			while(q.next()) {
				if(!q.getString("designation").equals("Medical")) {
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
			
//			ResultSet rs =
//				st.executeQuery("SELECT pid FROM Patient WHERE check-in !=null and check-out==null ");
			
			// TODO clear checkin/checkout time on logout
			
			int i=1;
	        //list of patients checked-in
	        String[] patients = new String[100];

//	        while(rs.next())
//	            {
//	                String name = rs.getString("pid");
//	                System.out.println(i + ".\t" + name);
//	                patients[i] = name;
//	                i++;
//	            }
	        System.out.println("*************");
            System.out.println("Select the patient:");	
            userinput = in.next();
            System.out.println("*************");
            String patientId = patients[Integer.parseInt(userinput)];
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
                //enterVitals(conn,staffId,patientId);
                break;
            case "2":
                //treatPatient(conn,staffId,patientId);
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
			System.out.println(e.toString());
		}
		
	}
	
	public static void enterVitals(Connection conn,int userId,String patientId) throws SQLException {
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
        System.out.println("1. Go Back");
        System.out.println("Choose operation to be performed:");
        userinput = in.next();
        switch (userinput) {
        case "1":
            //recordVitals(conn,temp,sysBP,diaBP,patientId,userId);
            
            break;
        case "2":
        	System.out.println("GO Back");
            //displayCheckedInPatients(conn, userId);
            break;
        default:
            System.out.println("Invalid input!");
            System.out.println("Please read the options carefully");
    }
		
	}
	public static void treatPatient(Connection conn,int userId,String patientId) throws SQLException {
		
		// Query to fetch body part associated to patient symptom
		PreparedStatement pstmt;
		ResultSet q1;
		ResultSet q2;
		Statement st = null;
		try {
			st = conn.createStatement();

	        q1 = st.executeQuery("SELECT part FROM Associated_to WHERE service = SELECT service from provides WHERE deptid = SELECT depId from worksIn Where staffid= "+userId+";");
	       
	        
	        q2 = st.executeQuery("SELECT part FROM Implies WHERE symptom = SELECT symptom from Has_symptom WHERE patient="+patientId+";");
	        
	        while(q1.next() && q2.next())
            {
	        	if(q1.getString("part").equals(q2.getString("part"))) {
	        		pstmt = conn.prepareStatement("INSERT INTO Patient (isTreated) VALUE (?) Where pid ="+patientId+";");
	        		System.out.println("Patient moved to treated list");
	        	}else {
	        		System.out.println("Inadequate Privilege");
	        	}
            }
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        if (st != null) { st.close(); }
	    }
		
		
		// Query to fetch body part associated with staff(??)
		/* if(a.equals(b)) {
			 // add patient to treated patient list
		 }else {
			 System.out.println("Inadequate privilege: No services for selected symptoms");
		 }*/
	}
	
	public static void recordVitals(Connection conn,int temp,int sysBP,int diaBP,String pid,int userId) throws SQLException {
		PreparedStatement pstmt;
		ResultSet rs;
		Statement st = null;
		try {
			st = conn.createStatement();
			pstmt = conn.prepareStatement("INSERT INTO Vital_Signals (sid, temp, sysBP, diaBP,pid) VALUE (?,?,?,?,?)");
			//pstmt.setInt (1, id(autoincrement));
	        pstmt.setInt(2, temp);
	        pstmt.setInt(3, sysBP);
	        pstmt.setInt (4, diaBP);
	        pstmt.setString(5, pid);
	      
	        pstmt.execute();

	        System.out.println("Vitals Recorded!");
	        
	        Calendar calendar = Calendar.getInstance();
	        java.sql.Date dateObj = new java.sql.Date(calendar.getTime().getTime());
	        //add check-in-end to patient table
	        pstmt = conn.prepareStatement("INSERT INTO Patient (check-in-end) VALUE (?)");
	        pstmt.setDate(1, dateObj);
	        pstmt.executeUpdate();
	        
	        rs = st.executeQuery("SELECT assessment_id FROM Evaluate WHERE userid = pid");
	        String assessmentId = null;
	        while(rs.next())
            {
                 assessmentId = rs.getString("assessment_id");
            }
	        
	        rs = st.executeQuery("SELECT category FROM Assessment WHERE assessment_id = "+assessmentId+";");
	        while(rs.next())
            {
	        	System.out.println("***************");
                System.out.println("The patient priority is:"+ rs.getString("category"));
            }
	        
	        System.out.println("GO Back");
            displayCheckedInPatients(conn, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        if (st != null) { st.close(); }
	    }
	}
	
 	
	
}
