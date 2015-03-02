package br.com.ifit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ifit.business.IFinanceiroBusiness;
import br.com.ifit.business.FinanceiroBusiness;
import br.com.ifit.business.IUsuarioBusiness;
import br.com.ifit.business.UsuarioBusiness;
import br.com.ifit.exception.BusinessException;
import br.com.ifit.model.Pagamento;
import br.com.ifit.model.Usuario;

@ManagedBean 
@ViewScoped
public class FinanceiroBean extends DefaultBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private IFinanceiroBusiness pagamentoBusinnes;
	private IUsuarioBusiness usuarioBusiness;
	private List<Pagamento> pagamentos;
	private Pagamento pagamento;
	
	private final String [] arrayMeses = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
	
	public FinanceiroBean() {
		pagamentoBusinnes = new FinanceiroBusiness();
		usuarioBusiness = new UsuarioBusiness();
	}
	
	public void gerarMensalidadesUsuario(String cpf){
		int mesCadastro = new Date().getMonth();
		
		for(int i=0;i<arrayMeses.length;i++){
			try {
				Pagamento pagamento = new Pagamento();
				pagamento.setUsuario(cpf);
				pagamento.setMes(arrayMeses[i]);
				pagamento.setValor(0);
				pagamento.setSituacao("Pendente");
				if((i+1)<mesCadastro){
					pagamento.setSituacao("Não Matriculado");
				}				
				pagamentoBusinnes.adicionar(pagamento);
			} catch (BusinessException e) {
				imprimirErro(e.getMessage());
			}
		}				
	}
	
	public void pagarMensalidade(){		
		try {
			if(pagamento.getValor()>0){
				pagamento.setSituacao("Pagamento Realizado");
				pagamentoBusinnes.atualizar(pagamento);
				imprimirMensagem("Pagamento realizado com sucesso.");
				fecharDialog("pagamentoDialog");
				getMensalidades(pagamento.getUsuario());
				iniciar();
			}			
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
		}
	}
	
	public void iniciar(){
		pagamento=null;
	}
	
	public List<Pagamento> getMensalidades(String cpf){
		try {
			pagamentos = pagamentoBusinnes.getPagamentos(cpf);
			return pagamentos;
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
			return null;
		}
	}
	
	
	public List<Usuario> getAlunosInadiplentes(){
		int mesAtual = new Date().getMonth();
		try {
			List<Usuario> lAlunos = usuarioBusiness.buscar("", "Aluno");
			if(lAlunos==null) return null;			
			
			List<Usuario> alunosInadiplentes = new ArrayList<Usuario>();
			for(Usuario aluno:lAlunos){
				List<Pagamento> lPagamentos = pagamentoBusinnes.getPagamentos(aluno.getCpf());
				if(lPagamentos==null) return null;
				if(lPagamentos.get(mesAtual-1).getSituacao().equals("Pendente")){
					alunosInadiplentes.add(aluno);
				}
			}		
			return alunosInadiplentes;
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
			return null;
		}
	}
	
	
	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	
	public boolean isPendente(Pagamento pagamento){
		return pagamento.getSituacao().equals("Pendente");
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

}
