package jsf;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Kaj Suiker on 1-4-2017.
 */
@Named
@ConversationScoped
public class UserBean implements Serializable {

    @Inject
    StartBean startBean;


}
