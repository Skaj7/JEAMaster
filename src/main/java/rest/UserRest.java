package rest;

import dao.UserDAO;
import domain.Tweet;
import domain.User;
import service.TweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.jws.soap.SOAPBinding;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Kaj Suiker on 12-3-2017.
 */
@Stateless
@Path("user")
public class UserRest {

    @Inject
    UserService userService;

    public UserRest() {
    }

    @POST
    @Produces("application/json")
    @Path("create")
    public User Create(JsonObject in ){
        return userService.Create(in.getString("email"), in.getString("username"), in.getString("password"));
    }
}
