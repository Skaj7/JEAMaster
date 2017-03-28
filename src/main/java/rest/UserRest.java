package rest;

import java.net.*;
import dao.UserDAO;
import domain.Tweet;
import domain.User;
import service.TweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.jws.soap.SOAPBinding;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaj Suiker on 12-3-2017.
 */
@Stateless
@Path("users")
public class UserRest {

    @Inject
    private UserService userService;

    @Context
    private UriInfo uriInfo;


    public UserRest() {
    }


    @POST
    public Response addUsers(User user){
        userService.save(user);

        URI uri= uriInfo.getAbsolutePathBuilder().path(user.getId().toString()).build();
        return Response.created(uri).build();
    }

    @POST
    @Produces("application/json")
    @Path("create")
    public User Create(JsonObject in ){
        return userService.Create(in.getString("email"), in.getString("username"), in.getString("password"));
    }
}
