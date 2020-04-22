/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import client.login_register_design.SignupForm;
import client.signup.SignupInterface;
import database.MyConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Pooja
 */
public class SignupImplement  extends UnicastRemoteObject implements SignupInterface {
    
    public SignupImplement() throws RemoteException{
        
    }
    // create a funcitn to verify the empty fields
    public boolean verifyFields(String uname, String pass1, String pass2, String email, String phone, String gender, String dob, String address) throws RemoteException
    {
        try{
               if(uname.trim().equals("") || pass1.trim().equals("") ||
               pass2.trim().equals("") || phone.trim().equals("") || 
               email.trim().equals("") || address.trim().equals("") ||
               dob == ""    )
            {
//                JOptionPane.showMessageDialog(null, "One or More Fields are Empty", "Empty Fields nnnnnnnn", 2);
                            return false;
            }
            // check if the two password are equal
            else if( !pass1.equals(pass2))
            {
                JOptionPane.showMessageDialog( null,  "Password doesn't Match", "Confirm Password", 2);
                return false;
            }
        }catch( Exception e){
            JOptionPane.showMessageDialog(null, "One or more Fields are Empty", "Empty Fields", 2);
        }
        // if everything is ok
            return true;
        }
    
    // create a funciton to check if the entered username already exists in the database
    public boolean checkUsername(String username){
        PreparedStatement st;
        ResultSet rs;
        boolean username_exist = false;
        
        String query = "SELECT * FROM `users2` WHERE 'username' = ?";
        
        try{
            st = (PreparedStatement) MyConnection.getConnection().prepareStatement(query);
            
            st.setString(1, username);
            rs = st.executeQuery();
        
            if(rs.next())
            {
                username_exist = true;
                JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username_Failed", 2);
                return username_exist;
            }
        }catch ( SQLException ex){
            Logger.getLogger( SignupForm.class.getName()).log( Level.SEVERE, null, ex);
        }
        return username_exist;
    }

    public boolean getSignup(String username, String pass1, String email, String phone_no, String gender, String bdate, String image_path, String address) throws RemoteException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        boolean signup_flag = false;
        
        PreparedStatement ps;
        ResultSet rs;
        String signupUserQuery = "INSERT INTO `users2`( `username`, `password`, `email`, `phone_no`, `gender`, `dob`, `image`, `address`) VALUES (?,?,?,?,?,?,?,?)";
                
        try{
            ps = MyConnection.getConnection().prepareStatement(signupUserQuery);
                
            ps.setString(1, username);
            ps.setString(2, pass1);
            ps.setString(3, email);
            ps.setString(4, phone_no);
            ps.setString(5, gender);
            ps.setString(6, bdate);
            
/*            //if username is available or already taken
                String query = "SELECT * FROM users2 WHERE username = ?";
                PreparedStatement st;
                ResultSet rs1;
                st= MyConnection.getConnection().prepareStatement(query);
                st.setString(1, username);
                rs1 = st.executeQuery();
                if(rs1.next())
                {
                        JOptionPane.showMessageDialog(null, "Username is already taken. Please choose another one", "Username Taken", 3);   
                }
*/
/*            try {
*/                        // save the image as blob in the database
                        /*if(image != null){
                            ps.setBlob(7, image);
                        }*/
                        try {
                            
            if(image_path != null){
                 InputStream image = new FileInputStream(new File(image_path));
                 ps.setBlob(7, image);
            }
        
                        else
                            ps.setNull(7, java.sql.Types.NULL);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Image storage error","Error to save", 2);
            Logger.getLogger(SignupForm.class.getName()).log(Level.SEVERE, null, ex);
        }
                        ps.setString(8, address);
                        if( ps.executeUpdate() != 0){
//                            JOptionPane.showMessageDialog(null, "Your Account has been Created");
                            signup_flag = true;
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Error : Check Your Information");
                        }
/*                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SignupForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
*/        
            if(ps.executeUpdate() > 0){
                signup_flag = true;
                JOptionPane.showMessageDialog(null, "New User Add");
            }
            
            }
            catch( SQLException ex){
                Logger.getLogger(SignupForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    return signup_flag;
    }
}
    
