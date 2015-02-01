package br.com.ifit.business;

import java.util.List;

import br.com.ifit.exception.BusinessException;
import br.com.ifit.model.Medicao;
import br.com.ifit.model.Usuario;

public interface IMedicaoBusiness {
	
	public Integer contar(Usuario usuario) throws BusinessException;
	
	public void adicionar(Medicao medicao) throws BusinessException;
	
    public List<Medicao> buscar(String cpf) throws BusinessException;
   
    public Medicao getPorId(int id) throws BusinessException;
    
    public void remover(Medicao medicao) throws BusinessException;
    
    public void atualizar(Medicao medicao) throws BusinessException;

}
