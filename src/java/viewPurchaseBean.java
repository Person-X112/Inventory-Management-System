/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.*;
import p1.DbConn;
import p1.sale;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author mzaid
 */
@ManagedBean
@RequestScoped
public class viewPurchaseBean {

    private List<sale> purchases = new ArrayList<>();

    public List<sale> getPurchases() {
        return purchases;
    }

    private void loadPurchases() {
        try {
            Connection conn = DbConn.getConnection();
            String query="select * from purorders";
            PreparedStatement ps=conn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                purchases.add(new sale(rs.getString(1),rs.getInt(3),rs.getInt(4),rs.getString(2)));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new instance of viewPurchaseBean
     */
    public viewPurchaseBean() {
        loadPurchases();
    }

}
