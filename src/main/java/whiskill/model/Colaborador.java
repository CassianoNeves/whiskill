package whiskill.model;

import java.util.ArrayList;
import java.util.List;

public class Colaborador {

	private int idColaborador;
	private String nome;
	private List<Skill> skills = new ArrayList<Skill>();
	private String imagemPerfil;
	
	
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	// Construtores
	public Colaborador(){}
	
	public Colaborador( String nome){
		this.nome = nome;
	}
	// Getters and Setters

	public int getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(int idColaborador) {
		this.idColaborador = idColaborador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagemPerfil() {
		return imagemPerfil;
	}

	public void setImagemPerfil(String imagemPerfil) {
		this.imagemPerfil = imagemPerfil;
	}		
}
