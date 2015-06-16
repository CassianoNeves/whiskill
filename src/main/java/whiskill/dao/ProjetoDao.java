package whiskill.dao;

import java.sql.ResultSet;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import whiskill.model.Projeto;
import whiskill.model.Skill;

@Component
public class ProjetoDao {
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	public int inserirProjeto( Projeto projeto ){
		jdbcTemplate.update( "INSERT INTO PROJETO (NOME) VALUES (?)",
				projeto.getNome() );
		List<Integer> idProjeto = jdbcTemplate.query( "SELECT MAX(IDPROJETO) AS IDPROJETO  FROM PROJETO", ( ResultSet rs, int rowNum ) ->{
			
			return rs.getInt( "IDPROJETO" );
		});
		
		return idProjeto.get(0);
	}
	
	public void inserirSkillProjeto( String query ){
		jdbcTemplate.update( query );
	}
	
	
	public List<Projeto> buscaTodosProjetos(){
		
		List<Projeto> projetos = jdbcTemplate.query("SELECT IDProjeto, Nome FROM Projeto", ( ResultSet rs, int rowNum ) ->{
			 Projeto projeto = new Projeto ( rs.getString( "nome" ));
			 projeto.setIdProjeto( rs.getInt( "idProjeto" ) );
			 return projeto;
		});
		
		return projetos;
	}
	
	public void excluirProjeto( int idProjeto ){
		jdbcTemplate.update( "DELETE FROM PROJETO WHERE idProjeto = ?", idProjeto );
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
		jdbcTemplate.update( "UPDATE Projeto SET NOME = ? WHERE IDProjeto = ?", 
				projeto.getNome(),
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

	public int buscaIdDoProjetoPorNome(String nome) {
		
		List<Projeto> projetos = jdbcTemplate.query("SELECT idProjeto FROM Projeto WHERE nome = ?", ( ResultSet rs, int rowNum ) ->{
             Projeto projeto = new Projeto ( rs.getInt("idProjeto"));
             return projeto;
        },
        nome);
        
        return projetos.get(0).getIdProjeto();
	}

	public List<Integer> buscarSkillsProjeto( int idProjeto ){
		return jdbcTemplate.query( "SELECT idSkill  FROM SKILLProjeto WHERE IDProjeto  = ?", ( ResultSet rs, int rowNum ) ->{
			return rs.getInt( "idSkill");
		},
		idProjeto);
	}
	public void excluirSkillsProjeto( int idProjeto ){
		jdbcTemplate.update( "DELETE SKILLPROJETO WHERE IDPROJETO = ?", idProjeto );
	}
	
}
