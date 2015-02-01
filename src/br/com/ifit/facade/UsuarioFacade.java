package br.com.ifit.facade;

import br.com.ifit.business.UsuarioBusiness;
import br.com.ifit.business.IUsuarioBusiness;
import br.com.ifit.exception.DAOException;
import br.com.ifit.model.Usuario;

public class UsuarioFacade implements IUsuarioFacade {

    private IUsuarioBusiness alunoBusiness;

	public UsuarioFacade() {
		this.alunoBusiness = new UsuarioBusiness();
	}
	
	public Usuario fazerLogin(String login, String senha) throws DAOException {
        return alunoBusiness.fazerLogin(login, senha);
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) throws SecurityException {
		// TODO Auto-generated method stub
		return null;
	}

}
