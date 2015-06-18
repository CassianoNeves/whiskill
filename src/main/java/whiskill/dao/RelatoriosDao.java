package whiskill.dao;

import java.util.List;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import whiskill.model.Colaborador;
import whiskill.model.Kpi;
import whiskill.model.Projeto;
import whiskill.model.Skill;

@Component
public class RelatoriosDao {

	@Inject 
	ColaboradorDao colaboradorDao;
	@Inject
	ProjetoDao projetoDao;
	@Inject
	ProjetoColaboradorDao projetoColaboradorDao;

	public Kpi calculaKPI(int idProjeto){

		// Passo 1: Pegar os dados 
		Projeto projeto = projetoDao.buscaSkillsPorId(idProjeto);
		List<Skill> skillsProjeto = projeto.getSkills();
		int numeroSkillsProjeto = skillsProjeto.size();
		List<Colaborador> colaboradores = projetoColaboradorDao.bucarColaboradoresPorIdDoProjeto(idProjeto);		
		int numeroDeColaboradores = colaboradores.size();

		if(numeroDeColaboradores != 0){
			// Passo 2: Comparar a Skill dos colaboradores com a Skill do Projeto e os Classifique - \o/
			// Passo 2.1: Defina as Classificações
			int colaboradoresSkillsNivelExcelente 	= 0;
			int colaboradoresSkillsNivelRegular 	= 0;
			// Passo 2.2: Categorize os Colaboradores
			// Variável para contagem do número de Skills em comum de Colaborador com o Projeto.
			int numeroSkillsIguais = 0;
			for (Colaborador colaborador : colaboradores) {
				// Passo 2.2.1: Pegar as Skills do Colaborador
				List<Skill> skillsColaborador = colaborador.getSkills();
				for (Skill skillColaborador : skillsColaborador) {
					for (Skill skillProjeto : skillsProjeto) {
						if(skillColaborador.getIdSkill() == skillProjeto.getIdSkill()){
							numeroSkillsIguais++;
						}
					}
				}
				// Passo 2.2.2: Define porcentagem de Skills do Colaborador.
				int porcentagemSkillsColaborador = (numeroSkillsIguais/numeroSkillsProjeto)*100;
				// Passo 2.2.3: Incrementa a categoria na qual o Colaborador se encaixa
				if(porcentagemSkillsColaborador >= 80){
					colaboradoresSkillsNivelExcelente++;
				} else if(porcentagemSkillsColaborador >=40 && porcentagemSkillsColaborador <=79){
					colaboradoresSkillsNivelRegular++;
				} 
			}

			// Passo 3: Calcular a porcentagem do KPI - \o/
			Kpi kpi;
			int porcentagemExcelente = (colaboradoresSkillsNivelExcelente/numeroDeColaboradores)*100;
			int porcentagemRegular = (colaboradoresSkillsNivelRegular/numeroDeColaboradores)*100;
			// Excelente: se 80% ou mais da equipe do projeto tiver 80% ou mais das skills do projeto
			if(porcentagemExcelente >= 80)
				kpi = new Kpi("Excelente!","Equipe não necessita de capacitação no momento!", 100);			
			// Regular: se entre 40% e 79% da equipe do projeto tiver 40% ou mais das skills do projeto
			else if(porcentagemRegular>= 40 && porcentagemRegular<=79)
				kpi = new Kpi("Regular!","Equipe poderia se beneficiar de capacitação!", 66);
			// Ruim: se menos que 40% da equipe do projeto tiver menos que 40% das skills do projeto. 
			else 
				kpi = new Kpi("Ruim!","A equipe deste projeto necessita de capacitação!",33);

			// Passo 4: Retorne o KPI de Preferência certo - \o/	
			return kpi;
		} else
			return new Kpi("Sem equipe!","Este projeto precisa de uma equipe!", 0);
	}
}