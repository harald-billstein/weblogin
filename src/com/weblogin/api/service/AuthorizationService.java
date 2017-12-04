package com.weblogin.api.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/login")
public class AuthorizationService {

    @GET
    @Path("/{username}")
  //  @Produces(MediaType.APPLICATION_JSON)
    public String getHashFromUserName(@PathParam("username") String userName) {
        System.out.println("inside get");
        return "test :" + userName;
    }

}
