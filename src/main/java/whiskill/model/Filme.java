package whiskill.model;

public class Filme {

	private int idFilme;
	private String nome;
	private Genero genero;
	private int ano;
	private String sinopse;
	private String imagem;
	private int mediaNota;
	
	public Filme() {}
	
	public Filme( String nome, Genero genero, int ano, String sinopse, String imagem, int mediaNota ) {
		this.nome = nome;
		this.genero = genero;
		this.ano = ano;
		this.sinopse = sinopse;
		this.imagem = imagem;
		this.mediaNota = mediaNota;
	}
	
	public int getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(int idFilme) {
		this.idFilme = idFilme;
	}
	
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public int getMediaNota() {
		return mediaNota;
	}

	public void setMediaNota(int mediaNota) {
		this.mediaNota = mediaNota;
	}
	
	
	
}
