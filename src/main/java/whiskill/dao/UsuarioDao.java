package whiskill.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import whiskill.model.Usuario;

@Component
public class UsuarioDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	public Usuario existeUsuario( Usuario usuario ){
		List<Usuario> usuarios = jdbcTemplate.query( "SELECT * FROM USUARIO WHERE (NOME = ? OR EMAIL = ?) AND SENHA = ?",
				(ResultSet rs, int rowNum) -> {
					Usuario usuarioRetorno = new Usuario( rs.getString( "nome" ),
							rs.getString( "email" ),
							rs.getString( "senha" ));
					usuarioRetorno.setIdUsuario( rs.getInt( "idUsuario") );
					return usuarioRetorno;
				},
				usuario.getNome(),
				usuario.getNome(),
				usuario.getSenha() );

		if( usuarios.size() == 1 ){
			return usuarios.get(0);
		}
		return null;
	}

	public void inserirUsuario( Usuario usuario ){
		jdbcTemplate.update( "INSERT INTO USUARIO(NOME, " +
				"EMAIL, " +
				"SENHA) " +
				"VALUES (?, ?, ?)",
				usuario.getNome(),
				usuario.getEmail(),
				usuario.getSenha());
	}

	public List<Usuario> buscaTodosUsuarios(){
		return jdbcTemplate.query("SELECT * FROM USUARIO", ( ResultSet rs, int rowNum ) ->{
			Usuario usuario = new Usuario( rs.getString( "nome" ),
					rs.getString( "email" ),
					rs.getString( "senha" ));
			usuario.setIdUsuario( rs.getInt( "idUsuario" ) );
			return usuario;
		});
	}

	public void excluir( int idUsuario ){
		jdbcTemplate.update( "DELETE FROM USUARIO WHERE IDUSUARIO = ?", idUsuario );
	}

	public Usuario buscarUsuarioPorId( int idUsuario ){
		List<Usuario> usuarios = new ArrayList<>();
		usuarios = jdbcTemplate.query( "SELECT * FROM USUARIO WHERE IDUSUARIO  = ?", ( ResultSet rs, int rowNum ) ->{
			Usuario usuario = new Usuario(rs.getString( "nome" ), 
					rs.getString( "email" ), 
					rs.getString( "senha" ));
			usuario.setIdUsuario( rs.getInt( "idUsuario" ));
			return usuario;
		},
		idUsuario);
		return usuarios.get(0);
	}

	public void atualizarUsuario( Usuario usuario ){
		jdbcTemplate.update( "UPDATE USUARIO SET NOME = ?, EMAIL = ?, SENHA = ? WHERE IDUSUARIO = ?", 
				usuario.getNome(),
				usuario.getEmail(),
				usuario.getSenha(),
				usuario.getIdUsuario());
	}
}