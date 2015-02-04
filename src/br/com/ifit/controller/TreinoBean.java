package br.com.ifit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ifit.business.ExercicioBusiness;
import br.com.ifit.business.IExercicioBusiness;
import br.com.ifit.business.ITreinoBusiness;
import br.com.ifit.business.TreinoBusiness;
import br.com.ifit.exception.BeanException;
import br.com.ifit.exception.BusinessException;
import br.com.ifit.exception.DAOException;
import br.com.ifit.model.ExercicioPorUsuario;
import br.com.ifit.model.Treino;
import br.com.ifit.model.Usuario;


@ManagedBean
@ViewScoped
public class TreinoBean extends DefaultBean {
	
	private Treino treino;
	private ITreinoBusiness treinoBusiness;
	private IExercicioBusiness exercicioBusiness;
    private List<Treino> treinos = null;
    private String cpf;
    private List<ExercicioPorUsuario> exerciciosPorUsuario;
    private List<ExercicioPorUsuario> arraySegunda;
    private List<ExercicioPorUsuario> arrayTerca;
    private List<ExercicioPorUsuario> arrayQuarta;
    private List<ExercicioPorUsuario> arrayQuinta;
    private List<ExercicioPorUsuario> arraySexta;
    private List<ExercicioPorUsuario> arraySabado;
    private List<ExercicioPorUsuario> arrayDomingo;
    private String ultimoNomeExercicio;
    
    public String getUltimoNomeExercicio() {
		return ultimoNomeExercicio;
	}

	public void setUltimoNomeExercicio(String ultimoNomeExercicio) {
		this.ultimoNomeExercicio = ultimoNomeExercicio;
	}

	public List<ExercicioPorUsuario> getArraySegunda() {
		return arraySegunda;
	}

	public void setArraySegunda(List<ExercicioPorUsuario> arraySegunda) {
		this.arraySegunda = arraySegunda;
	}

	public List<ExercicioPorUsuario> getArrayTerca() {
		return arrayTerca;
	}

	public void setArrayTerca(List<ExercicioPorUsuario> arrayTerca) {
		this.arrayTerca = arrayTerca;
	}

	public List<ExercicioPorUsuario> getArrayQuarta() {
		return arrayQuarta;
	}

	public void setArrayQuarta(List<ExercicioPorUsuario> arrayQuarta) {
		this.arrayQuarta = arrayQuarta;
	}

	public List<ExercicioPorUsuario> getArrayQuinta() {
		return arrayQuinta;
	}

	public void setArrayQuinta(List<ExercicioPorUsuario> arrayQuinta) {
		this.arrayQuinta = arrayQuinta;
	}

	public List<ExercicioPorUsuario> getArraySexta() {
		return arraySexta;
	}

	public void setArraySexta(List<ExercicioPorUsuario> arraySexta) {
		this.arraySexta = arraySexta;
	}

	public List<ExercicioPorUsuario> getArraySabado() {
		return arraySabado;
	}

	public void setArraySabado(List<ExercicioPorUsuario> arraySabado) {
		this.arraySabado = arraySabado;
	}

	public List<ExercicioPorUsuario> getArrayDomingo() {
		return arrayDomingo;
	}

	public void setArrayDomingo(List<ExercicioPorUsuario> arrayDomingo) {
		this.arrayDomingo = arrayDomingo;
	}

	private ExercicioPorUsuario exercicioPorUsuario;
    
	public ExercicioPorUsuario getExecicioPorUsuario() {
		return exercicioPorUsuario;
	}

