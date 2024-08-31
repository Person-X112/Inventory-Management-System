/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package p1;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author mzaid
 */
public class DbConn {
    private static final String URL="jdbc:mysql://localhost:3306/inventorymanagement";
    private static final String USER="root";
    private static final String PASS="zaid";
    private static final String DRIVER_CLASS="com.mysql.cj.jdbc.Driver";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException 
    {
        Class.forName(DRIVER_CLASS);
        return DriverManager.getConnection(URL,USER,PASS);
    }
}
