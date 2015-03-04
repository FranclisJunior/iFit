package br.com.ifit.business;

import java.util.List;

import br.com.ifit.exception.BusinessException;
import br.com.ifit.model.ExercicioPorUsuario;

public interface IExercicioBusiness {
	public ExercicioPorUsuario addExercicio (ExercicioPorUsuario exercicioPorUsuario) throws BusinessException;

	public List<ExercicioPorUsuario> getPorId(int id) throws BusinessException;
	
    public void remover(ExercicioPorUsuario exercicioPorUsuario) throws BusinessException;
	
    public void atualizar(ExercicioPorUsuario exercicioPorUsuario) throws BusinessException;
    
    public ExercicioPorUsuario getPorIdExercicio(int id) throws BusinessException;


}
