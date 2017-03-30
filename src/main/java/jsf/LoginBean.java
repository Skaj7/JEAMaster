package jsf;

import domain.User;
import service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.FailedLoginException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.net.URI;

/**
 * Created by Kaj Suiker on 30-3-2017.
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    private Credentials credentials;

    @Context
    private UriInfo uriInfo;

    private User user;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String login() throws FailedLoginException {
        user = userService.login(credentials.getUsername(),credentials.getPassword());
        return "index.xhtml";
    }

    public String logout() {

        return null;
    }
}