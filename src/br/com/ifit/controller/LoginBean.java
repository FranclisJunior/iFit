package br.com.ifit.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ifit.facade.IUsuarioFacade;
import br.com.ifit.facade.UsuarioFacade;
import br.com.ifit.model.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean extends DefaultBean{
	
	public static Usuario usuario;	
    private String login;
    private String senha;
    private IUsuarioFacade usuarioFacade;
    
    public LoginBean() {
    	 usuarioFacade = new UsuarioFacade(); 
	}   
    
    public String login() {
        try {
            usuario = usuarioFacade.fazerLogin(login, senha);             
            if(usuario.getTipo().equals("Aluno"))
            	return "/homeAluno.jsf?faces-redirect=true";            	            	
            
            return "/homeAdmin.jsf/face-redirect=true";
          
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
        	usuario=null;
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
    
    public Usuario getUsuario(){
    	return usuario;
    }
}
