package whiskill.model;

import java.util.ArrayList;
import java.util.List;

public class ColaboradorData {

	private Colaborador colaborador;
	private String dataInicio;
	private String dataFim;
	
	public ColaboradorData( Colaborador colaborador, String dataInicio, String dataFim) {
		this.colaborador = colaborador;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	
	public Colaborador getColaborador() {
		return colaborador;
	}
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
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
