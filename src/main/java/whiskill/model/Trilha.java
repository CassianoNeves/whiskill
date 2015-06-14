package whiskill.model;

public class Trilha {
	
	private int idTrilha;
	private String nome;
	private String descricao;
	
	public Trilha(){}
	
	public Trilha( String nome, String descricao ){
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public int getIdTrilha() {
		return idTrilha;
	}
	public void setIdTrilha(int idTrilha) {
		this.idTrilha = idTrilha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
