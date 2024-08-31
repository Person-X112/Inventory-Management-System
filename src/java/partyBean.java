/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import p1.DbConn;
import java.sql.*;

/**
 *
 * @author mzaid
 */
@ManagedBean
@RequestScoped
public class partyBean {

    private String parName = "", parAdd = "";

    public String getParName() {
        return parName;
    }

    public String getParAdd() {
        return parAdd;
    }

    public void setParName(String parName) {
        this.parName = parName;
    }

    public void setParAdd(String parAdd) {
        this.parAdd = parAdd;
    }

    private void createPar() {
        try {
            Connection conn = DbConn.getConnection();
            String query="insert into parties values(?,?)";
            PreparedStatement ps=conn.prepareStatement(query);
            ps.setString(1, parName);
            ps.setString(2, parAdd);
            ps.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String submit()
    {
        createPar();
        return "Success";
    }
    /**
     * Creates a new instance of partyBean
     */
    public partyBean() {
    }

}
