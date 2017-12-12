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

  private static final String LOGIN_PATH = "login.xhtml";
  private static final String SIGNUP_PATH = "signup.xhtml";
  private static final String PROFILE_PATH = "profile";
  private static final String GALLARY_PATH = "gallery.xhtml";

  private FacesContext context = FacesContext.getCurrentInstance();

  public NavigationItems() {
    context = FacesContext.getCurrentInstance();
  }

  public List<Item> createDefaultItems() {
    List<Item> defaultItems = new ArrayList<>();
    defaultItems.add(new Item("Gallery", GALLARY_PATH));

    return defaultItems;
  }

  public List<Item> createLogedinItems() {
    List<Item> logedinItems = new ArrayList<>();
    context.getViewRoot().getId();
    String xhtmlPage = context.getViewRoot().getViewId();

    if (xhtmlPage.equals("/profile.xhtml") || xhtmlPage.equals("/gallery.xhtml")) {
      System.out.println("Login items created");
      System.out.println("List size: " + logedinItems.size());
      logedinItems.add(new Item("Profile", PROFILE_PATH));
    }
    return logedinItems;
  }

  public List<Item> createLogedoutItems() {
    List<Item> logedoutItems = new ArrayList<>();
    String xhtmlPage = context.getViewRoot().getViewId();

    if (xhtmlPage.equals("/login.xhtml") || xhtmlPage.equals("/signup.xhtml")) {
      logedoutItems.add(new Item("Login", LOGIN_PATH));
      logedoutItems.add(new Item("Signup", SIGNUP_PATH));
    }
    return logedoutItems;
  }
}
