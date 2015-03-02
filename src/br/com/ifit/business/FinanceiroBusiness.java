package br.com.ifit.business;

import java.io.Serializable;
import java.util.List;

import br.com.ifit.exception.BusinessException;
import br.com.ifit.exception.DAOException;
import br.com.ifit.io.dao.IFinanceiroDao;
import br.com.ifit.io.dao.FinanceiroDao;
import br.com.ifit.model.Pagamento;

public class FinanceiroBusiness implements IFinanceiroBusiness, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IFinanceiroDao pagamentoDao;
	
	public FinanceiroBusiness() {
		pagamentoDao = new FinanceiroDao();
	}

	@Override
	public Pagamento adicionar(Pagamento pagamento) throws BusinessException {
		try{
			return pagamentoDao.save(pagamento);
		}catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	public void atualizar(Pagamento pagamento) throws BusinessException{
		try {
			pagamentoDao.update(pagamento);
		}catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	public List<Pagamento> getPagamentos(String cpf) throws BusinessException{
		try {
			return pagamentoDao.getPagamentos(cpf);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
	}
}
