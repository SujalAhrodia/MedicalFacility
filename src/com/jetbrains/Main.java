package com.jetbrains;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        System.out.println("Establish DB connection here");


        establish e = new establish();
        Connection conn = e.myConnection();

        if(conn != null)
        {
            System.out.println("Successful Connection!");
            conn.close();
        }
    }
}
