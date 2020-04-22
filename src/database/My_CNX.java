
package database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pooja
 */
public class My_CNX {

    private static final String servername = "localhost";
    private static final String username = "root";
    private static final String dbname = "users_db";
    private static final Integer portnumber = 3306;
    private static final String password =  "";
    
    public static Connection getConnection()
    {
        Connection cnx = null;
        
        MysqlDataSource datasource = new MysqlDataSource();
        
        datasource.setServerName(servername);
        datasource.setUser(username);
        datasource.setPassword(password);
        datasource.setDatabaseName(dbname);
        datasource.setPortNumber(portnumber);

        try {
            cnx = datasource.getConnection();
            
/*                        Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/users_db", "root", "");
*/
        } catch (SQLException ex) {
            Logger.getLogger(" Get Connection -> " + My_CNX.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cnx;
    }
/*
    public static Connection getConnection() {
        Connection cnx = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //cnx = DriverManager.getConnection("jdbc:mqsql://localhost/users_db","root", "");
            
            
            cnx=DriverManager.getConnection("jdbc:mysql://127.0.0.1/users_db","root", "");
        }
        catch( Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cnx;
    }
*/    
}
