package br.com.ifit;

import br.com.ifit.exception.PersistenciaAlunoException;
import br.com.ifit.model.Aluno;
import br.com.ifit.model.Endereco;

public class MainTeste {

	public static void main(String[] args) {
		try {
				
			Aluno a = new Aluno();
			a.setCpf("09119307446");
			a.setNome("Junior Galdino");
			a.setSexo("Masculino");
			a.setDataNascimento("15/05/1991");
			a.setEmail("franclis.silva@dce.ufpb.br");
			a.setTelefone("0303911");
			
			Endereco end = new Endereco();
			end.setRua("Rua da Aurora");
			end.setNumero("01");
			end.setBairro("Centro");
			end.setCidade("Rio Tinto");
			end.setEstado("Paraiba");			
			a.setEndereco(end);		
			
			//Adiciona
			Facade facade = new Facade();			
			String resultado = facade.saveAluno(a);
			System.out.println(resultado);
			
			//Recupera
			Aluno alunoSalvo = facade.getAluno("09119307446");
			System.out.println("Informaçoes do aluno salvo:");
			System.out.println(alunoSalvo.getNome());
			System.out.println(alunoSalvo.getEndereco().getCidade());
			
			
			//Atualiza
			alunoSalvo.setNome("Franclis Galdino");
			alunoSalvo.getEndereco().setCidade("Joao Pessoa");
			String resultadoA = facade.updateAluno(alunoSalvo);
			System.out.println(resultadoA);
			Aluno alunoAtualizado = facade.getAluno("09119307446");
			System.out.println("Informaçoes do aluno atualizado:");
			System.out.println(alunoAtualizado.getNome());
			System.out.println(alunoAtualizado.getEndereco().getCidade());
		
			
			//Deleta
			String deleteAluno = facade.deleteAluno("09119307446");
			System.out.println(deleteAluno);
			
			
			
		} catch (PersistenciaAlunoException e) {
			System.out.println(e.getMessage());
		}
	}

}
