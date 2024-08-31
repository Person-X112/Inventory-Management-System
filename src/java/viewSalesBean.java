/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.*;
import p1.DbConn;
import java.util.List;
import java.util.ArrayList;
import p1.sale;

/**
 *
 * @author mzaid
 */
    
@ManagedBean
@RequestScoped
public class viewSalesBean {

    
    
    
    private List<sale> sales=new ArrayList<>();

    public List<sale> getSales() {
        return sales;
    }
    private List<Integer> test=new ArrayList<>();
    public List<Integer> getTest(){
        return test;
    } 
    
    
    
    /**
     * Creates a new instance of viewSalesBean
     */
    private void imgoinginsane(){
        System.out.println("abcdefg");
    }
    private void loadRecords() {
        try {
            System.out.println("DB method called1");
            Connection conn = DbConn.getConnection();
            String query="select * from orders";
            PreparedStatement ps=conn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                sale s1=new sale(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
                sales.add(s1);
//sales.add(new viewSalesBean.sale(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4)));
                System.out.println("loop entered");
            }
            System.out.println("DB method called");
        } catch (NullPointerException|SQLException | ClassNotFoundException e) {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            e.printStackTrace();
        }
    }

    public viewSalesBean() {
        loadRecords();
        imgoinginsane();
        System.out.println("Bean contrustor called");
        test.add(1);
        test.add(2);
    }

}
