package br.com.ifit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;

@Entity

public class Treino {
	@Id
	@GeneratedValue
	private int id;
	@JoinColumn(name="USUARIO", referencedColumnName="cpf")
	private String usuario;
	@Column
	private Date data;
	@ManyToMany
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private List<ExercicioPorUsuario> exercicios;
	public List<ExercicioPorUsuario> getExercicios() {
		return exercicios;
	}
	public void setExercicios(List<ExercicioPorUsuario> exercicios) {
		this.exercicios = exercicios;
	}
	@Column
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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
	
	
}
