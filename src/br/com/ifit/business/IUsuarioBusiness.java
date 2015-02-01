package br.com.ifit.business;

import java.util.List;

import br.com.ifit.exception.BusinessException;
import br.com.ifit.exception.DAOException;
import br.com.ifit.model.Usuario;

public interface IUsuarioBusiness {

	public Usuario fazerLogin(String login, String senha) throws DAOException;
	
	public void adicionar(Usuario usuario) throws BusinessException;
	
	public void remover(Usuario usuario) throws BusinessException;
	
	public List<Usuario> buscar(String buscar, String tipo) throws DAOException;
	
	public Usuario getPorCpf(String cpf) throws DAOException;
	
	public void atualizar(Usuario usuario) throws BusinessException;
}