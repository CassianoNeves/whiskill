package whiskill.model;

public class Avaliacao {
	
	private int idAvaliacao;
	private int idUsuario;
	private int idFilme;
	private int nota;
	
	public Avaliacao() {}
	
	public Avaliacao( int idUsuario, int idFilme, int nota ){
		this.idUsuario = idUsuario;
		this.idFilme = idFilme;
		this.nota = nota;
	}

	public int getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(int idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(int idFilme) {
		this.idFilme = idFilme;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
	
	
	

}
