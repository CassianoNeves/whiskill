package whiskill.model;

import java.sql.Date;

public class ProjetoColaboradorData {

	private Projeto projeto;
	private Date dataInicio;
	private Date dataFim;
	
	
	public ProjetoColaboradorData( Projeto projeto, Date dataInicio, Date dataFim ) {
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
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
}
