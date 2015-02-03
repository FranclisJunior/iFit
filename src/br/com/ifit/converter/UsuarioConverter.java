package br.com.ifit.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ifit.business.IUsuarioBusiness;
import br.com.ifit.business.UsuarioBusiness;
import br.com.ifit.model.Usuario;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            IUsuarioBusiness usuarioBusiness = new UsuarioBusiness();
            System.out.println("converteu.");
            return usuarioBusiness.getPorCpf(value);
        } catch (Exception e) {
            System.out.println("erro de conversão");
            }
        return null;
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Usuario usuario = (Usuario) value;
            return String.valueOf(usuario.getCpf());
        }
        return "";
    }

}
