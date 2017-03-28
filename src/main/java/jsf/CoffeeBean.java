package jsf;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Kaj Suiker on 15-3-2017.
 */
@Named("CoffeeBean")
@SessionScoped
public class CoffeeBean implements Serializable {
    private String test = "test nee";
    public CoffeeBean() {
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
