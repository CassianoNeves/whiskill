package whiskill.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import whiskill.model.Colaborador;
import whiskill.model.Projeto;
import whiskill.model.Skill;

@Component
public class ProjetoDao {
	@Inject
	private JdbcTemplate jdbcTemplate;
	@Inject
	TrilhaDao trilhaDao;
	
	public int inserirProjeto( Projeto projeto ){
		jdbcTemplate.update( "INSERT INTO PROJETO (NOME, IMAGEMLOGO) VALUES (?, ?)",
				projeto.getNome(), projeto.getImagemLogo() );
		List<Integer> idProjeto = jdbcTemplate.query( "SELECT MAX(IDPROJETO) AS IDPROJETO  FROM PROJETO", ( ResultSet rs, int rowNum ) ->{
			
			return rs.getInt( "IDPROJETO" );
		});
		
		return idProjeto.get(0);
	}
	
	public void inserirSkillProjeto( String query ){
		jdbcTemplate.update( query );
	}
	
	
	public List<Projeto> buscaTodosProjetos(){
		
		List<Projeto> projetos = jdbcTemplate.query("SELECT IDProjeto, Nome, ImagemLogo FROM Projeto WHERE ATIVO='TRUE'", ( ResultSet rs, int rowNum ) ->{
			 Projeto projeto = new Projeto ( rs.getString( "nome" ));
			 projeto.setIdProjeto( rs.getInt( "idProjeto" ) );
			 projeto.setImagemLogo(rs.getString("ImagemLogo"));
			 return projeto;
		});
		
		return projetos;
	}
	/**
	 * Método para acesso ao banco realizando Join e trazendo as informações referentes as Skills do Projeto
	 * @return
	 */

	public Projeto buscaProjetoComSkillsPorId(int idProjeto){
		
		List<Projeto> projetos = jdbcTemplate.query("SELECT * FROM PROJETO WHERE IDPROJETO = ?", ( ResultSet rs, int rowNum ) ->{
				 Projeto projeto = new Projeto ( rs.getString( "nome"));
				 projeto.setIdProjeto( rs.getInt( "idProjeto" ) );
				 projeto.setImagemLogo(rs.getString("ImagemLogo"));
				 return projeto;
		}, idProjeto);
		
		List<Skill> skills = jdbcTemplate.query( "SELECT * FROM SKILLPROJETO SP JOIN SKILL S ON S.IDSKILL = SP.IDSKILL WHERE SP.IDPROJETO = ?", ( ResultSet rs, int rowNum ) ->{
			
			Skill skill = new Skill( rs.getInt( "IDSKILL" ), 
					rs.getString( "NOME" ),
					rs.getString( "DESCRICAO" ));
			
			skill.setTrilha( trilhaDao.buscaTrilhaPorId(rs.getInt( "trilha_id") ) );
			
			return skill;
		}, idProjeto);
		Projeto projeto = projetos.get(0);
		projeto.setSkills(skills);
		
	 return projeto;
	}
	
	
	public void excluirProjeto( int idProjeto ){
		jdbcTemplate.update( "UPDATE PROJETO SET ATIVO = 'FALSE' WHERE idProjeto = ?", idProjeto );
	}
	
	public Projeto buscaProjetoPorId( int idProjeto ){
		
		List<Projeto> projetos = jdbcTemplate.query("SELECT IdProjeto, nome FROM Projeto WHERE IdProjeto = ?", ( ResultSet rs, int rowNum ) ->{
			 Projeto projeto = new Projeto( rs.getString( "nome" ));
			 projeto.setIdProjeto( rs.getInt( "idProjeto" ) );
			 return projeto;
		},
		idProjeto);
		
		return projetos.get(0);
	}

	public void atualizarProjeto( Projeto projeto ){
		jdbcTemplate.update( "UPDATE Projeto SET NOME = ?, IMAGEMLOGO=? WHERE IDProjeto = ?", 
				projeto.getNome(),
				projeto.getImagemLogo(),
				projeto.getIdProjeto() );
	}

	// Métodos SkillProjeto
    /**
     * Inserir SkillProjeto
     * 
     */
    public void inserirSkillProjeto(int idProjeto, int idSkill){
        jdbcTemplate.update( "INSERT INTO SKILLPROJETO(IDProjeto,IDSkill) VALUES(?,?)", 
                idProjeto,
                idSkill );
    }
	/*** 
	 * Método para busca de apenas o Id e as Skills do Projeto
	 */
	public Projeto buscaSkillsPorId(int idProjeto){
		
		Projeto projeto = new Projeto();
		projeto.setIdProjeto(idProjeto);
		
		List<Skill> skills = jdbcTemplate.query( "SELECT s.IDSKILL FROM SKILLPROJETO SP JOIN SKILL S ON S.IDSKILL = SP.IDSKILL WHERE SP.IDPROJETO = ?", ( ResultSet rs, int rowNum ) ->{
			
			Skill skill = new Skill();
			skill.setIdSkill(rs.getInt( "IDSKILL" ));			
			return skill;
		}, idProjeto);
		
		projeto.setSkills(skills);
		
	 return projeto;
	}

	public int buscaIdDoProjetoPorNome(String nome) {
		
		List<Projeto> projetos = jdbcTemplate.query("SELECT idProjeto FROM Projeto WHERE nome = ?", ( ResultSet rs, int rowNum ) ->{
             Projeto projeto = new Projeto ( rs.getInt("idProjeto"));
             return projeto;
        },
        nome);
        
        return projetos.get(0).getIdProjeto();
	}
	
	public int buscarQuantidadeDeSkillsPorProjeto( int idProjeto ){
		List<Integer> quantidades = jdbcTemplate.query( "SELECT COUNT(1) AS QTDSKILLS FROM SKILLPROJETO WHERE IDPROJETO = ?", 
				( ResultSet rs, int rowNum ) ->{
			return rs.getInt( "QTDSKILLS" );
		}, idProjeto);
		
		return quantidades.get(0);
	}
	
	
	public void excluirSkillsProjeto( int idProjeto ){
		jdbcTemplate.update( "DELETE SKILLPROJETO WHERE IDPROJETO = ?", idProjeto );
	}
	
}
