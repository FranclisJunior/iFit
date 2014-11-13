package br.com.ifit.io;

import java.util.List;

import br.com.ifit.exception.PersistenciaAlunoException;
import br.com.ifit.model.Aluno;

public interface PersistenciaAluno {
	
	public String saveAluno(Aluno aluno)throws PersistenciaAlunoException;
	public Aluno getAluno(String cpf) throws PersistenciaAlunoException;
	public List<Aluno> getAlunos();
	public String updateAluno(Aluno aluno) throws PersistenciaAlunoException;
	public String deleteAluno(String cpf) throws PersistenciaAlunoException;
	
}
