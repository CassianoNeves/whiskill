package whiskill.model;

import java.sql.Date;

public class ProjetoColaboradorData {

	private Projeto projeto;
	private String dataInicio;
	private String dataFim;
	
	
	public ProjetoColaboradorData( Projeto projeto, String dataInicio, String dataFim ) {
		this.projeto = projeto;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
}
