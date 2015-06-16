package whiskill.model;

public class Skill {
	
	private int idSkill;
	private String nome;
	private String descricao;
	private Trilha trilha;
	
	
	// Construtores
	public Skill(){}
	
	public Skill( int idSkill, String nome, String descricao ){
		this.idSkill = idSkill;
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Skill( int idSkill, String nome, String descricao, Trilha trilha ){
		this.idSkill = idSkill;
		this.nome = nome;
		this.descricao = descricao;
		this.trilha = trilha;
	}
	
		
	public int getIdSkill() {
		return idSkill;
	}
	
	public void setIdSkill(int idSkill) {
		this.idSkill = idSkill;
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
	
	public Trilha getTrilha() {
		return trilha;
	}

	public void setTrilha(Trilha trilha) {
		this.trilha = trilha;
	}
}
