/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author mzaid
 */
@ManagedBean
@RequestScoped
public class mainPageBean {

    /**
     * Creates a new instance of mainPageBean
     */
    private String selectedButton;
    
    public String getSelectedButton()
    {
        return selectedButton;
    }
    public void setSelectedButton(String selectedButton)
    {
        this.selectedButton=selectedButton;
    }
    
    public void redirect()
    {
        switch(selectedButton)
        {
            case "purchase":
                redirectPage("Purchase");
                break;
            case "sale":
                redirectPage("Sales");
                break;
            case "item":
                redirectPage("/Item.xhtml");
                break;
            case "party":
                redirectPage("/Party.xhtml");
                break;
            case "viewSales":
                redirectPage("/ViewSales.xhtml");
                break;
            case "viewPurchases":
                redirectPage("/ViewPurchase.xhtml");
                break;
            case "viewStock":
                redirectPage("/ViewStock.xhtml");
                break;
        }/*
        if(selectedButton.equals("purchase"))
        {
            redirectPage("Purchase");
        }
        else if(selectedButton.equals("sales"))
        {
            redirectPage("sales");
        }*/
    }
    public void redirectPage(String page)
    {
        try{
           FacesContext fc= FacesContext.getCurrentInstance();
           NavigationHandler nh=fc.getApplication().getNavigationHandler();
           nh.handleNavigation(fc, null, page+"?faces-redirect=true");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public mainPageBean() {
    }
    
}
