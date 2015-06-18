package whiskill.controller;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import whiskill.dao.ColaboradorDao;
import whiskill.dao.SkillDao;
import whiskill.dao.TrilhaDao;
import whiskill.model.Colaborador;
import whiskill.model.Trilha;

@Controller
public class ColaboradorController {

	@Inject 
	ColaboradorDao colaboradorDao;

	@Inject
	SkillDao skillDao;

	@Inject
	TrilhaDao trilhaDao;

	@RequestMapping( value = "/colaborador/cadastro", method = RequestMethod.GET )
	public String colaboradorCadastro( Model model){
		model.addAttribute( "skills", skillDao.buscarTodasSkills() );
		model.addAttribute( "trilhas", trilhaDao.buscaTodasTrilhas() );
		return "/colaborador/ColaboradorCadastro";
	}

	@ResponseBody
	@RequestMapping( value = "/colaborador/inserir/colaborador", method = RequestMethod.POST )
	public int colaboradorInserirColaborador( Colaborador colaborador){
		int idCriado = colaboradorDao.inserirColaborador( colaborador );
		return idCriado;
	}

	@ResponseBody
	@RequestMapping( value = "/colaborador/inserir/SkillColaborador", method = RequestMethod.POST )
	public String colaboradorInserirSkillColaborador(  @RequestParam String query ){
		colaboradorDao.inserirSkillColaborador( query );
		return "ok";
	}

	@RequestMapping( value = "/colaborador/listar", method = RequestMethod.GET )
	public String colaboradorListar( Model model ){
		model.addAttribute( "colaboradores", colaboradorDao.buscaTodosColaboradores() );
		return "/colaborador/ColaboradorListar";
	}

	@RequestMapping( value = "/colaborador/atualizar", method = RequestMethod.GET )
	public String colaboradorAtualizar( Model model, @RequestParam int idColaborador ){
		model.addAttribute( "colaborador", colaboradorDao.buscaColaboradorPorId( idColaborador ) );
		model.addAttribute( "skills", skillDao.buscarTodasSkills() );
		model.addAttribute( "trilhas", trilhaDao.buscaTodasTrilhas() );
		return "/colaborador/ColaboradorAtualizar";
	}

	@ResponseBody
	@RequestMapping( value = "/colaborador/atualizar", method = RequestMethod.POST )
	public String colaboradorAtualizar( Colaborador colaborador ){
		colaboradorDao.atualizarColaborador( colaborador );
		return "ok";
	}

	@ResponseBody
	@RequestMapping( value = "/colaborador/excluirSkills", method = RequestMethod.POST )
	public String colaboradorExcluirSkills( @RequestParam int idColaborador ){
		colaboradorDao.excluirSkillsColaborador( idColaborador );
		return "ok";
	}

	@RequestMapping( value = "/colaborador/excluir", method = RequestMethod.GET )
	public String colaboradorExcluir( @RequestParam int idColaborador ){
		colaboradorDao.excluirColaborador( idColaborador);
		return "redirect:/colaborador/listar";
	}
}