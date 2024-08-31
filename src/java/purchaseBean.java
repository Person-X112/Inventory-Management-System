/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import p1.DbConn;
import javax.faces.context.FacesContext;
import javax.faces.application.NavigationHandler;

/**
 *
 * @author mzaid
 */
@ManagedBean
@SessionScoped
public class purchaseBean implements Serializable {

    /**
     * Creates a new instance of purchaseBean
     */
    private String selectedParty = "";
    private String selectedItem = "";
    private List<String> parties = new ArrayList<>();
    private HashMap<String, Integer> items = new HashMap<>();
    private int itemQuan = 0;
    private int price = 0;

    //Database connection
    private Connection connDB() {
        try {
            Connection conn = DbConn.getConnection();
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //getters
    public String getSelectedParty() {
        return selectedParty;
    }

    public Set getItems() {
        return items.keySet();
    }

    public List getParties() {
        return parties;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public int getItemQuan() {
        return itemQuan;
    }

    public int getPrice() {
        return price;
    }

    //setters
    public void setSelectedParty(String selectedParty) {
        this.selectedParty = selectedParty;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void setItemQuan(int itemQuan) {
        this.itemQuan = itemQuan;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //fetch parties from db
    private void itemsFromDB(Connection conn) {

        try {
            String query = "select * from items";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.put(rs.getString(1), rs.getInt(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //fetch items from db
    private void partiesFromDB(Connection conn) {
        try {
            String query = "Select parname from parties";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                parties.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //update records in db
    public void updateRecords() {
        try {
            Connection conn=DbConn.getConnection();
            String query1 = "update items set stock=stock + ? where itemName=?";
            String query2 = "insert into purorders values(?,?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(query1);
            PreparedStatement ps2 = conn.prepareStatement(query2);
            ps1.setInt(1, itemQuan);
            ps1.setString(2, selectedItem);
            
            ps2.setString(1, selectedParty);
            ps2.setString(2, selectedItem);
            ps2.setInt(3, itemQuan);
            ps2.setInt(4, price);
            
            ps1.executeUpdate();
            ps2.executeUpdate();
            
            FacesContext fc=FacesContext.getCurrentInstance();
            NavigationHandler nh=fc.getApplication().getNavigationHandler();
            
            nh.handleNavigation(fc, null, "Success?faces-redirect=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public purchaseBean() {
        Connection conn = connDB();
        itemsFromDB(conn);
        partiesFromDB(conn);
    }

}
