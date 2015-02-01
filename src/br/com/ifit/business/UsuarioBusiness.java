package br.com.ifit.business;

import java.util.List;

import javax.transaction.Transactional;

import br.com.ifit.exception.BusinessException;
import br.com.ifit.exception.DAOException;
import br.com.ifit.io.dao.UsuarioDao;
import br.com.ifit.io.dao.IUsuarioDao;
import br.com.ifit.model.Usuario;

public class UsuarioBusiness implements IUsuarioBusiness {
	
    private IUsuarioDao alunoDAO;
    
    public UsuarioBusiness () {
    	alunoDAO = new UsuarioDao();
    }

	public Usuario fazerLogin(String login, String senha) throws DAOException {
	    try {
	        Usuario aluno = alunoDAO.getPorLogin(login);
	        if(aluno == null || aluno.getSenha().equalsIgnoreCase(senha) == false) {
	            throw new DAOException("Login ou senha inválido");
	        } else {
	            return aluno;
	        }
	    } catch (DAOException ex) {
	        throw new DAOException(ex.getMessage());
	    }
	}
		
    @Transactional
	public void adicionar(Usuario usuario) throws BusinessException {
		try {
			if (alunoDAO.existeUsuarioPorCPF(usuario.getCpf())) {
				throw new BusinessException("CPF já cadastrado no sistema.");
			} else {
				alunoDAO.save(usuario);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
    
    public List<Usuario> buscar(String busca, String tipo) throws SecurityException {
        try {
            return alunoDAO.buscar(busca, tipo);
        } catch (DAOException ex) {
            throw new SecurityException(ex.getMessage());
        }
    }
    
    public Usuario getPorCpf(String cpf) throws DAOException {
            return alunoDAO.getPorCpf(cpf);
    }
    
    public void remover(Usuario usuario) throws BusinessException {
    	try {
			alunoDAO.remove(usuario);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
    }
    
    public void atualizar(Usuario usuario) throws BusinessException {
    	try {
			alunoDAO.update(usuario);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
    }

}