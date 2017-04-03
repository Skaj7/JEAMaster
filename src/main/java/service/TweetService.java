package service;

import dao.HeartDAO;
import dao.TweetDAO;
import dao.UserDAO;
import domain.Heart;
import domain.Tweet;
import domain.User;
import org.ocpsoft.pretty.time.PrettyTime;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

/**
 * Created by Kaj Suiker on 12-3-2017.
 */
@Stateless
public class TweetService {

    @Inject
    TweetDAO tweetDAO;

    @Inject
    UserDAO userDAO;

    @Inject
    HeartDAO heartDAO;

    public TweetService() {
    }

    /**
     * post a tweet
     * long userId
     * @param message
     * @param user
     * @return
     */
    public Tweet post(String message, User user) {
        if (message == "")
            throw new NullPointerException("Message not found");

//        User user = userDAO.Find(userId);

        if (user == null)
            throw new NullPointerException("User id not found");

        Tweet tweet = new Tweet();
        tweet.setMessage(message);
        tweet.setOwnerTweet(user);
        List<Tweet> tweets = user.getTweets();
        tweets.add(tweet);
        user.setTweets(tweets);
        userDAO.Edit(user);

        tweetDAO.Save(tweet);
        return tweet;
    }

    /**
     * heart a tweet
     * @param userId
     * @param tweetId
     */
    public void heart(int userId, int tweetId) {
        User user = userDAO.Find(userId);

        if (user == null)
            throw new NullPointerException("User id not found");

        Tweet tweet = tweetDAO.Find(tweetId);

        if (tweet == null)
            throw new NullPointerException("tweet id not found");

        Heart heart = new Heart();
        heart.setOwnerHeart(user);
        heart.setTweetHeart(tweet);

        List<Heart> hearts = user.getHearts();
        hearts.add(heart);
        user.setHearts(hearts);

        hearts = tweet.getHearts();
        hearts.add(heart);
        user.setHearts(hearts);

        userDAO.Edit(user);
        tweetDAO.Edit(tweet);

        heartDAO.Save(heart);
    }

    /**
     * get latest tweet user
     * @param userId
     * @return
     */
    public List<Tweet> latest(long userId) {
        User user = userDAO.Find(userId);

        if (user == null)
            throw new NullPointerException("User id not found");

        List<Tweet> tweets = new ArrayList<>();
        tweets.addAll(user.getTweets());
        //order by data
        Collections.sort(tweets, new Comparator<Tweet>() {
            public int compare(Tweet o1, Tweet o2) {
                Date a = o1.getCreatedAt();
                Date b = o2.getCreatedAt();
                if (a.after(b))
                    return -1;
                else if (a.equals(b)) // it's equals
                    return 0;
                else
                    return 1;
            }
        });

        return tweets;
    }

    /**
     * Get timeline of user
     * @param userId of user
     * @return list of tweets for the timeline
     */
    public List<Tweet> timeline(long userId) {
        User user = userDAO.Find(userId);

        if (user == null)
            throw new NullPointerException("User id not found");

        List<User> following = new ArrayList<>();
        following.addAll(user.getFollowing());
        following.add(user);

        return tweetDAO.GetTimeLines(following);
    }

    /**
     * delete own tweet
     * @param tweetId
     * @param userId
     */
    public void selfDelete(int tweetId, int userId) {
        Tweet tweet = tweetDAO.Find(tweetId);

        if (tweet == null)
            throw new NullPointerException("User id not found");
        if (tweet.getOwnerTweet().getId() != userId){
            throw new NullPointerException("No privilege");
        }

        tweetDAO.Delete(tweet);
    }

    /**
     * edit own tweet
     * @param tweetId
     * @param userId
     */
    public void edit(int tweetId, int userId) {
        Tweet tweet = tweetDAO.Find(tweetId);

        if (tweet == null)
            throw new NullPointerException("User id not found");
        if (tweet.getOwnerTweet().getId() != userId){
            throw new NullPointerException("No privilege");
        }

        tweetDAO.Edit(tweet);
    }

    public List<Tweet> search() {
        return null;
    }

    public List<Tweet> delete() {
        return null;
    }

}
