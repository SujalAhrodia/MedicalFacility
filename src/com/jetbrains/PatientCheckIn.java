package com.jetbrains;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PatientCheckIn
{
    String userinput;

    Scanner in = new Scanner(System.in);
    ResultSet temp = null;

    public void menuOptions(Connection conn) throws SQLException
    {
        Statement st = null;
        try
        {
            st = conn.createStatement();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            if (st != null) {
                st.close();
            }
        }
    }
}
