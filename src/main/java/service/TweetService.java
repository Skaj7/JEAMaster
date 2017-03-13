package service;

import dao.TweetDAO;
import dao.UserDAO;
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

    public TweetService() {
    }

    public List<Tweet> post() {
        return null;
    }

    public List<Tweet> heart() {
        return null;
    }

    public List<Tweet> delete() {
        return null;
    }

    public List<Tweet> edit() {
        return null;
    }

    public List<Tweet> latest(int i) {
        return null;
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
}
