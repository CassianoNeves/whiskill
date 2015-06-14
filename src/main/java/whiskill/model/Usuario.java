package whiskill.model;

public class Usuario {
	
	private int idUsuario;
	private String nome;
	private String email;
	private String senha;
	private boolean admin;
	
	public Usuario(){}
	
	public Usuario( String nome, String email, String senha, boolean admin ){
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.admin = admin;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
