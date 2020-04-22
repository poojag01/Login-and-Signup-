/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Pooja
 */
public class LoginServer {
    public static void main(String arg[])
    {
        try{
            Registry reg = LocateRegistry.createRegistry(9000);     //9000 can also be replaced by 1099
            LoginImplement lp = new LoginImplement();
            reg.rebind("login", lp);
            System.out.println("Server is Ready  ");
            
        }
        catch( Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
