package br.com.ifit.controller;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ifit.business.IMedicaoBusiness;
import br.com.ifit.business.MedicaoBusiness;
import br.com.ifit.exception.BusinessException;
import br.com.ifit.model.Medicao;
import br.com.ifit.model.Usuario;

@ManagedBean 
@ViewScoped
public class MedicaoBean extends DefaultBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IMedicaoBusiness medicaoBusiness;
	private Medicao medicao;
	private List<Medicao> medicoes;
	private String cpf;
	
	public MedicaoBean() {
		medicaoBusiness = new MedicaoBusiness();
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
		iniciar();
	}
	
	public List<Medicao> getMedicoes() {
		buscar();
		return medicoes;
	}	
	public void setMedicoes(List<Medicao> medicoes) {
		this.medicoes = medicoes;
	}
	public Medicao getMedicao() {
		return medicao;
	}
	
	public void iniciar() {
		medicao = new Medicao();
		medicoes = null;
	}

	public void setMedicao(Medicao medicao) {
		this.medicao = medicao;
	}	
	public Integer contar(Usuario usuario) {
		try {
			return medicaoBusiness.contar(usuario);
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
		return null;
	}
	
	public void adicionar() {
		try {
			medicao.setData(new Date());
			medicao.setUsuario(cpf);
			medicaoBusiness.adicionar(medicao);
			imprimirMensagem("Medidas adicionada com sucesso.");
			iniciar();
			fecharDialog("usuarioMedicaoDialog");
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
	}
	
    public void buscar() {
        try {
            if (medicoes == null) {
                medicoes = medicaoBusiness.buscar(cpf);
            }
        } catch (BusinessException e) {
            imprimirErro(e.getMessage());
        }
    }
    
    public List<Medicao> buscar(String cpf) {
        try {
            if (medicoes == null) {
             medicoes = medicaoBusiness.buscar(cpf);
            }
            return medicoes;
        } catch (BusinessException e) {
            imprimirErro(e.getMessage());
            return null;
        }
    }
    
    public void getPorId(int id) {
    	try {
			medicao = medicaoBusiness.getPorId(id);
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
    }
    
    public void atualizar() {
    	try {
			medicaoBusiness.atualizar(medicao);
			imprimirMensagem("Medidas atualizada com sucesso.");
			fecharDialog("usuarioMedicaoDialog");
			iniciar();
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
    }
    
    public void remover() {
    	try {
			medicaoBusiness.remover(medicao);
			imprimirMensagem("Medidas removida com sucesso.");
			fecharDialog("usuarioMedicaoDialog");
			iniciar();
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
    }
    
    public void remover(Medicao medicao) {
    	try {
			medicaoBusiness.remover(medicao);
			imprimirMensagem("Medidas removida com sucesso.");
			fecharDialog("usuarioMedicaoDialog");
			iniciar();
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
    }   
}
