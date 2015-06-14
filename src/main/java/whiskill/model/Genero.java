package whiskill.model;

public enum Genero {

	COMEDIA("Comédia"),
	ACAO("Ação"),
	AVENTURA("Aventura"),
	ADULTO("Adulto");
	
	String nome;
	private Genero( String nome ){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
