package whiskill.dao;

import java.sql.ResultSet;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import whiskill.model.Projeto;

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

	
}
