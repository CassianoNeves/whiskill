package whiskill.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import whiskill.dao.SkillDao;
import whiskill.dao.TrilhaDao;
import whiskill.model.Skill;

@Controller
public class SkillController {

	@Inject
	SkillDao skillDao;
	
	@Inject
	TrilhaDao trilhaDao;
	
	@RequestMapping( value = "/skill/listar", method = RequestMethod.GET )
	public String skillListar( Model model ){
		model.addAttribute( "skills", skillDao.buscarTodasSkills() );
		return "skill/SkillListar";
	}
	
	@RequestMapping( value = "/skill/cadastro", method = RequestMethod.GET )
	public String skillCadastro( Model model ){
		model.addAttribute( "trilhas", trilhaDao.buscaTodasTrilhas() );
		return "skill/SkillCadastro";
	}
	
	@RequestMapping( value = "/skill/inserir", method = RequestMethod.POST )
	public String skillInserir( Model model, Skill skill, @RequestParam int idTrilha ){
		skillDao.skillInserir( skill, idTrilha );
		return "redirect:/skill/listar";
	}
	
	@RequestMapping( value = "/skill/atualizar", method = RequestMethod.GET )
	public String skillAtualizar( Model model, @RequestParam int idSkill ){
		model.addAttribute( "skill", skillDao.buscarSkillPorId( idSkill ) );
		model.addAttribute( "trilhas", trilhaDao.buscaTodasTrilhas() );
		return "skill/SkillAtualizar";
	}
	
	@RequestMapping( value = "/skill/atualizar", method = RequestMethod.POST )
	public String skillAtualizar( Model model, Skill skill, @RequestParam int idTrilha ){
		skillDao.skillAtualizar( skill, idTrilha );
		return "redirect:/skill/listar";
	}
	
	@RequestMapping( value = "skill/excluir", method = RequestMethod.GET )
	public String skillExcluir( Model model, @RequestParam int idSkill ){
		skillDao.skillExcluir( idSkill );
		return "redirect:/skill/listar";
	}
}
