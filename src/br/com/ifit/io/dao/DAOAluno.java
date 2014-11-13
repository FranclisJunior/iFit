package br.com.ifit.io.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.com.ifit.exception.BDException;
import br.com.ifit.exception.PersistenciaAlunoException;
import br.com.ifit.io.ConnectionFactory;
import br.com.ifit.io.PersistenciaAluno;
import br.com.ifit.model.Aluno;
import br.com.ifit.model.Endereco;

public class DAOAluno extends ConnectionFactory implements PersistenciaAluno{

	@Override
	public String saveAluno(Aluno aluno) throws PersistenciaAlunoException {
		
		Connection conn=null;
		PreparedStatement stmt=null;
				
		try {
			conn = getConnection();
			//Adciona o login do aluno
			stmt = conn.prepareStatement("INSERT INTO tb_login"
					+ "(CPF,Login,Senha) VALUES(?,?,?)");
			
			stmt.setString(1,aluno.getCpf());
			stmt.setString(2,aluno.getEmail());
			stmt.setString(3,aluno.getCpf());			
			stmt.executeUpdate();
			
			
			//Adiciona o endereço do aluno
			stmt.clearParameters();
			stmt = conn.prepareStatement("INSERT INTO TB_Endereco"
					+ "(Rua,Numero,Bairro,Cidade,Estado) VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1,aluno.getEndereco().getRua());
			stmt.setString(2,aluno.getEndereco().getNumero());
			stmt.setString(3,aluno.getEndereco().getBairro());
			stmt.setString(4,aluno.getEndereco().getCidade());
			stmt.setString(5,aluno.getEndereco().getEstado());			
			stmt.executeUpdate();
			
			//pega o id do endereço
			int codEndereco = 0;
			ResultSet rs = stmt.getGeneratedKeys();			
			if(rs.next())
				codEndereco =  rs.getInt(1);
			
			//Adiciona o aluno
			stmt.clearParameters();
			stmt = conn.prepareStatement("INSERT INTO TB_Aluno"
					+ "(CPF,Nome,Sexo,DataNascimento,Email,Telefone,CodEndereco) VALUES(?,?,?,?,?,?,?)");
			
			stmt.setString(1,aluno.getCpf());
			stmt.setString(2,aluno.getNome());
			stmt.setString(3,aluno.getSexo());
			stmt.setString(4,aluno.getDataNascimento());
			stmt.setString(5,aluno.getEmail());
			stmt.setString(6,aluno.getTelefone());
			stmt.setInt(7,codEndereco);
			
			int r = stmt.executeUpdate();
						
			if(r>0)
				return "Aluno adicionado";
			else
				return "Falha ao adicionar o aluno";			
			
		} catch (BDException e) {
			e.printStackTrace();
			throw new PersistenciaAlunoException("Ocorreu alguma falha no banco de dados");
		}catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaAlunoException("Ocorreu alguma falha no banco de dados");
		}finally{
			closeConnection(conn,stmt,null);
		}
	}

