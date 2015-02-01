package br.com.ifit.business;

import java.util.List;

import br.com.ifit.exception.BusinessException;
import br.com.ifit.exception.DAOException;
import br.com.ifit.io.dao.ITreinoDao;
import br.com.ifit.io.dao.TreinoDao;
import br.com.ifit.model.Treino;
import br.com.ifit.model.Usuario;

public class TreinoBusiness implements ITreinoBusiness {

    private ITreinoDao treinoDAO;
    
    public TreinoBusiness() {
    	treinoDAO = new TreinoDao();
    }

	@Override
	public Treino adicionar(Treino treino) throws BusinessException {
		try {
			treino = treinoDAO.save(treino);
			return treino;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer contar(Usuario usuario) throws BusinessException {
		try {
			return treinoDAO.contar(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
    public List<Treino> buscar(String cpf) throws SecurityException {
        try {
            return treinoDAO.buscar(cpf);
        } catch (DAOException ex) {
            throw new SecurityException(ex.getMessage());
        }
    }
    
    public Treino getPorId(String id) throws BusinessException {
        try {
			return treinoDAO.getPorId(id);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
    }
    
    public void atualizar(Treino treino) throws BusinessException {
    	try {
			treinoDAO.update(treino);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
    }
    
    public void remover(Treino treino) throws BusinessException {
    	try {
    		treinoDAO.remove(treino);
    	} catch (DAOException e) {
    		throw new BusinessException(e.getMessage());
    	}
    }
	
}
