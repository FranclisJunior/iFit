package br.com.ifit.business;

import java.util.List;

import javax.transaction.Transactional;

import br.com.ifit.exception.BusinessException;
import br.com.ifit.exception.DAOException;
import br.com.ifit.io.dao.IMedicaoDao;
import br.com.ifit.io.dao.MedicaoDao;
import br.com.ifit.model.Medicao;
import br.com.ifit.model.Usuario;

public class MedicaoBusiness implements IMedicaoBusiness {
	
	private IMedicaoDao medicaoDAO;
	
	public MedicaoBusiness() {
		medicaoDAO = new MedicaoDao();
	}
	
	public Integer contar(Usuario usuario) throws BusinessException {
		try {
			return medicaoDAO.contar(usuario);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
    @Transactional
	public void adicionar(Medicao medicao) throws BusinessException {
		try {
			medicaoDAO.save(medicao);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
	}
    
    public List<Medicao> buscar(String cpf) throws BusinessException {
        try {
            return medicaoDAO.buscar(cpf);
        } catch (DAOException ex) {
            throw new BusinessException(ex.getMessage());
        }
    }
    
    public Medicao getPorId(int id) throws BusinessException {
    	try {
			return medicaoDAO.getPorId(id);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
    }
    
    public void remover(Medicao medicao) throws BusinessException {
    	try {
			medicaoDAO.remove(medicao);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}
    }

	@Override
	public void atualizar(Medicao medicao) throws BusinessException {
		try {
			medicaoDAO.update(medicao);
		} catch (DAOException e) {
			throw new BusinessException(e.getMessage());
		}		
	}
}
