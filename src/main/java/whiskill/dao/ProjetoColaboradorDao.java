package whiskill.dao;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProjetoColaboradorDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	
}
