package com.jetbrains;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        System.out.println("Establish DB connection here");

        Establish e = new Establish();
        Connection conn = e.myConnection();

        if(conn != null)
        {
            System.out.println("Successful Connection!");

            //Display the menu
            Menu menu = new Menu();
            menu.menuOptions(conn);

            conn.close();
        }
    }
}
