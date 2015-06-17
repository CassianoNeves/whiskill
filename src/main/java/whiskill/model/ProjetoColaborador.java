package whiskill.model;

import java.sql.Date;

public class ProjetoColaborador {
	
	private Colaborador colaborador;
	private double indicacao;
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

	public double getIndicacao() {
		return indicacao;
	}
	public void setIndicacao(double indicacao) {
		this.indicacao = indicacao;
	}

}
