package br.com.ifit.controller;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ifit.business.IPagamentoBusiness;
import br.com.ifit.business.PagamentoBusiness;
import br.com.ifit.exception.BusinessException;
import br.com.ifit.model.Pagamento;

@ManagedBean 
@ViewScoped
public class PagamentoBean extends DefaultBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private IPagamentoBusiness pagamentoBusinnes;
	private List<Pagamento> pagamentos;
	private Pagamento pagamento;
	
	private final String [] arrayMeses = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
	
	public PagamentoBean() {
		pagamentoBusinnes = new PagamentoBusiness();
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
				if(i<mesCadastro){
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
	
	public void getMensalidades(String cpf){
		try {
			pagamentos = pagamentoBusinnes.getPagamentos(cpf);
		} catch (BusinessException e) {
			imprimirErro(e.getMessage());
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
