package com.jetbrains;

import java.sql.*;
import java.util.Scanner;

public class TreatedPatient {
    String userinput;

    Scanner in = new Scanner(System.in);

    ResultSet resultSet = null;

    int dsUpdated = 0; //0-notSet 1-successful 2-deceased 3-refferred
    boolean rsUpdated = false;
    boolean treatmentUpdated = false;
    boolean neUpdated = false;

    /*
    Displays the Treated Patient List
    User Selects a Patient
    Asks the user if they want to checkout the selected patient or go back
     */
    public void treatedPatientMenu(Connection conn) throws SQLException {
        Statement st = null;
        try{
            st = conn.createStatement();

            System.out.println("*************");
            System.out.println("List of Treated Patients");
            System.out.println("*************");

            resultSet = st.executeQuery("SELECT * FROM Patient WHERE treated=1");

            while(resultSet.next())
            {
                int id = resultSet.getInt("user_id");
                System.out.println("Patient Id:"+id);
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
        finally {
            if(st!=null) {st.close();}
        }
    }

    /*
    Displays the Checkout Menu for the selected Patient
     */
    public void patientCheckout(Connection conn, Integer pid){
        try {
            System.out.println("*************");
            System.out.println("1.  Discharge Status "); //perfect
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
                    if(dsUpdated!=3)
                    {
                     System.out.println("Discharge Status is not Referred.");
                     this.patientCheckout(conn,pid);
                    }
                    else
                    {
                        System.out.println("Referral Status Menu");
                        this.referralStatusMenu(conn,pid);
                    }
                    break;
                case "3":
                    System.out.println("Treatment");
                    this.recordTreatment(conn ,pid);
                    break;
                case "4":
                    System.out.println("Negative Experience");
                    this.negExpMenu(conn, pid);
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
    public void dischargeStatusMenu(Connection conn, Integer pid) throws SQLException {
        PreparedStatement pstmt = null;
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
                    pstmt = conn.prepareStatement("UPDATE Report SET discharge_status = ?  WHERE rid=(SELECT rid FROM Patient_has_report WHERE user_id = ?)");
                    pstmt.setString (1, status);
                    pstmt.setInt (2, pid);
                    pstmt.execute();
                    dsUpdated = 1;
                    this.patientCheckout(conn, pid);
                    break;
                case "2":
                    System.out.println("Selected-Successful Deceased. Saved!");
                    status = "Deceased";
                    pstmt = conn.prepareStatement("UPDATE Report SET discharge_status = ?  WHERE rid=(SELECT rid FROM Patient_has_report WHERE user_id = ?)");
                    pstmt.setString (1, status);
                    pstmt.setInt (2, pid);
                    pstmt.execute();
                    dsUpdated = 2;
                    this.patientCheckout(conn, pid);
                    break;
                case "3":
                    System.out.println("Selected-Referred. Saved!");
                    status = "Referred";
                    pstmt = conn.prepareStatement("UPDATE Report SET discharge_status = ?  WHERE rid=(SELECT rid FROM Patient_has_report WHERE user_id = ?)");
                    pstmt.setString (1, status);
                    pstmt.setInt (2, pid);
                    pstmt.execute();
                    dsUpdated = 3;
                    this.patientCheckout(conn, pid);
                    break;
                case "4":
                    System.out.println("Go Back");
                    this.patientCheckout(conn, pid);
                    break;
            }

        }catch (Exception e){
            System.out.println(e.toString());
        }
        finally {
            if(pstmt!=null) {pstmt.close();}
        }
    }

    public void referralStatusMenu(Connection conn, Integer pid) throws SQLException {
        PreparedStatement pstmt = null;
        try{
            userinput = "";
            System.out.println("*************");
            System.out.println("1.  Facility Id ");
            System.out.println("2.  Referrer Id");
            System.out.println("3.  Add Reason");
            System.out.println("4.  Go Back");
            System.out.println("*************");
            System.out.println("Please enter your selection: (1-4) ");
            userinput = in.next();

            switch (userinput) {
                case "1":
                    System.out.println("Enter Facility Id (Enter 0 if none): ");
                    String fid = in.next();
                    pstmt = conn.prepareStatement("UPDATE Referral_status SET fid = ? WHERE rs_id = (SELECT rs_id FROM Report_has_ref WHERE rid=(SELECT rid FROM Patient_has_report WHERE user_id=?));");
                    pstmt.setInt (1, Integer.parseInt(fid));
                    pstmt.setInt (2, pid);
                    pstmt.execute();
                    this.referralStatusMenu(conn,pid);
                    break;
                case "2":
                    System.out.println("Enter Referrer's User Id: ");
                    String uid = in.next();
                    pstmt = conn.prepareStatement("UPDATE Referral_status SET referrer = ? WHERE rs_id = (SELECT rs_id FROM Report_has_ref WHERE rid=(SELECT rid FROM Patient_has_report WHERE user_id=?));");
                    pstmt.setInt (1, Integer.parseInt(uid));
                    pstmt.setInt (2, pid);
                    pstmt.execute();
                    this.referralStatusMenu(conn,pid);
                    break;
                case "3":
                     System.out.println("Add Reason");
                     this.referralReasonMenu(conn,pid);
                     break;
                case "4":
                     System.out.println("Go Back");
                     this.patientCheckout(conn, pid);
                     break;
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        finally {
            if(pstmt!=null) {pstmt.close();}
        }
    }

    public void negExpMenu(Connection conn, Integer pid) throws SQLException {
        Statement st  = null;
        try{
            st = conn.createStatement();

            System.out.println("*************");
            System.out.println("Negative Experience Codes");
            System.out.println("*************");

            resultSet = st.executeQuery("SELECT * FROM Negative_experience");

            System.out.println("Code" + "\t" + "Description");
            System.out.println("------------------------------");

            while(resultSet.next())
            {
                int code = resultSet.getInt("ne_code");
                String description = resultSet.getString("description");
                System.out.println(code + "\t" + description);
            }

            System.out.println("MENU");
            System.out.println("*************");
            System.out.println("1.  Enter Negative Experience");
            System.out.println("2.  Go Back");
            System.out.println("Please enter your selection: (1-2) ");
            userinput = in.next();
            switch (userinput) {
                case "1":
                    System.out.println("Enter Negative Experience Code: ");
                    String inputCode = in.next();
                    this.enterReason(conn,pid,inputCode);
                    this.patientCheckout(conn,pid);
                    break;
                case "2":
                    System.out.println("Go Back");
                    this.referralStatusMenu(conn, pid);
                    break;
            }

        }catch (Exception e){
            System.out.println(e.toString());
        }
        finally {
            if(st!=null) {st.close();}
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
    public void recordTreatment(Connection conn, Integer pid) throws SQLException {
        PreparedStatement pstmt = null;
        try{

            System.out.println("Enter Treatment Description: ");
            userinput = in.next();

            pstmt = conn.prepareStatement("UPDATE Report SET treatment = ?  WHERE rid=(SELECT rid FROM Patient_has_report WHERE user_id = ?)");
            pstmt.setString (1, userinput);
            pstmt.setInt (2, pid);
            pstmt.execute();

            System.out.println("Treatment Description Saved!");
            this.patientCheckout(conn, pid);

        } catch (Exception e){
            System.out.println(e.toString());
        }
        finally {
            if(pstmt!=null) {pstmt.close();}
        }

    }

    public void referralReasonMenu(Connection conn, Integer pid) throws SQLException {
        Statement st = null;
        PreparedStatement pstmt = null;
        try{
            st = conn.createStatement();

            System.out.println("*************");
            System.out.println("List of Reasons");
            System.out.println("*************");

            resultSet = st.executeQuery("SELECT * FROM Reason");

            System.out.println("Code" + "\t" + "Service Name" + "\t" + "Description");
            System.out.println("----------------------------------------------------");

            while(resultSet.next())
            {
                String code = resultSet.getString("reason_code");
                String service_name = resultSet.getString("service_name");
                String description = resultSet.getString("description");
                System.out.println(code + "\t" + service_name + "\t" + description);
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
                    String inputCode = in.next();

                    pstmt = conn.prepareStatement("INSERT INTO Referralstatus_has_reason (rs_id, reason_code) VALUES ((SELECT rs_id FROM Report_has_ref WHERE rid=(SELECT rid FROM Patient_has_Report WHERE user_id = ?)), ?)");
                    pstmt.setInt(1, pid);
                    pstmt.setString(2, inputCode);
                    pstmt.execute();
                    this.referralStatusMenu(conn, pid);
                    break;
                case "2":
                    System.out.println("Go Back");
                    this.referralStatusMenu(conn, pid);
                    break;
            }

        }catch (Exception e){
            System.out.println(e.toString());
        }
        finally {
            if(st!=null) {st.close();}
            if(pstmt!=null) {pstmt.close();}
        }
    }

    public void enterReason(Connection conn, Integer pid, String code) throws SQLException {
        PreparedStatement pstmt = null;
        try{
            System.out.println("Enter Negative Experience Description: ");
            String userinput = in.next();
            pstmt = conn.prepareStatement("UPDATE Report_has_negative SET user_desc = ? WHERE rid = (SELECT rid FROM Patient_has_Report WHERE user_id = ?) AND ne_code = ?");
            pstmt.setString (1, userinput);
            pstmt.setInt (2, pid);
            pstmt.setString (3, code);
            pstmt.execute();
            System.out.println("Description Saved!");


        }catch (Exception e){
            System.out.println(e.toString());
        } finally {
            if(pstmt!=null) {pstmt.close();}
        }
    }

}
