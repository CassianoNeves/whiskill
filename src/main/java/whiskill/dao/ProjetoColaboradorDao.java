package whiskill.dao;


import java.sql.ResultSet;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import whiskill.model.Colaborador;

@Component
public class ProjetoColaboradorDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Inject
	ColaboradorDao colaboradorDao;
	
	public List<Colaborador> bucarColaboradoresPorIdDoProjeto( int idProjeto ){
		return jdbcTemplate.query( "SELECT C.* FROM PROJETOCOLABORADOR PC "
				+ "JOIN COLABORADOR C ON C.IDCOLABORADOR = PC.IDCOLABORADOR  "
				+ "WHERE IDPROJETO = ?", ( ResultSet rs, int rowNum ) ->{
					Colaborador colaborador = 
							colaboradorDao.buscaColaboradorPorId(rs.getInt( "idColaborador") );
					return colaborador;
				}, idProjeto);
		
	}
}
