package whiskill.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import whiskill.dao.ProjetoDao;
import whiskill.dao.SkillDao;
import whiskill.model.Projeto;

@Controller
public class ProjetoController {
	
	@Inject 
	ProjetoDao projetoDao;
	@Inject
	SkillDao skillDao;
	
	@RequestMapping( value = "/projeto/cadastro", method = RequestMethod.GET )
	public String projetoCadastro(Model model){
		model.addAttribute("skills", skillDao.buscarTodasSkills() );
		return "/projeto/ProjetoCadastro";
	}
	
	@RequestMapping( value = "/projeto/inserir", method = RequestMethod.POST )
	public String projetoInserir( Projeto projeto , @RequestParam int idSkill ){
		projetoDao.inserirProjeto( projeto );
		int idProjeto = projetoDao.buscaIdDoProjetoPorNome(projeto.getNome());
		projetoDao.inserirSkillProjeto(idProjeto, idSkill);
		return "redirect:/projeto/listar";
	}
	
	@RequestMapping( value = "/projeto/listar", method = RequestMethod.GET )
	public String projetoListar( Model model ){
		model.addAttribute( "projetos", projetoDao.buscaTodosProjetos() );
		return "/projeto/ProjetoListar";
	}
	
	@RequestMapping( value = "/projeto/atualizar", method = RequestMethod.GET )
	public String projetoAtualizar( Model model, @RequestParam int idProjeto){
		model.addAttribute( "projeto", projetoDao.buscaProjetoPorId( idProjeto ) );
		model.addAttribute("skills", skillDao.buscarTodasSkills() );
		return "/projeto/ProjetoAtualizar";
	}
	
	@RequestMapping( value = "/projeto/atualizar", method = RequestMethod.POST )
	public String projetoAtualizar( Projeto projeto, @RequestParam int idSkill ){
		projetoDao.atualizarProjeto( projeto );
		projetoDao.inserirSkillProjeto(projeto.getIdProjeto(), idSkill);
		return "redirect:/projeto/listar";
	}
	
	@RequestMapping( value = "/projeto/excluir", method = RequestMethod.GET )
	public String projetoExcluir( @RequestParam int idProjeto ){
		projetoDao.excluirProjeto( idProjeto);
		return "redirect:/projeto/listar";

	}	
}
