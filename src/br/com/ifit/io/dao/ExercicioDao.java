package br.com.ifit.io.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.ifit.exception.DAOException;
import br.com.ifit.model.ExercicioPorUsuario;

public class ExercicioDao extends DaoGenericImpl<ExercicioPorUsuario> implements IExercicioDao, Serializable {

	@Override
	public ExercicioPorUsuario addExercicio(ExercicioPorUsuario exercicioPorUsuario) throws DAOException {
		return save(exercicioPorUsuario);
	}
	
    public List<ExercicioPorUsuario> buscar(int id) throws DAOException {
        Criteria criteria = this.getCriteria();
        List<ExercicioPorUsuario> resultado = null;
        try {
            criteria.add(Restrictions.eq("treino", id));
            resultado = (List<ExercicioPorUsuario>) criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e.getCause());
        }
        return resultado;
    }
    
    public ExercicioPorUsuario getPorId(int id) throws DAOException {
        Criteria criteria = this.getCriteria();
        ExercicioPorUsuario resultado = null;
        try {
            criteria.add(Restrictions.eq("id", id));
            resultado = (ExercicioPorUsuario) criteria.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e.getCause());
        }
        return resultado;
    }
}
