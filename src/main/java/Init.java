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

    }
}
