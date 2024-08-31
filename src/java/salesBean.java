/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import p1.DbConn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Set;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import javax.faces.application.NavigationHandler;

/**
 *
 * @author mzaid
 */
@ManagedBean
@RequestScoped
public class salesBean implements Serializable {

    public List<String> parties = new ArrayList<>();
    public HashMap<String, Integer> items = new HashMap<>();

    /**
     * Creates a new instance of salesBean
     */
    public Set<String> getItems() {
        return items.keySet();
    }

    public List<String> getParties() {
        return parties;
    }
    private String selectedParty = "";
    private String partyAddress="";
    private String selectedItem = "";
    private int itemQuan = 0;
    private int price = 0;
    private boolean errorOccurred = false;
    private String errorMessage = "";
    
    public boolean isErrorOccurred() {
        return errorOccurred;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setItemQuan(int itemQuan) {
        this.itemQuan = itemQuan;
    }

    public int getItemQuan() {
        return itemQuan;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void calcQuan() {

    }

    public void setSelectedParty(String selectedParty) {
        this.selectedParty = selectedParty;
    }

    public String getselectedParty() {
        return selectedParty;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    private void partiesFromDB(Connection conn) {
        try {
            String getPartiesQuery = "Select parname from parties";
            PreparedStatement getPartiesPS = conn.prepareCall(getPartiesQuery);
            ResultSet partiesRS = getPartiesPS.executeQuery();
            while (partiesRS.next()) {
                parties.add(partiesRS.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void itemsFromDB(Connection conn) {
        try {
            String getItemsQuery = "Select * from items";
            PreparedStatement getItemsPS = conn.prepareCall(getItemsQuery);
            ResultSet itemsRS = getItemsPS.executeQuery();
            while (itemsRS.next()) {
                items.put(itemsRS.getString(1), itemsRS.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void submit() {
        if (itemQuan > items.get(selectedItem)) {
            errorOccurred = true;
            errorMessage = "Item Quantity greater than available stock";
        } else {
            createOrder();
            
            FacesContext fc = FacesContext.getCurrentInstance();
            NavigationHandler nh = fc.getApplication().getNavigationHandler();
            nh.handleNavigation(fc, null, "salesPrint");
        }
    }
    public void goSales()
    {
        FacesContext fc1 = FacesContext.getCurrentInstance();
        NavigationHandler nh1 = fc1.getApplication().getNavigationHandler();

        nh1.handleNavigation(fc1, null, "Sales?faces-redirect=true");
    }
   
    public void goBack()
    {
        FacesContext fc1 = FacesContext.getCurrentInstance();
        NavigationHandler nh1 = fc1.getApplication().getNavigationHandler();

        nh1.handleNavigation(fc1, null, "InvMan?faces-redirect=true");
    }
    public String getPartyAddress()
    {
        return partyAddress;
    }

    private void createOrder() {
        try {
            String createOrderQuery = "insert into Orders(itemname, quantity, rate, parname) values(?,?,?,?)";
            Connection conn = DbConn.getConnection();
            PreparedStatement ps = conn.prepareStatement(createOrderQuery);
            ps.setString(1, selectedItem);
            ps.setInt(2, itemQuan);
            ps.setInt(3, price);
            ps.setString(4, selectedParty);
            ps.executeUpdate();
            ps.close();

            //descreasing quantity from items table
            String descreaseQuanQuery = "update items set stock=stock-? where itemname=?";
            PreparedStatement ps1 = conn.prepareStatement(descreaseQuanQuery);
            ps1.setInt(1, itemQuan);
            ps1.setString(2, selectedItem);
            ps1.executeUpdate();
            
            //fetching party address
            String getPartyAddress="select paraddress from parties where parname=?";
            PreparedStatement ps3=conn.prepareStatement(getPartyAddress);
            ps3.setString(1, selectedParty);
            ResultSet rs3=ps3.executeQuery();
            while(rs3.next())
            {
                partyAddress=rs3.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public salesBean() {
        try {
            Connection conn = DbConn.getConnection();
            partiesFromDB(conn);
            itemsFromDB(conn);
            System.out.println(items);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
