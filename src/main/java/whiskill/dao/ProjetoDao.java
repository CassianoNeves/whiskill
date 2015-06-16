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
	
	public void inserirProjeto( Projeto projeto ){
		jdbcTemplate.update( "INSERT INTO Projeto (NOME) VALUES (?)",
				projeto.getNome()
							);
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
     * Método para buscar Skills de um Projeto informando seu Id como parâmetro.
     * @param idProjeto
     * @return
     */
    public List<Skill> buscaSkillsProjeto(int idProjeto){
        
        List<Skill> skillsProjeto = jdbcTemplate.query("SELECT s.Nome, sp.IDSkill, s.descricao FROM SkillProjeto as sp WHERE IdProjeto = ? LEFT JOIN Skill s ON s.IDSkill = sp.idSkill", ( ResultSet rs, int rowNum ) ->{
             Skill skill = new Skill ( rs.getInt("sp.idProjeto"), rs.getString( "s.nome" ), rs.getString("s.descricao"));
             return skill;
        },
        idProjeto);
        
        return skillsProjeto;
    }
    /**
     * Inserir SkillProjeto
     * 
     */
    public void inserirSkillProjeto(int idProjeto, int idSkill){
        jdbcTemplate.update( "INSERT INTO SKILLPROJETO(IDProjeto,IDSkill) VALUES(?,?);", 
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

}
