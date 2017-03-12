package service;

import dao.TweetDAO;
import dao.UserDAO;
import domain.Tweet;
import domain.User;

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

    public List<Tweet> latestTweets(int userId){

        User user = userDAO.Find(userId);

        if (user == null)
            throw new NullPointerException("User id not found");

        List<User> following = user.getFollowing();

        return tweetDAO.GetLatestTweets(following);
    }
}
