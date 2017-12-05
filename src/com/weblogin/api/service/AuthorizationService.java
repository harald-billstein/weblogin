package com.weblogin.api.service;

import com.weblogin.api.repositories.AuthorizationImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class AuthorizationService extends AuthorizationImpl{

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response thisMethodNameDoesNotMatter(@PathParam("username") String userName) {
        return Response.ok().entity(getUserCredentials(userName)).build();
    }
}