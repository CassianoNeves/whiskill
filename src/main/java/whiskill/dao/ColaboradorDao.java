package whiskill.dao;

import java.sql.Date;
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
	
	@Inject
	TrilhaDao trilhaDao;
	
	public int inserirColaborador( Colaborador colaborador ){
		jdbcTemplate.update( "INSERT INTO COLABORADOR (NOME, IMAGEMPERFIL) VALUES (?,?)",
				colaborador.getNome(),
				colaborador.getImagemPerfil() );		
		List<Integer> idColaborador = jdbcTemplate.query( "SELECT MAX(IDCOLABORADOR) AS IDCOLABORADOR  FROM COLABORADOR", ( ResultSet rs, int rowNum ) ->{
			
			return rs.getInt( "IDCOLABORADOR" );
		});
		
		return idColaborador.get(0);
	}
	
	public List<Colaborador> buscaTodosColaboradores(){
		
		List<Colaborador> colaboradores = jdbcTemplate.query("SELECT IDColaborador, Nome, ImagemPerfil FROM Colaborador WHERE ATIVO = 'TRUE'", ( ResultSet rs, int rowNum ) ->{
			 Colaborador colaborador = new Colaborador ( rs.getString( "nome" ));
			 colaborador.setIdColaborador( rs.getInt( "idColaborador" ) );
			 colaborador.setImagemPerfil(rs.getString("ImagemPerfil"));
			 return colaborador;
		});
		
		return colaboradores;
	}
	
	public void excluirColaborador( int idColaborador ){
		jdbcTemplate.update( "UPDATE COLABORADOR SET ATIVO = 'FALSE' WHERE idColaborador = ?", idColaborador );
	}
	
	public Colaborador buscaColaboradorPorId( int idColaborador ){
		
		List<Colaborador> colaboradores = 
				jdbcTemplate.query("SELECT IdColaborador, nome, ImagemPerfil FROM Colaborador"+
					" WHERE IdColaborador = ?", ( ResultSet rs, int rowNum ) ->{
			 Colaborador colaborador = new Colaborador ( rs.getString( "nome" ));
			 colaborador.setIdColaborador( rs.getInt( "idColaborador" ));
			 colaborador.setImagemPerfil(rs.getString("ImagemPerfil"));			 
			 ;
			 return colaborador;
		},
		idColaborador);
		
		List<Skill> skills =
				jdbcTemplate.query( "SELECT S.IDSKILL, S.NOME, S.DESCRICAO, S.TRILHA_ID  "
						+ "FROM SKILLCOLABORADOR SC JOIN SKILL S ON S.IDSKILL = SC.IDSKILL  "
						+ "WHERE SC.IDCOLABORADOR  = ?", ( ResultSet rs, int rowNum ) ->{
							
							Skill skill = new Skill( rs.getInt( "IDSKILL" ), 
									rs.getString( "NOME" ),
									rs.getString( "DESCRICAO" ));
							
							skill.setTrilha( trilhaDao.buscaTrilhaPorId(rs.getInt( "trilha_id") ) );
							
							return skill;
						},
						idColaborador);
		Colaborador colaborador = colaboradores.get(0);
		colaborador.setSkills(skills);
		return colaborador;
	}

	public void atualizarColaborador( Colaborador colaborador ){
		jdbcTemplate.update( "UPDATE Colaborador SET NOME = ?, IMAGEMPERFIL = ? WHERE IDColaborador = ?", 
				colaborador.getNome(),
				colaborador.getImagemPerfil(),
				colaborador.getIdColaborador() );
	}
	
	public void inserirSkillColaborador( String query ){
		jdbcTemplate.update( query );
	}
	
	public List<Integer> buscarSkillsColaborador( int idColaborador ){
		return jdbcTemplate.query( "SELECT idSkill  FROM SKILLCOLABORADOR WHERE IDCOLABORADOR  = ?", ( ResultSet rs, int rowNum ) ->{
			return rs.getInt( "idSkill");
		},
		idColaborador);
	}
	

	
	public void excluirSkillsColaborador( int idColaborador ){
		jdbcTemplate.update( "DELETE SKILLCOLABORADOR WHERE IDCOLABORADOR = ?", idColaborador );
	}
	
}
