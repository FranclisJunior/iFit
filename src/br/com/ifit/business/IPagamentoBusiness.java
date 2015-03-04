package br.com.ifit.business;

import java.util.List;

import br.com.ifit.exception.BusinessException;
import br.com.ifit.model.Pagamento;

public interface IPagamentoBusiness {
	
	public Pagamento adicionar(Pagamento pagamento) throws BusinessException;
	public List<Pagamento> getPagamentos(String cpf) throws BusinessException;
	public void atualizar(Pagamento pagamento) throws BusinessException;
}