	public void setExecicioPorUsuario(ExercicioPorUsuario execicioPorUsuario) {
		this.exercicioPorUsuario = execicioPorUsuario;
	}

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}

	public TreinoBean () {
		treinoBusiness = new TreinoBusiness();
		exercicioBusiness = new ExercicioBusiness();
	}
	
	public void iniciar() {
		exerciciosPorUsuario = new ArrayList<ExercicioPorUsuario>();
		exerciciosDia();
		this.treino = new Treino();
		this.exercicioPorUsuario = new ExercicioPorUsuario();
		this.treinos = null;
	}
	
	public void iniciarExercicios() {
		exercicioPorUsuario = new ExercicioPorUsuario();
		this.treinos = null;
	}
	
	public void salvar() {
		try {
			treino.setUsuario(cpf);
			treino.setData(new Date());
			treino = treinoBusiness.adicionar(treino);
			if (exerciciosPorUsuario != null) {
				for (ExercicioPorUsuario exercicioPorUsuario : exerciciosPorUsuario) {
					exercicioPorUsuario.setTreino(treino.getId());
					exercicioPorUsuario = exercicioBusiness.addExercicio(exercicioPorUsuario);
				}
				treino.setExercicios(exerciciosPorUsuario);
				treinoBusiness.adicionar(treino);
			}
			imprimirMensagem("Treino adicionado com sucesso.");
			iniciar();			
			fecharDialog("usuarioTreinoDialog");
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
		iniciar();
	}

	public Integer contar(Usuario usuario) {
		try {
			return treinoBusiness.contar(usuario);
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
		return null;
	}

	public ITreinoBusiness getTreinoBusiness() {
		return treinoBusiness;
	}

	public void setTreinoBusiness(ITreinoBusiness treinoBusiness) {
		this.treinoBusiness = treinoBusiness;
	}

	public List<Treino> getTreinos() throws DAOException {
		buscar();
		return treinos;
	}

	public void setTreinos(List<Treino> treinos) {
		this.treinos = treinos;
	}
	
    public void buscar() throws DAOException {
        try {
            if (treinos == null) {
                treinos = treinoBusiness.buscar(cpf);
            }
        } catch (BusinessException e) {
            imprimirErro(e.getMessage());
        }
    }
	
    public void exerciciosDia() {
		arraySegunda = new ArrayList<ExercicioPorUsuario>();
		arrayTerca = new ArrayList<ExercicioPorUsuario>();
		arrayQuarta = new ArrayList<ExercicioPorUsuario>();
		arrayQuinta = new ArrayList<ExercicioPorUsuario>();
		arraySexta = new ArrayList<ExercicioPorUsuario>();
		arraySabado = new ArrayList<ExercicioPorUsuario>();  
    	for (ExercicioPorUsuario exercicioPorUsuario : exerciciosPorUsuario) {
    		addArrayDia(exercicioPorUsuario);
    	}
    }
    
    public void addArrayDia(ExercicioPorUsuario exercicioPorUsuario) {  	
    	switch (exercicioPorUsuario.getDia()) {
    		case "Segunda-feira":
    			arraySegunda.add(exercicioPorUsuario);
    			break;
    		case "Terca-feira":
    			arrayTerca.add(exercicioPorUsuario);
    			break;
    		case "Quarta-feira":
    			arrayQuarta.add(exercicioPorUsuario);
    			break;
    		case "Quinta-feira":
    			arrayQuinta.add(exercicioPorUsuario);
    			break;
    		case "Sexta-feira":
    			arraySexta.add(exercicioPorUsuario);
    			break;
    		case "Sabado":
    			arraySabado.add(exercicioPorUsuario);
    			break;
    		case "Domingo":
    			arrayDomingo.add(exercicioPorUsuario);
    			break;
    	}
    }
    
    public void adicionarExercicioProv() {
    	try {
	    	if (exerciciosPorUsuario == null) {
	    		exerciciosPorUsuario = new ArrayList<ExercicioPorUsuario>();
	    	}
			verificarCampos();
	    	exerciciosPorUsuario.add(exercicioPorUsuario);
			exerciciosDia();
			fecharDialog("exercicioDialog");
			exercicioPorUsuario = new ExercicioPorUsuario();
			imprimirMensagem("Exercicio adicionado com sucesso.");
		} catch (BeanException e) {
			imprimirErro(e.getMessage());
		}
    }

	public ExercicioPorUsuario getExercicioPorUsuario() {
		return exercicioPorUsuario;
	}

	public void setExercicioPorUsuario(ExercicioPorUsuario exercicioPorUsuario) {
		this.exercicioPorUsuario = exercicioPorUsuario;
	}
	
    public void atualizar(String id) {
    	try {
			treino = treinoBusiness.getPorId(id);
			exerciciosPorUsuario = exercicioBusiness.getPorId(Integer.parseInt(id));
			if (exerciciosPorUsuario != null) {
				exerciciosDia();				
			}
		} catch (BusinessException e) {
			imprimirMensagem(e.getMessage());
		}
    }
    
    public void atualizarTreino() {
    	try {
    		treino.setExercicios(null);
			treinoBusiness.atualizar(treino);
    		if (exerciciosPorUsuario != null) {
    			for (ExercicioPorUsuario exercicioPorUsuario : exerciciosPorUsuario) {
    				exercicioPorUsuario.setTreino(treino.getId());
    				if (exercicioPorUsuario.getId() != 0) {
    					exercicioBusiness.atualizar(exercicioPorUsuario);
    				} else {
    					exercicioBusiness.addExercicio(exercicioPorUsuario);
    				}
    			}
    		}
    		iniciar();
    		fecharDialog("usuarioTreinoDialog");
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
    }
    
    public void remover() {
    	try {
    		treinoBusiness.remover(treino);
    		if (treino.getExercicios() != null) {
    			for (ExercicioPorUsuario exercicioPorUsuario : treino.getExercicios()) {
    				exercicioBusiness.remover(exercicioPorUsuario);
    			}
    		}
    		iniciar();
    		fecharDialog("usuarioTreinoDialog");
			imprimirMensagem("Treino removido com sucesso.");
    	} catch (BusinessException e) {
    		imprimirErro(e.getMessage());
    	}
    }
    
    public void atualizarExercicioPorId(ExercicioPorUsuario exercicio) {
    	try {
    		if (exercicio.getId() == 0) {
        		for (ExercicioPorUsuario exercicioPorUsuario : exerciciosPorUsuario) {
        			if (exercicioPorUsuario.equals(exercicio)) {
        				this.exercicioPorUsuario = exercicioPorUsuario;
        			}
        		}    			
    		} else {
    			exercicioPorUsuario = exercicioBusiness.getPorIdExercicio(exercicio.getId());
    		}
			setUltimoNomeExercicio(exercicioPorUsuario.getNome());
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
    }
    
    public void removerExercicio() {
    	try {
    		for (int i = 0; i < exerciciosPorUsuario.size(); i++) {
    			if (exerciciosPorUsuario.get(i).getNome().equals(exercicioPorUsuario.getNome())) {
    				exerciciosPorUsuario.remove(i);
    			}
    		}
    		exerciciosDia();
    		exercicioBusiness.remover(exercicioPorUsuario);
			fecharDialog("exercicioDialog");
			imprimirMensagem("Exercicio removido com sucesso.");
			iniciarExercicios();
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
    }
    
    public void atualizarExercicio() {
    	try {
    		for (int i = 0; i < exerciciosPorUsuario.size(); i++) {
    			if (exerciciosPorUsuario.get(i).getNome().equals(getUltimoNomeExercicio())) {
    				exerciciosPorUsuario.set(i, exercicioPorUsuario);
    			}
    		}
    		exerciciosDia();
    		exercicioBusiness.atualizar(exercicioPorUsuario);
			fecharDialog("exercicioDialog");
			imprimirMensagem("Exercicio atualizado com sucesso.");
			iniciarExercicios();
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
    }
    
    public void verificarCampos() throws BeanException {
    	for (ExercicioPorUsuario exercicioPorUsuario : exerciciosPorUsuario) {
    		if (exercicioPorUsuario.getNome().equals(this.exercicioPorUsuario.getNome())) {
    			throw new BeanException("Já existe um exercicio cadastrado com esse nome.");
    		}
    	}
    }
}
