package br.com.ifit.controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;


@ManagedBean
public class DefaultBean implements Serializable {
	
    protected void abrirDialog(String dialog) {
        RequestContext request = RequestContext.getCurrentInstance();
        request.execute("PF('" + dialog + "').show()");
    }
	
    protected void fecharDialog(String dialog) {
        RequestContext request = RequestContext.getCurrentInstance();
        request.execute("PF('" + dialog + "').hide()");
    }

    protected void imprimirMensagem(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
    }

    protected void imprimirErro(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));
    }

    protected void redirect(String url) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            ec.redirect(url);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected String getBaseUrl() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getRequestURL().toString().replace(request.getRequestURI().substring(0), "") + request.getContextPath();
    }
    
    protected Boolean isUserInRole(String role) {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role);
    }
    
}
