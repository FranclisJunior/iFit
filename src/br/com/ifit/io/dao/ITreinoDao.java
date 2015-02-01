package br.com.ifit.io.dao;

import java.util.List;

import br.com.ifit.exception.DAOException;
import br.com.ifit.model.Treino;
import br.com.ifit.model.Usuario;

public interface ITreinoDao extends DaoGeneric<Treino>{
	
	public Integer contar(Usuario usuario) throws DAOException;
	
    List<Treino> buscar(String cpf) throws DAOException;
    
    public Treino getPorId(String id) throws DAOException;


}
