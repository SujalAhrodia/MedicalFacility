package com.jetbrains;
import java.sql.*;

public class establish {

    public Connection myConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useTimezone=true&serverTimezone=UTC","root","12345678");
            Statement s = con.createStatement();
            return con;
        }
        catch (Exception e)
        {
            System.out.println("here");

            System.out.println(e.getMessage());
        }

        return null;
    }
}
