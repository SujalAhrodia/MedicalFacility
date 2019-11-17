package com.jetbrains;

import java.sql.*;

public class PatientReport {
    Integer pid;

    public void displayReport(Connection conn, Integer pid) throws SQLException {
        Statement st = null;
        ResultSet resultSet = null;
        boolean ref = false;
        try{
            st = conn.createStatement();
            resultSet = st.executeQuery("SELECT * from Report_has_ref WHERE rid=(SELECT rid FROM Patient_has_Report WHERE user_id = "+pid+")");
            if(resultSet.next()==false){
                ref = false;
            }
            else
                ref = true;

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
             //code - description

            resultSet = st.executeQuery("SELECT treatment, discharge_status FROM Report WHERE rid=(SELECT rid FROM Patient_has_Report WHERE user_id =" + pid + ")"); //working

            while(resultSet.next())
            {
                String treatment = resultSet.getString("treatment");
                System.out.println("Treatment Provided:"+treatment);
                String ds = resultSet.getString("discharge_status");
                System.out.println("Discharge Status:"+ds);
            }

            if(ref){

                System.out.println("---Refferral Status---");
                resultSet = st.executeQuery("SELECT fid,referrer FROM Referral_Status WHERE rs_id = (SELECT rs_id FROM Report_has_ref WHERE rid=(SELECT rid FROM Patient_has_Report WHERE user_id = "+pid+"))"); //working

                while(resultSet.next())
                {
                    int fid = resultSet.getInt("fid");
                    System.out.println("Facility Referred to: " + fid);
                    int rid = resultSet.getInt("referrer");
                    System.out.println("Referrer ID: " + rid);
                }

                System.out.println("Referral Reasons:");
                System.out.println("Code" + "\t" + "Service Name" + "\t" + "Description");
                System.out.println(".....................................................");
                resultSet = st.executeQuery("select reason_code, service_name, description FROM Reason WHERE rs_id = (SELECT rs_id FROM Report_has_ref WHERE rid=(SELECT rid FROM Patient_has_Report WHERE user_id = "+pid+"))"); //working
                while(resultSet.next())
                {
                    String rcode = resultSet.getString("reason_code");
                    String sname = resultSet.getString("service_name");
                    String desc = resultSet.getString("description");
                    System.out.println(rcode + "\t" + sname + "\t" + desc);
                }
            }

            System.out.println("-----Negative Experiences-----");
            resultSet = st.executeQuery("SELECT ne_code, user_desc FROM Report_has_Negative WHERE rid=(SELECT rid FROM Patient_has_Report WHERE user_id =" + pid + ")");

            System.out.println("Code" + "\t" + "Description");
            System.out.println("..........................................");

            while(resultSet.next())
            {
                int code = resultSet.getInt("ne_code");
                String desc = resultSet.getString("user_desc");
                System.out.println(code + "\t" + desc);

            }

        }catch (Exception e){
            System.out.println(e.toString());
        }
        finally {
            if(st!=null) {st.close();}
        }
    }

    public void acknowledgeReport(Integer pid){

    }
}
