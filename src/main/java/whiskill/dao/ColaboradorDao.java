package whiskill.dao;

import java.sql.ResultSet;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import whiskill.model.Colaborador;

@Component
public class ColaboradorDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	public void inserirColaborador( Colaborador colaborador ){
		jdbcTemplate.update( "INSERT INTO COLABORADOR (NOME) VALUES (?)",
				colaborador.getNome()
							);
	}
	
	public List<Colaborador> buscaTodosColaboradores(){
		
		List<Colaborador> colaboradores = jdbcTemplate.query("SELECT IDColaborador, Nome FROM Colaborador", ( ResultSet rs, int rowNum ) ->{
			 Colaborador colaborador = new Colaborador ( rs.getString( "nome" ));
			 colaborador.setIdColaborador( rs.getInt( "idColaborador" ) );
			 return colaborador;
		});
		
		return colaboradores;
	}
	
	public void excluirColaborador( int idColaborador ){
		jdbcTemplate.update( "DELETE FROM COLABORADOR WHERE idColaborador = ?", idColaborador );
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

	
}