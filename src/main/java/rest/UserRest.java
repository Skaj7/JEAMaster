package rest;

import java.net.*;

import com.google.gson.Gson;
import com.sun.jndi.toolkit.url.Uri;
import dao.UserDAO;
import domain.Tweet;
import domain.User;
import service.TweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.jws.soap.SOAPBinding;
import javax.security.auth.login.FailedLoginException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaj Suiker on 12-3-2017.
 */
@Stateless
@Path("user")
public class UserRest {

    @Inject
    private UserService userService;

    @Context
    private UriInfo uriInfo;


    public UserRest() {
    }

    @POST
    @Produces("application/json")
    @Path("search")
    public Response search(User user){
        List<User> users = userService.search(user.getUsername());

        return Response.ok(users.get(0)).build();
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

    @GET
    @Produces("application/json")
    @Path("teeeest")
    public Response Create(){
        User user = new User();
        user.setId(1L);
        user.setUsername("Kaj");
        List<Tweet> list = new ArrayList<>();
        Tweet tweet = new Tweet();
        tweet.setMessage("ik");
        //tweet.setOwnerTweet(user);
        list.add(tweet);
        //user.setTweets(list);
        //tweet.setOwnerTweet(user);

        //solution
        for(Tweet t : list){
            //t.getOwnerTweet().setTweets(null);
        }

        return Response.ok(list).build();
    }

    @POST
    @Produces("application/json")
    @Path("login")
    public Response Login(User user) throws FailedLoginException {
        User currentUser = userService.login(user.getUsername(), user.getPassword());
        return Response.ok(currentUser).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{userId}")
    public Response User(@PathParam("userId") int id){
        User userById = userService.getUser(id);

        return Response.ok(userById).build();
    }
}
