package whiskill.dao;

import java.util.List;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import whiskill.model.Colaborador;
import whiskill.model.Projeto;
import whiskill.model.Skill;

@Component
public class RelatoriosDao {
	
	//@Inject
	//private JdbcTemplate jdbcTemplate;
	@Inject 
	ColaboradorDao colaboradorDao;
	@Inject
	ProjetoDao projetoDao;
	@Inject
	ProjetoColaboradorDao projetoColaboradorDao;
	
	public double calculaKPI(int idProjeto){
		// Pega o Colaborador e Projeto com SKills
		Projeto projeto = projetoDao.buscaSkillsPorId(idProjeto);
			//Criar outro método pra buscar só o IDColaborador e Skills(perf)
		List<Colaborador> colaboradores = projetoColaboradorDao.bucarColaboradoresPorIdDoProjeto(idProjeto);
		// Verifica
			// int colaboradores faixa acima de 80%
		int colaboradoresExcelente=0;
			// int colaboradores faixa entre 40% e 79%
		int colaboradoresRegular=0;
			// int colaboradores faixa abaixo de 40%
		int colaboradoresRuim=0;
		
		List<Skill> skillsProjeto = projeto.getSkills();
		int countSkillsProjeto = skillsProjeto.size();
		
		for (Colaborador colaborador : colaboradores) {
			List<Skill> skillsColaborador = colaborador.getSkills();
			int skillsIguais = 0;
			for (Skill skillColaborador : skillsColaborador) {
				for (Skill skillProjeto : skillsProjeto) {
					if(skillColaborador.getIdSkill() == skillProjeto.getIdSkill() ){
						skillsIguais++;
					}					
				}
			}	
			double porcentagemSkillColaborador = (skillsIguais / countSkillsProjeto)*100;
			if(porcentagemSkillColaborador >= 80){
				colaboradoresExcelente++;
			}else if(porcentagemSkillColaborador >= 40 && porcentagemSkillColaborador <= 79){
				colaboradoresRegular++;
			}else if(porcentagemSkillColaborador <=39){
				colaboradoresRuim++;
			}
		}	
		
		int quantidadeColaboradores = colaboradores.size();
		double porExcelente = (colaboradoresExcelente / quantidadeColaboradores);
		double porRegular   = (colaboradoresRegular / quantidadeColaboradores);
		double porRuim 		= (colaboradoresRuim / quantidadeColaboradores)*100;
		
		double porcentagemExcelente = (porExcelente * 20) + 80;
		double porcentagemRegular 	= (porRegular * 39) + 40; 
		double porcentagemRuim 		= (39 * porRuim)/100;
		
		if(porcentagemExcelente >= 80){
			return porcentagemExcelente;
		} else if(porcentagemRegular >=40 && porcentagemRegular <=79 ){
			return porcentagemRegular;
		} 
		return porcentagemRuim;
	}	
}