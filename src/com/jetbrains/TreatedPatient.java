package com.jetbrains;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TreatedPatient {
    String userinput;

    Scanner in = new Scanner(System.in);

    ResultSet resultSet = null;

    /*
    Displays the Treated Patient List
    User Selects a Patient
    Asks the user if they want to checkout the selected patient or go back
     */
    public void treatedPatientMenu(Connection conn){
        try{
            Statement st = conn.createStatement();

            System.out.println("*************");
            System.out.println("List of Treated Patients");
            System.out.println("*************");

            resultSet = st.executeQuery("SELECT * FROM Patient WHERE treated=1");

            while(resultSet.next())
            {
                int id = resultSet.getInt("fid");
                String name = resultSet.getString("fac_name");
                System.out.println(id + "\t" + name);
            }

            System.out.println("*************");
            System.out.println("Select Patient to Checkout");
            System.out.println("Patient Id:");
            int pId= in.nextInt();

            System.out.println("MENU");
            System.out.println("*************");
            System.out.println("1.  Checkout Patient");
            System.out.println("2.  Go Back");
            System.out.println("Please enter your selection: (1-2) ");
            userinput = in.next();
            switch (userinput) {
                case "1":
                    System.out.println("Patient Checkout");
                    this.patientCheckout(conn, pId);
                    break;
                case "2":
                    System.out.println("Go Back");
                    Staff staff = new Staff();
                    staff.routingMenu(conn,pId);
                    break;
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    /*
    Displays the Checkout Menu for the selected Patient
     */
    public void patientCheckout(Connection conn, Integer pid){
        try {
            System.out.println("*************");
            System.out.println("1.  Discharge Status ");
            System.out.println("2.  Referral Status");
            System.out.println("3.  Treatment ");
            System.out.println("4.  Negative Experience");
            System.out.println("5.  Go Back");
            System.out.println("6.  Submit");
            System.out.println("*************");
            System.out.println("Please enter your selection: (1-3) ");

            userinput = in.next();

            switch (userinput) {
                case "1":
                    System.out.println("Discharge Status");
                    this.dischargeStatusMenu(conn,pid);
                    break;
                case "2":
                    System.out.println("Referral Status");
                    this.referralStatusMenu(conn,pid);
                    break;
                case "3":
                    System.out.println("Treatment");
                    recordTreatment(conn ,pid);
                    break;
                case "4":
                    System.out.println("Negative Experience");
                    //this.negExpMenu(conn, pid);
                    break;
                case "5":
                    System.out.println("Go Back");
                    this.treatedPatientMenu(conn);
                    break;
                case "6":
                    System.out.println("Submit");
                    this.reportConfirmation(conn, pid);
                    break;
            }
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    /*
    Displays the menu to select on of the available status options
    Saves and Redirects back to checkout page upon selecting
     */
    public void dischargeStatusMenu(Connection conn, Integer pid){
        try{
            System.out.println("*************");
            System.out.println("1.  Successful Treatment ");
            System.out.println("2.  Deceased");
            System.out.println("3.  Referred ");
            System.out.println("4.  Go Back");
            System.out.println("*************");
            System.out.println("Please enter your selection: (1-4) ");

            userinput = in.next();
            String status = "";

            switch (userinput) {
                case "1":
                    System.out.println("Selected-Successful Treatment. Saved!");
                    status = "Successful Treatment";
                    break;
                case "2":
                    System.out.println("Selected-Successful Deceased. Saved!");
                    status = "Deceased";
                    break;
                case "3":
                    System.out.println("Selected-Referred. Saved!");
                    status = "Referred";
                    break;
                case "4":
                    System.out.println("Go Back");
                    this.patientCheckout(conn, pid);
                    break;
            }

            PreparedStatement pstmt = conn.prepareStatement("UPDATE Report SET discharge_status = ?  WHERE rid=(SELECT rid FROM Patient_has_report WHERE uid = ?)");
            pstmt.setString (1, status);
            pstmt.setInt (2, pid);
            pstmt.execute();

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void referralStatusMenu(Connection conn, Integer pid){
        try{
            userinput = "";
            System.out.println("*************");
            System.out.println("1.  Facility Id ");
            System.out.println("2.  Referrer Id");
            System.out.println("3.  Add Reason");
            System.out.println("4.  Go Back");
            System.out.println("*************");
            System.out.println("Please enter your selection: (1-4) ");

            switch (userinput) {
                case "1":
                    System.out.println("Enter Facility Id: ");
                    String fid = in.next();
                    PreparedStatement pstmt = conn.prepareStatement("UPDATE Referral_status SET fid = ? WHERE rs_id = (SELECT rs_id FROM Report_has_ref WHERE rid=(SELECT rid FROM Patient_has_report WHERE uid=?));");
                    pstmt.setInt (1, Integer.parseInt(fid));
                    pstmt.setInt (2, pid);
                    pstmt.execute();
                    break;
                case "2":
                    System.out.println("Enter Referrer's User Id: ");
                    String uid = in.next();
                    PreparedStatement pstmt2 = conn.prepareStatement("UPDATE Referral_status SET referrer = ? WHERE rs_id = (SELECT rs_id FROM Report_has_ref WHERE rid=(SELECT rid FROM Patient_has_report WHERE uid=?));");
                    pstmt2.setInt (1, Integer.parseInt(uid));
                    pstmt2.setInt (2, pid);
                    pstmt2.execute();
                    break;
                case "3":
                     System.out.println("Add Reason");
                     //this.referralReasonMenu(conn,pid);
                     break;
                case "4":
                     System.out.println("Go Back");
                     this.patientCheckout(conn, pid);
                     break;
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void negExpMenu(Connection conn, Integer pid){
        try{

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void reportConfirmation(Connection conn, Integer pid){
        try{
            PatientReport report = new PatientReport();
            report.displayReport(conn, pid);

            System.out.println("MENU");
            System.out.println("*************");
            System.out.println("1.  Confirm");
            System.out.println("2.  Go Back");
            System.out.println("Please enter your selection: (1-2) ");
            userinput = in.next();
            switch (userinput) {
                case "1":
                    System.out.println("Saved!");
                    break;
                case "2":
                    System.out.println("Go Back");
                    this.patientCheckout(conn, pid);
                    break;
            }


        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    /*
    Records the treatment description input from user and stays on the same checkout page
     */
    public void recordTreatment(Connection conn, Integer pid){
        try{

            System.out.println("Enter Treatment Description: ");
            userinput = in.next();

            PreparedStatement pstmt = conn.prepareStatement("UPDATE Report SET treatment = ?  WHERE rid=(SELECT rid FROM Patient_has_report WHERE uid = ?)");
            pstmt.setString (1, userinput);
            pstmt.setInt (2, pid);
            pstmt.execute();

            System.out.println("Treatment Description Saved!");
            this.patientCheckout(conn, pid);

        } catch (Exception e){
            System.out.println(e.toString());
        }

    }

    public void referralReasonMenu(Connection conn, Integer pid){
        try{
            Statement st = conn.createStatement();

            System.out.println("*************");
            System.out.println("List of Reasons");
            System.out.println("*************");

            resultSet = st.executeQuery(""); //Change Query

            System.out.println("Code" + "\t" + "Service Name" + "\t" + "Description");
            System.out.println("------------------------------------------------");

            while(resultSet.next())
            {
                int id = resultSet.getInt("fid");
                String name = resultSet.getString("fac_name");
                System.out.println(id + "\t" + name);
            }

            System.out.println("MENU");
            System.out.println("*************");
            System.out.println("1.  Reason");
            System.out.println("2.  Go Back");
            System.out.println("Please enter your selection: (1-2) ");
            userinput = in.next();
            switch (userinput) {
                case "1":
                    System.out.println("Enter Reason Code: ");
                    this.referralStatusMenu(conn,pid);
                    break;
                case "2":
                    System.out.println("Go Back");
                    this.referralStatusMenu(conn, pid);
                    break;
            }

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
