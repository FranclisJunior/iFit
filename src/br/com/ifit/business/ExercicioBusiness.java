package br.com.ifit.business;

import java.util.List;

import br.com.ifit.exception.BusinessException;
import br.com.ifit.exception.DAOException;
import br.com.ifit.io.dao.ExercicioDao;
import br.com.ifit.io.dao.IExercicioDao;
import br.com.ifit.model.ExercicioPorUsuario;

public class ExercicioBusiness implements IExercicioBusiness {
	
	IExercicioDao exercicioDAO;
	
	public ExercicioBusiness() {
		exercicioDAO = new ExercicioDao();
	}

	@Override
	public ExercicioPorUsuario addExercicio(ExercicioPorUsuario exercicioPorUsuario) throws BusinessException {
		try {
			return exercicioDAO.addExercicio(exercicioPorUsuario);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
    public List<ExercicioPorUsuario> getPorId(int id) throws BusinessException {
        try {
            return exercicioDAO.buscar(id);
        } catch (DAOException ex) {
            throw new BusinessException(ex.getMessage());
        }
    }
    
    public void remover(ExercicioPorUsuario exercicioPorUsuario) throws BusinessException {
    	try {
			exercicioDAO.remove(exercicioPorUsuario);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
    }
    
    public void atualizar(ExercicioPorUsuario exercicioPorUsuario) throws BusinessException {
    	try {
			exercicioDAO.update(exercicioPorUsuario);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
    }
	
    public ExercicioPorUsuario getPorIdExercicio(int id) throws BusinessException {
    	try {
			return exercicioDAO.getPorId(id);
		} catch (DAOException e) {
			throw new BusinessException (e.getMessage());
		}
    }

}
