package whiskill.dao;

import java.sql.ResultSet;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import whiskill.model.Colaborador;
import whiskill.model.Skill;

@Component
public class ColaboradorDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	public int inserirColaborador( Colaborador colaborador ){
		jdbcTemplate.update( "INSERT INTO COLABORADOR (NOME) VALUES (?)",
				colaborador.getNome() );
		List<Integer> idColaborador = jdbcTemplate.query( "SELECT MAX(IDCOLABORADOR) AS IDCOLABORADOR  FROM COLABORADOR", ( ResultSet rs, int rowNum ) ->{
			
			return rs.getInt( "IDCOLABORADOR" );
		});
		
		return idColaborador.get(0);
	}
	
	public List<Colaborador> buscaTodosColaboradores(){
		
		List<Colaborador> colaboradores = jdbcTemplate.query("SELECT IDColaborador, Nome FROM Colaborador WHERE ATIVO = 'TRUE'", ( ResultSet rs, int rowNum ) ->{
			 Colaborador colaborador = new Colaborador ( rs.getString( "nome" ));
			 colaborador.setIdColaborador( rs.getInt( "idColaborador" ) );
			 return colaborador;
		});
		
		return colaboradores;
	}
	
	public void excluirColaborador( int idColaborador ){
		jdbcTemplate.update( "UPDATE COLABORADOR SET ATIVO = 'FALSE' WHERE idColaborador = ?", idColaborador );
	}
	
	public Colaborador buscaColaboradorPorId( int idColaborador ){
		
		List<Colaborador> colaboradores = jdbcTemplate.query("SELECT IdColaborador, nome FROM Colaborador WHERE IdColaborador = ?", ( ResultSet rs, int rowNum ) ->{
			 Colaborador colaborador = new Colaborador ( rs.getString( "nome" ));
			 colaborador.setIdColaborador( rs.getInt( "idColaborador" ) );
			 return colaborador;
		},
		idColaborador);
		
		return colaboradores.get(0);
	}

	public void atualizarColaborador( Colaborador colaborador ){
		jdbcTemplate.update( "UPDATE Colaborador SET NOME = ? WHERE IDColaborador = ?", 
				colaborador.getNome(),
				colaborador.getIdColaborador() );
	}
	
	public void inserirSkillColaborador( String query ){
		jdbcTemplate.update( query );
	}
	
	public List<Integer> buscarSkillsColaborador( int idColaborador ){
		return jdbcTemplate.query( "SELECT *  FROM SKILLCOLABORADOR WHERE IDCOLABORADOR  = ?", ( ResultSet rs, int rowNum ) ->{
			return rs.getInt( "idSkill");
		},
		idColaborador);
	}
	
	public void excluirSkillsColaborador( int idColaborador ){
		jdbcTemplate.update( "DELETE SKILLCOLABORADOR WHERE IDCOLABORADOR = ?", idColaborador );
	}
	
}
