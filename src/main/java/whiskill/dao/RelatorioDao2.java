package whiskill.dao;

import java.sql.ResultSet;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import whiskill.model.Kpi;
import whiskill.model.ProjetoColaborador;

@Component
public class RelatorioDao2 {
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Inject
	ProjetoDao projetoDao;
	
	public Kpi calcularEstadoDoProjeto( int idProjeto ){
		
		int quantidadeDeSkillsDoProjeto = 
				projetoDao.buscarQuantidadeDeSkillsPorProjeto( idProjeto );
		
		int mediaProjeto = 0;
		int qtdColaboradores = 0;
		
		List<Integer> mediaDeCadaColaborador = jdbcTemplate.query( "SELECT SC.IDCOLABORADOR, COUNT(1) AS QTDSKILLS "
				+ "FROM SKILLPROJETO SP JOIN SKILL S ON S.IDSKILL = SP.IDSKILL 	"
				+ "LEFT JOIN SKILLCOLABORADOR SC ON SC.IDSKILL = S.IDSKILL "
				+ "LEFT JOIN COLABORADOR C ON C.IDCOLABORADOR  = SC.IDCOLABORADOR "
				+ "WHERE SP.IDPROJETO = ? AND SC.IDCOLABORADOR IN(SELECT IDCOLABORADOR "
										+ "FROM PROJETOCOLABORADOR WHERE IDPROJETO = ?) "
				+ "AND C.ATIVO = 'TRUE'  "
				+ "GROUP BY SC.IDCOLABORADOR "
				+ "ORDER BY QTDSKILLS DESC", ( ResultSet rs, int rowNum ) ->{
					
					
					int qdtSkillsColaborador = rs.getInt("QTDSKILLS");
					int mediaColaborador = (qdtSkillsColaborador * 100) / quantidadeDeSkillsDoProjeto;
					
					return mediaColaborador;
				},
				idProjeto,
				idProjeto);
		
		Kpi kpi = new Kpi();
		
		if( mediaDeCadaColaborador.size() > 0){
			for ( int mediaColaborador : mediaDeCadaColaborador ) {
				qtdColaboradores++;
				mediaProjeto += mediaColaborador;
			}
			//System.out.println(mediaProjeto/qtdColaboradores);
			kpi.setPorcentagem( mediaProjeto/qtdColaboradores );
			
			if( kpi.getPorcentagem() < 40 ){
				kpi.setStatus( "Ruim" );
				kpi.setResposta( "A equipe necessita urgente de capacitação." );
			}
			else if( kpi.getPorcentagem() >= 40 && kpi.getPorcentagem() <= 79 ){
				kpi.setStatus( "Regular" );
				kpi.setResposta( "A equipe poderia ser melhor." );
			}
			else{
				kpi.setStatus( "Excelente" );
				kpi.setResposta( "A equipe está bem preparada." );
			}
		} else{
			kpi.setPorcentagem( 0 );
			kpi.setStatus( "Sem equipe" );
			kpi.setResposta( "Este projeto ainda não tem equipe.");
		}
		
		return kpi;
	}

	
}
