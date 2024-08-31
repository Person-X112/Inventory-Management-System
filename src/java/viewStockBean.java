/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import p1.DbConn;
import p1.item;

/**
 *
 * @author mzaid
 */
@ManagedBean
@RequestScoped
public class viewStockBean {

    
    private List<item> items = new ArrayList<>();

    private void loadItems() {
        try {
            Connection conn=DbConn.getConnection();
            String query="Select * from items";
            PreparedStatement ps=conn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                item i1=new item(rs.getString(1), rs.getInt(2));
                items.add(i1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<item> getItems() {
        return items;
    }

    
    /**
     * Creates a new instance of viewStockBean
     */
    public viewStockBean() {
        loadItems();
    }

}
