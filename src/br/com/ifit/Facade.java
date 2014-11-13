package br.com.ifit;

import java.util.List;

import br.com.ifit.exception.PersistenciaAlunoException;
import br.com.ifit.io.PersistenciaAluno;
import br.com.ifit.io.dao.DAOAluno;
import br.com.ifit.model.Aluno;

public class Facade  implements PersistenciaAluno{
	
	PersistenciaAluno pAluno;
	
	public Facade(){
		 pAluno =  new DAOAluno();
	}
	
	
	
	public String saveAluno(Aluno aluno) throws PersistenciaAlunoException{
		return pAluno.saveAluno(aluno);
	}



	@Override
	public Aluno getAluno(String cpf) throws PersistenciaAlunoException {
		return pAluno.getAluno(cpf);
	}



	@Override
	public List<Aluno> getAlunos() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String updateAluno(Aluno aluno) throws PersistenciaAlunoException {
		return pAluno.updateAluno(aluno);
	}



	@Override
	public String deleteAluno(String cpf) throws PersistenciaAlunoException {
		return pAluno.deleteAluno(cpf);
	}
	
	
	
}
