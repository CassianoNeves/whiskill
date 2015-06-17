package whiskill.model;

import java.sql.Date;

public class ProjetoColaborador {
	
	private Colaborador colaborador;
	private int indicacao;
	private ProjetoColaboradorData pcd;
	
	public ProjetoColaboradorData getPcd() {
		return pcd;
	}
	public void setPcd(ProjetoColaboradorData pcd) {
		this.pcd = pcd;
	}
	public Colaborador getColaborador() {
		return colaborador;
	}
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public int getIndicacao() {
		return indicacao;
	}
	public void setIndicacao(int indicacao) {
		this.indicacao = indicacao;
	}

}
