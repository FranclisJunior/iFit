package br.com.ifit.io.dao;

import java.util.List;

import br.com.ifit.exception.DAOException;
import br.com.ifit.model.Medicao;
import br.com.ifit.model.Usuario;

public interface IMedicaoDao extends DaoGeneric<Medicao>{

	public Integer contar(Usuario usuario) throws DAOException;
	
    public List<Medicao> buscar(String cpf) throws DAOException;
    
    public Medicao getPorId(int id) throws DAOException;

}
