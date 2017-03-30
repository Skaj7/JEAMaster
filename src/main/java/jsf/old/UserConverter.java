package jsf.old;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

import domain.User;

public class UserConverter implements Converter {

    public UserConverter() {
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }

        UserManagedBean managedBean = (UserManagedBean) facesContext.getApplication().getVariableResolver().resolveVariable(
                facesContext, "user");

        final Long id = new Long(string);

        return managedBean.findEntity(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null) return null;

        if (object instanceof User) {
            User entity = (User) object;

            final String pk = String.valueOf(entity.getId());

            return pk;
        } else {
            throw new IllegalArgumentException("Incorrect object type: " + object.getClass().getName() + "; must be: User");
        }
    }
}
