package jsf;

import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;

import domain.Heart;

public class HeartConverter implements Converter {

    public HeartConverter() {
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }

        HeartManagedBean managedBean = (HeartManagedBean) facesContext.getApplication().getVariableResolver().resolveVariable(
                facesContext, "heart");

        final long id = Long.parseLong(string);

        return managedBean.findEntity(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null) return null;

        if (object instanceof Heart) {
            Heart entity = (Heart) object;

            final String pk = String.valueOf(entity.getId());

            return pk;
        } else {
            throw new IllegalArgumentException("Incorrect object type: " + object.getClass().getName() + "; must be: Heart");
        }
    }
}
