/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.jca.GetInstance;

/**
 *
 * @author Ahmed
 */
public class ConnexionDB {
    
    
     private  static ConnexionDB instance=null;
    String url="JDBC:mysql://localhost/pi";
    String username="root";
    String password="";
    Connection connection=null;
    private ConnexionDB() {
        try {
            connection=DriverManager.getConnection(url, username, password);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionDB.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    public static ConnexionDB getInstance(){
        if(instance==null){
            instance=new ConnexionDB();
        }
     return instance;
    }
    public Connection getConnection(){
        return connection;
    }
    
}
