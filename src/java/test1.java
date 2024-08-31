/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.context.FacesContext;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author mzaid
 */
@ManagedBean
@RequestScoped
public class test1 {
    public String user,pass;
    private String auser="Zaid", apass="team";
    FacesContext fc=FacesContext.getCurrentInstance();
    NavigationHandler nh=fc.getApplication().getNavigationHandler();
    /**
     * Creates a new instance of test1
     */
    public String getUser()
    {
        return user;
    }
    public void setUser(String user)
    {
        this.user=user;
    }
    public String getPass()
    {
        return pass;
    }
    
    public void setPass(String pass)
    {
        this.pass=pass;
    }
    
    public void check()
    {
        
        if(user.equals(auser)&&pass.equals(apass))
        {
            nh.handleNavigation(fc, null, "InvMan?faces-redirect=true");
        }
        else
        {
            nh.handleNavigation(fc, null, "failedAuth?faces-redirect=true");
        }
    }
    public void goBack()
    {
        nh.handleNavigation(fc, null, "index?faces-redirect=true");
    }
    public test1() {
    }
    
}
