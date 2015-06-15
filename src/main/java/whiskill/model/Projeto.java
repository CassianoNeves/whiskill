package whiskill.model;

public class Projeto {
	// Atributos
	private int idProjeto;
	private String nome;
	// Construtores
	public Projeto(){}
	
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
	
	

}
