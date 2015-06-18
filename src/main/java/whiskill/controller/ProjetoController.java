package whiskill.controller;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import whiskill.dao.ProjetoColaboradorDao;
import whiskill.dao.ProjetoDao;
import whiskill.dao.RelatoriosDao;
import whiskill.dao.SkillDao;
import whiskill.dao.TrilhaDao;
import whiskill.model.Colaborador;
import whiskill.model.Kpi;
import whiskill.model.Projeto;

@Controller
public class ProjetoController {

	@Inject 
	ProjetoDao projetoDao;

	@Inject
	SkillDao skillDao;

	@Inject
	TrilhaDao trilhaDao;

	@Inject
	ProjetoColaboradorDao pcDao;

	@Inject
	RelatoriosDao relatoriosDao;

	@RequestMapping( value = "/projeto/cadastro", method = RequestMethod.GET )
	public String projetoCadastro(Model model){
		model.addAttribute( "skills", skillDao.buscarTodasSkills() );
		model.addAttribute( "trilhas", trilhaDao.buscaTodasTrilhas() );		
		return "/projeto/ProjetoCadastro";
	}

	@RequestMapping( value = "/projeto/perfil", method = RequestMethod.GET )
	public String projetoPerfil(Model model, @RequestParam int idProjeto ){
		// kpi
		Kpi kpi = relatoriosDao.calculaKPI(idProjeto);
		if(kpi.getStatus() == "Excelente"){
			model.addAttribute("kpiExcelente", relatoriosDao.calculaKPI(idProjeto) );
		} else if(kpi.getStatus() == "Regular"){
			model.addAttribute("kpiRegular", relatoriosDao.calculaKPI(idProjeto) );
		} else {
			model.addAttribute("kpiRuim", relatoriosDao.calculaKPI(idProjeto) );
		}

		model.addAttribute( "projeto", projetoDao.buscaProjetoComSkillsPorId( idProjeto ) );
		model.addAttribute( "trilhas", trilhaDao.buscaTodasTrilhas() );
		model.addAttribute( "ColaboradoresDoProjeto", pcDao.bucarColaboradoresEDatasPorIdDoProjeto( idProjeto) );
		model.addAttribute( "indicados", pcDao.buscarColaboradoresIndicadosParaProjeto( idProjeto ) );
		return "/projeto/ProjetoPerfil";
	}

	@ResponseBody
	@RequestMapping( value = "/projeto/inserir/projeto", method = RequestMethod.POST )
	public int projetoInserir( Projeto projeto ){
		int idCriado = projetoDao.inserirProjeto( projeto );
		return idCriado;
	}

	@ResponseBody
	@RequestMapping( value = "/projeto/inserir/SkillProjeto", method = RequestMethod.POST )
	public String projetoInserirSkillProjeto(  @RequestParam String query ){
		projetoDao.inserirSkillProjeto( query );
		return "ok";
	}

	@RequestMapping( value = "/projeto/listar", method = RequestMethod.GET )
	public String projetoListar( Model model ){
		model.addAttribute( "projetos", projetoDao.buscaTodosProjetos() );
		return "/projeto/ProjetoListar";
	}

	@RequestMapping( value = "/projeto/atualizar", method = RequestMethod.GET )
	public String projetoAtualizar( Model model, @RequestParam int idProjeto){
		model.addAttribute( "projeto", projetoDao.buscaProjetoComSkillsPorId( idProjeto ) );
		//	model.addAttribute( "skillsDoProjeto", projetoDao.buscarSkillsProjeto( idProjeto ) );
		model.addAttribute( "skills", skillDao.buscarTodasSkills() );
		model.addAttribute( "trilhas", trilhaDao.buscaTodasTrilhas() );
		return "/projeto/ProjetoAtualizar";
	}

	@ResponseBody
	@RequestMapping( value = "/projeto/atualizar", method = RequestMethod.POST )
	public String projetoAtualizar( Projeto projeto){
		projetoDao.atualizarProjeto( projeto );
		return "ok";
	}

	@RequestMapping( value = "/projeto/excluir", method = RequestMethod.GET )
	public String projetoExcluir( @RequestParam int idProjeto ){
		projetoDao.excluirProjeto( idProjeto);
		return "redirect:/projeto/listar";
	}	

	@ResponseBody
	@RequestMapping( value = "/projeto/excluirSkills", method = RequestMethod.POST )
	public String colaboradorExcluirSkills( @RequestParam int idProjeto ){
		projetoDao.excluirSkillsProjeto( idProjeto );
		return "ok";
	}	
}