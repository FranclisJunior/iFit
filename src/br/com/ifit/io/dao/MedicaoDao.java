package br.com.ifit.io.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.ifit.exception.DAOException;
import br.com.ifit.model.Medicao;
import br.com.ifit.model.Usuario;

public class MedicaoDao extends DaoGenericImpl<Medicao> implements IMedicaoDao {
	
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
    
    public List<Medicao> buscar(String cpf) throws DAOException {
        Criteria criteria = this.getCriteria();
        List<Medicao> resultado = null;
        try {
            criteria.add(Restrictions.like("usuario", "%" + cpf + "%").ignoreCase());
            resultado = (List<Medicao>) criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e.getCause());
        }
        return resultado;
    }
    
    public Medicao getPorId(int id) throws DAOException {
        Criteria criteria = this.getCriteria();
        Medicao resultado = null;
        try {
            criteria.add(Restrictions.eq("id", id));
            resultado = (Medicao) criteria.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e.getCause());
        }
        return resultado;
    }
}