	@Override
	public Aluno getAluno(String cpf) throws PersistenciaAlunoException {
		Aluno aluno = null;
		Connection conn = null;
		ResultSet resultSet = null;
	    PreparedStatement stmt = null;
	    
	    try{
	    	conn = getConnection();
	    	stmt = conn.prepareStatement("SELECT * FROM tb_aluno al, tb_endereco end"
	    			+ " WHERE al.CPF = ? AND al.CodEndereco = end.CodEndereco");
	    	stmt.setString(1,cpf);
	    	resultSet = stmt.executeQuery();
	    	while(resultSet.next()){
	    		aluno = new Aluno();
	    		aluno.setCpf(resultSet.getString("al.CPF"));
	    		aluno.setNome(resultSet.getString("al.Nome"));
	    		aluno.setSexo(resultSet.getString("al.Sexo"));
	    		aluno.setDataNascimento(resultSet.getString("al.DataNascimento"));
	    		aluno.setEmail(resultSet.getString("al.Email"));
	    		aluno.setTelefone(resultSet.getString("al.Telefone"));
	    		
	    		Endereco end = new Endereco();
	    		end.setId(resultSet.getInt("end.CodEndereco"));
	    		end.setRua(resultSet.getString("end.Rua"));
	    		end.setNumero(resultSet.getString("end.Numero"));
	    		end.setBairro(resultSet.getString("end.Bairro"));
	    		end.setCidade(resultSet.getString("end.Cidade"));
	    		end.setEstado(resultSet.getString("end.Estado"));
	    		aluno.setEndereco(end);
	    	}    	
	    	
	    	return aluno;
	    	
	    }catch (BDException e) {
			e.printStackTrace();
			throw new PersistenciaAlunoException("Ocorreu alguma falha no banco de dados");
		}catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaAlunoException("Ocorreu alguma falha no banco de dados");
		}finally{
			closeConnection(conn,stmt,null);
		}
	    
	}

	@Override
	public List<Aluno> getAlunos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateAluno(Aluno aluno) throws PersistenciaAlunoException {
		Connection conn = null;
		ResultSet resultSet = null;
	    PreparedStatement stmt = null;
	    try{
	    	conn = getConnection();
	    	stmt = conn.prepareStatement("UPDATE tb_aluno SET Nome = ?, Sexo = ?, DataNascimento = ?, Email = ?, Telefone = ? WHERE CPF = ?");
	    	stmt.setString(1,aluno.getNome());
	    	stmt.setString(2,aluno.getSexo());
	    	stmt.setString(3,aluno.getDataNascimento());
	    	stmt.setString(4,aluno.getEmail());
	    	stmt.setString(5,aluno.getTelefone());
	    	stmt.setString(6, aluno.getCpf());
	    	int a = stmt.executeUpdate();
	    	
	    	stmt.clearParameters();
	    	stmt = conn.prepareStatement("UPDATE tb_endereco SET Rua = ?, Numero = ?, Bairro = ?, Cidade = ?, Estado = ? WHERE CodEndereco = ?");
	    	stmt.setString(1, aluno.getEndereco().getRua());
	    	stmt.setString(2,aluno.getEndereco().getNumero());
	    	stmt.setString(3, aluno.getEndereco().getBairro());
	    	stmt.setString(4, aluno.getEndereco().getCidade());
	    	stmt.setString(5, aluno.getEndereco().getEstado());
	    	stmt.setInt(6, aluno.getEndereco().getId());
	    	int b = stmt.executeUpdate();
	    	
	    	if(a>0 && b>0)
	    		return "Aluno atualizado";
	    	else
	    		return "Falha ao atualizar o aluno";	    	
	    	
	    }catch (BDException e) {
			e.printStackTrace();
			throw new PersistenciaAlunoException("Ocorreu alguma falha no banco de dados");
		}catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaAlunoException("Ocorreu alguma falha no banco de dados");
		}finally{
			closeConnection(conn,stmt,null);
		}
	}

	@Override
	public String deleteAluno(String cpf) throws PersistenciaAlunoException {
		Connection conn = null;
		ResultSet resultSet = null;
	    PreparedStatement stmt = null;
	    try{
	    	conn = getConnection();
	    	
	    	//Apaga endereço do aluno	 
	    	Aluno  aluno =  getAluno(cpf);
	    	if(aluno==null)
	    		return "Não existe aluno com esse CPF";
	    	
	    	int codEndereco = aluno.getEndereco().getId();
	    	stmt = conn.prepareStatement("DELETE FROM tb_endereco WHERE CodEndereco = ?");
	    	stmt.setInt(1,codEndereco);
	    	int a = stmt.executeUpdate();
	    	
	    	//Apaga aluno
	    	stmt.clearParameters();
	    	stmt = conn.prepareStatement("DELETE FROM tb_aluno WHERE CPF = ?");
	    	stmt.setString(1,cpf);
	    	int b = stmt.executeUpdate();
	    		    	
	    	//Apaga login do aluno
	    	stmt.clearParameters();
	    	stmt = conn.prepareStatement("DELETE FROM tb_login WHERE CPF = ?");
	    	stmt.setString(1,cpf);
	    	int c = stmt.executeUpdate();
	    	
	    	if(a>0 && b>0 && c>0)
	    		return "Aluno deletado";
	    	else
	    		return "Falha ao deletar aluno";    	
	    	
	    }catch (BDException e) {
			e.printStackTrace();
			throw new PersistenciaAlunoException("Ocorreu alguma falha no banco de dados");
		}catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenciaAlunoException("Ocorreu alguma falha no banco de dados");
		}finally{
			closeConnection(conn,stmt,null);
		}
	}

}
