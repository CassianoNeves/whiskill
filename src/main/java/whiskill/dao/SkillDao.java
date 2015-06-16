package whiskill.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import whiskill.model.Skill;import whiskill.model.Trilha;


@Component
public class SkillDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Inject
	TrilhaDao trilhaDao;
	
	public void skillInserir( Skill skill, int idTrilha ){
		jdbcTemplate.update( "INSERT INTO SKILL (NOME, DESCRICAO, TRILHA_ID) values (?, ?, ?)",
				skill.getNome(),
				skill.getDescricao(),
				idTrilha );
	}
	
	public List<Skill> buscarTodasSkills(){
		return jdbcTemplate.query( "SELECT * FROM SKILL WHERE ATIVO = 'TRUE'", ( ResultSet rs, int rowNum ) ->{
			Skill skill = new Skill( rs.getInt("idSkill"),
					rs.getString( "nome" ),
					rs.getString( "descricao" ));
			
			skill.setTrilha( trilhaDao.buscaTrilhaPorId(rs.getInt( "trilha_id") ) );
			return skill;
		});	
	}
	
	public Skill buscarSkillPorId( int idSkill ){
		List<Skill> skills = new ArrayList<>();
		
		skills = jdbcTemplate.query( "SELECT * FROM SKILL WHERE IDSKILL = ?", ( ResultSet rs, int rowNum ) ->{
			Skill skill = new Skill(rs.getInt( "idSkill"),
					rs.getString( "nome" ),
					rs.getString( "descricao" ) );
			
			skill.setTrilha( trilhaDao.buscaTrilhaPorId(rs.getInt( "trilha_id") ) );
			
			return skill;
		},
		idSkill);
		
		return skills.get( 0 );
	}
	
	public void skillAtualizar( Skill skill, int idTrilha ){
		jdbcTemplate.update( "UPDATE SKILL SET NOME = ?, DESCRICAO = ?, TRILHA_ID = ?  WHERE IDSKILL = ?",
				skill.getNome(),
				skill.getDescricao(),
				idTrilha,
				skill.getIdSkill());
	}

	public void skillExcluir( int idSkill ){
		jdbcTemplate.update( "UPDATE SKILL SET ATIVO = 'FALSE' WHERE IDSKILL = ? ", idSkill );
	}
	
	
	
	
	
	
	
	
}
