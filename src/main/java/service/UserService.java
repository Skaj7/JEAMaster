package service;

import dao.UserDAO;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.auth.login.FailedLoginException;
import java.util.List;

/**
 * Created by Kaj Suiker on 12-3-2017.
 */
@Stateless
public class UserService {
    @Inject
    UserDAO userDAO;

    public UserService() {
    }


    public User Create(String email, String username, String password) {
        if (email == "")
            throw new NullPointerException("email not found");

        if (username == "")
            throw new NullPointerException("username not found");

        if (password == "")
            throw new NullPointerException("password not found");

        if (emailExists(email))
            throw new NullPointerException("password not found");

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        userDAO.Save(user);
        return user;
    }

    public void save(User user) {
        userDAO.Save(user);
    }

    private boolean emailExists(String email){
        return userDAO.Find(email);
    }

    public User login(String username, String password) throws FailedLoginException {
        if(username == null)
            return null;
        if(password == null)
            return null;

        List<User> user = userDAO.login(username, password);

        if(user.size() == 0)
            throw new FailedLoginException("wrong username or password");

        return user.get(0);
    }

    public List<User> search(String username) {
        if(username == null){
            return null;
        }

        return userDAO.search(username);
    }

    public void follow(long followUser, User current) {
        User toFollow = userDAO.Find(followUser);

        if(toFollow == null){
            return;
        }

        List<User> following = current.getFollowing();
        following.add(toFollow);

        userDAO.Edit(current);
    }

    public User getUser(String id){
        if (id == null || id == ""){
            return null;
        }

        User user = userDAO.Find(Long.parseLong(id));

        if(user == null){
            return null;
        }

        return user;
    }

    public List<User> followers(User user) {
        if(user == null){
            return null;
        }

        return userDAO.Followers(user);
    }
}
