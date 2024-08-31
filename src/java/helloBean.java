/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import p1.DbConn;
/**
 *
 * @author mzaid
 */
@ManagedBean
@RequestScoped
public class helloBean {
    private String name;
    public String greeting;
    
    //setter and getter for name
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name=name;
    }
    
    //getter for greeting
    public String getGreeting()
    {
        return greeting;
    }
    
    public void sayHello()
    {
        try(Connection connection=DbConn.getConnection())
        {
            
            String query="select * from name";
            PreparedStatement ps=connection.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                greeting=rs.getString("name");
            }else{
                greeting="Hello "+name+"!";
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            greeting="hello";
        }
        
    }
    /**
     * Creates a new instance of helloBean
     */
    
}
