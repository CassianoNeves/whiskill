package whiskill.model;

public class Kpi {
	private String status;
	private String resposta;
	private int porcentagem;
	
	public Kpi() {}
	
	public Kpi(String status, String resposta, int porcentagem){
		this.status = status;
		this.resposta = resposta;
		this.porcentagem=porcentagem;
	}
	
	
		
	public void setStatus(String status) {
		this.status = status;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public void setPorcentagem(int porcentagem) {
		this.porcentagem = porcentagem;
	}

	// Getters and Setters
	public String getStatus() {
		return status;
	}

	public String getResposta() {
		return resposta;
	}

	public int getPorcentagem() {
		return porcentagem;
	}
}
