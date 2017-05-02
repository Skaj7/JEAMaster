package rest;

import domain.Tweet;
import domain.User;
import interceptor.InterceptorClass;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import service.TweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.json.JsonObject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Kaj Suiker on 10-3-2017.
 *
 */
@Stateless
@Path("tweet")
public class TweetRest {

    @Inject
    TweetService tweetService;

//    @POST
//    @Produces("application/json")
//    @Path("tweet/{userId}")
//    @Interceptors(InterceptorClass.class)
//    public Tweet post(@PathParam("userId") int id , String in){
//        Tweet tweet = tweetService.post(in, id);
//        tweet.getOwnerTweet().setTweets(null);
//        return tweet;
//    }

    @POST
    @Produces("application/json")
    @Path("heart")
    public void heart(JsonObject in) throws JSONException {
        tweetService.heart(in.getInt("userId"),in.getInt("tweetId"));
    }

    @POST
    @Produces("application/json")
    @Path("selfDelete")
    public void selfDelete(JSONObject in) throws JSONException {
        tweetService.selfDelete(in.getInt("tweetId"), in.getInt("userId"));
    }

    @POST
    @Produces("application/json")
    @Path("edit")
    public void edit(JSONObject in) throws JSONException {
        tweetService.edit(in.getInt("tweetId"), in.getInt("userId"));
    }

    @GET
    @Produces("application/json")
    @Path("timeline/{userId}")
    public List<Tweet> getTimeline(@PathParam("userId") int id){
        return tweetService.timeline(id);
    }

    @GET
    @Produces("application/json")
    @Path("ownLatest/{userId}")
    public List<Tweet> getLatest(@PathParam("userId") int id){
        return tweetService.latest(id);
    }





    @POST
    @Produces("application/json")
    @Path("{userId}")
    public void post(Tweet tweet, @PathParam("userId") int id){
        tweetService.post(tweet.getMessage(),id);
    }




    @GET
    @Produces("application/json")
    @Path("search")
    public List<Tweet> getSearch(){
        return tweetService.search();
    }

    @POST
    @Produces("application/json")
    @Path("delete")
    public void delete(){
        tweetService.delete();
    }
}
