package whiskill.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import whiskill.dao.ColaboradorDao;
import whiskill.model.Colaborador;

@Controller
public class ColaboradorController {
	
	@Inject 
	ColaboradorDao colaboradorDao;
	
	@RequestMapping( value = "/colaborador/cadastro", method = RequestMethod.GET )
	public String colaboradorCadastro(){
		return "/colaborador/ColaboradorCadastro";
	}
	
	@RequestMapping( value = "/colaborador/inserir", method = RequestMethod.POST )
	public String colaboradorInserir( Colaborador colaborador){
		colaboradorDao.inserirColaborador( colaborador );
		return "redirect:/colaborador/listar";
	}
	
	@RequestMapping( value = "/colaborador/listar", method = RequestMethod.GET )
	public String colaboradorListar( Model model ){
		model.addAttribute( "colaboradores", colaboradorDao.buscaTodosColaboradores() );
		return "/colaborador/ColaboradorListar";
	}
	
	@RequestMapping( value = "/colaborador/atualizar", method = RequestMethod.GET )
	public String colaboradorAtualizar( Model model, @RequestParam int idColaborador ){
		model.addAttribute( "colaborador", colaboradorDao.buscaColaboradorPorId( idColaborador ) );
		return "/colaborador/ColaboradorAtualizar";
	}
	
	@RequestMapping( value = "/colaborador/atualizar", method = RequestMethod.POST )
	public String colaboradorAtualizar( Colaborador colaborador ){
		colaboradorDao.atualizarColaborador( colaborador );
		return "redirect:/colaborador/listar";
	}
	
	@RequestMapping( value = "/colaborador/excluir", method = RequestMethod.GET )
	public String colaboradorExcluir( @RequestParam int idColaborador ){
		colaboradorDao.excluirColaborador( idColaborador);
		return "redirect:/colaborador/listar";
	}
}
