/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Pooja
 */
public class MyConnection {
    private static Connection con;
    //create a function to connect with mysql database
    public static Connection getConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/users_db", "root", "");
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }
}
