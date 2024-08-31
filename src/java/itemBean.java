/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import p1.DbConn;
import java.sql.*;
import javax.faces.context.FacesContext;
import javax.faces.application.NavigationHandler;

/**
 *
 * @author mzaid
 */
@ManagedBean
@RequestScoped
public class itemBean {

    private String itemName = "";

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void submit()
    {
        createItem();
    }
    private void createItem() {
        
        try {
            Connection conn = DbConn.getConnection();
            String query="insert into items values(?,0)";
            System.out.println("hello world");
            
            PreparedStatement ps=conn.prepareStatement(query);
            ps.setString(1, itemName);
            ps.executeUpdate();
            FacesContext fc=FacesContext.getCurrentInstance();
            NavigationHandler nh=fc.getApplication().getNavigationHandler();
            nh.handleNavigation(fc, null, "Success?faces-redirect=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new instance of itemBean
     */
    public itemBean() {
    }

}
