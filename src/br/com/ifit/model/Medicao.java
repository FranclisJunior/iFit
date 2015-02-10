package br.com.ifit.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Medicao implements Serializable {
	
	@Id
	@GeneratedValue
	private int id;
	
	@JoinColumn(name="USUARIO", referencedColumnName="cpf")
	private String usuario;
	
	@Column
	private Date data;
	
	@Column
	private float altura;
	
	@Column
	private float peso;
	
	@Column
	private float torax;
	
	@Column
	private float cintura;
	
	@Column
	private float abdomen;
	
	@Column
	private float quadril;
	
	@Column
	private float anteBracoDir;
	
	@Column
	private float anteBracoEsq;
	
	@Column
	private float bicepsDir;
	
	@Column
	private float bicepsEsq;
	
	@Column
	private float panturrilhaDir;
	
	@Column
	private float panturrilhaEsq;
	
	@Column
	private float coxaDir;
	
	@Column
	private float coxaEsq;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public float getAltura() {
		return altura;
	}
	public void setAltura(float altura) {
		this.altura = altura;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getTorax() {
		return torax;
	}
	public void setTorax(float torax) {
		this.torax = torax;
	}
	public float getCintura() {
		return cintura;
	}
	public void setCintura(float cintura) {
		this.cintura = cintura;
	}
	public float getAbdomen() {
		return abdomen;
	}
	public void setAbdomen(float abdomen) {
		this.abdomen = abdomen;
	}
	public float getQuadril() {
		return quadril;
	}
	public void setQuadril(float quadril) {
		this.quadril = quadril;
	}
	public float getAnteBracoDir() {
		return anteBracoDir;
	}
	public void setAnteBracoDir(float anteBracoDir) {
		this.anteBracoDir = anteBracoDir;
	}
	public float getAnteBracoEsq() {
		return anteBracoEsq;
	}
	public void setAnteBracoEsq(float anteBracoEsq) {
		this.anteBracoEsq = anteBracoEsq;
	}
	public float getBicepsDir() {
		return bicepsDir;
	}
	public void setBicepsDir(float bicepsDir) {
		this.bicepsDir = bicepsDir;
	}
	public float getBicepsEsq() {
		return bicepsEsq;
	}
	public void setBicepsEsq(float bicepsEsq) {
		this.bicepsEsq = bicepsEsq;
	}
	public float getPanturrilhaDir() {
		return panturrilhaDir;
	}
	public void setPanturrilhaDir(float panturrilhaDir) {
		this.panturrilhaDir = panturrilhaDir;
	}
	public float getPanturrilhaEsq() {
		return panturrilhaEsq;
	}
	public void setPanturrilhaEsq(float panturrilhaEsq) {
		this.panturrilhaEsq = panturrilhaEsq;
	}
	public float getCoxaDir() {
		return coxaDir;
	}
	public void setCoxaDir(float coxaDir) {
		this.coxaDir = coxaDir;
	}
	public float getCoxaEsq() {
		return coxaEsq;
	}
	public void setCoxaEsq(float coxaEsq) {
		this.coxaEsq = coxaEsq;
	}	
}
