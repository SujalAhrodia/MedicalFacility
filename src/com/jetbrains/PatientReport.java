package com.jetbrains;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PatientReport {
    Integer pid;

    public void displayReport(Connection conn, Integer pid){
        Statement st = null;
        ResultSet resultSet = null;
        try{
            st = conn.createStatement();

            System.out.println("*******************");
            System.out.println("Patient Report");
            System.out.println("*******************");
            //discharge status
            //treatment
            //Refferal Status
                //Facility Referred to
                //Staff
                //Referral Reasons:
            //Negative Exprience

            resultSet = st.executeQuery("SELECT * FROM Patient WHERE istreated='Y'");

            while(resultSet.next())
            {
                int id = resultSet.getInt("user_id");
                System.out.println("Patient Id:"+id);
            }
        }catch (Exception e){

        }
        finally {

        }
    }

    public void acknowledgeReport(Integer pid){

    }
}
