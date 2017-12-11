package com.weblogin.beans.view;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Class handling menu items
 * 
 * @author Harald & Stefan
 *
 */
@ManagedBean
@ApplicationScoped
public class NavigationItems {

  private List<Item> items;
  private List<Item> navigationItems;

  private static final String LOGIN_PATH = "login.xhtml";
  private static final String SIGNUP_PATH = "signup.xhtml";
  private static final String PROFILE_PATH = "profile.xhtml";
  private static final String GALLARY_PATH = "gallery.xhtml";

  private FacesContext context = FacesContext.getCurrentInstance();

  public NavigationItems() {

    context = FacesContext.getCurrentInstance();

    items = new ArrayList<>();
    items.add(new Item("Login", LOGIN_PATH, false));
    items.add(new Item("Signup", SIGNUP_PATH, false));
    items.add(new Item("Profile", PROFILE_PATH, true));
    items.add(new Item("Gallery", GALLARY_PATH, null));
  }


  /**
   * Method collecting menu items for the active view
   * 
   * @return list of menu items
   */
  public List<Item> getItems() {

    navigationItems = new ArrayList<>();
    String path = context.getViewRoot().getViewId();

    if (path.equals("/profile.xhtml") || path.equals("/gallery.xhtml")) {
      // USER LOGGED IN
      for (Item item : items) {
        System.out.println(item.getUrl());
        if (item.isIsloggedinAccessable() == null || item.isIsloggedinAccessable()) {
          navigationItems.add(item);
        }
      }
    } else {
      // USER LOGGED OUT
      for (Item item : items) {
        if (item.isIsloggedinAccessable() == null || !item.isIsloggedinAccessable()) {
          navigationItems.add(item);
        }
      }
    }
    return navigationItems;
  }
}
