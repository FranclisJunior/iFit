package br.com.ifit.io.dao;

import java.util.List;

import br.com.ifit.exception.DAOException;
import br.com.ifit.model.ExercicioPorUsuario;

public interface IExercicioDao extends DaoGeneric<ExercicioPorUsuario> {
	public ExercicioPorUsuario addExercicio (ExercicioPorUsuario exercicioPorUsuario) throws DAOException;
	
	public List<ExercicioPorUsuario> buscar (int id) throws DAOException;
	
    public ExercicioPorUsuario getPorId(int id) throws DAOException;
	
}
