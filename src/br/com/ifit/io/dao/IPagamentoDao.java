package br.com.ifit.io.dao;

import java.util.List;

import br.com.ifit.exception.DAOException;
import br.com.ifit.model.Pagamento;

public interface IPagamentoDao extends DaoGeneric<Pagamento>{
	
	public List<Pagamento> getPagamentos(String cpf) throws DAOException;
}
