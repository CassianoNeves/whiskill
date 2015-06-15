package whiskill.dao;

import java.sql.ResultSet;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import whiskill.model.Trilha;

@Component
public class TrilhaDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	public void inserirTrilha( Trilha trilha ){
		jdbcTemplate.update( "INSERT INTO TRILHA (NOME, DESCRICAO) VALUES (?, ?)",
				trilha.getNome(),
				trilha.getDescricao() );
	}
	
	public List<Trilha> buscaTodasTrilhas(){
		
		List<Trilha> trilhas = jdbcTemplate.query("SELECT * FROM Trilha", ( ResultSet rs, int rowNum ) ->{
			 Trilha trilha = new Trilha ( rs.getString( "nome" ),
                    		    rs.getString( "descricao" ));
			 trilha.setIdTrilha( rs.getInt( "idTrilha" ) );
			 return trilha;
		});
		
		return trilhas;
	}
	
	public void excluirTrilha( int idTrilha ){
		jdbcTemplate.update( "DELETE FROM TRILHA WHERE idTrilha = ?", idTrilha );
	}
	
public Trilha buscaTrilhaPorId( int idTrilha ){
		
		List<Trilha> trilhas = jdbcTemplate.query("SELECT * FROM Trilha WHERE IDTRILHA = ?", ( ResultSet rs, int rowNum ) ->{
			 Trilha trilha = new Trilha ( rs.getString( "nome" ),
                    		    rs.getString( "descricao" ));
			 trilha.setIdTrilha( rs.getInt( "idTrilha" ) );
			 return trilha;
		},
		idTrilha);
		
		return trilhas.get(0);
	}

	public void atualizarTrilha( Trilha trilha ){
		jdbcTemplate.update( "UPDATE TRILHA SET NOME = ?, DESCRICAO = ? WHERE IDTRILHA = ?", 
				trilha.getNome(),
				trilha.getDescricao(),
				trilha.getIdTrilha() );
	}
}
