package br.com.ifit.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

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
	
	private String tipo;
	
	private IUsuarioBusiness usuarioBusiness;
	
    private List<Usuario> usuarios = null;
    
    private boolean ativo = true;
	
	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public UsuarioBean () {
		if (this.usuario == null) {
			this.usuario = new Usuario();
			this.usuario.setEndereco(new Endereco());
		}
		
		usuarioBusiness = new UsuarioBusiness();
	}
	
	public IUsuarioBusiness getUsuarioBusiness() {
		return usuarioBusiness;
	}

	public void setUsuarioBusiness(IUsuarioBusiness usuarioBusiness) {
		this.usuarioBusiness = usuarioBusiness;
	}

	public void iniciar() {
		this.usuario = new Usuario();
        this.usuarios = null;
	}
	
	public void cadastrar(ActionEvent actionEvent) {
		try {
			usuarioBusiness.adicionar(usuario);
			imprimirMensagem("Usuário adicionado com sucesso.");
			iniciar();
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
    public void buscar() throws DAOException {
        try {
            if (usuarios == null) {
                usuarios = usuarioBusiness.buscar(busca, tipo);

            }
        } catch (SecurityException e) {
            imprimirErro(e.getMessage());
        }
    }

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Usuario> getUsuarios() throws DAOException {
		buscar();
		return usuarios;
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
    
    public void remover(Usuario usuario) {
    	try {
			usuarioBusiness.remover(usuario);
			imprimirMensagem("Usuário deletado com sucesso.");
			iniciar();
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
    }
    
    public void salvarOuAtualizar() {
    	try {
			usuarioBusiness.atualizar(usuario);
			imprimirMensagem("Usuário atualizado com sucesso.");
			iniciar();	
			fecharDialog("usuarioDialog");
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
    }
    
    public void atualizar(String cpf) {
    	try {
			usuario = usuarioBusiness.getPorCpf(cpf);
		} catch (DAOException e) {
			imprimirMensagem(e.getMessage());
		}
    }

}
