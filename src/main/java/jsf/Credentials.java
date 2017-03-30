package jsf;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by Kaj Suiker on 30-3-2017.
 */
@Named
@RequestScoped
public class Credentials {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
