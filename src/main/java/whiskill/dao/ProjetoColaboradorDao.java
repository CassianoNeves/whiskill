package whiskill.dao;


import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;


import javax.inject.Inject;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import whiskill.model.Colaborador;
import whiskill.model.Projeto;
import whiskill.model.ProjetoColaborador;import whiskill.model.ProjetoColaboradorData;


@Component
public class ProjetoColaboradorDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Inject
	ColaboradorDao colaboradorDao;
	
	@Inject
	ProjetoDao projetoDao;
	
	public List<Colaborador> bucarColaboradoresPorIdDoProjeto( int idProjeto ){
		return jdbcTemplate.query( "SELECT C.* FROM PROJETOCOLABORADOR PC "
				+ "JOIN COLABORADOR C ON C.IDCOLABORADOR = PC.IDCOLABORADOR  "
				+ "WHERE IDPROJETO = ?", ( ResultSet rs, int rowNum ) ->{
					Colaborador colaborador = 
							colaboradorDao.buscaColaboradorPorId(rs.getInt( "idColaborador") );
					return colaborador;
				}, idProjeto);
		
	}
	
	public List<ProjetoColaborador> buscarColaboradoresIndicadosParaProjeto( int idProjeto ){
		
		int quantidadeDeSkillsDoProjeto = 
				projetoDao.buscarQuantidadeDeSkillsPorProjeto( idProjeto );
		
		return jdbcTemplate.query( "SELECT SC.IDCOLABORADOR, COUNT(1) AS QTDSKILLS "
				+ "FROM SKILLPROJETO SP "
				+ "JOIN SKILL S ON S.IDSKILL = SP.IDSKILL "
				+ "LEFT JOIN SKILLCOLABORADOR SC ON SC.IDSKILL = S.IDSKILL "
				+ "WHERE SP.IDPROJETO = ? AND "
				+ "SC.IDCOLABORADOR NOT IN(SELECT IDCOLABORADOR "
						+ "FROM PROJETOCOLABORADOR WHERE IDPROJETO = ?) "
				+ "GROUP BY SC.IDCOLABORADOR "
				+ "ORDER BY QTDSKILLS DESC", ( ResultSet rs, int rowNum ) ->{
					
					ProjetoColaborador pc = new ProjetoColaborador();
					

					pc.setColaborador( colaboradorDao.buscaColaboradorPorId(
							rs.getInt("idColaborador") ) );
//					
//					pc.setPcd(buscarProjetoEDataInicioEFim( rs.getInt("idColaborador") ) );
//					
					int qdtSkillsColaborador = rs.getInt("QTDSKILLS");
					
					double qtdIndicacao = 
							(qdtSkillsColaborador * 100) / quantidadeDeSkillsDoProjeto;
//					
					pc.setIndicacao( qtdIndicacao );
//					
					return pc;
				},
				idProjeto,
				idProjeto);
		
	}

	
	public ProjetoColaboradorData buscarProjetoEDataInicioEFim( int idColaborador ){
		List<ProjetoColaboradorData> projetosData = jdbcTemplate.query( "SELECT * FROM PROJETOCOLABORADOR PC "
				+ "JOIN PROJETO P ON P.IDPROJETO = PC.IDPROJETO  "
				+ "WHERE IDCOLABORADOR = ?", ( ResultSet rs, int rowNum ) ->{
					Projeto projeto = new Projeto( 
							rs.getInt( "idProjeto" ),
							rs.getString( "nome"),
							rs.getString( "imagemLogo") );
					
					ProjetoColaboradorData pcd = 
							new ProjetoColaboradorData(
									projeto,
									rs.getDate( "DATAINICIO" ),
									rs.getDate( "DATAFIM" ) );
					
					return pcd;
				},
				idColaborador);
		
		return projetosData.get(0);
	}
}
