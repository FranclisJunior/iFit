package br.com.ifit.io.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.ifit.exception.DAOException;
import br.com.ifit.model.Treino;
import br.com.ifit.model.Usuario;

public class TreinoDao extends DaoGenericImpl<Treino> implements ITreinoDao {

    public Integer contar(Usuario usuario) throws DAOException {
        Criteria criteria = this.getCriteria();
        List<Usuario> resultado = null;
        try {
            criteria.add(Restrictions.like("usuario", "%" + usuario.getCpf() + "%").ignoreCase());
            resultado = (List<Usuario>) criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e.getCause());
        }
        return resultado.size();
    }
    
    public List<Treino> buscar(String cpf) throws DAOException {
        Criteria criteria = this.getCriteria();
        List<Treino> resultado = null;
        try {
            criteria.add(Restrictions.like("usuario", "%" + cpf + "%").ignoreCase());
            resultado = (List<Treino>) criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e.getCause());
        }
        return resultado;
    }

	@Override
    public Treino getPorId(String id) throws DAOException {
        Criteria criteria = this.getCriteria();
        Treino resultado = null;
        try {
            criteria.add(Restrictions.eq("id", Integer.parseInt(id)));
            resultado = (Treino) criteria.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e.getCause());
        }
        return resultado;
    }
	
}