package jsf;

import domain.User;
import service.UserService;

//import javax.faces.bean.SessionScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;

/**
 * Created by Kaj Suiker on 30-3-2017.
 */

//@ManagedBean(name = "loginBean")
@Named
@SessionScoped
public class LoginBean implements Serializable{

    @Inject
    private UserService userService;

    @Inject
    private Credentials credentials;

    @Inject
    private StartBean startBean;

    @Inject
    private ProfileBean profileBean;

    private User user;

    public LoginBean() {
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

        return "/index.xhtml";
    }

    public String logout() throws IOException {
        user = null;
        return "/login.xhtml";
    }
}