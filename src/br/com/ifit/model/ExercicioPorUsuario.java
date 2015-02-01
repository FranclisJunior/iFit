package br.com.ifit.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.ifit.model.Exercicio;

@Entity

public class ExercicioPorUsuario {
	@Id
	@GeneratedValue
	private int id;
	@JoinColumn(name="USUARIO", referencedColumnName="cpf")
	private String usuario;
	@JoinColumn(name="TREINO", referencedColumnName="id")
	private int treino;
	@Column
	private String observacoes;
	@Column
	private double intervalo;
	@Column
	private int series;
	@Column
	private String dia;
	@Column
	private String repeticoes;
	@Column
	private Long carga;
	@Column
	private String nome;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getCarga() {
		return carga;
	}
	public void setCarga(Long carga) {
		this.carga = carga;
	}
	public String getRepeticoes() {
		return repeticoes;
	}
	public void setRepeticoes(String repeticoes) {
		this.repeticoes = repeticoes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public int getTreino() {
		return treino;
	}
	public void setTreino(int treino) {
		this.treino = treino;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public double getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(double intervalo) {
		this.intervalo = intervalo;
	}
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	}
}
