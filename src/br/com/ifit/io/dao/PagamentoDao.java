package br.com.ifit.io.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.ifit.exception.DAOException;
import br.com.ifit.model.Medicao;
import br.com.ifit.model.Pagamento;

public class PagamentoDao extends DaoGenericImpl<Pagamento> implements IPagamentoDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public List<Pagamento> getPagamentos(String cpf) throws DAOException{
		Criteria criteria = this.getCriteria();
		List<Pagamento> pagamentos = null;
		try {	
			criteria.addOrder(Order.asc("id"));
	        criteria.add(Restrictions.eq("usuario", cpf));
	        pagamentos = (List<Pagamento>) criteria.list();
        } catch (Exception e) {
        	 e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e.getCause());
        }
		return pagamentos;
	}

}
