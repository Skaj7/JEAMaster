package service;

import dao.UserDAO;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

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

    private boolean emailExists(String email){
        return userDAO.Find(email);
    }
}
