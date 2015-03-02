package br.com.ifit.facade;

import java.io.Serializable;

import br.com.ifit.business.UsuarioBusiness;
import br.com.ifit.business.IUsuarioBusiness;
import br.com.ifit.exception.BusinessException;
import br.com.ifit.exception.DAOException;
import br.com.ifit.model.Usuario;

public class UsuarioFacade implements IUsuarioFacade,Serializable {

    private IUsuarioBusiness alunoBusiness;

	public UsuarioFacade() {
		this.alunoBusiness = new UsuarioBusiness();
	}
	
	public Usuario fazerLogin(String login, String senha) throws DAOException {
        try {
			return alunoBusiness.fazerLogin(login, senha);
		} catch (BusinessException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) throws SecurityException {
		// TODO Auto-generated method stub
		return null;
	}

}
