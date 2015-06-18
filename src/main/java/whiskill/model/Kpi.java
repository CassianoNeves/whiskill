package whiskill.model;

public class Kpi {
	private String status;
	private String resposta;
	private int porcentagem;
	
	public Kpi(String status, String resposta, int porcentagem){
		this.status = status;
		this.resposta = resposta;
		this.porcentagem=porcentagem;
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
