package br.com.ifit.business;

import java.util.List;

import br.com.ifit.exception.BusinessException;
import br.com.ifit.model.Treino;
import br.com.ifit.model.Usuario;

public interface ITreinoBusiness {

	public Treino adicionar(Treino treino) throws BusinessException;

	public Integer contar(Usuario usuario) throws BusinessException;
	
	public List<Treino> buscar(String cpf) throws BusinessException;
	
    public Treino getPorId(String id) throws BusinessException;
    
    public void atualizar(Treino treino) throws BusinessException;
    
    public void remover(Treino treino) throws BusinessException;
    
}
