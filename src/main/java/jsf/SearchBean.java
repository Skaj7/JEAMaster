package jsf;

import domain.User;
import service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Kaj Suiker on 1-4-2017.
 */
@Named
@RequestScoped
public class SearchBean {

    @Inject
    private LoginBean loginBean;

    @Inject
    private UserService userService;

    private String searchString;
    private List<User> searchResult;

    public SearchBean() {
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public List<User> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<User> searchResult) {
        this.searchResult = searchResult;
    }

    public void search(){
        searchResult = userService.search(searchString);
    }
}
