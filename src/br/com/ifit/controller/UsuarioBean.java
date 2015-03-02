package br.com.ifit.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ifit.business.IUsuarioBusiness;
import br.com.ifit.business.UsuarioBusiness;
import br.com.ifit.exception.BusinessException;
import br.com.ifit.exception.DAOException;
import br.com.ifit.model.Endereco;
import br.com.ifit.model.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean extends DefaultBean {
		
	private Usuario usuario;	
	private String busca;	
	private String tipo = "Aluno";	
	private IUsuarioBusiness usuarioBusiness;	
	private PagamentoBean pagamentoBean;
    private List<Usuario> usuarios = null;      
    private boolean ativo = true;
        
    public UsuarioBean () {
		if (this.usuario == null) {
			this.usuario = new Usuario();
			this.usuario.setEndereco(new Endereco());
		}		
		usuarioBusiness = new UsuarioBusiness();
		pagamentoBean = new PagamentoBean();
	} 
        	
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}	
	public IUsuarioBusiness getUsuarioBusiness() {
		return usuarioBusiness;
	}
	public void setUsuarioBusiness(IUsuarioBusiness usuarioBusiness) {
		this.usuarioBusiness = usuarioBusiness;
	}	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
	public Usuario getUsuario() {
		return usuario;
	}	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}
	
	public List<Usuario> getUsuarios() throws DAOException {
		buscar();
		return usuarios;
	}
	
    public void buscar(){
        try {
            if (usuarios == null) {
                usuarios = usuarioBusiness.buscar(busca, tipo);
            }
        } catch (BusinessException e) {
            imprimirErro(e.getMessage());
        }
    }

	public void iniciar() {
		this.usuario = new Usuario();
        this.usuarios = null;
	}
	
	public void cadastrar() {
		try {
			usuarioBusiness.adicionar(usuario);
			imprimirMensagem("Usuario adicionado com sucesso.");
			pagamentoBean.gerarMensalidadesUsuario(usuario.getCpf());
			iniciar();
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
	}
	    
    public void remover(Usuario usuario) {
    	try {
			usuarioBusiness.remover(usuario);
			imprimirMensagem("Usuario deletado com sucesso.");
			iniciar();
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
    }
    
    public void salvarOuAtualizar() {
    	try {
			usuarioBusiness.atualizar(usuario);
			imprimirMensagem("Usuario atualizado com sucesso.");
			iniciar();	
			fecharDialog("usuarioDialog");
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
    }
    
    public void atualizar(String cpf) throws DAOException {
    	try {
			usuario = usuarioBusiness.getPorCpf(cpf);
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
    }
}
