package br.com.ifit.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.ifit.facade.IUsuarioFacade;
import br.com.ifit.facade.UsuarioFacade;

@ManagedBean
@RequestScoped

public class LoginBean extends DefaultBean{

    private String login;
    private String senha;
    private IUsuarioFacade alunoFacade = new UsuarioFacade();
    
   
    
    public String login() {
        try {
            alunoFacade.fazerLogin(login, senha);  
            return "/index.jsf?faces-redirect=true";
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            ExternalContext extenalContext = facesContext.getExternalContext();
//            RequestDispatcher dispatcher = ((ServletRequest) extenalContext
//                    .getRequest())
//                    .getRequestDispatcher("/j_spring_security_check");
//            dispatcher.forward((ServletRequest) extenalContext.getRequest(),
//                    (ServletResponse) extenalContext.getResponse());
//            facesContext.responseComplete();        

        } catch (Exception e) {
            imprimirErro(e.getMessage());
        }
        return null;
    }
    
    public String logout() {
        try {        	       
        	imprimirMensagem("Logout realizado");
        } catch (Exception e) {
            imprimirErro(e.getMessage());
        }
    	return "/login.jsf?faces-redirect=true";
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
