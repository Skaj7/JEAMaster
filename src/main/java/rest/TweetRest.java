package rest;

import JSON.HeartJSON;
import domain.Tweet;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import service.TweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.beans.Transient;
import java.util.List;

/**
 * Created by Kaj Suiker on 10-3-2017.
 */
@Stateless
@Path("tweet")
public class TweetRest {

    @Inject
    TweetService tweetService;

    @POST
    @Produces("application/json")
    @Path("tweet")
    public Tweet post(JsonObject in ){
        Tweet tweet = tweetService.post(in.getString("message"), in.getInt("id"));
        tweet.getOwnerTweet().setTweets(null);
        return tweet;
    }

    @POST
    @Produces("application/json")
    @Path("heart")
    public void heart(){
        tweetService.heart(1,1);
    }

    @POST
    @Produces("application/json")
    @Path("selfDelete")
    public List<Tweet> selfDelete(){
        return tweetService.selfDelete();
    }

    @POST
    @Produces("application/json")
    @Path("delete")
    public List<Tweet> delete(){
        return tweetService.delete();
    }

    @POST
    @Produces("application/json")
    @Path("edit")
    public List<Tweet> edit(){
        return tweetService.edit();
    }

    @GET
    @Produces("application/json")
    @Path("timeline")
    public List<Tweet> getTimeline(){
        return tweetService.timeline(3);
    }

    @GET
    @Produces("application/json")
    @Path("ownLatest")
    public List<Tweet> getLatest(){
        return tweetService.latest(3);
    }

    @GET
    @Produces("application/json")
    @Path("search")
    public List<Tweet> getOwnLatest(){
        return tweetService.search();
    }
}
