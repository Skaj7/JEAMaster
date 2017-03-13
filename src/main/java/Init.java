import dao.TweetDAO;
import dao.UserDAO;
import domain.Tweet;
import domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaj Suiker on 12-3-2017.
 */
@Singleton
@Startup
public class Init {
    @Inject
    TweetDAO tweetDAO;

    @Inject
    UserDAO userDAO;

    @PostConstruct
    public void init(){
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        Tweet tweet1 = new Tweet("ja");
        Tweet tweet2 = new Tweet("nee");
        user1.setUsername("1");
        user2.setUsername("2");
        user3.setUsername("3");
        tweetDAO.Save(tweet1);
        tweetDAO.Save(tweet2);

        userDAO.Save(user2);
        userDAO.Save(user3);
        userDAO.Save(user1);

        user2.setTweets(new ArrayList<Tweet>(){{add(tweet1);}});
        user3.setTweets(new ArrayList<Tweet>(){{add(tweet2);}});

        userDAO.Edit(user2);
        userDAO.Edit(user3);

        tweet1.setOwnerTweet(user2);
        tweet2.setOwnerTweet(user3);

        tweetDAO.Edit(tweet1);
        tweetDAO.Edit(tweet2);

        List<User> fol = new ArrayList<>();
        fol.add(user2);
        user1.setFollowing(fol);

        userDAO.Edit(user1);
    }
}
