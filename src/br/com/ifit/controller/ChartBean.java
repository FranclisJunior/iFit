package br.com.ifit.controller;
 
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import br.com.ifit.business.IMedicaoBusiness;
import br.com.ifit.business.MedicaoBusiness;
import br.com.ifit.exception.BusinessException;
import br.com.ifit.model.Medicao;
 
@ManagedBean
@ViewScoped
public class ChartBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int lastPos;
	private IMedicaoBusiness medicaoB;
	private List<Medicao> listMedicoes;
	private String tipoGrafico = "line";	
    
    private CartesianChartModel model;
    
    public ChartBean() {    	
    	try {    		    	
        	medicaoB = new MedicaoBusiness();
        	if(LoginBean.usuario!=null)
			listMedicoes = medicaoB.buscar(LoginBean.usuario.getCpf());
        	lastPos= 1;        	
		} catch (BusinessException e) {	}   	 
	}
          
    public CartesianChartModel getModel(){ 
    	if(tipoGrafico.equals("bar")) model = new BarChartModel();
    	else model = new LineChartModel();
    	
    	ChartSeries m = new ChartSeries();
		model.setTitle("Graficos de medidas");
		model.setLegendPosition("e");
		model.setShowPointLabels(true);
		model.getAxes().put(AxisType.X, new CategoryAxis("Data"));
		Axis yAxis = model.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(100);
    	
		if(listMedicoes==null){
			m.setLabel("Sem registro de medidas");
			return model;
		}
		
    	switch (lastPos) {
		case 1:
			m.setLabel("Peso");
			for(Medicao med: listMedicoes){	m.set(med.getData().toLocaleString() ,med.getPeso()); }
			break;			
		case 2:
			m.setLabel("Torax");
			for(Medicao med: listMedicoes){ m.set(med.getData().toLocaleString() ,med.getTorax()); }
			break;
		case 3:
			m.setLabel("Abdomen");
			for(Medicao med: listMedicoes){ m.set(med.getData().toLocaleString() ,med.getAbdomen()); }
			break;
		case 4:
			m.setLabel("Cintura");
			for(Medicao med: listMedicoes){ m.set(med.getData().toLocaleString() ,med.getCintura()); }
			break;
		case 5:
			m.setLabel("Quadril");
			for(Medicao med: listMedicoes){ m.set(med.getData().toLocaleString() ,med.getQuadril()); }
			break;	
		case 6:
			m.setLabel("Antebraço esquerdo");
			for(Medicao med: listMedicoes){ m.set(med.getData().toLocaleString() ,med.getAnteBracoEsq()); }
			ChartSeries m2 = new ChartSeries();
			m2.setLabel("Antebraço direito");
			for(Medicao med: listMedicoes){ m2.set(med.getData().toLocaleString() ,med.getAnteBracoDir()); }
			model.addSeries(m2);
			break;
		case 7:
			m.setLabel("Biceps esquerdo");
			for(Medicao med: listMedicoes){ m.set(med.getData().toLocaleString() ,med.getBicepsEsq()); }
			ChartSeries m3 = new ChartSeries();
			m3.setLabel("Biceps direito");
			for(Medicao med: listMedicoes){ m3.set(med.getData().toLocaleString() ,med.getBicepsDir()); }
			model.addSeries(m3);
			break;
		case 8:
			m.setLabel("Panturrilha esquerda");
			for(Medicao med: listMedicoes){ m.set(med.getData().toLocaleString() ,med.getPanturrilhaEsq()); }
			ChartSeries m4 = new ChartSeries();
			m4.setLabel("Panturrilha direita");
			for(Medicao med: listMedicoes){ m4.set(med.getData().toLocaleString() ,med.getPanturrilhaDir()); }
			model.addSeries(m4);
			break;
		case 9:
			m.setLabel("Coxa esquerda");
			for(Medicao med: listMedicoes){ m.set(med.getData().toLocaleString() ,med.getCoxaEsq()); }
			ChartSeries m5 = new ChartSeries();
			m5.setLabel("Coxa direita");
			for(Medicao med: listMedicoes){ m5.set(med.getData().toLocaleString() ,med.getCoxaDir()); }
			model.addSeries(m5);
			break;
		default:
			m.setLabel("Default");
			for(Medicao med: listMedicoes){	m.set(med.getData().toLocaleString() ,med.getPeso()); }
			break;
		}    	
    	model.addSeries(m);       
        return model;
    }
    
	public int getLastPos() {
		return lastPos;
	}
	
	public void setLastPos(int lastPos) {
		this.lastPos = lastPos;
	}


	public String getTipoGrafico() {
		return tipoGrafico;
	}


	public void setTipoGrafico(String tipoGrafico) {
		this.tipoGrafico = tipoGrafico;
	}   
}