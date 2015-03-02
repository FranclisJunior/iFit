package br.com.ifit.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class LinkUtilBean {
		
    public String paginaListarUsuarios() {    	
        return "/listarUsuarios.jsf?faces-redirect=true";
    }
    
    public String paginaCadastroUsuario() {    	
        return "/cadastrarUsuario.jsf?faces-redirect=true";
    }
    
    public String paginaTreinos() {
    	return "/treinos.jsf?faces-redirect=true";
    }
    
    public String paginaMedidas() {
    	return "/medidas.jsf?faces-redirect=true";
    }
    
    public String paginaLogin() {
    	return "/login.jsf?faces-redirect=true";
    }
    
    public String paginaPagarMensalidades(){
    	return "/pagamentos.jsf?faces-redirect=true";
    }
    
    public String paginaMensalidadesAtrasadas(){
    	return "/usuariosInadiplentes.jsf?faces-redirect=true";
    }
    
}