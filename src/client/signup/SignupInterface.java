/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.signup;

import java.io.InputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Pooja
 */
public interface SignupInterface extends Remote {
    
        public boolean verifyFields(String username, String password, String confirm_password, String email,
                String phone_no, String gender, String dob, String address) throws RemoteException;
        
        public boolean checkUsername(String username) throws RemoteException;
        
        public boolean getSignup(String username, String pass1, String email, String phone_no, String gender, String bdate, String image_path, String address) throws RemoteException ;
        
}
