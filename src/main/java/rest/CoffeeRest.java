package rest;

import dao.TweetDAO;
import domain.Tweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Kaj Suiker on 10-3-2017.
 */
@Stateless
@Path("coffee")
public class CoffeeRest {

    @Inject
    TweetDAO tDAO;

    @GET
    @Produces("application/json")
    @Path("latest")
    public Tweet getLatest(){
        Tweet tweet = new Tweet("nee");
        tDAO.persist();
        return tweet;
    }

}
