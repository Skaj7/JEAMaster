package jsf;

import domain.User;
import service.UserService;

//import javax.faces.bean.SessionScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.context.FacesContext;
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

//@ManagedBean(name = "loginBean")
@Named
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    private Credentials credentials;

//    private String username;
//    private String password;
    private String messages;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    private User user;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

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

    public String login() {
        try {
            user = userService.login(credentials.getUsername(),credentials.getPassword());
        }catch (FailedLoginException fle){
            FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("hoi", msg);
            return "/login.xhtml";
        }
        if(user == null)
            return "/login.xhtml";

        return "secured/index.xhtml";
    }

    public String logout() {
        return null;
    }

    public boolean isLoggedIn(){
        if (user == null) {
            return false;
        }
        return true;
    }
}