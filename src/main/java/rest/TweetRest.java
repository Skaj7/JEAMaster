package rest;

import dao.TweetDAO;
import domain.Tweet;
import domain.User;
import service.TweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Kaj Suiker on 10-3-2017.
 */
@Stateless
@Path("tweet")
public class TweetRest {

    @Inject
    TweetService tweetService;

    @GET
    @Produces("application/json")
    @Path("latest")
    public List<Tweet> getLatest(){
        return tweetService.latestTweets(3);
    }

}
