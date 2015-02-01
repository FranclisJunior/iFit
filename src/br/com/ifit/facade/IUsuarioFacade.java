package br.com.ifit.facade;

import br.com.ifit.exception.DAOException;
import br.com.ifit.model.Usuario;

public interface IUsuarioFacade {

    Usuario fazerLogin(String login, String senha) throws DAOException;

    Usuario salvarUsuario(Usuario usuario) throws DAOException;

}
