package whiskill.dao;


import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import whiskill.model.Colaborador;

@Component
public class ProjetoColaboradorDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
//	public List<Colaborador> bucarColaboradoresPorIdDoProjeto( int idProjeto ){
//		jdbcTemplate.query( "SELECT C.* FROM PROJETOCOLABORADOR PC "
//				+ "JOIN COLABORADOR C ON C.IDCOLABORADOR = PC.IDCOLABORADOR  "
//				+ "WHERE IDPROJETO = ?", rse)
//		
//		return null;
//	}
}
