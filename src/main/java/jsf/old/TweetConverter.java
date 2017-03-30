package jsf.old;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

import domain.Tweet;

public class TweetConverter implements Converter {

    public TweetConverter() {
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }

        TweetManagedBean managedBean = (TweetManagedBean) facesContext.getApplication().getVariableResolver().resolveVariable(
                facesContext, "tweet");

        final Long id = new Long(string);

        return managedBean.findEntity(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null) return null;

        if (object instanceof Tweet) {
            Tweet entity = (Tweet) object;

            final String pk = String.valueOf(entity.getId());

            return pk;
        } else {
            throw new IllegalArgumentException("Incorrect object type: " + object.getClass().getName() + "; must be: Tweet");
        }
    }
}
