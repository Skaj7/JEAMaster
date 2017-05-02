import dao.TweetDAO;
import dao.UserDAO;
import domain.Tweet;
import domain.User;
import rest.TweetRest;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaj Suiker on 30-3-2017.
 */
@Singleton
@Startup
public class init {

    @Inject
    UserDAO userDAO;

    @Inject
    TweetDAO tweetDAO;

    @PostConstruct
    public void init(){
        List<User> search = userDAO.search("Kaj");
        if(search == null || search.isEmpty()) {
            User user = new User();
            user.setUsername("Kaj");
            user.setPassword("a");
            user.setEmail("kaj@mai.com");

            userDAO.Save(user);
            List<Tweet> tweets = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Tweet tweet = new Tweet();
                tweet.setMessage("" + i);
                tweet.setOwnerTweet(user);
                tweetDAO.Save(tweet);
                tweets.add(tweet);
            }
            //user.setTweets(tweets);
            userDAO.Edit(user);
        }
//            User use = new User();
//            use.setUsername("Sec");
//            use.setPassword("a");
//            use.setEmail("kaj@maooooi.com");
//            userDAO.Save(use);
//
//            Tweet t = new Tweet();
//            t.setMessage("IK ben er");
//            t.setOwnerTweet(use);
//            tweetDAO.Save(t);

        List<User> s = userDAO.search("Sec");

        List<User> aa = new ArrayList<>();
        aa.add(s.get(0));
            search.get(0).setFollowing(aa);
            userDAO.Save(search.get(0));
//            user.setBio("geen bio");
//            user.setSite("http");
//            user.setTweets(tweets);
//            List<User> aa = new ArrayList<>();
//            aa.add(use);
//            user.setFollowing(aa);
//            userDAO.Save(user);




        //}


//
//
//        userDAO.Save(user);
    }
}
