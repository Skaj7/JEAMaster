import dao.UserDAO;
import domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created by Kaj Suiker on 30-3-2017.
 */
@Singleton
@Startup
public class init {

    @Inject
    UserDAO userDAO;

    @PostConstruct
    public void init(){
        User user = new User();
        user.setUsername("Kaj");
        user.setPassword("aapje");
        user.setEmail("kaj@mail.com");

        //userDAO.Save(user);
    }
}
