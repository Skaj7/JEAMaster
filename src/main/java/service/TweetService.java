package service;

import dao.HeartDAO;
import dao.TweetDAO;
import dao.UserDAO;
import domain.Heart;
import domain.Tweet;
import domain.User;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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

    public Tweet post(String message, int userId) {
        if (message == "")
            throw new NullPointerException("Message not found");

        User user = userDAO.Find(userId);

        if (user == null)
            throw new NullPointerException("User id not found");

        Tweet tweet = new Tweet();
        tweet.setMessage(message);
        tweet.setOwnerTweet(user);
        tweetDAO.Save(tweet);
        return tweet;
    }

    public void heart(int userId, int tweetid) {
        User user = userDAO.Find(userId);

        if (user == null)
            throw new NullPointerException("User id not found");

        Tweet tweet = tweetDAO.Find(tweetid);

        if (tweet == null)
            throw new NullPointerException("tweet id not found");

        Heart heart = new Heart();
        heart.setOwnerHeart(user);
        heart.setTweetHeart(tweet);

        heartDAO.Save(heart);
    }

    public List<Tweet> latest(int userId) {
        User user = userDAO.Find(userId);

        if (user == null)
            throw new NullPointerException("User id not found");

        return tweetDAO.Latest(user);
    }

    /**
     * Get timeline of user
     * @param userId of user
     * @return list of tweets for the timeline
     */
    public List<Tweet> timeline(int userId) {
        User user = userDAO.Find(userId);

        if (user == null)
            throw new NullPointerException("User id not found");

        List<User> following = user.getFollowing();

        return tweetDAO.GetTimeLines(following);
    }

    public List<Tweet> search() {
        return null;
    }

    public List<Tweet> selfDelete() {
        return null;
    }

    public List<Tweet> delete() {
        return null;
    }

    public List<Tweet> edit() {
        return null;
    }
}
