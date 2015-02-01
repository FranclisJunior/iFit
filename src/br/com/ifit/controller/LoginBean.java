package br.com.ifit.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.ifit.facade.UsuarioFacade;
import br.com.ifit.facade.IUsuarioFacade;
import br.com.ifit.model.Usuario;

@ManagedBean
@RequestScoped

public class LoginBean {

    private String login;
    private String senha;
    private IUsuarioFacade alunoFacade = new UsuarioFacade();
    
    protected void imprimirErro(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));
    }
    
    public String login() {
        try {
            Usuario aluno = alunoFacade.fazerLogin(login, senha);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext extenalContext = facesContext.getExternalContext();
            RequestDispatcher dispatcher = ((ServletRequest) extenalContext
                    .getRequest())
                    .getRequestDispatcher("/j_spring_security_check");
            dispatcher.forward((ServletRequest) extenalContext.getRequest(),
                    (ServletResponse) extenalContext.getResponse());
            facesContext.responseComplete();
        } catch (Exception e) {
            imprimirErro(e.getMessage());
        }
        return null;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
