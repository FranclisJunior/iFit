package br.com.ifit.io.dao;

import java.util.List;

import br.com.ifit.exception.DAOException;
import br.com.ifit.model.Usuario;

public interface IUsuarioDao extends DaoGeneric<Usuario> {

    Usuario getPorLogin(String login) throws DAOException;
    
    boolean existeUsuarioPorCPF(String cpf) throws DAOException;
    
    List<Usuario> buscar(String busca, String tipo) throws DAOException;

    Usuario getPorCpf(String cpf) throws DAOException;
    
}