package whiskill.model;

public class Colaborador {

	private int idColaborador;
	private String nome;
	
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
}
