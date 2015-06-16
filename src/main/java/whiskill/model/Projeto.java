package whiskill.model;

import java.util.ArrayList;
import java.util.List;

public class Projeto {
	// Atributos
	private int idProjeto;
	private String nome;
	private List<Skill> skills = new ArrayList<>();
	// Métodos
//	
//	public void addSkill(Skill skill){
//		this.skillsProjeto.add(skill);
//	}
	
	// Construtores
	public Projeto(){}
	
	public Projeto(int id){
		this.idProjeto = id;
	}
	
	public Projeto(String nome){
		this.nome = nome;
	}
	// Getters & Setters

	public int getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skillsProjeto) {
		this.skills = skillsProjeto;
	}
	
	

}
