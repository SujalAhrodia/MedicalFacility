package com.jetbrains;

import java.sql.*;
import java.util.Scanner;

public class SignUp {
    int userinput;

    Scanner in = new Scanner(System.in);
    PreparedStatement pstmt = null;

    public void menuOptions(Connection conn) throws SQLException
    {
        System.out.println("*************");
        System.out.println("Enter the following information:");
        System.out.println("First Name: ");
        String fname= in.nextLine();

        System.out.println("Last Name: ");
        String lname = in.nextLine();

        System.out.println("Date of Birth: (format: YYYY-MM-DD)");
        //to-do
        String dob = in.nextLine();

        System.out.println("** Address ** ");
        System.out.println("Number: ");
        int no = Integer.parseInt(in.nextLine());

        System.out.println("Street Name: ");
        String sname = in.nextLine();

        System.out.println("City: ");
        String city = in.nextLine();

        System.out.println("State: ");
        String state = in.nextLine();

        //System.out.println("Country: ");
        //String country = in.nextLine();

        System.out.println("Phone number: ");
        int ph = in.nextInt();

        System.out.println("*************");
        System.out.println("1.  Sign Up ");
        System.out.println("2.  Go Back ");
        System.out.println("*************");
        System.out.println("Please enter your selection: (1/2) ");

        try
        {
            userinput = in.nextInt();

            switch (userinput) {
                case 1:
                    System.out.println("Sign Up");
                    //store info in db
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery("SELECT seq.NEXTVAL FROM dual");
                    
        			int uid = -1;
        			while (rs.next())
        				uid = rs.getInt("nextval");
                    pstmt = conn.prepareStatement("INSERT INTO Login_user (user_id,Fname, Lname, ph_no , DoB) VALUES (?,?,?,?,?)");
                    pstmt.setInt(1, uid);
                    pstmt.setString (2, fname);
                    pstmt.setString (3, lname);
                    pstmt.setInt (4, ph);
                    pstmt.setDate(5,java.sql.Date.valueOf(dob));
                    pstmt.execute();
		    
                    System.out.println("Signed Up");
                    ResultSet result = st.executeQuery("SELECT seq.NEXTVAL FROM dual");
                    int aid = -1;
                    while (result.next())
                        aid = result.getInt("nextval");
                    pstmt = conn.prepareStatement("INSERT INTO Address(addr_id, addr_number, addr_street, addr_city, addr_state) VALUES (?,?,?,?,?)");
                    pstmt.setInt (1, aid);
                    pstmt.setInt (2, no);
                    pstmt.setString (3, sname);
                    pstmt.setString (4, city);
                    pstmt.setString (5, state);
                    //pstmt.setString (5, country);

                    pstmt.execute();

                    pstmt = conn.prepareStatement("INSERT INTO User_has_Address(user_id,addr_id) VALUES (?,?)");

                    pstmt.setInt (1, uid);
                    pstmt.setInt (2, aid);

                    pstmt.execute();

                    //show msg
                    System.out.println("Profile Created!");

                    //goto sign in page
                    SignIn si = new SignIn();
                    si.menuOptions(conn);
                    break;
                case 2:
                    System.out.println("GO Back");
                    Menu menu = new Menu();
                    menu.menuOptions(conn);
                    break;
                default:
                    System.out.println("Invalid input!");
                    System.out.println("Please read the options carefully");
            }
        }
        catch (Exception e )
        {
        	e.printStackTrace();
        }
        finally
        {
            if(pstmt != null)
            {
                pstmt.close();
            }
        }
    }
}
