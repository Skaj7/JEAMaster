package jsf;

import domain.Tweet;
import domain.User;
import org.ocpsoft.pretty.time.PrettyTime;
import service.TweetService;
import service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Kaj Suiker on 1-4-2017.
 */
@Named
@SessionScoped
public class StartBean implements Serializable {
    
    @Inject
    LoginBean loginBean;

    @Inject
    TweetService tweetService;

    @Inject
    UserService userService;
    
    private int num = 0;
    private List<Tweet> timeline;
    private String message;
    private String search;
    private String followUser;
    private User follow;

    public User getFollow() {
        return follow;
    }

    public void setFollow(User follow) {
        this.follow = follow;
    }

    public String getFollowUser() {
        return followUser;
    }

    public void setFollowUser(String followUser) {
        this.followUser = followUser;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String test(){
        num++;
        return Integer.toString(num);
    }

    public void tweet() {
        tweetService.post(message, loginBean.getUser().getId());
        fillTimeline();
    }

    public void fill(){
        fillTimeline();
    }

    public void fillTimeline(){
        timeline = tweetService.timeline(loginBean.getUser().getId());
    }

    public List<Tweet> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<Tweet> timeline) {
        this.timeline = timeline;
    }

    public String timeAgo(Date test){
            PrettyTime p = new PrettyTime();
            return p.format(test);
    }

    public void follow(){
        userService.follow(followUser, loginBean.getUser());
        followUser = "";
    }
}
