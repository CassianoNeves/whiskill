package whiskill.dao;


import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;



import javax.inject.Inject;



import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;



import whiskill.model.Colaborador;
import whiskill.model.ColaboradorData;
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
	
	public List<ColaboradorData> bucarColaboradoresEDatasPorIdDoProjeto( int idProjeto ){
		return jdbcTemplate.query( "SELECT pc.idColaborador, to_char(pc.datafim, 'dd/MM/yyyy') as dataFim, "
				+ "to_char(pc.dataInicio, 'dd/MM/yyyy') as dataInicio"
				+ " FROM PROJETOCOLABORADOR PC "
				+ "JOIN COLABORADOR C ON C.IDCOLABORADOR = PC.IDCOLABORADOR "
				+ "WHERE IDPROJETO = ?", ( ResultSet rs, int rowNum ) ->{
					
					ColaboradorData colaborador = 
							new ColaboradorData(
									colaboradorDao.buscaColaboradorPorId(rs.getInt( "idColaborador") ),
									rs.getString( "dataInicio"),
									rs.getString( "dataFim") );
					
							return colaborador;
				}, 
				idProjeto);
		
	}
	
	public List<ProjetoColaborador> buscarColaboradoresIndicadosParaProjeto( int idProjeto ){
		
		int quantidadeDeSkillsDoProjeto = 
				projetoDao.buscarQuantidadeDeSkillsPorProjeto( idProjeto );
		
		return jdbcTemplate.query( "SELECT SC.IDCOLABORADOR, COUNT(1) AS QTDSKILLS "
				+ "FROM SKILLPROJETO SP JOIN SKILL S ON S.IDSKILL = SP.IDSKILL 	"
				+ "LEFT JOIN SKILLCOLABORADOR SC ON SC.IDSKILL = S.IDSKILL "
				+ "LEFT JOIN COLABORADOR C ON C.IDCOLABORADOR  = SC.IDCOLABORADOR "
				+ "WHERE SP.IDPROJETO = ? AND SC.IDCOLABORADOR NOT IN(SELECT IDCOLABORADOR "
										+ "FROM PROJETOCOLABORADOR WHERE IDPROJETO = ?) "
				+ "AND C.ATIVO = 'TRUE'  "
				+ "GROUP BY SC.IDCOLABORADOR "
				+ "ORDER BY QTDSKILLS DESC", ( ResultSet rs, int rowNum ) ->{
					
					ProjetoColaborador pc = new ProjetoColaborador();

					pc.setColaborador( colaboradorDao.buscaColaboradorPorId(
							rs.getInt("idColaborador") ) );
					
					pc.setPcd(buscarProjetoEDataInicioEFim( rs.getInt("idColaborador") ) );
					
					int qdtSkillsColaborador = rs.getInt("QTDSKILLS");
					pc.setIndicacao( (qdtSkillsColaborador * 100) / quantidadeDeSkillsDoProjeto );
					
					return pc;
				},
				idProjeto,
				idProjeto);
		
	}
	
	public ProjetoColaboradorData buscarProjetoEDataInicioEFim( int idColaborador ){
		List<ProjetoColaboradorData> projetosData = jdbcTemplate.query( 
				"SELECT p.idProjeto as ProjetoID, p.imagemLogo, p.nome,  to_char(pc.datainicio, 'dd/MM/yyyy') as dataInicio"
				+ ",  to_char(pc.datafim, 'dd/MM/yyyy') as dataFim "
				+ "FROM PROJETOCOLABORADOR PC "
				+ "JOIN PROJETO P ON P.IDPROJETO = PC.IDPROJETO  "
				+ "WHERE IDCOLABORADOR = ?", ( ResultSet rs, int rowNum ) ->{
					Projeto projeto = new Projeto( 
							rs.getInt( "ProjetoID" ),
							rs.getString( "nome"),
							rs.getString( "imagemLogo") );
					
					ProjetoColaboradorData pcd = 
							new ProjetoColaboradorData(
									projeto,
									rs.getString( "DATAINICIO" ),
									rs.getString( "DATAFIM" ) );
					
					return pcd;
				},
				idColaborador);
		
		if(projetosData.size() > 0 ){
			return projetosData.get(0);
		}
		
		return null;
	}
	
	public void inserirColaboradorEmProjeto( int idProjeto, int idColaborador, String dataInicio, String dataFim ){
		jdbcTemplate.update( "INSERT INTO PROJETOCOLABORADOR "
				+ "(IDPROJETO , IDCOLABORADOR , DATAINICIO , DATAFIM) "
				+ "values (?, ?, ?, ?)",
				idProjeto,
				idColaborador,
				dataInicio,
				dataFim);
	}
}
