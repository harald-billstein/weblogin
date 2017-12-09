package com.weblogin.beans.view;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@ApplicationScoped
public class NavigationItems {

  private List<Item> items;
  private List<Item> navigationItems;

  private static final String LOGIN_PATH = "login.xhtml";
  private static final String SIGNUP_PATH = "signup.xhtml";
  private static final String PROFILE_PATH = "profile.xhtml";
  private static final String GALLARY_PATH = "gallery.xhtml";
  
  FacesContext context = FacesContext.getCurrentInstance();
  HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

  public NavigationItems() {
    
    FacesContext context = FacesContext.getCurrentInstance();
    session = (HttpSession) context.getExternalContext().getSession(true);
    
    
    items = new ArrayList<>();
    items.add(new Item("Login", LOGIN_PATH, false));
    items.add(new Item("Signup", SIGNUP_PATH, false));
    items.add(new Item("Profile", PROFILE_PATH, true));
    items.add(new Item("Gallery", GALLARY_PATH, false));
  }

  public List<Item> getItems() {

    navigationItems = new ArrayList<>();

    // TODO hooka tag i en attribut typ has access eller nått, då man kan ha token men den är invalied
    if (session.getAttribute("token") == null) {
      // USER LOGGED OUT
      for (Item item : items) {
        if (    !item.isIsloggedinAccessable()  ) {
          System.out.println("add non profile item");
          navigationItems.add(item);
        }
      }
    } else {
      // USER LOGGED IN
      for (Item item : items) {
        System.out.println(item.getUrl());
        if (item.isIsloggedinAccessable()) {
          System.out.println("add profile item");
          navigationItems.add(item);
        }
      }
    }
    
    return navigationItems;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }
}
