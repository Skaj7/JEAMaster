package jsf;

import domain.Tweet;
import domain.User;
import service.TweetService;
import service.UserService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by Kaj Suiker on 30-3-2017.
 */
@Named
@SessionScoped
public class ProfileBean implements Serializable {

    @Inject
    private LoginBean loginBean;

    @Inject
    private StartBean startBean;

    @Inject
    private UserService userService;

    @Inject
    private TweetService tweetService;

    private User user;
    private List<User> data;
    private boolean isLogin = false;
    private boolean isAdmin = false;

    public ProfileBean() {
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tweet> getTweets(){
        return tweetService.latest(user.getId());
    }

    public String setProperties(){
        if (loginBean == null || startBean == null){
            return "/index.xhtml";
        }

        if(loginBean.getUser().getRole() == 1){
            isAdmin = true;
        }else {
            isAdmin = false;
        }

        if(startBean.getTests() == null || startBean.getTests() == ""){
            user = loginBean.getUser();
            isLogin = true;
        }else {
//            user = userService.getUser(startBean.getTests());
//            isLogin = false;
        }

        return null;
    }

    public void updateFollowingData(){
        data = user.getFollowing();
    }

    public void updateFollowerData(){
        data = userService.followers(user);
    }

    public String followCount(){
        if(user.getFollowing() == null || user.getFollowing().isEmpty()){
            return "0";
        }

        return Integer.toString(user.getFollowing().size());
    }

    public String followerCount(){
        return Integer.toString(userService.followers(user).size());
    }

    public String tweetCount(){
        throw new NotImplementedException();
//        if(user.getTweets() == null || user.getTweets().isEmpty()){
//            return "0";
//        }
//
//        return Integer.toString(user.getTweets().size());
    }

    public boolean followPos(){
        if (isLogin){
            return true;
        }

        if(loginBean.getUser().getFollowing().contains(user)){
            return true;
        }

        return false;
    }

    public void follow(){
        userService.follow(user.getId(), loginBean.getUser());
    }
}
