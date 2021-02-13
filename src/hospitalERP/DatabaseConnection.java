package hospitalERP;

import java.sql.*;
import javax.swing.*;

public class DatabaseConnection {
    
     Connection conn;
    public static Connection db_connect(){
        try{
            Connection conn  =  DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Aashiq\\Documents\\NetBeansProjects\\HopitalManagemantSystemSCIT\\HospitalManagementSystemSCIT.sqlite");
//            JOptionPane.showMessageDialog(null,"Connection Established" );
            return conn;
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);     
       return null;
        }  
    }
}
