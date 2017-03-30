package jsf;

import domain.User;
import service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.FailedLoginException;
import java.io.Serializable;

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
        return "StartPage";
    }

    //validate login
    public String validateUsernamePassword() {
        return null;
    }
//        boolean valid = LoginDAO.validate(user, pwd);
//        if (valid) {
//            HttpSession session = SessionUtils.getSession();
//            session.setAttribute("username", user);
//            return "admin";
//        } else {
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_WARN,
//                            "Incorrect Username and Passowrd",
//                            "Please enter correct username and Password"));
//            return "login";
//        }
//    }

    //logout event, invalidate session
    public String logout() {
//        HttpSession session = SessionUtils.getSession();
//        session.invalidate();
//        return "login";
        return null;
    }
}