/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import client.login_register_design.LoginRegisterForm;
import database.My_CNX;
import client.login.LoginInterface;

/**
 *
 * @author Pooja
 */
public class LoginImplement extends UnicastRemoteObject implements LoginInterface{

    public LoginImplement() throws RemoteException
    {
        
    }
    
    public boolean getLogin(String username, String password) throws RemoteException {
        boolean found = false;
/*        
        try{
            if(user.equals("admin") && pass.equals("123"))
                return found= true;
            else
                return found = false;
            
        }
        catch( Exception ex)
        {
            ex.printStackTrace();
        }
*/        
        
        PreparedStatement st;
        ResultSet rs;
        
        
        // create a select query to check if the username and the password exist in the database
        String query = "SELECT username, password FROM `users2` WHERE username = ? AND password = ?";
        
        try {
            
            st =  My_CNX.getConnection().prepareStatement(query);
            
            st.setString(1, username);
            st.setString(2, password);

                System.out.println("username : "+username+"  password : "+password);             
            rs = st.executeQuery();
            System.out.println(query+" \n"+st+"\n"+rs);  

            if( rs.next() )      
            {
                System.out.println(rs.getString(1)+"  "+rs.getString(2)); 
                found = true;
            }
    
        } catch (SQLException ex) {
            Logger.getLogger(LoginRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return found;
    }
    
}
